package com.org.test.config;

import com.org.test.lifecycle.MySmartLifecycle;
import com.org.test.listener.ContextRefreshedListener;
import com.org.test.listener.MyApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class MainConfig8 {

    @Bean
    public MySmartLifecycle mySmartLifecycle(){
        return new MySmartLifecycle();
    }
}
