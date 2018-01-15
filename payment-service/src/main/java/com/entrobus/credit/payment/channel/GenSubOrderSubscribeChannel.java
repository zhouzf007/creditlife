package com.entrobus.credit.payment.channel;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Component;

/**
 * @Author: zhouzf
 * @CreateTime: 2017/6/9 10:29
 * @Description:
 */
public interface GenSubOrderSubscribeChannel {

    String GENERATE_SUB_ORDER_SUBSCRIBE = "generate_sub_order_subscribe";

    @Input(GENERATE_SUB_ORDER_SUBSCRIBE)
    SubscribableChannel generateSubOrder();

}
