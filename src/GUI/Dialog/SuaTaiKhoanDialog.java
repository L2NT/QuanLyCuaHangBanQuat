package GUI.Dialog;

import BUS.TaiKhoanBUS;
import DTO.TaiKhoanDTO;
import DTO.NhanVienDTO;
import BUS.NhanVienBUS;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

public class SuaTaiKhoanDialog extends JDialog {
    private final JComboBox<String> cbbNhanVien;
    private final JTextField txtUsername, txtPassword;
    private final JComboBox<String> cbbQuyenHang;
    private final JButton btnLuu, btnHuy;
    private boolean saved = false;
    private String editingMaTK;
    private final NhanVienBUS nvBUS = new NhanVienBUS();

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

        // Quyền hạng
        gbc.gridy = 3; gbc.gridx = 0;
        pnl.add(new JLabel("Quyền hạng:"), gbc);
        gbc.gridx = 1;
        cbbQuyenHang = new JComboBox<>(new String[]{"QuanLy", "NhanVien"});
        // KHÔNG khóa combo quyền hạng để cho phép người dùng thay đổi
        pnl.add(cbbQuyenHang, gbc);

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

        // Sự kiện khi chọn nhân viên khác
        cbbNhanVien.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    setDefaultQuyenHang();
                }
            }
        });

        btnHuy.addActionListener(e -> dispose());
        btnLuu.addActionListener(e -> {
            String maNV   = (String) cbbNhanVien.getSelectedItem();
            String user   = txtUsername.getText().trim();
            String pass   = txtPassword.getText().trim();
            String role   = (String) cbbQuyenHang.getSelectedItem();

            new TaiKhoanBUS().capNhat(editingMaTK, user, pass, role, maNV);
            saved = true;
            dispose();
        });
    }

    // Phương thức cập nhật quyền hạng mặc định dựa trên chức vụ của nhân viên
    private void setDefaultQuyenHang() {
        String maNV = (String)cbbNhanVien.getSelectedItem();
        if (maNV != null && !maNV.isEmpty()) {
            // Truy vấn thông tin nhân viên từ BUS
            List<NhanVienDTO> listNV = nvBUS.layTatCa();
            for (NhanVienDTO nv : listNV) {
                if (nv.getMaNV().equals(maNV)) {
                    // Kiểm tra chức vụ và chọn đúng quyền hạng tương ứng
                    if ("Quản lý".equals(nv.getChucVu())) {
                        cbbQuyenHang.setSelectedItem("QuanLy");
                    } else {
                        cbbQuyenHang.setSelectedItem("NhanVien");
                    }
                    break;
                }
            }
        }
    }

    /** Gọi trước khi chỉnh sửa để nạp data và khoá cbbNhanVien */
    public void loadForEdit(String maTK) {
        this.editingMaTK = maTK;
        setTitle("Chỉnh sửa tài khoản");
        TaiKhoanDTO tk = new TaiKhoanBUS().layTheoMa(maTK);
        cbbNhanVien.setSelectedItem(tk.getMaNhanVien());
        cbbNhanVien.setEnabled(false);  // KHÓA không cho đổi nv
        txtUsername.setText(tk.getUsername());
        txtPassword.setText(tk.getPassword());
        cbbQuyenHang.setSelectedItem(tk.getVaiTro()); // Hiển thị quyền hạng hiện tại
        
        // Cập nhật quyền hạng mặc định theo nhân viên được chọn 
        // setDefaultQuyenHang();
    }

    /** Đánh dấu xem ng dùng có lưu thành công hay ko */
    public boolean isSaved() {
        return saved;
    }
}