package com.example.mylittleerp.controller;

import com.example.mylittleerp.ObjectView;
import com.example.mylittleerp.dbConnection.SupplierCRUD;
import com.example.mylittleerp.model.Customer;
import com.example.mylittleerp.model.Supplier;
import javafx.scene.control.ListView;

import javax.swing.*;

public class SupplierController {

    void addSupplier() {
        SwingUtilities.invokeLater(() -> new ObjectView(new Supplier(), "create"));
    }

    void deleteSupplier() {
        Supplier supplier = MainController.selectedSupplier;
        int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete " + supplier.getName() + "?", "Warning", JOptionPane.YES_NO_OPTION);
        if (dialogResult == JOptionPane.YES_OPTION) {
            SupplierCRUD.deleteSupplier(supplier.getName());
        }
    }

    void editSupplier() {
        SwingUtilities.invokeLater(() -> new ObjectView(MainController.selectedSupplier, "edit"));
    }

    void viewSupplier() {
        SwingUtilities.invokeLater(() -> new ObjectView(MainController.selectedSupplier, "view"));
    }
}
