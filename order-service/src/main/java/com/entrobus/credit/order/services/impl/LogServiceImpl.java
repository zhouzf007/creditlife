package com.entrobus.credit.order.services.impl;

import com.alibaba.fastjson.JSON;
import com.entrobus.credit.order.channel.LogPublishChannel;
import com.entrobus.credit.order.services.LogService;
import com.entrobus.credit.vo.log.OperationLogMsg;
import com.entrobus.credit.vo.log.OrderOperationMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RefreshScope
public class LogServiceImpl implements LogService{
    private static final Logger logger = LoggerFactory.getLogger(LogServiceImpl.class);
    @Autowired
    private LogPublishChannel logPublishChannel;
    @Value("${spring.application.name}")
    private String applicationName;
    @Override
    @Deprecated
    public void orderLog(OrderOperationMsg msg) {
//        if (msg.getTime() == null) msg.setTime(new Date());
//        logPublishChannel.orderOperationLog().send(buildMessage(msg));
        operation(msg);
    }

    /**
     * 记录操作日志
     *
     * @param msg
     */
    @Override
    public void operation(OperationLogMsg msg) {
        try {
            if (msg.getTime() == null) msg.setTime(new Date());
            msg.setApplicationName(applicationName);
            Message<OperationLogMsg> message = buildMessage(msg);
            logPublishChannel.operationLog().send(message);
        } catch (Exception e) {
            logger.error(String.format("操作日志发布失败,消息内容：%s", JSON.toJSONString(msg)), e);
        }
    }

    protected  <T> Message<T> buildMessage(T msg) {
        return MessageBuilder.withPayload(msg).build();
    }
}
