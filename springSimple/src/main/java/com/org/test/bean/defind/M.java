package com.org.test.bean.defind;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

/**
 * BeanFactoryPostProcessor和 BeanPostProcessor 比较：
 * BeanFactoryPostProcessor面对的是整个容器管理着所有bean包括普通bean和后置处理器的bean。
 * BeanPostProcessor：后置处理器的bean会比普通的bean先实例化，用于对普通bean实例化过程中的增强。
 *
 * 例子中通过获取了beanFactory 手动注册了一个bean
 *
 */
public class M implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
//        beanFactory.getBeanDefinition()
//        beanFactory.getBean()
//        beanFactory.getBeanDefinitionNames();//获取所有的bean定义名称
//        beanFactory.addBeanPostProcessor();//增加bean后置处理器
        beanFactory.registerSingleton("a1" ,new A1());
    }
}
