-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 03, 2025 at 06:26 PM
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
-- Database: `quanlycuahangquat`
--

-- --------------------------------------------------------

--
-- Table structure for table `chitiet_hoadon`
--

CREATE TABLE `chitiet_hoadon` (
  `MaHoaDon` varchar(50) NOT NULL,
  `MaQuat` varchar(50) NOT NULL,
  `SoLuong` int(11) NOT NULL,
  `DonGia` int(11) NOT NULL,
  `ThanhTien` int(11) NOT NULL,
  `MaBaoHanh` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `chitiet_hoadon`
--

INSERT INTO `chitiet_hoadon` (`MaHoaDon`, `MaQuat`, `SoLuong`, `DonGia`, `ThanhTien`, `MaBaoHanh`) VALUES
('HD001', 'Q001', 3, 550000, 1650000, 'BH001'),
('HD001', 'Q002', 2, 650000, 1300000, 'BH002'),
('HD001', 'Q003', 1, 350000, 350000, 'BH003'),
('HD002', 'Q002', 4, 650000, 2600000, 'BH004'),
('HD002', 'Q004', 1, 470000, 470000, 'BH005'),
('HD003', 'Q003', 5, 350000, 1750000, 'BH006'),
('HD003', 'Q005', 2, 1250000, 2500000, 'BH007'),
('HD003', 'Q006', 1, 2200000, 2200000, 'BH008'),
('HD004', 'Q004', 2, 470000, 940000, 'BH009'),
('HD004', 'Q007', 3, 250000, 750000, 'BH010'),
('HD005', 'Q005', 3, 1250000, 3750000, 'BH011'),
('HD005', 'Q008', 1, 980000, 980000, 'BH012'),
('HD005', 'Q009', 2, 560000, 1120000, 'BH013'),
('HD006', 'Q006', 2, 2200000, 4400000, 'BH014'),
('HD006', 'Q010', 1, 3600000, 3600000, 'BH015'),
('HD007', 'Q007', 4, 250000, 1000000, 'BH016'),
('HD007', 'Q011', 1, 750000, 750000, 'BH017'),
('HD007', 'Q012', 2, 410000, 820000, 'BH018'),
('HD008', 'Q008', 2, 980000, 1960000, 'BH019'),
('HD008', 'Q013', 1, 620000, 620000, 'BH020'),
('HD009', 'Q009', 3, 560000, 1680000, 'BH021'),
('HD009', 'Q014', 1, 2400000, 2400000, 'BH022'),
('HD009', 'Q015', 1, 490000, 490000, 'BH023'),
('HD010', 'Q001', 1, 550000, 550000, 'BH024'),
('HD010', 'Q010', 2, 3600000, 7200000, 'BH025'),
('HD011', 'Q002', 1, 650000, 650000, 'BH002'),
('HD011', 'Q011', 3, 750000, 2250000, 'BH011'),
('HD011', 'Q012', 2, 410000, 820000, 'BH012'),
('HD012', 'Q003', 2, 350000, 700000, 'BH003'),
('HD012', 'Q012', 4, 410000, 1640000, 'BH012'),
('HD013', 'Q005', 1, 1250000, 1250000, 'BH005'),
('HD013', 'Q013', 2, 620000, 1240000, 'BH013'),
('HD013', 'Q014', 1, 2400000, 2400000, 'BH014'),
('HD014', 'Q006', 1, 2200000, 2200000, 'BH006'),
('HD014', 'Q014', 3, 2400000, 7200000, 'BH014'),
('HD015', 'Q007', 1, 250000, 250000, 'BH007'),
('HD015', 'Q009', 1, 560000, 560000, 'BH009'),
('HD015', 'Q015', 2, 490000, 980000, 'BH015');

-- --------------------------------------------------------

--
-- Table structure for table `chitiet_phieuphap`
--

CREATE TABLE `chitiet_phieuphap` (
  `MaPhieuNhap` varchar(50) NOT NULL,
  `MaQuat` varchar(50) NOT NULL,
  `SoLuong` int(11) NOT NULL,
  `DonGia` int(11) NOT NULL,
  `ThanhTien` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `chitiet_phieuphap`
--

INSERT INTO `chitiet_phieuphap` (`MaPhieuNhap`, `MaQuat`, `SoLuong`, `DonGia`, `ThanhTien`) VALUES
('PN001', 'Q001', 10, 440000, 4400000),
('PN001', 'Q002', 5, 520000, 2600000),
('PN002', 'Q003', 8, 280000, 2240000),
('PN002', 'Q004', 6, 376000, 2256000),
('PN003', 'Q005', 7, 1000000, 7000000),
('PN003', 'Q006', 3, 1760000, 5280000),
('PN004', 'Q007', 4, 200000, 800000),
('PN004', 'Q008', 9, 784000, 7056000),
('PN005', 'Q009', 3, 448000, 1344000),
('PN005', 'Q010', 2, 2880000, 5760000),
('PN006', 'Q011', 10, 600000, 6000000),
('PN006', 'Q012', 5, 328000, 1640000),
('PN007', 'Q013', 4, 496000, 1984000),
('PN007', 'Q014', 6, 1920000, 11520000),
('PN008', 'Q015', 3, 392000, 1176000),
('PN009', 'Q001', 6, 440000, 2640000),
('PN009', 'Q005', 4, 1000000, 4000000),
('PN010', 'Q002', 10, 520000, 5200000),
('PN011', 'Q003', 8, 280000, 2240000),
('PN011', 'Q010', 2, 2880000, 5760000),
('PN012', 'Q006', 5, 1760000, 8800000),
('PN013', 'Q007', 7, 200000, 1400000),
('PN013', 'Q014', 3, 1920000, 5760000),
('PN014', 'Q008', 4, 784000, 3136000),
('PN015', 'Q009', 6, 448000, 2688000),
('PN015', 'Q011', 2, 600000, 1200000);

-- --------------------------------------------------------

--
-- Table structure for table `hoadon`
--

CREATE TABLE `hoadon` (
  `MaHoaDon` varchar(50) NOT NULL,
  `MaKhachHang` varchar(50) NOT NULL,
  `MaNhanVien` varchar(50) NOT NULL,
  `NgayLap` date NOT NULL,
  `MaSuKienKM` varchar(50) NOT NULL,
  `TongTien` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `hoadon`
--

INSERT INTO `hoadon` (`MaHoaDon`, `MaKhachHang`, `MaNhanVien`, `NgayLap`, `MaSuKienKM`, `TongTien`) VALUES
('HD001', 'KH003', 'NV002', '2024-05-01', '', 3300000),
('HD002', 'KH005', 'NV001', '2024-05-02', '', 3070000),
('HD003', 'KH007', 'NV003', '2024-05-03', '', 6450000),
('HD004', 'KH009', 'NV002', '2024-05-04', '', 1690000),
('HD005', 'KH011', 'NV001', '2024-05-05', '', 5850000),
('HD006', 'KH013', 'NV003', '2024-05-06', '', 8000000),
('HD007', 'KH004', 'NV002', '2024-05-07', '', 2570000),
('HD008', 'KH006', 'NV001', '2024-05-08', '', 2580000),
('HD009', 'KH008', 'NV003', '2024-05-09', '', 4570000),
('HD010', 'KH010', 'NV002', '2024-05-10', '', 7750000),
('HD011', 'KH012', 'NV001', '2024-05-11', '', 3720000),
('HD012', 'KH014', 'NV003', '2024-05-12', '', 2340000),
('HD013', 'KH015', 'NV002', '2024-05-13', '', 4890000),
('HD014', 'KH001', 'NV001', '2024-05-14', '', 9400000),
('HD015', 'KH002', 'NV003', '2024-05-15', '', 1790000);

-- --------------------------------------------------------

--
-- Table structure for table `khachhang`
--

CREATE TABLE `khachhang` (
  `MaKhachHang` varchar(50) NOT NULL,
  `HoTenKH` varchar(200) NOT NULL,
  `Sdt_KH` int(11) NOT NULL,
  `DiaChiKH` varchar(300) NOT NULL,
  `TongTienDaMua` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `khachhang`
--

INSERT INTO `khachhang` (`MaKhachHang`, `HoTenKH`, `Sdt_KH`, `DiaChiKH`, `TongTienDaMua`) VALUES
('KH001', 'Nguyễn Văn A', 912345678, 'Hà Nội', 1500000),
('KH002', 'Trần Thị B', 987654321, 'TP.HCM', 2300000),
('KH003', 'Lê Văn C', 933221144, 'Đà Nẵng', 1200000),
('KH004', 'Trần Văn Bảo', 914234567, 'TP.HCM', 850000),
('KH005', 'Lê Thị Hồng', 925345678, 'Đà Nẵng', 2000000),
('KH006', 'Phạm Văn Minh', 936456789, 'Hải Phòng', 950000),
('KH007', 'Đỗ Thị Thủy', 947567890, 'Cần Thơ', 1250000),
('KH008', 'Hoàng Văn Long', 958678901, 'Huế', 1100000),
('KH009', 'Ngô Thị Hương', 969789012, 'Bình Dương', 3000000),
('KH010', 'Bùi Văn Hùng', 970890123, 'Biên Hòa', 700000),
('KH011', 'Dương Thị Lan', 981901234, 'Quảng Ninh', 1600000),
('KH012', 'Trịnh Văn Sơn', 992012345, 'Lâm Đồng', 1300000),
('KH013', 'Cao Thị Yến', 903123456, 'Hưng Yên', 1750000),
('KH014', 'Tạ Văn Phúc', 914234561, 'Thái Bình', 900000),
('KH015', 'Phan Thị Nga', 925345672, 'Nam Định', 1950000);

-- --------------------------------------------------------

--
-- Table structure for table `khuyenmai_quat`
--

CREATE TABLE `khuyenmai_quat` (
  `MaKM_Quat` varchar(50) NOT NULL,
  `MaQuat` varchar(50) NOT NULL,
  `PhanTramGiam` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `khuyenmai_quat`
--

INSERT INTO `khuyenmai_quat` (`MaKM_Quat`, `MaQuat`, `PhanTramGiam`) VALUES
('KM001', 'Q004', 10),
('KM002', 'Q005', 15),
('KM003', 'Q006', 5);

-- --------------------------------------------------------

--
-- Table structure for table `nhacungcap`
--

CREATE TABLE `nhacungcap` (
  `MaNCC` varchar(50) NOT NULL,
  `TenNCC` varchar(200) NOT NULL,
  `DiaChiNCC` varchar(300) NOT NULL,
  `Sdt_NCC` varchar(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `nhacungcap`
--

INSERT INTO `nhacungcap` (`MaNCC`, `TenNCC`, `DiaChiNCC`, `Sdt_NCC`) VALUES
('NCC001', 'Toshiba Việt Nam', '123 Lê Lợi, TP.HCM', '0123456789'),
('NCC002', 'Midea Corporation', '456 Nguyễn Trãi, Hà Nội', '0987654321'),
('NCC003', 'Panasonic Việt Nam', '789 Trần Hưng Đạo, Đà Nẵng', '0912345678'),
('NCC004', 'LG Electronics', '123 Trần Phú, Hà Nội', '0901234567'),
('NCC005', 'Sharp Việt Nam', '456 Cách Mạng Tháng 8, TP.HCM', '0912345678'),
('NCC006', 'Samsung Việt Nam', '789 Lý Thường Kiệt, Đà Nẵng', '0923456789'),
('NCC007', 'Hitachi Việt Nam', '23 Nguyễn Huệ, TP.HCM', '0934567890'),
('NCC008', 'Daikin Việt Nam', '56 Hai Bà Trưng, Hà Nội', '0945678901'),
('NCC009', 'Electrolux Việt Nam', '34 Lê Duẩn, Đà Nẵng', '0956789012'),
('NCC010', 'TCL Việt Nam', '78 Nguyễn Văn Cừ, TP.HCM', '0967890123'),
('NCC011', 'Aqua Việt Nam', '90 Trần Hưng Đạo, Hà Nội', '0978901234'),
('NCC012', 'Sunhouse Group', '11 Nguyễn Trãi, Hà Nội', '0989012345'),
('NCC013', 'Sanaky Việt Nam', '12 Phạm Văn Đồng, TP.HCM', '0990123456'),
('NCC014', 'Kangaroo Việt Nam', '345 Nguyễn Thái Học, Đà Nẵng', '0910123456'),
('NCC015', 'BlueStone Việt Nam', '456 Tôn Đức Thắng, Cần Thơ', '0921234567');

-- --------------------------------------------------------

--
-- Table structure for table `nhanvien`
--

CREATE TABLE `nhanvien` (
  `MaNhanVien` varchar(50) NOT NULL,
  `HoTenNV` varchar(200) NOT NULL,
  `ChucVu` varchar(200) NOT NULL,
  `Sdt_NV` varchar(11) NOT NULL,
  `DiaChiNV` varchar(300) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `nhanvien`
--

INSERT INTO `nhanvien` (`MaNhanVien`, `HoTenNV`, `ChucVu`, `Sdt_NV`, `DiaChiNV`) VALUES
('NV001', 'Nguyễn Văn A', 'Quản lý', '0901234567', '12 Cách Mạng Tháng 8, TP.HCM'),
('NV002', 'Trần Thị B', 'Nhân viên bán hàng', '0912345678', '34 Hai Bà Trưng, Hà Nội'),
('NV003', 'Lê Văn C', 'Nhân viên kỹ thuật', '0934567890', '56 Nguyễn Huệ, Đà Nẵng'),
('NV004', 'Phạm Thị D', 'Nhân viên kỹ thuật', '0901112233', '45 Trần Phú, Nha Trang'),
('NV005', 'Đỗ Văn E', 'Nhân viên kỹ thuật', '0911223344', '123 Lê Duẩn, TP.HCM'),
('NV006', 'Nguyễn Thị F', 'Nhân viên kỹ thuật', '0922334455', '89 Trường Chinh, Hà Nội'),
('NV007', 'Hồ Văn G', 'Nhân viên kỹ thuật', '0933445566', '77 Nguyễn Trãi, TP.HCM'),
('NV008', 'Lý Thị H', 'Nhân viên kỹ thuật', '0944556677', '22 Phạm Văn Đồng, Cần Thơ'),
('NV009', 'Trịnh Văn I', 'Nhân viên kỹ thuật', '0955667788', '11 Nguyễn Tất Thành, Huế'),
('NV010', 'Vũ Thị J', 'Quản lý', '0966778899', '66 Điện Biên Phủ, TP.HCM'),
('NV011', 'Mai Văn K', 'Nhân viên bán hàng', '0977889900', '88 Hùng Vương, Đà Nẵng'),
('NV012', 'Bùi Thị L', 'Nhân viên bán hàng', '0988990011', '10 Lê Lợi, Hải Phòng'),
('NV013', 'Tô Văn M', 'Nhân viên bán hàng', '0911002200', '21 Nguyễn Huệ, TP.HCM'),
('NV014', 'Lâm Thị N', 'Nhân viên bán hàng', '0922113300', '33 Trần Hưng Đạo, Hà Nội'),
('NV015', 'Trương Văn O', 'Nhân viên bán hàng', '0933224400', '99 Quang Trung, Quảng Ninh');

-- --------------------------------------------------------

--
-- Table structure for table `nhasanxuat`
--

CREATE TABLE `nhasanxuat` (
  `MaNSX` varchar(50) NOT NULL,
  `TenNSX` varchar(200) NOT NULL,
  `DiaChi` varchar(300) NOT NULL,
  `Sdt_NSX` varchar(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `nhasanxuat`
--

INSERT INTO `nhasanxuat` (`MaNSX`, `TenNSX`, `DiaChi`, `Sdt_NSX`) VALUES
('NSX001', 'Panasonic', '123 Nguyễn Văn Linh, Hà Nội', '0901234567'),
('NSX002', 'Midea', '456 Lê Duẩn, TP.HCM', '0912345678'),
('NSX003', 'Asia', '789 Trường Chinh, Đà Nẵng', '0923456789'),
('NSX004', 'Toshiba', '12 Nguyễn Huệ, Hà Nội', '0934567890'),
('NSX005', 'Samsung', '45 Nguyễn Văn Linh, TP.HCM', '0945678901'),
('NSX006', 'LG', '78 Lý Tự Trọng, Đà Nẵng', '0956789012'),
('NSX007', 'Sony', '90 Nguyễn Trãi, TP.HCM', '0967890123'),
('NSX008', 'Hitachi', '21 Trần Hưng Đạo, Hà Nội', '0978901234'),
('NSX009', 'Sharp', '34 Tôn Đức Thắng, TP.HCM', '0989012345'),
('NSX010', 'Daikin', '56 Cách Mạng Tháng 8, Đà Nẵng', '0990123456'),
('NSX011', 'Electrolux', '11 Hai Bà Trưng, Hà Nội', '0901123456'),
('NSX012', 'Aqua', '67 Phạm Văn Đồng, TP.HCM', '0912233445'),
('NSX013', 'Kangaroo', '89 Nguyễn Thái Học, Hà Nội', '0923344556'),
('NSX014', 'Sanaky', '101 Trường Sa, TP.HCM', '0934455667'),
('NSX015', 'BlueStone', '112 Hoàng Văn Thụ, Đà Nẵng', '0945566778');

-- --------------------------------------------------------

--
-- Table structure for table `phieunhap`
--

CREATE TABLE `phieunhap` (
  `MaPhieuNhap` varchar(50) NOT NULL,
  `NgayNhap` date NOT NULL,
  `MaNCC` varchar(50) NOT NULL,
  `MaNhanVien` varchar(50) NOT NULL,
  `TongTien` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `phieunhap`
--

INSERT INTO `phieunhap` (`MaPhieuNhap`, `NgayNhap`, `MaNCC`, `MaNhanVien`, `TongTien`) VALUES
('PN001', '2024-04-01', 'NCC001', 'NV001', 7000000),
('PN002', '2024-04-05', 'NCC002', 'NV002', 4496000),
('PN003', '2024-04-08', 'NCC003', 'NV003', 12280000),
('PN004', '2024-04-04', 'NCC001', 'NV001', 7856000),
('PN005', '2024-04-05', 'NCC002', 'NV002', 7104000),
('PN006', '2024-04-06', 'NCC003', 'NV003', 7640000),
('PN007', '2024-04-07', 'NCC001', 'NV001', 13504000),
('PN008', '2024-04-08', 'NCC002', 'NV002', 1176000),
('PN009', '2024-04-09', 'NCC003', 'NV003', 6640000),
('PN010', '2024-04-10', 'NCC001', 'NV001', 5200000),
('PN011', '2024-04-11', 'NCC002', 'NV002', 8000000),
('PN012', '2024-04-12', 'NCC003', 'NV003', 8800000),
('PN013', '2024-04-13', 'NCC001', 'NV001', 7160000),
('PN014', '2024-04-14', 'NCC002', 'NV002', 3136000),
('PN015', '2024-04-15', 'NCC003', 'NV003', 3888000);

-- --------------------------------------------------------

--
-- Table structure for table `quanlibaohanh`
--

CREATE TABLE `quanlibaohanh` (
  `MaBaoHanh` varchar(50) NOT NULL,
  `MaQuat` varchar(50) NOT NULL,
  `MaKhachHang` varchar(50) NOT NULL,
  `ThoiGianBaoHanh` date NOT NULL,
  `TrangThai` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `quanlibaohanh`
--

INSERT INTO `quanlibaohanh` (`MaBaoHanh`, `MaQuat`, `MaKhachHang`, `ThoiGianBaoHanh`, `TrangThai`) VALUES
('BH003', 'QQ03', 'KH003', '2025-05-03', 'Còn hạn'),
('BH004', 'QQ04', 'KH005', '2025-05-03', 'Còn hạn'),
('BH005', 'QQ01', 'KH005', '2025-05-03', 'Còn hạn'),
('BH006', 'QQ05', 'KH007', '2025-05-03', 'Còn hạn'),
('BH007', 'QQ06', 'KH007', '2025-05-03', 'Còn hạn'),
('BH008', 'QQ04', 'KH009', '2025-05-03', 'Còn hạn'),
('BH009', 'QQ07', 'KH009', '2025-05-03', 'Còn hạn'),
('BH010', 'QQ03', 'KH011', '2025-05-03', 'Còn hạn'),
('BH011', 'QQ05', 'KH011', '2025-05-03', 'Còn hạn'),
('BH012', 'QQ08', 'KH013', '2025-05-03', 'Còn hạn'),
('BH013', 'QQ09', 'KH013', '2025-05-03', 'Còn hạn'),
('BH014', 'QQ06', 'KH004', '2025-05-03', 'Còn hạn'),
('BH015', 'QQ07', 'KH006', '2025-05-03', 'Còn hạn'),
('BH016', 'QQ10', 'KH006', '2025-05-03', 'Còn hạn'),
('BH017', 'QQ01', 'KH004', '2025-05-03', 'Còn hạn'),
('BH018', 'QQ02', 'KH004', '2025-05-03', 'Còn hạn'),
('BH019', 'QQ08', 'KH010', '2025-05-03', 'Còn hạn'),
('BH020', 'QQ11', 'KH010', '2025-05-03', 'Còn hạn'),
('BH021', 'QQ03', 'KH012', '2025-05-03', 'Còn hạn'),
('BH022', 'QQ05', 'KH014', '2025-05-03', 'Còn hạn'),
('BH023', 'QQ10', 'KH014', '2025-05-03', 'Còn hạn'),
('BH024', 'QQ12', 'KH015', '2025-05-03', 'Còn hạn'),
('BH025', 'QQ13', 'KH015', '2025-05-03', 'Còn hạn');

-- --------------------------------------------------------

--
-- Table structure for table `quat`
--

CREATE TABLE `quat` (
  `MaQuat` varchar(50) NOT NULL,
  `TenQuat` varchar(200) NOT NULL,
  `Gia` int(11) NOT NULL,
  `MaNSX` varchar(50) NOT NULL,
  `NgaySanXuat` date NOT NULL,
  `ChatLieu` varchar(100) NOT NULL,
  `ThuongHieu` varchar(100) NOT NULL,
  `MaLoaiSP` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `quat`
--

INSERT INTO `quat` (`MaQuat`, `TenQuat`, `Gia`, `MaNSX`, `NgaySanXuat`, `ChatLieu`, `ThuongHieu`, `MaLoaiSP`) VALUES
('Q001', 'Quạt đứng Toshiba Remote', 550000, 'NSX003', '2024-03-01', 'Nhựa cao cấp', 'Toshiba', 'QA1'),
('Q002', 'Quạt treo Midea 3 cánh', 650000, 'NSX002', '2024-03-15', 'Kim loại + nhựa', 'Midea', 'QA2'),
('Q003', 'Quạt mini Panasonic sạc USB', 350000, 'NSX001', '2024-02-20', 'Nhựa ABS', 'Panasonic', 'QA1'),
('Q004', 'Quạt Hộp Toshiba', 470000, 'NSX004', '2023-04-12', 'Nhựa cứng', 'Toshiba', 'LSP02'),
('Q005', 'Quạt Hơi Nước Samsung', 1250000, 'NSX005', '2023-02-05', 'Nhựa', 'Samsung', 'LSP03'),
('Q006', 'Quạt Điều Hòa LG', 2200000, 'NSX006', '2023-01-25', 'Hợp kim', 'LG', 'LSP03'),
('Q007', 'Quạt Mini Sony', 250000, 'NSX007', '2023-07-18', 'Nhựa mềm', 'Sony', 'LSP04'),
('Q008', 'Quạt Đứng Hitachi', 980000, 'NSX008', '2023-08-08', 'Nhựa', 'Hitachi', 'LSP01'),
('Q009', 'Quạt Treo Sharp', 560000, 'NSX009', '2023-09-01', 'Kim loại', 'Sharp', 'LSP02'),
('Q010', 'Quạt Công Nghiệp Daikin', 3600000, 'NSX010', '2023-07-10', 'Thép', 'Daikin', 'LSP05'),
('Q011', 'Quạt Sạc Electrolux', 750000, 'NSX011', '2023-03-23', 'Nhựa', 'Electrolux', 'LSP04'),
('Q012', 'Quạt Bàn Aqua', 410000, 'NSX012', '2023-05-30', 'Nhôm', 'Aqua', 'LSP01'),
('Q013', 'Quạt Đứng Kangaroo', 620000, 'NSX013', '2023-06-06', 'Nhựa', 'Kangaroo', 'LSP01'),
('Q014', 'Quạt Điều Hòa Sanaky', 2400000, 'NSX014', '2023-01-11', 'Kim loại', 'Sanaky', 'LSP03'),
('Q015', 'Quạt Hộp BlueStone', 490000, 'NSX015', '2023-04-22', 'Nhựa', 'BlueStone', 'LSP02');

-- --------------------------------------------------------

--
-- Table structure for table `skkhuyenmai`
--

CREATE TABLE `skkhuyenmai` (
  `MaSKKhuyenMai` varchar(50) NOT NULL,
  `PhanTramGiam` int(11) NOT NULL,
  `TenKhuyenMai` varchar(200) NOT NULL,
  `NgayBatDau` date NOT NULL,
  `NgayKetThuc` date NOT NULL,
  `DieuKien` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `skkhuyenmai`
--

INSERT INTO `skkhuyenmai` (`MaSKKhuyenMai`, `PhanTramGiam`, `TenKhuyenMai`, `NgayBatDau`, `NgayKetThuc`, `DieuKien`) VALUES
('KM001', 10, 'Giảm giá mùa hè', '2024-06-01', '2024-06-30', 'Đơn hàng từ 500k'),
('KM002', 15, 'Khuyến mãi lễ 30/4', '2024-04-20', '2024-05-01', 'Mua từ 2 sản phẩm trở lên'),
('KM003', 5, 'Ưu đãi cuối tuần', '2024-05-10', '2024-05-12', 'Áp dụng cho toàn bộ quạt treo');

-- --------------------------------------------------------

--
-- Table structure for table `taikhoan`
--

CREATE TABLE `taikhoan` (
  `MaTaiKhoan` varchar(10) NOT NULL,
  `TenTaiKhoan` varchar(100) NOT NULL,
  `MatKhau` varchar(100) NOT NULL,
  `VaiTro` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `taikhoan`
--

INSERT INTO `taikhoan` (`MaTaiKhoan`, `TenTaiKhoan`, `MatKhau`, `VaiTro`) VALUES
('TK001', 'admin', '123', 'Admin'),
('TK002', 'nv1', '123', 'NhanVien'),
('TK003', 'nv2', '123', 'KhachHang');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `chitiet_hoadon`
--
ALTER TABLE `chitiet_hoadon`
  ADD PRIMARY KEY (`MaHoaDon`,`MaQuat`);

--
-- Indexes for table `chitiet_phieuphap`
--
ALTER TABLE `chitiet_phieuphap`
  ADD PRIMARY KEY (`MaPhieuNhap`,`MaQuat`);

--
-- Indexes for table `hoadon`
--
ALTER TABLE `hoadon`
  ADD PRIMARY KEY (`MaHoaDon`);

--
-- Indexes for table `khachhang`
--
ALTER TABLE `khachhang`
  ADD PRIMARY KEY (`MaKhachHang`);

--
-- Indexes for table `khuyenmai_quat`
--
ALTER TABLE `khuyenmai_quat`
  ADD PRIMARY KEY (`MaKM_Quat`);

--
-- Indexes for table `nhacungcap`
--
ALTER TABLE `nhacungcap`
  ADD PRIMARY KEY (`MaNCC`);

--
-- Indexes for table `nhanvien`
--
ALTER TABLE `nhanvien`
  ADD PRIMARY KEY (`MaNhanVien`);

--
-- Indexes for table `nhasanxuat`
--
ALTER TABLE `nhasanxuat`
  ADD PRIMARY KEY (`MaNSX`);

--
-- Indexes for table `phieunhap`
--
ALTER TABLE `phieunhap`
  ADD PRIMARY KEY (`MaPhieuNhap`);

--
-- Indexes for table `quanlibaohanh`
--
ALTER TABLE `quanlibaohanh`
  ADD PRIMARY KEY (`MaBaoHanh`);

--
-- Indexes for table `quat`
--
ALTER TABLE `quat`
  ADD PRIMARY KEY (`MaQuat`);

--
-- Indexes for table `skkhuyenmai`
--
ALTER TABLE `skkhuyenmai`
  ADD PRIMARY KEY (`MaSKKhuyenMai`);

--
-- Indexes for table `taikhoan`
--
ALTER TABLE `taikhoan`
  ADD PRIMARY KEY (`MaTaiKhoan`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
