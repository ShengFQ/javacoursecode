package com.geekbang.shengfq.week6.stream;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * steam编程
 * 是一种流水线加工的函数,加工的原料是用泛型方式注入的.
 * 1.基本语法
 * 2.高级语法
 * 3.使用场景
 * @author sheng
 * @date 2021-03-18
 * */
public class MainApp {
    static List<Book> books = new ArrayList<>();
    static{
        books.add(new Book(7, "html", 44d, "编程语言", LocalDate.parse("2011-01-06")));
        books.add(new Book(8, "oracle", 150d, "数据库", LocalDate.parse("2013-08-09")));
        books.add(new Book(9, "mysql", 66d, "数据库", LocalDate.parse("2015-04-06")));
        books.add(new Book(10, "ssh", 70d, "编程语言", LocalDate.parse("2016-12-04")));
        books.add(new Book(11, "设计模式", 81d, "其他", LocalDate.parse("2017-04-06")));
        books.add(new Book(12, "重构", 62d, "其他", LocalDate.parse("2012-04-09")));
        books.add(new Book(13, "敏捷开发", 72d, "其他", LocalDate.parse("2016-09-07")));
        books.add(new Book(14, "从技术到管理", 42d, "其他", LocalDate.parse("2016-02-19")));
        books.add(new Book(15, "算法导论", 66d, "其他", LocalDate.parse("2010-05-08")));
        books.add(new Book(16, "oracle", 150d, "数据库", LocalDate.parse("2017-05-08")));
        books.add(new Book(1, "tomcat", 70d, "服务器", LocalDate.parse("2014-05-17")));
        books.add(new Book(2, "jetty", 60d, "服务器", LocalDate.parse("2015-12-01")));
        books.add(new Book(3, "nginx", 65d, "服务器", LocalDate.parse("2016-10-17")));
        books.add(new Book(4, "java", 66d, "编程语言", LocalDate.parse("2011-04-09")));
        books.add(new Book(5, "ruby", 80d, "编程语言", LocalDate.parse("2013-05-09")));
        books.add(new Book(6, "php", 40d, "编程语言", LocalDate.parse("2014-08-06")));

    }
    public static void main(String[] args) {
       // printUrlParams();
        transform();
    }
    /**
     * 一.将一个字符串构造为map集合
     * */
    private static void printUrlParams(){
        String queryString = "itemId=1&userId=10000&type=20&token=111111111111&key=index";
        //将字符串的参数转换为map,key为参数名,value为参数值, steam方法
        //1.先转为stream对象 2.用=号分隔符映射为map 3.再通过构造map对象收集为一个真实的map.
       Map<String,String> paramMap=
               Stream.of(queryString.split("&")).map(str->str.split("=")).collect(Collectors.toMap(x->x[0],y->y[1]));
        System.out.println(paramMap);
        paramMap.keySet().forEach(key -> {
            System.out.println("key:"+key+"|value:"+paramMap.get(key));
        });

    }

    /**
     * 二.中间操作
     * 1.选择与过滤 filter(Predicate p) ->接收有参返回bool的Lambda表达式,从流中排除某些元素
     * 2.distinct()筛选,通过流所生成元素的hashCode和equals()去除重复元素
     * */
    private static void transform(){
        Stream<Book> sourceStream=books.stream();
        //内部迭代过滤,避免迭代器
        //过滤大于100的书
        //Stream<Book> sourceStream1=  sourceStream.filter((x)-> (x.getPrice()>100));
        //过滤重复名称的书
        Stream<Book> sourceStream2=  sourceStream.distinct();

        List<Book> collectors1=sourceStream2.collect(Collectors.toList());
        collectors1.forEach(item->{
            System.out.println("book:"+item.toString());
        });
    }

		/*
		 * 类型 				语法						对应的lambda表达式
		静态方法引用 		类名::staticMethod   (args) -> 类名.staticMethod(args)
		实例方法引用 		inst::instMethod     (args) -> inst.instMethod(args)
		对象方法引用 		类名::instMethod     (inst,args)  ->类名.instMethod(args)
		构造方法引用 		类名::new            (args) -> new 类名(args)
		 * */


}
