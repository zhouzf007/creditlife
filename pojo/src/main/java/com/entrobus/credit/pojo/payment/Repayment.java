package com.entrobus.credit.pojo.payment;

import java.io.Serializable;
import java.util.Date;

public class Repayment implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column repayment.id
     *
     * @mbggenerated
     */
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column repayment.apply_no
     *
     * @mbggenerated
     */
    private String applyNo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column repayment.apply_time
     *
     * @mbggenerated
     */
    private Date applyTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column repayment.user_id
     *
     * @mbggenerated
     */
    private String userId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column repayment.order_id
     *
     * @mbggenerated
     */
    private String orderId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column repayment.account_id
     *
     * @mbggenerated
     */
    private String accountId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column repayment.account
     *
     * @mbggenerated
     */
    private String account;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column repayment.repayment_type
     *
     * @mbggenerated
     */
    private Integer repaymentType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column repayment.repayment_term
     *
     * @mbggenerated
     */
    private Integer repaymentTerm;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column repayment.money
     *
     * @mbggenerated
     */
    private Long money;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column repayment.source_account
     *
     * @mbggenerated
     */
    private String sourceAccount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column repayment.state
     *
     * @mbggenerated
     */
    private Integer state;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column repayment.update_time
     *
     * @mbggenerated
     */
    private Date updateTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column repayment.delete_time
     *
     * @mbggenerated
     */
    private Date deleteTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column repayment.delete_flag
     *
     * @mbggenerated
     */
    private Integer deleteFlag;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column repayment.create_operator
     *
     * @mbggenerated
     */
    private String createOperator;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column repayment.update_operator
     *
     * @mbggenerated
     */
    private String updateOperator;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column repayment.delete_operator
     *
     * @mbggenerated
     */
    private String deleteOperator;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table repayment
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column repayment.id
     *
     * @return the value of repayment.id
     *
     * @mbggenerated
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column repayment.id
     *
     * @param id the value for repayment.id
     *
     * @mbggenerated
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column repayment.apply_no
     *
     * @return the value of repayment.apply_no
     *
     * @mbggenerated
     */
    public String getApplyNo() {
        return applyNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column repayment.apply_no
     *
     * @param applyNo the value for repayment.apply_no
     *
     * @mbggenerated
     */
    public void setApplyNo(String applyNo) {
        this.applyNo = applyNo == null ? null : applyNo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column repayment.apply_time
     *
     * @return the value of repayment.apply_time
     *
     * @mbggenerated
     */
    public Date getApplyTime() {
        return applyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column repayment.apply_time
     *
     * @param applyTime the value for repayment.apply_time
     *
     * @mbggenerated
     */
    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column repayment.user_id
     *
     * @return the value of repayment.user_id
     *
     * @mbggenerated
     */
    public String getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column repayment.user_id
     *
     * @param userId the value for repayment.user_id
     *
     * @mbggenerated
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column repayment.order_id
     *
     * @return the value of repayment.order_id
     *
     * @mbggenerated
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column repayment.order_id
     *
     * @param orderId the value for repayment.order_id
     *
     * @mbggenerated
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column repayment.account_id
     *
     * @return the value of repayment.account_id
     *
     * @mbggenerated
     */
    public String getAccountId() {
        return accountId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column repayment.account_id
     *
     * @param accountId the value for repayment.account_id
     *
     * @mbggenerated
     */
    public void setAccountId(String accountId) {
        this.accountId = accountId == null ? null : accountId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column repayment.account
     *
     * @return the value of repayment.account
     *
     * @mbggenerated
     */
    public String getAccount() {
        return account;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column repayment.account
     *
     * @param account the value for repayment.account
     *
     * @mbggenerated
     */
    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column repayment.repayment_type
     *
     * @return the value of repayment.repayment_type
     *
     * @mbggenerated
     */
    public Integer getRepaymentType() {
        return repaymentType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column repayment.repayment_type
     *
     * @param repaymentType the value for repayment.repayment_type
     *
     * @mbggenerated
     */
    public void setRepaymentType(Integer repaymentType) {
        this.repaymentType = repaymentType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column repayment.repayment_term
     *
     * @return the value of repayment.repayment_term
     *
     * @mbggenerated
     */
    public Integer getRepaymentTerm() {
        return repaymentTerm;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column repayment.repayment_term
     *
     * @param repaymentTerm the value for repayment.repayment_term
     *
     * @mbggenerated
     */
    public void setRepaymentTerm(Integer repaymentTerm) {
        this.repaymentTerm = repaymentTerm;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column repayment.money
     *
     * @return the value of repayment.money
     *
     * @mbggenerated
     */
    public Long getMoney() {
        return money;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column repayment.money
     *
     * @param money the value for repayment.money
     *
     * @mbggenerated
     */
    public void setMoney(Long money) {
        this.money = money;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column repayment.source_account
     *
     * @return the value of repayment.source_account
     *
     * @mbggenerated
     */
    public String getSourceAccount() {
        return sourceAccount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column repayment.source_account
     *
     * @param sourceAccount the value for repayment.source_account
     *
     * @mbggenerated
     */
    public void setSourceAccount(String sourceAccount) {
        this.sourceAccount = sourceAccount == null ? null : sourceAccount.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column repayment.state
     *
     * @return the value of repayment.state
     *
     * @mbggenerated
     */
    public Integer getState() {
        return state;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column repayment.state
     *
     * @param state the value for repayment.state
     *
     * @mbggenerated
     */
    public void setState(Integer state) {
        this.state = state;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column repayment.update_time
     *
     * @return the value of repayment.update_time
     *
     * @mbggenerated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column repayment.update_time
     *
     * @param updateTime the value for repayment.update_time
     *
     * @mbggenerated
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column repayment.delete_time
     *
     * @return the value of repayment.delete_time
     *
     * @mbggenerated
     */
    public Date getDeleteTime() {
        return deleteTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column repayment.delete_time
     *
     * @param deleteTime the value for repayment.delete_time
     *
     * @mbggenerated
     */
    public void setDeleteTime(Date deleteTime) {
        this.deleteTime = deleteTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column repayment.delete_flag
     *
     * @return the value of repayment.delete_flag
     *
     * @mbggenerated
     */
    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column repayment.delete_flag
     *
     * @param deleteFlag the value for repayment.delete_flag
     *
     * @mbggenerated
     */
    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column repayment.create_operator
     *
     * @return the value of repayment.create_operator
     *
     * @mbggenerated
     */
    public String getCreateOperator() {
        return createOperator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column repayment.create_operator
     *
     * @param createOperator the value for repayment.create_operator
     *
     * @mbggenerated
     */
    public void setCreateOperator(String createOperator) {
        this.createOperator = createOperator == null ? null : createOperator.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column repayment.update_operator
     *
     * @return the value of repayment.update_operator
     *
     * @mbggenerated
     */
    public String getUpdateOperator() {
        return updateOperator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column repayment.update_operator
     *
     * @param updateOperator the value for repayment.update_operator
     *
     * @mbggenerated
     */
    public void setUpdateOperator(String updateOperator) {
        this.updateOperator = updateOperator == null ? null : updateOperator.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column repayment.delete_operator
     *
     * @return the value of repayment.delete_operator
     *
     * @mbggenerated
     */
    public String getDeleteOperator() {
        return deleteOperator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column repayment.delete_operator
     *
     * @param deleteOperator the value for repayment.delete_operator
     *
     * @mbggenerated
     */
    public void setDeleteOperator(String deleteOperator) {
        this.deleteOperator = deleteOperator == null ? null : deleteOperator.trim();
    }
}