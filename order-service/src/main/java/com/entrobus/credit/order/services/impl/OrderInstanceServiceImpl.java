package com.entrobus.credit.order.services.impl;

import com.entrobus.credit.order.dao.OrderInstanceMapper;
import com.entrobus.credit.order.services.OrderInstanceService;
import com.entrobus.credit.pojo.order.OrderInstance;
import com.entrobus.credit.pojo.order.OrderInstanceExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderInstanceServiceImpl implements OrderInstanceService {
    @Autowired
    private OrderInstanceMapper orderInstanceMapper;

    private static final Logger logger = LoggerFactory.getLogger(OrderInstanceServiceImpl.class);

    public int countByExample(OrderInstanceExample example) {
        int count = this.orderInstanceMapper.countByExample(example);
        logger.debug("count: {}", count);
        return count;
    }

    public OrderInstance selectByPrimaryKey(String id) {
        return this.orderInstanceMapper.selectByPrimaryKey(id);
    }

    public List<OrderInstance> selectByExample(OrderInstanceExample example) {
        return this.orderInstanceMapper.selectByExample(example);
    }

    public int deleteByPrimaryKey(String id) {
        return this.orderInstanceMapper.deleteByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(OrderInstance record) {
        return this.orderInstanceMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(OrderInstance record) {
        return this.orderInstanceMapper.updateByPrimaryKey(record);
    }

    public int deleteByExample(OrderInstanceExample example) {
        return this.orderInstanceMapper.deleteByExample(example);
    }

    public int updateByExampleSelective(OrderInstance record, OrderInstanceExample example) {
        return this.orderInstanceMapper.updateByExampleSelective(record, example);
    }

    public int updateByExample(OrderInstance record, OrderInstanceExample example) {
        return this.orderInstanceMapper.updateByExample(record, example);
    }

    public int insert(OrderInstance record) {
        return this.orderInstanceMapper.insert(record);
    }

    public int insertSelective(OrderInstance record) {
        return this.orderInstanceMapper.insertSelective(record);
    }
}