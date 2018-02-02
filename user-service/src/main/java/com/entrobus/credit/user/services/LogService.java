package com.entrobus.credit.user.services;

import com.entrobus.credit.vo.log.OperationLogMsg;
import com.entrobus.credit.vo.log.OrderOperationMsg;

public interface LogService {



    /**
     * 记录操作日志
     */
    void operation(OperationLogMsg msg);
}
