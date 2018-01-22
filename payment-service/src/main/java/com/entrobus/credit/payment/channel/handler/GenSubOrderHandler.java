package com.entrobus.credit.payment.channel.handler;

import com.entrobus.credit.common.Constants;
import com.entrobus.credit.common.util.DateUtils;
import com.entrobus.credit.payment.channel.GenSubOrderSubscribeChannel;
import com.entrobus.credit.payment.services.RepaymentPlanService;
import com.entrobus.credit.payment.services.RepaymentService;
import com.entrobus.credit.pojo.order.Orders;
import com.entrobus.credit.pojo.payment.Repayment;
import com.entrobus.credit.pojo.payment.RepaymentPlan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

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

    @StreamListener(GenSubOrderSubscribeChannel.GENERATE_SUB_ORDER_SUBSCRIBE)
    public void generateSubOrder(Orders order) throws Exception {
        if (order == null) return;
        Repayment repayment = new Repayment();
        int term = order.getRepaymentTerm();
        String orderId = order.getId();
        String account = order.getAccount();
        String userId = order.getUserId();
        String createOp = order.getCreateOperator();
        repayment.setApplyNo(order.getApplyNo());
        repayment.setOrderId(orderId);
        repayment.setApplyTime(order.getApplyTime());
        repayment.setAccount(order.getAccount());
        repayment.setRepaymentTerm(term);
        repayment.setRepaymentType(order.getRepaymentType());
        repayment.setState(Constants.REPAYMENT_ORDER_STATE.PASS);
        repayment.setSystemState(Constants.REPAYMENT_ORDER_STATE.PASS);
        repayment.setCreateOperator(createOp);
        repayment.setUserId(userId);
        repaymentService.insertSelective(repayment);
        String repaymentId = repayment.getId();
        //@TODO 计算每期的金额 以及 每期还款日期
        long rsMoney = order.getActualMoney() / term;//这里先简单写一个
        Date rsDate = DateUtils.addMonths(new Date(), 1);//默认按照一月一还
        for (int i = 0; i < term; i++) {
            RepaymentPlan plan = new RepaymentPlan();
            plan.setOrderId(orderId);
            plan.setSortId(i + 1);
            plan.setUserId(userId);
            plan.setState(Constants.REPAYMENT_ORDER_STATE.PASS);
            plan.setSystemState(Constants.REPAYMENT_ORDER_STATE.PASS);
            plan.setRepaymentId(repaymentId);
            plan.setAccount(account);
            plan.setMoney(rsMoney);
            plan.setPlanTime(rsDate);
            plan.setCreateOperator(createOp);
            repaymentPlanService.insertSelective(plan);
            rsDate = DateUtils.addMonths(rsDate, 1);
        }
    }

}
