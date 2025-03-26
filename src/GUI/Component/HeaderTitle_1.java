package GUI.Component;

import java.awt.*;
import javax.swing.*;

/**
 * HeaderTitle - hiển thị tiêu đề lớn ở đầu panel/dialog (nếu muốn dùng).
 */
public class HeaderTitle_1 extends JPanel {

    public HeaderTitle_1(String title) {
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.setBackground(Color.WHITE);

        JLabel lblTitle = new JLabel(title);
        lblTitle.setFont(lblTitle.getFont().deriveFont(Font.BOLD, 20f));
        this.add(lblTitle);
    }
}
