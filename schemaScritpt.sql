CREATE DATABASE OnlineShop;
USE OnlineShop;

-- ========================================== Table User =============================================== --
 Table: User
CREATE TABLE User (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    user_role VARCHAR(50),
    street_address VARCHAR(255),
    city VARCHAR(100),
    state VARCHAR(100),
    email VARCHAR(255) UNIQUE,
    user_job VARCHAR(255),
    password VARCHAR(255),
    credit_card VARCHAR(16),
    birthdate DATE
);


-- ========================================== Table User_phone =============================================== --

CREATE TABLE User_phone (
    user_id INT,
    phone VARCHAR(20),
    PRIMARY KEY (user_id, phone),
    FOREIGN KEY (user_id) REFERENCES User(user_id)
);



-- ========================================== Table User_interests =============================================== --

CREATE TABLE User_interests (
    user_id INT,
    interest VARCHAR(255),
    PRIMARY KEY (user_id, interest),
    FOREIGN KEY (user_id) REFERENCES User(user_id)
);



-- ========================================== Table Category =============================================== --
CREATE TABLE Category (
    categ_id INT AUTO_INCREMENT PRIMARY KEY,
    categ_name VARCHAR(255) NOT NULL,
    categ_descr VARCHAR(500)
);



-- ========================================== Table Product =============================================== --
CREATE TABLE Product (
    product_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    prod_description VARCHAR(1000),
    price DECIMAL(10, 2) NOT NULL,
    stock INT NOT NULL,
    category_id INT,
    FOREIGN KEY (category_id) REFERENCES Category(categ_id)
);


-- ========================================== Table Cart =============================================== --
CREATE TABLE Cart (
    cart_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    pay_method VARCHAR(50),
    FOREIGN KEY (user_id) REFERENCES User(user_id)
);





-- ========================================== Table WishList =============================================== --
CREATE TABLE WishList (
    wish_list_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    FOREIGN KEY (user_id) REFERENCES User(user_id)
);



-- ========================================== Table Cart_products =============================================== --
CREATE TABLE Cart_products (
    cart_id INT,
    product_id INT,
    quantity INT,
    price DECIMAL(10, 2),
    status VARCHAR(50),
    PRIMARY KEY (cart_id, product_id),
    FOREIGN KEY (cart_id) REFERENCES Cart(cart_id),
    FOREIGN KEY (product_id) REFERENCES Product(product_id)
);


-- ========================================== Table Wish_Products =============================================== --
CREATE TABLE Wish_Products (
    wish_list_id INT,
    product_id INT,
    PRIMARY KEY (wish_list_id, product_id),
    FOREIGN KEY (wish_list_id) REFERENCES WishList(wish_list_id),
    FOREIGN KEY (product_id) REFERENCES Product(product_id)
);


-- ========================================== Table Order =============================================== --
CREATE TABLE `Order` (
    order_id INT AUTO_INCREMENT PRIMARY KEY,
    order_status VARCHAR(50),
    pay_method VARCHAR(50),
    user_id INT,
    order_date DATE,
    street_address VARCHAR(255),
    city VARCHAR(100),
    state VARCHAR(100),
    FOREIGN KEY (user_id) REFERENCES User(user_id)
);


-- ========================================== Table Ordered_Products =============================================== --
Cart_products
CREATE TABLE Ordered_Products (
    order_id INT,
    product_id INT,
    order_description VARCHAR(500),
    PRIMARY KEY (order_id, product_id),
    FOREIGN KEY (order_id) REFERENCES `Order`(order_id),
    FOREIGN KEY (product_id) REFERENCES Product(product_id)
);
