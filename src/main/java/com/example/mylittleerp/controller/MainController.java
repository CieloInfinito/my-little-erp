package com.example.mylittleerp.controller;


import com.example.mylittleerp.model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class MainController {
    public ProductController productController = new ProductController();
    public SaleController saleController = new SaleController();
    public CustomerController customerController = new CustomerController();
    public SupplierController supplierController = new SupplierController();

    @FXML
    private ListView<Product> ProductListView;
    @FXML
    private ListView<Sale> SaleListView;
    @FXML
    private ListView<Customer> CustomerListView;
    @FXML
    private ListView<Supplier> SupplierListView;


    // Product
    @FXML
    void addProductAction(ActionEvent event) {
        productController.addProduct();
    }

    @FXML
    void deleteProductAction(ActionEvent event) {
        productController.deleteProduct();
    }

    @FXML
    void editProductAction(ActionEvent event) {
        productController.editProduct();
    }

    @FXML
    void viewProductAction(ActionEvent event) {
        productController.viewProduct();
    }

    // Sale
    @FXML
    void addSaleAction(ActionEvent event) {
        saleController.addSale();
    }

    @FXML
    void deleteSaleAction(ActionEvent event) {
        saleController.deleteSale();
    }

    @FXML
    void editSaleAction(ActionEvent event) {
        saleController.editSale();
    }

    @FXML
    void viewSaleAction(ActionEvent event) {
        saleController.viewSale();
    }

    // Customer
    @FXML
    void addCustomerAction(ActionEvent event) {
        customerController.addCustomer();
    }

    @FXML
    void deleteCustomerAction(ActionEvent event) {
        customerController.deleteCustomer();
    }

    @FXML
    void editCustomerAction(ActionEvent event) {
        customerController.editCustomer();
    }

    @FXML
    void viewCustomerAction(ActionEvent event) {
        customerController.viewCustomer();
    }

    // Supplier
    @FXML
    void addSupplierAction(ActionEvent event) {
        supplierController.addSupplier();
    }

    @FXML
    void deleteSupplierAction(ActionEvent event) {
        supplierController.deleteSupplier();
    }

    @FXML
    void editSupplierAction(ActionEvent event) {
        supplierController.editSupplier();
    }

    @FXML
    void viewSupplierAction(ActionEvent event) {
        supplierController.viewSupplier();
    }

}
