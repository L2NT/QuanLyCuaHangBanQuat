package GUI.Panel;

import GUI.Dialog.ThemKhuyenMaiDialog;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;


public class KhuyenMaiPanel extends JPanel implements ItemListener, KeyListener {
    private JButton btnThem, btnXoa, btnChiTiet, btnExcel, btnLamMoi;
    private JComboBox<String> cbbPhanTram;  // ComboBox lọc theo phần trăm giảm (hoặc tùy ý)
    private JTextField txtSearch;           // Ô tìm kiếm
    private JTable table;
    private DefaultTableModel tableModel;

    // Danh sách khuyến mãi gốc (dummy)
    private List<KhuyenMai> listKM;

    public KhuyenMaiPanel() {
        initComponent();
        initData();
        loadDataToTable(listKM); // Hiển thị toàn bộ ban đầu
    }

    private void initComponent() {
        setLayout(new BorderLayout(10, 10));
        setBackground(Color.WHITE);

        // ================== Thanh công cụ (toolbar) ==================
        // Dùng BorderLayout để tách: 
        // - bên trái (Thêm, Xóa, Chi tiết, Xuất Excel)
        // - bên phải (combo, ô tìm kiếm, nút làm mới)
        JPanel toolbar = new JPanel(new BorderLayout());

        // (A) Panel bên trái - nhóm nút
        JPanel leftToolPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        btnThem = new JButton("THÊM");
        btnXoa = new JButton("XÓA");
        btnChiTiet = new JButton("CHI TIẾT");
        btnExcel = new JButton("XUẤT EXCEL");
        leftToolPanel.add(btnThem);
        leftToolPanel.add(btnXoa);
        leftToolPanel.add(btnChiTiet);
        leftToolPanel.add(btnExcel);

        // (B) Panel bên phải - combo, ô tìm kiếm, nút làm mới
        JPanel rightToolPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 5));
        // Ví dụ combo lọc theo phần trăm giảm
        cbbPhanTram = new JComboBox<>(new String[]{
            "Tất cả",
            "Dưới 10%",
            "Từ 10% - 20%",
            "Trên 20%"
        });
        cbbPhanTram.addItemListener(this);

        txtSearch = new JTextField("Nhập nội dung tìm kiếm...", 15);
        txtSearch.addKeyListener(this);

        btnLamMoi = new JButton("LÀM MỚI");

        rightToolPanel.add(cbbPhanTram);
        rightToolPanel.add(txtSearch);
        rightToolPanel.add(btnLamMoi);

        // Ghép hai panel vào toolbar
        toolbar.add(leftToolPanel, BorderLayout.WEST);
        toolbar.add(rightToolPanel, BorderLayout.EAST);

        // Thêm toolbar lên top
        add(toolbar, BorderLayout.NORTH);

        // ================== Bảng hiển thị khuyến mãi ==================
        String[] columns = {
            "Mã KM",
            "Tên khuyến mãi",
            "Phần trăm giảm",
            "Ngày bắt đầu",
            "Ngày kết thúc",
            "Điều kiện áp dụng"
        };
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // ================== Sự kiện nút (demo) ==================
        btnThem.addActionListener(e -> {
            // Mở dialog thêm khuyến mãi
            ThemKhuyenMaiDialog dlg = new ThemKhuyenMaiDialog(SwingUtilities.getWindowAncestor(this));
            dlg.setVisible(true);
            // Nếu cần, bạn có thể lấy dữ liệu mới từ dlg để thêm vào bảng
        });

        btnXoa.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                tableModel.removeRow(row);
            }
        });

        btnChiTiet.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row < 0) {
                JOptionPane.showMessageDialog(this, "Chưa chọn khuyến mãi!");
                return;
            }
            // Demo: hiển thị thông báo
            // Hoặc bạn có thể mở dialog sửa chi tiết
            JOptionPane.showMessageDialog(this, "Chi tiết khuyến mãi (demo)!");
        });

        btnExcel.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Xuất Excel (demo)!");
        });

        btnLamMoi.addActionListener(e -> {
            // Xoá bảng rồi load lại danh sách gốc
            loadDataToTable(listKM);
        });
    }

    /**
     * Tạo dữ liệu mẫu cho khuyến mãi
     */
    private void initData() {
        listKM = new ArrayList<>();
        listKM.add(new KhuyenMai("KM001", "Giảm giá mùa hè", 10, "2025-06-01", "2025-06-15", "Đơn > 500k"));
        listKM.add(new KhuyenMai("KM002", "Giảm giá tri ân", 20, "2025-07-01", "2025-07-10", "Mọi đơn hàng"));
        listKM.add(new KhuyenMai("KM003", "Flash Sale", 5, "2025-07-05", "2025-07-06", "Giới hạn 1 lần mua"));
        listKM.add(new KhuyenMai("KM004", "Giảm sốc", 25, "2025-08-01", "2025-08-05", "Mua combo"));
        // ... Thêm tuỳ ý
    }

    /**
     * Đổ danh sách khuyến mãi vào bảng
     */
    private void loadDataToTable(List<KhuyenMai> data) {
        tableModel.setRowCount(0);
        for (KhuyenMai km : data) {
            tableModel.addRow(new Object[]{
                km.getMaKM(),
                km.getTenKM(),
                km.getPhanTramGiam(),
                km.getNgayBatDau(),
                km.getNgayKetThuc(),
                km.getDieuKienApDung()
            });
        }
    }

    /**
     * Lọc theo combo (phần trăm giảm) + ô tìm kiếm
     */
    private void filterData() {
        String selectedCombo = (String) cbbPhanTram.getSelectedItem(); // "Tất cả", "Dưới 10%", "Từ 10% - 20%", "Trên 20%"
        String searchText = txtSearch.getText().trim().toLowerCase();

        List<KhuyenMai> result = new ArrayList<>();
        for (KhuyenMai km : listKM) {
            // 1. Kiểm tra phần trăm giảm
            boolean matchPT = true;
            int pt = km.getPhanTramGiam();

            switch (selectedCombo) {
                case "Dưới 10%":
                    matchPT = (pt < 10);
                    break;
                case "Từ 10% - 20%":
                    matchPT = (pt >= 10 && pt <= 20);
                    break;
                case "Trên 20%":
                    matchPT = (pt > 20);
                    break;
                // "Tất cả" => matchPT = true
            }

            // 2. Kiểm tra từ khoá search (trong mã KM, tên KM, điều kiện, ...)
            boolean matchSearch = searchText.isEmpty()
                    || km.getMaKM().toLowerCase().contains(searchText)
                    || km.getTenKM().toLowerCase().contains(searchText)
                    || km.getDieuKienApDung().toLowerCase().contains(searchText);

            if (matchPT && matchSearch) {
                result.add(km);
            }
        }
        loadDataToTable(result);
    }

    // ========== ItemListener cho ComboBox ==========
    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            filterData();
        }
    }

    // ========== KeyListener cho ô tìm kiếm ==========
    @Override
    public void keyReleased(KeyEvent e) {
        filterData();
    }
    @Override
    public void keyTyped(KeyEvent e) { }
    @Override
    public void keyPressed(KeyEvent e) { }

    // ========== Lớp KhuyenMai (dummy) ==========
    class KhuyenMai {
        private String maKM;
        private String tenKM;
        private int phanTramGiam;
        private String ngayBatDau;
        private String ngayKetThuc;
        private String dieuKienApDung;

        public KhuyenMai(String maKM, String tenKM, int phanTramGiam,
                         String ngayBD, String ngayKT, String dieuKien) {
            this.maKM = maKM;
            this.tenKM = tenKM;
            this.phanTramGiam = phanTramGiam;
            this.ngayBatDau = ngayBD;
            this.ngayKetThuc = ngayKT;
            this.dieuKienApDung = dieuKien;
        }

        public String getMaKM() { return maKM; }
        public String getTenKM() { return tenKM; }
        public int getPhanTramGiam() { return phanTramGiam; }
        public String getNgayBatDau() { return ngayBatDau; }
        public String getNgayKetThuc() { return ngayKetThuc; }
        public String getDieuKienApDung() { return dieuKienApDung; }
    }
}
