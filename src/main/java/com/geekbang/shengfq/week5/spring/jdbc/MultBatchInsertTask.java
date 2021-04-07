package com.geekbang.shengfq.week5.spring.jdbc;

import com.geekbang.shengfq.week5.spring.bean.Orders;
import com.geekbang.shengfq.week5.spring.bean.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Timestamp;
import java.util.*;
import java.util.concurrent.*;

/**
 * class14作业:按自己设计的表结构，插入 100 万订单模拟数据，测试不同方式的插入效率
 * @author sheng
 * @date 2021-03-10
 * */
@Slf4j
public class MultBatchInsertTask {
    private static final int TOTAL_COUNT=100_0000,BATCH_COUNT=10000,THREADS=16;

    public static void main(String[] args) throws Exception {
        method3();
        System.out.println("main is over");
    }

    /**
     * 目标:将100万条记录插入,耗时在10秒内.
     * 通过
     * 1.sql语句改成批量操作 jdbcTemplateObject.batchUpdate();
     * 2.修改mysql执行的sql语句的长度
     SET @@global.max_allowed_packet=8388608;
     SET @@global.bulk_insert_buffer_size=125829120;
     SET @@global.net_buffer_length = 8192;
    测试:
     1.jdbc-sql语句拼接为批量插入.
     插入zw_alipay_order 18个字段, 100万批量插入,总耗时:51260毫秒.
     2.mysql连接字符串增加session开关 rewriteBatchedStatements=true
     插入zw_alipay_order 18个字段, 100万批量插入,总耗时:46807毫秒.
     3.设置mysql 的全局参数
     SET @@global.max_allowed_packet=8388608;
     SET @@global.bulk_insert_buffer_size=125829120;
     SET @@global.net_buffer_length = 8192;
     插入zw_alipay_order 18个字段, 100万批量插入,总耗时:44027毫秒.
     */
    private static void method3() throws Exception {
        CountDownLatch startCountDownLatch = new CountDownLatch(THREADS);
        CountDownLatch endCountDownLatch = new CountDownLatch(THREADS);
        ApplicationContext context=null;
        context = new ClassPathXmlApplicationContext("applicationContext.xml");
        IStudentService studentService=(IStudentService)context.getBean("studentServiceImpl");
        List<Orders> orders=new ArrayList<>();
        for (int i=0;i<TOTAL_COUNT;i++){
            Orders order= Orders.builder().id(i).orderNumber("order"+i).requestNumber("request"+i).customerId(i).orderAmount(Float.valueOf(1.0f)).remark("")
                    .orderAddressId(1)
                    .tradeStatus(1).transactionTime(new Timestamp(System.currentTimeMillis())).freezeAmount(Float.valueOf(1.0f)).completeTime(new Timestamp(System.currentTimeMillis()))
                    .payStatus(1).payTime(new Timestamp(System.currentTimeMillis())).productId(1).productCount(1).mobilePhone("1")
                    .paymentWay(1).isDeleted(1).build();

            orders.add(order);
        }
        //将100万的数据对象按每批次1万切割
        List<List<Orders>> parts=splitList(orders,TOTAL_COUNT/THREADS);
        long startTime = System.nanoTime();
        log.info("起始时间:{}",startTime);
        //TODO 1单线程串行插入
        /*ExecutorService pool1 = Executors.newFixedThreadPool(THREADS);
        Future<Integer> result = pool1.submit(new Callable<Integer>() {
            @Override
            public Integer call() {
                for (List<Orders> part:parts ) {
                    log.info("本批次插入 {} 条",part.size());
                    studentService.saveOrders(part);
                }
                return orders.size();
            }
        });
        log.info("插入记录数:{}" ,result.get());
        long endTime = System.nanoTime();
        log.info("结束时间:{}",endTime);
        long millis = TimeUnit.NANOSECONDS.toMillis(endTime - startTime);
        log.info("总耗时:{}毫秒",millis);
        */
        //TODO 2多线程并发插入
        for(int i = 0; i < THREADS; i++){
            final  int  j=i;
            Thread thread=new Thread(()->{

                log.info("本批次插入 {} 条",parts.get(j).size());
                studentService.saveOrders(parts.get(j),startCountDownLatch);
            });
            thread.start();
        }
        try {
            startCountDownLatch.await();
            long startMillis = System.currentTimeMillis();
            endCountDownLatch.await();
            long endMillis = System.currentTimeMillis();
            System.out.printf("总共耗时%d\n", endMillis - startMillis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 拆分list为小块
     * **/
    private static List<List<Orders>> splitList(List<Orders> list , int groupSize){
        int length = list.size();
        // 计算可以分成多少组
        //int num =length % groupSize == 0 ? length / groupSize : length / groupSize +1;
        //or
        int num = ( length + groupSize - 1 )/groupSize ;
        List<List<Orders>> newList = new ArrayList<>(num);
        for (int i = 0; i < num; i++) {
            // 开始位置
            int fromIndex = i * groupSize;
            // 结束位置
            int toIndex = (i+1) * groupSize < length ? ( i+1 ) * groupSize : length ;
            newList.add(list.subList(fromIndex,toIndex)) ;
        }
        return  newList ;
    }
}
