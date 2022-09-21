package com.bootcoding.restaurant.dao.menu;

import com.bootcoding.restaurant.common.DatabaseConfiguration;
import com.bootcoding.restaurant.common.DefaultConfiguration;
import com.bootcoding.restaurant.common.TableHeaders;
import com.bootcoding.restaurant.dao.entity.MenuItem;
import com.bootcoding.restaurant.dao.entity.Vendor;
import com.bootcoding.restaurant.utils.DBUtils;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class MenuItemDaoImpl implements MenuItemDao {

    private final DatabaseConfiguration databaseConfiguration;
    private final DefaultConfiguration defaultConfiguration;

    public MenuItemDaoImpl(DatabaseConfiguration databaseConfiguration, DefaultConfiguration defaultConfiguration) {
        this.databaseConfiguration = databaseConfiguration;
        this.defaultConfiguration = defaultConfiguration;
    }

    @Override
    public void createTable() {

    }

    @Override
    public List<MenuItem> getMenus(String vendorId) {
        return null;
    }

    @Override
    public List<MenuItem> getMenusByCategory(String vendorId, boolean veg) {
        return null;
    }

    @Override
    public List<MenuItem> getMenusByCategory1(String vendorId, String category) {
        return null;
    }

    @Override
    public List<MenuItem> getMenusBySubCategory(String vendorId, String subCategory) {
        return null;
    }

    @Override
    public List<MenuItem> getMenusByCity(String city) {
        return null;
    }


    @Override
    public void insertMenuItems(List<MenuItem> menuItems) {
        this.databaseConfiguration.getJdbcTemplate()
            .batchUpdate(DBUtils.buildAndGetInsertQuery(defaultConfiguration.getVendorMenuTable(), TableHeaders.MENU_CATEGORY_TABLE_COLS),
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps, int index) throws SQLException {
                        MenuItem menuItem = menuItems.get(index);
                        int parameterIndex = 1;
                        ps.setString(parameterIndex++, menuItem.getName());
                        ps.setBoolean(parameterIndex++, menuItem.isVeg());
                        ps.setString(parameterIndex++, menuItem.getCategory());
                        ps.setDouble(parameterIndex++, menuItem.getPrice());
                        ps.setString(parameterIndex++, menuItem.getDescription());
                        ps.setLong(parameterIndex++, menuItem.getCategoryId());
                        ps.setString(parameterIndex++, String.join(",", menuItem.getImages()));
                        ps.setLong(parameterIndex, menuItem.getVendorId());
                    }
                    @Override
                    public int getBatchSize() {
                        return menuItems.size();
                    }
                });
    }

    @Override
    public void updateMenuItemByDescription(String menuItemId, String description) {

    }
}
