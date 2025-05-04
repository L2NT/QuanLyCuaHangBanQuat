package BLL;

import DAO.TaiKhoanDAO;
import DTO.TaiKhoan;

import java.util.List;

public class TaiKhoanBLL {
    private final TaiKhoanDAO dao = new TaiKhoanDAO();

    public List<TaiKhoan> layTatCa() {
        return dao.getAll();
    }

    public TaiKhoan layTheoMa(String maTK) {
        return dao.getById(maTK);
    }

    public void them(String user, String pass, String role, String maNV) {
        // tự phát sinh MaTaiKhoan mới, ví dụ TK + timestamp hoặc từ sequence
        String maTK = "TK" + System.currentTimeMillis();
        dao.insert(new TaiKhoan(maTK, user, pass, role, maNV));
    }

    public void capNhat(String maTK, String user, String pass, String role, String maNV) {
        dao.update(new TaiKhoan(maTK, user, pass, role, maNV));
    }

    public void xoa(String maTK) {
        dao.delete(maTK);
    }

    public List<String> layDanhSachNhanVienChuaCoTaiKhoan() {
        return dao.getNhanVienChuaCoTaiKhoan();
    }
}
