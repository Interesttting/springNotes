package com.org.test.bean.defind;


public class H {
    private String name;
    public H() {
        System.out.println("construct H");
    }
    public H(String name) {
        this.name=name;
        System.out.println("construct H");
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
