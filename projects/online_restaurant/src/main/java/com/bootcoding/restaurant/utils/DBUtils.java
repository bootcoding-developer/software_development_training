package com.bootcoding.restaurant.utils;

import com.bootcoding.restaurant.common.TableHeaders;
import com.bootcoding.restaurant.dao.entity.Customer;

import java.sql.*;
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

    static Connection dbConnection = null;
    static PreparedStatement preparedStatement = null;
    private static void makeJDBCConnection() {
        try {
            Class.forName("com.postgresql.jdbc.Driver");
            System.out.println("Congrats - Seems your MySQL JDBC Driver Registered!");
        } catch (ClassNotFoundException e) {
            System.out.println("Sorry, couldn't found JDBC driver. Make sure you have added JDBC Maven Dependency Correctly");
            e.printStackTrace();
            return;
        }
        
        try {
            // DriverManager: The basic service for managing a set of JDBC drivers.
            dbConnection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/bootcoding_db", "root", "root");
            if (dbConnection != null) {
                System.out.println("Connection Successful! Enjoy. Now it's time to push data");
            } else {
                System.out.println("Failed to make connection!");
            }
        } catch (SQLException e) {
            System.out.println("MySQL Connection Failed!");
            e.printStackTrace();
            return;
        }
    }

    private static void insertCustomer(Customer customer) {
        try {
            String tableWithSchema = "bootcoding.customer";
            String insertQueryStatement = "INSERT  INTO  "+ tableWithSchema+"  VALUES  (?,?,?)";
            long nextId = getLastId(tableWithSchema) + 1;
            preparedStatement = dbConnection.prepareStatement(insertQueryStatement);
            preparedStatement.setLong(1, nextId);
            preparedStatement.setString(2, customer.getName());
            preparedStatement.setString(3, customer.getDeliveryAddress());
            // execute insert SQL statement
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static long getLastId(String table) throws SQLException {
        String query = "SELECT MAX(ID) FROM " + table;
        preparedStatement = dbConnection.prepareStatement(query);
        // Execute the Query, and get a java ResultSet
        ResultSet rs = preparedStatement.executeQuery();
        if(rs.next()){
           return rs.getLong(1);
        }
        return 0;
    }
    private static void getDataFromDB() {
        try {
            // MySQL Select Query Tutorial
            String getQueryStatement = "SELECT * FROM employee";
            preparedStatement = dbConnection.prepareStatement(getQueryStatement);
            // Execute the Query, and get a java ResultSet
            ResultSet rs = preparedStatement.executeQuery();
            // Let's iterate through the java ResultSet
            while (rs.next()) {
                String name = rs.getString("Name");
                String address = rs.getString("Address");
                int employeeCount = rs.getInt("EmployeeCount");
                String website = rs.getString("Website");
                // Simply Print the results
                System.out.format("%s, %s, %s, %s\n", name, address, employeeCount, website);
            }
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
    }
    // Simple log utility
    private static void log(String message) {
        System.out.println(message);
    }
}
