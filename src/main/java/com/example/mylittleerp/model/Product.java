package com.example.mylittleerp.model;

public class Product {
    String name;
    String description;
    double price;
    int stockQuantity;

    public Product(String name, String description, double price, int stockQuantity) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    public Product() {
    }
    public Product(String[] values) {
        if (values.length == 4) {
            this.name = values[0];
            this.description = values[1];
            this.price = Double.parseDouble(values[2]);
            this.stockQuantity = Integer.parseInt(values[3]);
        } else {
            // Handle the case where the array does not have enough elements
            throw new IllegalArgumentException("String array for Product constructor must have 4 elements");
        }
    }

    @Override
    public String toString() {
        return getName();
    }
    public String toStringWithAttributes() {
        return name + " " + description + " " + price + " " + stockQuantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }
}