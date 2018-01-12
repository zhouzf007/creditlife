package com.entrobus.credit.log.service.impl;

import com.alibaba.fastjson.JSON;
import com.entrobus.credit.common.util.GUIDUtil;
import com.entrobus.credit.log.dao.OrderOperationLogMapper;
import com.entrobus.credit.log.service.OrderOperationLogService;
import com.entrobus.credit.pojo.log.OrderOperationLog;
import com.entrobus.credit.pojo.log.OrderOperationLogExample;
import com.entrobus.credit.vo.log.OrderOperationMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderOperationLogServiceImpl implements OrderOperationLogService {
    @Autowired
    private OrderOperationLogMapper orderOperationLogMapper;

    private static final Logger logger = LoggerFactory.getLogger(OrderOperationLogServiceImpl.class);

    public int countByExample(OrderOperationLogExample example) {
        int count = this.orderOperationLogMapper.countByExample(example);
        logger.debug("count: {}", count);
        return count;
    }

    public OrderOperationLog selectByPrimaryKey(String id) {
        return this.orderOperationLogMapper.selectByPrimaryKey(id);
    }

    public List<OrderOperationLog> selectByExample(OrderOperationLogExample example) {
        return this.orderOperationLogMapper.selectByExample(example);
    }

    public int deleteByPrimaryKey(String id) {
        return this.orderOperationLogMapper.deleteByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(OrderOperationLog record) {
        return this.orderOperationLogMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(OrderOperationLog record) {
        return this.orderOperationLogMapper.updateByPrimaryKey(record);
    }

    public int deleteByExample(OrderOperationLogExample example) {
        return this.orderOperationLogMapper.deleteByExample(example);
    }

    public int updateByExampleSelective(OrderOperationLog record, OrderOperationLogExample example) {
        return this.orderOperationLogMapper.updateByExampleSelective(record, example);
    }

    public int updateByExample(OrderOperationLog record, OrderOperationLogExample example) {
        return this.orderOperationLogMapper.updateByExample(record, example);
    }

    public int insert(OrderOperationLog record) {
        defaultValue(record);
        return this.orderOperationLogMapper.insert(record);
    }

    public int insertSelective(OrderOperationLog record) {
        defaultValue(record);
        return this.orderOperationLogMapper.insertSelective(record);
    }

    private void defaultValue(OrderOperationLog record) {
        if (record.getId() == null) record.setId(GUIDUtil.genRandomGUID());
        if (record.getCreateTime() == null) record.setCreateTime(new Date());
//        if (record.getOperationTime() == null) record.setOperationTime(new Date());
    }

    @Override
    public   List<OrderOperationLog> selectByExampleWithBLOBs(OrderOperationLogExample example){
        return this.orderOperationLogMapper.selectByExampleWithBLOBs(example);
    }
//    @Override
//    public int updateByExampleWithBLOBs(OrderOperationLog record, OrderOperationLogExample example){
//        return this.orderOperationLogMapper.updateByExampleWithBLOBs(record,example);
//    }
//    @Override
//    public int updateByPrimaryKeyWithBLOBs(OrderOperationLog record){
//        return this.orderOperationLogMapper.updateByPrimaryKeyWithBLOBs(record);
//    }

    @Override
    public int logMsg(OrderOperationMsg msg){
        OrderOperationLog log = new OrderOperationLog();
        log.setOperationTime(msg.getTime());

        Object operationData = msg.getOperationData();
        if (operationData != null) {
            log.setOperationData(JSON.toJSONString(operationData));
        }
        log.setOperationDesc(msg.getDesc());
        log.setOrderState(msg.getOrderState());
        log.setRemark(msg.getRemark());
        log.setOperatorId(msg.getOperatorId());
        log.setOrderId(msg.getOrderId());
        log.setOperationState(msg.getOperationState());
        return insertSelective(log);
    }

}