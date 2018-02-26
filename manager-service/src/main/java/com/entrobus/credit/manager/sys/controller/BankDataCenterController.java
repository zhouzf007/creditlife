package com.entrobus.credit.manager.sys.controller;

import com.entrobus.credit.common.Constants;
import com.entrobus.credit.common.bean.WebResult;
import com.entrobus.credit.manager.bank.service.OrganizationService;
import com.entrobus.credit.manager.common.bean.SysLoginUserInfo;
import com.entrobus.credit.manager.common.controller.ManagerBaseController;
import com.entrobus.credit.manager.common.service.ManagerCacheService;
import com.entrobus.credit.pojo.manager.Organization;
import org.joda.time.DateTime;
import org.joda.time.Months;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 系统角色Controller
 */
@RestController
@RequestMapping("bank/data/center")
public class BankDataCenterController extends ManagerBaseController {

    @Autowired
    private ManagerCacheService managerCacheService;
    @Autowired
    private OrganizationService organizationService;


    @RequestMapping("/getOrgName")
    public WebResult getOrgName(){
        SysLoginUserInfo userInfo = managerCacheService.getCurrLoginUser();
        //资金方
        Organization organization = organizationService.selectByPrimaryKey(userInfo.getOrgId());
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("orgName", organization.getName());
        return WebResult.ok();
    }

}
