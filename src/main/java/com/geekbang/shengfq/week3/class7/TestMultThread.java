package com.geekbang.shengfq.week3.class7;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;
/**
 * class7作业:执行一个子线程完成后,主线程拿到结果,再结束.
 * @author sheng
 * @date 2021-02-05
 * 共验证11种方式
 * */
public class TestMultThread {
    private static int result = 0;
    private static Object oo = new Object();

    //
    public static void main(String[] args) throws Exception {
        TestMultThread testThead=new TestMultThread();
        method11();
        System.out.println("main is over");
    }

    /**
     * method 1
     * t1.wait();主线程主动阻塞等待,子线程先运行,通过t1.notifyAll()通知主线程可以继续执行.
     */
    private  void method1() throws Exception {
        final Object object = oo;
        MyTask task=new MyTask();
        MyThread t1 = new MyThread("t1",this,task);
        t1.start();
        synchronized (t1){
            t1.wait();
        }
        result=t1.getResult();
        System.out.println("result:"+result);
    }



    /**
     * 方法2.使用线程池+submit+callable
     *=>Future
     */
    private static void method2() throws Exception {
        ExecutorService pool1 = Executors.newFixedThreadPool(1);

        Future<Integer> result = pool1.submit(new Callable<Integer>() {
            @Override
            public Integer call() {
                MyTask task=new MyTask();
                return task.sum();
            }
        });
        System.out.println("return value:" + result.get());

    }

    /**
     * 方法3.使用线程池+submit+callable=>Future
     */
    private static void method3() throws Exception {
        ExecutorService pool1 = Executors.newSingleThreadExecutor();
        Future<Integer> result = pool1.submit(new Callable<Integer>() {
            @Override
            public Integer call() {
                MyTask task=new MyTask();
                return task.sum();
            }
        });
        System.out.println("return value:" + result.get());

    }
    /**
     * 方法4.使用futureTask封装异步执行结果
     */
    private static void method4() throws Exception {
        FutureTask<String> futureTask = new FutureTask<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "hello world";
            }
        });
        Thread t1 = new Thread(futureTask);
        t1.start();
        synchronized (t1){
            t1.join();
        }
        System.out.println("return value:" + futureTask.get());

    }
    /**
     * 方法5  线程池+execute+FutureTask
     */
    private static void method5() throws Exception {
        FutureTask<Integer> futureTask = new FutureTask<>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                MyTask task=new MyTask();
                return task.sum();
            }
        });
        ExecutorService pool1 = Executors.newSingleThreadExecutor();
        pool1.execute(futureTask);
        System.out.println("return value:" + futureTask.get());

    }

    /**
     * 方法6 线程池+submit+FutureTask=>
     */
    private static void method6() throws Exception {
        FutureTask<Integer> futureTask = new FutureTask<>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                MyTask task=new MyTask();
                return task.sum();
            }
        });
        ExecutorService pool1 = Executors.newSingleThreadExecutor();
        pool1.submit(futureTask);
        System.out.println("return value:" + futureTask.get());
    }
    /**
     * 方法7 使用线程池+submit+runnable=>Future
     * 并没有实现阻塞主线程等待worker线程
     */
    private static void method7() throws Exception {
        final Map<String,Integer> resultMap=new HashMap<>();
        ExecutorService pool1 = Executors.newSingleThreadExecutor();
        Future<String> result = (Future<String>) pool1.submit(new Runnable() {
            @Override
            public void run() {
                MyTask task=new MyTask();
                resultMap.putIfAbsent("a",task.sum());
            }
        });
        pool1.shutdown();
        System.out.println("return value:" + resultMap.get("a"));

    }
    /**
     * method8 [OK]
     * t1.join() 主线程阻塞,释放t1锁,t1运行.
     */
    private static void method8() throws Exception {
        MyTask task=new MyTask();
        Thread t1 = new Thread(() -> {
            result=task.sum();
        });
        t1.start();
        synchronized (t1){
            t1.join();//当前线程阻塞,会释放oo对象锁
        }
        System.out.println("result:"+result);
    }
    /**
     * method9
     * 同步队列存储共享变量
     * 信号量CountDownLatch线程排序
     * */
    private static void method9()throws Exception{
        final Map<String, Integer> resultMap=new HashMap<>();
        final CountDownLatch endLatch = new CountDownLatch(1);
        MyTask myTask=new MyTask();
        Runnable task=new Runnable() {
            @Override
            public void run() {
                int result=   myTask.sum();
                resultMap.putIfAbsent("a",result);
                endLatch.countDown();
            }
        };
        new Thread(task).start();
        try{
            endLatch.await();
            System.out.println(resultMap.get("a"));
        }catch(Exception e){
            e.printStackTrace();
        }

    }


    /**
     * method 10
     * oo.wait();主线程主动阻塞等待,子线程先运行,通过oo.notifyAll()通知主线程可以继续执行.
     * 缺点:wait,notify无法确定先后顺序,如果子线程先执行
     */
    private  void method10() throws Exception {
        final Object object = oo;
        MyTask task=new MyTask();
        MyThread t1 = new MyThread("t2",oo,task);
        t1.start();
        synchronized (oo){
            oo.wait(100);
        }
        result=t1.getResult();
        System.out.println("result:"+result);
    }

    /**
     * 使用CyclicBarrier实现并发排队
     * */
    private static void method11() throws Exception{
        final Map<String, Integer> resultMap=new HashMap<>();
        MyTask myTask=new MyTask();
        Runnable runnable=new Runnable() {
            @Override
            public void run() {
                int result=   myTask.sum();
               // System.out.println("result:"+result);
                resultMap.putIfAbsent("a",result);
            }
        };
        final CyclicBarrier cyclicBarrier = new CyclicBarrier(1,runnable);

        new Thread(runnable).start();
        try{
            cyclicBarrier.await();//线程阻塞
            System.out.println(resultMap);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
/**
 * 任务定义
* **/
class MyTask{
    /**
     * 任务主体
     */
    public  int sum() {
        return fibo(36);
    }

    private  int fibo(int a) {
        if (a < 2)
            return 1;
        return fibo(a - 1) + fibo(a - 2);
    }


}
/**
 * 线程定义
 */
class MyThread extends Thread {
    private volatile int result = 0;
    private String name;
    private Object oo;
    private MyTask task;
    public void setOo(Object oo) {
        this.oo = oo;
    }

    public int getResult(){
        return result;
    }

    public MyThread(String name,Object monitor,MyTask task) {
        this.name = name;
        this.task=task;
        this.oo=monitor;
    }

    @Override
    public void run() {
       /* try {
            Thread.sleep(100);
        }catch(InterruptedException e){
            e.printStackTrace();
        }*/
        synchronized (oo) {
            result = task.sum();
            oo.notifyAll();
        }
    }

}