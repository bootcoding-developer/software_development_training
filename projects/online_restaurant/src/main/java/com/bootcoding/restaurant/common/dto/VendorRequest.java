package com.bootcoding.restaurant.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VendorRequest {
    private String requestId;
    private List<VendorDTO> vendors;
}
