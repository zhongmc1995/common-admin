package com.zmc.shiro.filter;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Created by zhongmc on 2017/6/22.
 */
public class MyFormAuthenticationFilter extends FormAuthenticationFilter {

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        if(request.getAttribute(getFailureKeyAttribute()) != null) {
            //如果验证码错误，直接放行，跳转到/  发现没登陆又回到login
            return true;
        }
        return super.onAccessDenied(request, response, mappedValue);
    }
}
