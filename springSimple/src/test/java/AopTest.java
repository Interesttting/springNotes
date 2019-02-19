import com.org.test.aop.Calc;
import com.org.test.config.MainConfig3;
import com.org.test.config.MainConfig6;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AopTest {

    @Test
    public void test(){
        ApplicationContext context =new AnnotationConfigApplicationContext(MainConfig6.class);
        Calc bean = context.getBean(Calc.class);
        bean.add(1,1);
    }
}
