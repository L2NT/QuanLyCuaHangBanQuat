package bll;

import dao.TaiKhoanDAO;
import dto.TaiKhoan;

import java.sql.SQLException;
import java.util.List;

public class TaiKhoanBLL {
    private final TaiKhoanDAO dao = new TaiKhoanDAO();

    public List<TaiKhoan> layTatCa() {
        try {
            return dao.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return List.of();
        }
    }

    public boolean them(String username, String password) {
        try {
            dao.insert(new TaiKhoan(0, username, password));
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean xoa(int id) {
        try {
            dao.delete(id);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
