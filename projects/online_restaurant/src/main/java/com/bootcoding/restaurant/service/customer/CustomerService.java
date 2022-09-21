package com.bootcoding.restaurant.service.customer;

import com.bootcoding.restaurant.common.dto.VendorDTO;
import com.bootcoding.restaurant.config.ApplicationConfig;
import com.bootcoding.restaurant.dao.entity.Vendor;
import com.bootcoding.restaurant.dao.service.DAOService;
import com.bootcoding.restaurant.dao.vendor.VendorDao;
import com.bootcoding.restaurant.exceptions.VendorServiceException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CustomerService {

    private final ApplicationConfig applicationConfig;
    private final DAOService daoService;
    private final ModelMapper modelMapper;
    private final DataSource bootCodingDataSource;

    public CustomerService(ApplicationConfig applicationConfig, DAOService daoService,
                           @Qualifier("bootCodingDataSource") DataSource bootCodingDataSource, ModelMapper modelMapper) {
        this.applicationConfig = applicationConfig;
        this.bootCodingDataSource = bootCodingDataSource;
        this.daoService = daoService;
        this.modelMapper = modelMapper;
    }

    public void registerVendor(List<VendorDTO> vendors) throws VendorServiceException {
        List<Vendor> vendorEntities = convertToVendorEntities(vendors);
        VendorDao vendorDao = daoService.getVendorDao();
        vendorDao.insertVendors(vendorEntities, getTableName());
    }

    public void registerVendor(VendorDTO vendorDTO) throws VendorServiceException {
        Vendor vendor = convertToVendorEntity(vendorDTO);
        VendorDao vendorDao = daoService.getVendorDao();
        vendorDao.insertVendors(Collections.singletonList(vendor), getTableName());
    }

    private String getTableName() {
        return applicationConfig.defaultConfiguration().getRestaurantSchema() + "." + applicationConfig.defaultConfiguration().getVendorMetaTable();
    }


    private List<Vendor> convertToVendorEntities(List<VendorDTO> vendors) throws VendorServiceException {
        return vendors.stream().map(req -> modelMapper.map(req, Vendor.class)).collect(Collectors.toList());
    }


    private Vendor convertToVendorEntity(VendorDTO vendorDTO) throws VendorServiceException {
        return modelMapper.map(vendorDTO, Vendor.class);
    }


}
