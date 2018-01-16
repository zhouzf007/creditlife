package com.entrobus.credit.common.annotation;

import org.springframework.core.annotation.Order;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *  用于标记该方法需要写入日志。需要在各项目中实现注解解析（aop)
 * @author laotou
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Order(1)
public @interface RecordLog {
//    String value() default "";
    String desc() default "";
    String remark() default "";
}
