package com.geekbang.shengfq.week0.collection;

import java.util.*;

/**
 * Set集合测试
 *
 * @author shengfq
 * @date 2021-05-19
 */
public class TestSet {
    public static void main(String[] args) {
        testHashSet();
        System.out.println("=============");
        testTreeSet();
        System.out.println("=============");
        testLinkedHashSet();
        System.out.println("=============");
    }
    /**
     * list是有序集合
     * arraylist底层是数组实现,遍历很快,要修改元素需要重新copy原有数组到新数组copy and write
     * linkedlist底层是链表实现,修改很快,只要在末尾增加一个节点就行.
     * */

    /**
     * set是无重复值集合
     * 没有重复元素的无序集合 hashset
     * 支持null值增加,相同值会覆盖.
     * */
    private static void testHashSet(){
        Set<Integer> hashSet=new HashSet<>();
        for (int i = 0; i < 100; i++) {
            hashSet.add(i);
        }
        System.out.println("add complete 0-99");
        for (int i = 99; i >0; i--) {
            hashSet.add(i);
        }
        System.out.println("add null value");
        hashSet.add(null);
        System.out.println("HashSet elements:"+hashSet.size());
        hashSet.forEach((x)-> System.out.println("element:"+x));
    }
    /**
     * treeset是无重复有序集合
     * 添加null值会抛异常
     * */
    private static void testTreeSet(){

        Set<Integer> treeSet=new TreeSet<>();
        for (int i = 0; i < 100; i++) {
            treeSet.add(i);
        }
        for (int i = 99; i >0; i--) {
            treeSet.add(i);
        }
        //treeSet.add(null);
        System.out.println("TreeSet elements:"+treeSet.size());
        treeSet.forEach((x)-> System.out.println("element:"+x));
    }
    /**
     * LinkedHashset是无重复有序集合
     * */
    private static void testLinkedHashSet(){

        Set<Integer> linkedHashSet=new LinkedHashSet<>();
        for (int i = 0; i < 100; i++) {
            linkedHashSet.add(i);
        }
        for (int i = 99; i >0; i--) {
            linkedHashSet.add(i);
        }
        linkedHashSet.add(null);
        System.out.println("LinkedHashSet elements:"+linkedHashSet.size());
        linkedHashSet.forEach((x)-> System.out.println("element:"+x));
    }

    /**
     * sortedSet是接口
     * */
    private static void testSortedSet(){

    }
}
