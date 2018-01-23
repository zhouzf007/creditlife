package com.entrobus.credit.vo.order;

import java.util.Date;

public class PlanVo {

    private Long principalAndInterest;//应还本息
    private String stateName;//
    private Integer state;
    private Long capital;//本金
    private Date dueTime;//应还日期

    public Date getDueTime() {
        return dueTime;
    }

    public void setDueTime(Date dueTime) {
        this.dueTime = dueTime;
    }


    public Long getPrincipalAndInterest() {
        return principalAndInterest;
    }

    public void setPrincipalAndInterest(Long principalAndInterest) {
        this.principalAndInterest = principalAndInterest;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Long getCapital() {
        return capital;
    }

    public void setCapital(Long capital) {
        this.capital = capital;
    }
}
