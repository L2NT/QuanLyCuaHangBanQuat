package GUI;

import GUI.Panel.TaiKhoanPanel;
import javax.swing.*;
import java.awt.*;

public class AdminMainFrame extends JFrame {
    public AdminMainFrame() {
        setTitle("Giao diện Quản trị hệ thống");
        setSize(800,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        add(new TaiKhoanPanel(), BorderLayout.CENTER);
    }
}
