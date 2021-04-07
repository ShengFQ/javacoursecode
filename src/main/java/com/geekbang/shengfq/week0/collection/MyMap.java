package com.geekbang.shengfq.week0.collection;
/**
 * 自定义泛型接口
 * map接口定义行为
 * */
public interface MyMap<K,V> {
    V put(K key,V value);

    V get(K key);

    int size();

    public interface Entry<K,V>{
        K getKey();
        V getValue();
    }
}
