package com.bootcoding.restaurant.common.dto;

import com.bootcoding.restaurant.common.CategoryEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VendorDTO {

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
