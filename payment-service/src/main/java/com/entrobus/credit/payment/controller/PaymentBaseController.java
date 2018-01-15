package com.entrobus.credit.payment.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Controller父类
 */
public class PaymentBaseController {

    /**
     * 日志对象
     */
    protected Logger logger = LoggerFactory.getLogger(getClass());


    @Autowired
    protected RedisTemplate redisTemplate;

    /**
     * 客户端重定向
     *
     * @param url
     * @return
     */
    protected String redirect(String url) {
        return "redirect:" + url;
    }

    /**
     * 服务端重定向
     *
     * @param url
     * @return
     */
    protected String forward(String url) {
        return "forward:" + url;
    }


    /**
     * 获取当前请求对象
     *
     * @return
     */
    protected HttpServletRequest getRequest() {
        try {
            return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                    .getRequest();
        } catch (Exception e) {
            throw new RuntimeException("获取request失败", e);
        }
    }
}
