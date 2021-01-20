package com.geekbang.shengfq.week1;

public class MovingAverage {
    private int count=0;
    private double sum=0.0d;

    public void submit(double value){
        count++;
        sum+=value;
    }

    public double getAvg(){
        if(0==this.count){
            return sum;
        }
        return this.sum/this.count;
    }

    public static void main(String[] args) {
        MovingAverage ma=new MovingAverage();
        int num1=1;
        int num2=2;
        ma.submit(num1);
        ma.submit(num2);
        double avg=ma.getAvg();
    }
}
