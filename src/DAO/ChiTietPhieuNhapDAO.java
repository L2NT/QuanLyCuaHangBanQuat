/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.ChiTietPhieuNhapDTO;
import DTO.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ChiTietPhieuNhapDAO {

    // Lấy tất cả chi tiết phiếu nhập
    public static List<ChiTietPhieuNhapDTO> getAllChiTietPhieuNhap() {
        List<ChiTietPhieuNhapDTO> ds = new ArrayList<>();

        String sql = "SELECT * FROM ChiTietPhieuNhap";  // Đảm bảo đúng tên bảng

        try (
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()
        ) {
            while (rs.next()) {
                String maPhieuNhap = rs.getString("MaPhieuNhap");
                String maQuat = rs.getString("MaQuat");
                int soLuong = rs.getInt("SoLuong");
                int donGia = rs.getInt("DonGia");
                int thanhTien = rs.getInt("ThanhTien");

                ChiTietPhieuNhapDTO ct = new ChiTietPhieuNhapDTO(maPhieuNhap, maQuat, soLuong, donGia, thanhTien);
                ds.add(ct);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ds;
    }
    
    // Thêm chi tiết phiếu nhập
    public static boolean themChiTietPhieuNhap(ChiTietPhieuNhapDTO ct) {
    String sql = "INSERT INTO ChiTietPhieuNhap (MaPhieuNhap, MaQuat, SoLuong, DonGia, ThanhTien) VALUES (?, ?, ?, ?, ?)";

    try (
        Connection conn = DBConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)
    ) {
        stmt.setString(1, ct.getMaPhieuNhap());
        stmt.setString(2, ct.getMaQuat());
        stmt.setInt(3, ct.getSoLuong());
        stmt.setInt(4, ct.getDonGia());
        stmt.setInt(5, ct.getThanhTien());

        return stmt.executeUpdate() > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}

    
    
}
