package com.org.test.bean.defind;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.Resource;

public class K {


    /**
     * {@link com.org.test.config.MainConfig4}定义了多个H类型的bean，在使用@Autowired进行注入的时候，会去寻找@Primary修饰的bean
     */
    @Autowired
    private H h;
    /**
     * {@link com.org.test.config.MainConfig4}定义了多个H类型的bean，在使用@Autowired进行注入的时候，如果没有Primary修饰的bean，则使用@Qualifier("h1")指定查找名为h1的bean
     *
     */
    @Qualifier("h1")
    @Autowired
    private H hh;


    /**
     *
     */
    @Resource
    private J j;

    public K() {
        System.out.println("construct K");
    }

    public H getH() {
        return h;
    }

    public H getHh() {
        return hh;
    }

    public J getJ() {
        return j;
    }
}
