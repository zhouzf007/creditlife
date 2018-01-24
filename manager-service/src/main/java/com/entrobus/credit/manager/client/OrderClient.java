package com.entrobus.credit.manager.client;

import com.entrobus.credit.common.bean.WebResult;
import com.entrobus.credit.pojo.order.Orders;
import com.entrobus.credit.vo.order.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
import java.util.List;

@FeignClient(name = "order-service", fallback = OrderClient.OrderClientFallback.class)
public interface OrderClient {

    @PutMapping(value = "/order", consumes = MediaType.APPLICATION_JSON_VALUE)
    WebResult updateOrder(@RequestBody OrderExt order);

    @GetMapping(path = "/order/{id}")
    Orders getOrder(@PathVariable("id") String id);

    /**
     * 订单列表
     *
     * @return
     */
    @GetMapping(path = "/orderList")
    WebResult getOrderList(@RequestParam("states") String states, @RequestParam("orgId") String orgId, @RequestParam("offset") Integer offset, @RequestParam("limit") Integer limit);

    /**
     * 订单详情
     *
     * @return
     */
    @GetMapping(path = "/orderDtl")
    OrderDtlVo getOrderDtl(@RequestParam("id") String id);

    /**
     * 用户列表
     */
    @GetMapping(path = "/userOrderList")
    WebResult getUserOrderList(@RequestParam("state") Integer state, @RequestParam("orgId") String orgId, @RequestParam("offset") Integer offset, @RequestParam("limit") Integer limit);

    /**
     * 用户订单详情
     */
    @GetMapping(path = "/userOrderDtl")
    UserOrderDtlVo getUserOrderDtl(@RequestParam("userId") String userId);

    @Component
    class OrderClientFallback implements OrderClient {

        private static final Logger LOGGER = LoggerFactory.getLogger(OrderClientFallback.class);

        @Override
        public WebResult updateOrder(OrderExt order) {
            LOGGER.info("异常发生，进入fallback方法");
            return null;
        }

        @Override
        public Orders getOrder(String id) {
            LOGGER.info("异常发生，进入fallback方法");
            return null;
        }

        @Override
        public WebResult getOrderList(String states, String orgId, Integer offset, Integer limit) {
            return null;
        }

        @Override
        public OrderDtlVo getOrderDtl(String id) {
            return null;
        }

        @Override
        public WebResult getUserOrderList(Integer state, String orgId, Integer offset, Integer limit) {
            LOGGER.info("异常发生，进入fallback方法");
            return null;
        }

        @Override
        public UserOrderDtlVo getUserOrderDtl(String userId) {
            return null;
        }
    }
}