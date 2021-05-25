package com.geekbang.shengfq.week0.design.strategy02.services;

import com.geekbang.shengfq.week0.design.strategy02.Strategy;
import org.springframework.util.NumberUtils;

/**
 * 具体策略 （Concrete Strategies） 实现了上下文所用算法的各种不同变体。
 * 除法
 * @author
 * @date
 */
public class DStrategy implements Strategy {
    /**
     * 两个数的运算
     *
     * @param numbA
     * @param numbB
     */
    @Override
    public float execute(float numbA, float numbB) {
        return numbA/numbB;
    }
}
