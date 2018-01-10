package com.entrobus.credit.manager;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.entrobus.credit.manager.channel.LogPublishChannel;
import com.entrobus.credit.manager.common.filter.ChangeParameterFilter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;

@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
@EnableCircuitBreaker
@EnableTransactionManagement//开启事务
//@EnableOAuth2Client
@MapperScan(basePackages="com.entrobus.credit.manager.dao")
@EnableBinding(LogPublishChannel.class)
public class ManagerServiceApplication {


    /**
     * 使用@Bean注入fastJsonHttpMessageConvert
     * @return
     */
    @Bean
    public HttpMessageConverters fastJsonHttpMessageConverters(){
        //1.需要定义一个Convert转换消息的对象
        FastJsonHttpMessageConverter fastConverter=new FastJsonHttpMessageConverter();
        //2.添加fastjson的配置信息，比如是否要格式化返回的json数据
        FastJsonConfig fastJsonConfig=new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(
                SerializerFeature.PrettyFormat,
                SerializerFeature.WriteNullStringAsEmpty,//字符类型字段如果为null,输出为"",而非null
                SerializerFeature.WriteNullListAsEmpty,//List字段如果为null,输出为[],而非null
                SerializerFeature.DisableCircularReferenceDetect //避免循环引用
        );
        fastJsonConfig.setDateFormat("yyyy-MM-dd HH:mm:ss");
        //3.在convert中添加配置信息
        fastConverter.setFastJsonConfig(fastJsonConfig);
        HttpMessageConverter<?> converter=fastConverter;
        return new HttpMessageConverters(converter);
    }

    /**
     * 创建一个bean
     * @return
     */
    @Bean
    public Filter changeParameterFilter() {
        return new ChangeParameterFilter();
    }

    /**
     * 配置过滤器
     * @return
     */
    @Bean
    public FilterRegistrationBean someFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();

        registration.setFilter(changeParameterFilter());
        registration.addUrlPatterns("/*");
        //registration.addInitParameter("ignores","/content,/css,/plugins,/js,/captcha.jpg");
        registration.setDispatcherTypes(DispatcherType.REQUEST,DispatcherType.FORWARD);
        registration.setName("changeParameterFilter");
        registration.setOrder(Integer.MAX_VALUE);//spring boot 会按照order值的大小，从小到大的顺序来依次过滤。
        //再有一个过滤器的话，可以设置成 registration.setOrder(Integer.MAX_VALUE - 1)
        return registration;
    }

    public static void main(String[] args) {
        SpringApplication.run(ManagerServiceApplication.class, args);
    }
}