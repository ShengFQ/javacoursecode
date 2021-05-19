package com.geekbang.shengfq.week0.design.chain01.request;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.geekbang.shengfq.week0.design.chain01.abs.Request;
import lombok.Data;
import lombok.ToString;

/**
 * 请求A
 *
 * @author shengfq
 * @date 2021-05-19
 */
@Data
@ToString
public class Arequest implements Request {
    @Override
    public String methodName() {
        return "GET";
    }

    @Override
    public String url() {
        return "http://baidu.com";
    }

    @Override
    public JSONObject params() {
        return JSON.parseObject("{'name':'kk'}");
    }
}
