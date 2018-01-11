package com.entrobus.credit.order.services;

import com.entrobus.credit.vo.log.OrderOperationMsg;

public interface LogService {

    void orderLog(OrderOperationMsg msg);
}
