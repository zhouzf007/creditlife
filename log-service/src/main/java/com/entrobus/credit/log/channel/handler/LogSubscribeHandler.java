package com.entrobus.credit.log.channel.handler;

import com.alibaba.fastjson.JSON;
import com.entrobus.credit.log.channel.LogSubscribeChannel;
import com.entrobus.credit.log.service.OrderOperationLogService;
import com.entrobus.credit.vo.log.OrderLogMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

@EnableBinding(LogSubscribeChannel.class)
public class LogSubscribeHandler {
    private final static Logger logger = LoggerFactory.getLogger(LogSubscribeHandler.class);
    @Autowired
    private OrderOperationLogService orderOperationLogService;
    @StreamListener(LogSubscribeChannel.ORDER_OPERATION_LOG)
    public void orderOperationLog(OrderLogMsg msg){
//        orderOperationLogService.
        //todo
        logger.info(JSON.toJSONString(msg));
        orderOperationLogService.logMsg(msg);

    }
}
