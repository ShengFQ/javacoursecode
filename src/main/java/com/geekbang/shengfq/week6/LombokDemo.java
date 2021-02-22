package com.geekbang.shengfq.week6;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

/**
 * lombok注解
 * */
@ToString
@Data
@Builder
@AllArgsConstructor
@Slf4j
public class LombokDemo {

    private int size;
    private String name;
    private LombokDemo(){}
    static class LombokDemoHolder{
           private static final LombokDemo instance=new LombokDemo();
    }
    public static LombokDemo getInstance(){
        return LombokDemoHolder.instance;
    }

    public static void main(String[] args) {


    }
}
