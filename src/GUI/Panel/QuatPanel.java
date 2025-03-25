package GUI.Panel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class QuatPanel extends JPanel implements ItemListener, KeyListener {
    private JButton btnThem, btnXoa, btnChiTiet, btnExcel, btnLamMoi;
    private JComboBox<String> cbbLoaiQuat;  // ComboBox để lọc theo loại quạt
    private JTextField txtSearch;           // Ô tìm kiếm
    private JTable table;
    private DefaultTableModel tableModel;

    // Danh sách quạt gốc (dummy)
    private List<Quat> listQuat;

    public QuatPanel() {
        initComponent();
        initData();
        loadDataToTable(listQuat); // ban đầu hiển thị tất cả
    }

    private void initComponent() {
        setLayout(new BorderLayout(10, 10));
        setBackground(Color.WHITE);

        // ================== Thanh công cụ (toolbar) ==================
        // Dùng BorderLayout để tách: nút ở trái, combo + search + refresh ở phải
        JPanel toolbar = new JPanel(new BorderLayout());

        // (A) Panel bên trái - chứa nút Thêm, Xóa, Chi tiết, Xuất Excel
        JPanel leftToolPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        btnThem = new JButton("THÊM");
        btnXoa = new JButton("XÓA");
        btnChiTiet = new JButton("CHI TIẾT");
        btnExcel = new JButton("XUẤT EXCEL");
        leftToolPanel.add(btnThem);
        leftToolPanel.add(btnXoa);
        leftToolPanel.add(btnChiTiet);
        leftToolPanel.add(btnExcel);

        // (B) Panel bên phải - chứa combo (loại quạt), ô tìm kiếm, nút làm mới
        JPanel rightToolPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 5));
        // ComboBox loại quạt
        cbbLoaiQuat = new JComboBox<>(new String[]{"Tất cả", "Quạt treo tường", "Quạt đứng", "Quạt bàn"});
        cbbLoaiQuat.addItemListener(this); // lắng nghe sự kiện thay đổi combo
        // Ô tìm kiếm
        txtSearch = new JTextField("Nhập nội dung tìm kiếm...", 15);
        txtSearch.addKeyListener(this);    // lắng nghe gõ phím
        // Nút làm mới
        btnLamMoi = new JButton("LÀM MỚI");

        rightToolPanel.add(cbbLoaiQuat);
        rightToolPanel.add(txtSearch);
        rightToolPanel.add(btnLamMoi);

        // Ghép hai panel vào toolbar
        toolbar.add(leftToolPanel, BorderLayout.WEST);
        toolbar.add(rightToolPanel, BorderLayout.EAST);

        // Thêm toolbar vào top
        add(toolbar, BorderLayout.NORTH);

        // ================== Bảng hiển thị quạt ==================
        String[] columns = {"Mã quạt", "Tên quạt", "Loại quạt", "Thương hiệu", "Xuất xứ", "Số lượng"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);

        JScrollPane scroll = new JScrollPane(table);
        add(scroll, BorderLayout.CENTER);

        // ================== Sự kiện nút (demo) ==================
        btnThem.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Thêm quạt (demo)!");
            // Hoặc mở ThemQuatDialog
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
                JOptionPane.showMessageDialog(this, "Chưa chọn quạt!");
                return;
            }
            JOptionPane.showMessageDialog(this, "Chi tiết quạt (demo)!");
            // Hoặc mở ChiTietQuatDialog
        });
        btnExcel.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Xuất Excel (demo)!");
        });
        btnLamMoi.addActionListener(e -> {
            // Xóa bảng rồi load lại danh sách gốc
            loadDataToTable(listQuat);
        });
    }

    /**
     * Tạo dữ liệu mẫu cho quạt
     */
    private void initData() {
        listQuat = new ArrayList<>();
        listQuat.add(new Quat("Q001", "Quạt treo tường X", "Quạt treo tường", "Panasonic", "Nhật Bản", 10));
        listQuat.add(new Quat("Q002", "Quạt đứng Y", "Quạt đứng", "Midea", "Trung Quốc", 5));
        listQuat.add(new Quat("Q003", "Quạt bàn Z", "Quạt bàn", "Asia", "Việt Nam", 8));
        listQuat.add(new Quat("Q004", "Quạt đứng A", "Quạt đứng", "Panasonic", "Nhật Bản", 12));
        // Thêm tùy ý
    }

    /**
     * Đổ danh sách quạt lên bảng
     */
    private void loadDataToTable(List<Quat> data) {
        tableModel.setRowCount(0);
        for (Quat q : data) {
            tableModel.addRow(new Object[]{
                q.getMaQuat(), q.getTenQuat(), q.getLoaiQuat(),
                q.getThuongHieu(), q.getXuatXu(), q.getSoLuong()
            });
        }
    }

    /**
     * Lọc theo combo (loại quạt) + ô tìm kiếm
     */
    private void filterData() {
        String selectedLoai = (String) cbbLoaiQuat.getSelectedItem(); // "Tất cả", "Quạt treo tường", ...
        String searchText = txtSearch.getText().trim().toLowerCase();

        List<Quat> result = new ArrayList<>();
        for (Quat q : listQuat) {
            // 1. Kiểm tra loại quạt
            boolean matchLoai = true;
            if (!selectedLoai.equals("Tất cả")) {
                matchLoai = q.getLoaiQuat().equalsIgnoreCase(selectedLoai);
            }
            // 2. Kiểm tra search text (tìm trong mã quạt, tên quạt, thương hiệu, xuất xứ)
            boolean matchSearch = searchText.isEmpty()
                    || q.getMaQuat().toLowerCase().contains(searchText)
                    || q.getTenQuat().toLowerCase().contains(searchText)
                    || q.getThuongHieu().toLowerCase().contains(searchText)
                    || q.getXuatXu().toLowerCase().contains(searchText);

            if (matchLoai && matchSearch) {
                result.add(q);
            }
        }
        // Cập nhật bảng
        loadDataToTable(result);
    }

    // =========== ItemListener cho ComboBox (loại quạt) ===========
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

    // =========== Lớp Quat (dummy) ===========
    class Quat {
        private String maQuat;
        private String tenQuat;
        private String loaiQuat;
        private String thuongHieu;
        private String xuatXu;
        private int soLuong;

        public Quat(String ma, String ten, String loai, String thuongHieu, String xuatXu, int soLuong) {
            this.maQuat = ma;
            this.tenQuat = ten;
            this.loaiQuat = loai;
            this.thuongHieu = thuongHieu;
            this.xuatXu = xuatXu;
            this.soLuong = soLuong;
        }

        public String getMaQuat() { return maQuat; }
        public String getTenQuat() { return tenQuat; }
        public String getLoaiQuat() { return loaiQuat; }
        public String getThuongHieu() { return thuongHieu; }
        public String getXuatXu() { return xuatXu; }
        public int getSoLuong() { return soLuong; }
    }
}
