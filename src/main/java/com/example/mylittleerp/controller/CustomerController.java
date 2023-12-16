package com.example.mylittleerp.controller;


import com.example.mylittleerp.ObjectView;
import com.example.mylittleerp.dbConnection.CustomerCRUD;
import com.example.mylittleerp.model.Customer;
import javafx.scene.control.ListView;

import javax.swing.*;

public class CustomerController {

    void addCustomer() {
        SwingUtilities.invokeLater(() -> new ObjectView(new Customer(), "create"));
    }

    public static void deleteCustomer() {
        Customer customer = MainController.selectedCustomer;
        int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete " + customer.getName() + "?", "Warning", JOptionPane.YES_NO_OPTION);
        if (dialogResult == JOptionPane.YES_OPTION) {
            CustomerCRUD.deleteCustomer(customer.getName());
        }
    }


    void editCustomer() {
        SwingUtilities.invokeLater(() -> new ObjectView(MainController.selectedCustomer, "edit"));
    }

    void viewCustomer() {
        SwingUtilities.invokeLater(() -> new ObjectView(MainController.selectedCustomer, "view"));
    }
}
