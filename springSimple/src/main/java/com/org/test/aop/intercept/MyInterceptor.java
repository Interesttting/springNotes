package com.org.test.aop.intercept;

public class MyInterceptor {

    private final String name;

    public MyInterceptor(String name) {
        this.name = name;
    }

    public Object invoke(MyReflectiveMethodInvocation myReflectiveMethodInvocation){
        System.out.println("拦截器"+name+" has invoke before");
        Object o =myReflectiveMethodInvocation.invoke();
        System.out.println("拦截器"+name+" has invoke after");
        return o;
    }
}
