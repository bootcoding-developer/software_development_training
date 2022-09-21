package com.bootcoding.restaurant.common.dto;

import com.bootcoding.restaurant.dao.entity.Customer;
import lombok.Builder;
import lombok.Data;

/**
 * @author bootcoding on 19/09/22
 * @project software_development_training
 */
@Builder
@Data
public class CustomerRequest {
    private String requestId;
    private Customer customer;
}
