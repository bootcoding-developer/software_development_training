package com.bootcoding.restaurant.dao.entity;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Builder
@Data
@ToString
public class MenuItem {
    private long id;
    private String name;
    private boolean veg;
    private String menuCategory;
    private double price;
    private String description;

}
