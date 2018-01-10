package com.entrobus.credit.order.controller;

import com.entrobus.credit.common.Constants;
import com.entrobus.credit.common.util.GUIDUtil;
import com.entrobus.credit.order.services.LogService;
import com.entrobus.credit.pojo.user.UserInfo;
import com.entrobus.credit.vo.log.OrderOperationMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EurekaDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
public class ServiceBController {

    @Autowired
    EurekaDiscoveryClient discoveryClient;
    @Autowired
    private LogService logService;

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
        msg.setTime(System.currentTimeMillis());//操作时间
        msg.setOperationState(Constants.OperationState.SUCCESS);//操作状态：0-成功，1-失败，2-异常
        logService.orderLog(msg);
        return "成功";
    }
}