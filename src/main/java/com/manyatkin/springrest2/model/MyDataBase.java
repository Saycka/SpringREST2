package com.manyatkin.springrest2.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class MyDataBase {
    private static final MyDataBase INSTANCE = new MyDataBase();

    private final String URL = "jdbc:postgresql://localhost/test1";
    private final String USER = "postgres";
    private final String PASSWORD = "Qwer123";

    private final String TABLE_NAME = "items";
    private final String VENDOR_CODE_COLUMN = "vendor_code";
    private final String NAME_COLUMN = "name";
    private final String COST_COLUMN = "cost";

    private MyDataBase() {
    }

    public static MyDataBase getInstance() {
        return INSTANCE;
    }

    private Connection getConnection() throws SQLException {
        Properties properties = new Properties();
        properties.setProperty("user", USER);
        properties.setProperty("password", PASSWORD);

        return DriverManager.getConnection(URL, properties);
        //todo: DataSource
    }

    public void add(Item item) throws SQLException {
        String query = "INSERT INTO " + TABLE_NAME +
                " (" + VENDOR_CODE_COLUMN + ", " + NAME_COLUMN + ", " + COST_COLUMN + ") " +
                "VALUES (?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, item.getVendorCode());
            statement.setString(2, item.getName());
            statement.setInt(3, item.getCost());
            statement.executeUpdate();
        }
    }

    public void delete(String vendorCode) throws SQLException {
        String query = "DELETE FROM " + TABLE_NAME +
                " WHERE " + VENDOR_CODE_COLUMN + "=?";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, vendorCode);
            statement.executeUpdate();
        }
    }

    public void update(String vendorCode, String newName, int newCost) throws SQLException {
        String query = "UPDATE " + TABLE_NAME +
                " SET " +
                NAME_COLUMN + "=?, " +
                COST_COLUMN + "=? " +
                "WHERE " + VENDOR_CODE_COLUMN + "=?";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, newName);
            statement.setInt(2, newCost);
            statement.setString(3, vendorCode);
            statement.executeUpdate();
        }
    }

    public List<Item> getItemsList() throws SQLException {
        List<Item> res = new ArrayList<>();

        String query = "SELECT * FROM " + TABLE_NAME;

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                res.add(new Item(resultSet.getString(VENDOR_CODE_COLUMN),
                        resultSet.getString(NAME_COLUMN), resultSet.getInt(COST_COLUMN)));
            }
        }
        return res;
    }

    public Item findItemByVendorCode(String vendorCode) throws SQLException {
        Item res = null;
        String query = "SELECT * FROM " + TABLE_NAME +
                " WHERE " + VENDOR_CODE_COLUMN + "=?";

        ResultSet resultSet = null;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, vendorCode);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                res = new Item(vendorCode,
                        resultSet.getString(NAME_COLUMN), resultSet.getInt(COST_COLUMN));
            }
        }
        return res;
    }

}
