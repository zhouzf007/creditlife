package com.entrobus.credit.vo.user;

/**
 * Created by mozl on 2018/1/11.
 */
public class UserAccountInfo {

    private String id;
    private String account;
    private String bank;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String name;
    private Integer type;
    private Integer state;
    private Long balance;
    private String cellphone;

    public Integer getIsDefualt() {
        return isDefualt;
    }

    public void setIsDefualt(Integer isDefualt) {
        this.isDefualt = isDefualt;
    }

    private Integer isDefualt;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }
}
