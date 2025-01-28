package com.springboot3.springboot3.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {


    @Before("@annotation(com.springboot3.springboot3.aop.LogExecution)")
    public void loggingAdvice(){
        System.out.println("Advice Run Get Method Called");
    }

}
