package com.geekbang.shengfq.week5.spring.bean;

import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * spring bean 装载
 * @author sheng
 * @date 2021-02-08
 * */
public class SpringBeanLoadTest {
    public static void main(String[] args) {
        //xml方式 加载bean实例
        ApplicationContext context01=new ClassPathXmlApplicationContext("applicationContext.xml");
       // method1(context01);
       // annotation方式
        ApplicationContext context=new AnnotationConfigApplicationContext("com.geekbang.shengfq.week5.spring.bean");
       method2(context);
        //beanFactory方式
      //  method3();
    }
    /**
     * Xml方式加载bean
     * */
    private static void method1(ApplicationContext context){
        Student george=(Student) context.getBean("george");
        george.print();

        Klass klass01=(Klass) context.getBean("klass01");
        klass01.print();

        School school01=(School) context.getBean("school01");
        school01.print();
    }
    /**
     * annotation方式加载bean
     * */
    private static void method2(ApplicationContext context){
        Student student02=(Student) context.getBean("student02");
        student02.print();

        Klass klass02=(Klass)context.getBean("klass02");
        klass02.print();

        School school02=(School) context.getBean("school02");
        school02.print();
    }
    /**
     * beanFactory方式加载bean
     * */
    private static void method3(){
        DefaultListableBeanFactory beanFactory=new DefaultListableBeanFactory();
        ConstructorArgumentValues cargs=new ConstructorArgumentValues();
        MutablePropertyValues propertyValues=new MutablePropertyValues();
        propertyValues.addPropertyValue("age",1);
        propertyValues.addPropertyValue("name","student03");
        AbstractBeanDefinition bean03=new RootBeanDefinition(Student.class,cargs,propertyValues);
        beanFactory.registerBeanDefinition("student03",bean03);
        Student student03=(Student)beanFactory.getBean("student03");
        student03.print();
    }
}
