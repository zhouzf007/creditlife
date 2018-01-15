package com.entrobus.credit.user.controller;

import com.entrobus.credit.cache.CacheService;
import com.entrobus.credit.cache.Cachekey;
import com.entrobus.credit.common.bean.WebResult;
import com.entrobus.credit.common.util.ConversionUtil;
import com.entrobus.credit.common.util.GUIDUtil;
import com.entrobus.credit.pojo.user.UserAccount;
import com.entrobus.credit.pojo.user.UserInfo;
import com.entrobus.credit.pojo.user.UserInfoExample;
import com.entrobus.credit.user.bean.CacheUserInfo;
import com.entrobus.credit.user.client.MsgClient;
import com.entrobus.credit.user.common.controller.BaseController;
import com.entrobus.credit.user.services.UserAccountService;
import com.entrobus.credit.user.services.UserInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utils.Constants;
import utils.ShiroUtils;

import java.util.Date;
import java.util.List;

/**
 * Created by mozl on 2018/1/09.
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private UserAccountService userAccountService;

    @Autowired
    MsgClient msgClient;

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping("/login")
    public WebResult login(String cellphone, String pwd){
        if(ConversionUtil.isContainEmptyParam(cellphone, pwd)){
            return WebResult.error("请输入账号密码");
        }
        UserInfoExample example = new UserInfoExample();
        example.createCriteria().andCellphoneEqualTo(cellphone);
        List<UserInfo> userInfos = userInfoService.selectByExample(example);
        if(userInfos == null || userInfos.size() <= 0){
            return WebResult.error("该手机号码尚未注册");
        }
        UserInfo userInfo = userInfos.get(0);
        String password = ShiroUtils.sha256(pwd, userInfo.getSalt());
        if(!password.equals(userInfo.getPwd())){
            return WebResult.error("账号或密码错误");
        }
        if(userInfo.getState() == Constants.USER_STATUS.FROZEN){
            return WebResult.error("该账号已冻结，无法登录");
        }
        //登录成功，生成登录token
        String token = GUIDUtil.genRandomGUID();
        //userInfo
        CacheUserInfo loginUserInfo = userInfoService.getLoginUserInfo(userInfo, token);
        return WebResult.ok().put("token",token).put(WebResult.DATA, loginUserInfo);
    }

    @RequestMapping("/register")
    public WebResult register(String cellphone, String pwd, String code, String unionId){
        if(ConversionUtil.isContainEmptyParam(cellphone, pwd)){
            return WebResult.error("请输入账号密码");
        }
        if(ConversionUtil.isContainEmptyParam(code)){
            return WebResult.error("请输入验证码");
        }
        UserInfoExample example = new UserInfoExample();
        example.createCriteria().andCellphoneEqualTo(cellphone);
        List<UserInfo> userInfos = userInfoService.selectByExample(example);
        if(userInfos != null && userInfos.size() > 0){
            return WebResult.error("该手机号码已注册");
        }
        String verifyCode = CacheService.getCacheObj(redisTemplate, Cachekey.Sms.VERIFICATION_CODE + cellphone, String.class);
        if(StringUtils.isNotBlank(verifyCode) && !verifyCode.equals(code)){
            return WebResult.error("短信验证码不正确");
        }
        UserInfo userInfo = new UserInfo();
        userInfo.setCellphone(cellphone);
        userInfo.setPwd(pwd);
        userInfo.setUnionId(unionId);
        userInfoService.addUserInfo(userInfo);
        return WebResult.ok();
    }

    @RequestMapping("/reset")
    public WebResult reset(String cellphone, String pwd, String code){
        if(ConversionUtil.isContainEmptyParam(cellphone, pwd)){
            return WebResult.error("请输入账号密码");
        }
        if(ConversionUtil.isContainEmptyParam(code)){
            return WebResult.error("请输入验证码");
        }
        UserInfoExample example = new UserInfoExample();
        example.createCriteria().andCellphoneEqualTo(cellphone);
        List<UserInfo> userInfos = userInfoService.selectByExample(example);
        if(userInfos == null && userInfos.size() <= 0){
            return WebResult.error("该手机号码尚未注册");
        }
        String verifyCode = CacheService.getCacheObj(redisTemplate, Cachekey.Sms.VERIFICATION_CODE + cellphone, String.class);
        if(StringUtils.isNotBlank(verifyCode) && !verifyCode.equals(code)){
            return WebResult.error("短信验证码不正确");
        }
        UserInfo userInfo = userInfos.get(0);
        userInfo.setPwd(ShiroUtils.sha256(pwd, userInfo.getSalt()));
        userInfo.setUpdateTime(new Date());
        userInfoService.updateByPrimaryKeySelective(userInfo);
        return WebResult.ok();
    }

    @RequestMapping("/sendCode")
    public WebResult sendCode(String cellphone, Integer type){
        if(type != null || type == 3){
            UserInfoExample example = new UserInfoExample();
            example.createCriteria().andCellphoneEqualTo(cellphone);
            List<UserInfo> userInfos = userInfoService.selectByExample(example);
            if(type == 1){
                if(userInfos != null && userInfos.size() > 0){
                    return WebResult.error("该手机号码已注册");
                }
            }else if(type == 2){
                if(userInfos == null || userInfos.size() <= 0){
                    return WebResult.error("该手机号码尚未注册");
                }
            }
        }
        //判断是否为业主
        //发送验证码
        String verifyCode = msgClient.sendVerificationCode(cellphone, "");
        CacheService.setCacheObjExpir(redisTemplate, Cachekey.Sms.VERIFICATION_CODE+cellphone, verifyCode, 1000*60*30);
        return WebResult.ok();
    }

    /*
    * 获取登录用户信息，刷新缓存
    * */
    @RequestMapping("/info")
    public WebResult info(String token){
        if(ConversionUtil.isContainEmptyParam(token)){
            return WebResult.error("传入参数有错误");
        }
        CacheUserInfo loginUser = getCurrLoginUser();
        if(ConversionUtil.isContainEmptyParam(loginUser)){
            return WebResult.error("token不合法或已过期");
        }
        UserInfo userInfo = userInfoService.selectByPrimaryKey(loginUser.getId());
        loginUser = userInfoService.getLoginUserInfo(userInfo, token);
        return WebResult.ok().put(WebResult.DATA, loginUser);
    }

    /*
   * 判断用户的贷款状态
   * */
    @RequestMapping("/userLoanState/{id}")
    public WebResult getUserLoanState(@PathVariable("id") String id){
        if(StringUtils.isEmpty(id)){
            return WebResult.error("传入参数有错误");
        }

        return WebResult.ok().put(WebResult.DATA, "");
    }

    /*
    * 添加银行卡
    * */
    @RequestMapping("/addAccount")
    public WebResult addAccount(UserAccount userAccount){
        CacheUserInfo loginUser = getCurrLoginUser();
        if(ConversionUtil.isContainEmptyParam(loginUser)){
            return WebResult.error("token不合法或已过期");
        }
        //请使用您本人的银行卡

        //保存
        userAccount.setId(GUIDUtil.genRandomGUID());
        userAccount.setCreateTime(new Date());
        userAccount.setUserId(loginUser.getId());
        userAccount.setState(Constants.ACCOUNT_STATUS.WAIT);
        userAccount.setDeleteFlag(Constants.DELETEFLAG.NORMAL);
        userAccount.setCreateOperator(loginUser.getId());
        userAccountService.insertSelective(userAccount);
        return WebResult.ok().put(WebResult.DATA, userAccount.getId());
    }

    /*
    * 添加银行卡验证手机号
    * */
    @RequestMapping("/accountVerifyCode")
    public WebResult accountVerifyCode(String code, String cellphone, String id){
        CacheUserInfo loginUser = getCurrLoginUser();
        if(ConversionUtil.isContainEmptyParam(loginUser)){
            return WebResult.error("token不合法或已过期");
        }
        String verifyCode = CacheService.getCacheObj(redisTemplate, Cachekey.Sms.VERIFICATION_CODE + cellphone, String.class);
        if(StringUtils.isNotBlank(verifyCode) && !verifyCode.equals(code)){
            return WebResult.error("验证码错误");
        }
        UserAccount userAccount = new UserAccount();
        userAccount.setId(id);
        userAccount.setState(Constants.ACCOUNT_STATUS.NORMAL);
        userAccount.setUpdateTime(new Date());
        userAccount.setUpdateOperator(loginUser.getId());
        userAccountService.updateByPrimaryKeySelective(userAccount);
        return WebResult.ok();
    }

}
