package com.example.mylittleerp;

// Actually needed imports
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;

// Imports for testing purposes
import com.example.mylittleerp.controller.MainController;
import com.example.mylittleerp.dbConnection.CustomerCRUD;
import com.example.mylittleerp.dbConnection.ProductCRUD;
import com.example.mylittleerp.dbConnection.SupplierCRUD;
import com.example.mylittleerp.model.Customer;
import com.example.mylittleerp.model.Product;
import com.example.mylittleerp.model.Sale;
import com.example.mylittleerp.model.Supplier;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class ObjectView extends JFrame {

    boolean creating = false;
    boolean editing = false;
    boolean viewing = false;
    boolean isProduct = false;
    boolean isSupplier = false;
    boolean isSale = false;
    boolean isCustomer = false;

    public ObjectView(Object obj, String modo) {
        super(obj.getClass().getSimpleName());
        switch (modo) {
            case "create" -> creating = true;
            case "edit" -> editing = true;
            case "view" -> viewing = true;
        }
        switch (obj.getClass().getSimpleName()) {
            case "Product" -> isProduct = true;
            case "Supplier" -> isSupplier = true;
            case "Sale" -> isSale = true;
            case "Customer" -> isCustomer = true;
        }

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
                    textField.setEditable(!viewing);

                    add(label);
                    add(textField);
                } catch (Exception e) {
                    e.printStackTrace();
                }

        }

        addButtons();

        pack();
        Dimension packSize = getSize();

        setSize((int) (packSize.width * 1.8f), (int) (packSize.height * 1.8f));

        setLocationRelativeTo(null);
        setVisible(true);
    }
    private void addButtons() {
        if (editing) {
            addSaveButton();
        } else if (creating) {
            addCreateButton();

        }
        addExitButton();
    }

    private void addExitButton() {
        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle exit action
                dispose();
            }
        });
        add(exitButton);
    }

    private void addSaveButton() {
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] values = getObjectValues();
                if (isProduct) {
                    ProductCRUD.updateProduct(new Product(values));
                } else if (isSupplier) {
                    SupplierCRUD.updateSupplier(new Supplier(values));
                } else if (isSale) {
                    // Handle save action for Sale
                    // Implement your save logic here
                } else if (isCustomer) {
                    CustomerCRUD.updateCustomer(new Customer(values));
                }
                dispose();
            }
        });
        add(saveButton);
    }

    private void addCreateButton() {
        JButton createButton = new JButton("Create");
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] values = getObjectValues();
                for (String value: values) {
                    System.out.println(value);
                }

                if (isProduct) {
                    ProductCRUD.addProduct(new Product(values));
                } else if (isSupplier) {
                    SupplierCRUD.addSupplier(new Supplier(values));
                } else if (isSale) {
                    // Handle save action for Sale
                    // Implement your save logic here
                } else if (isCustomer) {
                    CustomerCRUD.addCustomer(new Customer(values));
                }
                dispose();
            }
        });
        add(createButton);
    }

    private String[] getObjectValues() {
        JTextField[] textFields = extractTextFields(ObjectView.this);
        int atributesNum = textFields.length;
        String[] values = new String[atributesNum];
        for (int i = 0; i < atributesNum; i++) {
            values[i] = textFields[i].getText();
        }
        return values;
    }

    public static JTextField[] extractTextFields(ObjectView objectView) {
        java.util.List<JTextField> textFieldList = new ArrayList<>();
        extractTextFieldsRecursive(objectView.getContentPane(), textFieldList);
        return textFieldList.toArray(new JTextField[0]);
    }


    private static void extractTextFieldsRecursive(Container container, java.util.List<JTextField> textFieldList) {
        Component[] components = container.getComponents();
        for (Component component : components) {
            if (component instanceof JTextField) {
                textFieldList.add((JTextField) component);
            } else if (component instanceof Container) {
                extractTextFieldsRecursive((Container) component, textFieldList);
            }
        }
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
