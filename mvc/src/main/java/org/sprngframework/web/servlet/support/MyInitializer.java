package org.sprngframework.web.servlet.support;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import org.sprngframework.web.config.AppContextConfig;
import org.sprngframework.web.config.RootContextConfig;

/**
 * 容器继承关系：
 * WebApplicationInitializer    接口
 *      AbstractContextLoaderInitializer    抽象类
 *          AbstractDispatcherServletInitializer    抽象类
 *              AbstractAnnotationConfigDispatcherServletInitializer    抽象类
 *                  MyInitializer   自定义实现类
 */
public class MyInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    /**
     * AnnotationConfigWebApplicationContext 跟容器
     * RootContextConfig 跟容器配置类
     * @return
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{RootContextConfig.class};
    }

    /**
     * AnnotationConfigWebApplicationContext app容器
     * AppContextConfig app容器配置类
     * @return
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{AppContextConfig.class};
    }

    /**
     * 获取DispatcherServlet的 mapping url
     *  /：拦截所有请求（包括静态资源（xx.js,xx.png）），但是不包括*.jsp；
     *  /*：拦截所有请求；连*.jsp页面都拦截；jsp页面是tomcat的jsp引擎解析的；如果配置了"/*" jsp不会被解析
     * @return
     */
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
