package gui;

import gui.panel.TaiKhoanPanel;
import gui.panel.NhanVienPanel;
import gui.panel.PhanQuyenPanel;

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
        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Tài khoản", new TaiKhoanPanel());
        tabs.addTab("Nhân viên", new NhanVienPanel());
        tabs.addTab("Phân quyền", new PhanQuyenPanel());
        JButton btnLogout = new JButton("Đăng xuất");
        btnLogout.addActionListener(e -> {
            dispose();
            new LoginFrame().setVisible(true);
        });
        JPanel p = new JPanel(new BorderLayout());
        p.add(tabs, BorderLayout.CENTER);
        p.add(btnLogout, BorderLayout.SOUTH);
        add(p);
    }
}
