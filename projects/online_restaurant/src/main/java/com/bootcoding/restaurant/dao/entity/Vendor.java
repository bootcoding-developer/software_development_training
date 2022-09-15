package com.bootcoding.restaurant.dao.entity;

import com.bootcoding.restaurant.common.CategoryEnum;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Builder
@Data
@ToString
public class Vendor {

    private long id;
    private String name;
    private CategoryEnum category;
    private String state;
    private String city;
    private String address;
    private Date registrationDate;
    private Date modifiedDate;
    private boolean approved;
    private double latitude;
    private double longitude;

}
