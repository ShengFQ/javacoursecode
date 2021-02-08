package com.geekbang.shengfq.week5.spring.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author sheng
 * @date 2021-02-08
 * */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class School {
    List<Student> students;

    void print(){
        System.out.println("student:"+this.students.size());
    }
}
