package GUI.Dialog;

import DAO.KhuyenMaiDAO;
import DTO.KhuyenMaiDTO;
import java.awt.*;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class SuaKhuyenMaiDialog extends JDialog {
    private boolean updated = false;
    private KhuyenMaiDTO khuyenMai;

    // Các thành phần nhập liệu
    private JTextField txtMaKM, txtTenKM, txtPhanTram, txtDieuKien, txtMinOrder, txtMinQuantity;
    private JFormattedTextField txtNgayBatDau, txtNgayKetThuc;
    private JButton btnSave, btnCancel;

    public SuaKhuyenMaiDialog(Frame parent, KhuyenMaiDTO km) {
        super(parent, "Sửa Khuyến Mãi", true);
        this.khuyenMai = km;
        initUI();
        loadData();
        setSize(500, 400);
        setLocationRelativeTo(parent);
    }

    private void initUI() {
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Panel nhập thông tin
        JPanel inputPanel = new JPanel(new GridLayout(8, 2, 10, 10));
        
        // Định dạng ngày
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        txtMaKM = new JTextField();
        txtMaKM.setEditable(false); // Không cho sửa mã
        txtTenKM = new JTextField();
        txtPhanTram = new JTextField();
        txtDieuKien = new JTextField();
        txtNgayBatDau = new JFormattedTextField(dateFormat);
        txtNgayKetThuc = new JFormattedTextField(dateFormat);
        txtMinOrder = new JTextField();
        txtMinQuantity = new JTextField();

        inputPanel.add(new JLabel("Mã KM:"));
        inputPanel.add(txtMaKM);
        inputPanel.add(new JLabel("Tên KM*:"));
        inputPanel.add(txtTenKM);
        inputPanel.add(new JLabel("Phần trăm giảm* (%):"));
        inputPanel.add(txtPhanTram);
        inputPanel.add(new JLabel("Ngày bắt đầu* (dd/MM/yyyy):"));
        inputPanel.add(txtNgayBatDau);
        inputPanel.add(new JLabel("Ngày kết thúc* (dd/MM/yyyy):"));
        inputPanel.add(txtNgayKetThuc);
        inputPanel.add(new JLabel("Điều kiện áp dụng:"));
        inputPanel.add(txtDieuKien);
        inputPanel.add(new JLabel("Đơn tối thiểu (VND):"));
        inputPanel.add(txtMinOrder);
        inputPanel.add(new JLabel("Số lượng tối thiểu:"));
        inputPanel.add(txtMinQuantity);

        // Panel nút
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnSave = new JButton("Lưu");
        btnCancel = new JButton("Hủy");

        btnSave.addActionListener(e -> saveData());
        btnCancel.addActionListener(e -> dispose());

        buttonPanel.add(btnSave);
        buttonPanel.add(btnCancel);

        mainPanel.add(inputPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private void loadData() {
        txtMaKM.setText(khuyenMai.getMaSKKhuyenMai());
        txtTenKM.setText(khuyenMai.getTenKhuyenMai());
        txtPhanTram.setText(String.valueOf(khuyenMai.getPhanTramGiam()));
        txtNgayBatDau.setText(new SimpleDateFormat("dd/MM/yyyy").format(khuyenMai.getNgayBatDau()));
        txtNgayKetThuc.setText(new SimpleDateFormat("dd/MM/yyyy").format(khuyenMai.getNgayKetThuc()));
        txtDieuKien.setText(khuyenMai.getDieuKien());
        txtMinOrder.setText(String.valueOf(khuyenMai.getMinOrderAmount()));
        txtMinQuantity.setText(String.valueOf(khuyenMai.getMinQuantity()));
    }

    private void saveData() {
        try {
            // Validate dữ liệu
            if (!validateInput()) return;

            // Cập nhật đối tượng
            khuyenMai.setTenKhuyenMai(txtTenKM.getText().trim());
            khuyenMai.setPhanTramGiam(Integer.parseInt(txtPhanTram.getText().trim()));
            khuyenMai.setNgayBatDau(parseDate(txtNgayBatDau.getText()));
            khuyenMai.setNgayKetThuc(parseDate(txtNgayKetThuc.getText()));
            khuyenMai.setDieuKien(txtDieuKien.getText().trim());
            
            // Xử lý giá trị trống cho các trường số
            try {
                khuyenMai.setMinOrderAmount(Integer.parseInt(txtMinOrder.getText().trim()));
            } catch (NumberFormatException e) {
                khuyenMai.setMinOrderAmount(0);
            }
            
            try {
                khuyenMai.setMinQuantity(Integer.parseInt(txtMinQuantity.getText().trim()));
            } catch (NumberFormatException e) {
                khuyenMai.setMinQuantity(0);
            }

            // Gọi DAO
            boolean updateResult = KhuyenMaiDAO.update(khuyenMai);
            if (updateResult) {
                updated = true;
                JOptionPane.showMessageDialog(this, "Cập nhật thành công!");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Cập nhật thất bại!");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Lỗi: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private boolean validateInput() {
        // Check required fields
        if (txtTenKM.getText().trim().isEmpty() ||
            txtPhanTram.getText().trim().isEmpty() ||
            txtNgayBatDau.getText().trim().isEmpty() ||
            txtNgayKetThuc.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ các trường bắt buộc (*)");
            return false;
        }

        // Validate số
        try {
            int pt = Integer.parseInt(txtPhanTram.getText().trim());
            if (pt <= 0 || pt > 100) {
                JOptionPane.showMessageDialog(this, "Phần trăm giảm phải từ 1-100%");
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Phần trăm giảm không hợp lệ");
            return false;
        }

        // Validate ngày
        java.util.Date startDate = null;
        java.util.Date endDate = null;
        
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            sdf.setLenient(false);
            startDate = sdf.parse(txtNgayBatDau.getText().trim());
            endDate = sdf.parse(txtNgayKetThuc.getText().trim());
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this, "Định dạng ngày không hợp lệ (dd/MM/yyyy)");
            return false;
        }
        
        // Kiểm tra ngày bắt đầu <= ngày kết thúc
        if (startDate.after(endDate)) {
            JOptionPane.showMessageDialog(this, "Ngày bắt đầu phải trước hoặc cùng ngày kết thúc");
            return false;
        }

        // Validate các trường số (nếu có giá trị)
        if (!txtMinOrder.getText().trim().isEmpty()) {
            try {
                int minOrder = Integer.parseInt(txtMinOrder.getText().trim());
                if (minOrder < 0) {
                    JOptionPane.showMessageDialog(this, "Đơn tối thiểu không được âm");
                    return false;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Đơn tối thiểu không hợp lệ");
                return false;
            }
        }
        
        if (!txtMinQuantity.getText().trim().isEmpty()) {
            try {
                int minQty = Integer.parseInt(txtMinQuantity.getText().trim());
                if (minQty < 0) {
                    JOptionPane.showMessageDialog(this, "Số lượng tối thiểu không được âm");
                    return false;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Số lượng tối thiểu không hợp lệ");
                return false;
            }
        }

        return true;
    }

    private Date parseDate(String dateStr) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            sdf.setLenient(false);
            return new Date(sdf.parse(dateStr).getTime());
        } catch (ParseException e) {
            return null;
        }
    }

    public boolean isUpdated() {
        return updated;
    }
}