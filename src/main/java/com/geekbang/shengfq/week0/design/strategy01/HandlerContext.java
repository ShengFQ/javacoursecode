package com.geekbang.shengfq.week0.design.strategy01;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 抽象工厂,
 * */
@Component
public class HandlerContext {

	@Autowired
	private ApplicationContext beanFactory;

	public  AbstractHandler getInstance(Integer type){

		Map<Integer,Class> map = (Map<Integer, Class>) beanFactory.getBean(OrderHandlerType.class.getName());

		return (AbstractHandler)beanFactory.getBean(map.get(type));
	}

}
