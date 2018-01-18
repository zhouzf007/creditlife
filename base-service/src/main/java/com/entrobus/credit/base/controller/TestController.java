package com.entrobus.credit.base.controller;

import com.entrobus.credit.common.bean.WebResult;
import com.entrobus.credit.vo.base.BsStaticVo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//@RefreshScope
@RestController
@RequestMapping("/test")
public class TestController {
//    @Value("${base.test.name}")
    private String name;
//    @Value("${base.test.password}")
    private String password;

    @GetMapping("/config")
    public WebResult testConfig(){
        return WebResult.ok().put("name",name).put("password",password);
    }
    @PostMapping("/validate")
    public WebResult validate(@Validated BsStaticVo vo){
        return WebResult.ok();
    }
}
