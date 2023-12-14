-- Create the database
CREATE DATABASE IF NOT EXISTS my_little_erp_db;
USE my_little_erp_db;

-- Create the table for products (Inventory)
CREATE TABLE IF NOT EXISTS inventory (
    product_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(25) NOT NULL,
    description TEXT,
    price DECIMAL(10, 2) NOT NULL,
    stock_quantity INT NOT NULL
);

-- Create the table for customers
CREATE TABLE IF NOT EXISTS customers (
    customer_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(25) NOT NULL,
    address TEXT,
    contact VARCHAR(20)
);

-- Create the table for suppliers
CREATE TABLE IF NOT EXISTS suppliers (
    supplier_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    address TEXT,
    contact VARCHAR(20)
);

-- Create the table for sales
CREATE TABLE IF NOT EXISTS sales (
    sale_id INT PRIMARY KEY AUTO_INCREMENT,
    customer_id INT,
    sale_date DATE NOT NULL,
    total_amount DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (customer_id) REFERENCES customers(customer_id)
);

-- Sample data for inventory
INSERT INTO inventory (name, description, price, stock_quantity) VALUES
    ('Pickles', 'Crunchy and delicious', 10.0, 100),
    ('Lettuce', 'Gentle and refreshing', 8.0, 50);

-- Sample data for customers
INSERT INTO customers (name, address, contact) VALUES
    ('Pickle Picker', 'Pickle Street', 'Pickle Contact'),
    ('Lettuce Lover', 'Lettuce Street', 'Lettuce Contact');

-- Sample data for suppliers
INSERT INTO suppliers (name, address, contact) VALUES
    ('Pickle Giver', 'Pickle Field', 'Peter Pickle'),
    ('Lettuce Farmer', 'Lettuce Farm', 'Lucy Lettuce');

-- Sample data for sales
INSERT INTO sales (customer_id, sale_date, total_amount) VALUES
    (1, '2023-01-01', 50.0),
    (2, '2023-01-02', 75.0);