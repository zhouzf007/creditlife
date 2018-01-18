package com.entrobus.credit.payment.client;

import com.entrobus.credit.pojo.order.Orders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "order-service", fallback = OrderClient.OrderClientFallback.class)
public interface OrderClient {


    @PutMapping(value = "/order/{id}")
    void updateOrder(@PathVariable("id") String id, @RequestBody Orders order);

    @GetMapping(path = "/order/{id}")
    Orders getOrder(@PathVariable("id") String id);

    @Component
    class OrderClientFallback implements OrderClient {

        @Override
        public void updateOrder(String id, Orders order) {
            LOGGER.info("异常发生，进入fallback方法");
        }

        @Override
        public Orders getOrder(String id) {
            return null;
        }

        private static final Logger LOGGER = LoggerFactory.getLogger(OrderClientFallback.class);
    }
}