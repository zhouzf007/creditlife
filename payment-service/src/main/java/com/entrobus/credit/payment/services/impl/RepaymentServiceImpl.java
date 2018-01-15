package com.entrobus.credit.payment.services.impl;

import com.entrobus.credit.common.Constants;
import com.entrobus.credit.common.util.GUIDUtil;
import com.entrobus.credit.payment.dao.RepaymentMapper;
import com.entrobus.credit.payment.services.RepaymentService;
import com.entrobus.credit.pojo.payment.Repayment;
import com.entrobus.credit.pojo.payment.RepaymentExample;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
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
        record.setUpdateTime(new Date());
        return this.repaymentMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(Repayment record) {
        record.setUpdateTime(new Date());
        return this.repaymentMapper.updateByPrimaryKey(record);
    }

    public int deleteByExample(RepaymentExample example) {
        return this.repaymentMapper.deleteByExample(example);
    }

    public int updateByExampleSelective(Repayment record, RepaymentExample example) {
        record.setUpdateTime(new Date());
        return this.repaymentMapper.updateByExampleSelective(record, example);
    }

    public int updateByExample(Repayment record, RepaymentExample example) {
        record.setUpdateTime(new Date());
        return this.repaymentMapper.updateByExample(record, example);
    }

    public int insert(Repayment record) {
        defaultValue(record);
        return this.repaymentMapper.insert(record);
    }

    public int insertSelective(Repayment record) {
        defaultValue(record);
        return this.repaymentMapper.insertSelective(record);
    }

    @Override
    public Repayment getRepaymentByOrderId(String orderId) {
        RepaymentExample example = new RepaymentExample();
        example.createCriteria().andOrderIdEqualTo(orderId).andDeleteFlagEqualTo(Constants.DELETE_FLAG.NO);
        List<Repayment> list = this.selectByExample(example);
        return list.isEmpty() ? null : list.get(0);
    }


    protected void defaultValue(Repayment record) {
        if (StringUtils.isEmpty(record.getId())) {
            record.setId(GUIDUtil.genRandomGUID());
        }
        record.setCreateTime(new Date());
        record.setDeleteFlag(Constants.DELETE_FLAG.NO);
        record.setUpdateTime(new Date());
    }
}