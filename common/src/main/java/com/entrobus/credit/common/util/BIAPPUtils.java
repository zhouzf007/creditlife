package com.entrobus.credit.common.util;

import java.math.BigDecimal;

public class BIAPPUtils {

    /**
     * <p>Description: 月还款利息  贷款本金×月利率 </p>
     *
     * @param loan                贷款本金
     * @param monthlyInterestRate 月利率
     * @return
     */


    public static BigDecimal monthlyInterest(BigDecimal loan, BigDecimal monthlyInterestRate) {
        return loan.multiply(monthlyInterestRate, MoneyUtils.MATHCONTEXT);
    }

    /**
     * <p>Description: 还款总利息  贷款本金×月利率×期数</p>
     *
     * @param loan                贷款本金
     * @param monthlyInterestRate 月利率
     * @param number              期数
     * @return
     */


    public static BigDecimal interest(BigDecimal loan, BigDecimal monthlyInterestRate, int number) {
        return loan.multiply(monthlyInterestRate).multiply(new BigDecimal(number), MoneyUtils.MATHCONTEXT);
    }

    /**
     * <p>Description: 月还款额</p>
     *
     * @param loan                贷款本金
     * @param monthlyInterestRate 月利率
     * @param amount              期数
     * @param curNumber           当前期数
     * @return
     */


    public static BigDecimal monthlyRepayment(BigDecimal loan, BigDecimal monthlyInterestRate, int amount, int curNumber) {
        BigDecimal monthlyInterest = monthlyInterest(loan, monthlyInterestRate);
        if (amount == curNumber) {
            return monthlyInterest.add(loan, MoneyUtils.MATHCONTEXT);//最后月还款额
        } else {
            return monthlyInterest;
        }
    }

}