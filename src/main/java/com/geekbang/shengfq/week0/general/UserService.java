package com.geekbang.shengfq.week0.general;

import java.io.Serializable;
import java.util.List;

public class UserService implements IBaseService<UserBean> {
    @Override
    public void save(UserBean o) {
        System.out.println("save o"+o.getClass());
        System.out.println("save o"+o.toString());
    }

    @Override
    public void delete(UserBean o) {
        System.out.println("delete UserBean");
    }

    @Override
    public void update(UserBean o) {
        System.out.println("update UserBean");
    }

    @Override
    public List selectAll(UserBean o) {
        System.out.println("selectAll UserBean");
        return null;
    }

    @Override
    public UserBean getOneById(Serializable id) {
        System.out.println("getOneById UserBean");
        return null;
    }
}
