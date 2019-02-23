package org.sprngframework.web.config;

import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 *  作为app容器的配置类，必须使用 @EnableWebMvc 注解，并且该类必须继承 WebMvcConfigurerAdapter 或者直接实现 WebMvcConfigurer 接口
 */
@EnableWebMvc
public class AppContextConfig extends WebMvcConfigurerAdapter {

}
