package com.yaoyue.concurrent.day01;

import lombok.extern.slf4j.Slf4j;

/**
 * @description: 重写Runnable接口创建线程，线程和任务分开
 * @author: WangDongXu (15224931482)
 * @date: 2021/1/27 20:34
 **/
@Slf4j(topic = "c.Test02")
public class Test02 {

    public static void main(String[] args) {
        // lambda简化：Runnable runnable = () -> log.info("running");
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                log.info("running");
            }
        };
        // 创建线程对象
        // 此处执行的是runnable中的run方法
        Thread t = new Thread(runnable, "thread1");
        // 启动线程
        t.start();
        t.setName("thread");
        log.info("running");
    }
}
