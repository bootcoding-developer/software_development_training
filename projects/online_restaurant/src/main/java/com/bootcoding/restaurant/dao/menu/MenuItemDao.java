package com.bootcoding.restaurant.dao.menu;

import com.bootcoding.restaurant.dao.entity.MenuItem;

import java.util.List;

public interface MenuItemDao {

    public void createTable();
    List<MenuItem> getMenus(String vendorId);

    List<MenuItem> getMenusByCategory(String vendorId, boolean veg);

    List<MenuItem> getMenusByCategory1(String vendorId, String category);

    List<MenuItem> getMenusBySubCategory(String vendorId, String subCategory);

    List<MenuItem> getMenusByCity(String city);

    void insertMenuItems(List<MenuItem> menuItems);

    void updateMenuItemByDescription(String menuItemId, String description);

}
