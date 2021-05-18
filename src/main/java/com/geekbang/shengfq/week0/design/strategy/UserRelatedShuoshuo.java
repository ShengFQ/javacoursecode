package com.geekbang.shengfq.week0.design.strategy;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("userRelatedShuoshuo")
@RelatedTypeAnnotation( value = UserRelatedType.SHUOSHUO )
public class UserRelatedShuoshuo implements UserRelated {
    @Override
    public List<String> list(String query) {
        System.out.println("我的说说！");
        return Lists.newArrayList(query);
    }
}
