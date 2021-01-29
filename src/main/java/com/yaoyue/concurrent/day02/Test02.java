package com.yaoyue.concurrent.day02;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @description: 测试打断休眠
 * @author: WangDongXu (15224931482)
 * @date: 2021/1/28 19:36
 **/
@Slf4j(topic = "c.Test02")
public class Test02 {

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread() {
            @Override
            public void run() {
                log.info("enter sleeping...");
                try {
                    Thread.sleep(2000);
                    // 等价于：TimeUnit.SECONDS.sleep(2); 建议采用此方法，更简洁一些
                } catch (InterruptedException e) {
                    log.info("weak up");
                    e.printStackTrace();
                }
            }
        };

        t1.start();
        Thread.sleep(1000);
        log.info("interrupt...");
        // 使用interrupt方法打断正在睡眠的线程，此时sleep方法会抛出 java.lang.InterruptedException: sleep interrupted
        t1.interrupt();
    }
}
