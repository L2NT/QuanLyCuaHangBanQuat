package GUI.Dialog;

import javax.swing.*;
import java.awt.*;

/**
 * Dialog thêm (hoặc sửa) nhân viên.
 * Trường: Mã NV, Tên NV, Chức vụ, SĐT, Email, Tình trạng, ...
 */
public class ThemNhanVienDialog extends JDialog {
    private JTextField txtMaNV, txtTenNV, txtSdt, txtEmail;
    private JComboBox<String> cbbChucVu;
    private JComboBox<String> cbbTinhTrang;
    private JButton btnLuu, btnHuy;
    private boolean isSaved = false;

    public ThemNhanVienDialog(Window owner) {
        super(owner, "Thêm Nhân Viên", ModalityType.APPLICATION_MODAL);
        initComponent();
    }

    private void initComponent() {
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel pnlForm = new JPanel(new GridLayout(6, 2, 5, 5));
        pnlForm.add(new JLabel("Mã NV:"));
        txtMaNV = new JTextField();
        pnlForm.add(txtMaNV);

        pnlForm.add(new JLabel("Tên NV:"));
        txtTenNV = new JTextField();
        pnlForm.add(txtTenNV);

        pnlForm.add(new JLabel("Chức vụ:"));
        cbbChucVu = new JComboBox<>(new String[]{"Quản lý", "Nhân viên"});
        pnlForm.add(cbbChucVu);

        pnlForm.add(new JLabel("SĐT:"));
        txtSdt = new JTextField();
        pnlForm.add(txtSdt);

        pnlForm.add(new JLabel("Email:"));
        txtEmail = new JTextField();
        pnlForm.add(txtEmail);

        pnlForm.add(new JLabel("Tình trạng:"));
        cbbTinhTrang = new JComboBox<>(new String[]{"Hoạt động", "Nghỉ việc"});
        pnlForm.add(cbbTinhTrang);

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
            JOptionPane.showMessageDialog(this, "Đã lưu nhân viên (demo).");
            dispose();
        });
        btnHuy.addActionListener(e -> dispose());
    }

    public boolean isSaved() {
        return isSaved;
    }

    public Object[] getNewNhanVien() {
        return new Object[]{
            txtMaNV.getText().trim(),
            txtTenNV.getText().trim(),
            cbbChucVu.getSelectedItem(),
            txtSdt.getText().trim(),
            txtEmail.getText().trim(),
            cbbTinhTrang.getSelectedItem()
        };
    }
}
