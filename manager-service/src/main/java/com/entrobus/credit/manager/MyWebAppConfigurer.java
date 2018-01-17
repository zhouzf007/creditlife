package com.entrobus.credit.manager;

import com.entrobus.credit.manager.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * 处理跨域调用问题
 * Created by cwh on 2018/1/2.
 */
@Configuration
public class MyWebAppConfigurer extends WebMvcConfigurerAdapter {

    /**
     * 登录拦截器
     */
    @Autowired
    private LoginInterceptor loginInterceptor;

    /**
     * 配置拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 多个拦截器组成一个拦截器链
        // addPathPatterns 用于添加拦截规则
        // excludePathPatterns 用户排除拦截
        registry.addInterceptor(loginInterceptor)
                .excludePathPatterns(
                        "/sys/user/login",
                        "/sys/user/logout",
                        "/sys/tool/createSuperAdmin",
                        "/loan/rate/**"
                )
                .addPathPatterns("/**");
        super.addInterceptors(registry);
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        // 注册公共参数分解器
        argumentResolvers.add(new CommonArgumentResolver());
    }

    /**
     * 配置跨域处理
     * @param registry
     */
   /* @Override
    public void addCorsMappings(CorsRegistry registry) {
       // registry.addMapping("/**");
        registry.addMapping("/**").allowedOrigins("http://localhost:63343").allowedHeaders("*")
                .allowedMethods("*")
                .allowCredentials(true);
    }*/
}