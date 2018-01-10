package com.entrobus.credit.order.services.impl;

import com.entrobus.credit.order.channel.LogPublishChannel;
import com.entrobus.credit.order.services.LogService;
import com.entrobus.credit.vo.log.OrderOperationMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class LogServiceImpl implements LogService{
    @Autowired
    private LogPublishChannel logPublishChannel;
    @Override
    public void orderLog(OrderOperationMsg msg) {
        logPublishChannel.orderOperationLog().send(MessageBuilder.withPayload(msg).build());
    }
}
