/**
 * Copyright (c) 2005-2012 https://github.com/zhangkaitao
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.zmc.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class SpringUtils implements ServletContextListener {

    private static WebApplicationContext springContext;

    public void contextInitialized(ServletContextEvent event) {
        springContext = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
    }

    public void contextDestroyed(ServletContextEvent event) {

    }

    public static ApplicationContext getApplicationContext() {
        return springContext;
    }

    public SpringUtils() {
    }


    public static <T> T getBean(Class<T> requiredType){

        if(springContext == null){

            throw new RuntimeException("springContext is null.");
        }
        return springContext.getBean(requiredType);
    }

}
