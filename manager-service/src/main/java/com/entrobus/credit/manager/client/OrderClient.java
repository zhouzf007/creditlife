package com.entrobus.credit.manager.client;

import com.entrobus.credit.pojo.order.Orders;
import com.entrobus.credit.vo.order.OrderListVo;
import com.entrobus.credit.vo.order.OrderQueryVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "order-service", fallback = OrderClient.OrderClientFallback.class)
public interface OrderClient {


    @PutMapping(value = "/order/{id}")
    void updateOrder(@PathVariable("id")String id,@RequestBody Orders order);

    @GetMapping(path = "/order/{id}")
    Orders getOrder(@PathVariable("id") String id);

    @GetMapping(path = "/orderList")
    List<OrderListVo> getOrderList(@RequestBody OrderQueryVo vo);

    @GetMapping(path = "/userOrderList")
    List<OrderListVo> getUserOrderList(@RequestBody OrderQueryVo vo);

    @Component
    class OrderClientFallback implements OrderClient {

        @Override
        public void updateOrder(String id, Orders order) {
            LOGGER.info("异常发生，进入fallback方法");
        }

        @Override
        public Orders getOrder(String id) {
            LOGGER.info("异常发生，进入fallback方法");
            return null;
        }

        @Override
        public List<OrderListVo> getOrderList(OrderQueryVo vo) {
            LOGGER.info("异常发生，进入fallback方法");
            return null;
        }

        @Override
        public List<OrderListVo> getUserOrderList(OrderQueryVo vo) {
            LOGGER.info("异常发生，进入fallback方法");
            return null;
        }


        private static final Logger LOGGER = LoggerFactory.getLogger(OrderClientFallback.class);
    }
}