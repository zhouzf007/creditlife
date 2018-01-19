package com.entrobus.credit.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.http.ServletInputStreamWrapper;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Component
public class AccessFilter extends ZuulFilter{

    private static final Logger logger = LoggerFactory.getLogger(AccessFilter.class);

    /**
     * pre : 请求被路由前调用
     * routing : 路由时调用
     * post : routing 和 error之后调用
     * error : 处理请求时发生错误时调用
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 判断是否执行过滤器
     * @return
     */
    @Override
    public boolean shouldFilter() {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        String requestURI = request.getRequestURI();
        return true;
    }

    /**
     * 过滤逻辑
     *
     * @return Some arbitrary artifact may be returned. Current implementation ignores it.
     * 返回值暂时没有用
     */
    @Override
    public Object run() {
        //获取当前请求上下文
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        //从请求头中获取登录token
        String token = request.getHeader("token");

//        if (StringUtils.isEmpty(token)){
//            context.setSendZuulResponse(false);//结束请求
//            context.setResponseStatusCode(401);//返回状态码401
//            return null;
//        }

        //将token作为请求参数放入到HttpServletRequest对象中，方便其他地方通过request.getParameter("token")去获取
        HttpServletResponse response = context.getResponse();
        return null;
    }
}
