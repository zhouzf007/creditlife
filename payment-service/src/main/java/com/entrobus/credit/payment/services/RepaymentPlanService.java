package com.entrobus.credit.payment.services;

import com.entrobus.credit.pojo.payment.RepaymentPlan;
import com.entrobus.credit.pojo.payment.RepaymentPlanExample;
import java.util.List;

public interface RepaymentPlanService {
    int countByExample(RepaymentPlanExample example);

    RepaymentPlan selectByPrimaryKey(String id);

    List<RepaymentPlan> selectByExample(RepaymentPlanExample example);

    int deleteByPrimaryKey(String id);

    int updateByPrimaryKeySelective(RepaymentPlan record);

    int updateByPrimaryKey(RepaymentPlan record);

    int deleteByExample(RepaymentPlanExample example);

    int updateByExampleSelective(RepaymentPlan record, RepaymentPlanExample example);

    int updateByExample(RepaymentPlan record, RepaymentPlanExample example);

    int insert(RepaymentPlan record);

    int insertSelective(RepaymentPlan record);

    List<RepaymentPlan> getRepaymentPlanByOrderId(String orderId);

    RepaymentPlan getLastRepaymentPlanByOrderId(String orderId);

    List<RepaymentPlan> getOverDueRepaymentPlans(String orderId);

    List<RepaymentPlan> getFinishedRepaymentPlans(String orderId);
}