package GUI.Panel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Panel quản lý Tài khoản (GUI Only).
 * Có bảng, thanh công cụ, lọc theo loại tài khoản, tìm kiếm.
 */
public class TaiKhoanPanel extends JPanel implements ItemListener, KeyListener {
    private JButton btnThem, btnXoa, btnSua, btnExcel, btnLamMoi;
    private JComboBox<String> cbbLoaiTK;     // ComboBox lọc (Tất cả, Admin, User, ...)
    private JTextField txtSearch;           // Ô tìm kiếm
    private JTable table;
    private DefaultTableModel tableModel;

    // Danh sách tài khoản gốc (dummy)
    private List<TaiKhoan> listTK;

    public TaiKhoanPanel() {
        initComponent();
        initData();
        loadDataToTable(listTK); // hiển thị toàn bộ ban đầu
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

        // (B) Panel bên phải - combo (loại TK), ô tìm kiếm, nút làm mới
        JPanel rightToolPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 5));
        cbbLoaiTK = new JComboBox<>(new String[]{"Tất cả", "Admin", "User"});
        cbbLoaiTK.addItemListener(this);

        txtSearch = new JTextField("Nhập nội dung tìm kiếm...", 15);
        txtSearch.addKeyListener(this);

        btnLamMoi = new JButton("LÀM MỚI");

        rightToolPanel.add(cbbLoaiTK);
        rightToolPanel.add(txtSearch);
        rightToolPanel.add(btnLamMoi);

        toolbar.add(leftToolPanel, BorderLayout.WEST);
        toolbar.add(rightToolPanel, BorderLayout.EAST);

        add(toolbar, BorderLayout.NORTH);

        // ========== Bảng hiển thị tài khoản ==========
        // Ví dụ cột: Mã TK, Tên đăng nhập, Loại TK, Email, Trạng thái
        String[] columns = {"Mã TK", "Tên đăng nhập", "Loại TK", "Email", "Trạng thái"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);

        JScrollPane scroll = new JScrollPane(table);
        add(scroll, BorderLayout.CENTER);

        // ========== Sự kiện nút (demo) ==========
        btnThem.addActionListener(e -> {
            // Mở dialog ThemTaiKhoanDialog (hoặc hiển thị message)
            JOptionPane.showMessageDialog(this, "Thêm tài khoản (demo)!");
            // ThemTaiKhoanDialog dlg = new ThemTaiKhoanDialog(SwingUtilities.getWindowAncestor(this));
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
                JOptionPane.showMessageDialog(this, "Chưa chọn tài khoản để sửa!");
                return;
            }
            JOptionPane.showMessageDialog(this, "Sửa tài khoản (demo)!");
            // Hoặc mở ThemTaiKhoanDialog, nạp data cũ
        });

        btnExcel.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Xuất Excel (demo)!");
        });

        btnLamMoi.addActionListener(e -> {
            // Xoá bảng rồi load lại danh sách gốc
            loadDataToTable(listTK);
        });
    }

    /**
     * Tạo dữ liệu mẫu cho tài khoản
     */
    private void initData() {
        listTK = new ArrayList<>();
        // (maTK, tenDangNhap, loaiTK, email, trangThai)
        listTK.add(new TaiKhoan("TK001", "admin", "Admin", "admin@quat.vn", "Hoạt động"));
        listTK.add(new TaiKhoan("TK002", "user1", "User", "user1@quat.vn", "Hoạt động"));
        listTK.add(new TaiKhoan("TK003", "user2", "User", "user2@quat.vn", "Khóa"));
        // Thêm tùy ý
    }

    /**
     * Đổ danh sách tài khoản lên bảng
     */
    private void loadDataToTable(List<TaiKhoan> data) {
        tableModel.setRowCount(0);
        for (TaiKhoan tk : data) {
            tableModel.addRow(new Object[]{
                tk.getMaTK(),
                tk.getTenDangNhap(),
                tk.getLoaiTK(),
                tk.getEmail(),
                tk.getTrangThai()
            });
        }
    }

    /**
     * Lọc theo combo (loại TK) + ô tìm kiếm
     */
    private void filterData() {
        String selectedLoai = (String) cbbLoaiTK.getSelectedItem(); // "Tất cả", "Admin", "User"
        String searchText = txtSearch.getText().trim().toLowerCase();

        List<TaiKhoan> result = new ArrayList<>();
        for (TaiKhoan tk : listTK) {
            // 1. Kiểm tra loại TK
            boolean matchLoai = true;
            if (!selectedLoai.equals("Tất cả")) {
                matchLoai = tk.getLoaiTK().equalsIgnoreCase(selectedLoai);
            }
            // 2. Kiểm tra search text (Mã TK, Tên đăng nhập, Email)
            boolean matchSearch = searchText.isEmpty()
                    || tk.getMaTK().toLowerCase().contains(searchText)
                    || tk.getTenDangNhap().toLowerCase().contains(searchText)
                    || tk.getEmail().toLowerCase().contains(searchText);

            if (matchLoai && matchSearch) {
                result.add(tk);
            }
        }
        loadDataToTable(result);
    }

    // =========== ItemListener cho ComboBox (loại TK) ===========
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

    // =========== Lớp TaiKhoan (dummy) ===========
    class TaiKhoan {
        private String maTK;
        private String tenDangNhap;
        private String loaiTK;     // Admin / User / ...
        private String email;
        private String trangThai;  // "Hoạt động" / "Khóa" / ...

        public TaiKhoan(String maTK, String tenDN, String loai, String email, String tt) {
            this.maTK = maTK;
            this.tenDangNhap = tenDN;
            this.loaiTK = loai;
            this.email = email;
            this.trangThai = tt;
        }

        public String getMaTK() { return maTK; }
        public String getTenDangNhap() { return tenDangNhap; }
        public String getLoaiTK() { return loaiTK; }
        public String getEmail() { return email; }
        public String getTrangThai() { return trangThai; }
    }
}
