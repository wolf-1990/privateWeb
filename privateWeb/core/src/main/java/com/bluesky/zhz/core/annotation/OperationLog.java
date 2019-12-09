package com.bluesky.zhz.core.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.PARAMETER, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OperationLog {

	String function() default "";// 功能

	int businessType() default 1;//1登录/登出 2业务

	int operationType() default 1;//操作类型 1-登录 2-登出 3-新增 4-修改 5-删除

	int platForm() default 1;//1-云校管理端  2-代理商端

}