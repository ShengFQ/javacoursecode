package com.geekbang.shengfq.week6;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.function.Consumer;

/**
 * Lambda表达式实现四则运算
 * @author sheng
 * @date 2021-02-19
 * */
public class LambdaOperaton<T extends Serializable & Collection> {

    /**
     * 函数式接口的多态封装
     * */
    public <T> T operate(int a, int b, MathOperation mathOperation){
        return (T) mathOperation.operation(a,b);
    }
    /**
     * 多态封装打印输出
     * */
    public void prints(String message,PrintService printService){
        printService.print(message);
    }

    public static void main(String[] args) {
       int a=10,b=5;
        String message="hello world";
        LambdaOperaton demo=new LambdaOperaton();
        //此处的Lambda表达式充当了匿名内部类创建对象的作用
         MathOperation opAdd = (x, y) -> x + y;

        System.out.printf("%s + %s =  %s",a,b,demo.operate(a,b,opAdd));
        System.out.println();
        MathOperation opSub=(x,y)->x-y;

        System.out.printf("%s - %s =  %s",a,b,demo.operate(a,b,opSub));
        System.out.println();
        MathOperation opMul=(x,y)->x*y;

        System.out.printf("%s * %s =  %s",a,b,demo.operate(a,b,opMul));
        System.out.println();
        MathOperation opDevi=(x,y)->x/y;

        System.out.printf("%s / %s =  %s",a,b,demo.operate(a,b,opDevi));
        System.out.println();
        //此处Lambda表达式
        PrintService printService1=(x) -> System.out.println(x);
        demo.prints(message,printService1);
        System.out.println();

        PrintService printService2=(x) ->{
            System.out.println(x);
        };
        demo.prints(message,printService2);
        System.out.println();

        Arrays.asList(1,2,3).forEach(x -> System.out.println(x+3));
        Arrays.asList(1,2,3).forEach( LambdaOperaton::println );
        demo.myPrintln(x -> System.out.println(x),2);
    }

    private  void myPrintln(Consumer consumer,T t){
        consumer.accept(t);
    }

    private static void println(int x) {
        System.out.println(x+3);
    }

    interface PrintService{
        /**
         * 输出一句话
         * */
        void print(String message);
    }

}
