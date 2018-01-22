package com.entrobus.credit.vo.order;

public class ApplyVo {

    private String token;
    private String userId;
    private long money;
    private String usage;

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    private Integer repaymentTerm;
    private Integer repaymentType;
    private Integer rate;
    private String prodId;
    private String orgId;
    private String signature;

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getUsage() {
        return usage;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }

    public Integer getRepaymentTerm() {
        return repaymentTerm;
    }

    public void setRepaymentTerm(Integer repaymentTerm) {
        this.repaymentTerm = repaymentTerm;
    }

    public Integer getRepaymentType() {
        return repaymentType;
    }

    public void setRepaymentType(Integer repaymentType) {
        this.repaymentType = repaymentType;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId;
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public long getMoney() {
        return money;
    }

    public void setMoney(long money) {
        this.money = money;
    }

}
