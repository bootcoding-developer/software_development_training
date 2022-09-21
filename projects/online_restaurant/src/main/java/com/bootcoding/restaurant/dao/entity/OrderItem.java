package com.bootcoding.restaurant.dao.entity;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

/**
 * @author bootcoding on 18/09/22
 * @project software_development_training
 */
@Builder
@Data
@ToString
public class OrderItem {
    private long id;
    private long orderId;
    private long vendorId;
    private String itemName;
    private String menuCategory;
    private String description;
    private boolean veg;
    private int quantity;
    private double price;
}
