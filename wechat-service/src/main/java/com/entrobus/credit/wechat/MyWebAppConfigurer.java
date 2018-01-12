package com.entrobus.credit.wechat;

import com.entrobus.credit.wechat.interceptor.WechatInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 处理跨域调用问题
 * Created by cwh on 2018/1/2.
 */
@Configuration
public class MyWebAppConfigurer extends WebMvcConfigurerAdapter {

    /**
     * 微信请求拦截器
     */
    @Autowired
    private WechatInterceptor wechatInterceptor;

    /**
     * 配置拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 多个拦截器组成一个拦截器链
        // addPathPatterns 用于添加拦截规则
        // excludePathPatterns 用户排除拦截
        registry.addInterceptor(wechatInterceptor)
                .excludePathPatterns(
                        "/wechat/portal",
                        "/wechat/oauth2/login",
                        "/wechat/menu/create",
                        "/wechat/menu/delete",
                        "/MP_verify_huRPGP9UaLkNzIq5.txt"
                )
                .addPathPatterns("/**");
        super.addInterceptors(registry);
    }
}