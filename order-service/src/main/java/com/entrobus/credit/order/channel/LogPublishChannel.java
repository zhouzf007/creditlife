package com.entrobus.credit.order.channel;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface LogPublishChannel {
    String ORDER_OPERATION_LOG_PUBLISH = "order_operation_log_publish";

    @Output(ORDER_OPERATION_LOG_PUBLISH)
    MessageChannel orderOperationLog();
}
