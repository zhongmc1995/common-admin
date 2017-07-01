package com.zmc.web.bind.annotation;


import com.zmc.constant.Constant;

import java.lang.annotation.*;
/**
 * <p>绑定当前登录的用户</p>
 * <p>不同于@ModelAttribute</p>
 *
 * Created by zhongmc on 2017/6/29.
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CurrentUser {

    /**
     * 当前Request attribute中的key
     * @return
     */
    String value() default Constant.CURRENT_USER;

}
