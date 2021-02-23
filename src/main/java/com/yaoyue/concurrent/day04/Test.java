package com.yaoyue.concurrent.day04;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "c.Test")
public class Test {

    static int count = 0;

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread("increase") {

            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    count++;
                }
            }
        };

        Thread t2 = new Thread("decrease") {

            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    count--;
                }
            }
        };

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        log.info("{}", count);
    }
}
