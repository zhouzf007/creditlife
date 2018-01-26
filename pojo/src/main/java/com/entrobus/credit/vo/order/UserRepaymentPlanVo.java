package com.entrobus.credit.vo.order;

import java.util.List;

public class UserRepaymentPlanVo {

    /**
     * 供接口使用
     */
    private Long balance;//余款总额
    private Long interest;//总利息

    public Long getInterest() {
        return interest;
    }

    public void setInterest(Long interest) {
        this.interest = interest;
    }

    private List<PlanVo> planList;

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public List<PlanVo> getPlanList() {
        return planList;
    }

    public void setPlanList(List<PlanVo> planList) {
        this.planList = planList;
    }
}
