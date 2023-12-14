package com.example.mylittleerp.model;

import java.util.ArrayList;
import java.util.List;

class Customer {
    String name;
    String address;
    String contact;
    List<Sale> purchaseHistory;

    public Customer(String name, String address, String contact) {
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.purchaseHistory = new ArrayList<>();
    }
}