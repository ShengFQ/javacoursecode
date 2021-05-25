package com.geekbang.shengfq.week0.collection;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Queue队列测试
 *
 * @author shengfq
 * @date 2021-05-20
 */
public class TestQueue {
    public static void main(String[] args) {
        testQueue();
    }

    /**
     * queue.peek() 返回最后一个值
     * */
    private static void testQueue(){
        ArrayBlockingQueue<Integer> queue=new ArrayBlockingQueue(100);
        for (int i = 0; i < 99; i++) {
            queue.add(i);
        }
        System.out.println("queue size:"+queue.size());
        for(int i=99;i>50;i--){
            queue.poll();//从头部删除元素
        }
        System.out.println("queue size:"+queue.size());
        queue.forEach((x)-> System.out.println("element:"+x));
    }
}
