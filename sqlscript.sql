create  database ecommerce_web_app;
use ecommerce_web_app;
CREATE TABLE user (
    user_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100) NOT NULL,
    user_role VARCHAR(50) NOT NULL,
    street_address VARCHAR(255),
    city VARCHAR(100),
    state VARCHAR(50) ,
    email VARCHAR(100) NOT NULL UNIQUE,
    user_job VARCHAR(100),
    password VARCHAR(100) NOT NULL,
    credit_card double,
    birthdate DATE
);

-- User_interests Table (Multivalued attribute for interests)
CREATE TABLE user_interests (
    user_id BIGINT NOT NULL,
    interest VARCHAR(100) NOT NULL,
    PRIMARY KEY (user_id, interest),
    FOREIGN KEY (user_id) REFERENCES user(user_id) ON DELETE CASCADE
);

-- Order Table
CREATE TABLE `order` (
    order_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    order_status VARCHAR(50),
    pay_method VARCHAR(50),
    order_date DATE NOT NULL,
    street_address VARCHAR(255) NOT NULL,
    city VARCHAR(100) NOT NULL,
    user_id BIGINT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user(user_id) 
);

-- Cart Table
CREATE TABLE cart (
    cart_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    cart_total DECIMAL(10, 2) NOT NULL,
    user_id BIGINT NOT NULL UNIQUE,
    FOREIGN KEY (user_id) REFERENCES user(user_id) ON DELETE CASCADE
);

-- Wishlist Table
CREATE TABLE wishlist (
    wishlist_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL UNIQUE,
    FOREIGN KEY (user_id) REFERENCES user(user_id) ON DELETE CASCADE
);

-- Category Table
CREATE TABLE category (
    category_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    category_name VARCHAR(100) NOT NULL,
    category_description TEXT
);

-- Product Table
CREATE TABLE product (
    product_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    product_description TEXT,
    price DECIMAL(10, 2) NOT NULL,
    stock int NOT NULL,
    category_id BIGINT NOT NULL,
    FOREIGN KEY (category_id) REFERENCES category(category_id) 
);

-- Cart_products Table (Many-to-Many between Cart and Product)
CREATE TABLE cart_products(
    cart_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    quantity INT NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    PRIMARY KEY (cart_id, product_id),
    FOREIGN KEY (cart_id) REFERENCES cart(cart_id),
    FOREIGN KEY (product_id) REFERENCES product(product_id)
);

-- Wishlist_products Table (Many-to-Many between Wishlist and Product)
CREATE TABLE wishlist_products (
    wishlist_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    PRIMARY KEY (wishlist_id, product_id),
    FOREIGN KEY (wishlist_id) REFERENCES wishlist(wishlist_id),
    FOREIGN KEY (product_id) REFERENCES product(product_id) 
);

-- Ordered_Product Table (Many-to-Many between Order and Product)
CREATE TABLE ordered_product (
    order_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    order_description TEXT,
    PRIMARY KEY (order_id, product_id),
    FOREIGN KEY (order_id) REFERENCES `order`(order_id),
    FOREIGN KEY (product_id) REFERENCES product(product_id) 
);


