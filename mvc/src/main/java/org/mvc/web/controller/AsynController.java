package org.mvc.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Callable;

@RestController
public class AsynController {


    /**
     *
     * 打印结果：
     * preHandle-------http-nio-8080-exec-5========/call *浏览器发起的请求
     * call start------------http-nio-8080-exec-5
     * call finish------------http-nio-8080-exec-5
     * execute task------------MvcAsync1 *在执行完return callable，提交到线程池执行
     * preHandle-------http-nio-8080-exec-6========/call *任务执行完成后 服务端自己向自己发起的请求
     * postHandle-------http-nio-8080-exec-6
     * afterCompletion-------http-nio-8080-exec-6
     *
     *
     * 这种方式导致客户端一直阻塞，只有服务端是异步方式处理任务而已。从打印结果来看，服务端接收请求的线程和处理业务的线程不是同一个线程，
     * 说明处理连接客户端请求的线程很快完成了任务就可以处理下一个客户端的连接了,而任务处理完成后再触发 服务器自己请求自己，把结果返回给对应的客户端
     * @return callable 返回后被放到线程池中执行
     */
    @RequestMapping("/call")
    public Callable<String> call(){
        System.out.println("call start------------"+Thread.currentThread().getName());
        Callable<String> callable =new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(10000);//模拟耗时任务
                System.out.println("execute task------------"+Thread.currentThread().getName());
                return "asyn finish";
            }
        };
        System.out.println("call finish------------"+Thread.currentThread().getName());
        return callable;
    }
}
