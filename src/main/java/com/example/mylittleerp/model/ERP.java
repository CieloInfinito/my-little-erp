package com.example.mylittleerp.model;

import java.util.*;

public class ERP {
    Map<String, Product> inventory = new HashMap<>();
    List<Sale> sales = new ArrayList<>();
    List<Customer> customers = new ArrayList<>();
    List<Supplier> suppliers = new ArrayList<>();

    // Methods for managing inventory
    public void registerProduct(String name, String description, double price, int stockQuantity) {
        Product product = new Product(name, description, price, stockQuantity);
        inventory.put(name, product);
    }

    public void updateInventory(String productName, int soldQuantity) {
        Product product = inventory.get(productName);
        if (product != null) {
            product.stockQuantity -= soldQuantity;
        }
    }

    public void viewInventory() {
        for (Product product : inventory.values()) {
            System.out.println("Name: " + product.name + ", Stock: " + product.stockQuantity);
        }
    }

    // Methods for managing sales
    public void registerSale(List<Product> products, int quantity, Date date, String customer) {
        Sale sale = new Sale(products, quantity, date, customer);
        sales.add(sale);
        for (Product product : products) {
            updateInventory(product.name, quantity);
        }
    }

    public void generateInvoice(Sale sale) {
        // Implement logic to generate an invoice
        // It can be a console print operation for now
        System.out.println("Invoice generated for the sale");
    }

    public double calculateIncomeFromSales() {
        double income = 0;
        for (Sale sale : sales) {
            for (Product product : sale.products) {
                income += product.price * sale.quantity;
            }
        }
        return income;
    }

    // Methods for managing customers
    public void registerCustomer(String name, String address, String contact) {
        Customer customer = new Customer(name, address, contact);
        customers.add(customer);
    }

    public Customer searchCustomerByName(String name) {
        for (Customer customer : customers) {
            if (customer.name.equals(name)) {
                return customer;
            }
        }
        return null;
    }

    // Methods for managing suppliers
    public void registerSupplier(String name, String address, String contact) {
        Supplier supplier = new Supplier(name, address, contact);
        suppliers.add(supplier);
    }

    public void manageSupplierOrder(Supplier supplier, Product product, int quantity) {
        supplier.suppliedProducts.add(product);
        // More advanced logic can be implemented for managing orders
    }

    public static void main(String[] args) {
        ERP myERP = new ERP();

        // Examples of usage
        myERP.registerProduct("Product1", "Description of Product1", 10.0, 100);
        myERP.registerProduct("Product2", "Description of Product2", 20.0, 50);

        myERP.viewInventory();

        myERP.registerCustomer("Customer1", "Customer1 Address", "Customer1 Contact");

        Customer foundCustomer = myERP.searchCustomerByName("Customer1");
        if (foundCustomer != null) {
            System.out.println("Customer found: " + foundCustomer.name);
        }

        myERP.registerSupplier("Supplier1", "Supplier1 Address", "Supplier1 Contact");

        // Assuming products and customers are already registered
        List<Product> saleProducts = new ArrayList<>();
        saleProducts.add(myERP.inventory.get("Product1"));
        myERP.registerSale(saleProducts, 5, new Date(), "Customer1");

        myERP.viewInventory();
        System.out.println("Income from sales: " + myERP.calculateIncomeFromSales());
    }
}