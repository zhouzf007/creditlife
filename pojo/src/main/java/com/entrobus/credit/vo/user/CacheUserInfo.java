package com.entrobus.credit.vo.user;

import java.util.List;

/**
 * Created by mozl on 2018/1/11.
 */
public class CacheUserInfo {

    private String id;
    private String name;//昵称
    private String realName;//真实姓名
    private String idCard;//身份证
    private String portrait;//头像
    private String cellphone;//手机号
    private String email;//邮箱
    private Integer age;//年龄
    private Integer gender;//性别
    private Integer creditScore;//熵商信用分
    private Long quota;//预估额度
    private String unionId;//unionId
    private Integer role;//角色
    private String defualtAccount;//默认账户

    public String getDefualtAccount() {
        return defualtAccount;
    }

    public void setDefualtAccount(String defualtAccount) {
        this.defualtAccount = defualtAccount;
    }

    public String getDefualtAccountId() {
        return defualtAccountId;
    }

    public void setDefualtAccountId(String defualtAccountId) {
        this.defualtAccountId = defualtAccountId;
    }

    private String defualtAccountId;//默认账户
    private List<UserAccountInfo> userAccountInfos;//用户帐号

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getCreditScore() {
        return creditScore;
    }

    public void setCreditScore(Integer creditScore) {
        this.creditScore = creditScore;
    }

    public Long getQuota() {
        return quota;
    }

    public void setQuota(Long quota) {
        this.quota = quota;
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public List<UserAccountInfo> getUserAccountInfos() {
        return userAccountInfos;
    }

    public void setUserAccountInfos(List<UserAccountInfo> userAccountInfos) {
        this.userAccountInfos = userAccountInfos;
    }
}
