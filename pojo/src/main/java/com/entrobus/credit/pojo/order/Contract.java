package com.entrobus.credit.pojo.order;

import java.io.Serializable;
import java.util.Date;

public class Contract implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column contract.id
     *
     * @mbggenerated
     */
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column contract.contract_no
     *
     * @mbggenerated
     */
    private String contractNo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column contract.order_id
     *
     * @mbggenerated
     */
    private String orderId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column contract.user_id
     *
     * @mbggenerated
     */
    private String userId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column contract.credit_report_id
     *
     * @mbggenerated
     */
    private String creditReportId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column contract.contract_url
     *
     * @mbggenerated
     */
    private String contractUrl;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column contract.signature
     *
     * @mbggenerated
     */
    private String signature;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column contract.credit_score
     *
     * @mbggenerated
     */
    private Double creditScore;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column contract.money
     *
     * @mbggenerated
     */
    private Long money;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column contract.create_time
     *
     * @mbggenerated
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column contract.update_time
     *
     * @mbggenerated
     */
    private Date updateTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column contract.delete_time
     *
     * @mbggenerated
     */
    private Date deleteTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column contract.delete_flag
     *
     * @mbggenerated
     */
    private Integer deleteFlag;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column contract.create_operator
     *
     * @mbggenerated
     */
    private String createOperator;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column contract.update_operator
     *
     * @mbggenerated
     */
    private String updateOperator;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column contract.delete_operator
     *
     * @mbggenerated
     */
    private String deleteOperator;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table contract
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column contract.id
     *
     * @return the value of contract.id
     *
     * @mbggenerated
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column contract.id
     *
     * @param id the value for contract.id
     *
     * @mbggenerated
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column contract.contract_no
     *
     * @return the value of contract.contract_no
     *
     * @mbggenerated
     */
    public String getContractNo() {
        return contractNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column contract.contract_no
     *
     * @param contractNo the value for contract.contract_no
     *
     * @mbggenerated
     */
    public void setContractNo(String contractNo) {
        this.contractNo = contractNo == null ? null : contractNo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column contract.order_id
     *
     * @return the value of contract.order_id
     *
     * @mbggenerated
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column contract.order_id
     *
     * @param orderId the value for contract.order_id
     *
     * @mbggenerated
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column contract.user_id
     *
     * @return the value of contract.user_id
     *
     * @mbggenerated
     */
    public String getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column contract.user_id
     *
     * @param userId the value for contract.user_id
     *
     * @mbggenerated
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column contract.credit_report_id
     *
     * @return the value of contract.credit_report_id
     *
     * @mbggenerated
     */
    public String getCreditReportId() {
        return creditReportId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column contract.credit_report_id
     *
     * @param creditReportId the value for contract.credit_report_id
     *
     * @mbggenerated
     */
    public void setCreditReportId(String creditReportId) {
        this.creditReportId = creditReportId == null ? null : creditReportId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column contract.contract_url
     *
     * @return the value of contract.contract_url
     *
     * @mbggenerated
     */
    public String getContractUrl() {
        return contractUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column contract.contract_url
     *
     * @param contractUrl the value for contract.contract_url
     *
     * @mbggenerated
     */
    public void setContractUrl(String contractUrl) {
        this.contractUrl = contractUrl == null ? null : contractUrl.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column contract.signature
     *
     * @return the value of contract.signature
     *
     * @mbggenerated
     */
    public String getSignature() {
        return signature;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column contract.signature
     *
     * @param signature the value for contract.signature
     *
     * @mbggenerated
     */
    public void setSignature(String signature) {
        this.signature = signature == null ? null : signature.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column contract.credit_score
     *
     * @return the value of contract.credit_score
     *
     * @mbggenerated
     */
    public Double getCreditScore() {
        return creditScore;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column contract.credit_score
     *
     * @param creditScore the value for contract.credit_score
     *
     * @mbggenerated
     */
    public void setCreditScore(Double creditScore) {
        this.creditScore = creditScore;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column contract.money
     *
     * @return the value of contract.money
     *
     * @mbggenerated
     */
    public Long getMoney() {
        return money;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column contract.money
     *
     * @param money the value for contract.money
     *
     * @mbggenerated
     */
    public void setMoney(Long money) {
        this.money = money;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column contract.create_time
     *
     * @return the value of contract.create_time
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column contract.create_time
     *
     * @param createTime the value for contract.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column contract.update_time
     *
     * @return the value of contract.update_time
     *
     * @mbggenerated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column contract.update_time
     *
     * @param updateTime the value for contract.update_time
     *
     * @mbggenerated
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column contract.delete_time
     *
     * @return the value of contract.delete_time
     *
     * @mbggenerated
     */
    public Date getDeleteTime() {
        return deleteTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column contract.delete_time
     *
     * @param deleteTime the value for contract.delete_time
     *
     * @mbggenerated
     */
    public void setDeleteTime(Date deleteTime) {
        this.deleteTime = deleteTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column contract.delete_flag
     *
     * @return the value of contract.delete_flag
     *
     * @mbggenerated
     */
    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column contract.delete_flag
     *
     * @param deleteFlag the value for contract.delete_flag
     *
     * @mbggenerated
     */
    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column contract.create_operator
     *
     * @return the value of contract.create_operator
     *
     * @mbggenerated
     */
    public String getCreateOperator() {
        return createOperator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column contract.create_operator
     *
     * @param createOperator the value for contract.create_operator
     *
     * @mbggenerated
     */
    public void setCreateOperator(String createOperator) {
        this.createOperator = createOperator == null ? null : createOperator.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column contract.update_operator
     *
     * @return the value of contract.update_operator
     *
     * @mbggenerated
     */
    public String getUpdateOperator() {
        return updateOperator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column contract.update_operator
     *
     * @param updateOperator the value for contract.update_operator
     *
     * @mbggenerated
     */
    public void setUpdateOperator(String updateOperator) {
        this.updateOperator = updateOperator == null ? null : updateOperator.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column contract.delete_operator
     *
     * @return the value of contract.delete_operator
     *
     * @mbggenerated
     */
    public String getDeleteOperator() {
        return deleteOperator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column contract.delete_operator
     *
     * @param deleteOperator the value for contract.delete_operator
     *
     * @mbggenerated
     */
    public void setDeleteOperator(String deleteOperator) {
        this.deleteOperator = deleteOperator == null ? null : deleteOperator.trim();
    }
}