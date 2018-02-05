package com.entrobus.credit.vo.order;

import java.util.Date;

public class UserOrdersVo {

    /**
     * 给前端接口使用
     */
    private String id;
    private Date loanTime;//放款日期

    public Integer getTerm() {
        return term;
    }

    public void setTerm(Integer term) {
        this.term = term;
    }

    public Integer getTotalTerm() {
        return totalTerm;
    }

    public void setTotalTerm(Integer totalTerm) {
        this.totalTerm = totalTerm;
    }

    private Integer term;//当期
    private Integer totalTerm;//总期
    private Date dueTime;//本期还款时间
    private String money;//贷款金额
    private String principalAndInterest;//本息
    private String balance;//借款余额
    private Integer state;

    public String getPrincipalAndInterest() {
        return principalAndInterest;
    }

    public void setPrincipalAndInterest(String principalAndInterest) {
        this.principalAndInterest = principalAndInterest;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    private String stateName;

    public Date getLoanTime() {
        return loanTime;
    }

    public void setLoanTime(Date loanTime) {
        this.loanTime = loanTime;
    }

    public Date getDueTime() {
        return dueTime;
    }

    public void setDueTime(Date dueTime) {
        this.dueTime = dueTime;
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
