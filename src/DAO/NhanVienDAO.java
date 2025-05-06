package DAO;

import DTO.NhanVienDTO;
import java.sql.*;
import java.util.*;
import DTO.DBConnection;

public class NhanVienDAO {
    public List<NhanVienDTO> getAll() throws SQLException {
        List<NhanVienDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM `nhanvien`";
        try (Connection c = DBConnection.getConnection();
             Statement s = c.createStatement();
             ResultSet rs = s.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new NhanVienDTO(
                    rs.getString("MaNhanVien"),
                    rs.getString("HoTenNV"),
                    rs.getString("ChucVu"),
                    rs.getString("Sdt_NV"),
                    rs.getString("DiaChiNV")
                ));
            }
        }
        return list;
    }

    public void insert(NhanVienDTO nv) throws SQLException {
        String sql = "INSERT INTO `nhanvien` VALUES (?,?,?,?,?)";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement p = c.prepareStatement(sql)) {
            p.setString(1, nv.getMaNV());
            p.setString(2, nv.getHoTen());
            p.setString(3, nv.getChucVu());
            p.setString(4, nv.getSdt());
            p.setString(5, nv.getDiaChi());
            p.executeUpdate();
        }
    }

    public void update(NhanVienDTO nv) throws SQLException {
        String sql = "UPDATE `nhanvien` SET HoTenNV=?, ChucVu=?, Sdt_NV=?, DiaChiNV=? WHERE MaNhanVien=?";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement p = c.prepareStatement(sql)) {
            p.setString(1, nv.getHoTen());
            p.setString(2, nv.getChucVu());
            p.setString(3, nv.getSdt());
            p.setString(4, nv.getDiaChi());
            p.setString(5, nv.getMaNV());
            p.executeUpdate();
        }
    }

    public void delete(String maNV) throws SQLException {
        String sql = "DELETE FROM `nhanvien` WHERE MaNhanVien=?";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement p = c.prepareStatement(sql)) {
            p.setString(1, maNV);
            p.executeUpdate();
        }
    }
}
