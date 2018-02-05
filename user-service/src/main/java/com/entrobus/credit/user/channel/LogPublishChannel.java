package com.entrobus.credit.user.channel;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface LogPublishChannel {
    /**
     * 操作日志
     */
//    String OPERATION_LOG = "creditlife_operation_log_publish";
    String OPERATION_LOG = "user_operation_log_publish";



    @Output(OPERATION_LOG)
    MessageChannel operationLog();
}
