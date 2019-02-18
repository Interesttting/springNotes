package com.org.test.bean.defind;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 如果自定义的bean需要获取容器中相关的信息；例如beanFactory、context等等等信息则需要根据需要实现xxxAware接口；而Aware接口都有相应的xxxAwareProcessor
 * 例如 ApplicationContextAware 对应的后置处理器为ApplicationContextAwareProcessor
 *
 *
 * 例子中为了让本类获取 ApplicationContext 对象 所以实现 ApplicationContextAware 之后就可以利用 ApplicationContext中的数据了
 *
 * spring 常用aware
 *
 * BeanNameAware               获得到容器中Bean的名称
 * BeanFactoryAware            获得当前bean Factory,从而调用容器的服务
 * ApplicationContextAware         当前的application context从而调用容器的服务
 * MessageSourceAware          得到message source从而得到文本信息
 * ApplicationEventPublisherAware      应用时间发布器,用于发布事件
 * ResourceLoaderAware         获取资源加载器,可以获得外部资源文件
 */
public class L implements ApplicationContextAware {
    private ApplicationContext context;
    private A a;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        a =applicationContext.getBean(A.class);
        context=applicationContext;//如果后续还需要拿applicationContext的数据  就赋值到成员变量中。
    }

    public A getA() {
        return a;
    }
}
