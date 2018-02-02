package com.entrobus.credit.log.controller;

import com.entrobus.credit.common.bean.WebResult;
import com.entrobus.credit.common.util.ServletUtil;
import com.entrobus.credit.log.service.ClientLogService;
import com.entrobus.credit.pojo.log.ClientLog;
import com.entrobus.credit.vo.common.CommonParameter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class LogApiController {
    @Autowired
    private ClientLogService clientLogService;

    /**
     * 客户端上传日志
     * @param form
     * @param parameter
     * @param request
     * @return
     */
    @PostMapping(value = "/client")
    public WebResult clientLog(@RequestParam String form, CommonParameter parameter, HttpServletRequest request){
        if (StringUtils.isBlank(form)) return WebResult.fail(WebResult.CODE_PARAMETERS);
        ClientLog log = new ClientLog();
        log.setAddress(ServletUtil.getIpAddr(request));
        log.setContent(form);
        if (StringUtils.isNotBlank(parameter.getClient()))
            log.setClient(Integer.valueOf(parameter.getClient()));
        clientLogService.insertSelective(log);
        return WebResult.ok();
    }


}
