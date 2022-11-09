package com.bootcoding.restaurant.controller;

import com.bootcoding.restaurant.common.dto.VendorRequest;
import com.bootcoding.restaurant.common.dto.Response;
import com.bootcoding.restaurant.dao.vendor.VendorDao;
import com.bootcoding.restaurant.exceptions.VendorServiceException;
import com.bootcoding.restaurant.service.vendor.VendorService;
import com.online.restaurant.Vendor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/v1")
@Slf4j
public class VendorController {

    private final VendorService vendorService;
    private final ModelMapper modelMapper;

    public VendorController(VendorService vendorService, ModelMapper modelMapper) {
        this.vendorService = vendorService;
        this.modelMapper = modelMapper;
    }

    @PostMapping(value = "/vendor")
    public ResponseEntity<Response> registerVendor(@RequestBody VendorRequest vendorRequest) {
        Response response;
        try {
            vendorService.registerVendor(vendorRequest.getVendors());
            response = new Response();
            response.setStatus("SUCCESS");
            response.setMessage("Your restaurant account has been registered successfully");
            response.setRequestId(UUID.randomUUID().toString());

        } catch (VendorServiceException ve) {
            log.error("Error while registering vendor : {}", ve.getMessage(), ve);
            response = new Response();
        }
        return ResponseEntity.ok(response);
    }
    @GetMapping (value = "/vendor")
    public ResponseEntity<Response> getallVendor() {
        Response response;
        //System.out.println(vendorService.getAllVendors());
        response = new Response();
        response.setStatus("SUCCESS");
        response.setMessage("Registered Vendors are below : ");
        response.setData(vendorService.getAllVendors());
       return ResponseEntity.ok(response);

    }
}
