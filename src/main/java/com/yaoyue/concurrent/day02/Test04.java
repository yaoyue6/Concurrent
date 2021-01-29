package com.yaoyue.concurrent.day02;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @description: join方法：当前线程等待调用方线程执行结束
 * @author: WangDongXu (15224931482)
 * @date: 2021/1/28 20:39
 **/
@Slf4j(topic = "c.Test04")
public class Test04 {

    public static void main(String[] args) throws InterruptedException {
        test2();
    }

    static int num = 1;

    public static void test1() {
        log.info("开始");
        Thread t = new Thread(() ->  {
            log.info("开始");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            num = 10;
            log.info("结束");
        });

        t.start();
        log.info("{}", num);
        log.info("结束");
    }

    public static void test2() throws InterruptedException {
        log.info("开始");
        Thread t2 = new Thread(() -> {
            log.info("开始");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            num = 5;
            log.info("结束");
        });

        t2.start();
        // 当前线程等待调用方所在线程执行结束
        t2.join();

        // t2.join(1000); 等待1s，超过时间则不再等待，如果时间之内已经执行完成，则以执行时间为准
        log.info("{}", num);
        log.info("结束");
    }
}
