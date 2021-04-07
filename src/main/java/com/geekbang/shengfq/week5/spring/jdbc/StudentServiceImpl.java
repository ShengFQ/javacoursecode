package com.geekbang.shengfq.week5.spring.jdbc;

import com.geekbang.shengfq.week5.spring.bean.Orders;
import com.geekbang.shengfq.week5.spring.bean.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * 业务访问类
 * */
@Service("studentServiceImpl")
public class StudentServiceImpl implements IStudentService {

    StudentJdbcTemplate studentJdbcTemplate;
    StudentJdbcRepository studentJDBCRepository;

    OrderJdbcRepository orderJdbcRepository;
    public void setStudentJdbcTemplate(StudentJdbcTemplate studentJdbcTemplate) {
        this.studentJdbcTemplate = studentJdbcTemplate;
    }

    public void setStudentJDBCRepository(StudentJdbcRepository studentJDBCRepository) {
        this.studentJDBCRepository = studentJDBCRepository;
    }

    public void setOrderJdbcRepository(OrderJdbcRepository orderJdbcRepository) {
        this.orderJdbcRepository = orderJdbcRepository;
    }

    @Override
    public void saveStudent(List<Student> students) {
        try {
            studentJDBCRepository.createMore(students);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void saveOrders(List<Orders> orders, CountDownLatch startCountDownLatch) {
        try {
            orderJdbcRepository.createMore(orders,startCountDownLatch);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
