package com.example.mylittleerp.dbConnection;

import com.example.mylittleerp.model.Product;
import com.example.mylittleerp.model.Supplier;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SupplierCRUD {

    private static final String URL = "jdbc:mysql://localhost:3306/my_little_erp_db";
    private static final String USER = "root";
    private static final String PASSWORD = "password";

    private static Connection connection;
    private static PreparedStatement preparedStatement;
    private static ResultSet resultSet;

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    public static List<Supplier> getAllSuppliers() {
        List<Supplier> suppliers = new ArrayList<>();
        String selectQuery = "SELECT * FROM suppliers";
        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Supplier supplier = mapResultSetToSupplier(resultSet);
                suppliers.add(supplier);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return suppliers;
    }
    public static void addSupplier(Supplier supplier) {
        String insertQuery = "INSERT INTO suppliers (name, address, contact) VALUES (?, ?, ?)";
        try {
            connection = connect();
            preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, supplier.getName());
            preparedStatement.setString(2, supplier.getAddress());
            preparedStatement.setString(3, supplier.getContact());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
    }

    public static Supplier getSupplierById(int supplierId) {
        String selectQuery = "SELECT * FROM suppliers WHERE supplier_id = ?";
        Supplier supplier = null;
        try {
            connection = connect();
            preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setInt(1, supplierId);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                supplier = mapResultSetToSupplier(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return supplier;
    }

    private static Supplier mapResultSetToSupplier(ResultSet resultSet) throws SQLException {
        Supplier supplier = new Supplier(
                resultSet.getString("name"),
                resultSet.getString("address"),
                resultSet.getString("contact")
        );

        // Assuming there is a suppliedProducts field in the database for identification
        // You may need to retrieve and set supplied products separately based on your database schema
        // supplier.setSuppliedProducts(getSuppliedProductsBySupplierId(resultSet.getInt("supplier_id")));

        return supplier;
    }

    public static void updateSupplier(Supplier supplier) {
        String updateQuery = "UPDATE suppliers SET address=?, contact=? WHERE name=?";
        try {
            connection = connect();
            preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.setString(1, supplier.getAddress());
            preparedStatement.setString(2, supplier.getContact());
            preparedStatement.setString(3, supplier.getName()); // Use name as the condition
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
    }


    public static void deleteSupplier(String supplierName) {
        String deleteQuery = "DELETE FROM suppliers WHERE name=?";
        try {
            connection = connect();
            preparedStatement = connection.prepareStatement(deleteQuery);
            preparedStatement.setString(1, supplierName);
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
