package com.bootcoding.restaurant.utils;

import com.bootcoding.restaurant.common.TableHeaders;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author bootcoding on 18/09/22
 * @project software_development_training
 */
public class DBUtils {

    public static final String BOOTCODING_SCHEMA = "bootcoding";
    public static String buildAndGetInsertQuery(String vendorTableName, String[] cols) {

        final List<String> placeHolders =  buildPlaceHolderList(cols);
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("insert into ").append(vendorTableName)
                .append(" (").append(String.join(",", TableHeaders.VENDOR_TABLE_COLS))
                .append(") values (").append(String.join(",", placeHolders)).append(")");
        return queryBuilder.toString();
    }

    public static List<String> buildPlaceHolderList(String[] tableCols) {
        List<String> placeHolders = new ArrayList<>();
        for (int i = 0; i < tableCols.length; i++) {
            placeHolders.add("?");
        }
        return placeHolders;
    }


    public static Timestamp getTimestamp(Date date) {
        return new Timestamp(date.getTime());
    }

    public static String withSchema(String tableName) {
        return BOOTCODING_SCHEMA + "." + tableName;
    }
}
