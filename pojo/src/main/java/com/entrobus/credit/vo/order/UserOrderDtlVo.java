package com.entrobus.credit.vo.order;

import java.util.List;

public class UserOrderDtlVo {

    private String userId;
    private Integer role;//角色
    private String roleName;//角色
    private String idCard;//身份证号
    private String name;//真实姓名
    private String mobile;// 手机号码
    private Integer score;//熵商信用分
    private String account;//银行卡信息
    private Integer userState;//用户状态
    private String quota;//额度
    private List<OrderListVo> orderList;
    private Integer state;


    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getQuota() {
        return quota;
    }

    public void setQuota(String quota) {
        this.quota = quota;
    }

    public String getUserId() {
        return userId;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
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

    public Integer getUserState() {
        return userState;
    }

    public void setUserState(Integer userState) {
        this.userState = userState;
    }

    public List<OrderListVo> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<OrderListVo> orderList) {
        this.orderList = orderList;
    }


}
