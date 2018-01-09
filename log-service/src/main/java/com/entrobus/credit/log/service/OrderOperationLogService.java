package com.entrobus.credit.log.service;

import com.entrobus.credit.pojo.log.OrderOperationLog;
import com.entrobus.credit.pojo.log.OrderOperationLogExample;
import com.entrobus.credit.vo.log.OrderLogMsg;

import java.util.List;

public interface OrderOperationLogService {
    int countByExample(OrderOperationLogExample example);

    OrderOperationLog selectByPrimaryKey(String id);

    List<OrderOperationLog> selectByExample(OrderOperationLogExample example);

    int deleteByPrimaryKey(String id);

    int updateByPrimaryKeySelective(OrderOperationLog record);

    int updateByPrimaryKey(OrderOperationLog record);

    int deleteByExample(OrderOperationLogExample example);

    int updateByExampleSelective(OrderOperationLog record, OrderOperationLogExample example);

    int updateByExample(OrderOperationLog record, OrderOperationLogExample example);

    int insert(OrderOperationLog record);

    int insertSelective(OrderOperationLog record);

    List<OrderOperationLog> selectByExampleWithBLOBs(OrderOperationLogExample example);

    int logMsg(OrderLogMsg msg);

//    int updateByExampleWithBLOBs(OrderOperationLog record, OrderOperationLogExample example);
//
//    int updateByPrimaryKeyWithBLOBs(OrderOperationLog record);
}