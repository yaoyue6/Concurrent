package com.yaoyue.concurrent.day05;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 测试局部变量在多线程中是否安全
 * @author: WangDongXu (15224931482)
 * @date: 2021/2/21 19:33
 **/
public class TestThreadSafe {

    static final int THREAD_NUMBER = 2;
    static final int LOOP_NUMBER = 200;

    public static void main(String[] args) {
        ThreadUnSafe threadUnSafe = new ThreadUnSafe();
        for (int i = 0; i < THREAD_NUMBER; i++) {
            new Thread(() -> {
                threadUnSafe.method1(LOOP_NUMBER);
            }, "Thread" + (i + 1)).start();
        }
    }
}

class ThreadUnSafe {
    /**
     * 成员变量，空间在堆中分配，在栈中调用方法时共享，因此是线程不安全的
     */
    List<String> list = new ArrayList<>();

    public void method1(int loopNumber) {
        for (int i = 0; i < loopNumber; i++) {
            method2();
            method3();
        }
    }

    private void method2() {
        list.add("1");
    }

    private void method3() {
        list.remove(0);
    }
}

class ThreadSafe {
    public void method1(int loopNumber) {
        // 在栈帧上定义，并且没有发生逃逸因此是线程安全的
        List<String> list = new ArrayList<>();
        for (int i = 0; i < loopNumber; i++) {
            method2(list);
            method3(list);
        }
    }

    public void method2(List<String> list) {
        list.add("1");
    }

    public void method3(List<String> list) {
        list.remove(0);
    }
}

/**
 * 子类重写父类中的方法，导致线程安全问题
 */
class ThreadSafeSubClass extends ThreadSafe {
    @Override
    public void method3(List<String> list) {
        // 此时子类中另开一个线程，而父类无法直接对子类进行约束，因此子类重写父类方法，多线程同时操作共享资源，导致线程不安全问题。
        // 解决方法：将不对外开放的method2和method3方法访问权限修饰符变为private，将method1方法变为final修饰
        new Thread(() -> {
            list.remove(0);
        }).start();
    }
}