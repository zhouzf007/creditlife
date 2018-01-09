package com.entrobus.credit.payment.services.impl;

import com.entrobus.credit.payment.dao.PaymentMapper;
import com.entrobus.credit.payment.services.PaymentService;
import com.entrobus.credit.pojo.payment.Payment;
import com.entrobus.credit.pojo.payment.PaymentExample;
import java.util.List;
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
        return this.paymentMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(Payment record) {
        return this.paymentMapper.updateByPrimaryKey(record);
    }

    public int deleteByExample(PaymentExample example) {
        return this.paymentMapper.deleteByExample(example);
    }

    public int updateByExampleSelective(Payment record, PaymentExample example) {
        return this.paymentMapper.updateByExampleSelective(record, example);
    }

    public int updateByExample(Payment record, PaymentExample example) {
        return this.paymentMapper.updateByExample(record, example);
    }

    public int insert(Payment record) {
        return this.paymentMapper.insert(record);
    }

    public int insertSelective(Payment record) {
        return this.paymentMapper.insertSelective(record);
    }
}