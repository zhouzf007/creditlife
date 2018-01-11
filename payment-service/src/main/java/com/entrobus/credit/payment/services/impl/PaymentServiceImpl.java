package com.entrobus.credit.payment.services.impl;

import com.entrobus.credit.common.Constants;
import com.entrobus.credit.common.util.GUIDUtil;
import com.entrobus.credit.payment.dao.PaymentMapper;
import com.entrobus.credit.payment.services.PaymentService;
import com.entrobus.credit.pojo.payment.Payment;
import com.entrobus.credit.pojo.payment.PaymentExample;

import java.util.Date;
import java.util.List;

import com.entrobus.credit.pojo.payment.Repayment;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentMapper paymentMapper;

    private static final Logger logger = LoggerFactory.getLogger(PaymentServiceImpl.class);

    public int countByExample(PaymentExample example) {
        int count = this.paymentMapper.countByExample(example);
        logger.debug("count: {}", count);
        return count;
    }

    public Payment selectByPrimaryKey(String id) {
        return this.paymentMapper.selectByPrimaryKey(id);
    }

    public List<Payment> selectByExample(PaymentExample example) {
        return this.paymentMapper.selectByExample(example);
    }

    public int deleteByPrimaryKey(String id) {
        return this.paymentMapper.deleteByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(Payment record) {
        record.setUpdateTime(new Date());
        return this.paymentMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(Payment record) {
        record.setUpdateTime(new Date());
        return this.paymentMapper.updateByPrimaryKey(record);
    }

    public int deleteByExample(PaymentExample example) {
        return this.paymentMapper.deleteByExample(example);
    }

    public int updateByExampleSelective(Payment record, PaymentExample example) {
        record.setUpdateTime(new Date());
        return this.paymentMapper.updateByExampleSelective(record, example);
    }

    public int updateByExample(Payment record, PaymentExample example) {
        record.setUpdateTime(new Date());
        return this.paymentMapper.updateByExample(record, example);
    }

    public int insert(Payment record) {
        defaultValue(record);
        return this.paymentMapper.insert(record);
    }

    public int insertSelective(Payment record) {
        defaultValue(record);
        return this.paymentMapper.insertSelective(record);
    }

    protected void defaultValue(Payment record) {
        if (StringUtils.isEmpty(record.getId())) {
            record.setId(GUIDUtil.genRandomGUID());
        }
        record.setDeleteFlag(Constants.DeleteFlag.NO);
        record.setUpdateTime(new Date());
    }
}