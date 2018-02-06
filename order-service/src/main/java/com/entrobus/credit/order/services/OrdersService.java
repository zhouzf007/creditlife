package com.entrobus.credit.order.services;

import com.entrobus.credit.common.bean.WebResult;
import com.entrobus.credit.pojo.order.Orders;
import com.entrobus.credit.pojo.order.OrdersExample;
import com.entrobus.credit.vo.order.ApplyVo;
import com.entrobus.credit.vo.order.UserOrderListVo;
import com.entrobus.credit.vo.order.OrderListVo;
import com.entrobus.credit.vo.order.UserOrdersVo;
import com.entrobus.credit.vo.user.CacheUserInfo;

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

    WebResult applyOrder(ApplyVo vo, CacheUserInfo userInfo) throws Exception;

    List<Orders> getUserOrders(String userId,Integer offset, Integer limit);

    Orders getUserLastOrder(String userId);

    WebResult getUserOrderList(List<Integer> states,String key,  String orgId, Integer offset, Integer limit) throws Exception;

    WebResult getOrderList(List<Integer> states,String searchKey, String orgId, Integer offset, Integer limit) throws Exception;

    //接口使用
    List<UserOrdersVo> getUserOrderList(String id, Integer offset, Integer limit) throws Exception;

    List<Orders> getDistinctUser(OrdersExample example);
}