package com.entrobus.credit.manager.interceptor;

import com.alibaba.fastjson.JSON;
import com.entrobus.credit.cache.CacheService;
import com.entrobus.credit.common.bean.WebResult;
import com.entrobus.credit.manager.common.bean.SysLoginUserInfo;
import com.entrobus.credit.manager.common.service.ManagerCacheService;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

/**
 * 登录拦截器
 */
@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {

    private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

    @Autowired
    private ManagerCacheService managerCacheService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        logger.debug(">>>LoginInterceptor>>>>>>>登录拦截器");
        //从缓存中获取当前登录的系统用户
        SysLoginUserInfo loginUser = managerCacheService.getCurrLoginUser();
        if(loginUser == null){//用户未登录
            //1.判断是否为ajax请求
            String ajaxRequest = request.getHeader("ajaxRequest");//自定义AJAX请求头，用于标识是否为ajax请求
            if (StringUtils.isNotEmpty(ajaxRequest) && Boolean.valueOf(ajaxRequest)) {
                logger.debug("----本次请求为AJAX请求-----");
                response.setHeader("loginStatus", "timeout");//在响应头设置登录状态
            }else{
                logger.debug("----本次请求为普通请求-----");
                write(JSON.toJSONString(WebResult.error("用户未登录")),response);
            }
            return false;
        }

        return super.preHandle(request, response, handler);
    }

    /**
     * 写出数据
     *
     * @param res 输出的字符串
     * @throws Exception
     */
    private void write(String res, HttpServletResponse response) {
        Writer writer = null;
        try {
            res = (null == res ? "" : res);
            response.setCharacterEncoding("UTF-8");
            //response.setHeader("Content-type", "text/html;charset=UTF-8");
            response.setHeader("Content-type", "application/json;charset=UTF-8");
            writer = response.getWriter();
            logger.debug("输出JSON字符串：" + res);
            writer.write(res);
        } catch (IOException e) {
            logger.error("输出JSON字符串异常", e);
        } finally {
            IOUtils.closeQuietly(writer);
        }
    }


}
