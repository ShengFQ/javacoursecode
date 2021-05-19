package com.geekbang.shengfq.week0.design.chain01.abs;

/**
 * 定义了所有具体处理者的通用接口
 * @author
 * @date
 */
public interface Handler {
    /**
     * 设置 处理链的下个处理者
     * */
    void setNext(Handler handler);
    /**
     * 请求处理
     * */
    void handle(Request request);
}
