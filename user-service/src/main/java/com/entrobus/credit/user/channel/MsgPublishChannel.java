package com.entrobus.credit.user.channel;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;

/**
 * @Author: zhouzf
 * @CreateTime: 2018/01/04 10:29
 */
@Component
public interface MsgPublishChannel {

    String SMS = "sms";

    @Output(SMS)
    MessageChannel sendMsg();
}
