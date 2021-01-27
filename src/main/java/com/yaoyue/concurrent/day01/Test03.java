package com.yaoyue.concurrent.day01;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @description: FutureTask配合Thread，用来处理有返回结果的情况
 * @author: WangDongXu (15224931482)
 * @date: 2021/1/27 21:21
 **/
@Slf4j(topic = "c.Test03")
public class Test03 {

    /**
     * FutureTask实现了RunnableFuture接口，RunnableFuture接口扩展自Runnable、Future接口，Future接口用来弥补Runnable接口中的run方法无法返回参数的问题
     * @param args
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 可使用lambda表达式替代
        FutureTask<Integer> task = new FutureTask<>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                log.info("running");
                // 线程休眠
                Thread.sleep(2000);
                return 100;
            }
        });

        Thread t = new Thread(task);
        t.start();
        log.info("{}", task.get());
    }

    /**
     * FutureTask的构造函数
     * public FutureTask(Callable<V> callable) {
     *     if (callable == null)
     *         throw new NullPointerException();
     *     this.callable = callable;
     *     this.state = NEW;       // ensure visibility of callable
     * }
     */
}
