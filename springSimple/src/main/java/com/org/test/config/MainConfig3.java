package com.org.test.config;

import com.org.test.bean.condition.MacCondition;
import com.org.test.bean.condition.WinCondition;
import com.org.test.bean.defind.A;
import com.org.test.bean.defind.F;
import com.org.test.bean.defind.G;
import com.org.test.model.Person;
import com.org.test.model.Person2;
import org.springframework.beans.factory.annotation.InitDestroyAnnotationBeanPostProcessor;
import org.springframework.context.annotation.*;

import java.util.Date;


@Configuration
@ComponentScan(value = "com.org.test.bean.defind")
@Import(value = {InitDestroyAnnotationBeanPostProcessor.class})
public class MainConfig3 {

    @Bean(initMethod = "init", destroyMethod = "destroy")
    public A a() {
        return new A();
    }

    @Bean
    public F f() {
        return new F();
    }

    @Conditional(value = {MacCondition.class})
    @Bean
    public Person p() {
        return new Person();
    }

    @Conditional(value = {WinCondition.class})
    @Bean
    public Person2 p2() {
        return new Person2();
    }

    @Bean
    public Date date(){
        return new Date(2018,1,1);
    }
    @Primary
    @Bean
    public Date date2(){
        return new Date(2015,11,11);
    }

    @Lazy
    @Bean
    public G g(){
        return new G();
    }

}
