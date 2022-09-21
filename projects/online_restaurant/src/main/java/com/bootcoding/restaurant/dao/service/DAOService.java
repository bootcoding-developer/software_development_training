package com.bootcoding.restaurant.dao.service;

import com.bootcoding.restaurant.common.DatabaseConfiguration;
import com.bootcoding.restaurant.common.DefaultConfiguration;
import com.bootcoding.restaurant.dao.category.CategoryDao;
import com.bootcoding.restaurant.dao.factory.DaoFactory;
import com.bootcoding.restaurant.dao.menu.MenuItemDao;
import com.bootcoding.restaurant.dao.vendor.VendorDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

@Slf4j
@Service
public class DAOService {
    private final DaoFactory daoFactory;
    @Qualifier("datasource")
    private final DataSource dataSource;

    private final JdbcTemplate jdbcTemplate;
    private final DatabaseConfiguration databaseConfiguration;
    private final DefaultConfiguration defaultConfiguration;

    public DAOService(DataSource dataSource, JdbcTemplate jdbcTemplate, DefaultConfiguration defaultConfiguration) {
        this.dataSource = dataSource;
        this.defaultConfiguration = defaultConfiguration;
        this.jdbcTemplate = jdbcTemplate;
        this.databaseConfiguration = getDatabaseConfig();
        this.daoFactory = DaoFactory.getDAOFactoryInstance();
    }

    private DatabaseConfiguration getDatabaseConfig() {
        return DatabaseConfiguration.builder()
                .jdbcTemplate(jdbcTemplate)
                .dbUsername(defaultConfiguration.getDbUsername())
                .queryMaxResults(defaultConfiguration.getQueryMaxResults())
                .build();
    }

    public VendorDao getVendorDao() {
        return daoFactory.getVendorDao(this.databaseConfiguration, this.defaultConfiguration);
    }

    public CategoryDao getCategoryDao() {
        return daoFactory.getCategoryDao(databaseConfiguration, this.defaultConfiguration);

    }

    public MenuItemDao getMenuItemDao() {
        return daoFactory.getMenuItemDao(databaseConfiguration, this.defaultConfiguration);
    }


}
