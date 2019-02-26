package javax.http.servlet;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * servlet3.0支持注解方式
 * <p>
 * servlet3.0还支持异步的处理形式：并不是说异步能够取代同步的方式，
 * 使用异步的好处能够更快的响应客户端，最大程度的利用连接客户端的线程，使其能够接受更多客户端的请求
 * 异步处理即请求线程和处理过程的线程不是同一个线程，
 * <p>
 * <p>
 * 测试例子1：
 * url： http://localhost:8080/servlet3/myAsyn
 * 结果：
 * 线程名称：http-nio-8080-exec-5,当前时间:2019/02/26 09:19:15:052,打印信息：接受请求
 * 线程名称：http-nio-8080-exec-5,当前时间:2019/02/26 09:19:15:056,打印信息：返回请求
 * 线程名称：http-nio-8080-exec-6,当前时间:2019/02/26 09:19:15:056,打印信息：处理过程
 * <p>
 * 测试例子2：
 * url： http://localhost:8080/servlet3/myAsyn?flag=0
 * 结果：
 * 线程名称：http-nio-8080-exec-8,当前时间:2019/02/26 09:19:52:163,打印信息：接受请求
 * 线程名称：http-nio-8080-exec-8,当前时间:2019/02/26 09:19:52:163,打印信息：处理过程
 * 线程名称：http-nio-8080-exec-8,当前时间:2019/02/26 09:19:57:167,打印信息：返回请求
 * <p>
 * 总结：
 * servlet异步使用场景：客户端发起请求不需要实时查看结果，可以通过后续其他结果查询。对于后来返回结果来讲，异步请求的处理过程必须对返回结果没有影响.
 * 例如：客户请求时提交一个任务，后台返回固定结果"任务处理中。。"，则在这次请求很快的反馈给了客户端，并且客户端可以再次发起查询。对于想马上看到结果的请求就不能使用异步了。
 * 这种做法的好处是在用户提交任务和看到结果的过程中给客户端良好的提示，并且当客户端发起查询时可能任务已经处理完了。
 * 异步原理和使用一个线程池处理一个概念。
 */
@WebServlet(value = "/myAsyn", asyncSupported = true)
public class MyAsynServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        printMsg("接受请求");
        String flag = req.getParameter("flag");//是否需要异步处理标志
        if (flag != null && !"1".equals(flag)) {
            executeTaskNoResult();
            resp.getWriter().write("successful");
        } else {
            AsyncContext asyncContext = req.startAsync();
            asyncContext.start(new Runnable() {
                @Override
                public void run() {
                    //异步情况下，处理的业务不能影响输出的结果
                    executeTaskNoResult();
                }
            });
            asyncContext.complete();
            asyncContext.getResponse().getWriter().write("successful");

        }
        printMsg("返回请求");

    }

    /**
     * 定义一个业务消耗时间为5s
     */
    public void executeTaskNoResult() {
        try {
            printMsg("处理过程");
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SSS");


    private void printMsg(String msg){
        String time =formatter.format(new Date());
        System.out.println("线程名称："+Thread.currentThread().getName()+",当前时间:"+time+",打印信息："+msg);
    }
}
