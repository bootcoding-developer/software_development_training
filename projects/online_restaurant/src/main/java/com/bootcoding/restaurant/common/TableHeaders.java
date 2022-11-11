package com.bootcoding.restaurant.common;

public class TableHeaders {

    public static final String[] VENDOR_TABLE_COLS = {
            "restaurant_name", "category", "state", "city", "address", "registration_date", "modified_date", "approved",
            "latitude", "longitude"
    };
    public static final String[] ORDER_TABLE_COLS = {
            "customer_id", "total_price", "delivery_address", "status", "created_at", "modified_at"
    };
    public static final String[] ORDER_MENU_ITEM_TABLE_COLS = {
            "order_id", "vendor_id", "item_name", "category", "description", "veg", "quantity", "price"
    };
    public static final String[] VENDOR_MENU_TABLE_COLS = {
            "name", "veg", "category", "price", "description", "category_id", "images", "vendor_id"
    };
    public static final String[] MENU_CATEGORY_TABLE_COLS = {
            "category","sub_category", "sub_category2", "veg"
    };
    public static final String[] CUSTOMER_TABLE_COLS = {
            "name","emailId", "password", "city", "state", "deliveryAddress", "createdAt", "modifiedAt"
    };

    private TableHeaders() {
    }

}
