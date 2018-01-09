package com.entrobus.credit.manager;

import com.entrobus.credit.manager.common.bean.CommonParameter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

/**
 * 公共请求参数解析器
 */
public class CommonArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.getParameterType().equals(CommonParameter.class);
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        HttpServletRequest request = (HttpServletRequest) nativeWebRequest.getNativeRequest();
        //从请求头中获取登录token
        String token = request.getHeader("token");
        CommonParameter commonParameter = new CommonParameter();
        commonParameter.setToken(token);
        String platform = request.getParameter("platform");
        if(StringUtils.isEmpty(platform)){
            platform = request.getHeader("platform");
            if(StringUtils.isNotEmpty(platform)){
                commonParameter.setPlatform(Integer.parseInt(platform));
            }
        }else{
            commonParameter.setPlatform(Integer.parseInt(platform));
        }
        return commonParameter;
    }
}
