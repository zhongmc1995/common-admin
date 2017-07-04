package com.zmc.web.bind.annotation;

import com.zmc.web.bind.handler.LogType;

import java.lang.annotation.*;

/**
 * Created by zhongmc on 2017/7/4.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Log {
    LogType type() default LogType.SPACE;
    String operation() default  "";
}
