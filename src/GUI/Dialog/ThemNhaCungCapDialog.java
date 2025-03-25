package GUI.Dialog;

import javax.swing.*;
import java.awt.*;

public class ThemNhaCungCapDialog extends JDialog {
    private JTextField txtMaNCC, txtTenNCC, txtSDT, txtDiaChi, txtEmail;
    private JButton btnLuu, btnHuy;

    // Biến này có thể dùng để biết user có bấm "Lưu" không
    private boolean isSaved = false;

    // Constructor để thêm mới
    public ThemNhaCungCapDialog(Window owner) {
        super(owner, "Thêm Nhà Cung Cấp", Dialog.ModalityType.APPLICATION_MODAL);
        initComponent();
    }

    // Constructor để sửa (nạp dữ liệu sẵn)
    public ThemNhaCungCapDialog(Window owner, String maNCC, String tenNCC, String sdt, String diaChi, String email) {
        super(owner, "Sửa Nhà Cung Cấp", Dialog.ModalityType.APPLICATION_MODAL);
        initComponent();
        // nạp dữ liệu cũ
        txtMaNCC.setText(maNCC);
        txtTenNCC.setText(tenNCC);
        txtSDT.setText(sdt);
        txtDiaChi.setText(diaChi);
        txtEmail.setText(email);
    }

    private void initComponent() {
        this.setSize(400, 300);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout(10, 10));

        JPanel pnlForm = new JPanel(new GridLayout(5, 2, 5, 5));
        pnlForm.add(new JLabel("Mã NCC:"));
        txtMaNCC = new JTextField();
        pnlForm.add(txtMaNCC);

        pnlForm.add(new JLabel("Tên NCC:"));
        txtTenNCC = new JTextField();
        pnlForm.add(txtTenNCC);

        pnlForm.add(new JLabel("SĐT:"));
        txtSDT = new JTextField();
        pnlForm.add(txtSDT);

        pnlForm.add(new JLabel("Địa chỉ:"));
        txtDiaChi = new JTextField();
        pnlForm.add(txtDiaChi);

        pnlForm.add(new JLabel("Email:"));
        txtEmail = new JTextField();
        pnlForm.add(txtEmail);

        this.add(pnlForm, BorderLayout.CENTER);

        JPanel pnlButton = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnLuu = new JButton("Lưu");
        btnHuy = new JButton("Hủy");
        pnlButton.add(btnLuu);
        pnlButton.add(btnHuy);

        this.add(pnlButton, BorderLayout.SOUTH);

        // Sự kiện nút Lưu
        btnLuu.addActionListener(e -> {
            // Ở đây chỉ demo -> hiển thị thông báo
            isSaved = true;
            JOptionPane.showMessageDialog(this, "Đã lưu nhà cung cấp (demo).");
            dispose();
        });

        // Nút Hủy
        btnHuy.addActionListener(e -> {
            dispose();
        });
    }

    public boolean isSaved() {
        return isSaved;
    }

    // Trả về mảng Object để thêm/sửa row trong bảng
    public Object[] getNewNCC() {
        return new Object[] {
            txtMaNCC.getText(),
            txtTenNCC.getText(),
            txtSDT.getText(),
            txtDiaChi.getText(),
            txtEmail.getText()
        };
    }
}
