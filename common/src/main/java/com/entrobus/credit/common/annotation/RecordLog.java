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

    /**
     * 操作名称、描述
     * @return
     */
    String desc() default "";

    /**
     * 备注
     * @return
     */
    String remark() default "";

    /**
     * 被操作数据的主键名称，
     * 即请求参数中主键的参数名
     * 通过aop根据relId取得实际的主键值
     *
     * 暂时只能拿到@PathVariable和@RequestParam注解设置的参数名
     * @return
     */
    String relId() default "id";
}
