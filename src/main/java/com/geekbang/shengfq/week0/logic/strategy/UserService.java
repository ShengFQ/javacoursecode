package com.geekbang.shengfq.week0.logic.strategy;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserService {
    public List<String> list(String query){
        UserRelatedType relatedType = UserRelatedType.valueOf(query);
        UserRelated related = UserRelatedFactory.createRelated( relatedType );
        if( related != null ) {
            return related.list( query );
        } else {
            return null;
        }
    }
}
