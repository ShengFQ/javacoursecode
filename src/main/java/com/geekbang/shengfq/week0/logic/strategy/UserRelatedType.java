package com.geekbang.shengfq.week0.logic.strategy;

//各个 tab 名称的枚举：
public enum UserRelatedType {
    /**
     * 说说
     */
    SHUOSHUO("说说"),

    /**
     * 日志
     */
    RIZHI("日志"),

    /**
     * 发布
     */
    ZHAOPIAN("照片"),

    /**
     * 访客
     */
    FANGKE("");

    private String desc;

    UserRelatedType(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
