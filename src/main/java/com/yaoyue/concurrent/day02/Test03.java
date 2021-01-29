package com.yaoyue.concurrent.day02;

import lombok.extern.slf4j.Slf4j;

/**
 * @description: 测试yield方法，让出时间片，将当前线程从Running状态变为Runnable
 * @author: WangDongXu (15224931482)
 * @date: 2021/1/28 20:09
 **/
@Slf4j(topic = "c.Test03")
public class Test03 {

    public static void main(String[] args) {
        Runnable r1 = () -> {
            int count = 0;
            while(true) {
                log.info("---->1 {}", count++);
            }
        };

        Runnable r2 = () -> {
            int count = 0;
            while(true) {
                // 让出当前线程，将当前线程从运行状态变为就绪状态
                // Thread.yield();
                log.info("      ---->2 {}", count++);
            }
        };

        Thread t1 = new Thread(r1, "t1");
        // 设置线程优先级，最高为10，最低为1
        t1.setPriority(1);
        t1.start();

        Thread t2 = new Thread(r2, "t2");
        t2.setPriority(10);
        t2.start();
    }
}
