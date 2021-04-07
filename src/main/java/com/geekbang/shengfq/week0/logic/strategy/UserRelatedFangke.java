package com.geekbang.shengfq.week0.logic.strategy;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("userRelatedFangke")
@RelatedTypeAnnotation( value = UserRelatedType.FANGKE )
public class UserRelatedFangke implements UserRelated {
    @Override
    public List<String> list(String query) {
        System.out.println("我的访客！");
        return Lists.newArrayList(query);
    }
}
