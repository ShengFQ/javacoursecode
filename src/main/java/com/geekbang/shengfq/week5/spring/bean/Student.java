package com.geekbang.shengfq.week5.spring.bean;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

@Data
@NoArgsConstructor
@ToString
public class Student {
    private int age;
    private String name;
    //@Resource
    @Autowired
    private Boy boy;

    void print(){
        System.out.println("student:"+this.toString()+" boy:"+this.boy.toString());
    }
}
