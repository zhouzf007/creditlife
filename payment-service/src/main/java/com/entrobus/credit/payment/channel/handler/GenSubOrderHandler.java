package com.entrobus.credit.payment.channel.handler;

import com.entrobus.credit.common.Constants;
import com.entrobus.credit.common.util.*;
import com.entrobus.credit.payment.channel.GenSubOrderSubscribeChannel;
import com.entrobus.credit.payment.client.UserClient;
import com.entrobus.credit.payment.services.RepaymentPlanService;
import com.entrobus.credit.payment.services.RepaymentService;
import com.entrobus.credit.pojo.order.Orders;
import com.entrobus.credit.pojo.payment.Repayment;
import com.entrobus.credit.pojo.payment.RepaymentPlan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author: zhouzf
 * @CreateTime: 2018/01/04 11:35
 * @Description:
 */
@Component
public class GenSubOrderHandler {


    @Autowired
    private RepaymentPlanService repaymentPlanService;

    @Autowired
    private RepaymentService repaymentService;

    @Autowired
    private UserClient userClient;

    @StreamListener(GenSubOrderSubscribeChannel.GENERATE_SUB_ORDER_SUBSCRIBE)
    public void generateSubOrder(Orders order) throws Exception {
        if (order == null) return;
        Repayment repayment = new Repayment();
        int term = order.getRepaymentTerm();
        String orderId = order.getId();
        String account = order.getAccount();
        String userId = order.getUserId();
        String createOp = order.getCreateOperator();
        repayment.setId(GUIDUtil.genRandomGUID());
        repayment.setApplyNo(order.getApplyNo());
        repayment.setPrincipal(order.getActualMoney());
        repayment.setOrderId(orderId);
        repayment.setApplyTime(order.getApplyTime());
        repayment.setAccount(order.getAccount());
        repayment.setRepaymentTerm(term);
        repayment.setState(Constants.REPAYMENT_ORDER_STATE.PASS);
        repayment.setSystemState(Constants.REPAYMENT_ORDER_STATE.PASS);
        repayment.setCreateOperator(createOp);
        repayment.setUserId(userId);
        repayment.setRepaymentType(order.getRepaymentType());
        BigDecimal principal = MoneyUtils.divide(order.getActualMoney().toString(),"100");
        BigDecimal monthRate = MoneyUtils.divide(order.getInterestRate().toString(), "120000");
        BigDecimal totaLInterest = new BigDecimal(0);
        if (order.getRepaymentType() == Constants.REPAYMENT_TYPE.INTEREST_CAPITAL) {
            totaLInterest = BIAPPUtils.interest(principal, monthRate, term);
        } else if (order.getRepaymentType() == Constants.REPAYMENT_TYPE.MONTH_EQUAL) {
            totaLInterest = CPMUtils.interest(principal, monthRate, term);
        }
        repayment.setInterest(totaLInterest.longValue());
        repaymentService.insertSelective(repayment);
        String repaymentId = repayment.getId();
        //@TODO 计算每期的金额 以及 每期还款日期
        Date repayDate = DateUtils.addMonths(order.getLoanTime(), 1);//默认按照一月一还
        if (DateUtils.getDay(repayDate) > 28) {
            repayDate = DateUtils.setDays(repayDate, 28);//当月大于28号按28号算
        }
        for (int i = 0; i < term; i++) {
            RepaymentPlan plan = new RepaymentPlan();
            plan.setOrderId(orderId);
            plan.setSortId(i + 1);
            plan.setUserId(userId);
            plan.setState(Constants.REPAYMENT_ORDER_STATE.PASS);
            plan.setSystemState(Constants.REPAYMENT_ORDER_STATE.PASS);
            plan.setRepaymentId(repaymentId);
            plan.setAccount(account);
            if (order.getRepaymentType() == Constants.REPAYMENT_TYPE.INTEREST_CAPITAL) {
                BigDecimal monthlyRepayment = BIAPPUtils.monthlyRepayment(principal, monthRate, term, i + 1).multiply(new BigDecimal(100));
                BigDecimal monthlyInterest = BIAPPUtils.monthlyInterest(principal, monthRate).multiply(new BigDecimal(100));
                plan.setInterest(monthlyInterest.longValue());
                plan.setRepayment(monthlyRepayment.longValue());
                plan.setPrincipal(i==term-1?principal.longValue()*100:0);
            } else if (order.getRepaymentType() == Constants.REPAYMENT_TYPE.MONTH_EQUAL) {
                BigDecimal monthlyRepayment = CPMUtils.monthlyRepayment(principal, monthRate, term);
                BigDecimal monthlyInterest = CPMUtils.monthlyInterest(principal, monthRate, monthlyRepayment, i + 1);
                BigDecimal monthlyPrincipal = CPMUtils.monthPrincipal(monthlyRepayment, monthlyInterest);
                plan.setInterest(monthlyInterest.longValue()*100);
                plan.setRepayment(monthlyRepayment.longValue()*100);
                plan.setPrincipal(monthlyPrincipal.longValue()*100);
            }
            plan.setPlanTime(repayDate);
            plan.setCreateOperator(createOp);
            repaymentPlanService.insertSelective(plan);
            repayDate = DateUtils.addMonths(repayDate, 1);
        }
        userClient.updateUserQuta(order.getUserId(),-order.getActualMoney());
    }

}
