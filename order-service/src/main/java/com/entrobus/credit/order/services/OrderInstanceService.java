package com.entrobus.credit.order.services;

import com.entrobus.credit.pojo.order.OrderInstance;
import com.entrobus.credit.pojo.order.OrderInstanceExample;
import com.entrobus.credit.pojo.order.Orders;

import java.util.List;

public interface OrderInstanceService {
    int countByExample(OrderInstanceExample example);

    OrderInstance selectByPrimaryKey(String id);

    List<OrderInstance> selectByExample(OrderInstanceExample example);

    int deleteByPrimaryKey(String id);

    int updateByPrimaryKeySelective(OrderInstance record);

    int updateByPrimaryKey(OrderInstance record);

    int deleteByExample(OrderInstanceExample example);

    int updateByExampleSelective(OrderInstance record, OrderInstanceExample example);

    int updateByExample(OrderInstance record, OrderInstanceExample example);

    int insert(OrderInstance record);

    int insertSelective(OrderInstance record);

    int saveOrderInstance(Orders record);
}