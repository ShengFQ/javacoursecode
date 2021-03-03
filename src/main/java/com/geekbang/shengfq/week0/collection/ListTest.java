package com.geekbang.shengfq.week0.collection;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import com.google.common.collect.Lists;

/**
 * list集合操作案例
 * 2021-03-03
 * 1.拆分list为小块
 * 2.list集合查找
 *
 * */
@Slf4j
public class ListTest {

    public static void main(String[] args) {
        List<String> groupList=new ArrayList<>();
        for (int i=0;i<100;i++){
            groupList.add("element"+i);
        }
        List<List<String>> part=splitList(groupList,20);
        log.info("part size {}",part.size());
        part.forEach(e-> System.out.println(e));
    }
    /**
     * 拆分list为小块
     * **/
    private static List<List<String>> splitList(List<String> list , int groupSize){
        int length = list.size();
        // 计算可以分成多少组
        //int num =length % groupSize == 0 ? length / groupSize : length / groupSize +1;
        //or
        int num = ( length + groupSize - 1 )/groupSize ;
        List<List<String>> newList = new ArrayList<>(num);
        for (int i = 0; i < num; i++) {
            // 开始位置
            int fromIndex = i * groupSize;
            // 结束位置
            int toIndex = (i+1) * groupSize < length ? ( i+1 ) * groupSize : length ;
            newList.add(list.subList(fromIndex,toIndex)) ;
        }
        return  newList ;
    }
    /**使用guava*/
    private List<List<String>> splitList2(List<String> list , int groupSize){
        return  Lists.partition(list, groupSize);
    }
}
