package com.niu.rabbitmq.delayqueue.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * 订单类实体
 *
 * @author niuqingsong
 * @date 2021/3/18
 */
@Data
public class Order implements Serializable {
    private static final long serialVersionUID = -2221214252163879885L;

    /**
     *   订单Id
     */
    private String orderId;

    /**
     *   订单状态 0：未支付，1：已支付，2：订单已取消
     */
    private Integer orderStatus;

    /**
     *   订单名字
     */
    private String orderName;
}