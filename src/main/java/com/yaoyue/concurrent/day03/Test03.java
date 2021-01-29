package com.yaoyue.concurrent.day03;

import com.sun.activation.registries.LogSupport;
import lombok.extern.slf4j.Slf4j;

/**
 * @description:
 * @author: WangDongXu (15224931482)
 * @date: 2021/1/29 16:32
 **/
@Slf4j(topic = "c.Test03")
public class Test03 {

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            log.info("park...");

        });
    }
}
