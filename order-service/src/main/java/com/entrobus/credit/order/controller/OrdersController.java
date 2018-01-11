package com.entrobus.credit.order.controller;

import com.entrobus.credit.common.util.GUIDUtil;
import com.entrobus.credit.pojo.order.Orders;
import com.entrobus.credit.vo.order.ApplyVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EurekaDiscoveryClient;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

@RefreshScope
@RestController
public class OrdersController {

    @Autowired
    EurekaDiscoveryClient discoveryClient;

    @Autowired
    RedisTemplate redisTemplate;


    @Value("${msg:unknown}")
    private String msg;

    @GetMapping(value = "/")
    public String printServiceB() {
        ServiceInstance serviceInstance = discoveryClient.getLocalServiceInstance();
        return serviceInstance.getServiceId() + " (" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + ")" + "===>Say " + msg;
    }
    @GetMapping(value = "/test")
    public String test() {
        return "测试";
    }


    @RequestMapping(method = RequestMethod.POST, path = "/applyLoan")
    public void apply(@RequestBody ApplyVo vo) {
        Orders order=new Orders();
        order.setId(GUIDUtil.genRandomGUID());
        order.setUserId(vo.getUserId());
        order.setApplyMoney(vo.getMoney());
        order.setApplyTime(new Date());
        order.setCreditScore(vo.getScore());
    }
}