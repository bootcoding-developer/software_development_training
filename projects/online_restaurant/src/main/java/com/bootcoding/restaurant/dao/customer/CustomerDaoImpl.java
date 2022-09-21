package com.bootcoding.restaurant.dao.customer;

import com.bootcoding.restaurant.common.DatabaseConfiguration;
import com.bootcoding.restaurant.common.DefaultConfiguration;
import com.bootcoding.restaurant.common.TableHeaders;
import com.bootcoding.restaurant.dao.entity.Customer;
import com.bootcoding.restaurant.utils.DBUtils;
import org.springframework.jdbc.core.PreparedStatementSetter;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

/**
 * @author bootcoding on 19/09/22
 * @project software_development_training
 */
public class CustomerDaoImpl implements CustomerDao {
    private final DatabaseConfiguration databaseConfiguration;
    private final DefaultConfiguration defaultConfiguration;

    public CustomerDaoImpl(DatabaseConfiguration databaseConfiguration, DefaultConfiguration defaultConfiguration){
        this.databaseConfiguration = databaseConfiguration;
        this.defaultConfiguration = defaultConfiguration;
    }

    @Override
    public void insertCustomer(Customer customer) {
        this.databaseConfiguration.getJdbcTemplate()
            .update(DBUtils.buildAndGetInsertQuery(defaultConfiguration.getOrderTable(), TableHeaders.CUSTOMER_TABLE_COLS), new PreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement ps) throws SQLException {
                    int parameterIndex = 1;

                    ps.setString(parameterIndex++, customer.getName());
                    ps.setString(parameterIndex++, customer.getEmailId());
                    ps.setString(parameterIndex++, customer.getPassword());
                    ps.setString(parameterIndex++, customer.getCity());
                    ps.setString(parameterIndex++, customer.getState());
                    ps.setString(parameterIndex++, customer.getDeliveryAddress());
                    ps.setTimestamp(parameterIndex++, DBUtils.getTimestamp(customer.getCreatedAt()));
                    ps.setTimestamp(parameterIndex, DBUtils.getTimestamp(customer.getModifiedAt()));
                }
            });
    }
}
