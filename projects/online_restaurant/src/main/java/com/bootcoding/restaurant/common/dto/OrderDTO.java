package com.bootcoding.restaurant.common.dto;

import com.bootcoding.restaurant.common.OrderStatus;

import java.util.List;

/**
 * @author bootcoding on 18/09/22
 * @project software_development_training
 */
public class OrderDTO {

    private long id;
    private long customerId;
    private long vendorId;
    private double totalPrice;
    private String deliveryAddress;
    private OrderStatus orderStatus;
    private List<OrderMenuItemDTO> menuItems;

}
