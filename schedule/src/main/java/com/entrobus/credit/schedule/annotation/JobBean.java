package com.entrobus.credit.schedule.annotation;

import org.springframework.core.annotation.Order;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @author laotou
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Order(1)
public @interface JobBean {
//    @AliasFor(annotation = JobDetail.class,attribute = "jobName")
//    String value() default "";
//    @AliasFor(annotation = JobDetail.class,attribute = "value")
    //任务名称
    String jobName() default "";
    //分组名称
    String groupName() default "creditlife_default";
    //cron 时间
    String cron() default "" ;
}
