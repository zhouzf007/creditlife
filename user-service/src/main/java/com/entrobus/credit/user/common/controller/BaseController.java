package com.entrobus.credit.user.common.controller;

import com.entrobus.credit.cache.CacheService;
import com.entrobus.credit.user.bean.LoginUserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import utils.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by mozl on 2018年1月11日.
 */
public class BaseController {

    //日志打印
    protected final Logger logger = LoggerFactory.getLogger(getClass());


    @Autowired
    protected RedisTemplate redisTemplate;

    /**
     * 获取当前请求对象
     *
     * @return
     */
    protected HttpServletRequest getRequest() {
        try {
            return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        } catch (Exception e) {
            throw new RuntimeException("获取request失败", e);
        }
    }

    protected HttpSession getSession(HttpServletRequest request) {
        return request.getSession();
    }

    /**
     * 获取当前登录的系统用户
     * @return
     */
    protected LoginUserInfo getCurrLoginUser() {
        String token = getRequest().getParameter("token");
        LoginUserInfo loginUser = CacheService.getCacheObj(redisTemplate, Constants.USER_LOGIN_REDIS.TOKEN+token,LoginUserInfo.class);
        return loginUser;
    }

    /**
     * 登录用户的ID
     * @return
     */
    protected String getLoginUserId() {
        return getCurrLoginUser().getId();
    }

}
