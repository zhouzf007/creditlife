package com.entrobus.credit.msg.services;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface MySink {

    String INPUT = "myinput";

    @Input(org.springframework.cloud.stream.messaging.Sink.INPUT)
    SubscribableChannel input();

}