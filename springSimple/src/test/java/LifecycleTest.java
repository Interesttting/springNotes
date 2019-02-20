import com.org.test.config.MainConfig7;
import com.org.test.config.MainConfig8;
import com.org.test.listener.MyEvent;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class LifecycleTest {


    /**
     * spring 监听内容包含组件有 事件广播器、事件监听、事件
     * 要在事件广播器里注册对应的事件监听后，在触发事件后才会执行 事件监听的业务代码
     */
    @Test
    public void test(){
        ApplicationContext context =new AnnotationConfigApplicationContext(MainConfig8.class);
        ((AnnotationConfigApplicationContext) context).close();
    }
}
