package com.yaoyue.concurrent.day03;

import lombok.extern.slf4j.Slf4j;

/**
 * @description: 两阶段终止模式，模拟监控程序
 * @author: WangDongXu (15224931482)
 * @date: 2021/1/29 16:06
 **/
@Slf4j(topic = "c.Test02")
public class Test02 {

    public static void main(String[] args) throws InterruptedException {
        TwoPhaseTermination tpt = new TwoPhaseTermination();
        tpt.start();
        Thread.sleep(3500);
        tpt.stop();
    }
}

@Slf4j(topic = "c.TwoPhaseTermination")
class TwoPhaseTermination {

    private Thread monitorThread;

    /**
     * 监控方法
     */
    public void start() {
        monitorThread = new Thread(() -> {
            while (true) {
                // 获取当前线程的打断标记   interrupted()方法和isInterrupted()方法类似，都是打断线程，但interrupted方法会清除打断标记
                boolean flag = Thread.currentThread().isInterrupted();
                if(flag) {
                    log.info("准备后事");
                    break;
                }
                // 监控周期为1s
                try {
                    // 睡眠中被打断会清除打断标记
                    Thread.sleep(1000);
                    // 正常状态被打断，打断标记为true
                    log.info("执行监控操作");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    // 进入此处一定是在睡眠中被打断的，打断标记为false，此时需要重置打断标记为true，否则无法终止循环
                    Thread.currentThread().interrupt();
                }

            }
        }, "t1");
        monitorThread.start();
    }

    /**
     * 终止方法
     */
    public void stop() {
        monitorThread.interrupt();
    }
}