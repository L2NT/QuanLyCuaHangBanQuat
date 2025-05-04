package dao;

import dto.Quat;
import Database.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuatDAO {

    public List<Quat> getAll() throws SQLException {
        List<Quat> list = new ArrayList<>();
        String sql = "SELECT * FROM quat";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new Quat(
                    rs.getString("MaQuat"),
                    rs.getString("TenQuat"),
                    rs.getInt("Gia"),
                    rs.getInt("SoLuongTon"), // ✅ thêm dòng này
                    rs.getString("MaNSX"),
                    rs.getString("NgaySanXuat"),
                    rs.getString("ChatLieu"),
                    rs.getString("ThuongHieu"),
                    rs.getString("MaLoaiSP")
                ));
            }
        }
        return list;
    }

    public void insert(Quat quat) throws SQLException {
        String sql = "INSERT INTO quat (MaQuat, TenQuat, Gia, SoLuongTon, MaNSX, NgaySanXuat, ChatLieu, ThuongHieu, MaLoaiSP) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, quat.getMaQuat());
            ps.setString(2, quat.getTenQuat());
            ps.setInt(3, quat.getGia());
            ps.setInt(4, quat.getSoLuongTon()); // ✅ thêm dòng này
            ps.setString(5, quat.getMaNSX());
            ps.setString(6, quat.getNgaySanXuat());
            ps.setString(7, quat.getChatLieu());
            ps.setString(8, quat.getThuongHieu());
            ps.setString(9, quat.getMaLoaiSP());
            ps.executeUpdate();
        }
    }

    public void delete(String maQuat) throws SQLException {
        String sql = "DELETE FROM quat WHERE MaQuat = ?";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, maQuat);
            ps.executeUpdate();
        }
    }

    public void update(Quat quat) throws SQLException {
        String sql = "UPDATE quat SET TenQuat = ?, Gia = ?, SoLuongTon = ?, MaNSX = ?, NgaySanXuat = ?, ChatLieu = ?, ThuongHieu = ?, MaLoaiSP = ? WHERE MaQuat = ?";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, quat.getTenQuat());
            ps.setInt(2, quat.getGia());
            ps.setInt(3, quat.getSoLuongTon()); // ✅ thêm dòng này
            ps.setString(4, quat.getMaNSX());
            ps.setString(5, quat.getNgaySanXuat());
            ps.setString(6, quat.getChatLieu());
            ps.setString(7, quat.getThuongHieu());
            ps.setString(8, quat.getMaLoaiSP());
            ps.setString(9, quat.getMaQuat());
            ps.executeUpdate();
        }
    }

    public Quat findByMaQuat(String maQuat) throws SQLException {
        String sql = "SELECT * FROM quat WHERE MaQuat = ?";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, maQuat);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Quat(
                        rs.getString("MaQuat"),
                        rs.getString("TenQuat"),
                        rs.getInt("Gia"),
                        rs.getInt("SoLuongTon"), // ✅ thêm dòng này
                        rs.getString("MaNSX"),
                        rs.getString("NgaySanXuat"),
                        rs.getString("ChatLieu"),
                        rs.getString("ThuongHieu"),
                        rs.getString("MaLoaiSP")
                    );
                }
            }
        }
        return null;
    }

    public List<Quat> findByTenQuat(String keyword) throws SQLException {
        List<Quat> list = new ArrayList<>();
        String sql = "SELECT * FROM quat WHERE TenQuat LIKE ?";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, "%" + keyword + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new Quat(
                        rs.getString("MaQuat"),
                        rs.getString("TenQuat"),
                        rs.getInt("Gia"),
                        rs.getInt("SoLuongTon"), // ✅ thêm dòng này
                        rs.getString("MaNSX"),
                        rs.getString("NgaySanXuat"),
                        rs.getString("ChatLieu"),
                        rs.getString("ThuongHieu"),
                        rs.getString("MaLoaiSP")
                    ));
                }
            }
        }
        return list;
    }

    public List<Quat> findByThuongHieu(String keyword) throws SQLException {
        List<Quat> list = new ArrayList<>();
        String sql = "SELECT * FROM quat WHERE ThuongHieu LIKE ?";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, "%" + keyword + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new Quat(
                        rs.getString("MaQuat"),
                        rs.getString("TenQuat"),
                        rs.getInt("Gia"),
                        rs.getInt("SoLuongTon"), // ✅ thêm dòng này
                        rs.getString("MaNSX"),
                        rs.getString("NgaySanXuat"),
                        rs.getString("ChatLieu"),
                        rs.getString("ThuongHieu"),
                        rs.getString("MaLoaiSP")
                    ));
                }
            }
        }
        return list;
    }
}
