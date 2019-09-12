package com.barista.Aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * 对controller方法做AOP日志
 *
 * @ClassName ControllerLog
 * @Author zhaoth
 * @Date 2019/9/5 10:06
 * @Version 1.0
 */
@Aspect
@Component
public class ControllerLog {
    private Logger logger = LoggerFactory.getLogger(ControllerLog.class);

    @Pointcut("execution(* com.barista..*Controller.*(..))")
    public void controllerAspect() {
    }

    @Around("controllerAspect()")
    public Object doBefore(ProceedingJoinPoint joinPoint) throws Throwable {
        //获取切入类名和方法名
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method targetMethod = methodSignature.getMethod();
        String targetMethodName = targetMethod.getDeclaringClass().getName() + " " + targetMethod.getName();
        logger.info("请求访问方法:[{}]", targetMethodName);

        //获取request中携带的信息
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        String ip = request.getRemoteAddr();
        String url = request.getRequestURL().toString();
        String method = request.getMethod();
        logger.info("IP:[{}]，URL:[{}]，请求类型:[{}]", ip, url, method);

        //拼接request中的参数
        StringBuilder builder = new StringBuilder();
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String name = parameterNames.nextElement();
            String[] values = request.getParameterValues(name);
            builder.append(name).append(":").append(Arrays.asList(values).toString()).append(",");
        }
        if (builder.length() > 0) {
            builder.deleteCharAt(builder.length() - 1);
        }
        logger.info("参数:[{}]", builder.toString());

        //调用目标方法并返回它的结果
        return joinPoint.proceed();
    }
}
