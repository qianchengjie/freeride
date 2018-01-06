package com.hdu.freeride.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Author: XiaoXiaoQian
 * Description:
 * Date: 2017/12/14 18:20
 */
@Aspect
@Component
public class HttpAspect {

    private static final Logger logger = LoggerFactory.getLogger(HttpAspect.class);
    @Pointcut("execution(public * com.hdu.freeride.controller.*.*(..))")
    public void log() {}

    @Before("log()")
    public void before(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest req = attributes.getRequest();
        HttpServletResponse res = attributes.getResponse();

        res.setHeader("Access-Control-Allow-Origin", "*");
        res.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE, PATCH");

        logger.info("uri={}", req.getRequestURI());
        logger.info("method={}", req.getMethod());
        logger.info("ip={}", req.getRemoteAddr());
        logger.info("class_method={}", joinPoint.getSignature().getDeclaringTypeName()
                + "." + joinPoint.getSignature().getName());
        logger.info("args={}", joinPoint.getArgs());
    }

    @AfterReturning(returning = "object", pointcut = "log()")
    public void afterReturning(Object object) {
        logger.info("response={}", object.toString());
    }

}
