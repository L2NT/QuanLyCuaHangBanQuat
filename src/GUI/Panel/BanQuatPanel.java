package GUI.Panel;

import Database.DBConnection;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.*;

public class BanQuatPanel extends JPanel implements ActionListener {

    // Tiêu đề
    private JLabel lblTitle;

    // Thông tin chung
    private JLabel lblNguoiLap, lblNgayLap, lblTrangThai, lblKhachHang, lblSanPham;
    private JTextField txtNguoiLap, txtNgayLap;
    private JRadioButton rdoThanhVien, rdoVangLai;
    private ButtonGroup bgTrangThai;

    // Khách hàng (combo phone + tên) với nút Mới
    private JComboBox<String> cbbKhachHang;
    private JButton btnMoiKhach;

    // Sản phẩm (combo) và nút Thêm
    private JComboBox<String> cbbSanPham;
    private JButton btnThemSP;

    // Bảng hiển thị sản phẩm
    private JTable table;
    private DefaultTableModel tableModel;

    // Phần dưới: Sử dụng điểm, Tổng, Điểm tích lũy, nút Lưu, Thanh toán, Xuất PDF, Hủy
    private JLabel lblTong;
    private JTextField txtTong;
    private JButton btnThanhToan, btnXuatPDF, btnHuy;

    // Màu nền pastel 
    private static final Color BACKGROUND_COLOR = new Color(233, 247, 249);

    public BanQuatPanel() {
        initComponent();
    }

    private void initComponent() {
        setLayout(new BorderLayout(10, 10));
        setBackground(BACKGROUND_COLOR);

        // ===== Tiêu đề =====
        lblTitle = new JLabel("Bán Quạt", SwingConstants.CENTER);
        lblTitle.setFont(lblTitle.getFont().deriveFont(Font.BOLD, 20f));
        add(lblTitle, BorderLayout.NORTH);

        // ===== Center panel =====
        JPanel centerPanel = new JPanel(new BorderLayout(10, 10));
        centerPanel.setOpaque(false);
        add(centerPanel, BorderLayout.CENTER);

        // Form nhập
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setOpaque(false);
        formPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Người lập / Ngày lập
        lblNguoiLap = new JLabel("Người lập:");
        txtNguoiLap = new JTextField("NV01", 15);
        lblNgayLap = new JLabel("Ngày lập:");
        txtNgayLap = new JTextField("2025-03-26", 15);
        txtNgayLap.setEditable(false);

        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(lblNguoiLap, gbc);
        gbc.gridx = 1;
        formPanel.add(txtNguoiLap, gbc);
        gbc.gridx = 2;
        formPanel.add(lblNgayLap, gbc);
        gbc.gridx = 3;
        formPanel.add(txtNgayLap, gbc);

        // Trạng thái khách
        lblTrangThai = new JLabel("Trạng thái khách:");
        rdoThanhVien = new JRadioButton("Thành viên", true);
        rdoVangLai = new JRadioButton("Vãng lai");
        bgTrangThai = new ButtonGroup();
        bgTrangThai.add(rdoThanhVien);
        bgTrangThai.add(rdoVangLai);
        rdoThanhVien.addActionListener(this);
        rdoVangLai.addActionListener(this);
        JPanel pnlTrangThai = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        pnlTrangThai.setOpaque(false);
        pnlTrangThai.add(rdoThanhVien);
        pnlTrangThai.add(Box.createHorizontalStrut(10));
        pnlTrangThai.add(rdoVangLai);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(lblTrangThai, gbc);
        gbc.gridx = 1;
        gbc.gridwidth = 3;
        formPanel.add(pnlTrangThai, gbc);
        gbc.gridwidth = 1;

        // Khách hàng
        lblKhachHang = new JLabel("Khách hàng:");
        cbbKhachHang = new JComboBox<>(new String[]{/* load tu elsewhere */});
        btnMoiKhach = new JButton("Mới");

        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(lblKhachHang, gbc);
        gbc.gridx = 1;
        formPanel.add(cbbKhachHang, gbc);
        gbc.gridx = 2;
        formPanel.add(btnMoiKhach, gbc);

        // Sản phẩm
        lblSanPham = new JLabel("Chọn quạt:");
        cbbSanPham = new JComboBox<>();
        btnThemSP = new JButton("Thêm");

        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(lblSanPham, gbc);
        gbc.gridx = 1;
        formPanel.add(cbbSanPham, gbc);
        gbc.gridx = 2;
        formPanel.add(btnThemSP, gbc);

        centerPanel.add(formPanel, BorderLayout.NORTH);

        // ===== Bảng =====
        tableModel = new DefaultTableModel(
                new String[]{"Mã Quạt", "Tên Quạt", "Giá", "Thương hiệu", "Số lượng"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int col) {
                // chỉ cho chỉnh số lượng
                return col == 4;
            }

            @Override
            public Class<?> getColumnClass(int col) {
                if (col == 2) {
                    return Integer.class;
                }
                if (col == 4) {
                    return Integer.class;
                }
                return String.class;
            }
        };
        table = new JTable(tableModel);
        // thêm Spinner editor cho cột Số lượng
        TableColumn qtyCol = table.getColumnModel().getColumn(4);
        qtyCol.setCellEditor(new SpinnerEditor());

        JScrollPane scroll = new JScrollPane(table);
        scroll.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY),
                "Danh sách sản phẩm",
                TitledBorder.LEFT, TitledBorder.TOP
        ));
        centerPanel.add(scroll, BorderLayout.CENTER);

        loadProductsToCombo();

        // ===== Bottom panel =====
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setOpaque(false);
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        add(bottomPanel, BorderLayout.SOUTH);

       


        // phải: các nút và tổng tiền
        JPanel rightBot = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        rightBot.setOpaque(false);
        lblTong = new JLabel("Tổng:");
        txtTong = new JTextField("0", 8);
        txtTong.setEditable(false);
        btnThanhToan = new JButton("Thanh toán");
        btnXuatPDF = new JButton("Xuất PDF");
        btnHuy = new JButton("Xoá");

        rightBot.add(lblTong);
        rightBot.add(txtTong);
        rightBot.add(btnThanhToan);
        rightBot.add(btnXuatPDF);
        rightBot.add(btnHuy);

        bottomPanel.add(rightBot, BorderLayout.EAST);

        // ===== Đăng ký sự kiện =====
        btnThemSP.addActionListener(this);
        btnMoiKhach.addActionListener(this);
        btnXuatPDF.addActionListener(this);
        btnHuy.addActionListener(this);
        btnThanhToan.addActionListener(this);
    }

    /**
     * Load danh sách quạt từ CSDL lên combobox dưới dạng "MaQuat - TenQuat"
     */
    private void loadProductsToCombo() {
        try (Connection conn = DBConnection.getConnection(); Statement st = conn.createStatement(); ResultSet rs = st.executeQuery("SELECT MaQuat, TenQuat FROM quat")) {
            while (rs.next()) {
                String item = rs.getString("MaQuat") + " - " + rs.getString("TenQuat");
                cbbSanPham.addItem(item);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Lỗi load sản phẩm: " + ex.getMessage(),
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Lấy chi tiết quạt (gia, thuong hieu) từ CSDL theo MaQuat
     */
    private QuatDTO fetchQuatDetail(String maQuat) throws SQLException {
        String sql = "SELECT Gia, ThuongHieu FROM quat WHERE MaQuat = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, maQuat);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new QuatDTO(maQuat,
                            (String) cbbSanPham.getSelectedItem(),
                            rs.getInt("Gia"),
                            rs.getString("ThuongHieu"));
                }
            }
        }
        return null;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();

        if (src == rdoThanhVien) {
            cbbKhachHang.setEnabled(true);
        } else if (src == rdoVangLai) {
            cbbKhachHang.setEnabled(false);
        } else if (src == btnThemSP) {
            String sel = (String) cbbSanPham.getSelectedItem();
            String ma = sel.split(" - ")[0].trim();
            try {
                QuatDTO q = fetchQuatDetail(ma);
                if (q != null) {
                    // thêm hàng với số lượng mặc định 1
                    tableModel.addRow(new Object[]{
                        q.getMaQuat(), q.getTenQuat(),
                        q.getGia(), q.getThuongHieu(), 1
                    });
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this,
                        "Không lấy được thông tin chi tiết!\n" + ex.getMessage(),
                        "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } else if (src == btnThanhToan) {
            int total = 0;
            for (int i = 0; i < tableModel.getRowCount(); i++) {
                int price = (int) tableModel.getValueAt(i, 2);
                int qty = (int) tableModel.getValueAt(i, 4);
                total += price * qty;
            }
            txtTong.setText(String.valueOf(total));

            // Hiển thị hộp thoại Xác nhận/ Hủy
            int choice = JOptionPane.showConfirmDialog(
                    this,
                    "Tổng thanh toán: " + total + " VND\nBạn có muốn thanh toán?",
                    "Xác nhận thanh toán",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE
            );

            if (choice == JOptionPane.OK_OPTION) {
                // Xử lý khi người dùng chọn OK
                JOptionPane.showMessageDialog(this, "Thanh toán thành công!", "Kết quả", JOptionPane.INFORMATION_MESSAGE);
                // Ví dụ: bạn có thể reset bảng ở đây
                tableModel.setRowCount(0);
                txtTong.setText("0");
       
            } else {
                // Xử lý khi người dùng chọn Cancel
                JOptionPane.showMessageDialog(this, "Bạn đã hủy thanh toán.", "Kết quả", JOptionPane.WARNING_MESSAGE);
            }
        } else if (src == btnXuatPDF) {
            JOptionPane.showMessageDialog(this, "Xuất PDF (demo)!");
        } else if (src == btnHuy) {
            tableModel.setRowCount(0);
            txtTong.setText("0");
        } else if (src == btnMoiKhach) {
            JOptionPane.showMessageDialog(this, "Thêm khách hàng mới (demo)!");
        }
    }

    /**
     * DTO đơn giản để tách fetch chi tiết quạt
     */
    private static class QuatDTO {

        private final String maQuat, tenQuat, thuongHieu;
        private final int gia;

        public QuatDTO(String maQuat, String tenQuat, int gia, String thuongHieu) {
            this.maQuat = maQuat;
            this.tenQuat = tenQuat;
            this.gia = gia;
            this.thuongHieu = thuongHieu;
        }

        public String getMaQuat() {
            return maQuat;
        }

        public String getTenQuat() {
            return tenQuat;
        }

        public int getGia() {
            return gia;
        }

        public String getThuongHieu() {
            return thuongHieu;
        }
    }

    /**
     * Spinner editor cho cột Số lượng
     */
    private static class SpinnerEditor extends AbstractCellEditor implements TableCellEditor {

        private final JSpinner spinner = new JSpinner(new SpinnerNumberModel(1, 1, 1000, 1));

        @Override
        public Component getTableCellEditorComponent(JTable table,
                Object value, boolean isSelected, int row, int column) {
            spinner.setValue(value);
            return spinner;
        }

        @Override
        public Object getCellEditorValue() {
            return spinner.getValue();
        }
    }
}
