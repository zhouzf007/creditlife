package com.entrobus.credit.vo.order;

import java.util.Date;

public class UserOrdersVo {

    /**
     * 给前端接口使用
     */
    private String id;
    private Date loanTime;//放款日期
    private String term;//当期
    private Date dueTime;//本期还款时间
    private Long applyMoney;//贷款金额
    private String principalAndInterest;//本息
    private Integer state;

    public Long getApplyMoney() {
        return applyMoney;
    }

    public void setApplyMoney(Long applyMoney) {
        this.applyMoney = applyMoney;
    }

    private String stateName;

    public Date getLoanTime() {
        return loanTime;
    }

    public void setLoanTime(Date loanTime) {
        this.loanTime = loanTime;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public Date getDueTime() {
        return dueTime;
    }

    public void setDueTime(Date dueTime) {
        this.dueTime = dueTime;
    }


    public String getPrincipalAndInterest() {
        return principalAndInterest;
    }

    public void setPrincipalAndInterest(String principalAndInterest) {
        this.principalAndInterest = principalAndInterest;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }
}
