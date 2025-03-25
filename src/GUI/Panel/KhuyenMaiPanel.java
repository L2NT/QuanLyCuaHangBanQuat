package GUI.Panel;

import GUI.Dialog.ThemKhuyenMaiDialog;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Panel quản lý Sự kiện khuyến mãi (GUI only)
 */
public class KhuyenMaiPanel extends JPanel {
    private JButton btnThem, btnXoa, btnSua, btnExcel, btnLamMoi;
    private JTable table;
    private DefaultTableModel tableModel;

    // Danh sách khuyến mãi gốc (dummy)
    private List<KhuyenMai> listKM;

    public KhuyenMaiPanel() {
        initComponent();
        initData();
        loadDataToTable(listKM);
    }

    private void initComponent() {
        setLayout(new BorderLayout(10, 10));
        setBackground(Color.WHITE);

        // ========== Thanh công cụ trên ==========
        JPanel toolbar = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        btnThem = new JButton("Thêm");
        btnXoa = new JButton("Xóa");
        btnSua = new JButton("Sửa");
        btnExcel = new JButton("Xuất Excel");
        btnLamMoi = new JButton("Làm mới");

        toolbar.add(btnThem);
        toolbar.add(btnXoa);
        toolbar.add(btnSua);
        toolbar.add(btnExcel);
        toolbar.add(btnLamMoi);

        add(toolbar, BorderLayout.NORTH);

        // ========== Bảng hiển thị khuyến mãi ==========
        // Cột: Mã KM, Tên KM, Phần trăm giảm, Ngày BD, Ngày KT, Điều kiện
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

        // ========== Sự kiện các nút (demo) ==========
        btnThem.addActionListener(e -> {
            // Mở dialog thêm khuyến mãi
            ThemKhuyenMaiDialog dlg = new ThemKhuyenMaiDialog(SwingUtilities.getWindowAncestor(this));
            dlg.setVisible(true);
            // Sau khi đóng dialog, có thể lấy data từ dlg để thêm vào bảng (nếu muốn).
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
                JOptionPane.showMessageDialog(this, "Chưa chọn khuyến mãi cần sửa!");
                return;
            }
            // Lấy dữ liệu cũ của row (demo)
            String maKM = (String) tableModel.getValueAt(row, 0);
            String tenKM = (String) tableModel.getValueAt(row, 1);
            int phanTram = Integer.parseInt(tableModel.getValueAt(row, 2).toString());
            String ngayBD = (String) tableModel.getValueAt(row, 3);
            String ngayKT = (String) tableModel.getValueAt(row, 4);
            String dieuKien = (String) tableModel.getValueAt(row, 5);

            // Mở dialog ThemKhuyenMaiDialog với dữ liệu cũ
            ThemKhuyenMaiDialog dlg = new ThemKhuyenMaiDialog(
                    SwingUtilities.getWindowAncestor(this),
                    maKM, tenKM, phanTram, ngayBD, ngayKT, dieuKien
            );
            dlg.setVisible(true);

            // Sau khi dialog đóng, bạn có thể cập nhật row (nếu người dùng Lưu)
            // Ở đây demo, chưa xử lý.
        });

        btnExcel.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Xuất Excel (demo)!");
        });

        btnLamMoi.addActionListener(e -> {
            // Xóa bảng rồi load lại data gốc
            loadDataToTable(listKM);
        });
    }

    /**
     * Tạo dữ liệu mẫu cho khuyến mãi
     */
    private void initData() {
        listKM = new ArrayList<>();
        // Giả sử "maKM", "tenKM", "phanTram", "ngayBD", "ngayKT", "dieuKien"
        listKM.add(new KhuyenMai("KM001", "Giảm giá mùa hè", 10, "2025-06-01", "2025-06-15", "Đơn hàng > 500k"));
        listKM.add(new KhuyenMai("KM002", "Giảm giá tri ân", 20, "2025-07-01", "2025-07-10", "Áp dụng mọi đơn hàng"));
    }

    /**
     * Đổ dữ liệu vào bảng
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

    // ========== Lớp KhuyenMai (dummy) ==========
    class KhuyenMai {
        private String maKM;
        private String tenKM;
        private int phanTramGiam;
        private String ngayBatDau;
        private String ngayKetThuc;
        private String dieuKienApDung;

        public KhuyenMai(String maKM, String tenKM, int phanTramGiam, 
                         String ngayBatDau, String ngayKetThuc, String dieuKien) {
            this.maKM = maKM;
            this.tenKM = tenKM;
            this.phanTramGiam = phanTramGiam;
            this.ngayBatDau = ngayBatDau;
            this.ngayKetThuc = ngayKetThuc;
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
