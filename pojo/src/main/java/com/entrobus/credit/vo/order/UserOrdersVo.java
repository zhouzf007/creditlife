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
    private Long money;//贷款金额
    private Long principalAndInterest;//本息
    private Long balance;//借款余额
    private Integer state;

    public Long getPrincipalAndInterest() {
        return principalAndInterest;
    }

    public void setPrincipalAndInterest(Long principalAndInterest) {
        this.principalAndInterest = principalAndInterest;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public Long getMoney() {
        return money;
    }

    public void setMoney(Long money) {
        this.money = money;
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
