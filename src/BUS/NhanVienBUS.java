package BUS;

import DAO.NhanVienDAO;
import DTO.NhanVien;
import java.sql.SQLException;
import java.util.List;

public class NhanVienBUS {
    private final NhanVienDAO dao = new NhanVienDAO();

    public List<NhanVien> layTatCa() {
        try {
            return dao.getAll();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return List.of();
        }
    }

    public boolean them(NhanVien nv) {
        try {
            dao.insert(nv);
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean sua(NhanVien nv) {
        try {
            dao.update(nv);
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean xoa(String maNV) {
        try {
            dao.delete(maNV);
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
