package com.geekbang.shengfq.week6;

import com.alibaba.fastjson.JSON;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * java8 stream编程
 * feature
 *
 * @author shengfq
 * @date 2021-02-22
 * */
public class StreamOperationDemo {

    public static void main(String[] args) {
       /* List<String> strList= Arrays.asList("1","2","3","4","5","6");
        List<Integer> list = Arrays.asList(4,2,3,5,1,2,2,7,6);
        optional(list);
        print(list);
        chain(list);*/
    }

    /**
     * 注意是泛型集合
     * stream.map()
     * stream.collect()
     * list toArray()
     * */
    private static void print(List<Integer> list){
       String elements=String.join(",",list.stream().map(i -> i.toString()).collect(Collectors.toList()).toArray(new String[]{}));
        System.out.println(elements);
    }
    private static void print(Object obj) {
        System.out.println(JSON.toJSONString(obj));
    }

    /**
     * 终止操作
     * 直接返回数据结果
     * */
    private static void optional(List<Integer> list){
        Optional<Integer> first=list.stream().findFirst();
        System.out.println("collections:"+first.map(i->1+i).orElse(-1));

        String append=list.stream().map(i->i.toString()).sorted().reduce("0",(x,y)->x+""+y);
        System.out.println("appends:"+append);

        int sum=list.stream().filter(i->i<4).distinct().reduce(0,(a,b)->a+b);
        System.out.println("element min 4 distinct value total sum :"+sum);

        Map map=list.stream().distinct().collect(Collectors.toMap(a->a, a->(a+1)));
        print(map);
    }

    private static void chain(List<Integer> list){
        List squareNums = list.stream().map(n -> n * n)
                .collect(Collectors.toList());
        //从上面例子可以看出，map生成的是个1:1映射，每个输入元素都按照规则转换成为另外一个元素。还有一些场景，是一对多映射关系的，这时需要flatMap，例如：
         Stream inputStream = Stream.of(
                Arrays.asList(1),
                Arrays.asList(2, 3),
                Arrays.asList(4, 5, 6)
        );
        Stream outputStream = inputStream.
                flatMap((childList) -> childList.toString());
    }
}
