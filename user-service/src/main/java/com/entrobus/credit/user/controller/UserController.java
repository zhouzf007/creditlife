package com.entrobus.credit.user.controller;

import com.entrobus.credit.cache.CacheService;
import com.entrobus.credit.cache.Cachekey;
import com.entrobus.credit.common.Constants;
import com.entrobus.credit.common.bean.WebResult;
import com.entrobus.credit.common.util.ConversionUtil;
import com.entrobus.credit.common.util.GUIDUtil;
import com.entrobus.credit.pojo.order.CreditReport;
import com.entrobus.credit.pojo.user.UserAccount;
import com.entrobus.credit.pojo.user.UserAccountExample;
import com.entrobus.credit.pojo.user.UserInfo;
import com.entrobus.credit.pojo.user.UserInfoExample;
import com.entrobus.credit.user.client.MsgClient;
import com.entrobus.credit.user.common.controller.BaseController;
import com.entrobus.credit.user.services.CreditReportService;
import com.entrobus.credit.user.services.UserAccountService;
import com.entrobus.credit.user.services.UserCacheService;
import com.entrobus.credit.user.services.UserInfoService;
import com.entrobus.credit.user.utils.ShiroUtils;
import com.entrobus.credit.vo.order.CreditReportVo;
import com.entrobus.credit.vo.user.CacheUserInfo;
import com.entrobus.credit.vo.user.UserAccountVo;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mozl on 2018/1/09.
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private UserCacheService userCacheService;

    @Autowired
    private UserAccountService userAccountService;

    @Autowired
    MsgClient msgClient;

    @Autowired
    CreditReportService creditReportService;

    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping(value = "/login")
    public WebResult login(String cellphone, String pwd) {
        if (ConversionUtil.isContainEmptyParam(cellphone, pwd)) {
            return WebResult.fail(WebResult.CODE_PARAMETERS, "请输入账号密码");
        }
        UserInfoExample example = new UserInfoExample();
        example.createCriteria().andCellphoneEqualTo(cellphone);
        List<UserInfo> userInfos = userInfoService.selectByExample(example);
        if (userInfos == null || userInfos.size() <= 0) {
            return WebResult.fail(WebResult.CODE_OPERATION, "该手机号码尚未注册");
        }
        UserInfo userInfo = userInfos.get(0);
        String password = ShiroUtils.sha256(pwd, userInfo.getSalt());
        if (!password.equals(userInfo.getPwd())) {
            return WebResult.fail(WebResult.CODE_OPERATION, "账号或密码错误");
        }
        if (userInfo.getState() == Constants.USER_STATUS.FROZEN) {
            return WebResult.fail(WebResult.CODE_FROZEN, "该账号已冻结，无法登录");
        }
        //登录成功，生成登录token
        String token = GUIDUtil.genRandomGUID();
        //userInfo
        CacheUserInfo loginUserInfo = userInfoService.getLoginUserInfo(userInfo, token);
        return WebResult.ok().put("token", token).put(WebResult.DATA, loginUserInfo);
    }

    @GetMapping("/register")
    public WebResult register(String cellphone, String pwd, String code, String unionId) {
        if (ConversionUtil.isContainEmptyParam(cellphone, pwd)) {
            return WebResult.fail(WebResult.CODE_PARAMETERS, "请输入账号密码");
        }
        if (ConversionUtil.isContainEmptyParam(code)) {
            return WebResult.fail(WebResult.CODE_PARAMETERS, "请输入验证码");
        }
        UserInfoExample example = new UserInfoExample();
        example.createCriteria().andCellphoneEqualTo(cellphone);
        List<UserInfo> userInfos = userInfoService.selectByExample(example);
        if (userInfos != null && userInfos.size() > 0) {
            return WebResult.fail(WebResult.CODE_OPERATION, "该手机号码已注册");
        }
        String verifyCode = CacheService.getString(redisTemplate, Cachekey.Sms.VERIFICATION_CODE + cellphone);
        if (StringUtils.isNotBlank(verifyCode) && !verifyCode.equals(code)) {
            return WebResult.fail(WebResult.CODE_VERIFYCODE, "短信验证码不正确");
        }
        UserInfo userInfo = new UserInfo();
        userInfo.setCellphone(cellphone);
        userInfo.setPwd(pwd);
        userInfo.setUnionId(unionId);
        userInfoService.addUserInfo(userInfo);
        String token = GUIDUtil.genRandomGUID();
        CacheUserInfo loginUserInfo = userInfoService.getLoginUserInfo(userInfo, token);
        CacheService.delete(redisTemplate, Cachekey.Sms.VERIFICATION_CODE + cellphone);
        return WebResult.ok().put("token", token).put(WebResult.DATA, loginUserInfo);
    }

    @GetMapping("/reset")
    public WebResult reset(String cellphone, String pwd, String code) {
        if (ConversionUtil.isContainEmptyParam(cellphone, pwd)) {
            return WebResult.fail(WebResult.CODE_PARAMETERS, "请输入账号密码");
        }
        if (ConversionUtil.isContainEmptyParam(code)) {
            return WebResult.fail(WebResult.CODE_PARAMETERS, "请输入验证码");
        }
        UserInfoExample example = new UserInfoExample();
        example.createCriteria().andCellphoneEqualTo(cellphone).andDeleteFlagEqualTo(Constants.DELETE_FLAG.NO);
        List<UserInfo> userInfos = userInfoService.selectByExample(example);
        if (userInfos == null && userInfos.size() <= 0) {
            return WebResult.fail(WebResult.CODE_OPERATION, "该手机号码尚未注册");
        }
        String verifyCode = CacheService.getString(redisTemplate, Cachekey.Sms.VERIFICATION_CODE + cellphone);
        if (StringUtils.isNotBlank(verifyCode) && !verifyCode.equals(code)) {
            return WebResult.fail(WebResult.CODE_VERIFYCODE, "短信验证码不正确");
        }
        UserInfo userInfo = userInfos.get(0);
        userInfo.setPwd(ShiroUtils.sha256(pwd, userInfo.getSalt()));
        userInfo.setUpdateTime(new Date());
        userInfoService.updateByPrimaryKeySelective(userInfo);
        CacheService.delete(redisTemplate, Cachekey.Sms.VERIFICATION_CODE + cellphone);
        return WebResult.ok();
    }

    @GetMapping("/identification")
    public WebResult identification(String token) {
        CacheUserInfo userInfo = userCacheService.getUserCacheBySid(token);
        if (userInfo == null) {
            return WebResult.fail(WebResult.CODE_TOKEN);
        }
        Map map = userInfoService.isOwner(userInfo.getCellphone());
        if(map == null || StringUtils.isBlank((String) map.get("name")) || StringUtils.isBlank((String) map.get("id_numb"))){
            return WebResult.fail(WebResult.CODE_OPERATION).put(WebResult.DATA, map);
        }
        return WebResult.ok().put(WebResult.DATA, map);
    }

    @GetMapping("/subCardInfo")
    public WebResult subCardInfo(String token, String name, String idCard) {
        CacheUserInfo userInfo = userCacheService.getUserCacheBySid(token);
        if (userInfo == null) {
            return WebResult.fail(WebResult.CODE_TOKEN);
        }
        if(StringUtils.isBlank(name) || StringUtils.isBlank(idCard)){
            return WebResult.fail(WebResult.CODE_PARAMETERS);
        }
        Map map = userInfoService.isOwner(userInfo.getCellphone());
        if(map == null){
            return WebResult.fail(WebResult.CODE_OPERATION, "您在物业预留的资料不完整，无法使用该服务。请前往物业完善资料");
        }
        String name1 = (String) map.get("name");
        String idCard1 = (String) map.get("id_numb");
        if(StringUtils.isBlank(name1) || StringUtils.isBlank(idCard1) || idCard1.length() != 18 || !name.equals(name1) || idCard.equals(idCard1)){
            return WebResult.fail(WebResult.CODE_OPERATION, "您在物业预留的资料不完整，无法使用该服务。请前往物业完善资料");
        }
        UserInfo info = userInfoService.selectByPrimaryKey(userInfo.getId());
        info.setRealName(name);
        info.setIdCard(idCard);
        info.setRole(Constants.USER_ROLE.OWNER);
        userInfoService.updateByPrimaryKeySelective(info);
        userCacheService.refreshUserCache(info);
        return WebResult.ok();
    }

    @GetMapping("/sendCode")
    public WebResult sendCode(String cellphone, Integer type) {
        if (StringUtils.isEmpty(cellphone) || type == null) {
            return WebResult.fail(WebResult.CODE_PARAMETERS);
        }
        if (Constants.VERIFICATION_TYPE.REGISTER == type || Constants.VERIFICATION_TYPE.RESET_PASSWORD == type) {
            UserInfoExample example = new UserInfoExample();
            example.createCriteria().andCellphoneEqualTo(cellphone);
            List<UserInfo> userInfos = userInfoService.selectByExample(example);
            if (Constants.VERIFICATION_TYPE.REGISTER == type) {
                if (userInfos != null && userInfos.size() > 0) {
                    return WebResult.fail(WebResult.CODE_OPERATION, "该手机号码已注册");
                }
            } else if (Constants.VERIFICATION_TYPE.RESET_PASSWORD == type) {
                if (userInfos == null || userInfos.size() <= 0) {
                    return WebResult.fail(WebResult.CODE_OPERATION, "该手机号码尚未注册");
                }
            }
//            Map map = userInfoService.isOwner(cellphone);
//            if(map == null || StringUtils.isBlank((String) map.get("name"))){
//                return WebResult.fail(WebResult.CODE_OPERATION, "抱歉，您非越秀地产业主。暂时不提供此服务");
//            }
        }
        msgClient.sendVerificationCode(cellphone, "");
        return WebResult.ok();
    }

    /*
    * 获取登录用户信息，刷新缓存
    * */
    @GetMapping(value = "/info")
    public WebResult info(String token) {
        if (ConversionUtil.isContainEmptyParam(token)) {
            return WebResult.fail(WebResult.CODE_PARAMETERS);
        }
        CacheUserInfo loginUser = userCacheService.getUserCacheBySid(token);
        if (ConversionUtil.isContainEmptyParam(loginUser)) {
            return WebResult.fail(WebResult.CODE_TOKEN);
        }
        UserInfo userInfo = userInfoService.selectByPrimaryKey(loginUser.getId());
        loginUser = userInfoService.getLoginUserInfo(userInfo, token);
        return WebResult.ok().put(WebResult.DATA, loginUser);
    }

    /*
   * 判断用户的贷款状态
   * */
    @GetMapping("/userLoanState/{id}")
    public WebResult getUserLoanState(@PathVariable("id") String id) {
        if (StringUtils.isEmpty(id)) {
            return WebResult.fail(WebResult.CODE_PARAMETERS);
        }

        return WebResult.ok().put(WebResult.DATA, "");
    }

    /*
    * 添加银行卡
    * */
    @PostMapping(value = "/addAccount",produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResult addAccount(@RequestBody UserAccountVo vo) {
        CacheUserInfo userInfo = userCacheService.getUserCacheBySid(vo.getToken());
        if (userInfo == null) {
            return WebResult.fail(WebResult.CODE_TOKEN);
        }
        //请使用您本人的银行卡
        //已添加
        UserAccountExample example = new UserAccountExample();
        example.createCriteria().andUserIdEqualTo(userInfo.getId()).andAccountEqualTo(vo.getAccount()).andDeleteFlagEqualTo(Constants.DELETE_FLAG.NO);
        List<UserAccount> userAccounts = userAccountService.selectByExample(example);
        if(userAccounts != null && userAccounts.size() > 0){
            return WebResult.fail(WebResult.CODE_OPERATION, "该银行卡已添加");
        }
        //保存
        UserAccount userAccount = new UserAccount();
        try {
            BeanUtils.copyProperties(userAccount, vo);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        userAccount.setId(GUIDUtil.genRandomGUID());
        userAccount.setCreateTime(new Date());
        userAccount.setUserId(userInfo.getId());
        userAccount.setState(Constants.ACCOUNT_STATUS.WAIT);
        userAccount.setDeleteFlag(Constants.DELETE_FLAG.NO);
        userAccount.setCreateOperator(userInfo.getId());
        userAccountService.insertSelective(userAccount);
        userCacheService.refreshUserCache(userInfo.getId());
        Map<String, String> map = new HashMap<>();
        map.put("id", userAccount.getId());
        return WebResult.ok().put(WebResult.DATA, map);
    }

    /*
    * 添加银行卡验证手机号
    * */
    @GetMapping("/accountVerifyCode")
    public WebResult accountVerifyCode(String code, String cellphone, String id, String token) {
        CacheUserInfo loginUser = userCacheService.getUserCacheBySid(token);
        if (ConversionUtil.isContainEmptyParam(loginUser)) {
            return WebResult.fail(WebResult.CODE_TOKEN);
        }
        String verifyCode = CacheService.getString(redisTemplate, Cachekey.Sms.VERIFICATION_CODE + cellphone);
        if (StringUtils.isNotBlank(verifyCode) && !verifyCode.equals(code)) {
            return WebResult.fail(WebResult.CODE_VERIFYCODE);
        }
        UserAccount userAccount = new UserAccount();
        userAccount.setId(id);
        userAccount.setState(Constants.ACCOUNT_STATUS.NORMAL);
        userAccount.setUpdateTime(new Date());
        userAccount.setUpdateOperator(loginUser.getRealName());
        userAccountService.updateByPrimaryKeySelective(userAccount);
        userCacheService.refreshUserCache(loginUser.getId());
        CacheService.delete(redisTemplate, Cachekey.Sms.VERIFICATION_CODE + cellphone);
        return WebResult.ok();
    }

    /**
     * 预估额度
     * */
    @GetMapping(value = "/userCreditReport")
    public WebResult getUserCrediReport(String token) {
        CacheUserInfo loginUser = userCacheService.getUserCacheBySid(token);
        if (ConversionUtil.isContainEmptyParam(loginUser)) {
            return WebResult.fail(WebResult.CODE_TOKEN);
        }
        CreditReport creditReport = creditReportService.getCreditReportByUid(loginUser);
        CreditReportVo vo = new CreditReportVo();
        try {
            if(creditReport != null){
                BeanUtils.copyProperties(vo, creditReport);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return WebResult.ok().put(WebResult.DATA, vo);
    }

    @GetMapping(value = "/creditReport/{id}")
    public CreditReport getCrediReport(@PathVariable("id") String id) {
        CreditReport creditReport = creditReportService.selectByPrimaryKey(id);
        return creditReport;
    }

    @PutMapping(value = "/userState")
    public void updateUserState(@RequestParam("userId") String userId, @RequestParam("state") Integer state) {
        UserInfo userInfo = userInfoService.selectByPrimaryKey(userId);
        if (userInfo != null) {
            userInfo.setState(state);
            userInfoService.updateByPrimaryKey(userInfo);
            userInfoService.initUserCache(userInfo);
        }
    }

}
