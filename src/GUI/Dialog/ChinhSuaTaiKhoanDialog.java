package GUI.Dialog;

import BLL.TaiKhoanBLL;
import DTO.TaiKhoan;
import javax.swing.*;
import java.awt.*;

public class ChinhSuaTaiKhoanDialog extends JDialog {
    private final JComboBox<String> cbbNV;
    private final JTextField txtUser, txtPass;
    private final JComboBox<String> cbbRole;
    private boolean saved = false;
    private String maTK;

    public ChinhSuaTaiKhoanDialog(Window owner) {
        super(owner, "Chỉnh sửa tài khoản", ModalityType.APPLICATION_MODAL);
        JPanel p = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);
        gbc.anchor = GridBagConstraints.WEST;

        // (1) Nhân viên (khóa khi sửa)
        gbc.gridx = 0; gbc.gridy = 0;
        p.add(new JLabel("Nhân viên:"), gbc);
        gbc.gridx = 1;
        cbbNV = new JComboBox<>();
        // load danh sách NV có tài khoản
        new TaiKhoanBLL().layDanhSachNhanVienCoTaiKhoan().forEach(cbbNV::addItem);
        cbbNV.setEnabled(false); // KHÓA lại khi chỉnh sửa
        p.add(cbbNV, gbc);

        // (2) Tên đăng nhập
        gbc.gridy = 1; gbc.gridx = 0;
        p.add(new JLabel("Tên đăng nhập:"), gbc);
        gbc.gridx = 1;
        txtUser = new JTextField(15);
        p.add(txtUser, gbc);

        // (3) Mật khẩu
        gbc.gridy = 2; gbc.gridx = 0;
        p.add(new JLabel("Mật khẩu:"), gbc);
        gbc.gridx = 1;
        txtPass = new JTextField(15);
        p.add(txtPass, gbc);

        // (4) Vai trò
        gbc.gridy = 3; gbc.gridx = 0;
        p.add(new JLabel("Vai trò:"), gbc);
        gbc.gridx = 1;
        cbbRole = new JComboBox<>(new String[]{"QuanLy", "NhanVien"});
        p.add(cbbRole, gbc);

        // (5) Buttons
        gbc.gridy = 4; gbc.gridx = 0; gbc.gridwidth = 2; gbc.anchor = GridBagConstraints.CENTER;
        JPanel btnP = new JPanel();
        JButton btnLuu = new JButton("Lưu");
        JButton btnHuy = new JButton("Hủy");
        btnP.add(btnLuu);
        btnP.add(btnHuy);
        p.add(btnP, gbc);

        getContentPane().add(p);
        pack();
        setLocationRelativeTo(owner);

        // Sự kiện
        btnHuy.addActionListener(e -> dispose());
        btnLuu.addActionListener(e -> {
            try {
                new TaiKhoanBLL().capNhat(
                    maTK,
                    txtUser.getText().trim(),
                    txtPass.getText().trim(),
                    (String) cbbRole.getSelectedItem(),
                    maTK.equals("TK000") ? null : (String) cbbNV.getSelectedItem()
                );
                saved = true;
                dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    /**
     * Nạp dữ liệu vào dialog và mở chế độ sửa
     */
    public void loadForEdit(String maTK) {
        this.maTK = maTK;
        TaiKhoan t = new TaiKhoanBLL().layTheoMa(maTK);
        cbbNV.setSelectedItem(t.getMaNhanVien());
        txtUser.setText(t.getUsername());
        txtPass.setText(t.getPassword());
        cbbRole.setSelectedItem(t.getVaiTro());
    }

    public boolean isSaved() {
        return saved;
    }
}
