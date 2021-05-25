package com.geekbang.shengfq.week0.design.strategy02;

/**
 * 上下文 （Context） 维护指向具体策略的引用， 且仅通过策略接口与该对象进行交流
 *
 * @author
 * @date
 */
public class Context {
    Strategy strategy;

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public float calculate(float a,float b){
        return strategy.execute(a,b);
    }
}
