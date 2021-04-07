package com.geekbang.shengfq.week0.general;

import java.io.Serializable;
import java.util.List;

/**
 * 泛型接口实现类
 * */
public class BaseService<T> implements IBaseService<T> {

    @Override
    public void save(T o) {
        System.out.println("save o"+o.getClass());
        System.out.println("save o"+o.toString());
    }

    @Override
    public void delete(T o) {
        System.out.println("delete T");
    }

    @Override
    public void update(T o) {
        System.out.println("update T");
    }

    @Override
    public List<T> selectAll(T o) {
        System.out.println("selectAll T");
        return null;
    }

    @Override
    public T getOneById(Serializable id) {
        System.out.println("getOneById T");
        return null;
    }
}
