package com.entrobus.credit.user.controller;

import com.entrobus.credit.common.util.RedisUtil;
import com.entrobus.credit.user.services.AService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RefreshScope
@RestController
public class ServiceAController {

    @Value("${name:unknown}")
    private String name;

    @Autowired
    private AService aService;

    @Autowired
    RedisTemplate redisTemplate;

    @GetMapping(value = "/")
    public String printServiceA() {
        return aService.printServiceA(name);
//        ServiceInstance serviceInstance = discoveryClient.getLocalServiceInstance();
//        return serviceInstance.getServiceId() + " (" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + ")" + "===>name:" + name + "<br/>" + serviceBClient.printServiceB();
    }

    @GetMapping(path = "/current")
    public Principal getCurrentAccount(Principal principal) {
        return principal;
    }

    @RequestMapping(value = "/setRedis")
    public void setRedis(String k,String v) {
        RedisUtil.setString(redisTemplate, k, v);
    }

    @RequestMapping(value = "/getRedis")
    public String getRedis(String k) {
        return redisTemplate.opsForValue().get(k).toString();
    }


}