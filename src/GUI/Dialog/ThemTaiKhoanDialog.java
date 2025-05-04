package GUI.Dialog;

import BLL.TaiKhoanBLL;
import DTO.TaiKhoan;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ThemTaiKhoanDialog extends JDialog {
    private final JComboBox<String> cbbNV;
    private final JTextField txtUser, txtPass;
    private final JComboBox<String> cbbRole;
    private final JButton btnLuu, btnHuy;

    private boolean editMode = false;
    private String editMaTK;

    private final TaiKhoanBLL bll = new TaiKhoanBLL();

    public ThemTaiKhoanDialog(Window owner) {
        super(owner, "Thêm tài khoản", ModalityType.APPLICATION_MODAL);

        JPanel pnl = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets    = new Insets(5,5,5,5);
        gbc.anchor    = GridBagConstraints.WEST;

        // (1) Nhân viên chưa có TK
        gbc.gridx=0; gbc.gridy=0;
        pnl.add(new JLabel("Nhân viên:"), gbc);
        gbc.gridx=1;
        cbbNV = new JComboBox<>();
        for (String nv : bll.layDanhSachNhanVienChuaCoTaiKhoan()) {
            cbbNV.addItem(nv);
        }
        pnl.add(cbbNV, gbc);

        // (2) Username
        gbc.gridy=1; gbc.gridx=0;
        pnl.add(new JLabel("Tên đăng nhập:"), gbc);
        gbc.gridx=1;
        txtUser = new JTextField(15);
        pnl.add(txtUser, gbc);

        // (3) Password
        gbc.gridy=2; gbc.gridx=0;
        pnl.add(new JLabel("Mật khẩu:"), gbc);
        gbc.gridx=1;
        txtPass = new JTextField(15);
        pnl.add(txtPass, gbc);

        // (4) Vai trò
        gbc.gridy=3; gbc.gridx=0;
        pnl.add(new JLabel("Vai trò:"), gbc);
        gbc.gridx=1;
        cbbRole = new JComboBox<>(new String[]{"QuanLy","NhanVien"});
        pnl.add(cbbRole, gbc);

        // (5) Nút
        gbc.gridy=4; gbc.gridx=0; gbc.gridwidth=2; gbc.anchor=GridBagConstraints.CENTER;
        JPanel btnP = new JPanel();
        btnLuu = new JButton("Lưu");
        btnHuy = new JButton("Hủy");
        btnP.add(btnLuu);
        btnP.add(btnHuy);
        pnl.add(btnP, gbc);

        getContentPane().add(pnl);
        pack();
        setLocationRelativeTo(owner);

        btnHuy.addActionListener(e -> dispose());
        btnLuu.addActionListener(e -> onSave());
    }

    private void onSave() {
        String nv   = (String)cbbNV.getSelectedItem();
        String user = txtUser.getText().trim();
        String pw   = txtPass.getText().trim();
        String role = (String)cbbRole.getSelectedItem();

        if (user.isEmpty()) {
            JOptionPane.showMessageDialog(
                this, "Tên đăng nhập không được để trống!",
                "Lỗi", JOptionPane.ERROR_MESSAGE
            );
            return;
        }
        // kiểm tra duy nhất
        for (TaiKhoan t : bll.layTatCa()) {
            if (t.getTenTaiKhoan().equalsIgnoreCase(user)
                && (!editMode || !t.getMaTaiKhoan().equals(editMaTK)))
            {
                JOptionPane.showMessageDialog(
                    this, "Tên đăng nhập đã tồn tại!",
                    "Lỗi", JOptionPane.ERROR_MESSAGE
                );
                return;
            }
        }
        if (editMode) {
            bll.capNhat(new TaiKhoan(editMaTK, user, pw, role, nv));
        } else {
            String newMa = "TK" + (System.currentTimeMillis()%100000);
            bll.them(new TaiKhoan(newMa, user, pw, role, nv));
        }
        dispose();
    }

    /** Chuyển sang sửa */
    public void loadForEdit(String maTK) {
        editMode  = true;
        editMaTK  = maTK;
        setTitle("Chỉnh sửa tài khoản");

        // cho phép chọn NV bất kỳ (bao gồm cả đã có TK)
        cbbNV.removeAllItems();
        for (String nv : bll.layTatCaNhanVien()) {
            cbbNV.addItem(nv);
        }

        // nạp dữ liệu vào form
        TaiKhoan tk = bll.layTheoMa(maTK);
        cbbNV.setSelectedItem(tk.getMaNhanVien());
        txtUser.setText(tk.getTenTaiKhoan());
        txtPass.setText(tk.getMatKhau());
        cbbRole.setSelectedItem(tk.getVaiTro());
    }
}
