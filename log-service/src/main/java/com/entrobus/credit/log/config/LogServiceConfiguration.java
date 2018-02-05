package com.entrobus.credit.log.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true, jsr250Enabled = true)
public class LogServiceConfiguration extends ResourceServerConfigurerAdapter {

//
//    @Bean
//    public OAuth2RestTemplate clientCredentialsRestTemplate( ClientCredentialsResourceDetails resource) {
//        return new OAuth2RestTemplate(resource);
//    }
//    //请求中加入oauth信息
//    @Bean
//    public RequestInterceptor oauth2FeignRequestInterceptor(OAuth2ClientContext oauth2ClientContext,
//                                                            ClientCredentialsResourceDetails resource) {
//        return new OAuth2FeignRequestInterceptor(oauth2ClientContext, resource);
//    }

//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .anyRequest().authenticated();
//    }
    @Override
    public void configure(HttpSecurity http) throws Exception {
//    http.authorizeRequests()
//            .anyRequest().permitAll()
//        ;
        http.authorizeRequests()
            .antMatchers("/client").denyAll()
        ;
    }

}