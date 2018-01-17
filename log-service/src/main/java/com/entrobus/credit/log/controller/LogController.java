package com.entrobus.credit.log.controller;

import com.entrobus.credit.common.util.RequestUtil;
import com.entrobus.credit.log.service.ClientLogService;
import com.entrobus.credit.log.service.OperationLogService;
import com.entrobus.credit.pojo.log.ClientLog;
import com.entrobus.credit.vo.common.CommonParameter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class LogController {
    @Autowired
    private OperationLogService operationLogService;
    @Autowired
    private ClientLogService clientLogService;

    @PostMapping(value = "/garbage")
    public int clear(){
        return operationLogService.clear();
    }

    @PostMapping(value = "/client")
    public void clientLog(@RequestParam String form, CommonParameter parameter, HttpServletRequest request){
        if (StringUtils.isBlank(form)) return;
        ClientLog log = new ClientLog();
        log.setAddress(RequestUtil.getIpAddr(request));
        log.setContent(form);
        if (StringUtils.isNotBlank(parameter.getClient()))
            log.setClient(Integer.valueOf(parameter.getClient()));
        clientLogService.insertSelective(log);
    }
}
