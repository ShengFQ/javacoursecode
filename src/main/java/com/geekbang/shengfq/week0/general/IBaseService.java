package com.geekbang.shengfq.week0.general;

import java.io.Serializable;
import java.util.List;

/**
 * 泛型接口
 * 对一组类的操作抽象,例如数据库的增删改查抽象接口定义
 * @author sheng
 * */
public interface IBaseService<T> {
    void save(T  t);

    void delete(T t);

    void update(T t);

    List<T> selectAll(T t);

    T getOneById(Serializable id);
}
