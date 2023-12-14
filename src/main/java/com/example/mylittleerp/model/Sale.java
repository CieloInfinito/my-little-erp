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
}