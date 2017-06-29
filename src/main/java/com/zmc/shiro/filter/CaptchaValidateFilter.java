package com.zmc.shiro.filter;

import com.google.code.kaptcha.servlet.KaptchaExtend;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by zhongmc on 2017/6/22.
 */
public class CaptchaValidateFilter extends AccessControlFilter {
    private boolean captchaEnbaled = true;
    private String captchaParam = "captchaCode";
    private String failureKeyAttribute = "shiroLoginFailure";

    public boolean isCaptchaEnbaled() {
        return captchaEnbaled;
    }

    public void setCaptchaEnbaled(boolean captchaEnbaled) {
        this.captchaEnbaled = captchaEnbaled;
    }

    public String getCaptchaParam() {
        return captchaParam;
    }

    public void setCaptchaParam(String captchaParam) {
        this.captchaParam = captchaParam;
    }

    public String getFailureKeyAttribute() {
        return failureKeyAttribute;
    }

    public void setFailureKeyAttribute(String failureKeyAttribute) {
        this.failureKeyAttribute = failureKeyAttribute;
    }

    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        HttpServletRequest httpServletRequest = WebUtils.toHttp(servletRequest);
        httpServletRequest.setAttribute("jcaptchaEbabled",captchaEnbaled);
        if (captchaEnbaled==false || !"post".equalsIgnoreCase(httpServletRequest.getMethod())){
            //
            return true;

        }
        //验证成功了
        //获取系统生成的验证码
        //String sysCaptchaCode = (String)httpServletRequest.getSession().getAttribute("KAPTCHA_SESSION_KEY");
        KaptchaExtend kaptchaExtend = new KaptchaExtend();
        String sysCaptchaCode = kaptchaExtend.getGeneratedKey(httpServletRequest);
        if (StringUtils.isEmpty(sysCaptchaCode)){
            //验证码过期
            return false;
        }
        //获取用户提交的验证码
        String submitCaptchaCode = httpServletRequest.getParameter(captchaParam);

        return sysCaptchaCode.equalsIgnoreCase(submitCaptchaCode);
    }

    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        servletRequest.setAttribute(failureKeyAttribute,"Captcha.error");
        return true;
    }
}
