package GUI.Panel;

import GUI.Component.itemTaskbar;
import GUI.Main;

import java.awt.*;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class QuanLyThuocTinhSP extends JPanel {
    private final Main mainFrame; // Main Frame chính

    private JPanel contentCenter;
    private final itemTaskbar[] listitem;

    // Icon & Header của các thuộc tính
    private final String[] iconst = {
        "brand_100px.svg", "price_100px.svg", "calendar_100px.svg",
        "factory_100px.svg", "material_100px.svg"
    };
    private final String[] header = {
        "Thương hiệu", "Giá", "Ngày sản xuất", "Mã nhà sản xuất", "Chất liệu"
    };

    // Màu nền giao diện
    private static final Color BACKGROUND_COLOR = new Color(240, 247, 250);

    public QuanLyThuocTinhSP(Main mainFrame) {
        this.mainFrame = mainFrame;
        this.listitem = new itemTaskbar[header.length];
        initComponent();
    }

    private void initComponent() {
        this.setLayout(new BorderLayout());
        this.setBackground(BACKGROUND_COLOR);

        initPadding(); // Thêm khoảng cách 4 phía

        contentCenter = new JPanel();
        contentCenter.setBackground(BACKGROUND_COLOR);
        contentCenter.setLayout(new GridLayout(3, 2, 20, 20));
        this.add(contentCenter, BorderLayout.CENTER);

        // Tạo danh sách itemTaskbar và thêm sự kiện click
        for (int i = 0; i < header.length; i++) {
            listitem[i] = new itemTaskbar(iconst[i], header[i], header[i]);
            contentCenter.add(listitem[i]);

            // Thêm sự kiện click
            final int index = i; // Biến final để sử dụng trong MouseAdapter
            listitem[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent evt) {
                    JPanel panel = getPanelByIndex(index);
                    if (panel != null) {
                        mainFrame.setPanel(panel); // Đổi sang panel tương ứng
                    }
                }
            });
        }
    }

    /**
     * Hàm lấy JPanel tương ứng khi click vào itemTaskbar
     */
    private JPanel getPanelByIndex(int index) {
        switch (index) {
            case 0:
                return createSamplePanel("Quản lý Thương Hiệu");
            case 1:
                return createSamplePanel("Quản lý Giá");
            case 2:
                return createSamplePanel("Quản lý Ngày Sản Xuất");
            case 3:
                return createSamplePanel("Quản lý Mã Nhà Sản Xuất");
            case 4:
                return createSamplePanel("Quản lý Chất Liệu");
            default:
                return null;
        }
    }

    /**
     * Tạo panel mẫu khi click vào các thuộc tính
     */
    private JPanel createSamplePanel(String title) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        JLabel label = new JLabel(title, SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 20));
        panel.add(label, BorderLayout.CENTER);
        return panel;
    }

    /**
     * Thêm padding để tạo khoảng cách xung quanh content
     */
    private void initPadding() {
        this.add(createPaddingPanel(), BorderLayout.NORTH);
        this.add(createPaddingPanel(), BorderLayout.SOUTH);
        this.add(createPaddingPanel(), BorderLayout.EAST);
        this.add(createPaddingPanel(), BorderLayout.WEST);
    }

    private JPanel createPaddingPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(BACKGROUND_COLOR);
        panel.setPreferredSize(new Dimension(40, 40));
        return panel;
    }
}
