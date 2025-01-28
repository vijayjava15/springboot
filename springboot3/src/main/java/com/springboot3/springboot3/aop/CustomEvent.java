package com.springboot3.springboot3.aop;

import org.springframework.context.ApplicationEvent;

public class CustomEvent<T> extends ApplicationEvent {



   private final   T object;

    public CustomEvent(Object source, T object) {
        super(source);
        this.object = object;
    }

    public T getObject() {
        return object;
    }
}
