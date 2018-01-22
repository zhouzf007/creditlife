package com.entrobus.credit.payment.services.impl;

import com.entrobus.credit.common.Constants;
import com.entrobus.credit.common.util.GUIDUtil;
import com.entrobus.credit.payment.dao.RepaymentPlanMapper;
import com.entrobus.credit.payment.services.RepaymentPlanService;
import com.entrobus.credit.pojo.payment.RepaymentPlan;
import com.entrobus.credit.pojo.payment.RepaymentPlanExample;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RepaymentPlanServiceImpl implements RepaymentPlanService {
    @Autowired
    private RepaymentPlanMapper repaymentPlanMapper;

    private static final Logger logger = LoggerFactory.getLogger(RepaymentPlanServiceImpl.class);

    public int countByExample(RepaymentPlanExample example) {
        int count = this.repaymentPlanMapper.countByExample(example);
        logger.debug("count: {}", count);
        return count;
    }

    public RepaymentPlan selectByPrimaryKey(String id) {
        return this.repaymentPlanMapper.selectByPrimaryKey(id);
    }

    public List<RepaymentPlan> selectByExample(RepaymentPlanExample example) {
        return this.repaymentPlanMapper.selectByExample(example);
    }

    public int deleteByPrimaryKey(String id) {
        return this.repaymentPlanMapper.deleteByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(RepaymentPlan record) {
        record.setUpdateTime(new Date());
        return this.repaymentPlanMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(RepaymentPlan record) {
        record.setUpdateTime(new Date());
        return this.repaymentPlanMapper.updateByPrimaryKey(record);
    }

    public int deleteByExample(RepaymentPlanExample example) {
        return this.repaymentPlanMapper.deleteByExample(example);
    }

    public int updateByExampleSelective(RepaymentPlan record, RepaymentPlanExample example) {
        record.setUpdateTime(new Date());
        return this.repaymentPlanMapper.updateByExampleSelective(record, example);
    }

    public int updateByExample(RepaymentPlan record, RepaymentPlanExample example) {
        record.setUpdateTime(new Date());
        return this.repaymentPlanMapper.updateByExample(record, example);
    }

    public int insert(RepaymentPlan record) {
        defaultValue(record);
        return this.repaymentPlanMapper.insert(record);
    }

    public int insertSelective(RepaymentPlan record) {
        defaultValue(record);
        return this.repaymentPlanMapper.insertSelective(record);
    }

    @Override
    public List<RepaymentPlan> getRepaymentPlanByOrderId(String orderId) {
        RepaymentPlanExample example = new RepaymentPlanExample();
        example.createCriteria().andOrderIdEqualTo(orderId).andDeleteFlagEqualTo(Constants.DELETE_FLAG.NO);
        example.setOrderByClause(" plan_time asc ");
        return this.selectByExample(example);
    }

    @Override
    public RepaymentPlan getLastRepaymentPlanByOrderId(String orderId) {
        RepaymentPlanExample example = new RepaymentPlanExample();
        example.createCriteria().andOrderIdEqualTo(orderId).andDeleteFlagEqualTo(Constants.DELETE_FLAG.NO).andPlanTimeLessThan(new Date());
        example.setOrderByClause(" plan_time desc ");
        List<RepaymentPlan> list = this.selectByExample(example);
        if (!list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public List<RepaymentPlan> getOverDueRepaymentPlans(String orderId) {
        RepaymentPlanExample example = new RepaymentPlanExample();
        example.createCriteria().andOrderIdEqualTo(orderId).andDeleteFlagEqualTo(Constants.DELETE_FLAG.NO).andPlanTimeLessThan(new Date()).andStateEqualTo(Constants.REPAYMENT_ORDER_STATE.OVERDUE);
        example.setOrderByClause(" plan_time desc ");
        return this.selectByExample(example);
    }

    @Override
    public List<RepaymentPlan> getFinishedRepaymentPlans(String orderId) {
        RepaymentPlanExample example = new RepaymentPlanExample();
        example.createCriteria().andOrderIdEqualTo(orderId).andDeleteFlagEqualTo(Constants.DELETE_FLAG.NO).andPlanTimeLessThan(new Date()).andStateEqualTo(Constants.REPAYMENT_ORDER_STATE.FINISHED);
        return this.selectByExample(example);
    }

    protected void defaultValue(RepaymentPlan record) {
        if (StringUtils.isEmpty(record.getId())) {
            record.setId(GUIDUtil.genRandomGUID());
        }
        record.setCreateTime(new Date());
        record.setDeleteFlag(Constants.DELETE_FLAG.NO);
        record.setUpdateTime(new Date());
    }
}