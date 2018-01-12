package com.entrobus.credit.pojo.order;

import java.io.Serializable;
import java.util.Date;

public class Orders implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column orders.id
     *
     * @mbggenerated
     */
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column orders.apply_no
     *
     * @mbggenerated
     */
    private String applyNo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column orders.apply_time
     *
     * @mbggenerated
     */
    private Date applyTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column orders.user_id
     *
     * @mbggenerated
     */
    private String userId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column orders.credit_report_id
     *
     * @mbggenerated
     */
    private String creditReportId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column orders.contract_id
     *
     * @mbggenerated
     */
    private String contractId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column orders.prod_id
     *
     * @mbggenerated
     */
    private String prodId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column orders.credit_score
     *
     * @mbggenerated
     */
    private Integer creditScore;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column orders.apply_money
     *
     * @mbggenerated
     */
    private Long applyMoney;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column orders.actual_money
     *
     * @mbggenerated
     */
    private Long actualMoney;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column orders.repayment_term
     *
     * @mbggenerated
     */
    private Integer repaymentTerm;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column orders.repayment_type
     *
     * @mbggenerated
     */
    private Integer repaymentType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column orders.loan_usage
     *
     * @mbggenerated
     */
    private String loanUsage;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column orders.interest_rate
     *
     * @mbggenerated
     */
    private Integer interestRate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column orders.system_state
     *
     * @mbggenerated
     */
    private Integer systemState;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column orders.state
     *
     * @mbggenerated
     */
    private Integer state;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column orders.auditor
     *
     * @mbggenerated
     */
    private String auditor;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column orders.audit_time
     *
     * @mbggenerated
     */
    private Date auditTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column orders.reason
     *
     * @mbggenerated
     */
    private String reason;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column orders.loan_operator
     *
     * @mbggenerated
     */
    private String loanOperator;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column orders.loan_time
     *
     * @mbggenerated
     */
    private Date loanTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column orders.update_time
     *
     * @mbggenerated
     */
    private Date updateTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column orders.delete_time
     *
     * @mbggenerated
     */
    private Date deleteTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column orders.delete_flag
     *
     * @mbggenerated
     */
    private Integer deleteFlag;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column orders.create_operator
     *
     * @mbggenerated
     */
    private String createOperator;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column orders.update_operator
     *
     * @mbggenerated
     */
    private String updateOperator;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column orders.delete_operator
     *
     * @mbggenerated
     */
    private String deleteOperator;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column orders.create_time
     *
     * @mbggenerated
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table orders
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column orders.id
     *
     * @return the value of orders.id
     *
     * @mbggenerated
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column orders.id
     *
     * @param id the value for orders.id
     *
     * @mbggenerated
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column orders.apply_no
     *
     * @return the value of orders.apply_no
     *
     * @mbggenerated
     */
    public String getApplyNo() {
        return applyNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column orders.apply_no
     *
     * @param applyNo the value for orders.apply_no
     *
     * @mbggenerated
     */
    public void setApplyNo(String applyNo) {
        this.applyNo = applyNo == null ? null : applyNo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column orders.apply_time
     *
     * @return the value of orders.apply_time
     *
     * @mbggenerated
     */
    public Date getApplyTime() {
        return applyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column orders.apply_time
     *
     * @param applyTime the value for orders.apply_time
     *
     * @mbggenerated
     */
    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column orders.user_id
     *
     * @return the value of orders.user_id
     *
     * @mbggenerated
     */
    public String getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column orders.user_id
     *
     * @param userId the value for orders.user_id
     *
     * @mbggenerated
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column orders.credit_report_id
     *
     * @return the value of orders.credit_report_id
     *
     * @mbggenerated
     */
    public String getCreditReportId() {
        return creditReportId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column orders.credit_report_id
     *
     * @param creditReportId the value for orders.credit_report_id
     *
     * @mbggenerated
     */
    public void setCreditReportId(String creditReportId) {
        this.creditReportId = creditReportId == null ? null : creditReportId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column orders.contract_id
     *
     * @return the value of orders.contract_id
     *
     * @mbggenerated
     */
    public String getContractId() {
        return contractId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column orders.contract_id
     *
     * @param contractId the value for orders.contract_id
     *
     * @mbggenerated
     */
    public void setContractId(String contractId) {
        this.contractId = contractId == null ? null : contractId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column orders.prod_id
     *
     * @return the value of orders.prod_id
     *
     * @mbggenerated
     */
    public String getProdId() {
        return prodId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column orders.prod_id
     *
     * @param prodId the value for orders.prod_id
     *
     * @mbggenerated
     */
    public void setProdId(String prodId) {
        this.prodId = prodId == null ? null : prodId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column orders.credit_score
     *
     * @return the value of orders.credit_score
     *
     * @mbggenerated
     */
    public Integer getCreditScore() {
        return creditScore;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column orders.credit_score
     *
     * @param creditScore the value for orders.credit_score
     *
     * @mbggenerated
     */
    public void setCreditScore(Integer creditScore) {
        this.creditScore = creditScore;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column orders.apply_money
     *
     * @return the value of orders.apply_money
     *
     * @mbggenerated
     */
    public Long getApplyMoney() {
        return applyMoney;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column orders.apply_money
     *
     * @param applyMoney the value for orders.apply_money
     *
     * @mbggenerated
     */
    public void setApplyMoney(Long applyMoney) {
        this.applyMoney = applyMoney;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column orders.actual_money
     *
     * @return the value of orders.actual_money
     *
     * @mbggenerated
     */
    public Long getActualMoney() {
        return actualMoney;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column orders.actual_money
     *
     * @param actualMoney the value for orders.actual_money
     *
     * @mbggenerated
     */
    public void setActualMoney(Long actualMoney) {
        this.actualMoney = actualMoney;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column orders.repayment_term
     *
     * @return the value of orders.repayment_term
     *
     * @mbggenerated
     */
    public Integer getRepaymentTerm() {
        return repaymentTerm;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column orders.repayment_term
     *
     * @param repaymentTerm the value for orders.repayment_term
     *
     * @mbggenerated
     */
    public void setRepaymentTerm(Integer repaymentTerm) {
        this.repaymentTerm = repaymentTerm;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column orders.repayment_type
     *
     * @return the value of orders.repayment_type
     *
     * @mbggenerated
     */
    public Integer getRepaymentType() {
        return repaymentType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column orders.repayment_type
     *
     * @param repaymentType the value for orders.repayment_type
     *
     * @mbggenerated
     */
    public void setRepaymentType(Integer repaymentType) {
        this.repaymentType = repaymentType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column orders.loan_usage
     *
     * @return the value of orders.loan_usage
     *
     * @mbggenerated
     */
    public String getLoanUsage() {
        return loanUsage;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column orders.loan_usage
     *
     * @param loanUsage the value for orders.loan_usage
     *
     * @mbggenerated
     */
    public void setLoanUsage(String loanUsage) {
        this.loanUsage = loanUsage == null ? null : loanUsage.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column orders.interest_rate
     *
     * @return the value of orders.interest_rate
     *
     * @mbggenerated
     */
    public Integer getInterestRate() {
        return interestRate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column orders.interest_rate
     *
     * @param interestRate the value for orders.interest_rate
     *
     * @mbggenerated
     */
    public void setInterestRate(Integer interestRate) {
        this.interestRate = interestRate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column orders.system_state
     *
     * @return the value of orders.system_state
     *
     * @mbggenerated
     */
    public Integer getSystemState() {
        return systemState;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column orders.system_state
     *
     * @param systemState the value for orders.system_state
     *
     * @mbggenerated
     */
    public void setSystemState(Integer systemState) {
        this.systemState = systemState;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column orders.state
     *
     * @return the value of orders.state
     *
     * @mbggenerated
     */
    public Integer getState() {
        return state;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column orders.state
     *
     * @param state the value for orders.state
     *
     * @mbggenerated
     */
    public void setState(Integer state) {
        this.state = state;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column orders.auditor
     *
     * @return the value of orders.auditor
     *
     * @mbggenerated
     */
    public String getAuditor() {
        return auditor;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column orders.auditor
     *
     * @param auditor the value for orders.auditor
     *
     * @mbggenerated
     */
    public void setAuditor(String auditor) {
        this.auditor = auditor == null ? null : auditor.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column orders.audit_time
     *
     * @return the value of orders.audit_time
     *
     * @mbggenerated
     */
    public Date getAuditTime() {
        return auditTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column orders.audit_time
     *
     * @param auditTime the value for orders.audit_time
     *
     * @mbggenerated
     */
    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column orders.reason
     *
     * @return the value of orders.reason
     *
     * @mbggenerated
     */
    public String getReason() {
        return reason;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column orders.reason
     *
     * @param reason the value for orders.reason
     *
     * @mbggenerated
     */
    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column orders.loan_operator
     *
     * @return the value of orders.loan_operator
     *
     * @mbggenerated
     */
    public String getLoanOperator() {
        return loanOperator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column orders.loan_operator
     *
     * @param loanOperator the value for orders.loan_operator
     *
     * @mbggenerated
     */
    public void setLoanOperator(String loanOperator) {
        this.loanOperator = loanOperator == null ? null : loanOperator.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column orders.loan_time
     *
     * @return the value of orders.loan_time
     *
     * @mbggenerated
     */
    public Date getLoanTime() {
        return loanTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column orders.loan_time
     *
     * @param loanTime the value for orders.loan_time
     *
     * @mbggenerated
     */
    public void setLoanTime(Date loanTime) {
        this.loanTime = loanTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column orders.update_time
     *
     * @return the value of orders.update_time
     *
     * @mbggenerated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column orders.update_time
     *
     * @param updateTime the value for orders.update_time
     *
     * @mbggenerated
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column orders.delete_time
     *
     * @return the value of orders.delete_time
     *
     * @mbggenerated
     */
    public Date getDeleteTime() {
        return deleteTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column orders.delete_time
     *
     * @param deleteTime the value for orders.delete_time
     *
     * @mbggenerated
     */
    public void setDeleteTime(Date deleteTime) {
        this.deleteTime = deleteTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column orders.delete_flag
     *
     * @return the value of orders.delete_flag
     *
     * @mbggenerated
     */
    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column orders.delete_flag
     *
     * @param deleteFlag the value for orders.delete_flag
     *
     * @mbggenerated
     */
    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column orders.create_operator
     *
     * @return the value of orders.create_operator
     *
     * @mbggenerated
     */
    public String getCreateOperator() {
        return createOperator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column orders.create_operator
     *
     * @param createOperator the value for orders.create_operator
     *
     * @mbggenerated
     */
    public void setCreateOperator(String createOperator) {
        this.createOperator = createOperator == null ? null : createOperator.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column orders.update_operator
     *
     * @return the value of orders.update_operator
     *
     * @mbggenerated
     */
    public String getUpdateOperator() {
        return updateOperator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column orders.update_operator
     *
     * @param updateOperator the value for orders.update_operator
     *
     * @mbggenerated
     */
    public void setUpdateOperator(String updateOperator) {
        this.updateOperator = updateOperator == null ? null : updateOperator.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column orders.delete_operator
     *
     * @return the value of orders.delete_operator
     *
     * @mbggenerated
     */
    public String getDeleteOperator() {
        return deleteOperator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column orders.delete_operator
     *
     * @param deleteOperator the value for orders.delete_operator
     *
     * @mbggenerated
     */
    public void setDeleteOperator(String deleteOperator) {
        this.deleteOperator = deleteOperator == null ? null : deleteOperator.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column orders.create_time
     *
     * @return the value of orders.create_time
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column orders.create_time
     *
     * @param createTime the value for orders.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}