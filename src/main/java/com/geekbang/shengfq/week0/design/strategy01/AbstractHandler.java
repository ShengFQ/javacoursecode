package com.geekbang.shengfq.week0.design.strategy01;
/**
 * 业务抽象行为处理
 * */
public abstract class AbstractHandler<T> {
	abstract public void handle(T orderBO);
}
