package com.org.test.listener;

import org.springframework.context.ApplicationEvent;

public class MyEvent extends ApplicationEvent {
    /**
     * Create a new ApplicationEvent.
     *
     * @param source 发生事件的对象
     */
    public MyEvent(Object source) {
        super(source);
    }
}
