package GUI;

import GUI.Component.MenuTaskbar;
import GUI.Panel.TrangChu;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Main extends JFrame {

    private JPanel mainContent;  // Khu vực hiển thị panel
    private MenuTaskbar menuTaskbar; // Thanh menu bên trái

    public Main() {
        initLookAndFeel();
        initComponent();
    }

   
    private void initLookAndFeel() {
        try {
            FlatRobotoFont.install();
            FlatLaf.setPreferredFontFamily(FlatRobotoFont.FAMILY);
            FlatIntelliJLaf.setup();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        // Tùy chỉnh UIManager
        UIManager.put("Table.showVerticalLines", false);
        UIManager.put("Table.showHorizontalLines", true);
        UIManager.put("TextComponent.arc", 5);
        UIManager.put("ScrollBar.thumbArc", 999);
        UIManager.put("ScrollBar.thumbInsets", new Insets(2, 2, 2, 2));
        UIManager.put("Button.iconTextGap", 10);
        UIManager.put("PasswordField.showRevealButton", true);
        UIManager.put("Table.selectionBackground", new Color(240, 247, 250));
        UIManager.put("Table.selectionForeground", new Color(0, 0, 0));
        UIManager.put("Table.scrollPaneBorder", new EmptyBorder(0, 0, 0, 0));
        UIManager.put("Table.rowHeight", 40);
        UIManager.put("TabbedPane.selectedBackground", Color.white);
        UIManager.put("TableHeader.height", 40);
        UIManager.put("TableHeader.background", new Color(242, 242, 242));
    }

    /**
     * Khởi tạo các thành phần GUI chính.
     */
    private void initComponent() {
        this.setTitle("Hệ thống quản lý cửa hàng bán quạt");
        this.setSize(1200, 800);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout(0, 0));

        // Menu bên trái
        menuTaskbar = new MenuTaskbar(this);
        menuTaskbar.setPreferredSize(new Dimension(250, 800));
        this.add(menuTaskbar, BorderLayout.WEST);

        // Vùng nội dung hiển thị panel
        mainContent = new JPanel();
        mainContent.setLayout(new BorderLayout());
        mainContent.setBackground(Color.WHITE);
        this.add(mainContent, BorderLayout.CENTER);

        // Mặc định hiển thị TrangChu
        setPanel(new TrangChu());
    }

    /**
     * Hàm cho phép thay đổi panel hiển thị ở khu vực mainContent.
     */
    public void setPanel(JPanel panel) {
        mainContent.removeAll();
        mainContent.add(panel, BorderLayout.CENTER);
        mainContent.revalidate();
        mainContent.repaint();
    }

    /**
     * Hàm main chạy chương trình
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Main().setVisible(true);
        });
    }
}
