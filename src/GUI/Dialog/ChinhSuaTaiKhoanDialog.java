package GUI.Dialog;

import BLL.TaiKhoanBLL;
import DTO.TaiKhoan;

import javax.swing.*;
import java.awt.*;

public class ChinhSuaTaiKhoanDialog extends JDialog {
    private final JComboBox<String> cbbNhanVien;
    private final JTextField txtUsername;
    private final JPasswordField txtPassword;
    private final JComboBox<String> cbbVaiTro;
    private final JButton btnLuu, btnHuy;

    public ChinhSuaTaiKhoanDialog(Window owner, TaiKhoan tk) {
        super(owner, "Chỉnh sửa tài khoản", ModalityType.APPLICATION_MODAL);

        JPanel pnl = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);
        gbc.anchor = GridBagConstraints.WEST;

        // (1) Mã TK (không sửa)
        gbc.gridx=0; gbc.gridy=0;
        pnl.add(new JLabel("Mã TK:"), gbc);
        gbc.gridx=1;
        JTextField txtMaTK = new JTextField(tk.getMaTaiKhoan(), 15);
        txtMaTK.setEditable(false);
        pnl.add(txtMaTK, gbc);

        // (2) NV đã có TK
        gbc.gridy=1; gbc.gridx=0;
        pnl.add(new JLabel("Nhân viên:"), gbc);
        gbc.gridx=1;
        cbbNhanVien = new JComboBox<>();
        new TaiKhoanBLL()
            .layDanhSachNhanVienCoTaiKhoan()
            .forEach(cbbNhanVien::addItem);
        cbbNhanVien.setSelectedItem(tk.getMaNhanVien());
        pnl.add(cbbNhanVien, gbc);

        // (3) Tên TK
        gbc.gridy=2; gbc.gridx=0;
        pnl.add(new JLabel("Tên đăng nhập:"), gbc);
        gbc.gridx=1;
        txtUsername = new JTextField(tk.getTenTaiKhoan(), 15);
        pnl.add(txtUsername, gbc);

        // (4) Mật khẩu
        gbc.gridy=3; gbc.gridx=0;
        pnl.add(new JLabel("Mật khẩu:"), gbc);
        gbc.gridx=1;
        txtPassword = new JPasswordField(tk.getMatKhau(), 15);
        pnl.add(txtPassword, gbc);

        // (5) Vai trò
        gbc.gridy=4; gbc.gridx=0;
        pnl.add(new JLabel("Vai trò:"), gbc);
        gbc.gridx=1;
        cbbVaiTro = new JComboBox<>(new String[]{"QuanLy","NhanVien"});
        cbbVaiTro.setSelectedItem(tk.getVaiTro());
        pnl.add(cbbVaiTro, gbc);

        // (6) Nút
        gbc.gridy=5; gbc.gridx=0; gbc.gridwidth=2; gbc.anchor=GridBagConstraints.CENTER;
        JPanel btnPnl = new JPanel();
        btnLuu = new JButton("Lưu");
        btnHuy = new JButton("Hủy");
        btnPnl.add(btnLuu);
        btnPnl.add(btnHuy);
        pnl.add(btnPnl, gbc);

        setContentPane(pnl);
        pack();
        setLocationRelativeTo(owner);

        btnHuy .addActionListener(e -> dispose());
        btnLuu .addActionListener(e -> {
            String maNV = (String)cbbNhanVien.getSelectedItem();
            String user = txtUsername.getText().trim();
            String pass = new String(txtPassword.getPassword());
            String role = (String)cbbVaiTro.getSelectedItem();
            new TaiKhoanBLL().capNhat(tk.getMaTaiKhoan(), user, pass, role, maNV);
            dispose();
        });
    }
}
