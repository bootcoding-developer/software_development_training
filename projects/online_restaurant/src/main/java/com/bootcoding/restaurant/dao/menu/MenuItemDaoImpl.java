package com.bootcoding.restaurant.dao.menu;

import com.bootcoding.restaurant.common.DatabaseConfiguration;
import com.bootcoding.restaurant.dao.entity.MenuItem;

import java.util.List;

public class MenuItemDaoImpl implements MenuItemDao {

    private final DatabaseConfiguration databaseConfiguration;

    public MenuItemDaoImpl(DatabaseConfiguration databaseConfiguration) {
        this.databaseConfiguration = databaseConfiguration;
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
    public void addMenuItems(String tableName, List<MenuItem> menuItems) {

    }

    @Override
    public void updateMenuItemByDescription(String tableName, String menuItemId, String description) {

    }
}
