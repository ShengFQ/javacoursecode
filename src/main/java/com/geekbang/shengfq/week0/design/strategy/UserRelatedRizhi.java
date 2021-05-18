package com.geekbang.shengfq.week0.design.strategy;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("userRelatedRizhi")
@RelatedTypeAnnotation( value = UserRelatedType.RIZHI )
public class UserRelatedRizhi implements UserRelated {
    @Override
    public List<String> list(String query) {
        System.out.println("我的日志！");
        return Lists.newArrayList(query);
    }
}
