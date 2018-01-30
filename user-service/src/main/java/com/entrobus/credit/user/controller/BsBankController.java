package com.entrobus.credit.user.controller;

import com.entrobus.credit.common.bean.WebResult;
import com.entrobus.credit.user.services.BsBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @PostMapping(value = "/verify",produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResult verify(@RequestParam("name") String name, @RequestParam("cellphone") String cellphone, @RequestParam("idCard") String idCard, @RequestParam("bankId") String bankId){
        Map<String, String> map = new HashMap<>();
        map.put("name", name);
        map.put("cellphone", cellphone);
        map.put("idCard", idCard);
        map.put("bankId", bankId);
        return bsBankService.verify(map);
    }

}
