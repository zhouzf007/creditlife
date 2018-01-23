package com.entrobus.credit.vo.order;

import java.util.List;

public class UserRepaymentPlanVo {

    /**
     * 供接口使用
     */
    private String balance;//余款总额
    private List<PlanVo> planList;

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public List<PlanVo> getPlanList() {
        return planList;
    }

    public void setPlanList(List<PlanVo> planList) {
        this.planList = planList;
    }
}
