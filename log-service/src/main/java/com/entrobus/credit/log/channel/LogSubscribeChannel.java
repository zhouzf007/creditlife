package com.entrobus.credit.log.channel;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface LogSubscribeChannel {
    String ORDER_OPERATION_LOG = "order_operation_log_subscribe";

    @Input(ORDER_OPERATION_LOG)
    SubscribableChannel sendMsg();
}
