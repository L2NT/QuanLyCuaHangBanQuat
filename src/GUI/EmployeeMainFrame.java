package GUI;

import GUI.Component.MenuTaskbar;
import GUI.Panel.TrangChu;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class EmployeeMainFrame extends JFrame {
    private final String maNhanVien;
    private MenuTaskbar menuTaskbar;
    private JPanel mainContent;

    public EmployeeMainFrame(String maNhanVien) {
        this.maNhanVien = maNhanVien;
        initLookAndFeel();
        initComponent();
    }

    private void initLookAndFeel() {
        FlatRobotoFont.install();
        FlatLaf.setPreferredFontFamily(FlatRobotoFont.FAMILY);
        FlatLaf.setPreferredLightFontFamily(FlatRobotoFont.FAMILY_LIGHT);
        FlatLaf.setPreferredSemiboldFontFamily(FlatRobotoFont.FAMILY_SEMIBOLD);
        FlatIntelliJLaf.setup();
        UIManager.put("PasswordField.showRevealButton", true);
    }

    private void initComponent() {
        setTitle("Giao diện Nhân viên");
        setSize(1200, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(0,0));

        // Tự động load tên NV từ CSDL
        String tenNV = loadTenNhanVien(maNhanVien);

        // Thông tin ở header
        JLabel lblInfo = new JLabel("Nhân viên: " + tenNV);
        lblInfo.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        add(lblInfo, BorderLayout.NORTH);

        // Menu (chỉ 4 chức năng)
        menuTaskbar = new MenuTaskbar(this, false);
        menuTaskbar.setPreferredSize(new Dimension(250, 800));
        add(menuTaskbar, BorderLayout.WEST);

        // Vùng nội dung
        mainContent = new JPanel(new BorderLayout());
        mainContent.setBackground(Color.WHITE);
        add(mainContent, BorderLayout.CENTER);

        setPanel(new TrangChu());
    }

    private String loadTenNhanVien(String maNV) {
        String sql = "SELECT HoTenNV FROM `nhan vien` WHERE MaNhanVien=?";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement p = c.prepareStatement(sql)) {
            p.setString(1, maNV);
            try (ResultSet r = p.executeQuery()) {
                if (r.next()) return r.getString("HoTenNV");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return maNV;
    }

    /** Cho phép swap panel con vào mainContent */
    public void setPanel(JPanel panel) {
        mainContent.removeAll();
        mainContent.add(panel, BorderLayout.CENTER);
        mainContent.revalidate();
        mainContent.repaint();
    }
}
