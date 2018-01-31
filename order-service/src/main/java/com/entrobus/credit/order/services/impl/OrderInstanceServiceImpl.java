package com.entrobus.credit.order.services.impl;

import com.entrobus.credit.common.Constants;
import com.entrobus.credit.common.util.GUIDUtil;
import com.entrobus.credit.order.client.UserClient;
import com.entrobus.credit.order.dao.OrderInstanceMapper;
import com.entrobus.credit.order.services.OrderInstanceService;
import com.entrobus.credit.pojo.order.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class OrderInstanceServiceImpl implements OrderInstanceService {
    @Autowired
    private OrderInstanceMapper orderInstanceMapper;

    @Autowired
    private UserClient userClient;

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
        record.setUpdateTime(new Date());
        return this.orderInstanceMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(OrderInstance record) {
        record.setUpdateTime(new Date());
        return this.orderInstanceMapper.updateByPrimaryKey(record);
    }

    public int deleteByExample(OrderInstanceExample example) {
        return this.orderInstanceMapper.deleteByExample(example);
    }

    public int updateByExampleSelective(OrderInstance record, OrderInstanceExample example) {
        record.setUpdateTime(new Date());
        return this.orderInstanceMapper.updateByExampleSelective(record, example);
    }

    public int updateByExample(OrderInstance record, OrderInstanceExample example) {
        record.setUpdateTime(new Date());
        return this.orderInstanceMapper.updateByExample(record, example);
    }

    public int insert(OrderInstance record) {
        defaultValue(record);
        return this.orderInstanceMapper.insert(record);
    }

    public int insertSelective(OrderInstance record) {
        defaultValue(record);
        return this.orderInstanceMapper.insertSelective(record);
    }

    protected void defaultValue(OrderInstance record) {
        if (StringUtils.isEmpty(record.getId())) {
            record.setId(GUIDUtil.genRandomGUID());
        }
        record.setUpdateTime(new Date());
    }

    @Override
    public int saveOrderInstance(Orders order) {
        OrderInstance instance = new OrderInstance();
        instance.setOrderId(order.getId());
        //申请信息
        instance.setActualMoney(order.getActualMoney());
        instance.setApplyMoney(order.getApplyMoney());
        instance.setApplyTime(order.getApplyTime());
        instance.setLoanUsage(order.getLoanUsage());
        //审核信息
        instance.setAuditTime(order.getAuditTime());
        instance.setAuditor(order.getAuditor());
        instance.setLoanOperator(order.getLoanOperator());
        instance.setReason(order.getReason());
        //合同信息
        instance.setContractId(order.getContractId());
//        Contract contract = contractService.selectByPrimaryKey(order.getContractId());
//        if (contract != null) {
////          instance.setContractContent(contract.get);
//            instance.setContractUrl(contract.getContractUrl());
//        }

        //信用报告
        CreditReport report = userClient.getCreditReport(order.getCreditReportId());
        if (report != null) {
//          instance.setCreditContent();
            instance.setCreditUrl(report.getReportUrl());
            instance.setCreditScore(order.getCreditScore());
        }
        //产品信息
        instance.setProdId(order.getProdId());
        instance.setInterestRate(order.getInterestRate());
        instance.setRepaymentTerm(order.getRepaymentTerm());
        instance.setRepaymentType(order.getRepaymentType());
        instance.setState(Constants.ORDER_STATE.FINISHED);
        return this.insertSelective(instance);
    }
}