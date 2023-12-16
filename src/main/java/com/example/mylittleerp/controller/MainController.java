package com.example.mylittleerp.controller;


import com.example.mylittleerp.dbConnection.CustomerCRUD;
import com.example.mylittleerp.dbConnection.ProductCRUD;
import com.example.mylittleerp.dbConnection.SupplierCRUD;
import com.example.mylittleerp.model.*;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MainController implements Initializable{
    public ProductController productController = new ProductController();
    public SaleController saleController = new SaleController();
    public CustomerController customerController = new CustomerController();
    public SupplierController supplierController = new SupplierController();
    public static Product selectedProduct;
    public static Sale selectedSale;
    public static Customer selectedCustomer;
    public static Supplier selectedSupplier;

    @FXML
    private ListView<Product> ProductListView;
    public static ObservableList<Product> ProductObservableList = FXCollections.observableArrayList();

    @FXML
    private ListView<Sale> SaleListView;
    public static ObservableList<Sale> SaleObservableList = FXCollections.observableArrayList();
    @FXML
    private ListView<Customer> CustomerListView;
    public static ObservableList<Customer> CustomerObservableList = FXCollections.observableArrayList();
    @FXML
    private ListView<Supplier> SupplierListView;
    public static ObservableList<Supplier> SupplierObservableList = FXCollections.observableArrayList();

    public void selectedOjectsThread() {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        // Programa la tarea para ejecutarse cada segundo
        scheduler.scheduleAtFixedRate(() -> {
            Platform.runLater(() ->{
                getAllSelected();
            });
        }, 0, 500, TimeUnit.MICROSECONDS);
    }
    public void updateAllThread() {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        // Programa la tarea para ejecutarse cada segundo
        scheduler.scheduleAtFixedRate(() -> {
            Platform.runLater(() ->{
                updateAll();
            });
        }, 0, 3, TimeUnit.SECONDS);
    }

    private void getAllSelected() {
        selectedProduct = getSelectedProduct();
        System.out.println(selectedProduct);
        //selectedSale = getSelectedSale();
        selectedCustomer = getSelectedCustomer();
        selectedSupplier = getSelectedSupplier();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ProductListView.setItems(ProductObservableList);
        SaleListView.setItems(SaleObservableList);
        CustomerListView.setItems(CustomerObservableList);
        SupplierListView.setItems(SupplierObservableList);
        updateAllThread();
        selectedOjectsThread();
    }
    public static void updateAll() {
        updateProducts();
        updateCustomers();
        updateSuppliers();
        // updateSales();
    }

    protected static void updateProducts() {
        // Vaciamos los anteriores datos del modelo
        ProductObservableList.clear();
        // Extraemos los datos de la base de datos
        List<Product> products = ProductCRUD.getAllProducts();
        for (Product product : products) {
            ProductObservableList.add(product);
        }
    }
    /*
    protected static void updateSales() {
        // Vaciamos los anteriores datos del modelo
        SaleObservableList.clear();
        // Extraemos los datos de la base de datos
        List<Sale> sales = ProductCRUD.getAllSales();
        for (Sale sale : sales) {
            SaleObservableList.add(sale);
        }
    }

     */
    protected static void updateCustomers() {
        // Vaciamos los anteriores datos del modelo
        CustomerObservableList.clear();
        // Extraemos los datos de la base de datos
        List<Customer> customers = CustomerCRUD.getAllCustomers();
        for (Customer customer : customers) {
            CustomerObservableList.add(customer);
        }
    }
    protected static void updateSuppliers() {
        // Vaciamos los anteriores datos del modelo
        SupplierObservableList.clear();
        // Extraemos los datos de la base de datos
        List<Supplier> suppliers = SupplierCRUD.getAllSuppliers();
        for (Supplier supplier : suppliers) {
            SupplierObservableList.add(supplier);
        }
    }
    protected Product getSelectedProduct() {
        return ProductListView.getSelectionModel().getSelectedItem();
    }


    // Method to get selected sale
    protected Sale getSelectedSale() {
        return SaleListView.getSelectionModel().getSelectedItem();
    }

    // Method to get selected customer
    protected Customer getSelectedCustomer() {
        return CustomerListView.getSelectionModel().getSelectedItem();
    }

    // Method to get selected supplier
    protected Supplier getSelectedSupplier() {
        return SupplierListView.getSelectionModel().getSelectedItem();
    }

    // Product CRUD
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

    // Sale CRUD
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

    // Customer CRUD
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

    // Supplier CRUD
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
