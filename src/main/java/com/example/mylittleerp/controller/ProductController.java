package com.example.mylittleerp.controller;

import com.example.mylittleerp.ObjectView;
import com.example.mylittleerp.dbConnection.ProductCRUD;
import com.example.mylittleerp.model.Customer;
import com.example.mylittleerp.model.Product;
import javafx.scene.control.ListView;

import javax.swing.*;

public class ProductController {

    void addProduct() {
        SwingUtilities.invokeLater(() -> new ObjectView(new Product(), "create"));
    }

    void deleteProduct() {
        Product product = MainController.selectedProduct;
        int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete " + product.getName() + "?", "Warning", JOptionPane.YES_NO_OPTION);
        if (dialogResult == JOptionPane.YES_OPTION) {
            ProductCRUD.deleteProduct(product.getName());
        }
    }

    void editProduct() {
        SwingUtilities.invokeLater(() -> new ObjectView(MainController.selectedProduct, "edit"));
    }

    void viewProduct() {
        SwingUtilities.invokeLater(() -> new ObjectView(MainController.selectedProduct, "view"));
    }
}
