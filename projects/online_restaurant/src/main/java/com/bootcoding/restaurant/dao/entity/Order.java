package com.bootcoding.restaurant.dao.entity;

import com.bootcoding.restaurant.common.OrderStatus;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.Date;
import java.util.List;

/**
 * @author bootcoding on 18/09/22
 * @project software_development_training
 */
@Data
@Builder
@ToString
public class Order {
    private long id;
    private long customerId;
    private double totalPrice;
    private String deliveryAddress;
    private OrderStatus orderStatus;
    private Date createdAt;
    private Date modifiedAt;
}
