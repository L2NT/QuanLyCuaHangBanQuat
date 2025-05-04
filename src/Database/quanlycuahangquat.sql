-- --------------------------------------------------------
-- Database: `quanlycuahangquat`
-- --------------------------------------------------------
DROP DATABASE IF EXISTS `quanlycuahangquat`;
CREATE DATABASE `quanlycuahangquat`
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;
USE `quanlycuahangquat`;

-- --------------------------------------------------------
-- Table: nhanvien
-- --------------------------------------------------------
CREATE TABLE `nhanvien` (
  `MaNhanVien` VARCHAR(50)  NOT NULL,
  `HoTenNV`    VARCHAR(200) NOT NULL,
  `ChucVu`     VARCHAR(200) NOT NULL,
  `Sdt_NV`     VARCHAR(11)  NOT NULL,
  `DiaChiNV`   VARCHAR(300) NOT NULL,
  PRIMARY KEY (`MaNhanVien`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `nhanvien`
  (`MaNhanVien`,`HoTenNV`,`ChucVu`,`Sdt_NV`,`DiaChiNV`)
VALUES
  ('NV001','Nguyễn Văn A','Quản lý','0901234567','12 Cách Mạng Tháng 8, TP.HCM'),
  ('NV002','Trần Thị B','Nhân viên','0912345678','34 Hai Bà Trưng, Hà Nội'),
  ('NV003','Lê Văn C','Nhân viên','0934567890','56 Nguyễn Huệ, Đà Nẵng');

-- --------------------------------------------------------
-- Table: taikhoan
-- --------------------------------------------------------
CREATE TABLE `taikhoan` (
  `MaTaiKhoan`  VARCHAR(10)  NOT NULL,
  `TenTaiKhoan` VARCHAR(100) NOT NULL,
  `MatKhau`     VARCHAR(100) NOT NULL,
  `VaiTro`      VARCHAR(50)  NOT NULL,
  `MaNhanVien`  VARCHAR(50)  NULL,
  PRIMARY KEY (`MaTaiKhoan`),
  INDEX idx_tk_nv (`MaNhanVien`),
  CONSTRAINT fk_tk_nv FOREIGN KEY (`MaNhanVien`) 
    REFERENCES `nhanvien`(`MaNhanVien`) 
    ON UPDATE CASCADE ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `taikhoan`
  (`MaTaiKhoan`,`TenTaiKhoan`,`MatKhau`,`VaiTro`,`MaNhanVien`)
VALUES
  ('TK000','admin','123','Admin',     NULL),
  ('TK001','nv1',  '123','QuanLy','NV001'),
  ('TK002','nv2',  '123','NhanVien','NV002'),
  ('TK003','nv3',  '123','NhanVien','NV003');

-- --------------------------------------------------------
-- Table: khachhang
-- --------------------------------------------------------
CREATE TABLE `khachhang` (
  `MaKhachHang`   VARCHAR(50)  NOT NULL,
  `HoTenKH`       VARCHAR(200) NOT NULL,
  `Sdt_KH`        VARCHAR(11)  NOT NULL,
  `DiaChiKH`      VARCHAR(300) NOT NULL,
  `TongTienDaMua` INT          NOT NULL,
  PRIMARY KEY (`MaKhachHang`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `khachhang`
  (`MaKhachHang`,`HoTenKH`,`Sdt_KH`,`DiaChiKH`,`TongTienDaMua`)
VALUES
  ('KH001','Nguyễn Văn A','0912345678','Hà Nội',1500000),
  ('KH002','Trần Thị B','0987654321','TP.HCM',2300000),
  ('KH003','Lê Văn C','0933221144','Đà Nẵng',1200000),
  ('KH004','Trần Văn Bảo','0914234567','TP.HCM',850000),
  ('KH005','Lê Thị Hồng','0925345678','Đà Nẵng',2000000),
  ('KH006','Phạm Văn Minh','0936456789','Hải Phòng',950000),
  ('KH007','Đỗ Thị Thủy','0947567890','Cần Thơ',1250000),
  ('KH008','Hoàng Văn Long','0958678901','Huế',1100000),
  ('KH009','Ngô Thị Hương','0969789012','Bình Dương',3000000),
  ('KH010','Bùi Văn Hùng','0970890123','Biên Hòa',700000),
  ('KH011','Dương Thị Lan','0981901234','Quảng Ninh',1600000),
  ('KH012','Trịnh Văn Sơn','0992012345','Lâm Đồng',1300000),
  ('KH013','Cao Thị Yến','0903123456','Hưng Yên',1750000),
  ('KH014','Tạ Văn Phúc','0914234561','Thái Bình',900000),
  ('KH015','Phan Thị Nga','0925345672','Nam Định',1950000);

-- --------------------------------------------------------
-- Table: su_kien_khuyen_mai
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

INSERT INTO `su_kien_khuyen_mai`
  (`MaSKKhuyenMai`,`TenKhuyenMai`,`PhanTramGiam`,`NgayBatDau`,`NgayKetThuc`,`DieuKien`)
VALUES
  ('KM01','Giảm giá mùa hè',10,'2024-06-01','2024-06-30','Đơn hàng >= 500k'),
  ('KM02','Khuyến mãi lễ',15,'2024-04-20','2024-05-01','Mua >= 2 sp');

-- --------------------------------------------------------
-- Table: nha_cung_cap
-- --------------------------------------------------------
CREATE TABLE `nha_cung_cap` (
  `MaNCC`     VARCHAR(50)  NOT NULL,
  `TenNCC`    VARCHAR(200) NOT NULL,
  `DiaChiNCC` VARCHAR(300) NOT NULL,
  `Sdt_NCC`   VARCHAR(11)  NOT NULL,
  PRIMARY KEY (`MaNCC`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `nha_cung_cap`
  (`MaNCC`,`TenNCC`,`DiaChiNCC`,`Sdt_NCC`)
VALUES
  ('NCC001','Toshiba Việt Nam','123 Lê Lợi, TP.HCM','0123456789'),
  ('NCC002','Midea Corporation','456 Nguyễn Trãi, Hà Nội','0987654321'),
  ('NCC003','Panasonic Việt Nam','789 Trần Hưng Đạo, Đà Nẵng','0912345678');

-- --------------------------------------------------------
-- Table: nha_san_xuat
-- --------------------------------------------------------
CREATE TABLE `nha_san_xuat` (
  `MaNSX`   VARCHAR(50)  NOT NULL,
  `TenNSX`  VARCHAR(200) NOT NULL,
  `DiaChi`  VARCHAR(300) NOT NULL,
  `Sdt_NSX` VARCHAR(11)  NOT NULL,
  PRIMARY KEY (`MaNSX`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `nha_san_xuat` (`MaNSX`,`TenNSX`,`DiaChi`,`Sdt_NSX`) VALUES
  ('NSX001','Panasonic','123 Nguyễn Văn Linh, Hà Nội','0901234567'),
  ('NSX002','Midea','456 Lê Duẩn, TP.HCM','0912345678'),
  ('NSX003','Asia','789 Trường Chinh, Đà Nẵng','0923456789'),
  ('NSX004','Toshiba','12 Nguyễn Huệ, Hà Nội','0934567890'),
  ('NSX005','Samsung','45 Nguyễn Văn Linh, TP.HCM','0945678901'),
  ('NSX006','LG','78 Lý Tự Trọng, Đà Nẵng','0956789012'),
  ('NSX007','Sony','90 Nguyễn Trãi, TP.HCM','0967890123'),
  ('NSX008','Hitachi','21 Trần Hưng Đạo, Hà Nội','0978901234'),
  ('NSX009','Sharp','34 Tôn Đức Thắng, TP.HCM','0989012345'),
  ('NSX010','Daikin','56 Cách Mạng Tháng 8, Đà Nẵng','0990123456'),
  ('NSX011','Electrolux','11 Hai Bà Trưng, Hà Nội','0901123456'),
  ('NSX012','Aqua','67 Phạm Văn Đồng, TP.HCM','0912233445'),
  ('NSX013','Kangaroo','89 Nguyễn Thái Học, Hà Nội','0923344556'),
  ('NSX014','Sanaky','101 Trường Sa, TP.HCM','0934455667'),
  ('NSX015','BlueStone','112 Hoàng Văn Thụ, Đà Nẵng','0945566778');

-- --------------------------------------------------------
-- Table: quat
-- --------------------------------------------------------
CREATE TABLE `quat` (
  `MaQuat`      VARCHAR(50)  NOT NULL,
  `TenQuat`     VARCHAR(200) NOT NULL,
  `Gia`         INT          NOT NULL,
  `MaNSX`       VARCHAR(50)  NOT NULL,
  `NgaySanXuat` DATE         NOT NULL,
  `ChatLieu`    VARCHAR(100) NOT NULL,
  `ThuongHieu`  VARCHAR(100) NOT NULL,
  `MaLoaiSP`    VARCHAR(50)  NOT NULL,
  PRIMARY KEY (`MaQuat`),
  INDEX idx_quat_nsx (`MaNSX`),
  CONSTRAINT fk_quat_nsx FOREIGN KEY (`MaNSX`) REFERENCES `nha_san_xuat`(`MaNSX`)
    ON UPDATE CASCADE ON DELETE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `quat` (`MaQuat`,`TenQuat`,`Gia`,`MaNSX`,`NgaySanXuat`,`ChatLieu`,`ThuongHieu`,`MaLoaiSP`) VALUES
  ('Q001','Quạt đứng Toshiba Remote',550000,'NSX003','2024-03-01','Nhựa cao cấp','Toshiba','QA1'),
  ('Q002','Quạt treo Midea 3 cánh',650000,'NSX002','2024-03-15','Kim loại + nhựa','Midea','QA2'),
  ('Q003','Quạt mini Panasonic sạc USB',350000,'NSX001','2024-02-20','Nhựa ABS','Panasonic','QA1'),
  ('Q004','Quạt Hộp Toshiba',470000,'NSX004','2023-04-12','Nhựa cứng','Toshiba','LSP02'),
  ('Q005','Quạt Hơi Nước Samsung',1250000,'NSX005','2023-02-05','Nhựa','Samsung','LSP03'),
  ('Q006','Quạt Điều Hòa LG',2200000,'NSX006','2023-01-25','Hợp kim','LG','LSP03'),
  ('Q007','Quạt Mini Sony',250000,'NSX007','2023-07-18','Nhựa mềm','Sony','LSP04'),
  ('Q008','Quạt Đứng Hitachi',980000,'NSX008','2023-08-08','Nhựa','Hitachi','LSP01'),
  ('Q009','Quạt Treo Sharp',560000,'NSX009','2023-09-01','Kim loại','Sharp','LSP02'),
  ('Q010','Quạt Công Nghiệp Daikin',3600000,'NSX010','2023-07-10','Thép','Daikin','LSP05'),
  ('Q011','Quạt Sạc Electrolux',750000,'NSX011','2023-03-23','Nhựa','Electrolux','LSP04'),
  ('Q012','Quạt Bàn Aqua',410000,'NSX012','2023-05-30','Nhôm','Aqua','LSP01'),
  ('Q013','Quạt Đứng Kangaroo',620000,'NSX013','2023-06-06','Nhựa','Kangaroo','LSP01'),
  ('Q014','Quạt Điều Hòa Sanaky',2400000,'NSX014','2023-01-11','Kim loại','Sanaky','LSP03'),
  ('Q015','Quạt Hộp BlueStone',490000,'NSX015','2023-04-22','Nhựa','BlueStone','LSP02');

-- --------------------------------------------------------
-- Table: phieunhap
-- --------------------------------------------------------
CREATE TABLE `phieunhap` (
  `MaPhieuNhap` VARCHAR(50) NOT NULL,
  `NgayNhap`    DATE        NOT NULL,
  `MaNCC`       VARCHAR(50) NOT NULL,
  `MaNhanVien`  VARCHAR(50) NOT NULL,
  `TongTien`    INT         NOT NULL,
  PRIMARY KEY (`MaPhieuNhap`),
  INDEX idx_pn_ncc (`MaNCC`),
  INDEX idx_pn_nv  (`MaNhanVien`),
  CONSTRAINT fk_pn_ncc FOREIGN KEY (`MaNCC`) REFERENCES `nha_cung_cap`(`MaNCC`),
  CONSTRAINT fk_pn_nv FOREIGN KEY (`MaNhanVien`) REFERENCES `nhanvien`(`MaNhanVien`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `phieunhap` (`MaPhieuNhap`,`NgayNhap`,`MaNCC`,`MaNhanVien`,`TongTien`) VALUES
  ('PN001','2024-04-01','NCC001','NV001',7000000),
  ('PN002','2024-04-05','NCC002','NV002',4496000),
  ('PN003','2024-04-08','NCC003','NV003',12280000),
  ('PN004','2024-04-04','NCC001','NV001',7856000),
  ('PN005','2024-04-05','NCC002','NV002',7104000),
  ('PN006','2024-04-06','NCC003','NV003',7640000),
  ('PN007','2024-04-07','NCC001','NV001',13504000),
  ('PN008','2024-04-08','NCC002','NV002',1176000),
  ('PN009','2024-04-09','NCC003','NV003',6640000),
  ('PN010','2024-04-10','NCC001','NV001',5200000),
  ('PN011','2024-04-11','NCC002','NV002',8000000),
  ('PN012','2024-04-12','NCC003','NV003',8800000),
  ('PN013','2024-04-13','NCC001','NV001',7160000),
  ('PN014','2024-04-14','NCC002','NV002',3136000),
  ('PN015','2024-04-15','NCC003','NV003',3888000);

-- --------------------------------------------------------
-- Table: chitiet_phieunhap
-- --------------------------------------------------------
CREATE TABLE `chitiet_phieunhap` (
  `MaPhieuNhap` VARCHAR(50) NOT NULL,
  `MaQuat`      VARCHAR(50) NOT NULL,
  `SoLuong`     INT         NOT NULL,
  `DonGia`      INT         NOT NULL,
  `ThanhTien`   INT         NOT NULL,
  PRIMARY KEY (`MaPhieuNhap`,`MaQuat`),
  INDEX idx_ctpn_quat (`MaQuat`),
  CONSTRAINT fk_ctpn_pn FOREIGN KEY (`MaPhieuNhap`) REFERENCES `phieunhap`(`MaPhieuNhap`),
  CONSTRAINT fk_ctpn_quat FOREIGN KEY (`MaQuat`)    REFERENCES `quat`(`MaQuat`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `chitiet_phieunhap` (`MaPhieuNhap`,`MaQuat`,`SoLuong`,`DonGia`,`ThanhTien`) VALUES
  ('PN001','Q001',10,440000,4400000),
  ('PN001','Q002',5,520000,2600000),
  ('PN002','Q003',8,280000,2240000),
  ('PN002','Q004',6,376000,2256000),
  ('PN003','Q005',7,1000000,7000000),
  ('PN003','Q006',3,1760000,5280000),
  ('PN004','Q007',4,200000,800000),
  ('PN004','Q008',9,784000,7056000),
  ('PN005','Q009',3,448000,1344000),
  ('PN005','Q010',2,2880000,5760000),
  ('PN006','Q011',10,600000,6000000),
  ('PN006','Q012',5,328000,1640000),
  ('PN007','Q013',4,496000,1984000),
  ('PN007','Q014',6,1920000,11520000),
  ('PN008','Q015',3,392000,1176000),
  ('PN009','Q001',6,440000,2640000),
  ('PN009','Q005',4,1000000,4000000),
  ('PN010','Q002',10,520000,5200000),
  ('PN011','Q003',8,280000,2240000),
  ('PN011','Q010',2,2880000,5760000),
  ('PN012','Q006',5,1760000,8800000),
  ('PN013','Q007',7,200000,1400000),
  ('PN013','Q014',3,1920000,5760000),
  ('PN014','Q008',4,784000,3136000),
  ('PN015','Q009',6,448000,2688000),
  ('PN015','Q011',2,600000,1200000);

-- --------------------------------------------------------
-- Table: hoadon
-- --------------------------------------------------------
CREATE TABLE `hoadon` (
  `MaHoaDon`      VARCHAR(50) NOT NULL,
  `MaKhachHang`   VARCHAR(50) NOT NULL,
  `MaNhanVien`    VARCHAR(50) NOT NULL,
  `NgayLap`       DATE        NOT NULL,
  `MaSuKienKM`    VARCHAR(50) NULL DEFAULT NULL,
  `TongTien`      INT         NOT NULL,
  PRIMARY KEY (`MaHoaDon`),
  INDEX idx_hd_kh (`MaKhachHang`),
  INDEX idx_hd_nv (`MaNhanVien`),
  CONSTRAINT fk_hd_kh FOREIGN KEY (`MaKhachHang`) REFERENCES `khachhang`(`MaKhachHang`),
  CONSTRAINT fk_hd_nv FOREIGN KEY (`MaNhanVien`) REFERENCES `nhanvien`(`MaNhanVien`),
  CONSTRAINT fk_hd_sk FOREIGN KEY (`MaSuKienKM`) REFERENCES `su_kien_khuyen_mai`(`MaSKKhuyenMai`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `hoadon`
  (`MaHoaDon`,`MaKhachHang`,`MaNhanVien`,`NgayLap`,`MaSuKienKM`,`TongTien`)
VALUES
  ('HD001','KH003','NV002','2024-05-01',NULL,3300000),
  ('HD002','KH005','NV001','2024-05-02',NULL,3070000),
  ('HD003','KH007','NV003','2024-05-03',NULL,6450000),
  ('HD004','KH009','NV002','2024-05-04',NULL,1690000),
  ('HD005','KH011','NV001','2024-05-05',NULL,5850000),
  ('HD006','KH013','NV003','2024-05-06',NULL,8000000),
  ('HD007','KH004','NV002','2024-05-07',NULL,2570000),
  ('HD008','KH006','NV001','2024-05-08',NULL,2580000),
  ('HD009','KH008','NV003','2024-05-09',NULL,4570000),
  ('HD010','KH010','NV002','2024-05-10',NULL,7750000),
  ('HD011','KH012','NV001','2024-05-11',NULL,3720000),
  ('HD012','KH014','NV003','2024-05-12',NULL,2340000),
  ('HD013','KH015','NV002','2024-05-13',NULL,4890000),
  ('HD014','KH001','NV001','2024-05-14',NULL,9400000),
  ('HD015','KH002','NV003','2024-05-15',NULL,1790000);

-- --------------------------------------------------------
-- Table: chitiet_hoadon
-- --------------------------------------------------------
CREATE TABLE `chitiet_hoadon` (
  `MaHoaDon`  VARCHAR(50) NOT NULL,
  `MaQuat`     VARCHAR(50) NOT NULL,
  `SoLuong`    INT         NOT NULL,
  `DonGia`     INT         NOT NULL,
  `ThanhTien`  INT         NOT NULL,
  `MaBaoHanh`  VARCHAR(50) NOT NULL,
  PRIMARY KEY (`MaHoaDon`,`MaQuat`),
  INDEX idx_cthd_quat (`MaQuat`),
  CONSTRAINT fk_cthd_hd FOREIGN KEY (`MaHoaDon`) REFERENCES `hoadon`(`MaHoaDon`),
  CONSTRAINT fk_cthd_quat FOREIGN KEY (`MaQuat`) REFERENCES `quat`(`MaQuat`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `chitiet_hoadon`
  (`MaHoaDon`,`MaQuat`,`SoLuong`,`DonGia`,`ThanhTien`,`MaBaoHanh`)
VALUES
  ('HD001','Q001',3,550000,1650000,'BH001'),
  ('HD001','Q002',2,650000,1300000,'BH002'),
  ('HD001','Q003',1,350000,350000,'BH003'),
  ('HD002','Q002',4,650000,2600000,'BH004'),
  ('HD002','Q004',1,470000,470000,'BH005'),
  ('HD003','Q003',5,350000,1750000,'BH006'),
  ('HD003','Q005',2,1250000,2500000,'BH007'),
  ('HD003','Q006',1,2200000,2200000,'BH008'),
  ('HD004','Q004',2,470000,940000,'BH009'),
  ('HD004','Q007',3,250000,750000,'BH010'),
  ('HD005','Q005',3,1250000,3750000,'BH011'),
  ('HD005','Q008',1,980000,980000,'BH012'),
  ('HD005','Q009',2,560000,1120000,'BH013'),
  ('HD006','Q006',2,2200000,4400000,'BH014'),
  ('HD006','Q010',1,3600000,3600000,'BH015'),
  ('HD007','Q007',4,250000,1000000,'BH016'),
  ('HD007','Q011',1,750000,750000,'BH017'),
  ('HD007','Q012',2,410000,820000,'BH018'),
  ('HD008','Q008',2,980000,1960000,'BH019'),
  ('HD008','Q013',1,620000,620000,'BH020'),
  ('HD009','Q009',3,560000,1680000,'BH021'),
  ('HD009','Q014',1,2400000,2400000,'BH022'),
  ('HD009','Q015',1,490000,490000,'BH023'),
  ('HD010','Q001',1,550000,550000,'BH024'),
  ('HD010','Q010',2,3600000,7200000,'BH025'),
  ('HD011','Q002',1,650000,650000,'BH002'),
  ('HD011','Q011',3,750000,2250000,'BH011'),
  ('HD011','Q012',2,410000,820000,'BH012'),
  ('HD012','Q003',2,350000,700000,'BH003'),
  ('HD012','Q012',4,410000,1640000,'BH012'),
  ('HD013','Q005',1,1250000,1250000,'BH005'),
  ('HD013','Q013',2,620000,1240000,'BH013'),
  ('HD013','Q014',1,2400000,2400000,'BH014'),
  ('HD014','Q006',1,2200000,2200000,'BH006'),
  ('HD014','Q014',3,2400000,7200000,'BH014'),
  ('HD015','Q007',1,250000,250000,'BH007'),
  ('HD015','Q009',1,560000,560000,'BH009'),
  ('HD015','Q015',2,490000,980000,'BH015');

-- --------------------------------------------------------
-- Table: quanlibaohanh
-- --------------------------------------------------------
CREATE TABLE `quanlibaohanh` (
  `MaBaoHanh`      VARCHAR(50) NOT NULL,
  `MaQuat`         VARCHAR(50) NOT NULL,
  `MaKhachHang`    VARCHAR(50) NOT NULL,
  `ThoiGianBaoHanh` DATE       NOT NULL,
  `TrangThai`      VARCHAR(100) NOT NULL,
  PRIMARY KEY (`MaBaoHanh`),
  INDEX idx_qbh_quat (`MaQuat`),
  INDEX idx_qbh_kh  (`MaKhachHang`),
  CONSTRAINT fk_qbh_quat FOREIGN KEY (`MaQuat`)      REFERENCES `quat`(`MaQuat`),
  CONSTRAINT fk_qbh_kh   FOREIGN KEY (`MaKhachHang`) REFERENCES `khachhang`(`MaKhachHang`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `quanlibaohanh`
  (`MaBaoHanh`,`MaQuat`,`MaKhachHang`,`ThoiGianBaoHanh`,`TrangThai`)
VALUES
  ('BH003','Q003','KH003','2025-05-03','Còn hạn'),
  ('BH004','Q004','KH005','2025-05-03','Còn hạn'),
  ('BH005','Q001','KH005','2025-05-03','Còn hạn'),
  ('BH006','Q005','KH007','2025-05-03','Còn hạn'),
  ('BH007','Q006','KH007','2025-05-03','Còn hạn'),
  ('BH008','Q004','KH009','2025-05-03','Còn hạn'),
  ('BH009','Q007','KH009','2025-05-03','Còn hạn'),
  ('BH010','Q003','KH011','2025-05-03','Còn hạn'),
  ('BH011','Q005','KH011','2025-05-03','Còn hạn'),
  ('BH012','Q008','KH013','2025-05-03','Còn hạn'),
  ('BH013','Q009','KH013','2025-05-03','Còn hạn'),
  ('BH014','Q006','KH004','2025-05-03','Còn hạn'),
  ('BH015','Q007','KH006','2025-05-03','Còn hạn'),
  ('BH016','Q010','KH006','2025-05-03','Còn hạn'),
  ('BH017','Q001','KH004','2025-05-03','Còn hạn'),
  ('BH018','Q002','KH004','2025-05-03','Còn hạn'),
  ('BH019','Q008','KH010','2025-05-03','Còn hạn'),
  ('BH020','Q011','KH010','2025-05-03','Còn hạn'),
  ('BH021','Q003','KH012','2025-05-03','Còn hạn'),
  ('BH022','Q005','KH014','2025-05-03','Còn hạn'),
  ('BH023','Q010','KH014','2025-05-03','Còn hạn'),
  ('BH024','Q012','KH015','2025-05-03','Còn hạn'),
  ('BH025','Q013','KH015','2025-05-03','Còn hạn');

COMMIT;
