package com.entrobus.credit.log.channel;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface LogSubscribeChannel {
    //订单日志
    String ORDER_OPERATION_LOG = "order_operation_log_subscribe";
    //后台用户登录日志
    String SYS_LONIN_LOG = "sys_lonin_log_subscribe";
    //通用登录日志
    String OPERATION_LOG = "creditlife_operation_log_subscribe";

    @Input(ORDER_OPERATION_LOG)
    SubscribableChannel orderOperationLog();

    @Input(SYS_LONIN_LOG)
    SubscribableChannel sysLoginLog();

    @Input(OPERATION_LOG)
    SubscribableChannel operationLog();
}
