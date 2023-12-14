package com.example.mylittleerp;

import com.example.mylittleerp.model.Customer;
import com.example.mylittleerp.model.Product;
import com.example.mylittleerp.model.Sale;
import com.example.mylittleerp.model.Supplier;


import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class ObjectView extends JFrame {

    public ObjectView(Object obj) {
        super(obj.getClass().getSimpleName() + " View");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(0, 2));

        Class<?> objClass = obj.getClass();
        Field[] fields = objClass.getDeclaredFields();

        for (Field field : fields) {
            String fieldName = field.getName();
                try {
                    field.setAccessible(true); // Allow access to private fields
                    JLabel label = new JLabel(fieldName);
                    Object fieldValue = field.get(obj);
                    JTextField textField = new JTextField(fieldValue != null ? fieldValue.toString() : "");
                    textField.setEditable(false);

                    add(label);
                    add(textField);
                } catch (Exception e) {
                    e.printStackTrace();
                }

        }

        pack();
        Dimension packSize = getSize();

        setSize((int) (packSize.width * 1.8f), (int) (packSize.height * 1.8f));

        setLocationRelativeTo(null);
        setVisible(true);
    }

    // This main is only for testing purposes
    /*
    public static void main(String[] args) {

        Product product1 = new Product("Laptop", "High-performance laptop", 999.99, 50);
        Product product2 = new Product("Smartphone", "Flagship smartphone", 699.99, 100);

        // Initialize Supplier object
        Supplier supplier = new Supplier("TechSupplier", "123 Tech Street", "supplier@example.com");
        supplier.getSuppliedProducts().add(product1);
        supplier.getSuppliedProducts().add(product2);

        // Initialize Customer object
        Customer customer = new Customer("John Doe", "456 Main Street", "customer@example.com");

        // Initialize Sale object
        List<Product> productsSold = new ArrayList<>();
        productsSold.add(product1);
        productsSold.add(product2);

        Sale sale = new Sale(productsSold, 2, new Date(), customer.getName());

        SwingUtilities.invokeLater(() -> new ObjectView(customer));
    }
    */
}
