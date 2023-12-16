package com.example.mylittleerp.dbConnection;

import com.example.mylittleerp.model.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerCRUD {

    private static final String URL = "jdbc:mysql://localhost:3306/my_little_erp_db";
    private static final String USER = "root";
    private static final String PASSWORD = "password";

    private static Connection connection;
    private static PreparedStatement preparedStatement;
    private static ResultSet resultSet;

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    public static List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        String selectQuery = "SELECT * FROM customers";
        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Customer customer = mapResultSetToCustomer(resultSet);
                customers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }
    public static void addCustomer(Customer customer) {
        String insertQuery = "INSERT INTO customers (name, address, contact) VALUES (?, ?, ?)";
        try {
            connection = connect();
            preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, customer.getName());
            preparedStatement.setString(2, customer.getAddress());
            preparedStatement.setString(3, customer.getContact());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
    }

    public static Customer getCustomerById(int customerId) {
        String selectQuery = "SELECT * FROM customers WHERE customer_id = ?";
        Customer customer = null;
        try {
            connection = connect();
            preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setInt(1, customerId);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                customer = mapResultSetToCustomer(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return customer;
    }

    private static Customer mapResultSetToCustomer(ResultSet resultSet) throws SQLException {
        return new Customer(
                resultSet.getString("name"),
                resultSet.getString("address"),
                resultSet.getString("contact")
        );
    }

    public static void updateCustomer(Customer customer) {
        String updateQuery = "UPDATE customers SET address=?, contact=? WHERE name=?";
        try {
            connection = connect();
            preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.setString(1, customer.getAddress());
            preparedStatement.setString(2, customer.getContact());
            preparedStatement.setString(3, customer.getName()); // Use name as the condition
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
    }


    public static void deleteCustomer(String customerName) {
        String deleteQuery = "DELETE FROM customers WHERE name=?";
        try {
            connection = connect();
            preparedStatement = connection.prepareStatement(deleteQuery);
            preparedStatement.setString(1, customerName);
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
