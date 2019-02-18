package com.org.test.config;

import com.org.test.bean.defind.*;
import org.springframework.context.annotation.*;


@Configuration
@PropertySource("classpath:app.properties")
public class MainConfig5 {

    @Bean
    public A a() {
        return new A();
    }
    @Bean
    public L l(){
        return new L();
    }
    @Bean
    public M m(){
        return new M();
    }
}
