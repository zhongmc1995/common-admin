package com.zmc.web.bind.handler;

import com.zmc.common.entity.LogRecord;
import com.zmc.service.LogRecordService;
import com.zmc.web.bind.annotation.Log;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;
/**
 * Created by zhongmc on 2017/7/4.
 * 日志切面
 */
@Aspect
public class LogAspect {
    @Autowired
    private LogRecordService logRecordService;
    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    /*@Pointcut("execution (* com.zmc.web.controller..*.*(..))")*/
    @Pointcut("@annotation(com.zmc.web.bind.annotation.Log)")

    /*@Pointcut("execution(* com.zmc.service.Impl..*.*(..))")
    * 拦截service的时候，aop的配置必须在applicationContext-service 中，
    * 因为mvc的配置文件先于service配置文件，就是说controller bean是先加载的
    * 如果配置在mvc的配置文件中，这个时候还没有加载service的bean，此时配置aop
    * 是无法对service进行代理的，最后的结果就是没有效果，无法拦截
    * 参考博客：
    * */
    public void logAspect() {
    }


    //配置后置返回通知,使用在方法aspect()上注册的切入点
    @AfterReturning("logAspect()")
    public void afterReturn(JoinPoint joinPoint) throws Exception {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String targetName = joinPoint.getTarget().getClass().getName();
        Method method = signature.getMethod();
        String methodName = method.getName();

        Log log = method.getAnnotation(Log.class);
        if (null!=log){
            String operation = log.operation();
            String typeStr = log.type().GetDescription();
            String ip = request.getRemoteAddr();
            logger.info(username+"---->"+ip+"---->"+typeStr+":"+operation);
            LogRecord logRecord = new LogRecord();
            String content = "[类名]:"+targetName+";[方法名]:"+methodName+";[ip]:"+ip;
            logRecord.setType(typeStr);
            logRecord.setContent(content);
            logRecord.setOperation(operation);
            logRecord.setCreate_by(username);
            logRecord.setCreate_time(new Date());
            logRecordService.addLogRecord(logRecord);
        }
    }

}