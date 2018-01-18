package com.entrobus.credit.user.controller;

import com.entrobus.credit.common.bean.WebResult;
import com.entrobus.credit.user.services.BsBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mozl on 2018/1/18.
 */
@RestController
@RequestMapping("/bsBank")
public class BsBankController {

    @Autowired
    private BsBankService bsBankService;

    @RequestMapping(value = "/verify")
    public WebResult verify(){
        Map<String, String> map = new HashMap<>();
        map.put("name", "111");
        map.put("cellphone", "111");
        map.put("idCard", "111");
        map.put("bankId", "111");
        bsBankService.verify(map);
        return WebResult.ok();
    }

}
