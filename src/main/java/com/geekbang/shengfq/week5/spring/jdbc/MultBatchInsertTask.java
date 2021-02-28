package com.geekbang.shengfq.week5.spring.jdbc;

import com.geekbang.shengfq.week5.spring.bean.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * class7作业:执行一个子线程完成后,主线程拿到结果,再结束.
 * @author sheng
 * @date 2021-02-05
 * 共验证11种方式
 * */
@Slf4j
public class MultBatchInsertTask {
    public static void main(String[] args) throws Exception {
        method3();
        System.out.println("main is over");
    }

    /**
     * 目标:将100万条记录插入,耗时在10秒内.
     * 方法3.使用线程池执行
     * 8个线程
     * 10000条记录 4604毫秒
     * 100000条记录 12012毫秒
     *
     * 18个线程
     * 100000条记录 13373毫秒
     */
    private static void method3() throws Exception {
        ApplicationContext context;
        context = new ClassPathXmlApplicationContext("applicationContext.xml");
        IStudentService studentService=(IStudentService)context.getBean("studentServiceImpl");
        List<Student> students=new ArrayList<>();
        for (int i=0;i<100000;i++){
            Student student=new  Student(i,"auto"+i);
            students.add(student);
        }
        long startTime = System.nanoTime();
        log.info("起始时间:{}",startTime);
        ExecutorService pool1 = Executors.newFixedThreadPool(8);
        Future<Integer> result = pool1.submit(new Callable<Integer>() {
            @Override
            public Integer call() {
                studentService.saveStudent(students);
                return students.size();
            }
        });
        log.info("插入记录数:{}" ,result.get());
        long endTime = System.nanoTime();
        log.info("结束时间:{}",endTime);
        long millis = TimeUnit.NANOSECONDS.toMillis(endTime - startTime);
        log.info("总耗时:{}毫秒",millis);
    }

}