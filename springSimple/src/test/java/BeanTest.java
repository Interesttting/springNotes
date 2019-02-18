import com.org.test.bean.defind.*;
import com.org.test.config.*;
import com.org.test.model.Person;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class BeanTest {

    /**
     * 注册bean方式1
     * 使用@Configuration下 @Bean 方式注册bean
     * 查看{@link MainConfig}
     */
    @Test
    public void test(){
        ApplicationContext context =new AnnotationConfigApplicationContext(MainConfig.class);
        Person person = context.getBean(Person.class);
        System.out.println(person);

        String[] beanNames = context.getBeanNamesForType(Person.class);
        for (String beanName : beanNames) {
            System.out.println(beanName);
        }
    }

    /**
     * 注册bean方式2
     * 使用@Configuration下 @ComponentScan 扫描@Component、@Service、@Controller、@Repository 方式注册bean
     * 查看{@link MainConfig2}
     */
    @Test
    public void test2(){
        ApplicationContext context =new AnnotationConfigApplicationContext(MainConfig2.class);


        String[] beanNames = context.getBeanDefinitionNames();
        for (String beanName : beanNames) {
            System.out.println(beanName);
        }
    }

    /**
     * 注册bean方式3
     * 使用@Configuration下 @Import 注册bean 查看{@link ImportConfig}
     * 其中 还通过 {@link com.org.test.bean.MyImportSelector} 批量生成多个bean
     * {@link com.org.test.bean.MyImportBeanDefinitionRegistrar} 通过修改bean 定义来注册bean
     */
    @Test
    public void test3(){
        ApplicationContext context =new AnnotationConfigApplicationContext(ImportConfig.class);


        String[] beanNames = context.getBeanDefinitionNames();
        for (String beanName : beanNames) {
            System.out.println(beanName);
        }

        System.out.println(context.getBean(C.class).toString());
    }


    /**
     * 注册bean方式4
     * 使用FactoryBean
     * 查看{@link F}
     */
    @Test
    public void test4() throws Exception {
        ApplicationContext context =new AnnotationConfigApplicationContext(MainConfig3.class);

        System.out.println(context.getBean(F.class).getObject());
    }

    /**
     * bean 声明周期管理的四种定义方式
     * 查看{@link com.org.test.bean.defind.A }{@link com.org.test.bean.defind.B }{@link com.org.test.bean.defind.C }{@link com.org.test.bean.defind.D }
     */
    @Test
    public void test5(){
        ApplicationContext context =new AnnotationConfigApplicationContext(MainConfig3.class);


        String[] beanNames = context.getBeanDefinitionNames();
        for (String beanName : beanNames) {
            System.out.println(beanName);
        }
        ((AnnotationConfigApplicationContext) context).destroy();
    }

    /**
     * 根据条件判断是否将bean注册到容器里
     * 查看
     *{@link com.org.test.bean.condition.WinCondition}
     *{@link com.org.test.bean.condition.MacCondition}
     * 使用条件方式表示 注册的不同bean继承同一个父类或者实现同一个接口
     */
    @Test
    public void test6(){
        ApplicationContext context =new AnnotationConfigApplicationContext(MainConfig3.class);


        String[] beanNames = context.getBeanDefinitionNames();
        for (String beanName : beanNames) {
            System.out.println(beanName);
        }
        ((AnnotationConfigApplicationContext) context).destroy();
    }

    /**
     * 当容器中包含多个同类型的bean时，又使用了@Autowire注解时 必须使用@Primary注解优先使用哪个bean
     * 查看{@link com.org.test.bean.defind.G}中使用注解@Autowire注入容器中多个同类型的bean ，在{@link MainConfig3}中定义了两个date 其中一个使用了@Primary注解
     */
    @Test
    public void test7(){
        ApplicationContext context =new AnnotationConfigApplicationContext(MainConfig3.class);

        G bean = context.getBean(G.class);

        DateFormat df =new SimpleDateFormat();
        System.out.println(df.format(bean.getDate()));
        ((AnnotationConfigApplicationContext) context).destroy();
    }


    /**
     *
     * bean懒加载 使用@Lazy注解  ，在正在使用bean的时候初始化bean
     * 正确定义方式：
     *     @Lazy
     *     @Bean
     *     public G g(){
     *         return new G();
     *     }
     *
     *     错误定义方式：@Lazy
     *                  public class G{
     *
     */
    @Test
    public void test8(){
        ApplicationContext context =new AnnotationConfigApplicationContext(MainConfig3.class);
        System.out.println("*************************");
        G bean = context.getBean(G.class);

    }

    /**
     * 定义方式：
     *     @Scope(value = "singleton")
     *     @Bean
     *     public H h1(){
     *         return new H();
     *     }
     *     默认是singleton 调用getbean时都是返回同一个bean
     *     其他值prototype 调用getbean时都是返回不同一个bean
     *     request、session了解就好
     *
     */
    @Test
    public void test9(){
        ApplicationContext context =new AnnotationConfigApplicationContext(MainConfig4.class);
        System.out.println("*************************");
        H bean = (H) context.getBean("h1");
        System.out.println(bean);
        H bean2 = (H) context.getBean("h1");
        System.out.println("bean==bean2:"+(bean==bean2));

        H bean3 = (H) context.getBean("h2");
        System.out.println(bean);
        H bean4 = (H) context.getBean("h2");
        System.out.println("bean3==bean4:"+(bean3==bean4));

    }

    /**
     * @Value bean 属性赋值
     *
     * @PropertySource("classpath:app.properties") 将app.properties中所有的key和value全部放在Environment
     */
    @Test
    public void test10(){
        ApplicationContext context =new AnnotationConfigApplicationContext(MainConfig4.class);
        J bean = context.getBean(J.class);
        System.out.println(bean.getColor());//key=app.color
        System.out.println(bean.getName());
        System.out.println(bean.getNum());

        Environment environment = context.getEnvironment();
        System.out.println("environment.getProperty(\"app.color\")="+environment.getProperty("app.color"));
    }

    /**
     *
     * bean注入的三种注解@Autowired、、@Inject、@Resource
     *
     * @Autowired 使@Qualifier和@Primary有效果，并且可以设置required属性；
     * @Inject 使@Qualifier和@Primary有效果，但没有没有相应的bean不会报错（JSR330，需要导入javax.inject，如果项目中不是用的spring环境的情况下需要注入bean则使用该方式）
     * @Resource 不支持@Primary和@Qualifier，注入时没有相应的bean会报错
     * @Resource 和 @Qualifier功能有些相似，都是通过id查找
     *
     * 具体配置查看{@link K}
     */
    @Test
    public void test11(){
        ApplicationContext context =new AnnotationConfigApplicationContext(MainConfig4.class);
        K bean = context.getBean(K.class);
        System.out.println(bean.getH().getName());
        System.out.println(bean.getHh().getName());
        System.out.println(bean.getJ());
    }

    /**
     *  如果自定义的bean需要获取容器中相关的信息；例如beanFactory、context等等等信息则需要根据需要实现xxxAware接口；而Aware接口都有相应的xxxAwareProcessor
     * 例如ApplicationContextAware对应的后置处理器为ApplicationContextAwareProcessor
     */
    @Test
    public void test12(){
        ApplicationContext context =new AnnotationConfigApplicationContext(MainConfig5.class);
        L bean = context.getBean(L.class);
        System.out.println(bean.getA());
    }

    /**
     * BeanFactoryPostProcessor 的作用 管理应用下所有的bean定义或者手动注册bean 查看{@link M}
     */
    @Test
    public void test13(){
        ApplicationContext context =new AnnotationConfigApplicationContext(MainConfig5.class);
        A1 bean = context.getBean(A1.class);
        System.out.println(bean);
    }

}
