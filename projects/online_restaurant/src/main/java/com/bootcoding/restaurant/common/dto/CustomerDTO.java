package com.bootcoding.restaurant.common.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @author bootcoding on 18/09/22
 * @project software_development_training
 */

@Data
@Builder
@ToString
public class CustomerDTO {
    private long id;
    private String name;
    private String emailId;
    private String password;
    private String city;
    private String state;
    private String deliveryAddress;
    private Date createdAt;
    private Date modifiedAt;

}
