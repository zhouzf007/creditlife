package com.entrobus.credit.vo.order;

import java.util.Date;

public class PlanVo {

    private Long principalAndInterest;//应还本息
    private String stateName;//
    private Integer state;//还款状态
    private Integer status;//阶段 0 未到期，1 当期，2 往期
    private Long capital;//本金
    private Date dueTime;//应还日期

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

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
