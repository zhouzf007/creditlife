package com.entrobus.credit.order.services.impl;

import com.entrobus.credit.common.Constants;
import com.entrobus.credit.order.dao.OrdersMapper;
import com.entrobus.credit.order.services.OrdersService;
import com.entrobus.credit.pojo.order.Orders;
import com.entrobus.credit.pojo.order.OrdersExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersServiceImpl implements OrdersService {
    @Autowired
    private OrdersMapper ordersMapper;

    private static final Logger logger = LoggerFactory.getLogger(OrdersServiceImpl.class);

    public int countByExample(OrdersExample example) {
        int count = this.ordersMapper.countByExample(example);
        logger.debug("count: {}", count);
        return count;
    }

    public Orders selectByPrimaryKey(String id) {
        return this.ordersMapper.selectByPrimaryKey(id);
    }

    public List<Orders> selectByExample(OrdersExample example) {
        return this.ordersMapper.selectByExample(example);
    }

    public int deleteByPrimaryKey(String id) {
        return this.ordersMapper.deleteByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(Orders record) {
        return this.ordersMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(Orders record) {
        return this.ordersMapper.updateByPrimaryKey(record);
    }

    public int deleteByExample(OrdersExample example) {
        return this.ordersMapper.deleteByExample(example);
    }

    public int updateByExampleSelective(Orders record, OrdersExample example) {
        return this.ordersMapper.updateByExampleSelective(record, example);
    }

    public int updateByExample(Orders record, OrdersExample example) {
        return this.ordersMapper.updateByExample(record, example);
    }

    public int insert(Orders record) {
        return this.ordersMapper.insert(record);
    }

    public int insertSelective(Orders record) {
        return this.ordersMapper.insertSelective(record);
    }

    @Override
    public List<Orders> getUserOrders(String userId) {
        OrdersExample example = new OrdersExample();
        example.createCriteria().andDeleteFlagEqualTo(Constants.DeleteFlag.NO).
                andUserIdEqualTo(userId);
        return this.ordersMapper.selectByExample(example);
    }

    @Override
    public Orders getUserLastOrder(String userId) {
        OrdersExample example = new OrdersExample();
        example.createCriteria().andDeleteFlagEqualTo(Constants.DeleteFlag.NO).
                andUserIdEqualTo(userId);
        example.setOrderByClause(" create_time desc ");
        List<Orders> list=this.ordersMapper.selectByExample(example);
        if (!list.isEmpty()){
            return  list.get(0);
        }
        return null;
    }
}