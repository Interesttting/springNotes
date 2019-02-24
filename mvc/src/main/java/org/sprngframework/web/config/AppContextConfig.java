package org.sprngframework.web.config;

import org.mvc.web.intercept.MyIntercept;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.*;

/**
 *  作为app容器的配置类，必须使用 @EnableWebMvc 注解，并且该类必须继承 WebMvcConfigurerAdapter，官方推荐继承 WebMvcConfigurationSupport 或者直接实现 WebMvcConfigurer 接口
 * ps：WebMvcConfigurationSupport 配置了spring mvc 默认的配置bean。
 */
@ComponentScan("org.mvc.web")
@EnableWebMvc
public class AppContextConfig extends WebMvcConfigurerAdapter {

    /**
     * 视图解析器配置
     * @param registry
     */
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp("WEB-INF/pages/",".jsp");
    }

    //静态资源访问,是否让tomcat来处理静态资源 表现欲jsp引用js、css、img等资源时是否可访问
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

//    /**
//     * 覆盖父类的方法 使用自己的bean
//     * @return
//     */
//    @Bean
//    public PathMatcher mvcPathMatcher() {
//        PathMatcher pathMatcher = getPathMatchConfigurer().getPathMatcher();
//        return (pathMatcher != null ? pathMatcher : new AntPathMatcher());
//    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyIntercept()).addPathPatterns("/**");
    }
}
