package com.geekbang.shengfq.week0.design.strategy01;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
/**
 * 启动类
 * 扫描包下用到目标注解的的类,实例化为单例
 * */
@Component
@SuppressWarnings({"unused","rawtypes"})
public class HandlerProcessor implements BeanFactoryPostProcessor {

	private String basePackage = "com.ecej.order.pipeline.processor";

    public static final Logger log = LoggerFactory.getLogger(HandlerProcessor.class);

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

		Map<Integer,Class> map = new HashMap<Integer,Class>();

		ClassScaner.scan(basePackage, OrderHandlerType.class).forEach(x ->{
			int type = x.getAnnotation(OrderHandlerType.class).value();
			map.put(type,x);
		});

		beanFactory.registerSingleton(OrderHandlerType.class.getName(), map);

		log.info("处理器初始化{}", JSONObject.toJSONString(beanFactory.getBean(OrderHandlerType.class.getName())));
	}
}
