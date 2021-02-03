package com.geekbang.shengfq.week3.class7;

import java.util.concurrent.Semaphore;

/**
 * JUC高级编程:
 * 信号量
 * 作用:控制线程的执行数量
 * 1.准入数量N
 * 2.N=1 则等价于独占锁
 * 场景:同一时间控制并发线程数,防止海量线程冲垮CPU性能
 * 这里的读锁和写锁是根据业务场景来使用的.
 * */
public class SemaphoreCounter {
    private int sum=0;
    //读锁最多100
    private Semaphore readSem=new Semaphore(100,true);
    //写锁独占
    private Semaphore writSem=new Semaphore(1);
    public int incrAndGet(){
        try{
            writSem.acquireUninterruptibly();
            Thread.sleep(100);
            return ++sum;
        }catch(InterruptedException e){
            e.printStackTrace();
        }finally {
            System.out.println("incrAndGet sum:"+sum);
            writSem.release();
        }
        return 0;
    }

    public int getSum(){
        try{
            readSem.acquireUninterruptibly();
            Thread.sleep(200);
            return sum;
        }catch(InterruptedException e){
            e.printStackTrace();
        }finally {
            System.out.println("getSum sum:"+sum);
            readSem.release();
        }
        return 0;
    }
}
