package com.org.test.bean.defind;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class B implements InitializingBean, DisposableBean {
    public B() {
        System.out.println("construct B");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("destroy B");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("afterPropertiesSet B");
    }
}
