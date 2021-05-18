package com.geekbang.shengfq.week0.design.strategy01.mq;

import java.io.Serializable;
import java.util.List;
/**
 * 消息序列化DO
 * */
public class MessageBean implements Serializable {
    private static final long serialVersionUID = 5454831432308782668L;
    private String cachKey;
    private List<Integer> type;
    private String orderBO;

    public MessageBean(List<Integer> type, String orderBO) {
        this.type = type;
        this.orderBO = orderBO;
    }

    public String getCachKey() {
        return cachKey;
    }

    public void setCachKey(String cachKey) {
        this.cachKey = cachKey;
    }

    public List<Integer> getType() {
        return type;
    }

    public void setType(List<Integer> type) {
        this.type = type;
    }

    public String getOrderBO() {
        return orderBO;
    }

    public void setOrderBO(String orderBO) {
        this.orderBO = orderBO;
    }
}
