CREATE TABLE `user` (
                        `id` int PRIMARY KEY AUTO_INCREMENT,
                        `username` varchar(255) NOT NULL,
                        `firstname` varchar(255) NULL,
                        `lastname` varchar(255) NULL,
                        `email` varchar(320) NOT NULL,
                        `password` varchar(255) NOT NULL,
                        `country` varchar(255) NULL,
                        `city` varchar(255) NULL,
                        `street` varchar(255) NULL,
                        `credit_limit` decimal(10,2) DEFAULT 0.00,
                        `birthdate` datetime NULL,
                        `phone` varchar(15) NULL,
                        `date_created` timestamp DEFAULT CURRENT_TIMESTAMP,
                        `last_updated` timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE `admin` (
                         `id` int PRIMARY KEY AUTO_INCREMENT,
                         `name` varchar(255) NOT NULL,
                         `email` varchar(320) NOT NULL,
                         `password` varchar(255) NOT NULL,
                         `date_created` timestamp DEFAULT CURRENT_TIMESTAMP,
                         `last_updated` timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE `product` (
                           `id` int PRIMARY KEY AUTO_INCREMENT,
                           `name` varchar(255) NOT NULL,
                           `price` decimal(10,2) NOT NULL,
                           `description` text NULL,
                           `stock` int NOT NULL DEFAULT 0,
                           `category_id` int,
                           `date_created` timestamp DEFAULT CURRENT_TIMESTAMP,
                           `last_updated` timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                           INDEX (`category_id`)
);

CREATE TABLE `category` (
                            `id` int PRIMARY KEY AUTO_INCREMENT,
                            `name` varchar(255) NOT NULL,
                            `description` varchar(255) NULL,
                            `date_created` timestamp DEFAULT CURRENT_TIMESTAMP,
                            `last_updated` timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE `user_interest` (
                                 `user_id` int,
                                 `category_id` int,
                                 PRIMARY KEY (`user_id`, `category_id`),
                                 INDEX (`user_id`),
                                 INDEX (`category_id`)
);

CREATE TABLE `orders` (  -- Renamed from `order` to avoid reserved word conflicts
                          `id` int PRIMARY KEY AUTO_INCREMENT,
                          `user_id` int,
                          `total_price` decimal(10,2) NOT NULL,
                          `date_created` timestamp DEFAULT CURRENT_TIMESTAMP,
                          INDEX (`user_id`)
);

CREATE TABLE `cart` (
                        `user_id` int,
                        `product_id` int,
                        `quantity` int NOT NULL DEFAULT 1,
                        PRIMARY KEY (`user_id`, `product_id`),
                        INDEX (`user_id`),
                        INDEX (`product_id`)
);

CREATE TABLE `wishlist` (
                            `user_id` int,
                            `product_id` int,
                            PRIMARY KEY (`user_id`, `product_id`),
                            INDEX (`user_id`),
                            INDEX (`product_id`)
);

CREATE TABLE `order_items` (
                               `id` int PRIMARY KEY AUTO_INCREMENT,
                               `order_id` int,
                               `product_id` int,
                               `quantity` int NOT NULL DEFAULT 1,
                               `price` decimal(10,2) NOT NULL,
                               INDEX (`order_id`),
                               INDEX (`product_id`)
);

ALTER TABLE `product`
    ADD FOREIGN KEY (`category_id`) REFERENCES `category` (`id`) ON DELETE SET NULL;

ALTER TABLE `user_interest`
    ADD FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE,
  ADD FOREIGN KEY (`category_id`) REFERENCES `category` (`id`) ON DELETE CASCADE;

ALTER TABLE `orders`
    ADD FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE SET NULL;

ALTER TABLE `cart`
    ADD FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE,
  ADD FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE CASCADE;

ALTER TABLE `wishlist`
    ADD FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE,
  ADD FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE CASCADE;

ALTER TABLE `order_items`
    ADD FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`) ON DELETE SET NULL,
  ADD FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE SET NULL;
