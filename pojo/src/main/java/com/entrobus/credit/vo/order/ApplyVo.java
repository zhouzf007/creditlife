package com.entrobus.credit.vo.order;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

public class ApplyVo {
    @NotBlank(message = "token")
    private String token;
//    @NotBlank(message = "userId")
    private String userId;
    private long money;
    @NotBlank(message = "usage")
    private String usage;

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }
    @NotNull(message = "repaymentTerm")
    private Integer repaymentTerm;
    @NotNull(message = "repaymentType")
    private Integer repaymentType;
    @NotNull(message = "rate")
    private Integer rate;
    @NotBlank(message = "prodId")
    private String prodId;
    @NotBlank(message = "orgId")
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
