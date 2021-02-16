package com.geekbang.shengfq.week5.single;
/**
 * 懒汉+局部锁
 * 线程不安全
 * @author shengfq
 * */
public class Singleton_5 {
    private static Singleton_5 INSTANCE;

    /**
     * 私有的构造方法，其他地方不能new
     */
    private Singleton_5() {
    }

    public static Singleton_5 getInstance() {
        if (INSTANCE == null) {
            //不在方法上加锁而在new的时候才加锁，减少锁的代码，然而这种方式并不行
            synchronized (Singleton_5.class) {
                // 测试，sleep一下，增加被其他线程打断的机会
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                INSTANCE = new Singleton_5();
            }
        }
        return INSTANCE;
    }

    /**
     * for test
     */
    public static void main(String[] args) {
        //同一个类的不同对象的hashcode不同
        //跑100个线程，看看有没有不同的实例
        for (int i = 0; i < 100; i++) {
            new Thread(() -> System.out.println(Singleton_5.getInstance().hashCode())).start();
        }
    }
}