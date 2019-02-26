package javax.http.servlet;

import javax.servlet.*;
import javax.servlet.annotation.HandlesTypes;
import java.util.EnumSet;
import java.util.Set;

/**
 * 自定义ServletContainerInitializer实现类的简单实现：
 * tomcat会在加载过程中把HandlesTypes中的类型的子类集合传入到onStartup方法的形参set中
 */
@HandlesTypes({Filter.class})
public class MyServletContainerInitializer implements ServletContainerInitializer {
    /**
     *
     * @param set @HandlesTypes({接口}) 接口所有的子类
     * @param context servlet上下文
     * @throws ServletException
     */
    @Override
    public void onStartup(Set<Class<?>> set, ServletContext context) throws ServletException {

        //注册 DispatcherServlet
        ServletRegistration.Dynamic dispatcherServlet = context.addServlet("DispatcherServlet", new DispatcherServlet());
        dispatcherServlet.addMapping("/");
        dispatcherServlet.setLoadOnStartup(0);

    }
}
