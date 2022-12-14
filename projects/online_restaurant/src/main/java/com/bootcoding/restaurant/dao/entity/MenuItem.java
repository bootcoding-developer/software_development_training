package com.bootcoding.restaurant.dao.entity;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author bootcoding on 18/09/22
 * @project software_development_training
 */

@Builder
@Data
public class MenuItem {

    private long id;
    private String name;
    private boolean veg;
    private long categoryId;
    private double price;
    private String description;
    private String category;
    private List<String> images;
    private long vendorId;
}
