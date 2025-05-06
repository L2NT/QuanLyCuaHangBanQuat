package GUI.Panel;

import javax.swing.*;
import java.awt.*;

public class TrangChu extends JPanel {

    public TrangChu() {
        initComponent();
    }

   private void initComponent() {
    this.setLayout(new BorderLayout());

    // Tiêu đề
//    JLabel lblTitle = new JLabel("TRANG CHỦ CỬA HÀNG QUẠT", SwingConstants.CENTER);
//    lblTitle.setFont(new Font("SansSerif", Font.BOLD, 24));
//    lblTitle.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0)); // thêm khoảng cách

    // Ảnh
    ImageIcon imageIcon = new ImageIcon(getClass().getResource("/img/quat.jpg")); // Đảm bảo ảnh trong thư mục resources
    JLabel lblImage = new JLabel(imageIcon, SwingConstants.CENTER);

    // Panel phụ chứa tiêu đề và ảnh
    JPanel centerPanel = new JPanel(new BorderLayout());
//    centerPanel.add(lblTitle, BorderLayout.NORTH);
    centerPanel.add(lblImage, BorderLayout.CENTER);

    this.add(centerPanel, BorderLayout.CENTER);
}

}
