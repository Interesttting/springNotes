package com.org.test.bean.defind;

import org.springframework.beans.factory.FactoryBean;

/**
 * &beanName获取Factory本身
 */
public class F implements FactoryBean<E> {
    @Override
    public E getObject() throws Exception {
        return new E();
    }

    @Override
    public Class<?> getObjectType() {
        return E.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
