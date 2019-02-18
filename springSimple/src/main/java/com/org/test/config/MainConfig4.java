package com.org.test.config;

import com.org.test.bean.condition.MacCondition;
import com.org.test.bean.condition.WinCondition;
import com.org.test.bean.defind.*;
import com.org.test.model.Person;
import com.org.test.model.Person2;
import org.springframework.beans.factory.annotation.InitDestroyAnnotationBeanPostProcessor;
import org.springframework.context.annotation.*;

import java.util.Date;


@Configuration
@PropertySource("classpath:app.properties")
public class MainConfig4 {

    @Scope(value = "singleton")
    @Bean("h1")
    public H h1() {
        return new H("h1");
    }
    @Primary
    @Scope(value = "prototype")
    @Bean("h2")
    public H h2() {
        return new H("h2");
    }

    @Scope(value = "request")
    @Bean
    public H h3() {
        return new H("h3");
    }

    @Scope(value = "session")
    @Bean
    public H h4() {
        return new H("h4");
    }

    @Bean("j")
    public J j() {
        return new J();
    }
    @Bean
    public K k() {
        return new K();
    }

}
