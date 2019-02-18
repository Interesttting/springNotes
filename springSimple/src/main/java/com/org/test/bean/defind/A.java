package com.org.test.bean.defind;

import org.springframework.stereotype.Component;

//Constructor > @PostConstruct >InitializingBean > init-method
//@PreDestroy > DisposableBean > destroy-method
public class A {

    public A() {
        System.out.println("construct A");
    }

    public void init() {
        System.out.println("init A");
    }

    public void destroy() {
        System.out.println("destroy A");
    }
}
