package com.org.test.aop.intercept;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.util.List;

public class MyReflectiveMethodInvocation implements MethodInterceptor {
    private Object target;
    private List<MyInterceptor> list;
    private int count=0;
    private Object obj=null;
    public Object invoke() {
        if (count<list.size()){
            list.get(count++).invoke(this);
        }else {
            obj =((Obj)target).add();
        }
        return obj;
    }

    public MyReflectiveMethodInvocation(Object target, List<MyInterceptor> list) {
        this.target = target;
        this.list = list;
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        return null;
    }
}
