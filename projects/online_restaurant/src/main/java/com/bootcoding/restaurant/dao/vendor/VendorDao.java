package com.bootcoding.restaurant.dao.vendor;

import com.bootcoding.restaurant.dao.entity.Vendor;

import java.util.List;

public interface VendorDao {

    List<Vendor> getVendors();

    void insertVendors(List<Vendor> vendors, String tableName);

    boolean existsVendor(Vendor vendor);

    void updateVendorCategory(List<Vendor> vendors);

    void updateVendorName(List<Vendor> vendors);
}
