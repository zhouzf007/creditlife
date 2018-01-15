package com.entrobus.credit.manager.sys.service;

import com.entrobus.credit.vo.log.OperationLogMsg;
import com.entrobus.credit.vo.log.SysLoginMsg;

public interface LogService {
    /**
     * 登录日志
     * @param msg
     */
    void login(SysLoginMsg msg);
    /**
     * 记录操作日志
     */
    void operation(OperationLogMsg msg);
}
