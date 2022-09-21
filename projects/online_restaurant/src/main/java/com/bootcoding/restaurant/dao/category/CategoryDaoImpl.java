package com.bootcoding.restaurant.dao.category;

import com.bootcoding.restaurant.common.DatabaseConfiguration;
import com.bootcoding.restaurant.common.DefaultConfiguration;
import com.bootcoding.restaurant.common.TableHeaders;
import com.bootcoding.restaurant.dao.entity.MenuCategory;
import com.bootcoding.restaurant.dao.entity.MenuItem;
import com.bootcoding.restaurant.utils.DBUtils;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class CategoryDaoImpl implements CategoryDao {

    private final DatabaseConfiguration databaseConfiguration;
    private final DefaultConfiguration defaultConfiguration;

    public CategoryDaoImpl(DatabaseConfiguration databaseConfiguration, DefaultConfiguration defaultConfiguration) {
        this.databaseConfiguration = databaseConfiguration;
        this.defaultConfiguration = defaultConfiguration;
    }

    @Override
    public void addCategories(List<MenuCategory> menuCategoryList) {

        this.databaseConfiguration.getJdbcTemplate()
            .batchUpdate(DBUtils.buildAndGetInsertQuery(defaultConfiguration.getCategoryTable(), TableHeaders.VENDOR_MENU_TABLE_COLS),
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps, int index) throws SQLException {
                        MenuCategory menuCategory = menuCategoryList.get(index);
                        int parameterIndex = 1;
                        ps.setString(parameterIndex++, menuCategory.getCategory());
                        ps.setString(parameterIndex++, menuCategory.getSubCategory());
                        ps.setString(parameterIndex++, menuCategory.getSubCategory2());
                        ps.setBoolean(parameterIndex, menuCategory.isVeg());
                    }
                    @Override
                    public int getBatchSize() {
                        return menuCategoryList.size();
                    }
                });
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
