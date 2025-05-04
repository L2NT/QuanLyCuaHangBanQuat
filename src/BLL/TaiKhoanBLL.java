package BLL;

import DAO.TaiKhoanDAO;
import DTO.TaiKhoan;

import java.sql.SQLException;
import java.util.List;

public class TaiKhoanBLL {
    private TaiKhoanDAO dao = new TaiKhoanDAO();

    public List<TaiKhoan> layTatCa() {
        try { return dao.getAll(); }
        catch(SQLException e) { throw new RuntimeException(e); }
    }

    public TaiKhoan layTheoMa(String maTK) {
        try { return dao.getByMa(maTK); }
        catch(SQLException e) { throw new RuntimeException(e); }
    }

    public void them(TaiKhoan tk) {
        try { dao.insert(tk); }
        catch(SQLException e) { throw new RuntimeException(e); }
    }

    public void capNhat(TaiKhoan tk) {
        try { dao.update(tk); }
        catch(SQLException e) { throw new RuntimeException(e); }
    }

    public void xoa(String maTK) {
        try { dao.delete(maTK); }
        catch(SQLException e) { throw new RuntimeException(e); }
    }

    public List<String> layDanhSachNhanVienChuaCoTaiKhoan() {
        try { return dao.listNhanVienChuaCoTK(); }
        catch(SQLException e) { throw new RuntimeException(e); }
    }
}
