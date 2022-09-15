package com.bootcoding.restaurant.dao.category;

import com.bootcoding.restaurant.common.DatabaseConfiguration;
import com.bootcoding.restaurant.dao.entity.MenuCategory;

import java.util.List;

public class CategoryDaoImpl implements CategoryDao {

    private final DatabaseConfiguration databaseConfiguration;

    public CategoryDaoImpl(DatabaseConfiguration databaseConfiguration) {
        this.databaseConfiguration = databaseConfiguration;
    }

    @Override
    public void addCategories(List<MenuCategory> menuCategoryList) {

    }

    @Override
    public MenuCategory getCategoryById(String id) {
        return null;
    }

    @Override
    public MenuCategory getCategoryDetailsByPrimaryCategory(String category) {
        return null;
    }

    @Override
    public void updateSubCategory(String subCategory1, String subCategory2) {

    }
}
