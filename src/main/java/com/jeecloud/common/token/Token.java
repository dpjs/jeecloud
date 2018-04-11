package com.jeecloud.common.token;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @ClassName: Token 
 * @Description: token注解
 * @author: admin
 * @date: 2018年3月15日 上午11:27:07
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface Token {
    //生成 Token 标志
    boolean save() default false ;
    //移除 Token 值
    boolean remove() default false ;
}
