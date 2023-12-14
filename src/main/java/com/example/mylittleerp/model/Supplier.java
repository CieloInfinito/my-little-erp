package com.example.mylittleerp.model;

import java.util.ArrayList;
import java.util.List;

public class Supplier {
    String name;
    String address;
    String contact;
    List<Product> suppliedProducts;

    public Supplier(String name, String address, String contact) {
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.suppliedProducts = new ArrayList<>();
    }
}