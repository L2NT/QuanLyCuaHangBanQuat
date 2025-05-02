package GUI;

import GUI.Panel.TaiKhoanPanel;
import GUI.Panel.NhanVienPanel;
import GUI.Panel.PhanQuyenPanel;

import javax.swing.*;
import java.awt.*;

public class MainAdminFrame extends JFrame {

    public MainAdminFrame() {
        setTitle("Quản trị hệ thống");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initUI();
    }

    private void initUI() {
        // Tạo tabbed pane với 3 tab
        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Tài khoản",    new TaiKhoanPanel());
        tabs.addTab("Nhân viên",    new NhanVienPanel());
        tabs.addTab("Phân quyền",   new PhanQuyenPanel());

        // Nút Đăng xuất
        JButton btnLogout = new JButton("Đăng xuất");
        btnLogout.addActionListener(e -> {
            dispose();               // đóng cửa sổ hiện tại
            new LoginFrame().setVisible(true);  // mở lại LoginFrame
        });

        // Đặt tabs vào giữa, nút logout ở dưới
        JPanel p = new JPanel(new BorderLayout());
        p.add(tabs, BorderLayout.CENTER);
        p.add(btnLogout, BorderLayout.SOUTH);

        setContentPane(p);
    }
}
