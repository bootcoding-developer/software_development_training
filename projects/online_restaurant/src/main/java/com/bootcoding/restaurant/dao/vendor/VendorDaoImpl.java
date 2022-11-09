package com.bootcoding.restaurant.dao.vendor;

import com.bootcoding.restaurant.common.Constants;
import com.bootcoding.restaurant.common.DatabaseConfiguration;
import com.bootcoding.restaurant.common.DefaultConfiguration;
import com.bootcoding.restaurant.common.TableHeaders;
import com.bootcoding.restaurant.dao.entity.Vendor;
import com.bootcoding.restaurant.utils.DBUtils;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.SessionFactory;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class VendorDaoImpl implements VendorDao {

    private SessionFactory sessionFactory;

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
    public void insertVendors(List<Vendor> vendors, String vendorTableName) {
        List<Vendor> newVendors = provideNonExistsVendors(vendors,vendorTableName);
        this.databaseConfiguration.getJdbcTemplate()
            .batchUpdate(DBUtils.buildAndGetInsertQuery(DBUtils.withSchema(vendorTableName),TableHeaders.VENDOR_TABLE_COLS),
            new BatchPreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement ps, int index) throws SQLException {
                    Vendor vendor = newVendors.get(index);
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
                    return newVendors.size();
                }
            });
    }

    private List<Vendor> provideNonExistsVendors(List<Vendor> vendors, String vendorTableName) {
        return vendors.stream().filter(vendor -> !existsVendor(vendor, vendorTableName)).collect(Collectors.toList());
    }

    public boolean existsVendor(Vendor vendor, String vendorTableName) {
        // Select * from vendor where restaurant_name = vendor.getName();
        try{
            Connection con = databaseConfiguration.getJdbcTemplate().getDataSource().getConnection();
            Statement stmt = con.createStatement();
            String sql ="select * from bootcoding."+ vendorTableName + " where restaurant_name = '" +vendor.getName()+"'";
            System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next()){
                log.info("Existing restaurant - "+vendor.getName());
                return true;
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return false;
    }

    public List<Vendor> getAllVendors() {
        ArrayList<Vendor> vendors = new ArrayList<>();
        try {
            Connection con = databaseConfiguration.getJdbcTemplate().getDataSource().getConnection();
            Statement stmt = con.createStatement();
            //stmt.executeQuery("select * from bootcoding.vendor");
            ResultSet resultSet = stmt.executeQuery("select * from bootcoding.vendor");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String restaurant_name = resultSet.getString("restaurant_name");
                Vendor vendor = new Vendor();
                vendor.setName(restaurant_name);
                vendor.setId(id);
                vendors.add(vendor);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return vendors;
        //return sessionFactory.getCurrentSession().createQuery("select * from bootcoding.vendor").list();
    }
    @Override
    public void updateVendorCategory(List<Vendor> vendors) {

    }


    @Override
    public void updateVendorName(List<Vendor> vendors) {

    }
}
