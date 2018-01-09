package com.entrobus.credit.payment.services.impl;

import com.entrobus.credit.payment.dao.RepaymentMapper;
import com.entrobus.credit.payment.services.RepaymentService;
import com.entrobus.credit.pojo.payment.Repayment;
import com.entrobus.credit.pojo.payment.RepaymentExample;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RepaymentServiceImpl implements RepaymentService {
    @Autowired
    private RepaymentMapper repaymentMapper;

    private static final Logger logger = LoggerFactory.getLogger(RepaymentServiceImpl.class);

    public int countByExample(RepaymentExample example) {
        int count = this.repaymentMapper.countByExample(example);
        logger.debug("count: {}", count);
        return count;
    }

    public Repayment selectByPrimaryKey(String id) {
        return this.repaymentMapper.selectByPrimaryKey(id);
    }

    public List<Repayment> selectByExample(RepaymentExample example) {
        return this.repaymentMapper.selectByExample(example);
    }

    public int deleteByPrimaryKey(String id) {
        return this.repaymentMapper.deleteByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(Repayment record) {
        return this.repaymentMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(Repayment record) {
        return this.repaymentMapper.updateByPrimaryKey(record);
    }

    public int deleteByExample(RepaymentExample example) {
        return this.repaymentMapper.deleteByExample(example);
    }

    public int updateByExampleSelective(Repayment record, RepaymentExample example) {
        return this.repaymentMapper.updateByExampleSelective(record, example);
    }

    public int updateByExample(Repayment record, RepaymentExample example) {
        return this.repaymentMapper.updateByExample(record, example);
    }

    public int insert(Repayment record) {
        return this.repaymentMapper.insert(record);
    }

    public int insertSelective(Repayment record) {
        return this.repaymentMapper.insertSelective(record);
    }
}