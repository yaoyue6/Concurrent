package com.yaoyue.concurrent.day04;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "c.TestState")
public class TestState {

    public static void main(String[] args) {
        // 当前线程创建成功，没有使用start方法启动，NEW
        Thread t1 = new Thread("t1") {

            @Override
            public void run() {
                log.info("running...");
            }
        };

        // 当前线程在运行中，RUNNABLE
        Thread t2 = new Thread("t2") {

            @Override
            public void run() {
                while (true) {

                }
            }
        };

        t2.start();

        // 当前线程执行完毕，TERMINATED
        Thread t3 = new Thread("t3") {

            @Override
            public void run() {
                log.info("running...");
            }
        };

        t3.start();

        // 当前线程沉睡100000ms，TIMED_WAITING
        Thread t4 = new Thread("t4") {

            @Override
            public void run() {
                synchronized (TestState.class) {
                    try {
                        Thread.sleep(100000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        t4.start();

        // 没有时间限制，一直等待t2线程执行完毕，WAITING
        Thread t5 = new Thread("t5") {

            @Override
            public void run() {
                try {
                    t2.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        t5.start();

        // 两个线程竞争锁对象，BLOCKED
        Thread t6 = new Thread("t6") {

            @Override
            public void run() {
                synchronized (TestState.class) {
                    try {
                        Thread.sleep(100000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        t6.start();

        // 当前线程阻塞，其它线程会先于主线程运行完
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        log.info("t1 state -> {}", t1.getState());
        log.info("t2 state -> {}", t2.getState());
        log.info("t3 state -> {}", t3.getState());
        log.info("t4 state -> {}", t4.getState());
        log.info("t5 state -> {}", t5.getState());
        log.info("t6 state -> {}", t6.getState());
    }
}
