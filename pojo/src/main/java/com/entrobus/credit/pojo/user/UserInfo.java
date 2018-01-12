package com.entrobus.credit.pojo.user;

import java.io.Serializable;
import java.util.Date;

public class UserInfo implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_info.id
     *
     * @mbggenerated
     */
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_info.name
     *
     * @mbggenerated
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_info.real_name
     *
     * @mbggenerated
     */
    private String realName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_info.id_card
     *
     * @mbggenerated
     */
    private String idCard;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_info.pwd
     *
     * @mbggenerated
     */
    private String pwd;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_info.salt
     *
     * @mbggenerated
     */
    private String salt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_info.account_id
     *
     * @mbggenerated
     */
    private String accountId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_info.portrait
     *
     * @mbggenerated
     */
    private String portrait;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_info.cellphone
     *
     * @mbggenerated
     */
    private String cellphone;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_info.email
     *
     * @mbggenerated
     */
    private String email;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_info.age
     *
     * @mbggenerated
     */
    private Integer age;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_info.gender
     *
     * @mbggenerated
     */
    private Integer gender;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_info.role
     *
     * @mbggenerated
     */
    private Integer role;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_info.credit_score
     *
     * @mbggenerated
     */
    private Integer creditScore;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_info.quota
     *
     * @mbggenerated
     */
    private Long quota;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_info.state
     *
     * @mbggenerated
     */
    private Integer state;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_info.union_id
     *
     * @mbggenerated
     */
    private String unionId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_info.remark
     *
     * @mbggenerated
     */
    private String remark;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_info.create_time
     *
     * @mbggenerated
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_info.update_time
     *
     * @mbggenerated
     */
    private Date updateTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_info.delete_time
     *
     * @mbggenerated
     */
    private Date deleteTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_info.delete_flag
     *
     * @mbggenerated
     */
    private Integer deleteFlag;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_info.create_operator
     *
     * @mbggenerated
     */
    private String createOperator;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_info.update_operator
     *
     * @mbggenerated
     */
    private String updateOperator;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_info.delete_operator
     *
     * @mbggenerated
     */
    private String deleteOperator;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table user_info
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_info.id
     *
     * @return the value of user_info.id
     *
     * @mbggenerated
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_info.id
     *
     * @param id the value for user_info.id
     *
     * @mbggenerated
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_info.name
     *
     * @return the value of user_info.name
     *
     * @mbggenerated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_info.name
     *
     * @param name the value for user_info.name
     *
     * @mbggenerated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_info.real_name
     *
     * @return the value of user_info.real_name
     *
     * @mbggenerated
     */
    public String getRealName() {
        return realName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_info.real_name
     *
     * @param realName the value for user_info.real_name
     *
     * @mbggenerated
     */
    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_info.id_card
     *
     * @return the value of user_info.id_card
     *
     * @mbggenerated
     */
    public String getIdCard() {
        return idCard;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_info.id_card
     *
     * @param idCard the value for user_info.id_card
     *
     * @mbggenerated
     */
    public void setIdCard(String idCard) {
        this.idCard = idCard == null ? null : idCard.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_info.pwd
     *
     * @return the value of user_info.pwd
     *
     * @mbggenerated
     */
    public String getPwd() {
        return pwd;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_info.pwd
     *
     * @param pwd the value for user_info.pwd
     *
     * @mbggenerated
     */
    public void setPwd(String pwd) {
        this.pwd = pwd == null ? null : pwd.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_info.salt
     *
     * @return the value of user_info.salt
     *
     * @mbggenerated
     */
    public String getSalt() {
        return salt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_info.salt
     *
     * @param salt the value for user_info.salt
     *
     * @mbggenerated
     */
    public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_info.account_id
     *
     * @return the value of user_info.account_id
     *
     * @mbggenerated
     */
    public String getAccountId() {
        return accountId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_info.account_id
     *
     * @param accountId the value for user_info.account_id
     *
     * @mbggenerated
     */
    public void setAccountId(String accountId) {
        this.accountId = accountId == null ? null : accountId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_info.portrait
     *
     * @return the value of user_info.portrait
     *
     * @mbggenerated
     */
    public String getPortrait() {
        return portrait;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_info.portrait
     *
     * @param portrait the value for user_info.portrait
     *
     * @mbggenerated
     */
    public void setPortrait(String portrait) {
        this.portrait = portrait == null ? null : portrait.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_info.cellphone
     *
     * @return the value of user_info.cellphone
     *
     * @mbggenerated
     */
    public String getCellphone() {
        return cellphone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_info.cellphone
     *
     * @param cellphone the value for user_info.cellphone
     *
     * @mbggenerated
     */
    public void setCellphone(String cellphone) {
        this.cellphone = cellphone == null ? null : cellphone.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_info.email
     *
     * @return the value of user_info.email
     *
     * @mbggenerated
     */
    public String getEmail() {
        return email;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_info.email
     *
     * @param email the value for user_info.email
     *
     * @mbggenerated
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_info.age
     *
     * @return the value of user_info.age
     *
     * @mbggenerated
     */
    public Integer getAge() {
        return age;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_info.age
     *
     * @param age the value for user_info.age
     *
     * @mbggenerated
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_info.gender
     *
     * @return the value of user_info.gender
     *
     * @mbggenerated
     */
    public Integer getGender() {
        return gender;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_info.gender
     *
     * @param gender the value for user_info.gender
     *
     * @mbggenerated
     */
    public void setGender(Integer gender) {
        this.gender = gender;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_info.role
     *
     * @return the value of user_info.role
     *
     * @mbggenerated
     */
    public Integer getRole() {
        return role;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_info.role
     *
     * @param role the value for user_info.role
     *
     * @mbggenerated
     */
    public void setRole(Integer role) {
        this.role = role;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_info.credit_score
     *
     * @return the value of user_info.credit_score
     *
     * @mbggenerated
     */
    public Integer getCreditScore() {
        return creditScore;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_info.credit_score
     *
     * @param creditScore the value for user_info.credit_score
     *
     * @mbggenerated
     */
    public void setCreditScore(Integer creditScore) {
        this.creditScore = creditScore;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_info.quota
     *
     * @return the value of user_info.quota
     *
     * @mbggenerated
     */
    public Long getQuota() {
        return quota;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_info.quota
     *
     * @param quota the value for user_info.quota
     *
     * @mbggenerated
     */
    public void setQuota(Long quota) {
        this.quota = quota;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_info.state
     *
     * @return the value of user_info.state
     *
     * @mbggenerated
     */
    public Integer getState() {
        return state;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_info.state
     *
     * @param state the value for user_info.state
     *
     * @mbggenerated
     */
    public void setState(Integer state) {
        this.state = state;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_info.union_id
     *
     * @return the value of user_info.union_id
     *
     * @mbggenerated
     */
    public String getUnionId() {
        return unionId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_info.union_id
     *
     * @param unionId the value for user_info.union_id
     *
     * @mbggenerated
     */
    public void setUnionId(String unionId) {
        this.unionId = unionId == null ? null : unionId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_info.remark
     *
     * @return the value of user_info.remark
     *
     * @mbggenerated
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_info.remark
     *
     * @param remark the value for user_info.remark
     *
     * @mbggenerated
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_info.create_time
     *
     * @return the value of user_info.create_time
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_info.create_time
     *
     * @param createTime the value for user_info.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_info.update_time
     *
     * @return the value of user_info.update_time
     *
     * @mbggenerated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_info.update_time
     *
     * @param updateTime the value for user_info.update_time
     *
     * @mbggenerated
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_info.delete_time
     *
     * @return the value of user_info.delete_time
     *
     * @mbggenerated
     */
    public Date getDeleteTime() {
        return deleteTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_info.delete_time
     *
     * @param deleteTime the value for user_info.delete_time
     *
     * @mbggenerated
     */
    public void setDeleteTime(Date deleteTime) {
        this.deleteTime = deleteTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_info.delete_flag
     *
     * @return the value of user_info.delete_flag
     *
     * @mbggenerated
     */
    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_info.delete_flag
     *
     * @param deleteFlag the value for user_info.delete_flag
     *
     * @mbggenerated
     */
    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_info.create_operator
     *
     * @return the value of user_info.create_operator
     *
     * @mbggenerated
     */
    public String getCreateOperator() {
        return createOperator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_info.create_operator
     *
     * @param createOperator the value for user_info.create_operator
     *
     * @mbggenerated
     */
    public void setCreateOperator(String createOperator) {
        this.createOperator = createOperator == null ? null : createOperator.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_info.update_operator
     *
     * @return the value of user_info.update_operator
     *
     * @mbggenerated
     */
    public String getUpdateOperator() {
        return updateOperator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_info.update_operator
     *
     * @param updateOperator the value for user_info.update_operator
     *
     * @mbggenerated
     */
    public void setUpdateOperator(String updateOperator) {
        this.updateOperator = updateOperator == null ? null : updateOperator.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_info.delete_operator
     *
     * @return the value of user_info.delete_operator
     *
     * @mbggenerated
     */
    public String getDeleteOperator() {
        return deleteOperator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_info.delete_operator
     *
     * @param deleteOperator the value for user_info.delete_operator
     *
     * @mbggenerated
     */
    public void setDeleteOperator(String deleteOperator) {
        this.deleteOperator = deleteOperator == null ? null : deleteOperator.trim();
    }
}