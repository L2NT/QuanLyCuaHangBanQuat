package GUI.Panel;

import DAO.KhuyenMaiDAO;
import DTO.KhuyenMaiDTO;
import GUI.Dialog.ThemKhuyenMaiDialog;
import GUI.Dialog.SuaKhuyenMaiDialog;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class KhuyenMaiPanel extends JPanel implements ItemListener, KeyListener {
    private JButton btnThem, btnXoa, btnSua, btnExcel, btnLamMoi;
    private JComboBox<String> cbbPhanTram;
    private JTextField txtSearch;
    private JTable table;
    private DefaultTableModel tableModel;
    private List<KhuyenMaiDTO> listKM;

    public KhuyenMaiPanel() {
        initComponent();
        loadDataFromDatabase();
    }

    private void initComponent() {
        setLayout(new BorderLayout(10, 10));
        setBackground(Color.WHITE);

        // ======= Toolbar ========
        JPanel toolbar = new JPanel(new BorderLayout());
        
        // Left tools
        JPanel leftToolPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        btnThem = new JButton("THÊM");
        btnXoa = new JButton("XÓA");
        btnSua = new JButton("SỬA");
        btnExcel = new JButton("XUẤT EXCEL");
        leftToolPanel.add(btnThem);
        leftToolPanel.add(btnXoa);
        leftToolPanel.add(btnSua);
        leftToolPanel.add(btnExcel);

        // Right tools
        JPanel rightToolPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 5));
        cbbPhanTram = new JComboBox<>(new String[]{"Tất cả", "Dưới 10%", "Từ 10% - 20%", "Trên 20%"});
        cbbPhanTram.addItemListener(this);
        
        txtSearch = new JTextField(15);
        setupSearchPlaceholder();
        txtSearch.addKeyListener(this);
        
        btnLamMoi = new JButton("LÀM MỚI");
        
        rightToolPanel.add(cbbPhanTram);
        rightToolPanel.add(txtSearch);
        rightToolPanel.add(btnLamMoi);

        toolbar.add(leftToolPanel, BorderLayout.WEST);
        toolbar.add(rightToolPanel, BorderLayout.EAST);
        add(toolbar, BorderLayout.NORTH);

        // ======= Table ========
        String[] columns = {"Mã KM", "Tên khuyến mãi", "Phần trăm giảm", "Ngày bắt đầu", "Ngày kết thúc", "Điều kiện"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // ======= Events ========
        btnThem.addActionListener(e -> themKhuyenMai());
        btnXoa.addActionListener(e -> xoaKhuyenMai());
        btnSua.addActionListener(e -> suaKhuyenMai());
        btnLamMoi.addActionListener(e -> refreshData());
    }

    private void setupSearchPlaceholder() {
        txtSearch.setText("Nhập nội dung tìm kiếm...");
        txtSearch.setForeground(Color.GRAY);
        
        txtSearch.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txtSearch.getText().equals("Nhập nội dung tìm kiếm...")) {
                    txtSearch.setText("");
                    txtSearch.setForeground(Color.BLACK);
                }
            }
            
            @Override
            public void focusLost(FocusEvent e) {
                if (txtSearch.getText().isEmpty()) {
                    txtSearch.setForeground(Color.GRAY);
                    txtSearch.setText("Nhập nội dung tìm kiếm...");
                }
            }
        });
    }

    private void loadDataFromDatabase() {
        try {
            listKM = KhuyenMaiDAO.selectAll();
            loadDataToTable(listKM);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Lỗi tải dữ liệu: " + ex.getMessage());
        }
    }

    private void loadDataToTable(List<KhuyenMaiDTO> data) {
        tableModel.setRowCount(0);
        for (KhuyenMaiDTO km : data) {
            tableModel.addRow(new Object[]{
                km.getMaSKKhuyenMai(),
                km.getTenKhuyenMai(),
                km.getPhanTramGiam() + "%",
                km.getNgayBatDau(),
                km.getNgayKetThuc(),
                km.getDieuKien()
            });
        }
    }

    private void themKhuyenMai() {
        ThemKhuyenMaiDialog dlg = new ThemKhuyenMaiDialog(SwingUtilities.getWindowAncestor(this));
        dlg.setVisible(true);
        if (dlg.isSaved()) {
            refreshData();
        }
    }

    private void xoaKhuyenMai() {
        int row = table.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn khuyến mãi cần xóa!");
            return;
        }
        
        String maKM = (String) tableModel.getValueAt(row, 0);
        int confirm = JOptionPane.showConfirmDialog(
            this, 
            "Xóa khuyến mãi " + maKM + "?",
            "Xác nhận xóa",
            JOptionPane.YES_NO_OPTION
        );
        
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                boolean success = KhuyenMaiDAO.delete(maKM);
                if (success) {
                    JOptionPane.showMessageDialog(this, "Xóa thành công!");
                    refreshData();
                } else {
                    JOptionPane.showMessageDialog(this, "Xóa thất bại!");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Lỗi khi xóa: " + ex.getMessage());
            }
        }
    }

    private void suaKhuyenMai() {
        int row = table.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn khuyến mãi cần sửa!");
            return;
        }
        
        String maKM = (String) tableModel.getValueAt(row, 0);
        KhuyenMaiDTO km = KhuyenMaiDAO.selectById(maKM);
        
        if (km != null) {
            SuaKhuyenMaiDialog dlg = new SuaKhuyenMaiDialog(
                (Frame) SwingUtilities.getWindowAncestor(this),
                km
            );
            dlg.setVisible(true);
            
            if (dlg.isUpdated()) {
                refreshData();
            }
        }
    }

    private void refreshData() {
        loadDataFromDatabase();
        txtSearch.setText("Nhập nội dung tìm kiếm...");
        txtSearch.setForeground(Color.GRAY);
        cbbPhanTram.setSelectedIndex(0);
    }

    private void filterData() {
        String searchText = txtSearch.getText().trim().toLowerCase();
        if (searchText.equals("nhập nội dung tìm kiếm...")) {
            searchText = "";
        }
        
        String filterType = (String) cbbPhanTram.getSelectedItem();
        List<KhuyenMaiDTO> filteredList = new ArrayList<>();

        for (KhuyenMaiDTO km : listKM) {
            // Lọc theo phần trăm
            boolean matchPT = true;
            int pt = km.getPhanTramGiam();
            
            switch (filterType) {
                case "Dưới 10%": matchPT = pt < 10; break;
                case "Từ 10% - 20%": matchPT = pt >= 10 && pt <= 20; break;
                case "Trên 20%": matchPT = pt > 20; break;
            }

            // Lọc theo từ khóa
            boolean matchSearch = searchText.isEmpty()
                || km.getMaSKKhuyenMai().toLowerCase().contains(searchText)
                || km.getTenKhuyenMai().toLowerCase().contains(searchText)
                || km.getDieuKien().toLowerCase().contains(searchText);

            if (matchPT && matchSearch) {
                filteredList.add(km);
            }
        }
        
        loadDataToTable(filteredList);
    }

    // ======= Implement interfaces ========
    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            filterData();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        filterData();
    }

    @Override public void keyTyped(KeyEvent e) {}
    @Override public void keyPressed(KeyEvent e) {}
}