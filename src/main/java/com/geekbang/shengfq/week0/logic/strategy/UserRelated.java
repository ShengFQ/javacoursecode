package com.geekbang.shengfq.week0.logic.strategy;

import java.util.List;

public interface UserRelated {

    /**
     * 列出详细信息
     *
     * @param query
     * @return
     */
    List<String> list(String query);
}
