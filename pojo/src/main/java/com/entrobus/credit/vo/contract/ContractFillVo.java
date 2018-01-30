package com.entrobus.credit.vo.contract;

public class ContractFillVo {
    private String signature;//签名
    private String contractNumber;//合同编号，与银行管理后台显示的编号一致
    private String borrowerFullName;//借款人全名
    private String lenderFullName;//贷款人全称，将来可配置，目前“中国建设银行股份有限公司佛山分行”
    private String borrowerCellphone;//借款人手机号
    private String borrowerIdCard;//借款人证件号（身份证）
    private String money; //借款金额
    private String capitalMoney; //中文大写金额，如：叁拾万元整
    private String term;//借款期限
    private String interestStartDay;//起息日
    private String repaymentMethod;//还款方式
    private String appointPayeeAccount;//指定收款账户，借款人的银行卡所属银行和卡号最后四位，例如：建设银行 2678
    private String annualInterestRate;//年化利率
    private String noOperationInvalidTime;//无操作失效时间
    private String borrowerAddress;//借款人住址
    private String borrowerPostalAddress;//通讯地址
    private String borrowerPostalCode;//通讯地址邮政编码
    private String borrowerCardBank;//借款人开卡银行
    private String borrowerCardId;//借款人银行卡号
    private String lenderName;//贷款人名称，暂填 中国建设银行
    private String lenderAddress;//贷款人住址，将来可配置，目前“广东省佛山市佛山大道南327号”
    private String lenderPostalAddress;//贷款人通讯地址，将来可配置，目前“广东省佛山市佛山大道南327号”
    private String lenderPostalCode;//贷款人通讯地址，将来可配置，目前“528000”
    private String lenderPhone;//贷款人联系电话，将来可配置，目前“0757-82781212”
    private String borrowerMailingAddress;//乙方（借款人）邮寄地址，从个人信用报告接口中返回
    private String loanValidityPeriodStart;//借款额度有效期开始日期，用户提交申请的当日日期
    private String loanValidityPeriodEnd;//借款额度有效期结束日期，借款额度有效期开始日期 一年后
    private String borrowerAutograph;//签名图片 使用base64编码

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    public String getBorrowerFullName() {
        return borrowerFullName;
    }

    public void setBorrowerFullName(String borrowerFullName) {
        this.borrowerFullName = borrowerFullName;
    }

    public String getLenderFullName() {
        return lenderFullName;
    }

    public void setLenderFullName(String lenderFullName) {
        this.lenderFullName = lenderFullName;
    }

    public String getBorrowerCellphone() {
        return borrowerCellphone;
    }

    public void setBorrowerCellphone(String borrowerCellphone) {
        this.borrowerCellphone = borrowerCellphone;
    }

    public String getBorrowerIdCard() {
        return borrowerIdCard;
    }

    public void setBorrowerIdCard(String borrowerIdCard) {
        this.borrowerIdCard = borrowerIdCard;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getCapitalMoney() {
        return capitalMoney;
    }

    public void setCapitalMoney(String capitalMoney) {
        this.capitalMoney = capitalMoney;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getInterestStartDay() {
        return interestStartDay;
    }

    public void setInterestStartDay(String interestStartDay) {
        this.interestStartDay = interestStartDay;
    }

    public String getRepaymentMethod() {
        return repaymentMethod;
    }

    public void setRepaymentMethod(String repaymentMethod) {
        this.repaymentMethod = repaymentMethod;
    }

    public String getAppointPayeeAccount() {
        return appointPayeeAccount;
    }

    public void setAppointPayeeAccount(String appointPayeeAccount) {
        this.appointPayeeAccount = appointPayeeAccount;
    }

    public String getAnnualInterestRate() {
        return annualInterestRate;
    }

    public void setAnnualInterestRate(String annualInterestRate) {
        this.annualInterestRate = annualInterestRate;
    }

    public String getNoOperationInvalidTime() {
        return noOperationInvalidTime;
    }

    public void setNoOperationInvalidTime(String noOperationInvalidTime) {
        this.noOperationInvalidTime = noOperationInvalidTime;
    }

    public String getBorrowerAddress() {
        return borrowerAddress;
    }

    public void setBorrowerAddress(String borrowerAddress) {
        this.borrowerAddress = borrowerAddress;
    }

    public String getBorrowerPostalAddress() {
        return borrowerPostalAddress;
    }

    public void setBorrowerPostalAddress(String borrowerPostalAddress) {
        this.borrowerPostalAddress = borrowerPostalAddress;
    }

    public String getBorrowerPostalCode() {
        return borrowerPostalCode;
    }

    public void setBorrowerPostalCode(String borrowerPostalCode) {
        this.borrowerPostalCode = borrowerPostalCode;
    }

    public String getBorrowerCardBank() {
        return borrowerCardBank;
    }

    public void setBorrowerCardBank(String borrowerCardBank) {
        this.borrowerCardBank = borrowerCardBank;
    }

    public String getBorrowerCardId() {
        return borrowerCardId;
    }

    public void setBorrowerCardId(String borrowerCardId) {
        this.borrowerCardId = borrowerCardId;
    }

    public String getLenderName() {
        return lenderName;
    }

    public void setLenderName(String lenderName) {
        this.lenderName = lenderName;
    }

    public String getLenderAddress() {
        return lenderAddress;
    }

    public void setLenderAddress(String lenderAddress) {
        this.lenderAddress = lenderAddress;
    }

    public String getLenderPostalAddress() {
        return lenderPostalAddress;
    }

    public void setLenderPostalAddress(String lenderPostalAddress) {
        this.lenderPostalAddress = lenderPostalAddress;
    }

    public String getLenderPostalCode() {
        return lenderPostalCode;
    }

    public void setLenderPostalCode(String lenderPostalCode) {
        this.lenderPostalCode = lenderPostalCode;
    }

    public String getLenderPhone() {
        return lenderPhone;
    }

    public void setLenderPhone(String lenderPhone) {
        this.lenderPhone = lenderPhone;
    }

    public String getBorrowerMailingAddress() {
        return borrowerMailingAddress;
    }

    public void setBorrowerMailingAddress(String borrowerMailingAddress) {
        this.borrowerMailingAddress = borrowerMailingAddress;
    }

    public String getLoanValidityPeriodStart() {
        return loanValidityPeriodStart;
    }

    public void setLoanValidityPeriodStart(String loanValidityPeriodStart) {
        this.loanValidityPeriodStart = loanValidityPeriodStart;
    }

    public String getLoanValidityPeriodEnd() {
        return loanValidityPeriodEnd;
    }

    public void setLoanValidityPeriodEnd(String loanValidityPeriodEnd) {
        this.loanValidityPeriodEnd = loanValidityPeriodEnd;
    }

    public String getBorrowerAutograph() {
        return borrowerAutograph;
    }

    public void setBorrowerAutograph(String borrowerAutograph) {
        this.borrowerAutograph = borrowerAutograph;
    }
}
