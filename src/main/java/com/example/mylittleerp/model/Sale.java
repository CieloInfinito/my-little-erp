package com.example.mylittleerp.model;

import java.util.Date;
import java.util.List;

public class Sale {
    List<Product> products;
    int quantity;
    Date date;
    String customer;

    public Sale(List<Product> products, int quantity, Date date, String customer) {
        this.products = products;
        this.quantity = quantity;
        this.date = date;
        this.customer = customer;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }
}