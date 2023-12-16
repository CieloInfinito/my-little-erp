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

    public Supplier() {
    }

    public Supplier(String[] values) {
        if (values.length == 3) {
            this.name = values[0];
            this.address = values[1];
            this.contact = values[2];
            this.suppliedProducts = new ArrayList<>();
        }/*
        else if (values.length == 4) {
            this.name = values[0];
            this.address = values[1];
            this.contact = values[2];
            this.suppliedProducts =
            for (String suppliedProduct : suppliedProducts) {
                this.suppliedProducts.add(suppliedProduct);
            }
        }*/
         else {
            // Handle the case where the array does not have enough elements
            throw new IllegalArgumentException("String array for Supplier constructor must have 3 elements");
        }
    }

    @Override
    public String toString() {
        return name +
                ", " + address +
                ", " + contact +
                ", " + suppliedProducts ;
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

    public List<Product> getSuppliedProducts() {
        return suppliedProducts;
    }

    public void setSuppliedProducts(List<Product> suppliedProducts) {
        this.suppliedProducts = suppliedProducts;
    }
}