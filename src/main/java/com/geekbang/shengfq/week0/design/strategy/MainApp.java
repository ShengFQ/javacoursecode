package com.geekbang.shengfq.week0.design.strategy;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainApp  {
    /**
     * 所有的bean都是注解方式加载,那就不要用xml方式获取上下文
     * */
    public static void main(String[] args) {
        ApplicationContext context=null;
        context = new AnnotationConfigApplicationContext("com.geekbang.shengfq.week0.logic.strategy");
        UserService userService=(UserService) context.getBean(UserService.class);
        userService.list("SHUOSHUO");
    }


}
