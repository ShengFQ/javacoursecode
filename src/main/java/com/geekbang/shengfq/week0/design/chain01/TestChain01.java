package com.geekbang.shengfq.week0.design.chain01;

import com.geekbang.shengfq.week0.design.chain01.abs.Handler;
import com.geekbang.shengfq.week0.design.chain01.abs.Request;
import com.geekbang.shengfq.week0.design.chain01.request.Arequest;
import com.geekbang.shengfq.week0.design.chain01.service.AHandler;
import com.geekbang.shengfq.week0.design.chain01.service.BHandler;
import com.geekbang.shengfq.week0.design.chain01.service.CHandler;
import com.geekbang.shengfq.week0.design.chain01.service.DHandler;

/**
 * 测试责任链调用
 * 角色:
 * handler接口,所有业务handler的抽象接口,通过setNext(Handler)实现责任链压栈.
 * baseHandler通用处理包含链上下一个处理器引用,实现默认的处理行为:将请求传递到下一个处理者
 * AHandler具体处理者,包含实际处理请求代码,每个处理者都可以决定是否沿着链路传递请求.
 * 客户端 可以根据程序逻辑一下车或者动态的生成调用链,但是不能产生回环调用,否则出现SOE
 * @author shengfq
 * @date 2021-05-19
 */
public class TestChain01 {
    public static void main(String[] args) {
        Request request=new Arequest();
        Handler handlerA=new AHandler();
        Handler handlerB=new BHandler();
        Handler handlerC=new CHandler();
        Handler handlerD=new DHandler();
        //链表循环,出现SOE
       /* handlerA.setNext(handlerD);
        handlerB.setNext(handlerC);
        handlerC.setNext(handlerD);
        handlerD.setNext(handlerB);*/
       handlerA.setNext(handlerB);
       handlerB.setNext(handlerC);
       handlerC.setNext(handlerD);
        handlerA.handle(request);
    }
}
