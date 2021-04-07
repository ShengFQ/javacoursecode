package com.geekbang.shengfq.week0.general;

import lombok.Data;

/**
 * POJO bean
 * */
@Data
public class UserBean {
    private int id;

    private String name;

    private String pwd;

    public UserBean(int id,String name){
        this.id=id;
        this.name=name;
    }
}
