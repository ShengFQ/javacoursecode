package com.geekbang.shengfq.week0.design.strategy02;

/**
 * 接口是所有具体策略的通用接口， 它声明了一个上下文用于执行策略的方法
 * liangge
 * @author
 * @date
 */
public interface Strategy {
    /**
     * 两个数的运算
     * */
    float execute(float numbA,float numbB);
}
