// src/GUI/ManagerMainFrame.java
package GUI;

import GUI.Component.MenuTaskbar;
import GUI.Panel.TrangChu;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;

import javax.swing.*;
import java.awt.*;

public class ManagerMainFrame extends JFrame {
    private MenuTaskbar menuTaskbar;
    private JPanel mainContent;

    public ManagerMainFrame() {
        initLookAndFeel();
        initComponent();
    }

    private void initLookAndFeel() {
        FlatRobotoFont.install();
        FlatLaf.setPreferredFontFamily(FlatRobotoFont.FAMILY);
        FlatLaf.setPreferredLightFontFamily(FlatRobotoFont.FAMILY_LIGHT);
        FlatLaf.setPreferredSemiboldFontFamily(FlatRobotoFont.FAMILY_SEMIBOLD);
        FlatIntelliJLaf.registerCustomDefaultsSource("style");
        FlatIntelliJLaf.setup();
        UIManager.put("PasswordField.showRevealButton", true);
    }

    private void initComponent() {
        setTitle("Giao diện Quản lý");
        setSize(1200, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(0, 0));

        // ⚙️ GÁN VÀO FIELD menuTaskbar
        menuTaskbar = new MenuTaskbar(this, true);
        menuTaskbar.setPreferredSize(new Dimension(250, 800));
        add(menuTaskbar, BorderLayout.WEST);

        // ⚙️ GÁN VÀO FIELD mainContent
        mainContent = new JPanel(new BorderLayout());
        mainContent.setBackground(Color.WHITE);
        add(mainContent, BorderLayout.CENTER);

        // Mặc định hiển thị TrangChu
        setPanel(new TrangChu());
    }

    /** Cho phép các panel con swap vào mainContent */
    public void setPanel(JPanel panel) {
        mainContent.removeAll();
        mainContent.add(panel, BorderLayout.CENTER);
        mainContent.revalidate();
        mainContent.repaint();
    }
}
