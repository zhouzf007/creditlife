package com.entrobus.credit.order.controller;

import com.entrobus.credit.cache.Cachekey;
import com.entrobus.credit.common.Constants;
import com.entrobus.credit.common.bean.WebResult;
import com.entrobus.credit.common.util.AmountUtil;
import com.entrobus.credit.common.util.DateUtils;
import com.entrobus.credit.order.channel.GenSubOrderPublishChannel;
import com.entrobus.credit.order.client.PaymentClient;
import com.entrobus.credit.order.client.ProductionClient;
import com.entrobus.credit.order.client.UserClient;
import com.entrobus.credit.order.services.ContractService;
import com.entrobus.credit.order.services.OrderCacheService;
import com.entrobus.credit.order.services.OrderInstanceService;
import com.entrobus.credit.order.services.OrdersService;
import com.entrobus.credit.pojo.order.Orders;
import com.entrobus.credit.pojo.payment.RepaymentPlan;
import com.entrobus.credit.vo.order.*;
import com.entrobus.credit.vo.user.CacheUserInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RefreshScope
@RestController
public class OrdersInterController {

    @Autowired
    OrdersService ordersService;

    @Autowired
    OrderInstanceService orderInstanceService;

    @Autowired
    OrderCacheService cacheService;

    @Autowired
    UserClient userClient;

    @Autowired
    ContractService contractService;

    @Autowired
    PaymentClient paymentClient;

    @Autowired
    ProductionClient productionClient;

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    GenSubOrderPublishChannel genSubOrderChannel;


    /**
     * 订单列表
     *
     * @param
     * @return
     */
    @GetMapping(path = "/orderList")
    public WebResult getOrderList(String states, String key, String orgId, Integer offset, Integer limit) throws Exception {
        List<Integer> stateList = new ArrayList<>();
        if (StringUtils.isNotEmpty(states)) {
            String[] ss = states.split(",");
            for (String s : ss) {
                stateList.add(Integer.parseInt(s));
            }
        }
        return ordersService.getOrderList(stateList, orgId, offset, limit);
    }

    /**
     * 贷款订单详情
     *
     * @param id
     */
    @GetMapping(path = "/orderDtl")
    public OrderDtlVo getOrderDtl(@RequestParam("id") String id) throws Exception {
        Orders order = ordersService.selectByPrimaryKey(id);
        CacheUserInfo userInfo = cacheService.getUserCacheByUid(order.getUserId());
        OrderDtlVo dtl = new OrderDtlVo();
        dtl.setId(order.getId());
        dtl.setUserId(userInfo.getId());
        dtl.setName(userInfo.getRealName());
        dtl.setIdCard(userInfo.getIdCard());
        dtl.setApplyNo(order.getApplyNo());
        dtl.setApplyTime(order.getApplyTime());
        dtl.setAccount(order.getAccount());
        dtl.setMobile(userInfo.getCellphone());
        dtl.setRole(order.getRole());
        dtl.setRoleName(cacheService.translate(Cachekey.Translation.ROLE_NAME + order.getRole()));
        dtl.setMoney(AmountUtil.changeF2Y(order.getApplyMoney()));
        dtl.setRate(AmountUtil.change2Percent(order.getInterestRate()));
        dtl.setRepaymentTerm(order.getRepaymentTerm() + "");
        dtl.setRepaymentType(order.getRepaymentType() + "");
        dtl.setRepaymentTypeName(cacheService.translate(Cachekey.Translation.REPAYMENT_TYPE + order.getRepaymentType()));
        dtl.setUsage(order.getLoanUsage());
        dtl.setScore(order.getCreditScore());
        dtl.setContractId(order.getContractId());
        dtl.setCreditReportId(order.getCreditReportId());
        dtl.setAuditor(order.getAuditor());
        dtl.setAuditTime(order.getAuditTime());
        dtl.setLoanOperator(order.getLoanOperator());
        dtl.setLoanTime(order.getLoanTime());
        dtl.setUserState(userInfo.getState());
        dtl.setReason(order.getReason());
        dtl.setState(order.getState());
        dtl.setStateName(cacheService.translate(Cachekey.Translation.ORDER_STATE + order.getState()));
        List<RepaymentPlanVo> planList = new ArrayList<>();
        if (order.getState() == Constants.ORDER_STATE.PASS || order.getState() == Constants.ORDER_STATE.OVERDUE || order.getState() == Constants.ORDER_STATE.FINISHED) {
            List<RepaymentPlan> list = paymentClient.getOrderRepaymentPlan(order.getId());
            for (int i = 0; i < list.size(); i++) {
                RepaymentPlan plan = list.get(i);
                RepaymentPlanVo vo = new RepaymentPlanVo();
                vo.setId(plan.getId());
                vo.setMoney(AmountUtil.changeF2Y(plan.getPrincipal()));
                vo.setRepayTime(plan.getPlanTime());
                vo.setUpdateTime(plan.getUpdateTime());
                vo.setOperator(plan.getUpdateOperator());
                vo.setState(plan.getState());
                vo.setStateName(cacheService.translate(Cachekey.Translation.REPAYMENT_STATE + plan.getState()));
                planList.add(vo);
            }
        }
        dtl.setList(planList);
        return dtl;
    }

    /**
     * 用户订单列表
     *
     * @param
     * @return
     */
    @GetMapping(path = "/userOrderList")
    public WebResult getUserOrderList(String states, String key, String orgId, Integer offset, Integer limit) throws Exception {
        List<Integer> stateList = new ArrayList<>();
        if (StringUtils.isNotEmpty(states)) {
            String[] ss = states.split(",");
            for (String s : ss) {
                stateList.add(Integer.parseInt(s));
            }
        }
        return ordersService.getUserOrderList(stateList, orgId, offset, limit);
    }

    /**
     * 用户贷款详情
     *
     * @param userId
     */
    @GetMapping(path = "/userOrderDtl")
    public UserOrderDtlVo getUserOrderDtl(@RequestParam("userId") String userId) throws Exception {
        CacheUserInfo userInfo = cacheService.getUserCacheByUid(userId);
        UserOrderDtlVo dtl = new UserOrderDtlVo();
        dtl.setUserId(userInfo.getId());
        dtl.setName(userInfo.getRealName());
        dtl.setIdCard(userInfo.getIdCard());
        dtl.setQuota(AmountUtil.changeF2Y(userInfo.getQuota()));
        dtl.setAccount(userInfo.getAccountBank() + "(" + userInfo.getDefualtAccount() + ")");
        dtl.setUserState(userInfo.getState());
        dtl.setMobile(userInfo.getCellphone());
        dtl.setRole(userInfo.getRole());
        dtl.setRoleName(cacheService.translate(Cachekey.Translation.ROLE_NAME + userInfo.getRole()));
        dtl.setScore(userInfo.getCreditScore());
        List<OrderListVo> rsOrderList = new ArrayList<>();
        List<Orders> orderList = ordersService.getUserOrders(userId);
        for (int i = 0; i < orderList.size(); i++) {
            Orders order = orderList.get(i);
            OrderListVo vo = new OrderListVo();
            vo.setId(order.getId());
            vo.setState(order.getState());
            vo.setApplyNo(order.getApplyNo());
            vo.setMoney(AmountUtil.changeF2Y(order.getApplyMoney()));
            vo.setApplyTime(order.getApplyTime());
            vo.setUpdateTime(order.getUpdateTime());
            vo.setStateName(cacheService.translate(Cachekey.Translation.ORDER_STATE + order.getState()));
            rsOrderList.add(vo);
        }
        dtl.setOrderList(rsOrderList);
        return dtl;
    }


    /**
     * 订单状态更新
     * 审核，放款，驳回，逾期，结清
     *
     * @param order
     */
    @PutMapping(value = "/order", consumes = MediaType.APPLICATION_JSON_VALUE)
    public WebResult updateOrder(@RequestBody OrderUpdateVo order) {
        Orders loanOrder = ordersService.selectByPrimaryKey(order.getId());
        if (loanOrder != null) {
            if (loanOrder.getState() == Constants.ORDER_STATE.AUIDT_PENGDING && order.getState() == Constants.ORDER_STATE.LOAN_PENGDING) {
                //审核
                loanOrder.setState(Constants.ORDER_STATE.LOAN_PENGDING);
                loanOrder.setAuditor(order.getAuditor());
                loanOrder.setAuditTime(new Date());
                ordersService.updateByPrimaryKeySelective(loanOrder);
            } else if ((loanOrder.getState() == Constants.ORDER_STATE.AUIDT_PENGDING || loanOrder.getState() == Constants.ORDER_STATE.LOAN_PENGDING) && order.getState() == Constants.ORDER_STATE.PASS) {
                //放款
                if (loanOrder.getAuditTime() == null) loanOrder.setAuditTime(new Date());
                if (StringUtils.isEmpty(loanOrder.getAuditor())) loanOrder.setAuditor(order.getAuditor());
                loanOrder.setState(Constants.ORDER_STATE.PASS);
                loanOrder.setLoanOperator(order.getLoanOperator());
                if (StringUtils.isNotEmpty(order.getLoanTimeStr())) {
                    loanOrder.setLoanTime(DateUtils.parseDate(order.getLoanTimeStr()));
                } else {
                    loanOrder.setLoanTime(new Date());
                }
                loanOrder.setActualMoney(order.getActualMoney() == null ? loanOrder.getApplyMoney() : order.getActualMoney());
                ordersService.updateByPrimaryKeySelective(loanOrder);
                //还款计划
                Message<Orders> msgs = MessageBuilder.withPayload(loanOrder).build();
                genSubOrderChannel.generateSubOrder().send(msgs);
            } else if (loanOrder.getState() == Constants.ORDER_STATE.AUIDT_PENGDING && order.getState() == Constants.ORDER_STATE.REJECTION) {
                //驳回
                loanOrder.setState(Constants.ORDER_STATE.REJECTION);
                loanOrder.setAuditor(order.getAuditor());
                loanOrder.setAuditTime(new Date());
                loanOrder.setRejectType(order.getRejectType());
                loanOrder.setReason(order.getReason());//原因
                loanOrder.setActualMoney(order.getActualMoney());//授信额度
                ordersService.updateByPrimaryKeySelective(loanOrder);
            } else if (loanOrder.getState() == Constants.ORDER_STATE.PASS && order.getState() == Constants.ORDER_STATE.OVERDUE) {
                //逾期
                loanOrder.setState(Constants.ORDER_STATE.OVERDUE);
                loanOrder.setUpdateOperator(order.getUpdateOperator());
                ordersService.updateByPrimaryKeySelective(loanOrder);
            } else if (order.getState() == Constants.ORDER_STATE.FINISHED) {
                //已完成
                loanOrder.setState(Constants.ORDER_STATE.FINISHED);
                loanOrder.setUpdateOperator(order.getUpdateOperator());
                ordersService.updateByPrimaryKeySelective(loanOrder);
                //生成订单实例
                orderInstanceService.saveOrderInstance(loanOrder);
            } else {
                return WebResult.error(WebResult.CODE_BUSI_DISPERMIT, "订单状态异常");
            }
        }
        return WebResult.ok();
    }

    /**
     * 查询指定订单
     *
     * @param id
     * @return
     */
    @GetMapping(path = "/order")
    public Orders getOrder(@RequestParam("id") String id) {
        return ordersService.selectByPrimaryKey(id);
    }

    /**
     * 查询指定用户的订单列表
     *
     * @param userId
     */
    @GetMapping(path = "/userOrder/{userId}")
    public List<Orders> getUserOrder(@PathVariable("userId") String userId) {
        return ordersService.getUserOrders(userId);
    }

}