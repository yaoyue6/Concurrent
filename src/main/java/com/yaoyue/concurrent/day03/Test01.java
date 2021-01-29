package com.yaoyue.concurrent.day03;

import lombok.extern.slf4j.Slf4j;

/**
 * @description: 测试interrupt方法
 * @author: WangDongXu (15224931482)
 * @date: 2021/1/29 15:11
 **/
@Slf4j(topic = "c.Test01")
public class Test01 {

    public static void main(String[] args) throws InterruptedException {
        test2();
    }

    /**
     * 测试打断正在睡眠的线程
     * @throws InterruptedException
     */
    public static void test1() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            log.info("线程t1启动");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                log.info("线程t1被打断");
                e.printStackTrace();
            }
        }, "t1");

        t1.start();
        Thread.sleep(1000);
        log.info("打断线程...");
        t1.interrupt();
        // 打断睡眠中的线程，会置空打断标记，此时打断标记为false
        log.info("打断标记：{}", t1.isInterrupted());
    }

    public static void test2() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            // 给线程留时间料理后事
            while(true) {
                // 获取当前线程的打断标记
                boolean flag = Thread.currentThread().isInterrupted();
                // 运行中的线程被打断，打断标记为true，此时可以通过打断标记来判断是否终止当前线程
                if(flag) {

                    break;
                }
            }
        }, "t1");

        t1.start();
        Thread.sleep(1000);
        log.info("打断线程");
        // 执行打断之后，程序并没有结束，说明此时并没有真正终止被打断的线程
        t1.interrupt();
        // 运行中的线程被打断，打断标记为true
        log.info("打断标记：{}", t1.isInterrupted());
    }
}
