package com.bootcoding.restaurant.dao.vendor;

import com.bootcoding.restaurant.common.DatabaseConfiguration;
import com.bootcoding.restaurant.common.TableHeaders;
import com.bootcoding.restaurant.dao.entity.Vendor;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VendorDaoImpl implements VendorDao {

    private final DatabaseConfiguration databaseConfiguration;
    private final List<String> placeHolders = new ArrayList<>();

    public VendorDaoImpl(DatabaseConfiguration databaseConfiguration) {
        this.databaseConfiguration = databaseConfiguration;
    }


    @Override
    public List<Vendor> getVendors() {
        return null;
    }

    @Override
    public void insertVendors(List<Vendor> vendors, String vendorTableName) {

        this.databaseConfiguration.getJdbcTemplate().batchUpdate(buildAndGetInsertQuery(vendorTableName),
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps, int index) throws SQLException {
                        Vendor vendor = vendors.get(index);
                        int parameterIndex = 1;
                        ps.setString(parameterIndex++, vendor.getName());
                        ps.setString(parameterIndex++, vendor.getCategory().name());
                        ps.setString(parameterIndex++, vendor.getState());
                        ps.setString(parameterIndex++, vendor.getCity());
                        ps.setString(parameterIndex++, vendor.getAddress());
                        ps.setTimestamp(parameterIndex++, getTimestamp(vendor.getRegistrationDate()));
                        ps.setTimestamp(parameterIndex++, getTimestamp(vendor.getModifiedDate()));
                        ps.setBoolean(parameterIndex++, vendor.isApproved());
                        ps.setDouble(parameterIndex++, vendor.getLatitude());
                        ps.setDouble(parameterIndex, vendor.getLongitude());

                    }

                    @Override
                    public int getBatchSize() {
                        return vendors.size();
                    }
                });
    }

    private Timestamp getTimestamp(Date date) {
        return new Timestamp(date.getTime());
    }

    private String buildAndGetInsertQuery(String vendorTableName) {
        buildPlaceHolderList();
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("insert into ").append(vendorTableName)
                .append(" (").append(String.join(",", TableHeaders.VENDOR_TABLE_COLS))
                .append(") values (").append(String.join(",", placeHolders)).append(")");
        return queryBuilder.toString();
    }

    private void buildPlaceHolderList() {
        if (placeHolders.isEmpty()) {
            for (int i = 0; i < TableHeaders.VENDOR_TABLE_COLS.length; i++) {
                placeHolders.add("?");
            }
        }
        // TODO for location
    }

    @Override
    public boolean existsVendor(Vendor vendor) {
        return false;
    }

    @Override
    public void updateVendorCategory(List<Vendor> vendors) {

    }

    @Override
    public void updateVendorName(List<Vendor> vendors) {

    }
}
