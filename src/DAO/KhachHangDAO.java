package DAO;
import DTO.DBConnection;
import DTO.KhachHangDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO cho bảng khachhang
 */
public class KhachHangDAO {

    /**
     * Lấy toàn bộ danh sách khách hàng
     */
    public static List<KhachHangDTO> selectAll() {
        List<KhachHangDTO> list = new ArrayList<>();
        String sql = "SELECT MaKhachHang, HoTenKH, Sdt_KH, DiaChiKH, TongTienDaMua FROM khachhang";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                KhachHangDTO kh = new KhachHangDTO(
                        rs.getString("MaKhachHang"),
                        rs.getString("HoTenKH"),
                        rs.getString("Sdt_KH"),
                        rs.getString("DiaChiKH"),
                        rs.getInt("TongTienDaMua")
                );
                list.add(kh);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    /**
     * Lấy khách hàng theo mã
     */
    public KhachHangDTO selectById(String maKh) {
        String sql = "SELECT HoTenKH, Sdt_KH, DiaChiKH, TongTienDaMua FROM khachhang WHERE MaKhachHang = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, maKh);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new KhachHangDTO(
                            maKh,
                            rs.getString("HoTenKH"),
                            rs.getString("Sdt_KH"),
                            rs.getString("DiaChiKH"),
                            rs.getInt("TongTienDaMua")
                    );
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * Thêm mới khách hàng
     */
    public boolean insert(KhachHangDTO kh) {
        String sql = "INSERT INTO khachhang (MaKhachHang, HoTenKH, Sdt_KH, DiaChiKH, TongTienDaMua) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, kh.getMaKhachHang());
            ps.setString(2, kh.getHoTenKH());
            ps.setString(3, kh.getSdtKH());
            ps.setString(4, kh.getDiaChiKH());
            ps.setInt(5, kh.getTongTienDaMua());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * Cập nhật khách hàng
     */
    public boolean update(KhachHangDTO kh) {
        String sql = "UPDATE khachhang SET HoTenKH = ?, Sdt_KH = ?, DiaChiKH = ?, TongTienDaMua = ? WHERE MaKhachHang = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, kh.getHoTenKH());
            ps.setString(2, kh.getSdtKH());
            ps.setString(3, kh.getDiaChiKH());
            ps.setInt(4, kh.getTongTienDaMua());
            ps.setString(5, kh.getMaKhachHang());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * Xóa khách hàng theo mã
     */
    public boolean delete(String maKh) {
        String sql = "DELETE FROM khachhang WHERE MaKhachHang = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, maKh);
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        KhachHangDAO dao = new KhachHangDAO();
        // Test selectAll
        dao.selectAll().forEach(System.out::println);
    }
}
