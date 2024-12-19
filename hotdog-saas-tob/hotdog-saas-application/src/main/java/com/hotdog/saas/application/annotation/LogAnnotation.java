package com.hotdog.saas.application.annotation;

import java.lang.annotation.*;

/**
 * 操作日志注解
 * 主要是查询使用，新增、修改、删除走canal
 *
 * @author hotdog
 * @date 2024-12-19 22:34:11
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogAnnotation {

    /**z
     * 菜单名
     */
    String menuName() default "";

    /**
     * 操作
     */
    String operation() default "";

    /**
     * 终端名
     */
    String itemName() default "";

}
