package com.entrobus.credit.manager.sys.service.impl;

import com.entrobus.credit.manager.common.channel.LogPublishChannel;
import com.entrobus.credit.manager.sys.service.LogService;
import com.entrobus.credit.vo.log.SysLoginMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class LogServiceImpl implements LogService {
    @Autowired
    private LogPublishChannel logPublishChannel;

    @Override
    public void login(SysLoginMsg msg) {
        Message<SysLoginMsg> message = buildMessage(msg);
        logPublishChannel.sysLoginLog().send(message);
    }
    protected  <T> Message<T> buildMessage(T msg) {
        return MessageBuilder.withPayload(msg).build();
    }
}
