-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Sep 18, 2024 at 11:15 PM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ecommerce`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `id` int(11) NOT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `date_created` datetime(6) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `last_updated` datetime(6) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`id`, `created_by`, `date_created`, `email`, `last_updated`, `name`, `password`) VALUES
(1, 'Haroun', '2024-09-15 01:24:45.000000', 'haroun@gmail.com', '2024-09-15 01:24:45.000000', 'Haroun', '1234');

-- --------------------------------------------------------

--
-- Table structure for table `cart`
--

CREATE TABLE `cart` (
  `quantity` int(11) NOT NULL,
  `product_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE `category` (
  `id` bigint(20) NOT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `date_created` datetime(6) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `last_updated` datetime(6) NOT NULL,
  `name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`id`, `created_by`, `date_created`, `description`, `last_updated`, `name`) VALUES
(1, NULL, '2024-09-15 01:24:45.000000', NULL, '2024-09-15 01:24:45.000000', 'Men\'s Shoes'),
(2, NULL, '2024-09-15 01:24:45.000000', NULL, '2024-09-15 01:24:45.000000', 'Women\'s Shoes'),
(3, NULL, '2024-09-15 01:24:45.000000', NULL, '2024-09-15 01:24:45.000000', 'Kids\' Shoes'),
(4, NULL, '2024-09-15 01:24:45.000000', NULL, '2024-09-15 01:24:45.000000', 'Sports Shoes'),
(5, NULL, '2024-09-15 01:24:45.000000', NULL, '2024-09-15 01:24:45.000000', 'Luxury Sneakers'),
(6, NULL, '2024-09-15 01:24:45.000000', NULL, '2024-09-15 01:24:45.000000', 'Casual Sneakers');

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `id` bigint(20) NOT NULL,
  `date_created` datetime(6) DEFAULT NULL,
  `total_price` decimal(38,2) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`id`, `date_created`, `total_price`, `user_id`) VALUES
(1, '2024-09-15 01:24:45.000000', 300.00, 1),
(2, '2024-09-15 01:24:45.000000', 150.00, NULL),
(3, '2024-09-15 01:24:45.000000', 250.00, 3),
(4, '2024-09-15 01:24:45.000000', 400.00, 4),
(5, '2024-09-15 01:24:45.000000', 500.00, NULL),
(6, '2024-09-15 05:00:11.000000', 150.00, 6),
(7, '2024-09-15 05:04:56.000000', 800.00, 6),
(8, '2024-09-15 06:37:55.000000', 850.00, 6),
(9, '2024-09-15 16:34:16.000000', 270.00, 6),
(10, '2024-09-15 21:47:11.000000', 840.00, 6),
(11, '2024-09-16 04:54:06.000000', 1050.00, 15),
(12, '2024-09-16 05:05:33.000000', 1000.00, 15),
(13, '2024-09-16 06:24:35.000000', 530.00, 15),
(14, '2024-09-16 06:25:47.000000', 200.00, 15),
(15, '2024-09-16 06:26:12.000000', 400.00, 15),
(16, '2024-09-16 06:26:37.000000', 200.00, 15),
(17, '2024-09-16 06:27:14.000000', 150.00, 15),
(18, '2024-09-16 17:42:14.000000', 440.00, 15),
(19, '2024-09-18 12:20:36.000000', 360.00, 15),
(20, '2024-09-18 15:02:01.000000', 120.00, 15),
(21, '2024-09-18 15:16:43.000000', 200.00, 15),
(22, '2024-09-18 20:56:47.000000', 380.00, 15),
(23, '2024-09-18 20:59:39.000000', 180.00, 15),
(24, '2024-09-18 21:01:42.000000', 660.00, 15);

-- --------------------------------------------------------

--
-- Table structure for table `order_items`
--

CREATE TABLE `order_items` (
  `id` bigint(20) NOT NULL,
  `price` decimal(38,2) NOT NULL,
  `quantity` int(11) NOT NULL,
  `order_id` bigint(20) DEFAULT NULL,
  `product_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `order_items`
--

INSERT INTO `order_items` (`id`, `price`, `quantity`, `order_id`, `product_id`) VALUES
(1, 150.00, 2, 1, 1),
(2, 180.00, 1, 2, 2),
(3, 120.00, 3, 3, 3),
(4, 150.00, 1, 6, 1),
(5, 200.00, 4, 7, 4),
(6, 150.00, 3, 8, 1),
(7, 200.00, 2, 8, 4),
(8, 120.00, 1, 9, 3),
(9, 150.00, 1, 9, 1),
(10, 200.00, 3, 10, 4),
(11, 120.00, 2, 10, 3),
(12, 200.00, 3, 11, 4),
(13, 150.00, 3, 11, 1),
(14, 200.00, 5, 12, 4),
(15, 180.00, 1, 13, 2),
(16, 150.00, 1, 13, 1),
(17, 200.00, 1, 13, 4),
(18, 200.00, 1, 14, 4),
(19, 200.00, 2, 15, 4),
(20, 200.00, 1, 16, 4),
(21, 150.00, 1, 17, 1),
(22, 120.00, 2, 18, 3),
(23, 200.00, 1, 18, 4),
(24, 180.00, 2, 19, 2),
(25, 120.00, 1, 20, 3),
(26, 200.00, 1, 21, 4),
(27, 180.00, 1, 22, 2),
(28, 200.00, 1, 22, 4),
(29, 180.00, 1, 23, 2),
(30, 120.00, 1, 24, 3),
(31, 180.00, 3, 24, 2);

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `id` bigint(20) NOT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `date_created` datetime(6) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `product_image` varchar(255) DEFAULT NULL,
  `last_updated` datetime(6) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `price` decimal(38,2) NOT NULL,
  `shoe_color` varchar(255) DEFAULT NULL,
  `shoe_size` varchar(255) DEFAULT NULL,
  `stock` int(11) NOT NULL,
  `category_id` bigint(20) NOT NULL,
  `brand` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`id`, `created_by`, `date_created`, `description`, `product_image`, `last_updated`, `name`, `price`, `shoe_color`, `shoe_size`, `stock`, `category_id`, `brand`) VALUES
(1, NULL, '2024-09-15 01:24:45.000000', NULL, NULL, '2024-09-15 01:24:45.000000', 'Nike Air', 150.00, NULL, NULL, 0, 1, NULL),
(2, NULL, '2024-09-15 01:24:45.000000', NULL, NULL, '2024-09-15 01:24:45.000000', 'Adidas Ultraboost', 180.00, NULL, NULL, 7, 4, NULL),
(3, NULL, '2024-09-15 01:24:45.000000', NULL, NULL, '2024-09-15 01:24:45.000000', 'Puma Sneakers', 120.00, NULL, NULL, 1, 6, NULL),
(4, NULL, '2024-09-15 01:25:08.000000', 'Added by Admin', NULL, '2024-09-15 01:25:08.000000', 'nike2', 200.00, NULL, NULL, 175, 1, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL,
  `birthdate` date DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `credit_limit` decimal(38,2) DEFAULT NULL,
  `date_created` date NOT NULL,
  `email` varchar(255) NOT NULL,
  `firstname` varchar(255) DEFAULT NULL,
  `lastname` varchar(255) DEFAULT NULL,
  `last_updated` date NOT NULL,
  `password` varchar(255) NOT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `street` varchar(255) DEFAULT NULL,
  `username` varchar(255) NOT NULL,
  `email_status` tinyint(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `birthdate`, `city`, `country`, `credit_limit`, `date_created`, `email`, `firstname`, `lastname`, `last_updated`, `password`, `phone`, `street`, `username`, `email_status`) VALUES
(1, '1990-01-01', 'New York', 'USA', 5000.00, '2024-09-15', 'haroun@example.com', 'Haroun', 'Smith', '2024-09-15', '$2a$10$ekShelLZSnNrslh9EVqTvuoe7HiZXeARL9ncUgN0P9hWeq3yYFuyW', '555-1234', '5th Avenue', 'Haroun00Smith', NULL),
(3, '1992-05-20', 'Chicago', 'USA', 4000.00, '2024-09-15', 'ghandy@example.com', 'Ghandy', 'Brown', '2024-09-15', '$2a$10$5VEHFKb6GJvcge2pXQF2bunmMpGSOcEw8bN6pt2777aoAB5tKzaWi', '555-6789', 'Lake Shore Drive', 'Ghandy00Brown', NULL),
(4, '1995-07-30', 'Miami', 'USA', 3500.00, '2024-09-15', 'alice@example.com', 'Alice', 'Davis', '2024-09-15', '$2a$10$2iSxTI8En74N0HGSc7Z4/O2w0g67yYkHci38v2wwJeGf7/ggQMuuS', '555-9876', 'Ocean Drive', 'Alice00Davis', NULL),
(6, '1222-12-02', 'asd', 'asd', 18890.00, '2024-09-15', 't@gmail.com', 'tony', 'tony', '2024-09-15', '$2a$10$CkJjc/qMl/zyuAqQn/y0A.q4rjM./98de4Fs5D6i7uG57CjgIRCEu', '1231231231', 'asd', 'antony', NULL),
(7, '1212-12-12', 'abdul', 'abdul', 12121210.00, '2024-09-15', 'abdul@gmail.com', 'adbul', 'abdul', '2024-09-15', '$2a$10$3XY4DdyNgyU1iIEYb1QN2.29rjMa1aOS0vT6xvbEugT7HccaYD4R6', '1231231231', 'abdul', 'abdul', NULL),
(8, '1233-03-12', 'dsa', NULL, 123123.00, '2024-09-15', 'noura@gmail.com', 'asd', 'asd', '2024-09-15', '$2a$10$XQSjgsdjJPgwiUosd7DsduA/121epJrmx5uFfvuRg4pZjxOFbfdd.', '1231323123', 'asd', 'noura', NULL),
(14, '1111-12-31', '123123', '123123', 5214323.00, '2024-09-15', 'asd@gmail.com', 'asd', 'asd', '2024-09-15', '$2a$10$ICeiuB9SLepMr8wmUrCkauoELhQADvAEvwNN.iQW0DsWRm31gJw/q', '1231231231', '123123', 'asd', NULL),
(15, '1000-12-12', 'asd', 'asd', 123213117343.00, '2024-09-16', 'saber@gmail.com', 'saber', 'saber', '2024-09-18', '$2a$10$unYK5bi4U1VFob4Gj4JFT.Akox7e4NuPSQed97vquY1XxpuSqIw5q', '12312321313', 'asd', 'saber', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `user_interest`
--

CREATE TABLE `user_interest` (
  `user_id` bigint(20) NOT NULL,
  `category_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user_interest`
--

INSERT INTO `user_interest` (`user_id`, `category_id`) VALUES
(7, 1),
(7, 3),
(7, 4),
(7, 5),
(7, 6),
(8, 6),
(14, 3),
(15, 4),
(15, 5),
(15, 6);

-- --------------------------------------------------------

--
-- Table structure for table `wishlist`
--

CREATE TABLE `wishlist` (
  `user_id` bigint(20) NOT NULL,
  `product_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKc0r9atamxvbhjjvy5j8da1kam` (`email`);

--
-- Indexes for table `cart`
--
ALTER TABLE `cart`
  ADD PRIMARY KEY (`product_id`,`user_id`),
  ADD KEY `FKl70asp4l4w0jmbm1tqyofho4o` (`user_id`);

--
-- Indexes for table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKel9kyl84ego2otj2accfd8mr7` (`user_id`);

--
-- Indexes for table `order_items`
--
ALTER TABLE `order_items`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKbioxgbv59vetrxe0ejfubep1w` (`order_id`),
  ADD KEY `FKlf6f9q956mt144wiv6p1yko16` (`product_id`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK1mtsbur82frn64de7balymq9s` (`category_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKob8kqyqqgmefl0aco34akdtpe` (`email`),
  ADD UNIQUE KEY `UKsb8bbouer5wak8vyiiy4pf2bx` (`username`);

--
-- Indexes for table `user_interest`
--
ALTER TABLE `user_interest`
  ADD PRIMARY KEY (`user_id`,`category_id`),
  ADD KEY `FK25swxjvbuqcjoov4jphqb3s5t` (`category_id`);

--
-- Indexes for table `wishlist`
--
ALTER TABLE `wishlist`
  ADD PRIMARY KEY (`user_id`,`product_id`),
  ADD KEY `FKqchevbfw5wq0f4uqacns02rp7` (`product_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `category`
--
ALTER TABLE `category`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `orders`
--
ALTER TABLE `orders`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT for table `order_items`
--
ALTER TABLE `order_items`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;

--
-- AUTO_INCREMENT for table `product`
--
ALTER TABLE `product`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `cart`
--
ALTER TABLE `cart`
  ADD CONSTRAINT `FK3d704slv66tw6x5hmbm6p2x3u` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  ADD CONSTRAINT `FKl70asp4l4w0jmbm1tqyofho4o` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Constraints for table `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `FKel9kyl84ego2otj2accfd8mr7` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE SET NULL;

--
-- Constraints for table `order_items`
--
ALTER TABLE `order_items`
  ADD CONSTRAINT `FKbioxgbv59vetrxe0ejfubep1w` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`),
  ADD CONSTRAINT `FKlf6f9q956mt144wiv6p1yko16` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`);

--
-- Constraints for table `product`
--
ALTER TABLE `product`
  ADD CONSTRAINT `FK1mtsbur82frn64de7balymq9s` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`);

--
-- Constraints for table `user_interest`
--
ALTER TABLE `user_interest`
  ADD CONSTRAINT `FK25swxjvbuqcjoov4jphqb3s5t` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`),
  ADD CONSTRAINT `FKdi9smphhv09dottb2sc1j3k64` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Constraints for table `wishlist`
--
ALTER TABLE `wishlist`
  ADD CONSTRAINT `FKd4r80jm8s41fgoa0xv9yy5lo8` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FKqchevbfw5wq0f4uqacns02rp7` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
