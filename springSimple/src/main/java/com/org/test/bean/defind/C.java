package com.org.test.bean.defind;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * 容器中没有InitDestroyAnnotationBeanPostProcessor的后置处理器  @PostConstruct、@PreDestroy就不起作用
 * 谨慎使用
 */
@Component
public class C {
    public C() {
        System.out.println("construct C");
    }

    @PostConstruct
    public void initMethod() {
        System.out.println("initMethod C");
    }

    @PreDestroy
    public void destoryMethod() {
        System.out.println("destoryMethod C");
    }
}
