package com.org.test.config;

import com.org.test.aop.AspectDemo;
import com.org.test.aop.Calc;
import com.org.test.bean.defind.A;
import com.org.test.bean.defind.L;
import com.org.test.bean.defind.M;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;


@Configuration
@EnableAspectJAutoProxy
public class MainConfig6 {

    @Bean
    public Calc calc(){
        return new Calc();
    }

    @Bean
    public AspectDemo aspectDemo(){
        return new AspectDemo();
    }
}
