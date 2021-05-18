package com.geekbang.shengfq.week0.design.strategy01.service;

import com.geekbang.shengfq.week0.design.strategy01.AbstractHandler;
import com.geekbang.shengfq.week0.design.strategy01.OrderHandlerType;
import org.springframework.stereotype.Component;
/**
 * 策略模式
 * 优点
 *
 * 易于扩展，增加一个新的策略只需要添加一个具体的策略类即可，基本不需要改变原有的代码，符合开放封闭原则
 * 避免使用多重条件选择语句，充分体现面向对象设计思想 策略类之间可以自由切换，由于策略类都实现同一个接口，所以使它们之间可以自由切换
 * 每个策略类使用一个策略类，符合单一职责原则 客户端与策略算法解耦，两者都依赖于抽象策略接口，符合依赖反转原则
 * 客户端不需要知道都有哪些策略类，符合最小知识原则
 * 缺点
 *
 * 策略模式，当策略算法太多时，会造成很多的策略类
 * 客户端不知道有哪些策略类，不能决定使用哪个策略类，这点可以通过封装common公共包解决，也可以考虑使IOC容器和依赖注入的方式来解决。
 *
 * 消息类型枚举值 匹配不同的消息处理器
 * */
@Component
@OrderHandlerType(16)
public class DispatchModeProcessor<String> extends AbstractHandler {


    @Override
    public void handle(Object orderBO) {
        /**
         * 订单完结广播通知(1 - 支付完成)
         */
        System.out.println("订单结完...");

        /**
         *  SCMS 出库单
         */
        System.out.println("SCMS出库单");
    }
}
