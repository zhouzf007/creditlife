package com.entrobus.credit.payment.client;

import com.entrobus.credit.pojo.order.Orders;
import com.entrobus.credit.vo.order.OrderUpdateVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "order-service", fallback = OrderClient.OrderClientFallback.class)
public interface OrderClient {


    @PutMapping(value = "/order")
    void updateOrder(@RequestParam("id") String id, @RequestBody OrderUpdateVo order);

    @GetMapping(path = "/order")
    Orders getOrder(@RequestParam("id") String id);

    @Component
    class OrderClientFallback implements OrderClient {

        @Override
        public void updateOrder(String id, OrderUpdateVo order) {
            LOGGER.info("异常发生，进入fallback方法");
        }

        @Override
        public Orders getOrder(String id) {
            return null;
        }

        private static final Logger LOGGER = LoggerFactory.getLogger(OrderClientFallback.class);
    }
}