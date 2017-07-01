package com.zmc.web.bind.handler;

import com.zmc.web.bind.annotation.CurrentUser;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * 用于绑定@FormModel的方法参数解析器
 * Created by zhongmc on 2017/6/29.
 */
public class CurrentUserMethodArgumentResolver implements HandlerMethodArgumentResolver {

    public CurrentUserMethodArgumentResolver() {
    }


    public boolean supportsParameter(MethodParameter parameter) {
        if (parameter.hasParameterAnnotation(CurrentUser.class)) {
            return true;
        }
        return false;
    }


    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        CurrentUser currentUserAnnotation = parameter.getParameterAnnotation(CurrentUser.class);
        return webRequest.getAttribute(currentUserAnnotation.value(), NativeWebRequest.SCOPE_REQUEST);
    }
}
