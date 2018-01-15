package com.entrobus.credit.payment.services;

import com.entrobus.credit.pojo.payment.RepaymentPlanInstance;
import com.entrobus.credit.pojo.payment.RepaymentPlanInstanceExample;
import com.entrobus.credit.pojo.payment.RepaymentPlan;

import java.util.List;

public interface RepaymentPlanInstanceService {
    int countByExample(RepaymentPlanInstanceExample example);

    RepaymentPlanInstance selectByPrimaryKey(String id);

    List<RepaymentPlanInstance> selectByExample(RepaymentPlanInstanceExample example);

    int deleteByPrimaryKey(String id);

    int updateByPrimaryKeySelective(RepaymentPlanInstance record);

    int updateByPrimaryKey(RepaymentPlanInstance record);

    int deleteByExample(RepaymentPlanInstanceExample example);

    int updateByExampleSelective(RepaymentPlanInstance record, RepaymentPlanInstanceExample example);

    int updateByExample(RepaymentPlanInstance record, RepaymentPlanInstanceExample example);

    int insert(RepaymentPlanInstance record);

    int insertSelective(RepaymentPlanInstance record);

    int saveRepaymentPlanInstance(RepaymentPlan record);
}