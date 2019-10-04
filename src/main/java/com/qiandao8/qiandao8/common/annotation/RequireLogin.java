package com.qiandao8.qiandao8.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Bert Q
 * description //需要登录才能使用的类/方法上面打上这个注解
 * @date 13:36 2019/10/4
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RequireLogin {

}
