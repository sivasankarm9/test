package com.sample.ems.logs;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Log4j2
@Aspect
@Component
public class GlobalLogger {

    @Pointcut("within(com.sample.ems.controller..*)")
    public void controllerMethods(){};

    @Pointcut("within(com.sample.ems.service..*)")
    public void serviceMethods(){};

    @Pointcut("within(com.sample.ems.service.impl..*)")
    public void serviceImplMethods(){};

    @Pointcut("within(com.sample.ems.repository..*)")
    public void repositoryMethods(){};


    @Before("controllerMethods() || serviceMethods() || serviceImplMethods() || repositoryMethods()")
    public void logMethodsEntry(JoinPoint joinPoint){
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        log.info("Entering method: {}, {}() with arguments = {}", className, methodName, args);
    }

    @AfterReturning(pointcut = "controllerMethods() || serviceMethods() || serviceImplMethods() || repositoryMethods()", returning = "results")
    public void logMethodsExit(JoinPoint joinPoint, Object results){
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        log.info("Existing method {}, {}() exit response = {}", className, methodName, results);
    }

    @AfterThrowing(pointcut = "controllerMethods() || serviceMethods() || serviceImplMethods() || repositoryMethods()", throwing = "exception")
    public void controllerMethodExceptions(JoinPoint joinPoint, Throwable exception){
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        log.info("Exception in  method {}, {}() with  exception message = {} and exception: {}", className, methodName, exception.getMessage(), exception);
    }

}
