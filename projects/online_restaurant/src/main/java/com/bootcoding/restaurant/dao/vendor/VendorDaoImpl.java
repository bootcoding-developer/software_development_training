package com.bootcoding.restaurant.dao.vendor;

import com.bootcoding.restaurant.common.Constants;
import com.bootcoding.restaurant.common.DatabaseConfiguration;
import com.bootcoding.restaurant.common.DefaultConfiguration;
import com.bootcoding.restaurant.common.TableHeaders;
import com.bootcoding.restaurant.dao.entity.Vendor;
import com.bootcoding.restaurant.utils.DBUtils;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VendorDaoImpl implements VendorDao {

    private final DatabaseConfiguration databaseConfiguration;
    private final DefaultConfiguration defaultConfiguration;

    public VendorDaoImpl(DatabaseConfiguration databaseConfiguration, DefaultConfiguration defaultConfiguration) {
        this.databaseConfiguration = databaseConfiguration;
        this.defaultConfiguration = defaultConfiguration;
    }


    @Override
    public void createTable() {
        String username = defaultConfiguration.getDbUsername();
        String tableName = defaultConfiguration.getVendorMetaTable();
        String tableNameWithSchema = DBUtils.withSchema(tableName);
        StringBuilder query = new StringBuilder();

        query.append(Constants.CREATE_TABLE_IF_NOT_EXISTS).append(tableNameWithSchema).append(" ( ")
                .append("id bigint NOT NULL GENERATED ALWAYS AS IDENTITY (INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 999999999 CACHE 1),")
                .append("restaurant_name character varying(200) ,")
                .append("category character varying(200) ,")
                .append("state character varying(200) ,")
                .append("city character varying(200) ,")
                .append("address character varying(2000) ,")
                .append("registration_date timestamp ,")
                .append("modified_date timestamp ,")
                .append("approved boolean ,")
                .append("latitude numeric,")
                .append("longitude numeric, ")
                .append(" CONSTRAINT ").append(tableName).append("_pkey PRIMARY KEY (id)")
                .append(") ;")
                .append(" ALTER TABLE ").append(tableNameWithSchema).append(" OWNER to ").append(username).append(";")
                .append(" grant select on ").append(tableNameWithSchema).append(" to postgres;");


        databaseConfiguration.getJdbcTemplate().execute(query.toString());

    }

    @Override
    public List<Vendor> getVendors() {
        return null;
    }

    @Override
    public void insertVendors(List<Vendor> vendors, String vendorTableName) {
        this.databaseConfiguration.getJdbcTemplate()
            .batchUpdate(DBUtils.buildAndGetInsertQuery(DBUtils.withSchema(vendorTableName),TableHeaders.VENDOR_TABLE_COLS),
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
                    ps.setTimestamp(parameterIndex++, DBUtils.getTimestamp(vendor.getRegistrationDate()));
                    ps.setTimestamp(parameterIndex++, DBUtils.getTimestamp(vendor.getModifiedDate()));
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
