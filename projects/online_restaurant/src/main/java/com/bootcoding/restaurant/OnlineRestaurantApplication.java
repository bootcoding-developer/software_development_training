package com.bootcoding.restaurant;

import com.bootcoding.restaurant.common.CategoryEnum;
import com.bootcoding.restaurant.common.DefaultConfiguration;
import com.bootcoding.restaurant.dao.entity.Vendor;
import com.bootcoding.restaurant.service.vendor.VendorService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@SpringBootApplication
@Slf4j
public class OnlineRestaurantApplication implements CommandLineRunner {

    @Autowired
    DefaultConfiguration defaultConfiguration;
    @Autowired
    VendorService vendorService;

    public static void main(String[] args) {
        SpringApplication.run(OnlineRestaurantApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        vendorService.createTable();
        log.info("Online Restaurant Application is started ...");



    }

    private List<Vendor> buildVendors() {
        return Collections.singletonList(Vendor.builder()
                .name("Haldirams")
                .state("Maharashtra")
                .city("Nagpur")
                .category(CategoryEnum.VEG)
                .approved(true)
                .registrationDate(new Date())
                .modifiedDate(new Date())
                .build());
    }
}
