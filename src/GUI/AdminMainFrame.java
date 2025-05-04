package GUI;

import GUI.Panel.TaiKhoanPanel;
import GUI.Panel.PhanQuyenPanel;

import javax.swing.*;
import java.awt.*;

public class AdminMainFrame extends JFrame {
    private final JTabbedPane tabs = new JTabbedPane();

    public AdminMainFrame() {
        initUI();
    }

    private void initUI() {
        setTitle("Giao diện Quản trị hệ thống");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        tabs.addTab("Quản lý tài khoản",   new TaiKhoanPanel());
        tabs.addTab("Phân quyền",          new PhanQuyenPanel());

        add(tabs, BorderLayout.CENTER);
    }
}
