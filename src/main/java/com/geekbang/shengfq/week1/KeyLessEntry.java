package com.geekbang.shengfq.week1;

import java.util.HashMap;
import java.util.Map;
/**
 * 演示内存泄漏导致内存溢出的场景
 * 代码本意是想循环覆盖map的key-value,
 * 但是由于key是引用类型,equals方法被重写了,
 * 导致key的hashcode每次计算都不一样,map内的key没有被覆盖,空间持续扩大,最终导致oom
 * @date 2021-01-14
 * @author sheng
 * */
public class KeyLessEntry {
    static class Key{
        Integer id;
        Key(Integer id){
            this.id=id;
        }

        @Override
        public int hashCode() {
            return id.hashCode();
        }

        /**
         * 如果不重写equals方法,默认的引用对象判断肯定不匹配
         * */
        /*@Override
        public boolean equals(Object o) {
            boolean response=false;
            if(o instanceof Key){
                response=(((Key)o).id).equals(this.id);
            }
            return response;
        }*/
    }
    public static void main(String[] args) {
        Map m=new HashMap();
        while(true){
            for (int i = 0; i < 10; i++) {
                if(!m.containsKey(new Key(i))){
                    m.put(new Key(i),"number:"+i);
                }
            }
            System.out.println("m.size() = "+m.size());
        }
    }
}
