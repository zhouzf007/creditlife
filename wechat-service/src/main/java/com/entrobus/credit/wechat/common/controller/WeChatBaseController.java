package com.entrobus.credit.wechat.common.controller;

import com.entrobus.credit.wechat.common.util.WebUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by gacl on 2017/3/3.
 */
public class WeChatBaseController{

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

    protected Map<String, String> getParams(HttpServletRequest request) {
        return WebUtil.getRequestParams(request);
    }

    protected Map<String, String> getNotBlankParams(HttpServletRequest request) {
        return WebUtil.getNotBlankRequestParams(request);
    }

    protected HttpSession getSession() {
        return getSession(getRequest());
    }

    protected HttpSession getSession(HttpServletRequest request) {
        return request.getSession();
    }


}
