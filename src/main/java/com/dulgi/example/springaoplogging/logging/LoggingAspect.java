package com.dulgi.example.springaoplogging.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class LoggingAspect {
    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Pointcut("execution(* com.dulgi.example..*.*(..))"
            + " ||execution(* java.lang.Integer..*.*(..))"
    )
    private void common() { }

    @Pointcut("execution(* com.dulgi.example..dto.*.set*(..))")
    private void setter() { }

    @Pointcut("execution(* com.dulgi.example..dto.*.get*(..))")
    private void getter() { }


    @Before(value ="common()")
    public void beforeLog(JoinPoint jp) {
        logger.info("---- 메소드 실행 : " + jp.getSignature().toShortString() + "----");
        logger.info("" + jp.getSignature().getDeclaringType());
        logger.info("argument : " + jp.getArgs().getClass().getSimpleName() + " " + Arrays.toString(jp.getArgs()));
    }

    @AfterReturning(value = "common()", returning = "obj")
    public void afterReturningLog(JoinPoint jp, Object obj) {
        logger.info("---- 메소드 종료 : " + jp.getSignature().toShortString() + "----");
        System.out.println("return: " + obj);
    }

    @AfterReturning(value="getter()", returning = "obj")
    public void afterGetter(JoinPoint jp, Object obj) {
        logger.info("---- " + jp.getSignature().toShortString());
        System.out.println("return : " + obj);
    }

    @AfterReturning(value="setter()", returning = "obj")
    public void afterSetter(JoinPoint jp, Object obj) {
        logger.info("---- " + jp.getSignature().toShortString());
        System.out.println("return : " + obj);
    }
}
