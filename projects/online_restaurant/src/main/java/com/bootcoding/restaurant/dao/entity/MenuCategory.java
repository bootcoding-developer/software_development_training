package com.bootcoding.restaurant.dao.entity;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Builder
@Data
@ToString
public class MenuCategory {

    private long id;
    private String category;
    private String subCategory;
    private String subCategory2;
    private boolean veg;
}
