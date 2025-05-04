package BUS;

import DAO.TaiKhoanDAO;
import DTO.TaiKhoan;

import java.sql.SQLException;
import java.util.List;

public class TaiKhoanBUS {
    private final TaiKhoanDAO dao = new TaiKhoanDAO();

    public List<TaiKhoan> layTatCa() {
        try { return dao.getAll(); }
        catch(SQLException ex) { throw new RuntimeException(ex); }
    }

    public TaiKhoan layTheoMa(String ma) {
        try { return dao.getByMa(ma); }
        catch(SQLException ex) { throw new RuntimeException(ex); }
    }

    public List<String> layDanhSachNhanVienChuaCoTaiKhoan() {
        try { return dao.getNhanVienWithoutAccount(); }
        catch(SQLException ex) { throw new RuntimeException(ex); }
    }

    public List<String> layDanhSachNhanVienCoTaiKhoan() {
        try { return dao.getNhanVienWithAccount(); }
        catch(SQLException ex) { throw new RuntimeException(ex); }
    }

    public void them(String user, String pass, String role, String maNV) {
        try {
            if (dao.getByUsername(user) != null)
                throw new IllegalArgumentException("Tên tài khoản đã tồn tại");
            String nextId = genNextId();
            dao.insert(new TaiKhoan(nextId, maNV, user, pass, role));
        } catch(SQLException ex) { throw new RuntimeException(ex); }
    }

    public void capNhat(String maTK, String user, String pass, String role, String maNV) {
        if ("TK000".equals(maTK))
            throw new IllegalArgumentException("Không thể sửa tài khoản Admin");
        try {
            TaiKhoan t = dao.getByUsername(user);
            if (t != null && !t.getMaTaiKhoan().equals(maTK))
                throw new IllegalArgumentException("Tên tài khoản đã tồn tại");
            dao.update(new TaiKhoan(maTK, maNV, user, pass, role));
        } catch(SQLException ex) { throw new RuntimeException(ex); }
    }

    public void xoa(String maTK) {
        if ("TK000".equals(maTK))
            throw new IllegalArgumentException("Không thể xóa tài khoản Admin");
        try { dao.delete(maTK); }
        catch(SQLException ex) { throw new RuntimeException(ex); }
    }

    private String genNextId() throws SQLException {
        int max = 0;
        for (TaiKhoan t : dao.getAll()) {
            String num = t.getMaTaiKhoan().replaceAll("\\D+", "");
            max = Math.max(max, Integer.parseInt(num));
        }
        return String.format("TK%03d", max + 1);
    }
}
