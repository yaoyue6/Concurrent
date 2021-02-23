package com.yaoyue.concurrent.day05;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;

/**
 * @description: 转账练习
 * @author: WangDongXu (15224931482)
 * @date: 2021/2/21 21:18
 **/
@Slf4j(topic = "c.TransferMoney")
public class TransferMoney {

    public static void main(String[] args) throws InterruptedException {
        Account a = new Account(1000);
        Account b = new Account(1000);


        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                a.transfer(b, randomAccount());
            }
        }, "t1");

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                b.transfer(a, randomAccount());
            }
        }, "t2");

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        // 查看剩余总金额
        log.info("剩余总金额：{}", a.getMoney() + b.getMoney());
    }


    static Random random = new Random();

    /**
     * 随机转账金额
     *
     * @return
     */
    public static int randomAccount() {
        return random.nextInt(100) + 1;
    }
}

/**
 * 账户
 */
class Account {
    private int money;

    public Account(int money) {
        this.money = money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getMoney() {
        return money;
    }

    /**
     * 转账  临界区
     *
     * @param target
     * @param amount
     */
    public void transfer(Account target, int amount) {
        // 此时不能直接在方法上加锁，只保护了this的成员变量，而没有保护target的成员变量
        synchronized (Account.class) {
            if (this.money >= amount) {
                setMoney(this.getMoney() - amount);
                target.setMoney(target.getMoney() + amount);
            }
        }
    }
}
