package GUI.Dialog;

import javax.swing.*;
import java.awt.*;

/**
 * Dialog thêm (hoặc sửa) khuyến mãi.
 * Chứa 6 trường: Mã KM, Tên KM, Phần trăm giảm, Ngày BD, Ngày KT, Điều kiện.
 */
public class ThemKhuyenMaiDialog extends JDialog {

    private JTextField txtMaKM, txtTenKM, txtPhanTram, txtNgayBD, txtNgayKT, txtDieuKien;
    private JButton btnLuu, btnHuy;
    private boolean isSaved = false;  // Để biết user có bấm Lưu hay không

    // Constructor dành cho thêm mới
    public ThemKhuyenMaiDialog(Window owner) {
        super(owner, "Thêm Khuyến Mãi", ModalityType.APPLICATION_MODAL);
        initComponent();
    }

    // Constructor dành cho sửa (nạp dữ liệu cũ)
    public ThemKhuyenMaiDialog(Window owner,
                               String maKM, String tenKM, int phanTram,
                               String ngayBD, String ngayKT, String dieuKien) {
        super(owner, "Sửa Khuyến Mãi", ModalityType.APPLICATION_MODAL);
        initComponent();
        // Nạp dữ liệu cũ
        txtMaKM.setText(maKM);
        txtTenKM.setText(tenKM);
        txtPhanTram.setText(String.valueOf(phanTram));
        txtNgayBD.setText(ngayBD);
        txtNgayKT.setText(ngayKT);
        txtDieuKien.setText(dieuKien);
    }

    private void initComponent() {
        setSize(400, 350);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel pnlForm = new JPanel(new GridLayout(6, 2, 5, 5));

        pnlForm.add(new JLabel("Mã KM:"));
        txtMaKM = new JTextField();
        pnlForm.add(txtMaKM);

        pnlForm.add(new JLabel("Tên KM:"));
        txtTenKM = new JTextField();
        pnlForm.add(txtTenKM);

        pnlForm.add(new JLabel("Phần trăm giảm:"));
        txtPhanTram = new JTextField();
        pnlForm.add(txtPhanTram);

        pnlForm.add(new JLabel("Ngày bắt đầu:"));
        txtNgayBD = new JTextField();
        pnlForm.add(txtNgayBD);

        pnlForm.add(new JLabel("Ngày kết thúc:"));
        txtNgayKT = new JTextField();
        pnlForm.add(txtNgayKT);

        pnlForm.add(new JLabel("Điều kiện áp dụng:"));
        txtDieuKien = new JTextField();
        pnlForm.add(txtDieuKien);

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
            JOptionPane.showMessageDialog(this, "Đã lưu khuyến mãi (demo).");
            dispose();
        });
        btnHuy.addActionListener(e -> {
            dispose();
        });
    }

    public boolean isSaved() {
        return isSaved;
    }

    // Trả về dữ liệu mới (nếu cần)
    public Object[] getKhuyenMaiData() {
        return new Object[]{
            txtMaKM.getText().trim(),
            txtTenKM.getText().trim(),
            Integer.parseInt(txtPhanTram.getText().trim()),
            txtNgayBD.getText().trim(),
            txtNgayKT.getText().trim(),
            txtDieuKien.getText().trim()
        };
    }
}
