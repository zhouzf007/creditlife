package com.entrobus.credit.manager.channel;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface LogPublishChannel {
    /**
     * 登录日志
     */
    String SYS_LONIN_LOG = "sys_lonin_log_publish";

    @Output(SYS_LONIN_LOG)
    MessageChannel sysLoginLog();
}
