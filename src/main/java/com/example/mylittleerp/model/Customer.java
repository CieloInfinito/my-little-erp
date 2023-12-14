package com.example.mylittleerp.model;

import java.util.ArrayList;
import java.util.List;

public class Customer {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public List<Sale> getPurchaseHistory() {
        return purchaseHistory;
    }

    public void setPurchaseHistory(List<Sale> purchaseHistory) {
        this.purchaseHistory = purchaseHistory;
    }
}