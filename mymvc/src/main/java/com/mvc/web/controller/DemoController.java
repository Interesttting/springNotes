package com.mvc.web.controller;

import com.mvc.web.annotation.Param;
import com.mvc.web.annotation.Resource;
import com.mvc.web.annotation.Controller;
import com.mvc.web.annotation.RequestMapping;
import com.mvc.web.service.DemoService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class DemoController {

    @Resource
    private DemoService demoService;
    @RequestMapping("/")
    public String index(HttpServletRequest request, HttpServletResponse response,@Param("a")int v){
        return "index";
    }
}
