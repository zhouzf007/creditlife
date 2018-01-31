package com.entrobus.credit.manager.sys.service.impl;

import com.alibaba.fastjson.JSON;
import com.entrobus.credit.manager.common.channel.LogPublishChannel;
import com.entrobus.credit.manager.sys.service.LogService;
import com.entrobus.credit.vo.log.OperationLogMsg;
import com.entrobus.credit.vo.log.SysLoginMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
public class LogServiceImpl implements LogService {
    @Value("${spring.application.name}")
    private String applicationName;
    @Autowired
    private LogPublishChannel logPublishChannel;
    private static final Logger logger = LoggerFactory.getLogger(LogServiceImpl.class);

    /**
     * 登录日志
     * @param msg
     */
    @Override
    public void login(SysLoginMsg msg) {
        try {
            if (msg.getLoginTime() == null) msg.setLoginTime(new Date());
            Message<SysLoginMsg> message = buildMessage(msg);
            logPublishChannel.sysLoginLog().send(message);
        } catch (Exception e) {
            logger.error(String.format("登陆日志发布失败,消息内容：%s", JSON.toJSONString(msg)), e);
        }
    }
    /**
     * 记录操作日志
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

    /**
     * 构建消息
     * @param msg
     * @param <T>
     * @return
     */
    protected <T> Message<T> buildMessage(T msg) {
        return MessageBuilder.withPayload(msg).build();
    }
}
