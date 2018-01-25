package com.entrobus.credit.common.util;

import java.math.BigDecimal;

/**
 * <p>Title: 等额本息还款工具类</p>
 *
 */
public class CPMUtils{

    /**
     * <p>Description: 每月还款总额。〔贷款本金×月利率×（1＋月利率）＾还款月数〕÷〔（1＋月利率）＾还款月数－1〕</p>
     * @param principal 贷款本金
     * @param monthlyInterestRate 月利率
     * @param amount 期数
     * @return
     */
    public static BigDecimal monthlyRepayment(BigDecimal principal, BigDecimal monthlyInterestRate, int amount){
        //（1＋月利率）＾还款月数
        BigDecimal temp = monthlyInterestRate.add(MoneyUtils.ONE).pow(amount);
        return principal.multiply(monthlyInterestRate)
                .multiply(temp)
                .divide(temp.subtract(MoneyUtils.ONE), MoneyUtils.MATHCONTEXT);
    }

    /**
     * <p>Description: 月还款利息。（贷款本金×月利率-月还款额）*（1+月利率)^（当前期数-1）+月还款额</p>
     * @param principal 贷款本金
     * @param monthlyInterestRate 月利率
     * @param monthlyRepayment 月还款额
     * @param number 当前期数
     * @return
     */
    public static BigDecimal monthlyInterest(BigDecimal principal, BigDecimal monthlyInterestRate, BigDecimal monthlyRepayment, int number){
        //（1+月利率)^（当前期数-1）
        BigDecimal temp = monthlyInterestRate.add(MoneyUtils.ONE).pow(number - 1);
        return principal.multiply(monthlyInterestRate)
                .subtract(monthlyRepayment)
                .multiply(temp).add(monthlyRepayment, MoneyUtils.MATHCONTEXT);
    }

    /**
     * <p>Description: 还款总利息。期数×贷款本金×月利率×（1＋月利率）^期数÷〔（1＋月利率）^期数－1〕－贷款本金 </p>
     * @param principal 贷款本金
     * @param monthlyInterestRate 月利率
     * @param amount 还款期数
     * @return
     */
    public static BigDecimal interest(BigDecimal principal, BigDecimal monthlyInterestRate, int amount){
        //（1＋月利率）^期数
        BigDecimal temp = monthlyInterestRate.add(MoneyUtils.ONE).pow(amount);
        return new BigDecimal(amount)
                .multiply(principal)
                .multiply(monthlyInterestRate)
                .multiply(temp)
                .divide(temp.subtract(MoneyUtils.ONE), MoneyUtils.MATHCONTEXT)
                .subtract(principal, MoneyUtils.MATHCONTEXT);
    }

    /**
     * <p>Description: 月还款本金。已经精确到分位，未做单位换算</p>
     * @param principal 贷款本金
     * @param monthlyInterestRate 月利率
     * @param monthlyRepayment 月还款额
     * @param number 当前期数
     * @return
     */
    public static BigDecimal monthlyPrincipal(BigDecimal principal, BigDecimal monthlyInterestRate, BigDecimal monthlyRepayment, int number){
        BigDecimal monthInterest = monthlyInterest(principal, monthlyInterestRate, monthlyRepayment, number);
        //月还款额-月还款利息
        return monthlyRepayment.subtract(monthInterest).setScale(MoneyUtils.MONEYSHOWSCALE, MoneyUtils.SAVEROUNDINGMODE);
    }

    /**
     * <p>Description: 月还款本金。已经精确到分位，未做单位换算</p>
     * @param monthRepayment 月还款总额
     * @param monthInterest 月还款利息
     * @return
     */
    public static BigDecimal monthPrincipal(BigDecimal monthRepayment, BigDecimal monthInterest){
        //月还款总额-月还款利息
        return monthRepayment.subtract(monthInterest).setScale(MoneyUtils.MONEYSHOWSCALE, MoneyUtils.SAVEROUNDINGMODE);
    }

}