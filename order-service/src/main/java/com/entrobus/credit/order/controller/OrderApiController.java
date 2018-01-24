package com.entrobus.credit.order.controller;

import com.entrobus.credit.common.Constants;
import com.entrobus.credit.common.bean.WebResult;
import com.entrobus.credit.order.channel.GenSubOrderPublishChannel;
import com.entrobus.credit.order.client.BsStaticsClient;
import com.entrobus.credit.order.client.PaymentClient;
import com.entrobus.credit.order.client.ProductionClient;
import com.entrobus.credit.order.client.UserClient;
import com.entrobus.credit.order.services.ContractService;
import com.entrobus.credit.order.services.OrderCacheService;
import com.entrobus.credit.order.services.OrderInstanceService;
import com.entrobus.credit.order.services.OrdersService;
import com.entrobus.credit.pojo.order.Orders;
import com.entrobus.credit.pojo.payment.RepaymentPlan;
import com.entrobus.credit.vo.order.ApplyVo;
import com.entrobus.credit.vo.order.PlanVo;
import com.entrobus.credit.vo.order.UserOrdersVo;
import com.entrobus.credit.vo.order.UserRepaymentPlanVo;
import com.entrobus.credit.vo.user.CacheUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RefreshScope
@RestController
@RequestMapping("/api")
public class OrderApiController {

    @Autowired
    OrdersService ordersService;

    @Autowired
    OrderCacheService cacheService;

    @Autowired
    ProductionClient productionClient;


    @Autowired
    PaymentClient paymentClient;

    @Autowired
    BsStaticsClient bsStaticsClient;

    /**
     * 用户的贷款状态
     * 未贷款-1,待审核 0,待放款 1,已驳回 2,使用中 3,使用中（已逾期）4,已完成 5
     *
     * @param token
     * @return
     */
    @GetMapping(path = "/userLoanState")
    public WebResult getUserLoanState(@RequestParam("token") String token) {
        CacheUserInfo userInfo = cacheService.getUserCacheBySid(token);
        if (userInfo == null) {
            return WebResult.fail(WebResult.CODE_NOT_LOGIN, "用户未登陆");
        }
        Orders lastOrder = ordersService.getUserLastOrder(userInfo.getId());
        Map rsMap = new HashMap<>();
        if (lastOrder == null) {
            rsMap.put("state", -1);
        } else {
            rsMap.put("state", lastOrder.getState());
        }
        return WebResult.ok(rsMap);
    }

    /**
     * 贷款申请
     *
     * @param vo
     * @return
     */
    @PostMapping(value = "/order", produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResult apply(@RequestBody ApplyVo vo) {
        CacheUserInfo userInfo = cacheService.getUserCacheBySid(vo.getToken());
        if (userInfo == null) {
            return WebResult.fail("用户未登录");
        }
        return ordersService.applyOrder(vo, userInfo);
    }

    /**
     * 借款记录列表
     *
     * @param
     * @return
     */
    @GetMapping(path = "/userOrderList")
    public WebResult getUserOrderList(@RequestParam("token") String token) throws Exception {
        CacheUserInfo userInfo = cacheService.getUserCacheBySid(token);
        if (userInfo == null) {
            return WebResult.fail(WebResult.CODE_NOT_LOGIN, "用户未登录");
        }
        List<UserOrdersVo> list = ordersService.getUserOrderList(userInfo.getId(), null, null);
        Map rsMap = new HashMap<>();
        rsMap.put("list", list);
        return WebResult.ok(rsMap);
    }

    /**
     * 还款计划
     *
     * @param
     * @return
     */
    @GetMapping(path = "/repaymentPlan")
    public WebResult getRepaymentPlan(@RequestParam("token") String token, @RequestParam("orderId") String orderId) throws Exception {
        CacheUserInfo userInfo = cacheService.getUserCacheBySid(token);
        if (userInfo == null) {
            return WebResult.fail(WebResult.CODE_NOT_LOGIN, "用户未登录");
        }
        UserRepaymentPlanVo rsVo = new UserRepaymentPlanVo();
        rsVo.setBalance(10000L);
        List<PlanVo> plist = new ArrayList<>();
        List<RepaymentPlan> list = paymentClient.getOrderRepaymentPlan(orderId);
        for (int i = 0; i < list.size(); i++) {
            RepaymentPlan plan = list.get(i);
            PlanVo vo = new PlanVo();
            vo.setState(plan.getState());
            vo.setStateName("");
            vo.setDueTime(plan.getPlanTime());
            vo.setPrincipalAndInterest(plan.getInterest() + plan.getPrincipal());
            vo.setCapital(plan.getPrincipal());
            plist.add(vo);
        }
        rsVo.setPlanList(plist);
        return WebResult.ok(rsVo);
    }

}