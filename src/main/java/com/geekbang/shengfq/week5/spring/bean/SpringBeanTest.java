package com.geekbang.shengfq.week5.spring.bean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * spring bean 装载
 * @author sheng
 * @date 2021-02-08
 * */
public class SpringBeanTest {
    public static void main(String[] args) {
        ApplicationContext contex=new ClassPathXmlApplicationContext("applicationContext.xml");
        //method1(contex);
        //method2(contex);
        method3(contex);
    }

    private static void method1(ApplicationContext context){
        Student george=(Student) context.getBean("george");
        System.out.println(george.toString());

    }

    private static  void method2(ApplicationContext context){
        Student george=(Student) context.getBean("george");
        george.print();
        Boy kimmking=george.getBoy();
        System.out.println(kimmking.toString());
    }

    private static void method3(ApplicationContext context){
        Student george=context.getBean(Student.class);
        System.out.println(george.toString());
        george.print();
    }
}
