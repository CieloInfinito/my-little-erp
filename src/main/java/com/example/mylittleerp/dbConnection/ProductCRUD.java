package com.example.mylittleerp.dbConnection;

import com.example.mylittleerp.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductCRUD {

    private static final String URL = "jdbc:mysql://localhost:3306/my_little_erp_db";
    private static final String USER = "root";
    private static final String PASSWORD = "password";

    private static Connection connection;
    private static PreparedStatement preparedStatement;
    private static ResultSet resultSet;

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    public static List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        String selectQuery = "SELECT * FROM inventory";
        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Product product = mapResultSetToProduct(resultSet);
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }
    public static List<String> getAllProductNames() {
        List<String> productNames = new ArrayList<>();
        String selectQuery = "SELECT name FROM inventory";
        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                String productName = resultSet.getString("name");
                productNames.add(productName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productNames;
    }

    public static void addProduct(Product product) {
        String insertQuery = "INSERT INTO inventory (name, description, price, stock_quantity) VALUES (?, ?, ?, ?)";
        try {
            connection = connect();
            preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setDouble(3, product.getPrice());
            preparedStatement.setInt(4, product.getStockQuantity());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
    }

    public static Product getProductById(int productId) {
        String selectQuery = "SELECT * FROM inventory WHERE product_id = ?";
        Product product = null;
        try {
            connection = connect();
            preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setInt(1, productId);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                product = mapResultSetToProduct(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return product;
    }

    protected static Product mapResultSetToProduct(ResultSet resultSet) throws SQLException {
        return new Product(
                resultSet.getString("name"),
                resultSet.getString("description"),
                resultSet.getDouble("price"),
                resultSet.getInt("stock_quantity")
        );
    }

    public static void updateProduct(Product product) {
        String updateQuery = "UPDATE inventory SET description=?, price=?, stock_quantity=? WHERE name=?";
        try {
            connection = connect();
            preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.setString(1, product.getDescription());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setInt(3, product.getStockQuantity());
            preparedStatement.setString(4, product.getName()); // Use name as the condition
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
    }


    public static void deleteProduct(String name) {
        String deleteQuery = "DELETE FROM inventory WHERE name=?";
        try {
            connection = connect();
            preparedStatement = connection.prepareStatement(deleteQuery);
            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
    }

    private static void closeResources() {
        try {
            if (resultSet != null) resultSet.close();
            if (preparedStatement != null) preparedStatement.close();
            if (connection != null) connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
