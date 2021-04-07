package com.geekbang.shengfq.week5.spring.jdbc;

import com.geekbang.shengfq.week5.spring.bean.Orders;
import com.geekbang.shengfq.week5.spring.bean.Student;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * biz interface
 * @author shengfq
 * */
public interface IStudentService {
    void saveStudent(List<Student> students);

    void saveOrders(List<Orders> orders, CountDownLatch startCountDownLatch);
}
