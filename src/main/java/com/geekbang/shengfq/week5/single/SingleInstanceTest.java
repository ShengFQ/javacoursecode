package com.geekbang.shengfq.week5.single;

/**
 * 单例的N种写法
 * @author shengfq
 * @date 2021-02-14
 * */
public class SingleInstanceTest {
    public static void main(String[] args) {
        /*Klass instance1=Klass.getInstance();
        Klass instance2=Klass.getInstance();
        if(instance1==instance2){
            System.out.println("instance1==instance2");
        }else{
            System.out.println("not equals...");
        }*/

        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Klass instance=Klass.getInstance();
                    System.out.println(instance.hashCode());
                }
            }).start();
        }

        System.out.println("main is over");
    }


}
