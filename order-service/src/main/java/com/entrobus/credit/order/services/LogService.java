package com.entrobus.credit.order.services;

import com.entrobus.credit.vo.log.OperationLogMsg;
import com.entrobus.credit.vo.log.OrderOperationMsg;

public interface LogService {
    @Deprecated
    void orderLog(OrderOperationMsg msg);

    /**
     * 记录操作日志
     */
    void operation(OperationLogMsg msg);
}
