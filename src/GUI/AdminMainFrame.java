package GUI;

import BLL.TaiKhoanBLL;
import DTO.TaiKhoan;
import GUI.Dialog.ThemTaiKhoanDialog;
import GUI.Dialog.ChinhSuaTaiKhoanDialog;
import GUI.Panel.TaiKhoanPanel;

import javax.swing.*;
import java.awt.*;

public class AdminMainFrame extends JFrame {
    private final TaiKhoanPanel tkPanel;

    public AdminMainFrame() {
        initUI();
    }

    private void initUI() {
        setTitle("Giao diện Quản trị hệ thống");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // chỉ còn 1 tab: Quản lý tài khoản
        tkPanel = new TaiKhoanPanel();
        add(tkPanel, BorderLayout.CENTER);

        // --- hook các nút THÊM / CHỈNH SỬA / XÓA / LÀM MỚI ---
        tkPanel.addThemAction(e -> {
            ThemTaiKhoanDialog dlg = new ThemTaiKhoanDialog(this);
            dlg.setVisible(true);
            tkPanel.loadDataFromBLL();
        });
        tkPanel.addChinhSuaAction(e -> {
            String maTK = tkPanel.getSelectedMaTK();
            if (maTK == null) return;
            TaiKhoan tk = new TaiKhoanBLL().layTheoMa(maTK);
            ChinhSuaTaiKhoanDialog dlg = new ChinhSuaTaiKhoanDialog(this, tk);
            dlg.setVisible(true);
            tkPanel.loadDataFromBLL();
        });
        tkPanel.addXoaAction(e -> {
            String maTK = tkPanel.getSelectedMaTK();
            if (maTK == null) return;
            int ok = JOptionPane.showConfirmDialog(
                this,
                "Bạn có chắc muốn xóa tài khoản " + maTK + "?",
                "Xác nhận",
                JOptionPane.YES_NO_OPTION
            );
            if (ok == JOptionPane.YES_OPTION) {
                new TaiKhoanBLL().xoa(maTK);
                tkPanel.loadDataFromBLL();
            }
        });
        tkPanel.addLamMoiAction(e -> tkPanel.loadDataFromBLL());
    }
}
