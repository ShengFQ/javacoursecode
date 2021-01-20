package com.geekbang.shengfq.week1;

public class ForLoopTest {
    private static int[] numbers={1,6,8};

    public static void main(String[] args) {
        MovingAverage ma=new MovingAverage();
        for (int num:numbers){
            ma.submit(num);
        }
        double avg=ma.getAvg();
    }
}
