package com.entrobus.credit.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
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
        logger.info("getRouteHost:"+request.getContextPath());
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
        ParameterRequestWrapper requestWrapper = new ParameterRequestWrapper(request);
        //从请求头中获取登录token
        String token = request.getHeader("token");

//        if (StringUtils.isEmpty(token)){
//            context.setSendZuulResponse(false);//结束请求
//            context.setResponseStatusCode(401);//返回状态码401
//            return null;
//        }

        //将token作为请求参数放入到HttpServletRequest对象中，方便其他地方通过request.getParameter("token")去获取
        requestWrapper.addParameter("token",token);
        String platform = request.getParameter("platform");
        if(StringUtils.isEmpty(platform)){
            platform = request.getHeader("platform");
            requestWrapper.addParameter("platform",platform);
        }

        HttpServletResponse response = context.getResponse();
        return null;
    }
    /**
     * HttpServletRequest请求参数包装类，用于添加额外请求参数
     */
    public class ParameterRequestWrapper extends HttpServletRequestWrapper {

        private Map<String , String[]> params = new HashMap<String, String[]>();


        @SuppressWarnings("unchecked")
        public ParameterRequestWrapper(HttpServletRequest request) {
            // 将request交给父类，以便于调用对应方法的时候，将其输出，其实父亲类的实现方式和第一种new的方式类似
            super(request);
            //将参数表，赋予给当前的Map以便于持有request中的参数
            this.params.putAll(request.getParameterMap());
        }
        //重载一个构造方法
        public ParameterRequestWrapper(HttpServletRequest request , Map<String , Object> extendParams) {
            this(request);
            addAllParameters(extendParams);//这里将扩展参数写入参数表
        }
        @Override
        public String getParameter(String name) {//重写getParameter，代表参数从当前类中的map获取
            String[]values = params.get(name);
            if(values == null || values.length == 0) {
                return null;
            }
            return values[0];
        }

        @Override
        public Map<String, String[]> getParameterMap() {
            return params;
        }


        public String[] getParameterValues(String name) {//同上
            return params.get(name);
        }
        public void addAllParameters(Map<String , Object>otherParams) {//增加多个参数
            for(Map.Entry<String , Object>entry : otherParams.entrySet()) {
                addParameter(entry.getKey() , entry.getValue());
            }
        }
        public void addParameter(String name , Object value) {//增加参数
            if(value != null) {
                if(value instanceof String[]) {
                    params.put(name , (String[])value);
                }else if(value instanceof String) {
                    params.put(name , new String[] {(String)value});
                }else {
                    params.put(name , new String[] {String.valueOf(value)});
                }
            }
        }
    }
}
