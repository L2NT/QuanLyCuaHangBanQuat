package GUI.Dialog;

import javax.swing.*;
import java.awt.*;

/**
 * Dialog thêm (hoặc sửa) khách hàng.
 * Trường: Mã KH, Tên KH, SĐT, Email, Loại KH, Điểm tích lũy.
 */
public class ThemKhachHangDialog extends JDialog {
    private JTextField txtMaKH, txtTenKH, txtSdt, txtEmail, txtDiem;
    private JComboBox<String> cbbLoaiKH;
    private JButton btnLuu, btnHuy;
    private boolean isSaved = false;

    public ThemKhachHangDialog(Window owner) {
        super(owner, "Thêm Khách Hàng", ModalityType.APPLICATION_MODAL);
        initComponent();
    }

    private void initComponent() {
        setSize(400, 350);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel pnlForm = new JPanel(new GridLayout(6, 2, 5, 5));
        pnlForm.add(new JLabel("Mã KH:"));
        txtMaKH = new JTextField();
        pnlForm.add(txtMaKH);

        pnlForm.add(new JLabel("Tên KH:"));
        txtTenKH = new JTextField();
        pnlForm.add(txtTenKH);

        pnlForm.add(new JLabel("SĐT:"));
        txtSdt = new JTextField();
        pnlForm.add(txtSdt);

        pnlForm.add(new JLabel("Email:"));
        txtEmail = new JTextField();
        pnlForm.add(txtEmail);

        pnlForm.add(new JLabel("Loại KH:"));
        cbbLoaiKH = new JComboBox<>(new String[]{"Thành viên", "Vãng lai"});
        pnlForm.add(cbbLoaiKH);

        pnlForm.add(new JLabel("Điểm tích lũy:"));
        txtDiem = new JTextField("0");
        pnlForm.add(txtDiem);

        add(pnlForm, BorderLayout.CENTER);

        JPanel pnlButton = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnLuu = new JButton("Lưu");
        btnHuy = new JButton("Hủy");
        pnlButton.add(btnLuu);
        pnlButton.add(btnHuy);
        add(pnlButton, BorderLayout.SOUTH);

        // Sự kiện nút
        btnLuu.addActionListener(e -> {
            isSaved = true;
            JOptionPane.showMessageDialog(this, "Đã lưu khách hàng (demo).");
            dispose();
        });
        btnHuy.addActionListener(e -> dispose());
    }

    public boolean isSaved() {
        return isSaved;
    }

    public Object[] getNewKhachHang() {
        return new Object[]{
            txtMaKH.getText().trim(),
            txtTenKH.getText().trim(),
            txtSdt.getText().trim(),
            txtEmail.getText().trim(),
            cbbLoaiKH.getSelectedItem(),
            Integer.parseInt(txtDiem.getText().trim())
        };
    }
}
