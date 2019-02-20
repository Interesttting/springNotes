package com.org.test.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

/**
 * @Pointcut("")定义切入点
 *     //@Before/@After/@AfterReturning/@AfterThrowing/@Around 修饰被嵌入的方法
 *     @Before/@After/@AfterReturning/@AfterThrowing修饰的方法 无法获取到ProceedingJoinPoint 对象 除非重写对应的后置处理器然后注册到ioc中
 *
 *
 *
 *
 */
@Aspect
public class AspectDemo {
    /**
     *  * 任意公共方法的执行：execution(public * *(..))
     *  *
     *  * 任何一个以“set”开始的方法的执行：execution(* set*(..))
     *  *
     *  * AccountService 接口的任意方法的执行：execution(* com.xyz.service.AccountService.*(..))
     *  *
     *  * 定义在service包里的任意方法的执行：execution(* com.xyz.service.*.*(..))
     *  *
     *  * 定义在service包和所有子包里的任意类的任意方法的执行：execution(* com.xyz.service..*.*(..))
     *  *
     *  * 定义在pointcutexp包和所有子包里的JoinPointObjP2类的任意方法的执行：execution(* com.test.spring.aop.pointcutexp..JoinPointObjP2.*(..))")
     */
    @Pointcut("execution(public * com.org.test.aop.Calc.*(..))")
    public void pointcut(){}

    @Before("pointcut()")
    public void logBefore() {
        System.out.println("logBefore");
    }
    @After("pointcut()")
    public void logAfter() {
        System.out.println("logAfter");
    }
    @AfterReturning("pointcut()")
    public void logAfterReturning() {
        System.out.println("logAfterReturning");
    }
    @AfterThrowing("pointcut()")
    public void logAfterThrowing() {
        System.out.println("logAfterThrowing");
    }
    @Around("pointcut()")
    public Object logAround(ProceedingJoinPoint point) throws Throwable {
        System.out.println("logAround before");
        Object o =point.proceed();
        System.out.println("logAround after");
        return o;
    }

}
