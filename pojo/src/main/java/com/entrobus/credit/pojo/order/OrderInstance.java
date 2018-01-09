package com.entrobus.credit.pojo.order;

import java.io.Serializable;
import java.util.Date;

public class OrderInstance implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_instance.id
     *
     * @mbggenerated
     */
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_instance.order_id
     *
     * @mbggenerated
     */
    private String orderId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_instance.apply_no
     *
     * @mbggenerated
     */
    private String applyNo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_instance.apply_time
     *
     * @mbggenerated
     */
    private Date applyTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_instance.user_id
     *
     * @mbggenerated
     */
    private String userId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_instance.credit_report_id
     *
     * @mbggenerated
     */
    private String creditReportId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_instance.contract_id
     *
     * @mbggenerated
     */
    private String contractId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_instance.contract_url
     *
     * @mbggenerated
     */
    private String contractUrl;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_instance.contract_content
     *
     * @mbggenerated
     */
    private String contractContent;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_instance.credit_score
     *
     * @mbggenerated
     */
    private Integer creditScore;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_instance.credit_url
     *
     * @mbggenerated
     */
    private String creditUrl;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_instance.credit_content
     *
     * @mbggenerated
     */
    private String creditContent;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_instance.apply_money
     *
     * @mbggenerated
     */
    private Long applyMoney;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_instance.actual_money
     *
     * @mbggenerated
     */
    private Long actualMoney;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_instance.repayment_term
     *
     * @mbggenerated
     */
    private Integer repaymentTerm;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_instance.repayment_method
     *
     * @mbggenerated
     */
    private Integer repaymentMethod;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_instance.loan_use
     *
     * @mbggenerated
     */
    private String loanUse;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_instance.interest_rate
     *
     * @mbggenerated
     */
    private Integer interestRate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_instance.state
     *
     * @mbggenerated
     */
    private Integer state;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_instance.auditor
     *
     * @mbggenerated
     */
    private String auditor;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_instance.audit_time
     *
     * @mbggenerated
     */
    private Date auditTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_instance.reason
     *
     * @mbggenerated
     */
    private String reason;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_instance.loan_time
     *
     * @mbggenerated
     */
    private Date loanTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_instance.loan_operator
     *
     * @mbggenerated
     */
    private String loanOperator;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_instance.loan_user_time
     *
     * @mbggenerated
     */
    private Date loanUserTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_instance.update_time
     *
     * @mbggenerated
     */
    private Date updateTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_instance.delete_time
     *
     * @mbggenerated
     */
    private Date deleteTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_instance.delete_flag
     *
     * @mbggenerated
     */
    private Integer deleteFlag;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table order_instance
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_instance.id
     *
     * @return the value of order_instance.id
     *
     * @mbggenerated
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_instance.id
     *
     * @param id the value for order_instance.id
     *
     * @mbggenerated
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_instance.order_id
     *
     * @return the value of order_instance.order_id
     *
     * @mbggenerated
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_instance.order_id
     *
     * @param orderId the value for order_instance.order_id
     *
     * @mbggenerated
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_instance.apply_no
     *
     * @return the value of order_instance.apply_no
     *
     * @mbggenerated
     */
    public String getApplyNo() {
        return applyNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_instance.apply_no
     *
     * @param applyNo the value for order_instance.apply_no
     *
     * @mbggenerated
     */
    public void setApplyNo(String applyNo) {
        this.applyNo = applyNo == null ? null : applyNo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_instance.apply_time
     *
     * @return the value of order_instance.apply_time
     *
     * @mbggenerated
     */
    public Date getApplyTime() {
        return applyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_instance.apply_time
     *
     * @param applyTime the value for order_instance.apply_time
     *
     * @mbggenerated
     */
    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_instance.user_id
     *
     * @return the value of order_instance.user_id
     *
     * @mbggenerated
     */
    public String getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_instance.user_id
     *
     * @param userId the value for order_instance.user_id
     *
     * @mbggenerated
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_instance.credit_report_id
     *
     * @return the value of order_instance.credit_report_id
     *
     * @mbggenerated
     */
    public String getCreditReportId() {
        return creditReportId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_instance.credit_report_id
     *
     * @param creditReportId the value for order_instance.credit_report_id
     *
     * @mbggenerated
     */
    public void setCreditReportId(String creditReportId) {
        this.creditReportId = creditReportId == null ? null : creditReportId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_instance.contract_id
     *
     * @return the value of order_instance.contract_id
     *
     * @mbggenerated
     */
    public String getContractId() {
        return contractId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_instance.contract_id
     *
     * @param contractId the value for order_instance.contract_id
     *
     * @mbggenerated
     */
    public void setContractId(String contractId) {
        this.contractId = contractId == null ? null : contractId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_instance.contract_url
     *
     * @return the value of order_instance.contract_url
     *
     * @mbggenerated
     */
    public String getContractUrl() {
        return contractUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_instance.contract_url
     *
     * @param contractUrl the value for order_instance.contract_url
     *
     * @mbggenerated
     */
    public void setContractUrl(String contractUrl) {
        this.contractUrl = contractUrl == null ? null : contractUrl.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_instance.contract_content
     *
     * @return the value of order_instance.contract_content
     *
     * @mbggenerated
     */
    public String getContractContent() {
        return contractContent;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_instance.contract_content
     *
     * @param contractContent the value for order_instance.contract_content
     *
     * @mbggenerated
     */
    public void setContractContent(String contractContent) {
        this.contractContent = contractContent == null ? null : contractContent.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_instance.credit_score
     *
     * @return the value of order_instance.credit_score
     *
     * @mbggenerated
     */
    public Integer getCreditScore() {
        return creditScore;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_instance.credit_score
     *
     * @param creditScore the value for order_instance.credit_score
     *
     * @mbggenerated
     */
    public void setCreditScore(Integer creditScore) {
        this.creditScore = creditScore;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_instance.credit_url
     *
     * @return the value of order_instance.credit_url
     *
     * @mbggenerated
     */
    public String getCreditUrl() {
        return creditUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_instance.credit_url
     *
     * @param creditUrl the value for order_instance.credit_url
     *
     * @mbggenerated
     */
    public void setCreditUrl(String creditUrl) {
        this.creditUrl = creditUrl == null ? null : creditUrl.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_instance.credit_content
     *
     * @return the value of order_instance.credit_content
     *
     * @mbggenerated
     */
    public String getCreditContent() {
        return creditContent;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_instance.credit_content
     *
     * @param creditContent the value for order_instance.credit_content
     *
     * @mbggenerated
     */
    public void setCreditContent(String creditContent) {
        this.creditContent = creditContent == null ? null : creditContent.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_instance.apply_money
     *
     * @return the value of order_instance.apply_money
     *
     * @mbggenerated
     */
    public Long getApplyMoney() {
        return applyMoney;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_instance.apply_money
     *
     * @param applyMoney the value for order_instance.apply_money
     *
     * @mbggenerated
     */
    public void setApplyMoney(Long applyMoney) {
        this.applyMoney = applyMoney;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_instance.actual_money
     *
     * @return the value of order_instance.actual_money
     *
     * @mbggenerated
     */
    public Long getActualMoney() {
        return actualMoney;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_instance.actual_money
     *
     * @param actualMoney the value for order_instance.actual_money
     *
     * @mbggenerated
     */
    public void setActualMoney(Long actualMoney) {
        this.actualMoney = actualMoney;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_instance.repayment_term
     *
     * @return the value of order_instance.repayment_term
     *
     * @mbggenerated
     */
    public Integer getRepaymentTerm() {
        return repaymentTerm;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_instance.repayment_term
     *
     * @param repaymentTerm the value for order_instance.repayment_term
     *
     * @mbggenerated
     */
    public void setRepaymentTerm(Integer repaymentTerm) {
        this.repaymentTerm = repaymentTerm;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_instance.repayment_method
     *
     * @return the value of order_instance.repayment_method
     *
     * @mbggenerated
     */
    public Integer getRepaymentMethod() {
        return repaymentMethod;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_instance.repayment_method
     *
     * @param repaymentMethod the value for order_instance.repayment_method
     *
     * @mbggenerated
     */
    public void setRepaymentMethod(Integer repaymentMethod) {
        this.repaymentMethod = repaymentMethod;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_instance.loan_use
     *
     * @return the value of order_instance.loan_use
     *
     * @mbggenerated
     */
    public String getLoanUse() {
        return loanUse;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_instance.loan_use
     *
     * @param loanUse the value for order_instance.loan_use
     *
     * @mbggenerated
     */
    public void setLoanUse(String loanUse) {
        this.loanUse = loanUse == null ? null : loanUse.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_instance.interest_rate
     *
     * @return the value of order_instance.interest_rate
     *
     * @mbggenerated
     */
    public Integer getInterestRate() {
        return interestRate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_instance.interest_rate
     *
     * @param interestRate the value for order_instance.interest_rate
     *
     * @mbggenerated
     */
    public void setInterestRate(Integer interestRate) {
        this.interestRate = interestRate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_instance.state
     *
     * @return the value of order_instance.state
     *
     * @mbggenerated
     */
    public Integer getState() {
        return state;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_instance.state
     *
     * @param state the value for order_instance.state
     *
     * @mbggenerated
     */
    public void setState(Integer state) {
        this.state = state;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_instance.auditor
     *
     * @return the value of order_instance.auditor
     *
     * @mbggenerated
     */
    public String getAuditor() {
        return auditor;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_instance.auditor
     *
     * @param auditor the value for order_instance.auditor
     *
     * @mbggenerated
     */
    public void setAuditor(String auditor) {
        this.auditor = auditor == null ? null : auditor.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_instance.audit_time
     *
     * @return the value of order_instance.audit_time
     *
     * @mbggenerated
     */
    public Date getAuditTime() {
        return auditTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_instance.audit_time
     *
     * @param auditTime the value for order_instance.audit_time
     *
     * @mbggenerated
     */
    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_instance.reason
     *
     * @return the value of order_instance.reason
     *
     * @mbggenerated
     */
    public String getReason() {
        return reason;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_instance.reason
     *
     * @param reason the value for order_instance.reason
     *
     * @mbggenerated
     */
    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_instance.loan_time
     *
     * @return the value of order_instance.loan_time
     *
     * @mbggenerated
     */
    public Date getLoanTime() {
        return loanTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_instance.loan_time
     *
     * @param loanTime the value for order_instance.loan_time
     *
     * @mbggenerated
     */
    public void setLoanTime(Date loanTime) {
        this.loanTime = loanTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_instance.loan_operator
     *
     * @return the value of order_instance.loan_operator
     *
     * @mbggenerated
     */
    public String getLoanOperator() {
        return loanOperator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_instance.loan_operator
     *
     * @param loanOperator the value for order_instance.loan_operator
     *
     * @mbggenerated
     */
    public void setLoanOperator(String loanOperator) {
        this.loanOperator = loanOperator == null ? null : loanOperator.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_instance.loan_user_time
     *
     * @return the value of order_instance.loan_user_time
     *
     * @mbggenerated
     */
    public Date getLoanUserTime() {
        return loanUserTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_instance.loan_user_time
     *
     * @param loanUserTime the value for order_instance.loan_user_time
     *
     * @mbggenerated
     */
    public void setLoanUserTime(Date loanUserTime) {
        this.loanUserTime = loanUserTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_instance.update_time
     *
     * @return the value of order_instance.update_time
     *
     * @mbggenerated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_instance.update_time
     *
     * @param updateTime the value for order_instance.update_time
     *
     * @mbggenerated
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_instance.delete_time
     *
     * @return the value of order_instance.delete_time
     *
     * @mbggenerated
     */
    public Date getDeleteTime() {
        return deleteTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_instance.delete_time
     *
     * @param deleteTime the value for order_instance.delete_time
     *
     * @mbggenerated
     */
    public void setDeleteTime(Date deleteTime) {
        this.deleteTime = deleteTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_instance.delete_flag
     *
     * @return the value of order_instance.delete_flag
     *
     * @mbggenerated
     */
    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_instance.delete_flag
     *
     * @param deleteFlag the value for order_instance.delete_flag
     *
     * @mbggenerated
     */
    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
}