package com.entrobus.credit.order.services;

import com.entrobus.credit.pojo.order.Orders;
import com.entrobus.credit.pojo.order.OrdersExample;

import java.util.List;

public interface OrdersService {
    int countByExample(OrdersExample example);

    Orders selectByPrimaryKey(String id);

    List<Orders> selectByExample(OrdersExample example);

    int deleteByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Orders record);

    int updateByPrimaryKey(Orders record);

    int deleteByExample(OrdersExample example);

    int updateByExampleSelective(Orders record, OrdersExample example);

    int updateByExample(Orders record, OrdersExample example);

    int insert(Orders record);

    int insertSelective(Orders record);

    List<Orders> getUserOrders(String userId);
}