package GUI.Panel;

import GUI.Dialog.ThemHoaDonDialog;
import GUI.Dialog.ChiTietHoaDonDialog;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Panel quản lý Hóa đơn: hiển thị danh sách, thêm, xóa, xem chi tiết, v.v.
 * Có combo filter, ô tìm kiếm, nút làm mới tương tự các panel khác.
 */
public class HoaDonPanel extends JPanel implements ItemListener, KeyListener {
    private JButton btnThem, btnXoa, btnChiTiet, btnExcel, btnLamMoi;
    private JComboBox<String> cbbFilter;  // ComboBox lọc theo Mã HD / Mã KH / Mã NV / Tất cả
    private JTextField txtSearch;         // Ô tìm kiếm
    private JTable table;
    private DefaultTableModel tableModel;

    // Danh sách hóa đơn gốc (dummy)
    private List<HoaDon> listHD;

    public HoaDonPanel() {
        initComponent();
        initData();
        loadDataToTable(listHD); // hiển thị tất cả ban đầu
    }

    private void initComponent() {
        setLayout(new BorderLayout(10, 10));
        setBackground(Color.WHITE);

        // ================== Thanh công cụ (toolbar) ==================
        JPanel toolbar = new JPanel(new BorderLayout());

        // (A) Panel nút bên trái
        JPanel leftToolPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        btnThem = new JButton("THÊM");
        btnXoa = new JButton("XÓA");
        btnChiTiet = new JButton("CHI TIẾT");
        btnExcel = new JButton("XUẤT EXCEL");
        leftToolPanel.add(btnThem);
        leftToolPanel.add(btnXoa);
        leftToolPanel.add(btnChiTiet);
        leftToolPanel.add(btnExcel);

        // (B) Panel filter + search + refresh bên phải
        JPanel rightToolPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 5));
        cbbFilter = new JComboBox<>(new String[]{"Tất cả", "Mã HĐ", "Mã NV", "Mã KH"});
        cbbFilter.addItemListener(this);

        txtSearch = new JTextField("Nhập nội dung tìm kiếm...", 15);
        txtSearch.addKeyListener(this);

        btnLamMoi = new JButton("LÀM MỚI");

        rightToolPanel.add(cbbFilter);
        rightToolPanel.add(txtSearch);
        rightToolPanel.add(btnLamMoi);

        toolbar.add(leftToolPanel, BorderLayout.WEST);
        toolbar.add(rightToolPanel, BorderLayout.EAST);

        add(toolbar, BorderLayout.NORTH);

        // ================== Bảng hiển thị hóa đơn ==================
        String[] columns = {"Mã HĐ", "Mã NV", "Mã KH", "Ngày lập", "Mã KM", "Tổng tiền"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // ================== Sự kiện nút ==================
        btnThem.addActionListener(e -> {
            // Mở dialog ThemHoaDonDialog
            ThemHoaDonDialog dlg = new ThemHoaDonDialog(SwingUtilities.getWindowAncestor(this));
            dlg.setVisible(true);
            // Sau khi đóng, có thể lấy dữ liệu mới từ dlg nếu muốn thêm vào bảng
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
                JOptionPane.showMessageDialog(this, "Chưa chọn hóa đơn!");
                return;
            }
            // Lấy mã HD từ bảng
            String maHD = (String) tableModel.getValueAt(row, 0);
            // Mở dialog Chi tiết hóa đơn
            ChiTietHoaDonDialog dlg = new ChiTietHoaDonDialog(SwingUtilities.getWindowAncestor(this), maHD);
            dlg.setVisible(true);
        });

        btnExcel.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Xuất Excel (demo)!");
        });

        btnLamMoi.addActionListener(e -> {
            // Xóa bảng rồi load lại data gốc
            loadDataToTable(listHD);
        });
    }

    /**
     * Tạo dữ liệu mẫu cho hóa đơn
     */
    private void initData() {
        listHD = new ArrayList<>();
        // (maHD, maNV, maKH, ngayLap, maKM, tongTien)
        listHD.add(new HoaDon("HD001", "NV01", "KH01", "2025-09-01", "KM001", 1500000));
        listHD.add(new HoaDon("HD002", "NV02", "KH02", "2025-09-02", null, 500000));
        listHD.add(new HoaDon("HD003", "NV01", "KH03", "2025-09-03", "KM002", 2000000));
    }

    /**
     * Đổ danh sách hóa đơn vào bảng
     */
    private void loadDataToTable(List<HoaDon> data) {
        tableModel.setRowCount(0);
        for (HoaDon hd : data) {
            tableModel.addRow(new Object[]{
                hd.getMaHD(),
                hd.getMaNV(),
                hd.getMaKH(),
                hd.getNgayLap(),
                hd.getMaKM() == null ? "" : hd.getMaKM(),
                hd.getTongTien()
            });
        }
    }

    /**
     * Lọc dữ liệu theo combo (Mã HĐ, Mã NV, Mã KH, Tất cả) + từ khóa tìm kiếm
     */
    private void filterData() {
        String selected = (String) cbbFilter.getSelectedItem();  // "Tất cả", "Mã HĐ", "Mã NV", "Mã KH"
        String searchText = txtSearch.getText().trim().toLowerCase();

        List<HoaDon> result = new ArrayList<>();
        for (HoaDon hd : listHD) {
            boolean matchSearch = false;
            // Nếu searchText rỗng => matchSearch = true (không lọc)
            if (searchText.isEmpty()) {
                matchSearch = true;
            } else {
                // Tùy theo combo, ta so sánh
                switch (selected) {
                    case "Mã HĐ":
                        matchSearch = hd.getMaHD().toLowerCase().contains(searchText);
                        break;
                    case "Mã NV":
                        matchSearch = hd.getMaNV().toLowerCase().contains(searchText);
                        break;
                    case "Mã KH":
                        matchSearch = hd.getMaKH().toLowerCase().contains(searchText);
                        break;
                    case "Tất cả":
                    default:
                        // Tìm trong cả MãHD, MãNV, MãKH, Ngày, MãKM
                        if (hd.getMaHD().toLowerCase().contains(searchText)
                            || hd.getMaNV().toLowerCase().contains(searchText)
                            || hd.getMaKH().toLowerCase().contains(searchText)
                            || (hd.getMaKM() != null && hd.getMaKM().toLowerCase().contains(searchText))
                            || hd.getNgayLap().toLowerCase().contains(searchText)) {
                            matchSearch = true;
                        }
                        break;
                }
            }

            if (matchSearch) {
                result.add(hd);
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

    // ========== Lớp HoaDon (dummy) ==========
    class HoaDon {
        private String maHD;
        private String maNV;
        private String maKH;
        private String ngayLap;
        private String maKM;  // có thể null
        private int tongTien;

        public HoaDon(String maHD, String maNV, String maKH, 
                      String ngayLap, String maKM, int tongTien) {
            this.maHD = maHD;
            this.maNV = maNV;
            this.maKH = maKH;
            this.ngayLap = ngayLap;
            this.maKM = maKM;
            this.tongTien = tongTien;
        }

        public String getMaHD() { return maHD; }
        public String getMaNV() { return maNV; }
        public String getMaKH() { return maKH; }
        public String getNgayLap() { return ngayLap; }
        public String getMaKM() { return maKM; }
        public int getTongTien() { return tongTien; }
    }
}
