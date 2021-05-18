package com.geekbang.shengfq.week0.design.strategy;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("userRelatedZhaopian")
@RelatedTypeAnnotation( value = UserRelatedType.ZHAOPIAN )
public class UserRelatedZhaopian implements UserRelated {
    @Override
    public List<String> list(String query) {
        System.out.println("我的照片！");
        return Lists.newArrayList(query);
    }
}
