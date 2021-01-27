package com.yaoyue.concurrent.day01;

import lombok.extern.slf4j.Slf4j;

/**
 * @description: 继承Thread类创建线程，线程和任务合在一起
 * @author: WangDongXu (15224931482)
 * @date: 2021/1/27 20:24
 **/
@Slf4j(topic = "c.Test01")
public class Test01 {

    public static void main(String[] args) {
        // 也可在此处命名
        Thread t = new Thread("thread1") {
            // 此处是重写Thread的run方法
            @Override
            public void run() {
                log.info("running");
            }
        };
        t.start();
        // 为线程命名，以最后一次命名为准
        t.setName("thread");

        log.info("running");
    }
}
