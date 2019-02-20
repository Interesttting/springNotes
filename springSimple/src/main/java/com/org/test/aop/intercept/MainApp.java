package com.org.test.aop.intercept;

import org.springframework.aop.framework.ProxyFactory;

import java.util.ArrayList;
import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        Obj obj=new Obj();
        List<MyInterceptor> list =new ArrayList<>();
        list.add(new MyInterceptor("MyInterceptor1"));
        list.add(new MyInterceptor("MyInterceptor2"));

        new MyReflectiveMethodInvocation(obj,list).invoke();
    }
}
