package com.didispace.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * Web层日志切面2 使用Around注解
 */
@Aspect
//@Order(5)
@Component
public class WebLogAspect2 {

    private Logger logger = Logger.getLogger(getClass());

    //ThreadLocal<Long> startTime = new ThreadLocal<>();

    @Pointcut("execution(public * com.didispace.web..*.*(..))")
    public void webLog(){}

    @Around("webLog()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        Long startTime = System.currentTimeMillis();

        Object ret = proceedingJoinPoint.proceed();

        Long spendTime = System.currentTimeMillis()-startTime;
        logger.info("耗时 : " +  spendTime.intValue());

        // 记录下请求内容
        logger.info("URL : " + request.getRequestURL().toString());
        logger.info("HTTP_METHOD : " + request.getMethod());
        logger.info("IP : " + request.getRemoteAddr());
        logger.info("CLASS_METHOD : " + proceedingJoinPoint.getSignature().getDeclaringTypeName() + "." + proceedingJoinPoint.getSignature().getName());
        logger.info("ARGS : " + Arrays.toString(proceedingJoinPoint.getArgs()));

        return ret;
    }


}

