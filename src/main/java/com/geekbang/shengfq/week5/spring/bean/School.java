package com.geekbang.shengfq.week5.spring.bean;

import lombok.Data;

import java.util.List;

/**
 * @author sheng
 * @date 2021-02-08
 * */
@Data
public class School {
    String name;
    List<Klass> klasses;

    public School(String name,List<Klass> klasses){
        this.name=name;
        this.klasses=klasses;
    }
    public void print(){
        System.out.println("school name:"+this.name);
        klasses.forEach(Klass::print);
    }
}
