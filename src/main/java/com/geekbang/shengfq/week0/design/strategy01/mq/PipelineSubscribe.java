package com.geekbang.shengfq.week0.design.strategy01.mq;

import com.alibaba.fastjson.JSONObject;
import com.geekbang.shengfq.week0.design.strategy01.AbstractHandler;
import com.geekbang.shengfq.week0.design.strategy01.HandlerContext;
import com.geekbang.shengfq.week0.design.strategy01.domain.OrderBO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

/**
 * rabbitmq的订阅器
 * 流量dispatch入口
 * */
@Component
@RabbitListener(queues = "OrderPipelineQueue")
public class PipelineSubscribe{

	private final Logger LOGGER = LoggerFactory.getLogger(PipelineSubscribe.class);

	@Autowired
	private com.geekbang.shengfq.week0.design.strategy01.HandlerContext HandlerContext;

    @RabbitHandler
    public void subscribeMessage(MessageBean bean){

    	OrderBO orderBO = JSONObject.parseObject(bean.getOrderBO(), OrderBO.class);

    	if(null != orderBO && !CollectionUtils.isEmpty(bean.getType()))
    	{
    		for(int value:bean.getType())
    		{
       		 AbstractHandler handler = HandlerContext.getInstance(value);
       		 handler.handle(orderBO);
    		}
		}
	}
}
