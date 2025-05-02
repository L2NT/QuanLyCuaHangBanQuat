-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th4 23, 2025 lúc 09:27 AM
-- Phiên bản máy phục vụ: 10.4.32-MariaDB
-- Phiên bản PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `quanlycuahangquat`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chi tiet hoa don`
--

CREATE TABLE `chi tiet hoa don` (
  `MaHoaDon` varchar(50) NOT NULL,
  `MaQuat` varchar(50) NOT NULL,
  `SoLuong` int(11) NOT NULL,
  `DonGia` int(11) NOT NULL,
  `ThanhTien` int(11) NOT NULL,
  `MaBaoHanh` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `chi tiet hoa don`
--

INSERT INTO `chi tiet hoa don` (`MaHoaDon`, `MaQuat`, `SoLuong`, `DonGia`, `ThanhTien`, `MaBaoHanh`) VALUES
('HD001', 'Q004', 2, 450000, 900000, 'BH001'),
('HD002', 'Q005', 1, 750000, 750000, 'BH002'),
('HD003', 'Q006', 3, 300000, 900000, 'BH003');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chi tiet phieu phap`
--

CREATE TABLE `chi tiet phieu phap` (
  `MaPhieuNhap` varchar(50) NOT NULL,
  `MaQuat` varchar(50) NOT NULL,
  `SoLuong` int(11) NOT NULL,
  `DonGia` int(11) NOT NULL,
  `ThanhTien` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `chi tiet phieu phap`
--

INSERT INTO `chi tiet phieu phap` (`MaPhieuNhap`, `MaQuat`, `SoLuong`, `DonGia`, `ThanhTien`) VALUES
('PN001', 'Q004', 5, 400000, 2000000),
('PN002', 'Q005', 3, 700000, 2100000),
('PN003', 'Q006', 4, 280000, 1120000);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `hoa don`
--

CREATE TABLE `hoa don` (
  `MaHoaDon` varchar(50) NOT NULL,
  `MaKhachHang` varchar(50) NOT NULL,
  `MaNhanVien` varchar(50) NOT NULL,
  `NgayLap` date NOT NULL,
  `MaSuKienKM` varchar(50) NOT NULL,
  `TongTien` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `hoa don`
--

INSERT INTO `hoa don` (`MaHoaDon`, `MaKhachHang`, `MaNhanVien`, `NgayLap`, `MaSuKienKM`, `TongTien`) VALUES
('HD001', 'KH001', 'NV001', '2024-04-10', 'SK01', 900000),
('HD002', 'KH002', 'NV002', '2024-04-11', 'SK02', 750000),
('HD003', 'KH003', 'NV003', '2024-04-12', 'SK03', 900000);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `khach hang`
--

CREATE TABLE `khach hang` (
  `MaKhachHang` varchar(50) NOT NULL,
  `HoTenKH` varchar(200) NOT NULL,
  `Sdt_KH` int(11) NOT NULL,
  `DiaChiKH` varchar(300) NOT NULL,
  `TongTienDaMua` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `khach hang`
--

INSERT INTO `khach hang` (`MaKhachHang`, `HoTenKH`, `Sdt_KH`, `DiaChiKH`, `TongTienDaMua`) VALUES
('KH001', 'Nguyễn Văn A', 912345678, 'Hà Nội', 1500000),
('KH002', 'Trần Thị B', 987654321, 'TP.HCM', 2300000),
('KH003', 'Lê Văn C', 933221144, 'Đà Nẵng', 1200000);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `khuyen mai_quat`
--

CREATE TABLE `khuyen mai_quat` (
  `MaKM_Quat` varchar(50) NOT NULL,
  `MaQuat` varchar(50) NOT NULL,
  `PhanTramGiam` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `khuyen mai_quat`
--

INSERT INTO `khuyen mai_quat` (`MaKM_Quat`, `MaQuat`, `PhanTramGiam`) VALUES
('KM001', 'Q004', 10),
('KM002', 'Q005', 15),
('KM003', 'Q006', 5);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `nha cung cap`
--

CREATE TABLE `nha cung cap` (
  `MaNCC` varchar(50) NOT NULL,
  `TenNCC` varchar(200) NOT NULL,
  `DiaChiNCC` varchar(300) NOT NULL,
  `Sdt_NCC` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `nha cung cap`
--

INSERT INTO `nha cung cap` (`MaNCC`, `TenNCC`, `DiaChiNCC`, `Sdt_NCC`) VALUES
('NCC001', 'Toshiba Việt Nam', '123 Lê Lợi, TP.HCM', 123456789),
('NCC002', 'Midea Corporation', '456 Nguyễn Trãi, Hà Nội', 987654321),
('NCC003', 'Panasonic Việt Nam', '789 Trần Hưng Đạo, Đà Nẵng', 912345678);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `nhan vien`
--

CREATE TABLE `nhan vien` (
  `MaNhanVien` varchar(50) NOT NULL,
  `HoTenNV` varchar(200) NOT NULL,
  `ChucVu` varchar(200) NOT NULL,
  `Sdt_NV` int(11) NOT NULL,
  `DiaChiNV` varchar(300) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `nhan vien`
--

INSERT INTO `nhan vien` (`MaNhanVien`, `HoTenNV`, `ChucVu`, `Sdt_NV`, `DiaChiNV`) VALUES
('NV001', 'Nguyễn Văn A', 'Quản lý', 901234567, '12 Cách Mạng Tháng 8, TP.HCM'),
('NV002', 'Trần Thị B', 'Nhân viên bán hàng', 912345678, '34 Hai Bà Trưng, Hà Nội'),
('NV003', 'Lê Văn C', 'Thủ kho', 934567890, '56 Nguyễn Huệ, Đà Nẵng');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `nha san xuat`
--

CREATE TABLE `nha san xuat` (
  `MaNSX` varchar(50) NOT NULL,
  `TenNSX` varchar(200) NOT NULL,
  `DiaChi` varchar(300) NOT NULL,
  `Sdt_NSX` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `nha san xuat`
--

INSERT INTO `nha san xuat` (`MaNSX`, `TenNSX`, `DiaChi`, `Sdt_NSX`) VALUES
('NSX001', 'Panasonic', '123 Nguyễn Văn Linh, Hà Nội', 901234567),
('NSX002', 'Midea', '456 Lê Duẩn, TP.HCM', 912345678),
('NSX003', 'Asia', '789 Trường Chinh, Đà Nẵng', 923456789);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `phieu nhap`
--

CREATE TABLE `phieu nhap` (
  `MaPhieuNhap` varchar(50) NOT NULL,
  `NgayNhap` date NOT NULL,
  `MaNCC` varchar(50) NOT NULL,
  `MaNhanVien` varchar(50) NOT NULL,
  `TongTien` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `phieu nhap`
--

INSERT INTO `phieu nhap` (`MaPhieuNhap`, `NgayNhap`, `MaNCC`, `MaNhanVien`, `TongTien`) VALUES
('PN001', '2024-04-01', 'NCC001', 'NV001', 1500000),
('PN002', '2024-04-05', 'NCC002', 'NV002', 2300000),
('PN003', '2024-04-08', 'NCC003', 'NV003', 1750000);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `quan li bao hanh`
--

CREATE TABLE `quan li bao hanh` (
  `MaBaoHanh` varchar(50) NOT NULL,
  `MaQuat` varchar(50) NOT NULL,
  `MaKhachHang` varchar(50) NOT NULL,
  `ThoiGianBaoHanh` varchar(50) NOT NULL,
  `TrangThai` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `quan li bao hanh`
--

INSERT INTO `quan li bao hanh` (`MaBaoHanh`, `MaQuat`, `MaKhachHang`, `ThoiGianBaoHanh`, `TrangThai`) VALUES
('BH001', 'Q001', 'KH001', '2024-04-01 đến 2025-04-01', 'Đang xử lý'),
('BH002', 'Q002', 'KH002', '2024-04-05 đến 2025-04-05', 'Đã hoàn thành'),
('BH003', 'Q003', 'KH003', '2024-04-10 đến 2025-04-10', 'Chờ xác nhận');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `quat`
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
-- Đang đổ dữ liệu cho bảng `quat`
--

INSERT INTO `quat` (`MaQuat`, `TenQuat`, `Gia`, `MaNSX`, `NgaySanXuat`, `ChatLieu`, `ThuongHieu`, `MaLoaiSP`) VALUES
('Q001', 'Quạt hộp Toshiba 5 cánh', 450000, 'NSX003', '2024-04-01', 'Nhựa ABS', 'Toshiba', 'QA1'),
('Q002', 'Quạt công nghiệp Midea', 750000, 'NSX002', '2024-04-10', 'Kim loại', 'Midea', 'QA2'),
('Q003', 'Quạt mini Panasonic USB', 300000, 'NSX001', '2024-03-15', 'Nhựa + Kim loại', 'Panasonic', 'QA1');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `sk khuyen mai`
--

CREATE TABLE `sk khuyen mai` (
  `MaSKKhuyenMai` varchar(50) NOT NULL,
  `PhanTramGiam` int(11) NOT NULL,
  `TenKhuyenMai` varchar(200) NOT NULL,
  `NgayBatDau` date NOT NULL,
  `NgayKetThuc` date NOT NULL,
  `DieuKien` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `sk khuyen mai`
--

INSERT INTO `sk khuyen mai` (`MaSKKhuyenMai`, `PhanTramGiam`, `TenKhuyenMai`, `NgayBatDau`, `NgayKetThuc`, `DieuKien`) VALUES
('KM001', 10, 'Giảm giá mùa hè', '2024-06-01', '2024-06-30', 'Đơn hàng từ 500k'),
('KM002', 15, 'Khuyến mãi lễ 30/4', '2024-04-20', '2024-05-01', 'Mua từ 2 sản phẩm trở lên'),
('KM003', 5, 'Ưu đãi cuối tuần', '2024-05-10', '2024-05-12', 'Áp dụng cho toàn bộ quạt treo');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tai khoan`
--

CREATE TABLE `tai khoan` (
  `MaTaiKhoan` varchar(50) NOT NULL,
  `TenTaiKhoan` varchar(200) NOT NULL,
  `MatKhau` varchar(200) NOT NULL,
  `VaiTro` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `tai khoan`
--

INSERT INTO `tai khoan` (`MaTaiKhoan`, `TenTaiKhoan`, `MatKhau`, `VaiTro`) VALUES
('TK001', 'nguyenvana', 'matkhau123', 'Admin'),
('TK002', 'tranthingoc', 'ngoc2024', 'NhanVien'),
('TK003', 'lebaoduy', 'duy_quat123', 'KhachHang');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `chi tiet hoa don`
--
ALTER TABLE `chi tiet hoa don`
  ADD PRIMARY KEY (`MaHoaDon`);

--
-- Chỉ mục cho bảng `chi tiet phieu phap`
--
ALTER TABLE `chi tiet phieu phap`
  ADD PRIMARY KEY (`MaPhieuNhap`);

--
-- Chỉ mục cho bảng `hoa don`
--
ALTER TABLE `hoa don`
  ADD PRIMARY KEY (`MaHoaDon`);

--
-- Chỉ mục cho bảng `khach hang`
--
ALTER TABLE `khach hang`
  ADD PRIMARY KEY (`MaKhachHang`);

--
-- Chỉ mục cho bảng `khuyen mai_quat`
--
ALTER TABLE `khuyen mai_quat`
  ADD PRIMARY KEY (`MaKM_Quat`);

--
-- Chỉ mục cho bảng `nha cung cap`
--
ALTER TABLE `nha cung cap`
  ADD PRIMARY KEY (`MaNCC`);

--
-- Chỉ mục cho bảng `nhan vien`
--
ALTER TABLE `nhan vien`
  ADD PRIMARY KEY (`MaNhanVien`);

--
-- Chỉ mục cho bảng `nha san xuat`
--
ALTER TABLE `nha san xuat`
  ADD PRIMARY KEY (`MaNSX`);

--
-- Chỉ mục cho bảng `phieu nhap`
--
ALTER TABLE `phieu nhap`
  ADD PRIMARY KEY (`MaPhieuNhap`);

--
-- Chỉ mục cho bảng `quan li bao hanh`
--
ALTER TABLE `quan li bao hanh`
  ADD PRIMARY KEY (`MaBaoHanh`);

--
-- Chỉ mục cho bảng `quat`
--
ALTER TABLE `quat`
  ADD PRIMARY KEY (`MaQuat`);

--
-- Chỉ mục cho bảng `sk khuyen maii`
--
ALTER TABLE `sk khuyen mai`
  ADD PRIMARY KEY (`MaSKKhuyenMai`);

--
-- Chỉ mục cho bảng `tai khoan`
--
ALTER TABLE `tai khoan`
  ADD PRIMARY KEY (`MaTaiKhoan`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
