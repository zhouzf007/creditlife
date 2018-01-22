package com.entrobus.credit.order.controller;

import com.entrobus.credit.cache.Cachekey;
import com.entrobus.credit.common.Constants;
import com.entrobus.credit.common.bean.WebResult;
import com.entrobus.credit.common.util.AmountUtil;
import com.entrobus.credit.common.util.GUIDUtil;
import com.entrobus.credit.order.channel.GenSubOrderPublishChannel;
import com.entrobus.credit.order.client.PaymentClient;
import com.entrobus.credit.order.client.ProductionClient;
import com.entrobus.credit.order.client.UserClient;
import com.entrobus.credit.order.services.*;
import com.entrobus.credit.pojo.order.Contract;
import com.entrobus.credit.pojo.order.CreditReport;
import com.entrobus.credit.pojo.order.Orders;
import com.entrobus.credit.pojo.order.OrdersExample;
import com.entrobus.credit.pojo.payment.RepaymentPlan;
import com.entrobus.credit.pojo.user.UserInfo;
import com.entrobus.credit.vo.loan.LoanProductVo;
import com.entrobus.credit.vo.log.OrderOperationMsg;
import com.entrobus.credit.vo.order.*;
import com.entrobus.credit.vo.user.CacheUserInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.MediaType;
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
            return WebResult.error("用户未登陆");
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
            return WebResult.error("用户未登录");
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
    public WebResult getUserOrderList(@RequestParam("token") String token, @RequestParam("offset") Integer offset, @RequestParam("limit") Integer limit) throws Exception {
        CacheUserInfo userInfo = cacheService.getUserCacheBySid(token);
        if (userInfo == null) {
            return WebResult.error("用户未登录");
        }
        List<UserOrdersVo> list=ordersService.getUserOrderList(userInfo.getId(), offset, limit);
        Map rsMap = new HashMap<>();
        rsMap.put("list",list);
        return WebResult.ok(rsMap);
    }

}