package com.entrobus.credit.order.channel;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface GenSubOrderChannel {

    String GENERATE_SUB_ORDER_PUBLISH = "generate_sub_order_publish";

    @Output(GENERATE_SUB_ORDER_PUBLISH)
    MessageChannel generateSubOrder();
}
