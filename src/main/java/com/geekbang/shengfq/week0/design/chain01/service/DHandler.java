package com.geekbang.shengfq.week0.design.chain01.service;

import com.geekbang.shengfq.week0.design.chain01.abs.BaseHandler;
import com.geekbang.shengfq.week0.design.chain01.abs.Handler;
import com.geekbang.shengfq.week0.design.chain01.abs.Request;

/**
 * 具体处理者D
 *
 * @author shengfq
 * @date
 */
public class DHandler extends BaseHandler implements Handler {
    /**
     * 处理器前置通知
     */
    @Override
   public void preHandle() {
        System.out.println("DHandler preHandle()");
    }

    /**
     * 处理器后置通知
     */
    @Override
   public void afterHandle() {
        System.out.println("DHandler afterHandle() ");
    }

    /**
     * 是否能处理
     *
     * @param request
     */
    @Override
    public boolean canHandle(Request request) {
        return true;
    }

    @Override
    public void handle(Request request) {
        //通用的逻辑判断,具体实现由子类实现
        if(canHandle(request)) {
            preHandle();
            System.out.println("DHandler handle ()"+request.toString());
            afterHandle();
        }else {
            super.handle(request);
        }
    }

}
