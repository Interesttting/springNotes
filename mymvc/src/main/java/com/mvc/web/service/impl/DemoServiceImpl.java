package com.mvc.web.service.impl;

import com.mvc.web.annotation.Service;
import com.mvc.web.service.DemoService;

@Service(value="DemoService")
public class DemoServiceImpl implements DemoService {

    public boolean doSomething(String command) {
        return false;
    }
}
