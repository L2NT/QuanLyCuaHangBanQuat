package GUI.Panel;

import dto.DBConnection;
import DTO.KhuyenMaiDTO;
import DAO.KhuyenMaiDAO;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.table.*;

public class BanQuatPanel extends JPanel implements ActionListener {

    // Tiêu đề
    private JLabel lblTitle;

    // Thông tin chung
    private JLabel lblNguoiLap, lblNgayLap, lblTrangThai, lblKhachHang, lblSanPham, lblKhuyenMai;
    private JTextField txtNguoiLap, txtNgayLap;
    private String MaNhanVien;

    public BanQuatPanel(String maNhanVien) {
        this.MaNhanVien = maNhanVien;
        initComponent();
    }
    private JRadioButton rdoThanhVien, rdoVangLai;
    private ButtonGroup bgTrangThai;

    // Khách hàng (combo phone + tên) với nút Mới
    private JComboBox<String> cbbKhachHang;

    // Khuyến mãi (combo)
    private JComboBox<String> cbbKhuyenMai;
    private JCheckBox chkApDungKM;

    // Sản phẩm (combo) và nút Thêm
    private JComboBox<String> cbbSanPham;
    private JButton btnThemSP;

    // Bảng hiển thị sản phẩm
    private JTable table;
    private DefaultTableModel tableModel;

    // Phần dưới: Tổng, các nút
    private JLabel lblTong;
    private JTextField txtTong;
    private JButton btnThanhToan, btnXuatPDF, btnHuy;

    private static final Color BACKGROUND_COLOR = new Color(233, 247, 249);

    public BanQuatPanel() {
        initComponent();
    }

    private void initComponent() {
        setLayout(new BorderLayout(10, 10));
        setBackground(BACKGROUND_COLOR);

        lblTitle = new JLabel("Bán Quạt", SwingConstants.CENTER);
        lblTitle.setFont(lblTitle.getFont().deriveFont(Font.BOLD, 20f));
        add(lblTitle, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new BorderLayout(10, 10));
        centerPanel.setOpaque(false);
        add(centerPanel, BorderLayout.CENTER);

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setOpaque(false);
        formPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Người lập / Ngày lập
        lblNguoiLap = new JLabel("Người lập:");
        String tenNV = loadTenNhanVien(this.MaNhanVien);  // Sử dụng biến đã truyền từ constructor
        txtNguoiLap = new JTextField(tenNV, 15);
        txtNguoiLap.setEditable(false);
        lblNgayLap = new JLabel("Ngày lập:");
        // Lấy ngày hôm nay dạng yyyy-MM-dd
        LocalDate today = LocalDate.now();
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        txtNgayLap = new JTextField(today.format(fmt), 15);
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
        cbbKhachHang = new JComboBox<>();
        loadCustomersToCombo();
        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(lblKhachHang, gbc);
        gbc.gridx = 1;
        formPanel.add(cbbKhachHang, gbc);
        gbc.gridx = 2;

        // Khuyến mãi
        lblKhuyenMai = new JLabel("Khuyến mãi:");
        cbbKhuyenMai = new JComboBox<>();
        loadPromotionsToCombo();
        chkApDungKM = new JCheckBox("Áp dụng");
        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(lblKhuyenMai, gbc);
        gbc.gridx = 1;
        formPanel.add(cbbKhuyenMai, gbc);
        gbc.gridx = 2;
        formPanel.add(chkApDungKM, gbc);

        // Sản phẩm
        lblSanPham = new JLabel("Chọn quạt:");
        cbbSanPham = new JComboBox<>();
        btnThemSP = new JButton("Thêm");
        gbc.gridx = 0;
        gbc.gridy = 4;
        formPanel.add(lblSanPham, gbc);
        gbc.gridx = 1;
        formPanel.add(cbbSanPham, gbc);
        gbc.gridx = 2;
        formPanel.add(btnThemSP, gbc);

        centerPanel.add(formPanel, BorderLayout.NORTH);

        // Bảng
        tableModel = new DefaultTableModel(new String[]{"Mã Quạt", "Tên Quạt", "Giá", "Thương hiệu", "Số lượng"}, 0) {
            @Override
            public boolean isCellEditable(int r, int c) {
                return c == 4;
            }

            @Override
            public Class<?> getColumnClass(int c) {
                if (c == 2 || c == 4) {
                    return Integer.class;
                }
                return String.class;
            }
        };
        tableModel.addTableModelListener(e -> {
            if (e.getType() == TableModelEvent.UPDATE && e.getColumn() == 4) {
                updateTotal();
            }
        });
        table = new JTable(tableModel);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        TableColumn qtyCol = table.getColumnModel().getColumn(4);
        qtyCol.setCellEditor(new SpinnerEditor());
        JScrollPane scroll = new JScrollPane(table);
        scroll.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY), "Danh sách sản phẩm", TitledBorder.LEFT, TitledBorder.TOP));
        centerPanel.add(scroll, BorderLayout.CENTER);

        loadProductsToCombo();

        // Bottom
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setOpaque(false);
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        add(bottomPanel, BorderLayout.SOUTH);
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

        // events
        btnThemSP.addActionListener(this);
        btnXuatPDF.addActionListener(this);
        btnHuy.addActionListener(this);
        btnThanhToan.addActionListener(this);
        chkApDungKM.addActionListener(this);
    }

    private void loadProductsToCombo() {
        try (Connection conn = DBConnection.getConnection(); Statement st = conn.createStatement(); ResultSet rs = st.executeQuery("SELECT MaQuat,TenQuat FROM quat")) {
            while (rs.next()) {
                cbbSanPham.addItem(rs.getString("MaQuat") + " - " + rs.getString("TenQuat"));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Lỗi load sản phẩm: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadCustomersToCombo() {
        cbbKhachHang.removeAllItems();
        try (Connection conn = DBConnection.getConnection(); Statement st = conn.createStatement(); ResultSet rs = st.executeQuery("SELECT Sdt_KH,HoTenKH FROM khachhang")) {
            while (rs.next()) {
                cbbKhachHang.addItem(rs.getString("Sdt_KH") + " – " + rs.getString("HoTenKH"));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Lỗi load khách hàng: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadPromotionsToCombo() {
        cbbKhuyenMai.removeAllItems();
        String sql = "SELECT MaSKKhuyenMai,DieuKien,TenKhuyenMai FROM su_kien_khuyen_mai";
        try (Connection conn = DBConnection.getConnection(); Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                String item = rs.getString("MaSKKhuyenMai") + " – " + rs.getString("DieuKien") + " – " + rs.getString("TenKhuyenMai");
                cbbKhuyenMai.addItem(item);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Lỗi load khuyến mãi: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private QuatDTO fetchQuatDetail(String maQuat) throws SQLException {
        String sql = "SELECT Gia,ThuongHieu FROM quat WHERE MaQuat=?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, maQuat);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new QuatDTO(maQuat, (String) cbbSanPham.getSelectedItem(), rs.getInt("Gia"), rs.getString("ThuongHieu"));
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
                    tableModel.addRow(new Object[]{q.getMaQuat(), q.getTenQuat(), q.getGia(), q.getThuongHieu(), 1});
                    updateTotal();
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Không lấy được thông tin!\n" + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } else if (src == btnThanhToan) {
            processPayment();
        } else if (src == btnXuatPDF) {
            JOptionPane.showMessageDialog(this, "Xuất PDF (demo)!");
        } else if (src == btnHuy) {
            tableModel.setRowCount(0);
            txtTong.setText("0");
            updateTotal();
        } else if (src == chkApDungKM) {
            updateTotal();
        }
    }

    private void processPayment() {
        int originalTotal = calculateSubtotal();
        int finalTotal = originalTotal;
        String discountInfo = "";

        // Kiểm tra áp dụng khuyến mãi nếu checkbox được chọn
        if (chkApDungKM.isSelected()) {
            String selectedKM = (String) cbbKhuyenMai.getSelectedItem();
            if (selectedKM != null && !selectedKM.isEmpty()) {
                String maKM = selectedKM.split(" – ")[0].trim();
                PromotionCheckResult result = checkPromotion(maKM, originalTotal);

                if (result.isValid) {
                    // Áp dụng giảm giá
                    int discount = (originalTotal * result.discountPercent) / 100;
                    finalTotal = originalTotal - discount;
                    discountInfo = "\nKhuyến mãi: " + result.promotionName
                            + "\nGiảm giá: " + discount + " VND (" + result.discountPercent + "%)";
                } else {
                    // Thông báo không thỏa điều kiện
                    JOptionPane.showMessageDialog(this,
                            "Không thể áp dụng khuyến mãi: " + result.message,
                            "Thông báo", JOptionPane.WARNING_MESSAGE);
                    chkApDungKM.setSelected(false);
                }
            }
        }

        txtTong.setText(String.valueOf(finalTotal));
        String message = "Tổng thanh toán: " + finalTotal + " VND" + discountInfo + "\nBạn có muốn thanh toán?";

        int choice = JOptionPane.showConfirmDialog(this, message, "Xác nhận thanh toán",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (choice == JOptionPane.OK_OPTION) {
            JOptionPane.showMessageDialog(this, "Thanh toán thành công!", "Kết quả", JOptionPane.INFORMATION_MESSAGE);
            tableModel.setRowCount(0);
            txtTong.setText("0");
            updateTotal();
        } else {
            JOptionPane.showMessageDialog(this, "Bạn đã hủy thanh toán.", "Kết quả", JOptionPane.WARNING_MESSAGE);
        }
    }

    private int calculateSubtotal() {
        int total = 0;
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            total += ((int) tableModel.getValueAt(i, 2)) * ((int) tableModel.getValueAt(i, 4));
        }
        return total;
    }

    private void updateTotal() {
        int total = calculateSubtotal();

        // Nếu áp dụng khuyến mãi, hiển thị tổng tiền đã giảm giá
        if (chkApDungKM.isSelected() && cbbKhuyenMai.getSelectedItem() != null) {
            String maKM = ((String) cbbKhuyenMai.getSelectedItem()).split(" – ")[0].trim();
            PromotionCheckResult result = checkPromotion(maKM, total);

            if (result.isValid) {
                int discount = (total * result.discountPercent) / 100;
                total -= discount;
            }
        }

        txtTong.setText(String.valueOf(total));
        boolean hasItems = tableModel.getRowCount() > 0;
        btnThanhToan.setEnabled(hasItems);
        btnXuatPDF.setEnabled(hasItems);
    }

    /**
     * Lớp chứa kết quả kiểm tra khuyến mãi
     */
    private static class PromotionCheckResult {

        boolean isValid;
        String message;
        int discountPercent;
        String promotionName;

        public PromotionCheckResult(boolean isValid, String message, int discountPercent, String promotionName) {
            this.isValid = isValid;
            this.message = message;
            this.discountPercent = discountPercent;
            this.promotionName = promotionName;
        }
    }

    /**
     * Kiểm tra điều kiện áp dụng khuyến mãi
     *
     * @param maKM Mã khuyến mãi
     * @param total Tổng tiền hóa đơn
     * @return Kết quả kiểm tra
     */
    private PromotionCheckResult checkPromotion(String maKM, int total) {
        KhuyenMaiDAO kmDAO = new KhuyenMaiDAO();
        KhuyenMaiDTO km = kmDAO.selectById(maKM);

        if (km == null) {
            return new PromotionCheckResult(false, "Không tìm thấy thông tin khuyến mãi", 0, "");
        }

        // Kiểm tra ngày khuyến mãi còn hiệu lực không
        Date currentDate = new Date();
        if (currentDate.before(km.getNgayBatDau()) || currentDate.after(km.getNgayKetThuc())) {
            return new PromotionCheckResult(false,
                    "Khuyến mãi chỉ áp dụng từ " + km.getNgayBatDau() + " đến " + km.getNgayKetThuc(),
                    0, "");
        }

        // Kiểm tra điều kiện số tiền tối thiểu
        if (km.getMinOrderAmount() > 0 && total < km.getMinOrderAmount()) {
            return new PromotionCheckResult(false,
                    "Cần đạt giá trị đơn hàng tối thiểu " + km.getMinOrderAmount() + " VND",
                    0, "");
        }

        // Kiểm tra điều kiện số lượng sản phẩm tối thiểu
        int totalQuantity = 0;
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            totalQuantity += (int) tableModel.getValueAt(i, 4);
        }

        if (km.getMinQuantity() > 0 && totalQuantity < km.getMinQuantity()) {
            return new PromotionCheckResult(false,
                    "Cần mua tối thiểu " + km.getMinQuantity() + " sản phẩm",
                    0, "");
        }

        return new PromotionCheckResult(true,
                "Áp dụng thành công khuyến mãi " + km.getTenKhuyenMai(),
                km.getPhanTramGiam(), km.getTenKhuyenMai());
    }

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

    private String loadTenNhanVien(String maNV) {
        String sql = "SELECT HoTenNV FROM nhanvien WHERE MaNhanVien = ?";
        try (Connection c = DBConnection.getConnection(); PreparedStatement p = c.prepareStatement(sql)) {
            p.setString(1, maNV);
            try (ResultSet r = p.executeQuery()) {
                if (r.next()) {
                    return r.getString("HoTenNV");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return maNV;
    }

    private static class SpinnerEditor extends AbstractCellEditor implements TableCellEditor {

        private final JSpinner spinner = new JSpinner(new SpinnerNumberModel(1, 1, 1000, 1));

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            spinner.setValue(value);
            return spinner;
        }

        @Override
        public Object getCellEditorValue() {
            return spinner.getValue();
        }
    }
}
