package com.entrobus.credit.contract.config;

import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.ResourceHttpMessageConverter;

import java.nio.charset.Charset;

//@Configuration
public class FeignMultipartSupportConfig {
//    @Bean
//    @Primary
//    @Scope("prototype")
    public Encoder multipartFormEncoder() {
        return new SpringFormEncoder();
    }
//    @Bean
    public ResourceHttpMessageConverter resourceHttpMessageConverter(){
        return new ResourceHttpMessageConverter();
    }
//    @Bean
    public FormHttpMessageConverter formHttpMessageConverter(MultipartFileHttpMessageConverter multipartFileHttpMessageConverter){
        FormHttpMessageConverter formHttpMessageConverter = new FormHttpMessageConverter();
        formHttpMessageConverter.addPartConverter(multipartFileHttpMessageConverter);
        return formHttpMessageConverter;
    }

//    @Bean
    public MultipartFileHttpMessageConverter multipartFileHttpMessageConverter(){
        return new MultipartFileHttpMessageConverter();
    }
}
