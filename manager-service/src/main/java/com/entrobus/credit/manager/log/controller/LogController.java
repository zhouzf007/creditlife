package com.entrobus.credit.manager.log.controller;

import com.alibaba.fastjson.JSONObject;
import com.entrobus.credit.common.bean.WebResult;
import com.entrobus.credit.manager.client.LogClient;
import com.entrobus.credit.manager.common.bean.SysLoginUserInfo;
import com.entrobus.credit.manager.common.controller.ManagerBaseController;
import com.entrobus.credit.manager.common.service.ManagerCacheService;
import com.entrobus.credit.manager.sys.service.SysUserService;
import com.entrobus.credit.pojo.manager.SysUser;
import com.entrobus.credit.vo.log.BankOperationLogVo;
import com.entrobus.credit.vo.log.LogQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/log")
public class LogController extends ManagerBaseController{
    @Autowired
    private LogClient logClient;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private ManagerCacheService managerCacheService;

    @RequestMapping("/bankOperationLog")
    public WebResult bankOperationLogList(int offset, int limit){
        SysLoginUserInfo loginUser = getCurrLoginUser();
//        vo.setOrgId(loginUser.getOrgId());
        WebResult result = logClient.bankOperationLogList(loginUser.getOrgId(), offset, limit);
        return result;
    }
}
