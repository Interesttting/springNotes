package com.org.test.bean.defind;


import org.springframework.beans.factory.annotation.Value;

public class J {

    @Value("${app.color}")
    private String color;

    @Value("#{5+6}")
    private int num;
    @Value("wang")
    private String name;

    public J() {
        System.out.println("construct J");
    }

    public String getColor() {
        return color;
    }

    public int getNum() {
        return num;
    }

    public String getName() {
        return name;
    }
}
