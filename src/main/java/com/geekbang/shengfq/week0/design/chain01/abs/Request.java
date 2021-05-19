package com.geekbang.shengfq.week0.design.chain01.abs;

import com.alibaba.fastjson.JSONObject;

/**
 * 请求的抽象
 *
 * @author shengfq
 * @date 2021-05-19
 */
public interface Request {
    //请求的方法/参数/url 都是可以自定义的参数
     String methodName();

     String url();

     JSONObject params();

}
