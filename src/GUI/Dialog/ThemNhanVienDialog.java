// src/GUI/Dialog/ThemNhanVienDialog.java
package GUI.Dialog;

import BUS.NhanVienBUS;
import DTO.NhanVienDTO;

import javax.swing.*;
import java.awt.*;

public class ThemNhanVienDialog extends JDialog {
    private final JTextField txtMa, txtTen, txtSdt, txtDiaChi;
    private final JComboBox<String> cbbChucVu;
    private final JButton btnLuu, btnHuy;
    private final NhanVienBUS bll = new NhanVienBUS();
    private boolean saved = false;

    public ThemNhanVienDialog(Window owner) {
        super(owner, "Thêm nhân viên", ModalityType.APPLICATION_MODAL);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx=0; gbc.gridy=0;
        add(new JLabel("Mã NV:"), gbc);
        gbc.gridx=1;
        txtMa = new JTextField(15);
        add(txtMa, gbc);

        gbc.gridy=1; gbc.gridx=0;
        add(new JLabel("Họ tên:"), gbc);
        gbc.gridx=1;
        txtTen = new JTextField(15);
        add(txtTen, gbc);

        gbc.gridy=2; gbc.gridx=0;
        add(new JLabel("Chức vụ:"), gbc);
        gbc.gridx=1;
        cbbChucVu = new JComboBox<>(new String[]{"Quản lý","Nhân viên"});
        add(cbbChucVu, gbc);

        gbc.gridy=3; gbc.gridx=0;
        add(new JLabel("SĐT:"), gbc);
        gbc.gridx=1;
        txtSdt = new JTextField(15);
        add(txtSdt, gbc);

        gbc.gridy=4; gbc.gridx=0;
        add(new JLabel("Địa chỉ:"), gbc);
        gbc.gridx=1;
        txtDiaChi = new JTextField(15);
        add(txtDiaChi, gbc);

        gbc.gridy=5; gbc.gridx=0; gbc.gridwidth=2; gbc.anchor = GridBagConstraints.CENTER;
        JPanel pnl = new JPanel();
        btnLuu = new JButton("Lưu");
        btnHuy = new JButton("Hủy");
        pnl.add(btnLuu);
        pnl.add(btnHuy);
        add(pnl, gbc);

        pack();
        setLocationRelativeTo(owner);

        btnHuy.addActionListener(e -> dispose());
        btnLuu.addActionListener(e -> {
            NhanVienDTO nv = new NhanVienDTO(
                txtMa.getText().trim(),
                txtTen.getText().trim(),
                cbbChucVu.getSelectedItem().toString(),
                txtSdt.getText().trim(),
                txtDiaChi.getText().trim()
            );
            if (bll.them(nv)) {
                saved = true;
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Thêm thất bại!");
            }
        });
    }

    public boolean isSaved() { return saved; }
}
