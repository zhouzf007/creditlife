package com.entrobus.credit.pojo.order;

import java.io.Serializable;
import java.util.Date;

public class CreditReport implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column credit_report.id
     *
     * @mbggenerated
     */
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column credit_report.user_id
     *
     * @mbggenerated
     */
    private String userId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column credit_report.report_url
     *
     * @mbggenerated
     */
    private String reportUrl;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column credit_report.quota
     *
     * @mbggenerated
     */
    private Long quota;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column credit_report.credit_score
     *
     * @mbggenerated
     */
    private Double creditScore;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column credit_report.create_time
     *
     * @mbggenerated
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column credit_report.update_time
     *
     * @mbggenerated
     */
    private Date updateTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column credit_report.delete_time
     *
     * @mbggenerated
     */
    private Date deleteTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column credit_report.delete_flag
     *
     * @mbggenerated
     */
    private Integer deleteFlag;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column credit_report.create_operator
     *
     * @mbggenerated
     */
    private String createOperator;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column credit_report.update_operator
     *
     * @mbggenerated
     */
    private String updateOperator;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column credit_report.delete_operator
     *
     * @mbggenerated
     */
    private String deleteOperator;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column credit_report.address_house
     *
     * @mbggenerated
     */
    private String addressHouse;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table credit_report
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column credit_report.id
     *
     * @return the value of credit_report.id
     *
     * @mbggenerated
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column credit_report.id
     *
     * @param id the value for credit_report.id
     *
     * @mbggenerated
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column credit_report.user_id
     *
     * @return the value of credit_report.user_id
     *
     * @mbggenerated
     */
    public String getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column credit_report.user_id
     *
     * @param userId the value for credit_report.user_id
     *
     * @mbggenerated
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column credit_report.report_url
     *
     * @return the value of credit_report.report_url
     *
     * @mbggenerated
     */
    public String getReportUrl() {
        return reportUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column credit_report.report_url
     *
     * @param reportUrl the value for credit_report.report_url
     *
     * @mbggenerated
     */
    public void setReportUrl(String reportUrl) {
        this.reportUrl = reportUrl == null ? null : reportUrl.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column credit_report.quota
     *
     * @return the value of credit_report.quota
     *
     * @mbggenerated
     */
    public Long getQuota() {
        return quota;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column credit_report.quota
     *
     * @param quota the value for credit_report.quota
     *
     * @mbggenerated
     */
    public void setQuota(Long quota) {
        this.quota = quota;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column credit_report.credit_score
     *
     * @return the value of credit_report.credit_score
     *
     * @mbggenerated
     */
    public Double getCreditScore() {
        return creditScore;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column credit_report.credit_score
     *
     * @param creditScore the value for credit_report.credit_score
     *
     * @mbggenerated
     */
    public void setCreditScore(Double creditScore) {
        this.creditScore = creditScore;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column credit_report.create_time
     *
     * @return the value of credit_report.create_time
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column credit_report.create_time
     *
     * @param createTime the value for credit_report.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column credit_report.update_time
     *
     * @return the value of credit_report.update_time
     *
     * @mbggenerated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column credit_report.update_time
     *
     * @param updateTime the value for credit_report.update_time
     *
     * @mbggenerated
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column credit_report.delete_time
     *
     * @return the value of credit_report.delete_time
     *
     * @mbggenerated
     */
    public Date getDeleteTime() {
        return deleteTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column credit_report.delete_time
     *
     * @param deleteTime the value for credit_report.delete_time
     *
     * @mbggenerated
     */
    public void setDeleteTime(Date deleteTime) {
        this.deleteTime = deleteTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column credit_report.delete_flag
     *
     * @return the value of credit_report.delete_flag
     *
     * @mbggenerated
     */
    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column credit_report.delete_flag
     *
     * @param deleteFlag the value for credit_report.delete_flag
     *
     * @mbggenerated
     */
    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column credit_report.create_operator
     *
     * @return the value of credit_report.create_operator
     *
     * @mbggenerated
     */
    public String getCreateOperator() {
        return createOperator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column credit_report.create_operator
     *
     * @param createOperator the value for credit_report.create_operator
     *
     * @mbggenerated
     */
    public void setCreateOperator(String createOperator) {
        this.createOperator = createOperator == null ? null : createOperator.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column credit_report.update_operator
     *
     * @return the value of credit_report.update_operator
     *
     * @mbggenerated
     */
    public String getUpdateOperator() {
        return updateOperator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column credit_report.update_operator
     *
     * @param updateOperator the value for credit_report.update_operator
     *
     * @mbggenerated
     */
    public void setUpdateOperator(String updateOperator) {
        this.updateOperator = updateOperator == null ? null : updateOperator.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column credit_report.delete_operator
     *
     * @return the value of credit_report.delete_operator
     *
     * @mbggenerated
     */
    public String getDeleteOperator() {
        return deleteOperator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column credit_report.delete_operator
     *
     * @param deleteOperator the value for credit_report.delete_operator
     *
     * @mbggenerated
     */
    public void setDeleteOperator(String deleteOperator) {
        this.deleteOperator = deleteOperator == null ? null : deleteOperator.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column credit_report.address_house
     *
     * @return the value of credit_report.address_house
     *
     * @mbggenerated
     */
    public String getAddressHouse() {
        return addressHouse;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column credit_report.address_house
     *
     * @param addressHouse the value for credit_report.address_house
     *
     * @mbggenerated
     */
    public void setAddressHouse(String addressHouse) {
        this.addressHouse = addressHouse == null ? null : addressHouse.trim();
    }
}