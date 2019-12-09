package com.bluesky.zhz.web.config;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
 
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Target(METHOD)
public @interface AccessLimit {
 
    int seconds() default 60;
    int maxCount() default 1;
}