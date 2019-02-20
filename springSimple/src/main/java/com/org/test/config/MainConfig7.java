package com.org.test.config;

import com.org.test.aop.AspectDemo;
import com.org.test.aop.Calc;
import com.org.test.listener.ContextRefreshedListener;
import com.org.test.listener.MyApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


@Configuration
public class MainConfig7 {

    @Bean
    public MyApplicationListener myApplicationListener(){
        return new MyApplicationListener();
    }
    @Bean
    public ContextRefreshedListener contextRefreshedListener(){
        return new ContextRefreshedListener();
    }
}
