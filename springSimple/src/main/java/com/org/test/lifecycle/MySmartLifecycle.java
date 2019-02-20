package com.org.test.lifecycle;

import org.springframework.context.SmartLifecycle;

public class MySmartLifecycle implements SmartLifecycle {

    private boolean isRunning;
    //为true时表示在应用启动时 调用start方法 ；为false需要自己手动调用start方法
    @Override
    public boolean isAutoStartup() {
        return true;
    }

    @Override
    public void stop(Runnable callback) {

        System.out.println("MySmartLifecycle stop callback");
        Thread.currentThread().interrupt();
    }

    @Override
    public void start() {
        System.out.println("MySmartLifecycle start");
        isRunning=true;
    }

    @Override
    public void stop() {
        System.out.println("MySmartLifecycle stop");
    }

    @Override
    public boolean isRunning() {
        return isRunning;
    }

    @Override
    public int getPhase() {
        return 0;
    }
}
