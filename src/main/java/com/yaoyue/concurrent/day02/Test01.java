package com.yaoyue.concurrent.day02;

import lombok.extern.slf4j.Slf4j;

/**
 * @description: 测试线程休眠
 * @author: WangDongXu (15224931482)
 * @date: 2021/1/28 19:35
 **/
@Slf4j(topic = "c.Test01")
public class Test01 {

    public static void main(String[] args) throws InterruptedException {
        Runnable target = () -> {
            try {
                // 睡眠之后从Runnable状态变化为TIME_WAITING状态
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        Thread t = new Thread(target);

        // 不调用start方法为NEW状态
        t.start();
        // 获取当前线程的运行状态
        log.info("state: {}", t.getState());

        Thread.sleep(1000);
        log.info("state: {}", t.getState());
    }
}
