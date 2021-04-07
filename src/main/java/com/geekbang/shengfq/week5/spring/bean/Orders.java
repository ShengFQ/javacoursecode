package com.geekbang.shengfq.week5.spring.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * 订单表
 * @author sheng
 *
 * */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Orders {
    private Integer id;
    private String orderNumber;
    private String requestNumber;
    private Integer customerId;
    private Float orderAmount;
    private Integer orderAddressId;
    private String remark;
    private Timestamp transactionTime;
    private Integer tradeStatus;

    private Float freezeAmount;
    private Timestamp completeTime;

    private Integer payStatus;
    private Timestamp payTime;

    private Integer productId;
    private Integer productCount;

    private String mobilePhone;
    private Integer paymentWay;

    private Integer isDeleted;

}
