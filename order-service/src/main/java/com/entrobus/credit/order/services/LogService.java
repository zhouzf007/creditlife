package com.entrobus.credit.order.services;

import com.entrobus.credit.vo.log.OrderLogMsg;

public interface LogService {

    void orderLog(OrderLogMsg msg);
}
