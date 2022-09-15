package com.bootcoding.restaurant.controller;

import com.bootcoding.restaurant.common.dto.VendorRequest;
import com.bootcoding.restaurant.common.dto.VendorResponse;
import com.bootcoding.restaurant.exceptions.VendorServiceException;
import com.bootcoding.restaurant.service.VendorService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/v1/vendor")
@Slf4j
public class VendorController {

    private final VendorService vendorService;
    private final ModelMapper modelMapper;

    public VendorController(VendorService vendorService, ModelMapper modelMapper) {
        this.vendorService = vendorService;
        this.modelMapper = modelMapper;
    }

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VendorResponse> registerVendor(@RequestBody VendorRequest vendorRequest) {
        VendorResponse response;
        try {

            vendorService.registerVendor(vendorRequest.getVendors());
            response = new VendorResponse();
        } catch (VendorServiceException ve) {
            log.error("Error while registering vendor : {}", ve.getMessage(), ve);
            response = new VendorResponse();
        }
        return ResponseEntity.ok(response);
    }
}
