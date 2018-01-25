package com.entrobus.credit.vo.order;

public class OrderUpdateVo {


    private String id;
    private String orgId;
    private Long actualMoney;
    private Integer state;
    private String auditor;
    private Integer rejectType;
    private String reason;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public Long getActualMoney() {
        return actualMoney;
    }

    public void setActualMoney(Long actualMoney) {
        this.actualMoney = actualMoney;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getAuditor() {
        return auditor;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor;
    }

    public Integer getRejectType() {
        return rejectType;
    }

    public void setRejectType(Integer rejectType) {
        this.rejectType = rejectType;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getLoanOperator() {
        return loanOperator;
    }

    public void setLoanOperator(String loanOperator) {
        this.loanOperator = loanOperator;
    }

    public String getUpdateOperator() {
        return updateOperator;
    }

    public void setUpdateOperator(String updateOperator) {
        this.updateOperator = updateOperator;
    }

    public String getDeleteOperator() {
        return deleteOperator;
    }

    public void setDeleteOperator(String deleteOperator) {
        this.deleteOperator = deleteOperator;
    }

    private String loanOperator;
    private String updateOperator;
    private String deleteOperator;

    private String loanTimeStr;

    public String getLoanTimeStr() {
        return loanTimeStr;
    }

    public void setLoanTimeStr(String loanTimeStr) {
        this.loanTimeStr = loanTimeStr;
    }
}
