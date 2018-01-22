package com.entrobus.credit.base.controller;

import com.alibaba.fastjson.JSON;
import com.entrobus.credit.base.service.BsStaticsCacheService;
import com.entrobus.credit.common.Constants;
import com.entrobus.credit.common.bean.WebResult;
import com.entrobus.credit.vo.base.BsStaticVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RefreshScope
@RestController
@RequestMapping("/test")
public class TestController {
//    @Value("${base.test.name}")
    private String name;
//    @Value("${base.test.password}")
    private String password;
    @Autowired
    private BsStaticsCacheService bsStaticsCacheService;

    @GetMapping("/config")
    public WebResult testConfig(){
        return WebResult.ok().put("name",name).put("password",password);
    }
    @PostMapping("/validate")
    public WebResult validate(@RequestBody @Validated BsStaticVo vo){
        return WebResult.ok();
    }
    @GetMapping("/testCache")
    public WebResult testCache(){
        WebResult ok = WebResult.ok();
        BsStaticVo bsStaticVo = bsStaticsCacheService.getByTypeAndValue(Constants.CODE_TYPE.CODE_TYPE, "CODE_TYPE");
        ok.put("getByTypeAndValue", bsStaticVo);
        BsStaticVo r2  = bsStaticsCacheService.getById(bsStaticVo.getId());
        ok.put("getById", r2);
        List<BsStaticVo> r3  = bsStaticsCacheService.getByType(bsStaticVo.getCodeType());
        ok.put("getByType", r3);
        BsStaticVo r4  = bsStaticsCacheService.getOrCache(Constants.CODE_TYPE.CODE_TYPE, "CODE_TYPE");
        ok.put("getOrCache", r4);
        BsStaticVo r5  = bsStaticsCacheService.getOrCacheById(bsStaticVo.getId());
        ok.put("getOrCacheById", r5);
        List<BsStaticVo> r6  = bsStaticsCacheService.getOrCacheByType(bsStaticVo.getCodeType());
        ok.put("getOrCacheByType", r6);
       String r7  = bsStaticsCacheService.getOrCacheName(bsStaticVo.getCodeType(),bsStaticVo.getCodeValue());
        ok.put("getOrCacheName", r7);

        return ok;
    }
}
