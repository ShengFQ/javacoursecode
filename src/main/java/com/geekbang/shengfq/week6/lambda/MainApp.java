package com.geekbang.shengfq.week6.lambda;
/**
 * Lambda表达式
 *  @author sheng
 *  * @date 2021-03-18
 * */
public class MainApp {
    public static void main(String[] args) {

        //消费者自定义个实例变量,实现的doSomething方法为打印这个参数
        MyLambda myLambda=s-> System.out.println(s);
        MyLambda myLambda2=s->{String ss=s+s;
            System.out.println(ss);
        };
        enact(myLambda,"hello");
        enact(myLambda2,"hi");
    }

    /**
     * 1.表达式基本语法
     * 1. 不需要参数,给返回值.Supplier 函数 例如:()->5
     * 2.接收一个参数,返回一个值.Function 函数 例如: x->2*x
     * 3.接收两个参数,返回一个值. Function 函数 例如: (x,y) ->x-y
     * 4.接收一个参数,不返回值.Consumer 函数 例如:(String s) -> System.out.print(s)
     * 5.接收一个参数,返回一个bool.Predicate 函数 例如:(bool s) -> s
     * */


    /**
     * lambda表达式的应用场景:
     * 一.框架中定义的行为
     * */
    public static void enact(MyLambda myLambda,String s){
        myLambda.doSomething(s);
    }
    /**
     * 框架中的占位符
     * */
    interface MyLambda{
        void doSomething(String s);
    }

}
