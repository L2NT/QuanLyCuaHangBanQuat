package GUI.Panel;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class NhanVienPanel extends JPanel implements ItemListener, KeyListener {
    private JButton btnThem, btnXoa, btnSua, btnExcel, btnLamMoi;
    private JComboBox<String> cbbChucVu;     // ComboBox để lọc (Tất cả, Quản lý, Nhân viên,...)
    private JTextField txtSearch;           // Ô tìm kiếm
    private JTable table;
    private DefaultTableModel tableModel;

    // Danh sách nhân viên gốc (dummy)
    private List<NhanVien> listNV;

    public NhanVienPanel() {
        initComponent();
        initData();
        loadDataToTable(listNV); // Ban đầu hiển thị toàn bộ
    }

    private void initComponent() {
        setLayout(new BorderLayout(10, 10));
        setBackground(Color.WHITE);

        // ========== Thanh công cụ (toolbar) ==========
        JPanel toolbar = new JPanel(new BorderLayout());

        // (A) Panel bên trái - nút Thêm, Xóa, Sửa, Xuất Excel
        JPanel leftToolPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        btnThem = new JButton("THÊM");
        btnXoa = new JButton("XÓA");
        btnSua = new JButton("SỬA");
        btnExcel = new JButton("XUẤT EXCEL");
        leftToolPanel.add(btnThem);
        leftToolPanel.add(btnXoa);
        leftToolPanel.add(btnSua);
        leftToolPanel.add(btnExcel);

        // (B) Panel bên phải - combo (chức vụ), ô tìm kiếm, nút làm mới
        JPanel rightToolPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 5));
        cbbChucVu = new JComboBox<>(new String[]{"Tất cả", "Quản lý", "Nhân viên"});
        cbbChucVu.addItemListener(this);

        txtSearch = new JTextField("Nhập nội dung tìm kiếm...", 15);
        txtSearch.addKeyListener(this);

        btnLamMoi = new JButton("LÀM MỚI");

        rightToolPanel.add(cbbChucVu);
        rightToolPanel.add(txtSearch);
        rightToolPanel.add(btnLamMoi);

        toolbar.add(leftToolPanel, BorderLayout.WEST);
        toolbar.add(rightToolPanel, BorderLayout.EAST);

        add(toolbar, BorderLayout.NORTH);

        // ========== Bảng hiển thị nhân viên ==========
        // Cột ví dụ: Mã NV, Tên NV, Chức vụ, SĐT, Email, Tình trạng
        String[] columns = {"Mã NV", "Tên NV", "Chức vụ", "SĐT", "Email", "Tình trạng"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);

        JScrollPane scroll = new JScrollPane(table);
        add(scroll, BorderLayout.CENTER);

        // ========== Sự kiện nút (demo) ==========
        btnThem.addActionListener(e -> {
            // Mở dialog ThemNhanVienDialog (hoặc showMessageDialog)
            JOptionPane.showMessageDialog(this, "Thêm nhân viên (demo)!");
            // ThemNhanVienDialog dlg = new ThemNhanVienDialog(SwingUtilities.getWindowAncestor(this));
            // dlg.setVisible(true);
        });

        btnXoa.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                tableModel.removeRow(row);
            }
        });

        btnSua.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row < 0) {
                JOptionPane.showMessageDialog(this, "Chưa chọn nhân viên để sửa!");
                return;
            }
            JOptionPane.showMessageDialog(this, "Sửa nhân viên (demo)!");
            // Hoặc mở ThemNhanVienDialog, nạp data cũ
        });

        btnExcel.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Xuất Excel (demo)!");
        });

        btnLamMoi.addActionListener(e -> {
            // Xoá bảng rồi load lại danh sách gốc
            loadDataToTable(listNV);
        });
    }

    /**
     * Tạo dữ liệu mẫu cho nhân viên
     */
    private void initData() {
        listNV = new ArrayList<>();
        // (maNV, tenNV, chucVu, sdt, email, tinhTrang)
        listNV.add(new NhanVien("NV001", "Nguyễn Văn A", "Quản lý", "0909123456", "nva@quat.vn", "Hoạt động"));
        listNV.add(new NhanVien("NV002", "Trần Thị B", "Nhân viên", "0818111222", "ttb@quat.vn", "Hoạt động"));
        listNV.add(new NhanVien("NV003", "Lê Hoàng C", "Nhân viên", "0799887766", "lhc@quat.vn", "Nghỉ việc"));
        // Thêm tùy ý
    }

    /**
     * Đổ danh sách nhân viên vào bảng
     */
    private void loadDataToTable(List<NhanVien> data) {
        tableModel.setRowCount(0);
        for (NhanVien nv : data) {
            tableModel.addRow(new Object[]{
                nv.getMaNV(),
                nv.getTenNV(),
                nv.getChucVu(),
                nv.getSdt(),
                nv.getEmail(),
                nv.getTinhTrang()
            });
        }
    }

    /**
     * Lọc theo combo (chức vụ) + ô tìm kiếm
     */
    private void filterData() {
        String selectedChucVu = (String) cbbChucVu.getSelectedItem(); // "Tất cả", "Quản lý", "Nhân viên"
        String searchText = txtSearch.getText().trim().toLowerCase();

        List<NhanVien> result = new ArrayList<>();
        for (NhanVien nv : listNV) {
            // 1. Kiểm tra chức vụ
            boolean matchCV = true;
            if (!selectedChucVu.equals("Tất cả")) {
                matchCV = nv.getChucVu().equalsIgnoreCase(selectedChucVu);
            }
            // 2. Kiểm tra search text (Mã NV, Tên NV, SĐT, Email)
            boolean matchSearch = searchText.isEmpty()
                    || nv.getMaNV().toLowerCase().contains(searchText)
                    || nv.getTenNV().toLowerCase().contains(searchText)
                    || nv.getSdt().toLowerCase().contains(searchText)
                    || nv.getEmail().toLowerCase().contains(searchText);

            if (matchCV && matchSearch) {
                result.add(nv);
            }
        }
        loadDataToTable(result);
    }

    // =========== ItemListener cho ComboBox (chức vụ) ===========
    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            filterData();
        }
    }

    // =========== KeyListener cho ô tìm kiếm ===========
    @Override
    public void keyReleased(KeyEvent e) {
        filterData();
    }
    @Override
    public void keyTyped(KeyEvent e) { }
    @Override
    public void keyPressed(KeyEvent e) { }

    // =========== Lớp NhanVien (dummy) ===========
    class NhanVien {
        private String maNV;
        private String tenNV;
        private String chucVu;     // Quản lý / Nhân viên / ...
        private String sdt;
        private String email;
        private String tinhTrang;  // "Hoạt động", "Nghỉ việc", ...

        public NhanVien(String maNV, String tenNV, String chucVu,
                        String sdt, String email, String tinhTrang) {
            this.maNV = maNV;
            this.tenNV = tenNV;
            this.chucVu = chucVu;
            this.sdt = sdt;
            this.email = email;
            this.tinhTrang = tinhTrang;
        }

        public String getMaNV() { return maNV; }
        public String getTenNV() { return tenNV; }
        public String getChucVu() { return chucVu; }
        public String getSdt() { return sdt; }
        public String getEmail() { return email; }
        public String getTinhTrang() { return tinhTrang; }
    }
}
