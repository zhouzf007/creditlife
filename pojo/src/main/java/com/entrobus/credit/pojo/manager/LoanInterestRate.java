package com.entrobus.credit.pojo.manager;

import java.io.Serializable;
import java.util.Date;

public class LoanInterestRate implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column loan_interest_rate.id
     *
     * @mbggenerated
     */
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column loan_interest_rate.loan_periods_id
     *
     * @mbggenerated
     */
    private String loanPeriodsId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column loan_interest_rate.repayment_type
     *
     * @mbggenerated
     */
    private Integer repaymentType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column loan_interest_rate.interest_rate
     *
     * @mbggenerated
     */
    private Long interestRate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column loan_interest_rate.create_time
     *
     * @mbggenerated
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column loan_interest_rate.update_time
     *
     * @mbggenerated
     */
    private Date updateTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column loan_interest_rate.delete_time
     *
     * @mbggenerated
     */
    private Date deleteTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column loan_interest_rate.delete_flag
     *
     * @mbggenerated
     */
    private Integer deleteFlag;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table loan_interest_rate
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column loan_interest_rate.id
     *
     * @return the value of loan_interest_rate.id
     *
     * @mbggenerated
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column loan_interest_rate.id
     *
     * @param id the value for loan_interest_rate.id
     *
     * @mbggenerated
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column loan_interest_rate.loan_periods_id
     *
     * @return the value of loan_interest_rate.loan_periods_id
     *
     * @mbggenerated
     */
    public String getLoanPeriodsId() {
        return loanPeriodsId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column loan_interest_rate.loan_periods_id
     *
     * @param loanPeriodsId the value for loan_interest_rate.loan_periods_id
     *
     * @mbggenerated
     */
    public void setLoanPeriodsId(String loanPeriodsId) {
        this.loanPeriodsId = loanPeriodsId == null ? null : loanPeriodsId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column loan_interest_rate.repayment_type
     *
     * @return the value of loan_interest_rate.repayment_type
     *
     * @mbggenerated
     */
    public Integer getRepaymentType() {
        return repaymentType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column loan_interest_rate.repayment_type
     *
     * @param repaymentType the value for loan_interest_rate.repayment_type
     *
     * @mbggenerated
     */
    public void setRepaymentType(Integer repaymentType) {
        this.repaymentType = repaymentType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column loan_interest_rate.interest_rate
     *
     * @return the value of loan_interest_rate.interest_rate
     *
     * @mbggenerated
     */
    public Long getInterestRate() {
        return interestRate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column loan_interest_rate.interest_rate
     *
     * @param interestRate the value for loan_interest_rate.interest_rate
     *
     * @mbggenerated
     */
    public void setInterestRate(Long interestRate) {
        this.interestRate = interestRate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column loan_interest_rate.create_time
     *
     * @return the value of loan_interest_rate.create_time
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column loan_interest_rate.create_time
     *
     * @param createTime the value for loan_interest_rate.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column loan_interest_rate.update_time
     *
     * @return the value of loan_interest_rate.update_time
     *
     * @mbggenerated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column loan_interest_rate.update_time
     *
     * @param updateTime the value for loan_interest_rate.update_time
     *
     * @mbggenerated
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column loan_interest_rate.delete_time
     *
     * @return the value of loan_interest_rate.delete_time
     *
     * @mbggenerated
     */
    public Date getDeleteTime() {
        return deleteTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column loan_interest_rate.delete_time
     *
     * @param deleteTime the value for loan_interest_rate.delete_time
     *
     * @mbggenerated
     */
    public void setDeleteTime(Date deleteTime) {
        this.deleteTime = deleteTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column loan_interest_rate.delete_flag
     *
     * @return the value of loan_interest_rate.delete_flag
     *
     * @mbggenerated
     */
    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column loan_interest_rate.delete_flag
     *
     * @param deleteFlag the value for loan_interest_rate.delete_flag
     *
     * @mbggenerated
     */
    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
}