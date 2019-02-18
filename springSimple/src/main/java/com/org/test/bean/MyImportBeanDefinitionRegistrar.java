package com.org.test.bean;

import com.org.test.bean.defind.E;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * BeanDefinitionRegistry registry 直接修改bean定义来生成bean
 *
 * 由于该方式获取了所有的bean定义 就可以做一些逻辑判断
 */
public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(E.class);
        registry.registerBeanDefinition("bean-e",rootBeanDefinition);
    }
}
