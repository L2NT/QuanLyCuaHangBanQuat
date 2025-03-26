package GUI.Dialog;

import javax.swing.*;
import java.awt.*;

/**
 * Dialog thêm (hoặc sửa) tài khoản.
 * Trường: Mã TK, Tên đăng nhập, Loại TK, Email, Trạng thái, ...
 */
public class ThemTaiKhoanDialog extends JDialog {
    private JTextField txtMaTK, txtTenDN, txtEmail;
    private JComboBox<String> cbbLoaiTK;
    private JComboBox<String> cbbTrangThai;
    private JButton btnLuu, btnHuy;
    private boolean isSaved = false;

    public ThemTaiKhoanDialog(Window owner) {
        super(owner, "Thêm Tài Khoản", ModalityType.APPLICATION_MODAL);
        initComponent();
    }

    private void initComponent() {
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel pnlForm = new JPanel(new GridLayout(5, 2, 5, 5));
        pnlForm.add(new JLabel("Mã TK:"));
        txtMaTK = new JTextField();
        pnlForm.add(txtMaTK);

        pnlForm.add(new JLabel("Tên đăng nhập:"));
        txtTenDN = new JTextField();
        pnlForm.add(txtTenDN);

        pnlForm.add(new JLabel("Loại TK:"));
        cbbLoaiTK = new JComboBox<>(new String[]{"Admin", "User"});
        pnlForm.add(cbbLoaiTK);

        pnlForm.add(new JLabel("Email:"));
        txtEmail = new JTextField();
        pnlForm.add(txtEmail);

        pnlForm.add(new JLabel("Trạng thái:"));
        cbbTrangThai = new JComboBox<>(new String[]{"Hoạt động", "Khóa"});
        pnlForm.add(cbbTrangThai);

        add(pnlForm, BorderLayout.CENTER);

        JPanel pnlButton = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnLuu = new JButton("Lưu");
        btnHuy = new JButton("Hủy");
        pnlButton.add(btnLuu);
        pnlButton.add(btnHuy);
        add(pnlButton, BorderLayout.SOUTH);

        // Sự kiện
        btnLuu.addActionListener(e -> {
            isSaved = true;
            JOptionPane.showMessageDialog(this, "Đã lưu tài khoản (demo).");
            dispose();
        });
        btnHuy.addActionListener(e -> dispose());
    }

    public boolean isSaved() {
        return isSaved;
    }

    public Object[] getNewTaiKhoan() {
        return new Object[]{
            txtMaTK.getText().trim(),
            txtTenDN.getText().trim(),
            cbbLoaiTK.getSelectedItem(),
            txtEmail.getText().trim(),
            cbbTrangThai.getSelectedItem()
        };
    }
}
