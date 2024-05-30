-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 07, 2024 at 06:14 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `urbanelegance2128084`
--

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `orderID` int(11) NOT NULL,
  `userID` int(11) DEFAULT NULL,
  `productID` int(11) DEFAULT NULL,
  `orderQuantity` int(11) DEFAULT NULL,
  `orderDate` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`orderID`, `userID`, `productID`, `orderQuantity`, `orderDate`) VALUES
(20, 9, 2, 1, '2024-04-20 09:23:34'),
(21, 9, 4, 1, '2024-04-20 09:23:34'),
(22, 9, 5, 1, '2024-04-20 09:23:34'),
(23, 9, 8, 1, '2024-04-20 09:23:34'),
(24, 18, 2, 2, '2024-04-26 15:00:42'),
(25, 18, 11, 3, '2024-04-26 15:00:42'),
(30, 9, 4, 3, '2024-05-05 03:35:42'),
(31, 9, 2, 2, '2024-05-05 03:35:42');

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

CREATE TABLE `products` (
  `productID` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `description` text DEFAULT NULL,
  `category` varchar(50) NOT NULL,
  `price` decimal(10,2) NOT NULL,
  `quantity` int(11) NOT NULL,
  `imageUrl` varchar(255) DEFAULT NULL,
  `size` varchar(10) DEFAULT NULL,
  `color` varchar(20) DEFAULT NULL,
  `status` enum('active','retired') DEFAULT 'active'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `products`
--

INSERT INTO `products` (`productID`, `name`, `description`, `category`, `price`, `quantity`, `imageUrl`, `size`, `color`, `status`) VALUES
(2, 'Modern Fit Suit', 'morn fit suit for all', 'Suits', 4000.00, 5, 'product-images/modern-fit-suit.webp', '28', 'Blue', 'active'),
(4, 'Double Breasted Suit', 'In contrast to the single-breasted type of menâs suit, the double-breasted suit includes additional buttons on either side of the jacket for aesthetic purposes. The total number of buttons ranges from four to eight and typically lands at six. As a result of the extra buttons, the focal point drifts from the seam toward the sides to create the illusion of a wider frame. Whether such optical trickery is beneficial usually boils down to body type, whereas stockier men are probably better off sticking with a single-breasted suit.\r\n\r\nWhile previously relegated to formal events, the double-breasted suit is catching on among fashion-forward men, especially in Europe. The modernized suit works great with a variety of colours and most common features peak lapels on the jacket. As for the rules of buttoning a double-breasted menâs jacket, theyâre quite simple: no matter how many buttons there are, always leave the bottom button unfastened and the top button(s) fastened whether sitting or standing.\r\n\r\nButtons: 4, 6 or 8 buttons max, 6 being the most common.\r\nLapels: Peak or shawl lapels, the peak being the most common.', 'Suits', 3499.97, 7, 'product-images/double-breasted-suit.webp', '28', 'Gray', 'active'),
(5, 'Peak Lapel', 'peak lapel for any man', 'Suits', 4000.00, 5, 'product-images/peak-lapel-suit.webp', '32', 'Black', 'active'),
(8, 'Shawl Lapel', 'One look at a shawl lapel and its smooth, uninterrupted lining, and youâre already picturing an elegant black-tie affair. Thatâs because this type of suit lapel is more or less exclusively found on formal wear like tuxedos and fancy dinner jackets. Entailed in the shawl lapelâs aesthetic is a timeless sense of class and distinction. These lapels provide a welcoming complement to most menâs formal wear styles.\r\n\r\nKey features: Rounded sides, continuous curve with no hard edges, iconic smoking jacket style.', 'Suits', 3500.00, 4, 'product-images/shawl-lapel-suit.webp', '34', 'Black', 'active'),
(10, 'Round neck T-shirt', 'This can be put anytime even in summer', 'Shirts', 349.97, 5, 'product-images/T-shit3.jpg', 'Medium', 'White', 'active'),
(11, 'Sharp Nose', 'Nice formal shoes for any occation', 'Shoes', 150.99, 1495, 'product-images/shoe.jpg', '7', 'Black', 'active'),
(13, 'Round Belt', 'Belt good for any occasion', 'Belt', 149.99, 5, 'product-images/belt3.jpg', 'Medium', 'Blue', 'active'),
(14, 'Boot Shoe1', 'Boots for winter', 'shoes', 250.00, 5, 'product-images/shoe16.png', '7', 'Black', 'active'),
(16, 'Wedding Shoes', 'Attend any wedding with these nice looking shoes', 'Shoes', 299.00, 5, 'product-images/shoe6.png', '7', 'Black', 'active'),
(17, 'round T-shirt xy', 'Dark t-shirt for winter', 'Shirts', 68.00, 5, 'product-images/OIG3 (1).jpg', 'Small', 'Gray', 'active'),
(18, 'Dark t-shit 2', 'Dark shirts are good for dull men', 'Shirts', 100.00, 5, 'product-images/OIG4 (2).jpg', 'Medium', 'Gray', 'active'),
(20, 'Casual Pants', 'Nice pants for summer time', 'Pants', 149.99, 4, 'product-images/OIG1.eQSCvQoSojH4he.BH_.jpg', '28', 'Gray', 'active'),
(21, 'Slim Fit pants', 'Slim Fit pants for men', 'Pants', 199.99, 4, 'product-images/OIG2 (1).jpg', '34', 'Gray', 'active'),
(22, 'Form hats', 'Hats that any man would like', 'Hats', 165.00, 3, 'product-images/download.png', 'Small', 'Blue', 'active'),
(23, 'Hats2', 'Hats that any man would like', 'Hats', 120.00, 4, 'product-images/hat1.png', 'Medium', 'White', 'active'),
(24, 'Belt-mar0on', 'Men\'s belts', 'Belts', 250.00, 4, 'product-images/belt.jpg', 'Small', 'maroon', 'active'),
(25, 'Pape-Belts', 'Men belts', 'Belts', 349.98, 5, 'product-images/belt3.jpg', 'Large', 'Maroon', 'active'),
(26, 'Men-Jewellery', 'Men\'s jewelleries anytime', 'Jewellery', 330.00, 3, 'product-images/jewellery.jpg', 'Small', 'Black', 'active'),
(27, 'Multiple-jewel', 'Various Jewelleries for men', 'Jewellery', 379.99, 4, 'product-images/jewellery4.jpg', 'Medium', 'Black', 'active'),
(28, 'Men\'s frag1', 'Smell good frags', 'fragrance', 449.99, 5, 'product-images/fragnance.jpg', '500ml', 'maroon', 'active'),
(29, 'Men\'s frag2', 'Look good and smell good ', 'fragrances', 449.98, 5, 'product-images/fragnance6.jpg', '250ml', 'red', 'active');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `userID` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `country` varchar(50) DEFAULT NULL,
  `city` varchar(50) DEFAULT NULL,
  `postalCode` varchar(20) DEFAULT NULL,
  `street` varchar(100) DEFAULT NULL,
  `isAdmin` tinyint(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`userID`, `username`, `password`, `email`, `phone`, `country`, `city`, `postalCode`, `street`, `isAdmin`) VALUES
(1, 'pieter', '5994471abb01112afcc18159f6cc74b4f511b99806da59b3caf5a9c173cacfc5', 'pieter1@gmail.com', '57966441', 'South Africa', 'Bloemfontein', '9400', 'Willows', 1),
(2, 'ponza', '5994471abb01112afcc18159f6cc74b4f511b99806da59b3caf5a9c173cacfc5', 'ponza1@gmail.com', '0724235342', 'South Africa', 'Bloemfontein', '9300', 'Grassland', 0),
(9, 'kutloano', '91f8bee90120724ceacf5be4761e818432b0e7c1aaa231d73d8b8006adc20109', 'emkay618@gmail.com', '0823423121', 'South Africa', 'Bloemfontein', '9300', 'Willows', 0),
(18, 'fede', '5994471abb01112afcc18159f6cc74b4f511b99806da59b3caf5a9c173cacfc5', 'matubatubatlholohelo5@gmail.com', '0731213124', 'South Africa', 'Bloemfontein', '9300', 'Greenland', 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`orderID`),
  ADD KEY `userID` (`userID`),
  ADD KEY `fk_productID` (`productID`);

--
-- Indexes for table `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`productID`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`userID`),
  ADD UNIQUE KEY `unique_username` (`username`),
  ADD UNIQUE KEY `unique_email` (`email`),
  ADD UNIQUE KEY `unique_phone` (`phone`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `orders`
--
ALTER TABLE `orders`
  MODIFY `orderID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;

--
-- AUTO_INCREMENT for table `products`
--
ALTER TABLE `products`
  MODIFY `productID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `userID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`userID`) REFERENCES `users` (`userID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
