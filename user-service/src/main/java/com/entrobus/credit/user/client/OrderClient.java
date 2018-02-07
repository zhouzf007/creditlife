package com.entrobus.credit.user.client;

import com.entrobus.credit.pojo.order.Orders;
import com.entrobus.credit.vo.order.OrderUpdateVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "order-service", fallback = OrderClient.OrderClientFallback.class)
public interface OrderClient {


    @GetMapping(path = "/api/userOrderState")
    Orders userOrderState(@RequestParam("id") String id);

    @Component
    class OrderClientFallback implements OrderClient {

        private static final Logger LOGGER = LoggerFactory.getLogger(OrderClientFallback.class);

        @Override
        public Orders userOrderState(String id) {
            return null;
        }
    }
}