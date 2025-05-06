// src/GUI/Dialog/SuaNhanVienDialog.java
package GUI.Dialog;

import BUS.NhanVienBUS;
import DTO.NhanVienDTO;

import javax.swing.*;
import java.awt.*;

public class SuaNhanVienDialog extends JDialog {
    private final JTextField txtMa, txtTen, txtSdt, txtDiaChi;
    private final JComboBox<String> cbbChucVu;
    private final JButton btnLuu, btnHuy;
    private final NhanVienBUS bll = new NhanVienBUS();
    private boolean saved = false;

    public SuaNhanVienDialog(Window owner, String maNV) {
        super(owner, "Chỉnh sửa nhân viên", ModalityType.APPLICATION_MODAL);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);
        gbc.anchor = GridBagConstraints.WEST;

        // Load dữ liệu
        NhanVienDTO n = bll.layTatCa().stream()
                        .filter(x -> x.getMaNV().equals(maNV))
                        .findFirst()
                        .orElseThrow();

        gbc.gridx=0; gbc.gridy=0;
        add(new JLabel("Mã NV:"), gbc);
        gbc.gridx=1;
        txtMa = new JTextField(n.getMaNV(), 15);
        txtMa.setEnabled(false);
        add(txtMa, gbc);

        gbc.gridy=1; gbc.gridx=0;
        add(new JLabel("Họ tên:"), gbc);
        gbc.gridx=1;
        txtTen = new JTextField(n.getHoTen(), 15);
        add(txtTen, gbc);

        gbc.gridy=2; gbc.gridx=0;
        add(new JLabel("Chức vụ:"), gbc);
        gbc.gridx=1;
        cbbChucVu = new JComboBox<>(new String[]{"Quản lý","Nhân viên"});
        cbbChucVu.setSelectedItem(n.getChucVu());
        add(cbbChucVu, gbc);

        gbc.gridy=3; gbc.gridx=0;
        add(new JLabel("SĐT:"), gbc);
        gbc.gridx=1;
        txtSdt = new JTextField(n.getSdt(), 15);
        add(txtSdt, gbc);

        gbc.gridy=4; gbc.gridx=0;
        add(new JLabel("Địa chỉ:"), gbc);
        gbc.gridx=1;
        txtDiaChi = new JTextField(n.getDiaChi(), 15);
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
                n.getMaNV(),
                txtTen.getText().trim(),
                cbbChucVu.getSelectedItem().toString(),
                txtSdt.getText().trim(),
                txtDiaChi.getText().trim()
            );
            if (bll.sua(nv)) {
                saved = true;
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Cập nhật thất bại!");
            }
        });
    }

    public boolean isSaved() { return saved; }
}
