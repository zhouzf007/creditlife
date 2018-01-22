package com.entrobus.credit.pojo.payment;

import java.io.Serializable;
import java.util.Date;

public class RepaymentPlan implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column repayment_plan.id
     *
     * @mbggenerated
     */
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column repayment_plan.order_id
     *
     * @mbggenerated
     */
    private String orderId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column repayment_plan.repayment_id
     *
     * @mbggenerated
     */
    private String repaymentId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column repayment_plan.user_id
     *
     * @mbggenerated
     */
    private String userId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column repayment_plan.account
     *
     * @mbggenerated
     */
    private String account;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column repayment_plan.plan_time
     *
     * @mbggenerated
     */
    private Date planTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column repayment_plan.money
     *
     * @mbggenerated
     */
    private Long money;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column repayment_plan.state
     *
     * @mbggenerated
     */
    private Integer state;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column repayment_plan.system_state
     *
     * @mbggenerated
     */
    private Integer systemState;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column repayment_plan.create_time
     *
     * @mbggenerated
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column repayment_plan.create_operator
     *
     * @mbggenerated
     */
    private String createOperator;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column repayment_plan.update_time
     *
     * @mbggenerated
     */
    private Date updateTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column repayment_plan.update_operator
     *
     * @mbggenerated
     */
    private String updateOperator;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column repayment_plan.delete_flag
     *
     * @mbggenerated
     */
    private Integer deleteFlag;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column repayment_plan.sort_id
     *
     * @mbggenerated
     */
    private Integer sortId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table repayment_plan
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column repayment_plan.id
     *
     * @return the value of repayment_plan.id
     *
     * @mbggenerated
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column repayment_plan.id
     *
     * @param id the value for repayment_plan.id
     *
     * @mbggenerated
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column repayment_plan.order_id
     *
     * @return the value of repayment_plan.order_id
     *
     * @mbggenerated
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column repayment_plan.order_id
     *
     * @param orderId the value for repayment_plan.order_id
     *
     * @mbggenerated
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column repayment_plan.repayment_id
     *
     * @return the value of repayment_plan.repayment_id
     *
     * @mbggenerated
     */
    public String getRepaymentId() {
        return repaymentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column repayment_plan.repayment_id
     *
     * @param repaymentId the value for repayment_plan.repayment_id
     *
     * @mbggenerated
     */
    public void setRepaymentId(String repaymentId) {
        this.repaymentId = repaymentId == null ? null : repaymentId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column repayment_plan.user_id
     *
     * @return the value of repayment_plan.user_id
     *
     * @mbggenerated
     */
    public String getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column repayment_plan.user_id
     *
     * @param userId the value for repayment_plan.user_id
     *
     * @mbggenerated
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column repayment_plan.account
     *
     * @return the value of repayment_plan.account
     *
     * @mbggenerated
     */
    public String getAccount() {
        return account;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column repayment_plan.account
     *
     * @param account the value for repayment_plan.account
     *
     * @mbggenerated
     */
    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column repayment_plan.plan_time
     *
     * @return the value of repayment_plan.plan_time
     *
     * @mbggenerated
     */
    public Date getPlanTime() {
        return planTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column repayment_plan.plan_time
     *
     * @param planTime the value for repayment_plan.plan_time
     *
     * @mbggenerated
     */
    public void setPlanTime(Date planTime) {
        this.planTime = planTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column repayment_plan.money
     *
     * @return the value of repayment_plan.money
     *
     * @mbggenerated
     */
    public Long getMoney() {
        return money;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column repayment_plan.money
     *
     * @param money the value for repayment_plan.money
     *
     * @mbggenerated
     */
    public void setMoney(Long money) {
        this.money = money;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column repayment_plan.state
     *
     * @return the value of repayment_plan.state
     *
     * @mbggenerated
     */
    public Integer getState() {
        return state;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column repayment_plan.state
     *
     * @param state the value for repayment_plan.state
     *
     * @mbggenerated
     */
    public void setState(Integer state) {
        this.state = state;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column repayment_plan.system_state
     *
     * @return the value of repayment_plan.system_state
     *
     * @mbggenerated
     */
    public Integer getSystemState() {
        return systemState;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column repayment_plan.system_state
     *
     * @param systemState the value for repayment_plan.system_state
     *
     * @mbggenerated
     */
    public void setSystemState(Integer systemState) {
        this.systemState = systemState;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column repayment_plan.create_time
     *
     * @return the value of repayment_plan.create_time
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column repayment_plan.create_time
     *
     * @param createTime the value for repayment_plan.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column repayment_plan.create_operator
     *
     * @return the value of repayment_plan.create_operator
     *
     * @mbggenerated
     */
    public String getCreateOperator() {
        return createOperator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column repayment_plan.create_operator
     *
     * @param createOperator the value for repayment_plan.create_operator
     *
     * @mbggenerated
     */
    public void setCreateOperator(String createOperator) {
        this.createOperator = createOperator == null ? null : createOperator.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column repayment_plan.update_time
     *
     * @return the value of repayment_plan.update_time
     *
     * @mbggenerated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column repayment_plan.update_time
     *
     * @param updateTime the value for repayment_plan.update_time
     *
     * @mbggenerated
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column repayment_plan.update_operator
     *
     * @return the value of repayment_plan.update_operator
     *
     * @mbggenerated
     */
    public String getUpdateOperator() {
        return updateOperator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column repayment_plan.update_operator
     *
     * @param updateOperator the value for repayment_plan.update_operator
     *
     * @mbggenerated
     */
    public void setUpdateOperator(String updateOperator) {
        this.updateOperator = updateOperator == null ? null : updateOperator.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column repayment_plan.delete_flag
     *
     * @return the value of repayment_plan.delete_flag
     *
     * @mbggenerated
     */
    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column repayment_plan.delete_flag
     *
     * @param deleteFlag the value for repayment_plan.delete_flag
     *
     * @mbggenerated
     */
    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column repayment_plan.sort_id
     *
     * @return the value of repayment_plan.sort_id
     *
     * @mbggenerated
     */
    public Integer getSortId() {
        return sortId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column repayment_plan.sort_id
     *
     * @param sortId the value for repayment_plan.sort_id
     *
     * @mbggenerated
     */
    public void setSortId(Integer sortId) {
        this.sortId = sortId;
    }
}