package com.entrobus.credit.user.controller;

import com.entrobus.credit.cache.CacheService;
import com.entrobus.credit.cache.Cachekey;
import com.entrobus.credit.common.bean.WebResult;
import com.entrobus.credit.common.util.ConversionUtil;
import com.entrobus.credit.common.util.GUIDUtil;
import com.entrobus.credit.pojo.user.UserInfo;
import com.entrobus.credit.pojo.user.UserInfoExample;
import com.entrobus.credit.user.bean.CacheUserInfo;
import com.entrobus.credit.user.client.MsgClient;
import com.entrobus.credit.user.common.controller.BaseController;
import com.entrobus.credit.user.services.UserInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.Cache;
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
        return WebResult.ok().put("token",token).put("data", loginUserInfo);
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
        if(!verifyCode.equals(code)){
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
        if(!verifyCode.equals(code)){
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
        if(type != null){
            UserInfoExample example = new UserInfoExample();
            example.createCriteria().andCellphoneEqualTo(cellphone);
            List<UserInfo> userInfos = userInfoService.selectByExample(example);
            if(type == 1){
                if(userInfos != null && userInfos.size() > 0){
                    return WebResult.error("该手机号码已注册");
                }
            }else{
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
        return WebResult.ok().put("data", loginUser);
    }

    /*
   * 判断用户的贷款状态
   * */
    @RequestMapping("/userLoanState/{id}")
    public WebResult getUserLoanState(@PathVariable("id") String id){
        if(StringUtils.isEmpty(id)){
            return WebResult.error("传入参数有错误");
        }

        return WebResult.ok().put("data", "");
    }

}
