package com.entrobus.credit.payment.services.impl;

import com.entrobus.credit.common.util.GUIDUtil;
import com.entrobus.credit.payment.dao.RepaymentPlanInstanceMapper;
import com.entrobus.credit.payment.services.RepaymentPlanInstanceService;
import com.entrobus.credit.pojo.payment.RepaymentPlanInstance;
import com.entrobus.credit.pojo.payment.RepaymentPlanInstanceExample;

import java.util.Date;
import java.util.List;

import com.entrobus.credit.pojo.payment.RepaymentPlan;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RepaymentPlanInstanceServiceImpl implements RepaymentPlanInstanceService {
    @Autowired
    private RepaymentPlanInstanceMapper RepaymentPlanInstanceMapper;

    private static final Logger logger = LoggerFactory.getLogger(RepaymentPlanInstanceServiceImpl.class);

    public int countByExample(RepaymentPlanInstanceExample example) {
        int count = this.RepaymentPlanInstanceMapper.countByExample(example);
        logger.debug("count: {}", count);
        return count;
    }

    public RepaymentPlanInstance selectByPrimaryKey(String id) {
        return this.RepaymentPlanInstanceMapper.selectByPrimaryKey(id);
    }

    public List<RepaymentPlanInstance> selectByExample(RepaymentPlanInstanceExample example) {
        return this.RepaymentPlanInstanceMapper.selectByExample(example);
    }

    public int deleteByPrimaryKey(String id) {
        return this.RepaymentPlanInstanceMapper.deleteByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(RepaymentPlanInstance record) {
        return this.RepaymentPlanInstanceMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(RepaymentPlanInstance record) {
        return this.RepaymentPlanInstanceMapper.updateByPrimaryKey(record);
    }

    public int deleteByExample(RepaymentPlanInstanceExample example) {
        return this.RepaymentPlanInstanceMapper.deleteByExample(example);
    }

    public int updateByExampleSelective(RepaymentPlanInstance record, RepaymentPlanInstanceExample example) {
        return this.RepaymentPlanInstanceMapper.updateByExampleSelective(record, example);
    }

    public int updateByExample(RepaymentPlanInstance record, RepaymentPlanInstanceExample example) {
        return this.RepaymentPlanInstanceMapper.updateByExample(record, example);
    }

    public int insert(RepaymentPlanInstance record) {
        defaultValue(record);
        return this.RepaymentPlanInstanceMapper.insert(record);
    }

    public int insertSelective(RepaymentPlanInstance record) {
        defaultValue(record);
        return this.RepaymentPlanInstanceMapper.insertSelective(record);
    }

    @Override
    public int saveRepaymentPlanInstance(RepaymentPlan record) {
        RepaymentPlanInstance repaymentPlan = new RepaymentPlanInstance();
        repaymentPlan.setOrderId(record.getOrderId());
        repaymentPlan.setRepaymentPlanId(record.getId());
        repaymentPlan.setRepaymentMoney(record.getMoney());
        repaymentPlan.setRepaymentTime(record.getPlanTime());
        repaymentPlan.setAccountId(record.getAccountId());
        repaymentPlan.setCreateOperator(record.getUpdateOperator());
        repaymentPlan.setUserId(record.getUserId());
        return this.insertSelective(repaymentPlan);
    }

    protected void defaultValue(RepaymentPlanInstance record) {
        if (StringUtils.isEmpty(record.getId())) {
            record.setId(GUIDUtil.genRandomGUID());
        }
        record.setCreateTime(new Date());
    }
}