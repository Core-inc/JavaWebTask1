package com.teamcore.manageapp.service.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

@Aspect
public class ServiceLoggingAspect {


   private final static Logger logger = LoggerFactory.getLogger(ServiceLoggingAspect.class);

    @Pointcut("@annotation(com.teamcore.manageapp.service.annotations.Loggable)")
    public void loggableMethods(){}

    @Pointcut("within(com.teamcore.manageapp.service.dao..*)")
    public void daoMethods(){}


    @Pointcut("within(com.teamcore.manageapp.service.service..*)")
    public void serviceMethods() {}

   @Around("loggableMethods() || daoMethods() || serviceMethods()")
    public Object logMethodInvocation(ProceedingJoinPoint jp) throws Throwable {
       logger.info("hijacked class: {}", jp.getTarget().getClass().getName());
       logger.info("hijacked method: {}", jp.getSignature().getName());
       logger.info("hijacked arguments: {}", Arrays.toString(jp.getArgs()));

       Object returnValue = null;
       try {
           returnValue = jp.proceed();
       } catch(Throwable t) {
           logger.error("hijacked exception: " + t);
           throw t;
       }

       logger.info("hijacked return value: {}", returnValue);
       return returnValue;

    }

}
