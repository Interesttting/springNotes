package javax.http.servlet;

import javax.servlet.*;
import javax.servlet.annotation.HandlesTypes;
import java.util.EnumSet;
import java.util.Set;

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
        for (Class<?> aClass : set) {
            System.out.println(aClass);//对这些子类进行处理 class javax.http.servlet.MyFilter
        }
        //注册servlet
        ServletRegistration.Dynamic demo = context.addServlet("demo", new DemoServlet());
        demo.addMapping("/demo");
        //注册filter
        FilterRegistration.Dynamic dynamic = context.addFilter("myfilter", new MyFilter());
        dynamic.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST),true,"/*");
        //注册监听
        context.addListener(new MyEventListener());
    }
}
