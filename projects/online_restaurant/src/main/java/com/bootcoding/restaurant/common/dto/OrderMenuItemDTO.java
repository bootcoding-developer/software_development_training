package com.bootcoding.restaurant.common.dto;

import lombok.Data;

/**
 * @author bootcoding on 18/09/22
 * @project software_development_training
 */

@Data
public class OrderMenuItemDTO {
    private long id;
    private long orderId;
    private long vendorId;
    private String itemName;
    private String menuCategory;
    private String description;
    private int quantity;
    private double price;
}
