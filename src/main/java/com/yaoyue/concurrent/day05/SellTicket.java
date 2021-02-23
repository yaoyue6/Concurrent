package com.yaoyue.concurrent.day05;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;

/**
 * @description: 卖票
 * @author: WangDongXu (15224931482)
 * @date: 2021/2/21 20:43
 **/
@Slf4j(topic = "c.SellTicket")
public class SellTicket {

    public static void main(String[] args) throws InterruptedException {
        // 模拟多人买票
        TicketWindow ticketWindow = new TicketWindow(1000);
        log.info("开始启动...");

        List<Integer> list = new Vector<>();
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 2000; i++) {
            Thread thread = new Thread(() -> {
                // 卖票
                int amount = ticketWindow.sellTicket(randomCount());
                list.add(amount);
            });
            threads.add(thread);
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        // 统计剩余票数
        log.info("余票数量：{}", ticketWindow.getCount());

        // 统计卖出票数
        log.info("卖出票数：{}", list.stream().mapToInt(i -> i).sum());
    }

    static Random random = new Random();

    /**
     * 随机购票数，1~5
     * @return
     */
    public static int randomCount() {
        return random.nextInt(5) + 1;
    }
}

class TicketWindow {
    private int count;

    public TicketWindow(int count) {
        this.count = count;
    }

    /**
     * 获取余票数量
     * @return
     */
    public int getCount() {
        return count;
    }

    /**
     * 卖票  临界区
     * @param amount
     * @return
     */
    public synchronized int sellTicket(int amount) {
        if (count >= amount) {
            count = count - amount;
            return amount;
        }
        return 0;
    }
}
