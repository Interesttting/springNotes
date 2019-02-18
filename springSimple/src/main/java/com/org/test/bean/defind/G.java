package com.org.test.bean.defind;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.Date;

/**
 *
 */
@Lazy
public class G{

    public G() {
        System.out.println("init G()");
    }

    @Autowired
    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
