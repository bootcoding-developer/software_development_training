package com.bootcoding.restaurant.dao.category;

import com.bootcoding.restaurant.dao.entity.MenuCategory;

import java.util.List;

public interface CategoryDao {

    void addCategories(List<MenuCategory> menuCategoryList);

    MenuCategory getCategoryById(String id);

    MenuCategory getCategoryDetailsByPrimaryCategory(String category);

    void updateSubCategory(String subCategory1, String subCategory2);
}

