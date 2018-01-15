package com.entrobus.credit.manager.sys.service;

import com.entrobus.credit.vo.log.OperationLogMsg;
import com.entrobus.credit.vo.log.SysLoginMsg;

public interface LogService {
    void login(SysLoginMsg msg);

    void operation(OperationLogMsg msg);
}
