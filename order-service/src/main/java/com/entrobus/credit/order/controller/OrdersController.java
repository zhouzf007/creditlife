package com.entrobus.credit.order.controller;

import com.entrobus.credit.cache.Cachekey;
import com.entrobus.credit.common.Constants;
import com.entrobus.credit.common.bean.WebResult;
import com.entrobus.credit.common.util.AmountUtil;
import com.entrobus.credit.common.util.GUIDUtil;
import com.entrobus.credit.order.channel.GenSubOrderPublishChannel;
import com.entrobus.credit.order.client.PaymentClient;
import com.entrobus.credit.order.services.*;
import com.entrobus.credit.pojo.order.Contract;
import com.entrobus.credit.pojo.order.CreditReport;
import com.entrobus.credit.pojo.order.Orders;
import com.entrobus.credit.pojo.order.OrdersExample;
import com.entrobus.credit.pojo.payment.RepaymentPlan;
import com.entrobus.credit.vo.order.*;
import com.entrobus.credit.vo.user.CacheUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RefreshScope
@RestController
public class OrdersController {

    @Autowired
    OrdersService ordersService;
    @Autowired
    OrderInstanceService orderInstanceService;

    @Autowired
    OrderCacheService cacheService;

    @Autowired
    CreditReportService creditReportService;

    @Autowired
    ContractService contractService;

    @Autowired
    PaymentClient paymentClient;

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    GenSubOrderPublishChannel genSubOrderChannel;

    @Value("${msg:unknown}")
    private String msg;

    /**
     * 申请贷款
     *
     * @param vo
     */
    @PostMapping(path = "/loanOrder")
    public WebResult apply(@RequestBody ApplyVo vo) {
        Orders order = new Orders();
        order.setId(GUIDUtil.genRandomGUID());
        order.setUserId(vo.getUserId());
        order.setProdId(vo.getProdId());
        //@TODO 产品合法性检验
        //加入产品信息
        order.setRepaymentTerm(vo.getRepaymentTerm());
        order.setRepaymentType(vo.getRepaymentType());
        order.setInterestRate(vo.getRate());

        order.setLoanUsage(vo.getUsage());
        order.setApplyMoney(vo.getMoney());
        order.setApplyTime(new Date());

        //加入用户信息
        CacheUserInfo userInfo = cacheService.getUserCacheByUid(vo.getUserId());
        order.setRole(userInfo.getRole() + "");

        //信用报告信息
        Contract contract = new Contract();
        CreditReport creditReport = creditReportService.getCreditReportByUid(vo.getUserId());
        if (creditReport != null) {
            order.setCreditReportId(creditReport.getId());
            order.setCreditScore(creditReport.getCreditScore());
            contract.setCreditReportId(creditReport.getId());
            contract.setCreditScore(creditReport.getCreditScore());
        }
        //生成合同信息
        contract.setId(GUIDUtil.genRandomGUID());
        contract.setOrderId(order.getId());
        contract.setSignature(vo.getSignature());
        contractService.insertSelective(contract);
        order.setContractId(contract.getId());
        ordersService.insertSelective(order);
        Map rsMap = new HashMap<>();
        rsMap.put("applyNo", order.getApplyNo());
        return WebResult.ok(rsMap);
    }

    /**
     * 贷款订单详情
     *
     * @param id
     */
    @GetMapping(path = "/orderDtl/{id}")
    public WebResult apply(@PathVariable("id") String id) throws Exception {

        Orders order = ordersService.selectByPrimaryKey(id);
        CacheUserInfo userInfo = cacheService.getUserCacheByUid(order.getUserId());
        OrderDtlVo dtl = new OrderDtlVo();
        dtl.setId(order.getId());
        dtl.setName(userInfo.getRealName());
        dtl.setIdCard(userInfo.getIdCard());
        dtl.setApplyNo(order.getApplyNo());
        dtl.setApplyTime(order.getApplyTime());
        dtl.setAccount(order.getAccount());
        dtl.setMobile(userInfo.getCellphone());
        dtl.setRole(order.getRole());

        dtl.setMoney(AmountUtil.changeF2Y(order.getApplyMoney()));
        dtl.setRate(AmountUtil.change2Percent(order.getInterestRate()));
        dtl.setRepaymentTerm(order.getRepaymentTerm() + "");
        dtl.setRepaymentType(order.getRepaymentType() + "");
        dtl.setUsage(order.getLoanUsage());
        dtl.setScore(order.getCreditScore());

        dtl.setContractId(order.getContractId());
        dtl.setCreditReportId(order.getCreditReportId());
        dtl.setStateName(cacheService.translate(Cachekey.Translation.ORDER_STATE + order.getState()));
        List<RepaymentPlanVo> planList = new ArrayList<>();
        if (order.getState() == Constants.ORDER_STATE.PASS || order.getState() == Constants.ORDER_STATE.OVERDUE || order.getState() == Constants.ORDER_STATE.FINISHED) {
            List<RepaymentPlan> list = paymentClient.getOrderRepaymentPlan(order.getId());
            for (int i = 0; i < list.size(); i++) {
                RepaymentPlan plan = list.get(i);
                RepaymentPlanVo vo = new RepaymentPlanVo();
                vo.setId(plan.getId());
                vo.setMoney(AmountUtil.changeF2Y(plan.getMoney()));
                vo.setRepayTime(plan.getPlanTime());
                vo.setUpdateTime(plan.getUpdateTime());
                vo.setOperator(plan.getUpdateOperator());
                vo.setStateName(cacheService.translate(Cachekey.Translation.REPAYMENT_STATE + plan.getState()));
                planList.add(vo);
            }
        }
        dtl.setList(planList);
        Map rsMap = new HashMap<>();
        rsMap.put("orderDtl", dtl);
        return WebResult.ok(rsMap);
    }

    /**
     * 用户的贷款状态
     * 未贷款-1,待审核 0,待放款 1,已驳回 2,使用中 3,使用中（已逾期）4,已完成 5
     *
     * @param id
     * @return
     */
    @GetMapping(path = "/userLoanState/{id}")
    public int getUserLoanState(@PathVariable("id") String id) {
        Orders lastOrder = ordersService.getUserLastOrder(id);
        if (lastOrder == null) return Constants.ORDER_STATE.NOT_LOAN;
        return lastOrder.getState();
    }

    /**
     * 查询指定用户的订单列表
     *
     * @param userId
     */
    @GetMapping(path = "/userOrder/{userId}")
    public void getUserOrder(@PathVariable("userId") String userId) {
        ordersService.getUserOrders(userId);
    }

    /**
     * 查询指定订单
     *
     * @param id
     * @return
     */
    @GetMapping(path = "/order/{id}")
    public Orders getOrder(@PathVariable("id") String id) {
        return ordersService.selectByPrimaryKey(id);
    }

    /**
     * 订单列表（银行端）
     *
     * @param vo
     * @return
     */
    @GetMapping(path = "/orderList")
    public WebResult getOrderList(@RequestBody OrderQueryVo vo) throws Exception {
        OrdersExample example = new OrdersExample();
        OrdersExample.Criteria criteria = example.createCriteria();
        criteria.andDeleteFlagEqualTo(Constants.DeleteFlag.NO);
        if (vo.getState() != null) {
            criteria.andStateEqualTo(vo.getState());
        }
        example.setOrderByClause(" create_time desc ");
        List<Orders> list = ordersService.selectByExample(example);
        List<OrderListVo> rsList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Orders order = list.get(i);
            OrderListVo orderVo = new OrderListVo();
            orderVo.setApplyNo(order.getApplyNo());
            orderVo.setApplyTime(order.getApplyTime());
            orderVo.setId(order.getId());
            orderVo.setMoney(AmountUtil.changeF2Y(order.getApplyMoney()));
            CacheUserInfo userInfo = cacheService.getUserCacheByUid(order.getUserId());
            orderVo.setUserName(userInfo.getRealName());
            orderVo.setStateName(cacheService.translate(Cachekey.Translation.ORDER_STATE + order.getState()));
            rsList.add(orderVo);
        }
        Map rsMap = new HashMap<>();
        rsMap.put("list", rsList);
        return WebResult.ok(rsMap);
    }

    /**
     * 订单列表（熵商管理端）
     *
     * @param vo
     * @return
     */
    @GetMapping(path = "/userOrderList")
    public WebResult getUserOrderList(@RequestBody OrderQueryVo vo) throws Exception {
        OrdersExample example = new OrdersExample();
        OrdersExample.Criteria criteria = example.createCriteria();
        criteria.andDeleteFlagEqualTo(Constants.DeleteFlag.NO);
        if (vo.getState() != null) {
            criteria.andStateEqualTo(vo.getState());
        }
        example.setOrderByClause(" create_time desc ");
        List<Orders> list = ordersService.selectByExample(example);
        List<OrderListVo> rsList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Orders order = list.get(i);
            OrderListVo rsorderVo = new OrderListVo();
            rsorderVo.setMoney(AmountUtil.changeF2Y(order.getApplyMoney()));
            CacheUserInfo userInfo = cacheService.getUserCacheByUid(order.getUserId());
            rsorderVo.setUserName(userInfo.getRealName());
            rsorderVo.setMobile(userInfo.getCellphone());
            rsorderVo.setScore(order.getCreditScore());
            rsorderVo.setStateName(cacheService.translate(Cachekey.Translation.ORDER_STATE + order.getState()));
            rsList.add(rsorderVo);
        }
        Map rsMap = new HashMap<>();
        rsMap.put("list", rsList);
        return WebResult.ok(rsMap);
    }

    /**
     * 用户贷款详情
     *
     * @param userId
     */
    @GetMapping(path = "/userOrderDtl/{userId}")
    public WebResult getUserOrderDtl(@PathVariable("userId") String userId) throws Exception {

        CacheUserInfo userInfo = cacheService.getUserCacheByUid(userId);
        UserOrderDtlVo dtl = new UserOrderDtlVo();
        dtl.setUserId(userInfo.getId());
        dtl.setName(userInfo.getRealName());
        dtl.setIdCard(userInfo.getIdCard());
        dtl.setQuota(AmountUtil.changeF2Y(userInfo.getQuota()));
        dtl.setAccount("建设银行");
        dtl.setMobile(userInfo.getCellphone());
        dtl.setRole(userInfo.getRole() + "");
        dtl.setScore(userInfo.getCreditScore());
        List<OrderListVo> rsOrderList = new ArrayList<>();
        List<Orders> orderList = ordersService.getUserOrders(userId);
        for (int i = 0; i < orderList.size(); i++) {
            Orders order = orderList.get(i);
            OrderListVo vo = new OrderListVo();
            vo.setApplyNo(order.getApplyNo());
            vo.setMoney(AmountUtil.changeF2Y(order.getApplyMoney()));
            vo.setApplyTime(order.getApplyTime());
            vo.setUpdateTime(order.getUpdateTime());
            vo.setStateName(cacheService.translate(Cachekey.Translation.ORDER_STATE + order.getState()));
            rsOrderList.add(vo);
        }
        dtl.setOrderList(rsOrderList);
        Map rsMap = new HashMap<>();
        rsMap.put("orderDtl", dtl);
        return WebResult.ok(rsMap);
    }

    /**
     * 订单状态更新
     * 审核，放款，驳回，逾期，结清
     *
     * @param id
     * @param order
     */
    @PutMapping("/order/{id}")
    public WebResult updateOrder(@PathVariable("id") String id, @RequestBody Orders order) {
        Orders loanOrder = ordersService.selectByPrimaryKey(id);
        if (loanOrder != null) {
            if (loanOrder.getState() == Constants.ORDER_STATE.AUIDT_PENGDING && order.getState() == Constants.ORDER_STATE.LOAN_PENGDING) {
                //审核
                loanOrder.setState(Constants.ORDER_STATE.LOAN_PENGDING);
                loanOrder.setAuditor(order.getAuditor());
                loanOrder.setAuditTime(new Date());
                ordersService.updateByPrimaryKeySelective(loanOrder);
            } else if ((loanOrder.getState() == Constants.ORDER_STATE.AUIDT_PENGDING || loanOrder.getState() == Constants.ORDER_STATE.LOAN_PENGDING) && order.getState() == Constants.ORDER_STATE.PASS) {
                //放款
                loanOrder.setState(Constants.ORDER_STATE.PASS);
                loanOrder.setAuditor(order.getAuditor());
                loanOrder.setAuditTime(new Date());
                ordersService.updateByPrimaryKeySelective(loanOrder);
                //还款计划
                Message<Orders> msgs = MessageBuilder.withPayload(loanOrder).build();
                genSubOrderChannel.generateSubOrder().send(msgs);
            } else if (loanOrder.getState() == Constants.ORDER_STATE.AUIDT_PENGDING && order.getState() == Constants.ORDER_STATE.REJECTION) {
                //驳回
                loanOrder.setState(Constants.ORDER_STATE.REJECTION);
                loanOrder.setAuditor(order.getAuditor());
                loanOrder.setAuditTime(new Date());
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
            }
        }
        return WebResult.ok();
    }
}