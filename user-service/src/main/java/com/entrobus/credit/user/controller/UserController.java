package com.entrobus.credit.user.controller;

import com.entrobus.credit.pojo.order.CreditReport;
import com.entrobus.credit.pojo.user.UserInfo;
import com.entrobus.credit.user.common.controller.BaseController;
import com.entrobus.credit.user.services.CreditReportService;
import com.entrobus.credit.user.services.UserInfoService;
import com.entrobus.credit.vo.user.CacheUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by mozl on 2018/1/09.
 */
@RestController
@RequestMapping("/api")
public class UserController extends BaseController {

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

}
