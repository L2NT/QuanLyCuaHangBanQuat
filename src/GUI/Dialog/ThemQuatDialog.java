package GUI.Dialog;

import javax.swing.*;
import java.awt.*;

/**
 * Dialog thêm quạt (chỉ nhập thông tin cơ bản, không lưu DB).
 */
public class ThemQuatDialog extends JDialog {

    private JTextField txtTenQuat, txtLoaiQuat, txtThuongHieu, txtXuatXu, txtSoLuong;
    private JButton btnLuu, btnHuy;

    public ThemQuatDialog(Window owner) {
        super(owner, "Thêm Quạt", Dialog.ModalityType.APPLICATION_MODAL);
        initComponent();
    }

    private void initComponent() {
        this.setSize(400, 300);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout(10, 10));

        JPanel pnlForm = new JPanel(new GridLayout(5, 2, 5, 5));
        pnlForm.add(new JLabel("Tên quạt:"));
        txtTenQuat = new JTextField();
        pnlForm.add(txtTenQuat);

        pnlForm.add(new JLabel("Loại quạt:"));
        txtLoaiQuat = new JTextField();
        pnlForm.add(txtLoaiQuat);

        pnlForm.add(new JLabel("Thương hiệu:"));
        txtThuongHieu = new JTextField();
        pnlForm.add(txtThuongHieu);

        pnlForm.add(new JLabel("Xuất xứ:"));
        txtXuatXu = new JTextField();
        pnlForm.add(txtXuatXu);

        pnlForm.add(new JLabel("Số lượng:"));
        txtSoLuong = new JTextField();
        pnlForm.add(txtSoLuong);

        this.add(pnlForm, BorderLayout.CENTER);

        JPanel pnlButton = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnLuu = new JButton("Lưu");
        btnHuy = new JButton("Hủy");
        pnlButton.add(btnLuu);
        pnlButton.add(btnHuy);

        this.add(pnlButton, BorderLayout.SOUTH);

        // Nút Lưu (chỉ thông báo, không lưu DB)
        btnLuu.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Đã lưu quạt (demo).");
            dispose();
        });

        // Nút Hủy
        btnHuy.addActionListener(e -> {
            dispose();
        });
    }
}
