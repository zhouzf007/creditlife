package com.entrobus.credit.user.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true, jsr250Enabled = true)
public class ServiceAConfiguration extends ResourceServerConfigurerAdapter {
//    public RequestInterceptor oauth2FeignRequestInterceptor(OAuth2ClientContext oauth2ClientContext,
//                                                            ClientCredentialsResourceDetails resource) {
//        return new OAuth2FeignRequestInterceptor(oauth2ClientContext, resource);
//    }
//
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .anyRequest().authenticated();
//    }
}