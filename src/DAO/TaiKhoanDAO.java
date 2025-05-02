package dao;

import dto.TaiKhoan;
import Database.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaiKhoanDAO {

    public List<TaiKhoan> getAll() throws SQLException {
        List<TaiKhoan> list = new ArrayList<>();
        String sql = "SELECT id, username, password FROM taikhoan";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new TaiKhoan(
                    rs.getInt("id"),
                    rs.getString("username"),
                    rs.getString("password")
                ));
            }
        }
        return list;
    }

    public void insert(TaiKhoan tk) throws SQLException {
        String sql = "INSERT INTO taikhoan(username,password) VALUES(?,?)";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, tk.getUsername());
            ps.setString(2, tk.getPassword());
            ps.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM taikhoan WHERE id = ?";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    // TODO: thêm update(), findById() nếu cần
}
