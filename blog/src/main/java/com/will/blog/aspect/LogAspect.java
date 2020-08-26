package com.will.blog.aspect;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class LogAspect {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(* com.will.blog.controller.*.*(..))")
    public void log(){}

    @Before("log()")
    public void doBefore(JoinPoint joinPoint){
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        String url = request.getRequestURI();
        String ip = request.getRemoteAddr();
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String method = joinPoint.getSignature().getName();
        String args = JSON.toJSONString(((MethodSignature) joinPoint.getSignature()).getParameterNames());
        logger.info("请求ip:{},url:{},target:{}:{},args:{}",ip,url,className,method,args);
    }

    @AfterReturning(returning = "result",pointcut = "log()")
    public void afterReturning(Object result){
        logger.info("result : {}",result);
    }

}
