package com.entrobus.credit.vo.order;

import java.util.Date;
import java.util.List;

public class OrderDtlVo {

    private String id;
    private String userId;
    private String money;
    private String usage;
    private String applyNo;
    private String role;
    private String idCard;
    private String name;
    private Date applyTime;
    private String mobile;
    private String contractId;
    private String repaymentTerm;
    private String repaymentType;
    private String rate;
    private Integer score;
    private String account;
    private String creditReportId;
    private String stateName;
    private String auditor;// 审核人
    private String auditTime;// 审核日期
    private String loanOperator;//放款人
    private String loanTime;//操作放款日期/实际放款日期

    public String getAuditor() {
        return auditor;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor;
    }

    public String getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(String auditTime) {
        this.auditTime = auditTime;
    }

    public String getLoanOperator() {
        return loanOperator;
    }

    public void setLoanOperator(String loanOperator) {
        this.loanOperator = loanOperator;
    }

    public String getLoanTime() {
        return loanTime;
    }

    public void setLoanTime(String loanTime) {
        this.loanTime = loanTime;
    }

    public List<RepaymentPlanVo> getList() {
        return list;
    }

    public void setList(List<RepaymentPlanVo> list) {
        this.list = list;
    }

    private List<RepaymentPlanVo> list;

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getUsage() {
        return usage;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }

    public String getApplyNo() {
        return applyNo;
    }

    public void setApplyNo(String applyNo) {
        this.applyNo = applyNo;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public String getRepaymentTerm() {
        return repaymentTerm;
    }

    public void setRepaymentTerm(String repaymentTerm) {
        this.repaymentTerm = repaymentTerm;
    }

    public String getRepaymentType() {
        return repaymentType;
    }

    public void setRepaymentType(String repaymentType) {
        this.repaymentType = repaymentType;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getCreditReportId() {
        return creditReportId;
    }

    public void setCreditReportId(String creditReportId) {
        this.creditReportId = creditReportId;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

}
