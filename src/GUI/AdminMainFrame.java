package GUI;

import GUI.Panel.TaiKhoanPanel;
import javax.swing.*;
import java.awt.*;

public class AdminMainFrame extends JFrame {
    public AdminMainFrame() {
        initUI();
    }
    private void initUI() {
        setTitle("Quản trị hệ thống");
        setSize(1000,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // Chỉ còn 1 panel TK
        add(new TaiKhoanPanel(), BorderLayout.CENTER);
    }
}
