package com.org.test.config;

import com.org.test.model.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MainConfig {

    @Bean("abcPerson")
    public Person person01(){
        return new Person("james",20);
    }
}
