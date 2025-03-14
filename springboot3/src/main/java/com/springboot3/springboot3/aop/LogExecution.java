package com.springboot3.springboot3.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD) // Specifies that this annotation can only be applied to methods
@Retention(RetentionPolicy.RUNTIME)
public @interface LogExecution {
}
