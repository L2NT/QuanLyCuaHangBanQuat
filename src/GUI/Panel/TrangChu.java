package GUI.Panel;

import javax.swing.*;
import java.awt.*;

public class TrangChu extends JPanel {

    public TrangChu() {
        initComponent();
    }

    private void initComponent() {
        this.setLayout(new BorderLayout());
        JLabel lblTitle = new JLabel("TRANG CHỦ CỬA HÀNG QUẠT", SwingConstants.CENTER);
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 24));
        this.add(lblTitle, BorderLayout.CENTER);
    }
}
