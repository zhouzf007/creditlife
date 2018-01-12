package com.entrobus.credit.payment.services.impl;

import com.entrobus.credit.common.Constants;
import com.entrobus.credit.common.util.GUIDUtil;
import com.entrobus.credit.payment.dao.RepaymentInstanceMapper;
import com.entrobus.credit.payment.services.RepaymentInstanceService;
import com.entrobus.credit.pojo.payment.Repayment;
import com.entrobus.credit.pojo.payment.RepaymentInstance;
import com.entrobus.credit.pojo.payment.RepaymentInstanceExample;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RepaymentInstanceServiceImpl implements RepaymentInstanceService {
    @Autowired
    private RepaymentInstanceMapper repaymentInstanceMapper;

    private static final Logger logger = LoggerFactory.getLogger(RepaymentInstanceServiceImpl.class);

    public int countByExample(RepaymentInstanceExample example) {
        int count = this.repaymentInstanceMapper.countByExample(example);
        logger.debug("count: {}", count);
        return count;
    }

    public RepaymentInstance selectByPrimaryKey(String id) {
        return this.repaymentInstanceMapper.selectByPrimaryKey(id);
    }

    public List<RepaymentInstance> selectByExample(RepaymentInstanceExample example) {
        return this.repaymentInstanceMapper.selectByExample(example);
    }

    public int deleteByPrimaryKey(String id) {
        return this.repaymentInstanceMapper.deleteByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(RepaymentInstance record) {
        return this.repaymentInstanceMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(RepaymentInstance record) {
        return this.repaymentInstanceMapper.updateByPrimaryKey(record);
    }

    public int deleteByExample(RepaymentInstanceExample example) {
        return this.repaymentInstanceMapper.deleteByExample(example);
    }

    public int updateByExampleSelective(RepaymentInstance record, RepaymentInstanceExample example) {
        return this.repaymentInstanceMapper.updateByExampleSelective(record, example);
    }

    public int updateByExample(RepaymentInstance record, RepaymentInstanceExample example) {
        return this.repaymentInstanceMapper.updateByExample(record, example);
    }

    public int insert(RepaymentInstance record) {
        defaultValue(record);
        return this.repaymentInstanceMapper.insert(record);
    }

    public int insertSelective(RepaymentInstance record) {
        defaultValue(record);
        return this.repaymentInstanceMapper.insertSelective(record);
    }

    protected void defaultValue(RepaymentInstance record) {
        if (StringUtils.isEmpty(record.getId())) {
            record.setId(GUIDUtil.genRandomGUID());
        }
        record.setCreateTime(new Date());
    }
}