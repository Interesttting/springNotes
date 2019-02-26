package org.mvc.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 *
 * spring mvc 提供了多种异步处理的方式：
 * 方式1：返回值为Callable类型
 * 方式2：返回值为DeferredResult类型
 *
 * 两种比较之下：
 * 方式1、方式2原理都是一样的：请求线程和工作线程不是同一个线程；并且在任务结束的时候让服务器自己请求自己后把结果返回给客户端
 * 唯一不同的是方式2提供了超时机制。
 *
 * 使用场景：
 * 如果客户端请求是一个耗时的任务，则spring异步处理的可以让并发量保持不变，而不是下降。
 *
 * spring mvc异步和servlet3.0异步比较：
 * servlet3.0的功能比较简陋：没有阻塞客户端，等同于向线程池提交了个任务，不会去等待结果。处理方式单一。
 * spring mvc：可以阻塞，可以不阻塞，可以超时处理。处理方式多样
 *
 * ps：如果任务耗时很长，不想让客户端阻塞太久，则选用方式2，把超时时间调小，先返回类似"处理中的"的结果，让客户端通过其他接口查询。
 *
 */
@RestController()
public class AsynController {


    private Executor executor = Executors.newFixedThreadPool(5);

    /**
     * 异步处理方式1：
     * <p>
     * 打印结果：
     * preHandle-------http-nio-8080-exec-5========/call *浏览器发起的请求
     * call start------------http-nio-8080-exec-5
     * call finish------------http-nio-8080-exec-5
     * execute task------------MvcAsync1 *在执行完return callable，提交到线程池执行
     * preHandle-------http-nio-8080-exec-6========/call *任务执行完成后 服务端自己向自己发起的请求
     * postHandle-------http-nio-8080-exec-6
     * afterCompletion-------http-nio-8080-exec-6
     * <p>
     * <p>
     * 这种方式导致客户端一直阻塞，只有服务端是异步方式处理任务而已。从打印结果来看，服务端接收请求的线程和处理业务的线程不是同一个线程，
     * 说明处理连接客户端请求的线程很快完成了任务就可以处理下一个客户端的连接了,而任务处理完成后再触发 服务器自己请求自己，把结果返回给对应的客户端
     *
     * @return callable 返回后被放到线程池中执行
     */
    @RequestMapping("/call")
    public Callable<String> call() {
        System.out.println("call start------------" + Thread.currentThread().getName());
        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(10000);//模拟耗时任务
                System.out.println("execute task------------" + Thread.currentThread().getName());
                return "asyn finish";
            }
        };
        System.out.println("call finish------------" + Thread.currentThread().getName());
        return callable;
    }


    /**
     * 异步处理方式2
     * 返回值为DeferredResult类
     * 原理和方式1一样：这种方式导致客户端一直阻塞，只有服务端是异步方式处理任务而已。从打印结果来看，服务端接收请求的线程和处理业务的线程不是同一个线程，
     * 说明处理连接客户端请求的线程很快完成了任务就可以处理下一个客户端的连接了,而任务处理完成后再触发 服务器自己请求自己，把结果返回给对应的客户端
     * 不同的是：方式2有超时机制，
     * 方式2：触发服务器自己请求自己的两个时间点，
     * 1、在超时间内调用了setResult。
     * 2、在超时间后调用了setResult，或者没有调用。超时的那一刻会触发（需要注意的时即使是已经做了超时处理返回了结果，工作线程仍然在运行，直到run方法结束）
     *
     * 未超时的打印结果：
     * preHandle-------http-nio-8080-exec-7========/dowork
     * dowork--------pool-2-thread-2
     * preHandle-------http-nio-8080-exec-8========/dowork
     * postHandle-------http-nio-8080-exec-8
     * afterCompletion-------http-nio-8080-exec-8
     *
     * 超时的打印结果：
     * preHandle-------http-nio-8080-exec-9========/dowork
     * preHandle-------http-nio-8080-exec-10========/dowork
     * postHandle-------http-nio-8080-exec-10
     * afterCompletion-------http-nio-8080-exec-10
     * dowork--------pool-2-thread-3
     *
     * @return
     */
    @RequestMapping("/dowork")
    public DeferredResult<String> dowork() {
        //使用了超时机制，超时的时候要可以提示失败也可以提示正在处理中，看业务
        final DeferredResult<String> result = new DeferredResult<String>((long) 5000, "dealing");
        //也可以不使用超时机制
//        final DeferredResult<String> result = new DeferredResult<String>();
        //提交给线程池处理
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);//未超时
//                    Thread.sleep(6000);//超时
                    System.out.println("dowork--------" + Thread.currentThread().getName());
                    //1、调用setResult方法后触发服务器再次请求自己，没有超时则返回结果值successful，如果超时了返回timeout
                    //2、不管是否调用setResult，一旦超时就会直接返回
                    //3、超时后，在调用setResult，对结果没有影响了
                    result.setResult("successful...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        return result;
    }

    /**
     * 该方式等同于servlet3.0异步
     * 向线程池提交了任务，不管结果就返回了
     * @return
     */
    @RequestMapping("/normal")
    public String normal(){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    //dosomething
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        return "dealing";
    }
}
