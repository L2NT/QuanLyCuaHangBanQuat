package GUI.Panel;

import DAO.KhachHangDAO;
import DTO.KhachHangDTO;
import GUI.Dialog.ThemKhachHangDialog;
import GUI.Dialog.SuaKhachHangDialog;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class KhachHangPanel extends JPanel implements ItemListener, KeyListener {

    private JButton btnThem, btnXoa, btnSua, btnExcel, btnLamMoi;
    private JComboBox<String> cbbLoaiKH;
    private JTextField txtSearch;
    private JTable table;
    private DefaultTableModel tableModel;
    private KhachHangDAO khachHangDAO = new KhachHangDAO();
    private List<KhachHangDTO> listKH;

    public KhachHangPanel() {
        initComponent();
        loadDataFromDB();
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
        String[] columns = {"Mã KH", "Tên KH", "SĐT", "Địa chỉ", "Tổng tiền đã mua"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);

        JScrollPane scroll = new JScrollPane(table);
        add(scroll, BorderLayout.CENTER);

        btnThem.addActionListener(e -> {
            // Mở dialog thêm khách hàng và sử dụng DTO/DAO
            ThemKhachHangDialog dialog = new ThemKhachHangDialog();
            dialog.setVisible(true);
            if (dialog.isSaved()) {
                listKH = khachHangDAO.selectAll();
                loadDataToTable(listKH);
            }
        });

        btnXoa.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                String maKH = (String) tableModel.getValueAt(row, 0);
                KhachHangDAO dao = new KhachHangDAO();
                if (dao.delete(maKH)) {
                    JOptionPane.showMessageDialog(this, "Xóa thành công!");
                    listKH = khachHangDAO.selectAll();
                    loadDataToTable(listKH);
                }
            }
        });

        btnSua.addActionListener(e -> {
            openSuaKhachHangDialog();
        });

        btnExcel.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Xuất Excel (demo)!");
        });

        btnLamMoi.addActionListener(e -> {
            loadDataFromDB();
        });
    }

    private void initData() {
        KhachHangDAO dao = new KhachHangDAO();
        listKH = dao.selectAll(); // Load danh sách KH từ CSDL
    }

    /**
     * Đổ danh sách khách hàng lên bảng
     */
    private void loadDataFromDB() {
        listKH = KhachHangDAO.selectAll();
        loadDataToTable(listKH);
    }

    private void loadDataToTable(List<KhachHangDTO> data) {
        tableModel.setRowCount(0);
        for (KhachHangDTO kh : data) {
            tableModel.addRow(new Object[]{
                kh.getMaKhachHang(),
                kh.getHoTenKH(),
                kh.getSdtKH(), // Sửa từ getSdt() → getSdtKH()
                kh.getDiaChiKH(), // Thêm địa chỉ
                kh.getTongTienDaMua() // Thêm tổng tiền
            });
        }
    }

    // Mở dialog thêm khách hàng
    private void openThemKhachHangDialog() {
        ThemKhachHangDialog dlg = new ThemKhachHangDialog();
        dlg.setVisible(true);
        if (dlg.isSaved()) {
            loadDataFromDB(); // Load lại dữ liệu sau khi thêm
        }
    }

    // Xóa khách hàng
    private void deleteKhachHang() {
        int row = table.getSelectedRow();
        if (row >= 0) {
            String maKH = (String) tableModel.getValueAt(row, 0);
            if (khachHangDAO.delete(maKH)) {
                JOptionPane.showMessageDialog(this, "Xóa thành công!");
                loadDataFromDB();
            } else {
                JOptionPane.showMessageDialog(this, "Xóa thất bại!");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn khách hàng!");
        }
    }

    // Mở dialog sửa khách hàng
    private void openSuaKhachHangDialog() {
        int row = table.getSelectedRow();
        if (row >= 0) {
            String maKH = (String) tableModel.getValueAt(row, 0);
            KhachHangDTO kh = khachHangDAO.selectById(maKH);
            SuaKhachHangDialog dlg = new SuaKhachHangDialog((JFrame) SwingUtilities.getWindowAncestor(this), kh);
            dlg.setVisible(true);
            if (dlg.isSaved()) {
                loadDataFromDB();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn khách hàng!");
        }
    }

    private void filterData() {
        String selectedLoai = (String) cbbLoaiKH.getSelectedItem();
        String searchText = txtSearch.getText().trim().toLowerCase();

        List<KhachHangDTO> result = new ArrayList<>();
        for (KhachHangDTO kh : listKH) {
            boolean matchSearch
                    = kh.getMaKhachHang().toLowerCase().contains(searchText)
                    || kh.getHoTenKH().toLowerCase().contains(searchText)
                    || kh.getSdtKH().contains(searchText)
                    || // Sửa từ getSdt() → getSdtKH()
                    kh.getDiaChiKH().toLowerCase().contains(searchText);

            if (matchSearch) {
                result.add(kh);
            }
        }
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
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }
}
