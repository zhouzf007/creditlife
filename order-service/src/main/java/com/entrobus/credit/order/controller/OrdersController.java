package com.entrobus.credit.order.controller;

import com.entrobus.credit.common.Constants;
import com.entrobus.credit.common.util.GUIDUtil;
import com.entrobus.credit.order.services.LogService;
import com.entrobus.credit.pojo.user.UserInfo;
import com.entrobus.credit.vo.log.OrderOperationMsg;
import com.entrobus.credit.common.util.GUIDUtil;
import com.entrobus.credit.pojo.order.Orders;
import com.entrobus.credit.vo.order.ApplyVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EurekaDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

import java.util.Date;

@RefreshScope
@RestController
public class OrdersController {

    @Autowired
    EurekaDiscoveryClient discoveryClient;
    @Autowired
    private LogService logService;

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
    @PostMapping("/log")
    public String log(){
        //订单操作 日志
        OrderOperationMsg msg = new OrderOperationMsg();
        msg.setDesc("测试");//操作说明：自定义,如 提交申请（创建订单）、审核 等

        UserInfo userInfo = new UserInfo();//测试用
        userInfo.setId(GUIDUtil.genRandomGUID());
        userInfo.setIdCard(GUIDUtil.genRandomGUID());
        msg.setOperationData(userInfo);//操作参数

        msg.setOperatorId("111");// 操作人id
        msg.setOrderId("1111");//订单id
        msg.setOrderState(1);//操作完成后的订单状态
//        msg.setRemark("dsds");//备注（1024）：自定义，如：超时、定时操作等

        msg.setRemark("dsds");//备注（1024）：自定义，如：超时、定时操作等
        msg.setTime(new Date());//操作时间
        msg.setOperationState(Constants.OperationState.SUCCESS);//操作状态：0-成功，1-失败，2-异常
        logService.orderLog(msg);
        return "成功";
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