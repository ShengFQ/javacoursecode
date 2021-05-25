package com.geekbang.shengfq.week0.design.strategy02;

import com.geekbang.shengfq.week0.design.strategy02.services.AStrategy;
import com.geekbang.shengfq.week0.design.strategy02.services.BStrategy;
import com.geekbang.shengfq.week0.design.strategy02.services.CStrategy;
import com.geekbang.shengfq.week0.design.strategy02.services.DStrategy;

/**
 * 测试策略模式
 客户端 （Client） 会创建一个特定策略对象并将其传递给上下文。 上下文则会提供一个设置器以便客户端在运行时替换相关联的策略。
 * @author
 * @date
 */
public class TestStrategy {

    /**
     * 静态策略处理
     * client
     * */
    public static void calculate(){
        float a=1.0f,b=2.0f,c=1;
        Context context=new Context();
        Strategy strategyA=new AStrategy();
        context.setStrategy(strategyA);
        c=context.calculate(a,b);
        System.out.println("result:"+c);
        Strategy strategyB=new BStrategy();
        context.setStrategy(strategyB);
        c=context.calculate(a,b);
        System.out.println("result:"+c);
        Strategy strategyC=new CStrategy();
        context.setStrategy(strategyC);
        c=context.calculate(a,b);
        System.out.println("result:"+c);
        Strategy strategyD=new DStrategy();
        context.setStrategy(strategyD);
        c=context.calculate(a,b);
        System.out.println("result:"+c);
    }

    /**
     * Lambda表达式实现策略对象
     *
     * */
    public static void lambdaCal(){
        float x=1.0f,y=2.0f,z=1;
        Context context=new Context();
        Strategy add=(a,b)->{return a+b;};
        Strategy min=(a,b)->{return a-b;};
        Strategy mul=(a,b)->{return a*b;};
        Strategy div=(a,b)->{return a/b;};
        context.setStrategy(add);
        z=context.calculate(x,y);
        System.out.println("result:"+z);
        context.setStrategy(min);
        z=context.calculate(x,y);
        System.out.println("result:"+z);
        context.setStrategy(mul);
        z=context.calculate(x,y);
        System.out.println("result:"+z);
        context.setStrategy(div);
        z=context.calculate(x,y);
        System.out.println("result:"+z);

    }

    public static void main(String[] args) {
        calculate();
        lambdaCal();
    }
}
