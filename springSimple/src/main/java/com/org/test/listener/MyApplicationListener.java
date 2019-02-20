package com.org.test.listener;

import org.springframework.context.ApplicationListener;

public class MyApplicationListener implements ApplicationListener<MyEvent> {

    @Override
    public void onApplicationEvent(MyEvent event) {
        System.out.println(event);
    }
}
