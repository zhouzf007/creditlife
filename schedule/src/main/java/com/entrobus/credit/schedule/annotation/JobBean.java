package com.entrobus.credit.schedule.annotation;

import org.springframework.core.annotation.Order;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Order(1)
public @interface JobBean {
//    @AliasFor(annotation = JobDetail.class,attribute = "jobName")
//    String value() default "";
//    @AliasFor(annotation = JobDetail.class,attribute = "value")
    String jobName() default "";
    String groupName() default "creditlife_default";
    String cron() default "" ;
}
