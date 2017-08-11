package com.teamcore.manageapp.service.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.util.Arrays;

@Aspect
public class LoggingAspect {


    @Pointcut("@annotation(com.teamcore.manageapp.service.annotations.Loggable)")
    public void loggableMethods(){}

    @Pointcut("within(com.teamcore.manageapp.service.dao..*)")
    public void daoMethods(){}


    @Pointcut("within(com.teamcore.manageapp.service.service..*)")
    public void serviceMethods() {}

   @Around("loggableMethods() || daoMethods() || serviceMethods()")
    public Object logMethodInvocation(ProceedingJoinPoint jp) throws Throwable {
       System.out.println("hijacked class: " + jp.getTarget().getClass().getName());
       System.out.println("hijacked method : " + jp.getSignature().getName());
       System.out.println("hijacked arguments : " + Arrays.toString(jp.getArgs()));

       Object returnValue = null;
       try {
           returnValue = jp.proceed();
       } catch(Throwable t) {
           System.out.println("hijacked exception! : " + t);
           throw t;
       }

       System.out.println("hijacked return value :" + returnValue);
       return returnValue;

    }
}
