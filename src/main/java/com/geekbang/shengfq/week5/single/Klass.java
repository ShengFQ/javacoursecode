package com.geekbang.shengfq.week5.single;

/**
 * 一个进程中只能存在唯一一个对象
 * 简单的单例写法
 * @author shengfq
 * @date 2021-02-14
 */
public class Klass {
    private int age = 0;
   // private static final Klass instance=new Klass();
   /*public static Klass getInstance(){
       return instance;
   }*/
    private Klass() {
    }


    private static Klass instance;
    public static  Klass getInstance() {
        if (instance == null) {
            //不在方法上加锁而在new的时候才加锁，减少锁的代码，然而这种方式并不行
            synchronized (Klass.class) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                instance = new Klass();
            }
        }
        return instance;
    }

    /**
     * 非原子性操作
     * 加锁同步访问
     */
    public int increase() {
        return age++;
    }
}