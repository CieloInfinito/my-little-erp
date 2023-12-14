package com.example.mylittleerp.model;

class Product {
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
}