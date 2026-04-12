CREATE DATABASE IF NOT EXISTS ecom_db;

CREATE TABLE IF NOT EXISTS ecom_db.customer_tbl (
  customer_id INT PRIMARY KEY AUTO_INCREMENT,
  address VARCHAR(100),
  email VARCHAR(100),
  name VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS ecom_db.product_tbl (
  id INT PRIMARY KEY AUTO_INCREMENT,
  price DECIMAL(10,2),
  product_name VARCHAR(100),
  quantity INT,
  category_id BIGINT,
  CONSTRAINT fk_category
    FOREIGN KEY (category_id)
    REFERENCES ecom_db.product_category(id)
    ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS ecom_db.product_category (
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(100),
  description VARCHAR(100)
);

INSERT INTO ecom_db.product_category (name, description)
VALUES
('Electronics', 'Electronic items'),
('Clothing', 'Fashion and wearables'),
('Books', 'All kinds of books'),
('Furniture', 'Home and office furniture');


