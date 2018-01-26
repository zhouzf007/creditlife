package com.entrobus.credit.manager.client;

import com.entrobus.credit.common.bean.WebResult;
import com.entrobus.credit.pojo.payment.RepaymentPlan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by zhouzf on 2017/12/28.
 */
@FeignClient(name = "payment-service", fallback = PaymentClient.PaymentClientFallback.class)
public interface PaymentClient {

    @GetMapping(value = "/api/orderRepaymentState", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    int getOrderRepaymentState(@RequestParam("orderId") String orderId);

    @GetMapping(value = "/api/orderRepaymentPlan", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    List<RepaymentPlan> getOrderRepaymentPlan(@RequestParam("orderId") String orderId);

    /**
     * 订单的当前还款计划
     *
     * @param orderId
     * @return
     */
    @GetMapping("/api/presentRepaymentPlan")
    RepaymentPlan getPresentRepaymentPlan(@RequestParam("orderId") String orderId);

    @PutMapping("/api/repaymentPlan")
    WebResult updateRepaymentPlan(@RequestParam("id") String id, @RequestParam("state") Integer state);

    @Component
    class PaymentClientFallback implements PaymentClient {

        @Override
        public int getOrderRepaymentState(String orderId) {
            LOGGER.info("异常发生，进入fallback方法");
            return -1;
        }

        @Override
        public List<RepaymentPlan> getOrderRepaymentPlan(String orderId) {
            LOGGER.info("异常发生，进入fallback方法");
            return null;
        }

        @Override
        public RepaymentPlan getPresentRepaymentPlan(String orderId) {
            return null;
        }

        @Override
        public WebResult updateRepaymentPlan(String id, Integer state) {
            return null;
        }

        private static final Logger LOGGER = LoggerFactory.getLogger(PaymentClientFallback.class);
    }
}
