-- --------------------------------------------------------
-- Database: `quanlycuahangquat`
-- --------------------------------------------------------

DROP DATABASE IF EXISTS `quanlycuahangquat`;
CREATE DATABASE `quanlycuahangquat` CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE `quanlycuahangquat`;

-- --------------------------------------------------------
-- Table structure for table `nhanvien`
-- --------------------------------------------------------
CREATE TABLE `nhanvien` (
  `MaNhanVien` VARCHAR(50) NOT NULL,
  `HoTenNV`    VARCHAR(200) NOT NULL,
  `ChucVu`     VARCHAR(200) NOT NULL,
  `Sdt_NV`     VARCHAR(11)  NOT NULL,
  `DiaChiNV`   VARCHAR(300) NOT NULL,
  PRIMARY KEY (`MaNhanVien`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Sample data
INSERT INTO `nhanvien` (`MaNhanVien`, `HoTenNV`,      `ChucVu`,               `Sdt_NV`,     `DiaChiNV`) VALUES
('NV001','Nguyễn Văn A', 'Quản lý',             '0901234567','12 Cách Mạng Tháng 8, TP.HCM'),
('NV002','Trần Thị B',   'Nhân viên',           '0912345678','34 Hai Bà Trưng, Hà Nội'),
('NV003','Lê Văn C',     'Nhân viên',           '0934567890','56 Nguyễn Huệ, Đà Nẵng');

-- --------------------------------------------------------
-- Table structure for table `taikhoan`
-- --------------------------------------------------------
CREATE TABLE `taikhoan` (
  `MaTaiKhoan`  VARCHAR(10)  NOT NULL,
  `TenTaiKhoan` VARCHAR(100) NOT NULL,
  `MatKhau`     VARCHAR(100) NOT NULL,
  `VaiTro`      VARCHAR(50)  NOT NULL,
  `MaNhanVien`  VARCHAR(50)  NULL,
  PRIMARY KEY (`MaTaiKhoan`),
  INDEX idx_tk_nhanvien (`MaNhanVien`),
  CONSTRAINT fk_tk_nv FOREIGN KEY (`MaNhanVien`)
    REFERENCES `nhanvien`(`MaNhanVien`)
    ON UPDATE CASCADE
    ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Sample accounts: admin (no NV), nv1→NV002
INSERT INTO `taikhoan` (`MaTaiKhoan`,`TenTaiKhoan`,`MatKhau`,`VaiTro`,`MaNhanVien`) VALUES
('TK001','admin','123','Admin',    NULL),
('TK002','nv1',  '123','NhanVien','NV002');

-- --------------------------------------------------------
-- Table structure for table `khachhang`
-- --------------------------------------------------------
CREATE TABLE `khachhang` (
  `MaKhachHang`    VARCHAR(50)  NOT NULL,
  `HoTenKH`        VARCHAR(200) NOT NULL,
  `Sdt_KH`         VARCHAR(11)  NOT NULL,
  `DiaChiKH`       VARCHAR(300) NOT NULL,
  `TongTienDaMua`  INT          NOT NULL,
  PRIMARY KEY (`MaKhachHang`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Sample
INSERT INTO `khachhang` VALUES
('KH001','Nguyễn Văn A','0912345678','Hà Nội',1500000),
('KH002','Trần Thị B','0987654321','TP.HCM',2300000);

-- --------------------------------------------------------
-- Table structure for table `su_kien_khuyen_mai`
-- --------------------------------------------------------
CREATE TABLE `su_kien_khuyen_mai` (
  `MaSKKhuyenMai` VARCHAR(50)  NOT NULL,
  `TenKhuyenMai`  VARCHAR(200) NOT NULL,
  `PhanTramGiam`  INT          NOT NULL,
  `NgayBatDau`    DATE         NOT NULL,
  `NgayKetThuc`   DATE         NOT NULL,
  `DieuKien`      VARCHAR(200) NOT NULL,
  PRIMARY KEY (`MaSKKhuyenMai`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Sample
INSERT INTO `su_kien_khuyen_mai` VALUES
('KM01','Giảm giá mùa hè',10,'2024-06-01','2024-06-30','Đơn hàng >= 500k'),
('KM02','Khuyến mãi lễ',15,'2024-04-20','2024-05-01','Mua >= 2 sp');

-- --------------------------------------------------------
-- Table structure for table `nha_cung_cap`
-- --------------------------------------------------------
CREATE TABLE `nha_cung_cap` (
  `MaNCC`      VARCHAR(50)  NOT NULL,
  `TenNCC`     VARCHAR(200) NOT NULL,
  `DiaChiNCC`  VARCHAR(300) NOT NULL,
  `Sdt_NCC`    VARCHAR(11)  NOT NULL,
  PRIMARY KEY (`MaNCC`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Sample
INSERT INTO `nha_cung_cap` VALUES
('NCC01','Toshiba VN','123 Lê Lợi, TP.HCM','0123456789'),
('NCC02','Midea','456 Nguyễn Trãi, Hà Nội','0987654321');

-- --------------------------------------------------------
-- Table structure for table `nhà_sản_xuất`
-- --------------------------------------------------------
CREATE TABLE `nha_san_xuat` (
  `MaNSX`   VARCHAR(50)  NOT NULL,
  `TenNSX`  VARCHAR(200) NOT NULL,
  `DiaChi`  VARCHAR(300) NOT NULL,
  `Sdt_NSX` VARCHAR(11)  NOT NULL,
  PRIMARY KEY (`MaNSX`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------
-- Table structure for table `quat`
-- --------------------------------------------------------
CREATE TABLE `quat` (
  `MaQuat`       VARCHAR(50)  NOT NULL,
  `TenQuat`      VARCHAR(200) NOT NULL,
  `Gia`          INT          NOT NULL,
  `MaNSX`        VARCHAR(50)  NOT NULL,
  `NgaySanXuat`  DATE         NOT NULL,
  `ChatLieu`     VARCHAR(100) NOT NULL,
  `ThuongHieu`   VARCHAR(100) NOT NULL,
  `MaLoaiSP`     VARCHAR(50)  NOT NULL,
  PRIMARY KEY (`MaQuat`),
  INDEX idx_quat_nsx(`MaNSX`),
  CONSTRAINT fk_quat_nsx FOREIGN KEY (`MaNSX`)
    REFERENCES `nha_san_xuat`(`MaNSX`)
    ON UPDATE CASCADE
    ON DELETE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------
-- Table structure for table `phieunhap`
-- --------------------------------------------------------
CREATE TABLE `phieunhap` (
  `MaPhieuNhap` VARCHAR(50) NOT NULL,
  `NgayNhap`    DATE        NOT NULL,
  `MaNCC`       VARCHAR(50) NOT NULL,
  `MaNhanVien`  VARCHAR(50) NOT NULL,
  `TongTien`    INT         NOT NULL,
  PRIMARY KEY (`MaPhieuNhap`),
  INDEX idx_pn_ncc(`MaNCC`),
  INDEX idx_pn_nv(`MaNhanVien`),
  CONSTRAINT fk_pn_ncc FOREIGN KEY (`MaNCC`)
    REFERENCES `nha_cung_cap`(`MaNCC`),
  CONSTRAINT fk_pn_nv FOREIGN KEY (`MaNhanVien`)
    REFERENCES `nhanvien`(`MaNhanVien`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------
-- Table structure for table `chitiet_phieunhap`
-- --------------------------------------------------------
CREATE TABLE `chitiet_phieunhap` (
  `MaPhieuNhap` VARCHAR(50) NOT NULL,
  `MaQuat`      VARCHAR(50) NOT NULL,
  `SoLuong`     INT         NOT NULL,
  `DonGia`      INT         NOT NULL,
  `ThanhTien`   INT         NOT NULL,
  PRIMARY KEY (`MaPhieuNhap`,`MaQuat`),
  INDEX idx_ctpn_quat(`MaQuat`),
  CONSTRAINT fk_ctpn_pn FOREIGN KEY (`MaPhieuNhap`) REFERENCES `phieunhap`(`MaPhieuNhap`),
  CONSTRAINT fk_ctpn_quat FOREIGN KEY (`MaQuat`)    REFERENCES `quat`(`MaQuat`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------
-- Table structure for table `hoadon`
-- --------------------------------------------------------
CREATE TABLE `hoadon` (
  `MaHoaDon`    VARCHAR(50) NOT NULL,
  `MaKhachHang` VARCHAR(50) NOT NULL,
  `MaNhanVien`  VARCHAR(50) NOT NULL,
  `NgayLap`     DATE        NOT NULL,
  `MaSKKhuyenMai` VARCHAR(50) DEFAULT '',
  `TongTien`    INT         NOT NULL,
  PRIMARY KEY (`MaHoaDon`),
  INDEX idx_hd_kh(`MaKhachHang`),
  INDEX idx_hd_nv(`MaNhanVien`),
  CONSTRAINT fk_hd_kh FOREIGN KEY (`MaKhachHang`) REFERENCES `khachhang`(`MaKhachHang`),
  CONSTRAINT fk_hd_nv FOREIGN KEY (`MaNhanVien`)   REFERENCES `nhanvien`(`MaNhanVien`),
  CONSTRAINT fk_hd_sk FOREIGN KEY (`MaSKKhuyenMai`) REFERENCES `su_kien_khuyen_mai`(`MaSKKhuyenMai`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------
-- Table structure for table `chitiet_hoadon`
-- --------------------------------------------------------
CREATE TABLE `chitiet_hoadon` (
  `MaHoaDon`  VARCHAR(50) NOT NULL,
  `MaQuat`     VARCHAR(50) NOT NULL,
  `SoLuong`    INT         NOT NULL,
  `DonGia`     INT         NOT NULL,
  `ThanhTien`  INT         NOT NULL,
  PRIMARY KEY (`MaHoaDon`,`MaQuat`),
  INDEX idx_cthd_quat(`MaQuat`),
  CONSTRAINT fk_cthd_hd FOREIGN KEY (`MaHoaDon`) REFERENCES `hoadon`(`MaHoaDon`),
  CONSTRAINT fk_cthd_quat FOREIGN KEY (`MaQuat`) REFERENCES `quat`(`MaQuat`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------
-- Table structure for table `quanlibaohanh`
-- --------------------------------------------------------
CREATE TABLE `quanlibaohanh` (
  `MaBaoHanh`      VARCHAR(50) NOT NULL,
  `MaQuat`         VARCHAR(50) NOT NULL,
  `MaKhachHang`    VARCHAR(50) NOT NULL,
  `ThoiGianBaoHanh` DATE       NOT NULL,
  `TrangThai`      VARCHAR(100) NOT NULL,
  PRIMARY KEY (`MaBaoHanh`),
  INDEX idx_qbh_quat(`MaQuat`),
  INDEX idx_qbh_kh(`MaKhachHang`),
  CONSTRAINT fk_qbh_quat FOREIGN KEY (`MaQuat`)      REFERENCES `quat`(`MaQuat`),
  CONSTRAINT fk_qbh_kh FOREIGN KEY (`MaKhachHang`)  REFERENCES `khachhang`(`MaKhachHang`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

COMMIT;
