package com.geekbang.shengfq.week5.spring.jdbc;

import com.geekbang.shengfq.week5.spring.bean.Orders;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * spring jdbc 实现dao
 * @author sheng
 * */
@Slf4j
public class OrderJdbcRepository implements OrdersDAO {
    private DataSource dataSource;
    private PlatformTransactionManager transactionManager;
    private static final String SQL_BATCH_INSERT = "insert into zw_alipay_order (id,order_number,request_number,customer_id,order_amount,order_address_id," +
            "remark,transaction_time,trade_status,freeze_amount,complete_time,pay_status,pay_time,product_id,product_count,mobile_phone,payment_way,is_deleted) values ";
    private static final String SQL_INSERT = "insert into zw_alipay_order (id,order_number,request_number,customer_id,order_amount,order_address_id,"  +
            "remark,transaction_time,trade_status,freeze_amount,complete_time,pay_status,pay_time,product_id,product_count,mobile_phone,payment_way,is_deleted) values " +
            "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    /**
     * This is the method to be used to initialize
     * database resources ie. connection.
     *
     * @param ds
     */
    @Override
    public void setDataSource(DataSource ds) {
        this.dataSource=ds;
    }

    public void setTransactionManager(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }
    /**
     * This is the method to be used to create
     * a record in the Student table.
     *
     * @param item
     */
    @Override
    public void create(Orders item) {

    }

    /**
     * This is the method to be used to list down
     * a record from the Student table corresponding
     * to a passed student id.
     *
     * @param id
     */
    @Override
    public Orders get(Integer id) {
        return null;
    }

    /**
     * This is the method to be used to list down
     * all the records from the Student table.
     */
    @Override
    public List<Orders> listOrders() {
        return null;
    }

    /**
     * This is the method to be used to delete
     * a record from the Student table corresponding
     * to a passed student id.
     *
     * @param id
     */
    @Override
    public void delete(Integer id) {

    }

    /**
     * This is the method to be used to update
     * a record into the Student table.
     *
     * @param id
     * @param amount
     */
    @Override
    public void update(Integer id, Integer amount) {

    }

    /**
     * this is the method to be used to create
     * more record in the Student table
     *
     * @param orders
     */
    @Override
    public void createMore(List<Orders> orders) throws Exception {

    }

    /**
     * this is the method to be used to create
     * more record in the Student table
     * 使用的是原始的JDBC方式,连接不停的重建
     * @param orders
     */
    public void createMore(List<Orders> orders, CountDownLatch startCountDownLatch) throws Exception{
        TransactionDefinition transactionDefinition=new DefaultTransactionDefinition();
        TransactionStatus transactionStatus= this.transactionManager.getTransaction(transactionDefinition);
        //Connection connection=null;
       // PreparedStatement preparedStatement=null;
        StringBuffer suffix=new StringBuffer();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(" ")) {
           // connection=dataSource.getConnection();
           // preparedStatement=connection.prepareStatement(" ");

            for(Orders order:orders){
                suffix.append("(")
                        .append(order.getId()).append(",")
                        .append("'").append(order.getOrderNumber()).append("'").append(",")
                        .append("'").append(order.getRequestNumber()).append("'").append(",")
                        .append(order.getCustomerId()).append(",")
                        .append(order.getOrderAmount()).append(",")
                        .append("'").append(order.getOrderAddressId()).append("'").append(",")
                        .append("'").append(order.getRemark()).append("'").append(",")
                        .append("'").append(order.getTransactionTime()).append("'").append(",")
                        .append(order.getTradeStatus()).append(",")
                        .append(order.getFreezeAmount()).append(",")
                        .append("'").append(order.getCompleteTime()).append("'").append(",")
                        .append(order.getPayStatus()).append(",")
                        .append("'").append(order.getPayTime()).append("'").append(",")
                        .append(order.getProductId()).append(",")
                        .append(order.getProductCount()).append(",")
                        .append("'").append(order.getMobilePhone()).append("'").append(",")
                        .append(order.getPaymentWay()).append(",")
                        .append(order.getIsDeleted()).append("),");
            }
            StringBuffer sql=new StringBuffer(SQL_BATCH_INSERT);
            sql.append(suffix.substring(0,suffix.length()-1));
            //log.info("sql:{}",sql.toString());
            preparedStatement.addBatch(sql.toString());
            preparedStatement.executeBatch();
            //transactionManager.commit(transactionStatus);
            startCountDownLatch.countDown();
            startCountDownLatch.await();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
