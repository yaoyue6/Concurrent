package com.yaoyue.concurrent.day01;

import lombok.extern.slf4j.Slf4j;

/**
 * @description: 多线程
 * @author: WangDongXu (15224931482)
 * @date: 2021/1/27 21:38
 **/
@Slf4j(topic = "c.Test04")
public class Test04 {

    public static void main(String[] args) {
        new Thread(() -> {
            while (true) {
                log.info("thread1 is running");
            }
        }, "t1").start();

        new Thread(() -> {
            while (true) {
                log.info("thread2 is running");
            }
        }, "t2").start();
    }
}
