package com.geekbang.shengfq.week6;
/**
 * 函数式接口
 * 1.只能有一个方法
 * @author shengfq
 * @date 2021-02-19
 * */
interface MathOperation<T> {
        T operation(int a, int b); // 返回类型+函数名+参数类型的列表

    }
    