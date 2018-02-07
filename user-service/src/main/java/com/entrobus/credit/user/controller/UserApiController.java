package com.entrobus.credit.user.controller;

import com.entrobus.credit.pojo.order.CreditReport;
import com.entrobus.credit.pojo.user.UserInfo;
import com.entrobus.credit.user.services.CreditReportService;
import com.entrobus.credit.user.services.UserInfoService;
import com.entrobus.credit.vo.user.CacheUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserApiController {

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    CreditReportService creditReportService;

    /**
     * 预估额度
     */
    @GetMapping(value = "/userCreditReport")
    public CreditReport getUserCrediReport(@RequestParam("userId") String userId) {
        CacheUserInfo user = new CacheUserInfo();
        user.setId(userId);
        CreditReport creditReport = creditReportService.getCreditReportByUid(user);
        if (creditReport==null){

        }
        return creditReport;
    }

    @GetMapping(value = "/creditReport")
    public CreditReport getCrediReport(@RequestParam("id") String id) {
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

    @PutMapping(value = "/userQuta")
    public void updateUserQuta(@RequestParam("userId") String userId, @RequestParam("quta") Long quta) {
        UserInfo userInfo = userInfoService.selectByPrimaryKey(userId);
        if (userInfo != null) {
            userInfo.setQuota(userInfo.getQuota()+quta>0?userInfo.getQuota()+quta:0);
            userInfoService.updateByPrimaryKey(userInfo);
            userInfoService.initUserCache(userInfo);
        }
    }
}
