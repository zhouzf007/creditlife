package com.entrobus.credit.manager.interceptor;

import com.alibaba.fastjson.JSON;
import com.entrobus.credit.cache.CacheService;
import com.entrobus.credit.common.bean.WebResult;
import com.entrobus.credit.manager.common.bean.SysLoginUserInfo;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

/**
 * 登录拦截器
 */
@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {

    private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {


        logger.debug(">>>LoginInterceptor>>>>>>>登录拦截器");
        //从请求头中获取登录token
        String token = request.getHeader("token");
        if(StringUtils.isEmpty(token)){
            token = request.getParameter("token");
        }

        //从缓存中获取当前登录的系统用户
        SysLoginUserInfo loginUser = CacheService.getCacheObj(redisTemplate,token,SysLoginUserInfo.class);
        if(loginUser == null){//用户未登录
            //1.判断是否为ajax请求
            //Boolean ajaxRequest = request.getHeader("ajaxRequest");
            if (request.getHeader("x-requested-with") != null
                    && request.getHeader("x-requested-with")
                    .equalsIgnoreCase("XMLHttpRequest"))//如果是ajax请求响应头会有，x-requested-with；
            {
                logger.debug("----本次请求为AJAX请求-----");
                response.setHeader("SessionStatus", "timeout");//在响应头设置session状态
            }else{
                logger.debug("----本次请求为普通请求-----");
                write(JSON.toJSONString(WebResult.error("用户未登录")),response);
            }
            return false;
        }

        /**
         * 获取本次请求的参数
         */
        Map<String,String[]> parameterMap = new HashMap<>(request.getParameterMap());
        //添加token参数到parameterMap
        parameterMap.put("token",new String[]{token});
        request = new ParameterRequestWrapper(request, parameterMap);
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

    class ParameterRequestWrapper extends HttpServletRequestWrapper {

        private Map<String, String[]> params;

        public ParameterRequestWrapper(HttpServletRequest request, Map<String, String[]> newParams) {
            super(request);
            this.params = newParams;
            // RequestDispatcher.forward parameter
            renewParameterMap(request);
        }

        @Override
        public String getParameter(String name) {
            String result = "";

            Object v = params.get(name);
            if (v == null) {
                result = null;
            } else if (v instanceof String[]) {
                String[] strArr = (String[]) v;
                if (strArr.length > 0) {
                    result =  strArr[0];
                } else {
                    result = null;
                }
            } else if (v instanceof String) {
                result = (String) v;
            } else {
                result =  v.toString();
            }

            return result;
        }

        @Override
        public Map getParameterMap() {
            return params;
        }

        @Override
        public Enumeration getParameterNames() {
            return new Vector(params.keySet()).elements();
        }

        @Override
        public String[] getParameterValues(String name) {
            String[] result = null;

            Object v = params.get(name);
            if (v == null) {
                result =  null;
            } else if (v instanceof String[]) {
                result =  (String[]) v;
            } else if (v instanceof String) {
                result =  new String[] { (String) v };
            } else {
                result =  new String[] { v.toString() };
            }

            return result;
        }

        private void renewParameterMap(HttpServletRequest req) {

            String queryString = req.getQueryString();

            if (queryString != null && queryString.trim().length() > 0) {
                String[] params = queryString.split("&");

                for (int i = 0; i < params.length; i++) {
                    int splitIndex = params[i].indexOf("=");
                    if (splitIndex == -1) {
                        continue;
                    }

                    String key = params[i].substring(0, splitIndex);

                    if (!this.params.containsKey(key)) {
                        if (splitIndex < params[i].length()) {
                            String value = params[i].substring(splitIndex + 1);
                            this.params.put(key, new String[] { value });
                        }
                    }
                }
            }
        }

    }
}
