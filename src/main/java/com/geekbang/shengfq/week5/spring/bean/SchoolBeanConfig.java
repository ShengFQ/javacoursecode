package com.geekbang.shengfq.week5.spring.bean;

import lombok.Data;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 通过配置化加载bean
 * @author sheng
 * */
@Configuration
@Data
public class SchoolBeanConfig implements ApplicationContextAware {
    private ApplicationContext applicationContext;

    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    /**
     * 装载一个klass对象
     * */
    @Bean("school02")
    public School buildSchool(){
        List<Klass> klasses=new ArrayList();
        Optional<Object> klass02 = Optional.ofNullable(applicationContext.getBean("klass02"));
        klass02.ifPresent(obj -> klasses.add((Klass) obj));
        return new School("school02",klasses);
    }
}
