package GUI.Dialog;

import DTO.KhuyenMaiDTO;
import DAO.KhuyenMaiDAO;
import java.util.Date;
import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

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
                               Date ngayBD, Date ngayKT, String dieuKien,
                               int minOrderAmount, int minQuantity) {
        super(owner, "Sửa Khuyến Mãi", ModalityType.APPLICATION_MODAL);
        initComponent();
        // Nạp dữ liệu cũ
        txtMaKM.setText(maKM);
        txtTenKM.setText(tenKM);
        txtPhanTram.setText(String.valueOf(phanTram));
        txtNgayBD.setText(formatDate(ngayBD));
        txtNgayKT.setText(formatDate(ngayKT));
        txtDieuKien.setText(dieuKien);
        // Có thể lưu 2 tham số minOrderAmount và minQuantity nếu cần
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

        pnlForm.add(new JLabel("Ngày bắt đầu (yyyy-MM-dd):"));
        txtNgayBD = new JTextField();
        pnlForm.add(txtNgayBD);

        pnlForm.add(new JLabel("Ngày kết thúc (yyyy-MM-dd):"));
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

        // Sự kiện nút Lưu
        btnLuu.addActionListener(e -> {
            try {
                String maKM = txtMaKM.getText().trim();
                String tenKM = txtTenKM.getText().trim();
                int phanTram = Integer.parseInt(txtPhanTram.getText().trim());
                Date dateBD = convertToDate(txtNgayBD.getText().trim());
                Date dateKT = convertToDate(txtNgayKT.getText().trim());
                String dieuKien = txtDieuKien.getText().trim();

                // Ví dụ sử dụng giá trị mặc định cho MinOrder và MinQuantity
                int minOrderAmount = 0;
                int minQuantity = 0;
                
                KhuyenMaiDTO km = new KhuyenMaiDTO(
                    maKM, tenKM, phanTram,
                    dateBD, dateKT, dieuKien,
                    minOrderAmount, minQuantity
                );

                boolean success = KhuyenMaiDAO.insert(km);
                if (success) {
                    isSaved = true;
                    JOptionPane.showMessageDialog(this, "Thêm khuyến mãi thành công!");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Thêm thất bại, mã KM có thể đã tồn tại.");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Lỗi khi thêm: " + ex.getMessage());
            }
        });

        // Sự kiện nút Hủy
        btnHuy.addActionListener(e -> dispose());
    }

    public boolean isSaved() {
        return isSaved;
    }

    private Date convertToDate(String str) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            return sdf.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String formatDate(Date date) {
        if (date == null) return "";
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }
}
