package GUI.Panel;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

public class BanQuatPanel extends JPanel implements ActionListener {
    // Tiêu đề
    private JLabel lblTitle;

    // Thông tin chung
    private JLabel lblNguoiLap, lblNgayLap, lblTrangThai, lblKhachHang, lblSanPham;
    private JTextField txtNguoiLap, txtNgayLap;
    private JRadioButton rdoThanhVien, rdoVangLai;
    private ButtonGroup bgTrangThai;

    // Khách hàng (combo phone + tên) & nút "Mới"
    private JComboBox<String> cbbKhachHang;
    private JButton btnMoiKhach;

    // Sản phẩm (combo) & nút "Thêm"
    private JComboBox<String> cbbSanPham;
    private JButton btnThemSP;

    // Bảng hiển thị sản phẩm
    private JTable table;
    private DefaultTableModel tableModel;

    // Phần dưới: Sử dụng điểm, Tổng, Điểm tích lũy, nút Lưu, Xuất PDF, Hủy
    private JCheckBox chkSuDungDiem;
    private JLabel lblTong, lblDiemTichLuy;
    private JTextField txtTong, txtDiemTichLuy;
    private JButton btnLuu, btnXuatPDF, btnHuy;

    // Màu nền pastel (có thể đổi)
    private static final Color BACKGROUND_COLOR = new Color(233, 247, 249);

    public BanQuatPanel() {
        initComponent();
    }

    private void initComponent() {
        // Sử dụng BorderLayout cho panel chính
        setLayout(new BorderLayout(10, 10));
        setBackground(BACKGROUND_COLOR);

        // ========== Tiêu đề ở trên cùng ==========
        lblTitle = new JLabel("HÓA ĐƠN BÁN QUẠT", SwingConstants.CENTER);
        lblTitle.setFont(lblTitle.getFont().deriveFont(Font.BOLD, 20f));
        add(lblTitle, BorderLayout.NORTH);

        // ========== Panel trung tâm chứa form + bảng ==========
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setOpaque(false); 
        add(centerPanel, BorderLayout.CENTER);

        // Tạo panel form (GridBagLayout) cho các trường nhập
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setOpaque(false);
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Dòng 1: Người lập, Ngày lập
        lblNguoiLap = new JLabel("Người lập:");
        txtNguoiLap = new JTextField(15);
        txtNguoiLap.setText("NV01"); // ví dụ cứng

        lblNgayLap = new JLabel("Ngày lập:");
        txtNgayLap = new JTextField(15);
        txtNgayLap.setText("2025-03-26"); // ví dụ cứng
        txtNgayLap.setEditable(false); // Ko cho sửa

        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(lblNguoiLap, gbc);
        gbc.gridx = 1;
        formPanel.add(txtNguoiLap, gbc);

        gbc.gridx = 2;
        formPanel.add(lblNgayLap, gbc);
        gbc.gridx = 3;
        formPanel.add(txtNgayLap, gbc);

        // Dòng 2: Trạng thái khách (radio)
        lblTrangThai = new JLabel("Trạng thái khách:");
        rdoThanhVien = new JRadioButton("Thành viên", true);
        rdoVangLai = new JRadioButton("Vãng lai");
        bgTrangThai = new ButtonGroup();
        bgTrangThai.add(rdoThanhVien);
        bgTrangThai.add(rdoVangLai);
        // Đăng ký sự kiện cho radio
        rdoThanhVien.addActionListener(this);
        rdoVangLai.addActionListener(this);

        JPanel pnlTrangThai = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        pnlTrangThai.setOpaque(false);
        pnlTrangThai.add(rdoThanhVien);
        pnlTrangThai.add(Box.createHorizontalStrut(10));
        pnlTrangThai.add(rdoVangLai);

        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(lblTrangThai, gbc);
        gbc.gridx = 1; gbc.gridwidth = 3;
        formPanel.add(pnlTrangThai, gbc);
        gbc.gridwidth = 1;

        // Dòng 3: Khách hàng (combo phone + tên), nút Mới
        lblKhachHang = new JLabel("Khách hàng:");
        cbbKhachHang = new JComboBox<>(new String[]{
            "3122410369 - Lê Nguyễn Nhất Tâm",
            "3123456789- Lưu Hồng Phúc"
        });
        btnMoiKhach = new JButton("Mới");

        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(lblKhachHang, gbc);
        gbc.gridx = 1;
        formPanel.add(cbbKhachHang, gbc);
        gbc.gridx = 2;
        formPanel.add(btnMoiKhach, gbc);

        // Dòng 4: Sản phẩm (quạt), nút Thêm
        lblSanPham = new JLabel("Sản phẩm (quạt):");
        cbbSanPham = new JComboBox<>(new String[]{
            "Q001 - Quạt đứng Y",
            "Q002 - Quạt treo tường X",
            "Q003 - Quạt bàn Z"
        });
        btnThemSP = new JButton("Thêm");

        gbc.gridx = 0; gbc.gridy = 3;
        formPanel.add(lblSanPham, gbc);
        gbc.gridx = 1;
        formPanel.add(cbbSanPham, gbc);
        gbc.gridx = 2;
        formPanel.add(btnThemSP, gbc);

        centerPanel.add(formPanel, BorderLayout.NORTH);

        // ========== Bảng sản phẩm ==========
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setOpaque(false);
        tablePanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY),
                "Danh sách sản phẩm",
                TitledBorder.LEFT,
                TitledBorder.TOP
        ));

        String[] columns = {"Mã SP", "Tên SP", "Lo SX", "Giá tiền", "Số lượng"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);

        tablePanel.add(new JScrollPane(table), BorderLayout.CENTER);
        centerPanel.add(tablePanel, BorderLayout.CENTER);

        // ========== Panel dưới: Sử dụng điểm, Tổng, Điểm, Nút Lưu/Xuất PDF/Hủy ==========
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setOpaque(false);
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(bottomPanel, BorderLayout.SOUTH);

        // Bên trái: checkbox "Sử dụng điểm"
        chkSuDungDiem = new JCheckBox("Sử dụng điểm");
        chkSuDungDiem.setOpaque(false);

        // Bên phải: Tổng, Điểm, nút
        JPanel rightBottom = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 5));
        rightBottom.setOpaque(false);

        lblTong = new JLabel("Tổng:");
        txtTong = new JTextField("0", 8);
        txtTong.setEditable(false);

        lblDiemTichLuy = new JLabel("Điểm Tích Lũy:");
        txtDiemTichLuy = new JTextField("0", 8);
        txtDiemTichLuy.setEditable(false);

        btnLuu = new JButton("Lưu");
        btnXuatPDF = new JButton("Xuất PDF");
        btnHuy = new JButton("Hủy");

        rightBottom.add(lblTong);
        rightBottom.add(txtTong);
        rightBottom.add(lblDiemTichLuy);
        rightBottom.add(txtDiemTichLuy);
        rightBottom.add(btnLuu);
        rightBottom.add(btnXuatPDF);
        rightBottom.add(btnHuy);

        bottomPanel.add(chkSuDungDiem, BorderLayout.WEST);
        bottomPanel.add(rightBottom, BorderLayout.EAST);

        // ========== Sự kiện nút ==========
        btnMoiKhach.addActionListener(this);
        btnThemSP.addActionListener(this);
        btnLuu.addActionListener(this);
        btnXuatPDF.addActionListener(this);
        btnHuy.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();

        if (src == rdoThanhVien) {
            // Nếu chọn "Thành viên", bật combo khách hàng
            cbbKhachHang.setEnabled(true);
        } else if (src == rdoVangLai) {
            // Nếu chọn "Vãng lai", combo khách hàng bị vô hiệu
            cbbKhachHang.setEnabled(false);
        }

        if(src == btnMoiKhach) {
            JOptionPane.showMessageDialog(this, "Thêm khách hàng mới (demo)!");
        } else if(src == btnThemSP) {
            // Lấy sản phẩm từ combo, thêm 1 dòng vào bảng
            String selected = (String) cbbSanPham.getSelectedItem();
            // Tách Mã SP, Tên SP
            String ma = selected.split(" - ")[0];
            String ten = selected.split(" - ")[1];
            // Demo "Lo SX" = "Việt Nam", "Giá tiền" = 500000, "Số lượng"=1
            tableModel.addRow(new Object[]{ma, ten, "Việt Nam", 500000, 1});
        } else if(src == btnLuu) {
            JOptionPane.showMessageDialog(this, "Đã lưu hóa đơn (demo)!");
            // Xóa bảng, reset
            tableModel.setRowCount(0);
            txtTong.setText("0");
            txtDiemTichLuy.setText("0");
        } else if(src == btnXuatPDF) {
            JOptionPane.showMessageDialog(this, "Xuất PDF (demo)!");
        } else if(src == btnHuy) {
            JOptionPane.showMessageDialog(this, "Hủy (demo)!");
        }
    }
}
