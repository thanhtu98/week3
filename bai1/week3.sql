-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Máy chủ: localhost
-- Thời gian đã tạo: Th8 06, 2020 lúc 03:56 PM
-- Phiên bản máy phục vụ: 10.4.13-MariaDB
-- Phiên bản PHP: 7.4.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `week3`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `City`
--

CREATE TABLE `City` (
  `idx` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `population` int(11) NOT NULL,
  `codeCountry` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `City`
--

INSERT INTO `City` (`idx`, `name`, `population`, `codeCountry`) VALUES
(2, 'kula', 33, 'asia2'),
(3, 'kua wq', 12, 'asia1'),
(5, 'hcm', 34, 'asia1'),
(6, 'hello', 10, 'Africa1'),
(7, 'titi kaka', 32, 'Africa2'),
(8, 'tsu ami', 12, 'Africa1'),
(9, 'phu tho', 14, 'Africa1'),
(10, 'lucxambua', 32, 'Africa1'),
(11, 'buterfly', 23, 'Africa2'),
(13, 'kulima', 6, 'Africa2'),
(14, 'lao cai', 2, 'asia1'),
(15, 'yen bai', 1, 'asia1'),
(16, 'thai nguyen', 10, 'asia2'),
(17, 'quang ninh', 7, 'asia2'),
(18, 'Thai Binh', 10, 'asia2'),
(20, 'ca mau', 1, 'asia3'),
(21, 'binh thuan', 6, 'asia3'),
(29, 'HaNoi', 3, 'asia1'),
(32, 'halo', 14, 'asia3');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `Country`
--

CREATE TABLE `Country` (
  `code` varchar(20) NOT NULL,
  `name` varchar(50) NOT NULL,
  `continent` varchar(20) NOT NULL,
  `surfaceArea` double NOT NULL,
  `population` int(11) NOT NULL,
  `gnp` double NOT NULL,
  `capital` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `Country`
--

INSERT INTO `Country` (`code`, `name`, `continent`, `surfaceArea`, `population`, `gnp`, `capital`) VALUES
('Africa1', 'An Do', 'Africa', 61245.1, 99, 124, 6),
('Africa2', 'Li La', 'Africa', 12341, 110, 42, 7),
('asia1', 'vietnam', 'asia', 3164.12, 85, 61, 29),
('asia2', 'lao', 'asia', 941.2, 76, 12, 2),
('asia3', 'thai', 'asia', 1241.4, 67, 75, 32);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `City`
--
ALTER TABLE `City`
  ADD PRIMARY KEY (`idx`),
  ADD KEY `codeCountry` (`codeCountry`);

--
-- Chỉ mục cho bảng `Country`
--
ALTER TABLE `Country`
  ADD PRIMARY KEY (`code`);

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `City`
--
ALTER TABLE `City`
  ADD CONSTRAINT `City_ibfk_1` FOREIGN KEY (`codeCountry`) REFERENCES `Country` (`code`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
