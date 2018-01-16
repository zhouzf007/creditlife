package com.entrobus.credit.vo.order;

import java.util.Date;

public class RepaymentPlanVo {

    private String id;
    private Date repayTime;
    private String money;
    private String stateName;
    private Date updateTime;
    private String operator;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getRepayTime() {
        return repayTime;
    }

    public void setRepayTime(Date repayTime) {
        this.repayTime = repayTime;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }
}
