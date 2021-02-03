package com.geekbang.shengfq.week3.class7;

/**
 * 多线程下信号量测试
 */
public class TestSemaphore {

    public static void main(String[] args) {
        SemaphoreCounter semaphoreCounter = new SemaphoreCounter();
        for (int i = 0; i < 100; i++) {
            Thread t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    semaphoreCounter.incrAndGet();
                }
            });
            Thread t2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    semaphoreCounter.getSum();
                }
            });

            t1.start();
            t2.start();
        }
        System.out.println("main is over");
    }
}
