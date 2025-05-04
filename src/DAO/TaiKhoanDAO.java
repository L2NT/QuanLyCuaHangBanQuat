package DAO;

import Database.DBConnection;
import DTO.TaiKhoan;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaiKhoanDAO {
    // Lấy tất cả tài khoản
    public List<TaiKhoan> getAll() {
        List<TaiKhoan> list = new ArrayList<>();
        String sql = "SELECT * FROM taikhoan";
        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new TaiKhoan(
                    rs.getString("MaTaiKhoan"),
                    rs.getString("TenTaiKhoan"),
                    rs.getString("MatKhau"),
                    rs.getString("VaiTro"),
                    rs.getString("MaNhanVien")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // Lấy 1 tài khoản theo mã
    public TaiKhoan getById(String maTK) {
        String sql = "SELECT * FROM taikhoan WHERE MaTaiKhoan=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, maTK);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new TaiKhoan(
                        rs.getString("MaTaiKhoan"),
                        rs.getString("TenTaiKhoan"),
                        rs.getString("MatKhau"),
                        rs.getString("VaiTro"),
                        rs.getString("MaNhanVien")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Thêm mới
    public boolean insert(TaiKhoan tk) {
        String sql = "INSERT INTO taikhoan(MaTaiKhoan,TenTaiKhoan,MatKhau,VaiTro,MaNhanVien) VALUES(?,?,?,?,?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, tk.getMaTaiKhoan());
            ps.setString(2, tk.getUsername());
            ps.setString(3, tk.getPassword());
            ps.setString(4, tk.getVaiTro());
            ps.setString(5, tk.getMaNhanVien());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Cập nhật
    public boolean update(TaiKhoan tk) {
        String sql = "UPDATE taikhoan SET TenTaiKhoan=?,MatKhau=?,VaiTro=?,MaNhanVien=? WHERE MaTaiKhoan=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, tk.getUsername());
            ps.setString(2, tk.getPassword());
            ps.setString(3, tk.getVaiTro());
            ps.setString(4, tk.getMaNhanVien());
            ps.setString(5, tk.getMaTaiKhoan());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Xóa
    public boolean delete(String maTK) {
        String sql = "DELETE FROM taikhoan WHERE MaTaiKhoan=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, maTK);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Lấy danh sách mã nhân viên chưa có tài khoản
    public List<String> getNhanVienChuaCoTaiKhoan() {
        List<String> list = new ArrayList<>();
        String sql = "SELECT MaNhanVien FROM nhanvien "
                   + "WHERE MaNhanVien NOT IN (SELECT MaNhanVien FROM taikhoan WHERE MaNhanVien IS NOT NULL)";
        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(rs.getString("MaNhanVien"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
