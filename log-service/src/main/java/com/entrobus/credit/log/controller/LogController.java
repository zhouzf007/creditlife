package com.entrobus.credit.log.controller;

import com.entrobus.credit.log.service.OperationLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class LogController {
    @Autowired
    private OperationLogService operationLogService;

    @PostMapping(value = "/garbage")
    public int clear(){
        return operationLogService.clear();
    }
}
