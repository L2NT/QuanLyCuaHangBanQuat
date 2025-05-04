package DAO;

import DTO.TaiKhoan;
import Database.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaiKhoanDAO {

    public List<TaiKhoan> getAll() throws SQLException {
        List<TaiKhoan> list = new ArrayList<>();
        String sql = "SELECT * FROM taikhoan";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement p = c.prepareStatement(sql);
             ResultSet r = p.executeQuery()) {
            while (r.next()) {
                list.add(new TaiKhoan(
                    r.getString("MaTaiKhoan"),
                    r.getString("TenTaiKhoan"),
                    r.getString("MatKhau"),
                    r.getString("VaiTro"),
                    r.getString("MaNhanVien")
                ));
            }
        }
        return list;
    }

    public TaiKhoan getByMa(String maTK) throws SQLException {
        String sql = "SELECT * FROM taikhoan WHERE MaTaiKhoan=?";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement p = c.prepareStatement(sql)) {
            p.setString(1, maTK);
            try (ResultSet r = p.executeQuery()) {
                if (r.next())
                    return new TaiKhoan(
                      r.getString("MaTaiKhoan"),
                      r.getString("TenTaiKhoan"),
                      r.getString("MatKhau"),
                      r.getString("VaiTro"),
                      r.getString("MaNhanVien")
                    );
            }
        }
        return null;
    }

    public void insert(TaiKhoan tk) throws SQLException {
        String sql = "INSERT INTO taikhoan(MaTaiKhoan,TenTaiKhoan,MatKhau,VaiTro,MaNhanVien) VALUES(?,?,?,?,?)";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement p = c.prepareStatement(sql)) {
            p.setString(1, tk.getMaTK());
            p.setString(2, tk.getTenTK());
            p.setString(3, tk.getMatKhau());
            p.setString(4, tk.getVaiTro());
            p.setString(5, tk.getMaNhanVien());
            p.executeUpdate();
        }
    }

    public void update(TaiKhoan tk) throws SQLException {
        String sql = "UPDATE taikhoan SET TenTaiKhoan=?,MatKhau=?,VaiTro=?,MaNhanVien=? WHERE MaTaiKhoan=?";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement p = c.prepareStatement(sql)) {
            p.setString(1, tk.getTenTK());
            p.setString(2, tk.getMatKhau());
            p.setString(3, tk.getVaiTro());
            p.setString(4, tk.getMaNhanVien());
            p.setString(5, tk.getMaTK());
            p.executeUpdate();
        }
    }

    public void delete(String maTK) throws SQLException {
        String sql = "DELETE FROM taikhoan WHERE MaTaiKhoan=?";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement p = c.prepareStatement(sql)) {
            p.setString(1, maTK);
            p.executeUpdate();
        }
    }

    /** Trả về list mã NV chưa có TK */
    public List<String> listNhanVienChuaCoTK() throws SQLException {
        List<String> lst = new ArrayList<>();
        String sql =
          "SELECT MaNhanVien FROM nhanvien " +
          "WHERE MaNhanVien NOT IN (SELECT MaNhanVien FROM taikhoan WHERE MaNhanVien IS NOT NULL)";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement p = c.prepareStatement(sql);
             ResultSet r = p.executeQuery()) {
            while (r.next()) lst.add(r.getString(1));
        }
        return lst;
    }
}
