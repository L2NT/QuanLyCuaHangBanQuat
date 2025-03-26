package GUI.Panel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Panel quản lý Khách hàng (GUI Only).
 * Có bảng, thanh công cụ, lọc theo loại khách, tìm kiếm.
 */
public class KhachHangPanel extends JPanel implements ItemListener, KeyListener {
    private JButton btnThem, btnXoa, btnSua, btnExcel, btnLamMoi;
    private JComboBox<String> cbbLoaiKH;    // ComboBox để lọc theo loại khách (Tất cả, Thành viên, Vãng lai,...)
    private JTextField txtSearch;           // Ô tìm kiếm
    private JTable table;
    private DefaultTableModel tableModel;

    // Danh sách khách hàng gốc (dummy)
    private List<KhachHang> listKH;

    public KhachHangPanel() {
        initComponent();
        initData();
        loadDataToTable(listKH); // ban đầu hiển thị tất cả
    }

    private void initComponent() {
        setLayout(new BorderLayout(10, 10));
        setBackground(Color.WHITE);

        // ========== Thanh công cụ (toolbar) ==========
        JPanel toolbar = new JPanel(new BorderLayout());

        // (A) Panel bên trái - chứa nút Thêm, Xóa, Sửa, Xuất Excel
        JPanel leftToolPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        btnThem = new JButton("THÊM");
        btnXoa = new JButton("XÓA");
        btnSua = new JButton("SỬA");
        btnExcel = new JButton("XUẤT EXCEL");
        leftToolPanel.add(btnThem);
        leftToolPanel.add(btnXoa);
        leftToolPanel.add(btnSua);
        leftToolPanel.add(btnExcel);

        // (B) Panel bên phải - combo (loại KH), ô tìm kiếm, nút làm mới
        JPanel rightToolPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 5));
        cbbLoaiKH = new JComboBox<>(new String[]{"Tất cả", "Thành viên", "Vãng lai"});
        cbbLoaiKH.addItemListener(this);

        txtSearch = new JTextField("Nhập nội dung tìm kiếm...", 15);
        txtSearch.addKeyListener(this);

        btnLamMoi = new JButton("LÀM MỚI");

        rightToolPanel.add(cbbLoaiKH);
        rightToolPanel.add(txtSearch);
        rightToolPanel.add(btnLamMoi);

        toolbar.add(leftToolPanel, BorderLayout.WEST);
        toolbar.add(rightToolPanel, BorderLayout.EAST);

        add(toolbar, BorderLayout.NORTH);

        // ========== Bảng hiển thị khách hàng ==========
        // Cột: Mã KH, Tên KH, SĐT, Email, Loại KH, Điểm tích lũy
        String[] columns = {"Mã KH", "Tên KH", "SĐT", "Email", "Loại KH", "Điểm tích lũy"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);

        JScrollPane scroll = new JScrollPane(table);
        add(scroll, BorderLayout.CENTER);

        // ========== Sự kiện nút (demo) ==========
        btnThem.addActionListener(e -> {
            // Mở dialog ThemKhachHangDialog (hoặc showMessageDialog)
            JOptionPane.showMessageDialog(this, "Thêm khách hàng (demo)!");
            // ThemKhachHangDialog dlg = new ThemKhachHangDialog(SwingUtilities.getWindowAncestor(this));
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
                JOptionPane.showMessageDialog(this, "Chưa chọn khách hàng để sửa!");
                return;
            }
            JOptionPane.showMessageDialog(this, "Sửa khách hàng (demo)!");
            // Hoặc mở ThemKhachHangDialog, nạp data cũ
        });

        btnExcel.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Xuất Excel (demo)!");
        });

        btnLamMoi.addActionListener(e -> {
            // Xóa bảng rồi load lại danh sách gốc
            loadDataToTable(listKH);
        });
    }

    /**
     * Tạo dữ liệu mẫu cho khách hàng
     */
    private void initData() {
        listKH = new ArrayList<>();
        // (maKH, tenKH, sdt, email, loaiKH, diemTichLuy)
        listKH.add(new KhachHang("KH001", "Lê Nguyễn Nhất Tâm", "3122410369", "nva@gmail.com", "Thành viên", 100));
        listKH.add(new KhachHang("KH002", "Lưu Hồng Phúc", "3123456789", "ttb@gmail.com", "Vãng lai", 0));
        
    }

    /**
     * Đổ danh sách khách hàng lên bảng
     */
    private void loadDataToTable(List<KhachHang> data) {
        tableModel.setRowCount(0);
        for (KhachHang kh : data) {
            tableModel.addRow(new Object[]{
                kh.getMaKH(),
                kh.getTenKH(),
                kh.getSdt(),
                kh.getEmail(),
                kh.getLoaiKH(),
                kh.getDiemTichLuy()
            });
        }
    }

    /**
     * Lọc theo combo (loại KH) + ô tìm kiếm
     */
    private void filterData() {
        String selectedLoai = (String) cbbLoaiKH.getSelectedItem(); // "Tất cả", "Thành viên", "Vãng lai"
        String searchText = txtSearch.getText().trim().toLowerCase();

        List<KhachHang> result = new ArrayList<>();
        for (KhachHang kh : listKH) {
            // 1. Kiểm tra loại KH
            boolean matchLoai = true;
            if (!selectedLoai.equals("Tất cả")) {
                matchLoai = kh.getLoaiKH().equalsIgnoreCase(selectedLoai);
            }
            // 2. Kiểm tra search text (trong Mã KH, Tên KH, SĐT, Email)
            boolean matchSearch = searchText.isEmpty()
                    || kh.getMaKH().toLowerCase().contains(searchText)
                    || kh.getTenKH().toLowerCase().contains(searchText)
                    || kh.getSdt().toLowerCase().contains(searchText)
                    || kh.getEmail().toLowerCase().contains(searchText);

            if (matchLoai && matchSearch) {
                result.add(kh);
            }
        }
        // Cập nhật bảng
        loadDataToTable(result);
    }

    // =========== ItemListener cho ComboBox (loại KH) ===========
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

    // =========== Lớp KhachHang (dummy) ===========
    class KhachHang {
        private String maKH;
        private String tenKH;
        private String sdt;
        private String email;
        private String loaiKH;       // "Thành viên" / "Vãng lai" / ...
        private int diemTichLuy;

        public KhachHang(String maKH, String tenKH, String sdt, String email, String loaiKH, int diem) {
            this.maKH = maKH;
            this.tenKH = tenKH;
            this.sdt = sdt;
            this.email = email;
            this.loaiKH = loaiKH;
            this.diemTichLuy = diem;
        }

        public String getMaKH() { return maKH; }
        public String getTenKH() { return tenKH; }
        public String getSdt() { return sdt; }
        public String getEmail() { return email; }
        public String getLoaiKH() { return loaiKH; }
        public int getDiemTichLuy() { return diemTichLuy; }
    }
}
