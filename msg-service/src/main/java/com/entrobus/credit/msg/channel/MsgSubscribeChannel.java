package com.entrobus.credit.msg.channel;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * @Author: zhouzf
 * @CreateTime: 2017/6/9 10:29
 * @Description:
 */
public interface MsgSubscribeChannel {

    String SMS_SUBSCRIBE = "sms_subscribe";

    @Input(SMS_SUBSCRIBE)
    SubscribableChannel sendMsg();

}
