package GUI.Panel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import BUS.PhieuNhapBUS;
import DTO.PhieuNhapDTO;
import java.util.List;
import java.util.Map;
import GUI.Dialog.ThemPhieuNhapDialog;


public class PhieuNhapPanel extends JPanel {

    private JButton btnThem, btnChiTiet, btnHuyPhieu, btnXuatExcel, btnLamMoi;
    private JComboBox<String> cbbSearchType;
    private JTextField txtSearch;

    private JTable table;
    private DefaultTableModel tableModel;

    // Bộ lọc bên trái
    private JComboBox<String> cbbNhaCungCap, cbbNhanVien;
    private JTextField txtTuNgay, txtDenNgay, txtSoTien; 
    // (Có thể dùng JDateChooser, v.v. tuỳ bạn)

    public PhieuNhapPanel() {
        initComponent();
      loadData();
    }

    private void initComponent() {
        setLayout(new BorderLayout(10, 10));

        // ==================== Thanh công cụ trên ====================
        // Gồm 2 phần: 
        // (A) Nút lớn bên trái
        // (B) Combobox + ô tìm kiếm + refreszh btn
        JPanel topPanel = new JPanel(new BorderLayout());

        //(A) Panel chứa các nút lớn (FlowLayout trái)
        JPanel leftToolPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        btnThem = new JButton("THÊM");
        btnChiTiet = new JButton("CHI TIẾT");
        btnHuyPhieu = new JButton("HỦY PHIẾU");
        btnXuatExcel = new JButton("XUẤT EXCEL");
        leftToolPanel.add(btnThem);
        leftToolPanel.add(btnChiTiet);
        leftToolPanel.add(btnHuyPhieu);
        leftToolPanel.add(btnXuatExcel);

        //(B) Panel chứa combo + ô tìm kiếm + nút làm mới (FlowLayout phải)
        JPanel rightToolPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 5));
        cbbSearchType = new JComboBox<>(new String[]{"Tất cả", "Mã phiếu nhập", "Nhà cung cấp", "Nhân viên nhập"});
        txtSearch = new JTextField("Nhập nội dung tìm kiếm...", 15);
        btnLamMoi = new JButton("Làm mới");

        rightToolPanel.add(cbbSearchType);
        rightToolPanel.add(txtSearch);
        rightToolPanel.add(btnLamMoi);

        // Ghép 2 panel vào topPanel
        topPanel.add(leftToolPanel, BorderLayout.WEST);
        topPanel.add(rightToolPanel, BorderLayout.EAST);

        // Thêm topPanel lên khu vực north
        add(topPanel, BorderLayout.NORTH);

        // ==================== Panel lọc bên trái ====================
        JPanel filterPanel = new JPanel();
        filterPanel.setLayout(new GridLayout(5, 1, 5, 5)); 
        // Hoặc BoxLayout, tuỳ ý
        filterPanel.setPreferredSize(new Dimension(200, 0)); 
        // Để chiều rộng cố định, chiều cao flexy

        // chọn nhà cung cấp
        JPanel row1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        row1.add(new JLabel("Nhà cung cấp:"));
        cbbNhaCungCap = new JComboBox<>(new String[]{"Tất cả", "NCC01", "NCC02", "NCC03"});
        row1.add(cbbNhaCungCap);
        filterPanel.add(row1);

        // chọn nhân viên
        JPanel row2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        row2.add(new JLabel("Nhân viên:"));
        cbbNhanVien = new JComboBox<>(new String[]{"Tất cả", "NV01", "NV02", "NV03"});
        row2.add(cbbNhanVien);
        filterPanel.add(row2);

        //Từ ngày
        JPanel row3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        row3.add(new JLabel("Từ ngày:"));
        txtTuNgay = new JTextField(10);
        row3.add(txtTuNgay);
        filterPanel.add(row3);

        //Đến ngày
        JPanel row4 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        row4.add(new JLabel("Đến ngày:"));
        txtDenNgay = new JTextField(10);
        row4.add(txtDenNgay);
        filterPanel.add(row4);

        //Số tiền
        JPanel row5 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        row5.add(new JLabel("Số tiền (VNĐ):"));
        txtSoTien = new JTextField(10);
        row5.add(txtSoTien);
        filterPanel.add(row5);

        // Thêm filterPanel vào west
        add(filterPanel, BorderLayout.WEST);

        // ==================== Bảng hiển thị trung tâm ====================
        String[] columns = {"STT", "Mã phiếu", "Tên nhà cung cấp", "Nhân viên nhập", "Thời gian", "Tổng tiền"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);

        // Cho bảng vào JScrollPane
        JScrollPane scroll = new JScrollPane(table);
        add(scroll, BorderLayout.CENTER);

        // ==================== Sự kiện mẫu này nọ ====================
       btnThem.addActionListener(e -> {
    ThemPhieuNhapDialog dialog = new ThemPhieuNhapDialog((Frame) SwingUtilities.getWindowAncestor(this), true);
    dialog.setLocationRelativeTo(this); // Căn giữa với frame cha
    dialog.setVisible(true);
});

        btnChiTiet.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Chi tiết phiếu nhập!");
        });
        btnHuyPhieu.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Hủy phiếu nhập!");
        });
        btnXuatExcel.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Xuất Excel!");
        });
        btnLamMoi.addActionListener(e -> {
            // Xoá bảng rồi thêm lại
            tableModel.setRowCount(0);
        loadData();
        });
    }

private void loadData() {
    // Xóa dữ liệu cũ
    tableModel.setRowCount(0);

    // Lấy danh sách phiếu nhập và map MaNCC -> TenNCC
    List<PhieuNhapDTO> danhSach = new PhieuNhapBUS().getAllPhieuNhap();
    Map<String, String> nhaCungCapMap = PhieuNhapBUS.getTenNhaCungCapMap();

    int stt = 1;
    for (PhieuNhapDTO pn : danhSach) {
        String tenNCC = nhaCungCapMap.getOrDefault(pn.getMaNCC(), "Không rõ");
        tableModel.addRow(new Object[]{
            stt++,
            pn.getMaPhieuNhap(),
            tenNCC,
            pn.getMaNhanVien(), // hoặc map tên nhân viên nếu cần
            pn.getNgayNhap(),
            pn.getTongTien()
        });
    }
}


}
