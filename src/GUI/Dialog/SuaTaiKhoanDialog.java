package GUI.Dialog;

import BUS.TaiKhoanBUS;
import DTO.TaiKhoan;

import javax.swing.*;
import java.awt.*;

public class SuaTaiKhoanDialog extends JDialog {
    private final JComboBox<String> cbbNhanVien;
    private final JTextField txtUsername, txtPassword;
    private final JComboBox<String> cbbVaiTro;
    private final JButton btnLuu, btnHuy;
    private boolean saved = false;
    private String editingMaTK;

    public SuaTaiKhoanDialog(Window owner) {
        super(owner, "Chỉnh sửa tài khoản", ModalityType.APPLICATION_MODAL);

        JPanel pnl = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);
        gbc.anchor = GridBagConstraints.WEST;

        // Mã NV
        gbc.gridx = 0; gbc.gridy = 0;
        pnl.add(new JLabel("Nhân viên:"), gbc);
        gbc.gridx = 1;
        cbbNhanVien = new JComboBox<>();
        // Nạp danh sách NV đã có TK (trừ Admin)
        new TaiKhoanBUS().layDanhSachNhanVienCoTaiKhoan()
                         .forEach(cbbNhanVien::addItem);
        pnl.add(cbbNhanVien, gbc);

        // Tên đăng nhập
        gbc.gridy = 1; gbc.gridx = 0;
        pnl.add(new JLabel("Tên đăng nhập:"), gbc);
        gbc.gridx = 1;
        txtUsername = new JTextField(15);
        pnl.add(txtUsername, gbc);

        // Mật khẩu
        gbc.gridy = 2; gbc.gridx = 0;
        pnl.add(new JLabel("Mật khẩu:"), gbc);
        gbc.gridx = 1;
        txtPassword = new JTextField(15);
        pnl.add(txtPassword, gbc);

        // Vai trò
        gbc.gridy = 3; gbc.gridx = 0;
        pnl.add(new JLabel("Vai trò:"), gbc);
        gbc.gridx = 1;
        cbbVaiTro = new JComboBox<>(new String[]{"QuanLy", "NhanVien"});
        pnl.add(cbbVaiTro, gbc);

        // Nút Lưu / Hủy
        gbc.gridy = 4; gbc.gridx = 0; gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JPanel btnPnl = new JPanel();
        btnLuu = new JButton("Lưu");
        btnHuy = new JButton("Hủy");
        btnPnl.add(btnLuu);
        btnPnl.add(btnHuy);
        pnl.add(btnPnl, gbc);

        getContentPane().add(pnl);
        pack();
        setLocationRelativeTo(owner);

        btnHuy.addActionListener(e -> dispose());
        btnLuu.addActionListener(e -> {
            String maNV   = (String) cbbNhanVien.getSelectedItem();
            String user   = txtUsername.getText().trim();
            String pass   = txtPassword.getText().trim();
            String role   = (String) cbbVaiTro.getSelectedItem();

            new TaiKhoanBUS().capNhat(editingMaTK, user, pass, role, maNV);
            saved = true;
            dispose();
        });
    }

    /** Gọi trước khi chỉnh sửa để nạp data và khoá cbbNhanVien */
    public void loadForEdit(String maTK) {
        this.editingMaTK = maTK;
        setTitle("Chỉnh sửa tài khoản");
        TaiKhoan tk = new TaiKhoanBUS().layTheoMa(maTK);
        cbbNhanVien.setSelectedItem(tk.getMaNhanVien());
        cbbNhanVien.setEnabled(false);  // KHÓA không cho đổi NV
        txtUsername.setText(tk.getUsername());
        txtPassword.setText(tk.getPassword());
        cbbVaiTro.setSelectedItem(tk.getVaiTro());
    }

    /** Đánh dấu xem người dùng có lưu thành công hay không */
    public boolean isSaved() {
        return saved;
    }
}
