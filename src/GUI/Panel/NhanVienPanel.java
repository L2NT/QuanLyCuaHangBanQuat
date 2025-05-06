// src/GUI/Panel/NhanVienPanel.java
package GUI.Panel;

import BUS.NhanVienBUS;
import DTO.NhanVienDTO;
import GUI.Dialog.ThemNhanVienDialog;
import GUI.Dialog.SuaNhanVienDialog;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;

public class NhanVienPanel extends JPanel {
    private final DefaultTableModel model;
    private final JTable tbl;
    // Bỏ final để khởi tạo trong createButtonPanel()
    private JComboBox<String> cbbFilter;
    private JTextField txtSearch;
    private final NhanVienBUS bll = new NhanVienBUS();

    public NhanVienPanel() {
        setLayout(new BorderLayout(10,10));
        setBorder(new EmptyBorder(10,10,10,10));

        // Tạo toolbar (thiết lập cbbFilter và txtSearch trong đó)
        JPanel toolbar = createButtonPanel();
        add(toolbar, BorderLayout.NORTH);

        // Tạo table model
        model = new DefaultTableModel(
            new Object[]{"Mã NV","Họ tên","Chức vụ","SĐT","Địa chỉ"}, 0
        ) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        tbl = new JTable(model);
        tbl.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Lần đầu load dữ liệu
        reloadData();

        add(new JScrollPane(tbl), BorderLayout.CENTER);
    }

    private JPanel createButtonPanel() {
        JPanel toolbar = new JPanel(new BorderLayout());
        toolbar.setBackground(Color.WHITE);
        toolbar.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        // A) Nút bên trái
        JPanel leftToolPanel = new JPanel(new FlowLayout(FlowLayout.LEFT,10,5));
        leftToolPanel.setOpaque(false);
        
        // Tạo các buttons với icons
        // Lưu ý: Đường dẫn tới icons cần được điều chỉnh theo cấu trúc thư mục của bạn
        ImageIcon iconThem = new ImageIcon(getClass().getResource("/icon/them.png"));
        JButton btnThem = new JButton("THÊM", iconThem);
        btnThem.setHorizontalTextPosition(SwingConstants.CENTER);
        btnThem.setVerticalTextPosition(SwingConstants.BOTTOM);
        
        ImageIcon iconXoa = new ImageIcon(getClass().getResource("/icon/xoa.png"));
        JButton btnXoa = new JButton("XÓA", iconXoa);
        btnXoa.setHorizontalTextPosition(SwingConstants.CENTER);
        btnXoa.setVerticalTextPosition(SwingConstants.BOTTOM);
        
        ImageIcon iconSua = new ImageIcon(getClass().getResource("/icon/sua.png"));
        JButton btnSua = new JButton("SỬA", iconSua);
        btnSua.setHorizontalTextPosition(SwingConstants.CENTER);
        btnSua.setVerticalTextPosition(SwingConstants.BOTTOM);
        
        ImageIcon iconExcel = new ImageIcon(getClass().getResource("/icon/xuatexcel.png"));
        JButton btnExcel = new JButton("XUẤT EXCEL", iconExcel);
        btnExcel.setHorizontalTextPosition(SwingConstants.CENTER);
        btnExcel.setVerticalTextPosition(SwingConstants.BOTTOM);
        
        leftToolPanel.add(btnThem);
        leftToolPanel.add(btnXoa);
        leftToolPanel.add(btnSua);
        leftToolPanel.add(btnExcel);

        // B) Filter + Search + Refresh bên phải
        JPanel rightToolPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT,10,5));
        rightToolPanel.setOpaque(false);
        
        JLabel lblFilter = new JLabel("Chức vụ:");
        cbbFilter = new JComboBox<>(new String[]{"Tất cả","Quản lý","Nhân viên"});
        
        JLabel lblSearch = new JLabel("Tìm kiếm:");
        txtSearch = new JTextField(15);
        
        JButton btnLamMoi = new JButton("LÀM MỚI");
        
        rightToolPanel.add(lblFilter);
        rightToolPanel.add(cbbFilter);
        rightToolPanel.add(lblSearch);
        rightToolPanel.add(txtSearch);
        rightToolPanel.add(btnLamMoi);

        toolbar.add(leftToolPanel, BorderLayout.WEST);
        toolbar.add(rightToolPanel, BorderLayout.EAST);

        // Kịch bản lọc & tìm kiếm
        ActionListener doFilter = e -> reloadData();
        cbbFilter.addActionListener(doFilter);
        txtSearch.addActionListener(doFilter);
        btnLamMoi.addActionListener(e -> {
            cbbFilter.setSelectedIndex(0);
            txtSearch.setText("");
            reloadData();
        });

        btnThem.addActionListener(e -> {
            ThemNhanVienDialog dlg = new ThemNhanVienDialog(
                SwingUtilities.getWindowAncestor(this)
            );
            dlg.setVisible(true);
            if (dlg.isSaved()) reloadData();
        });

        btnSua.addActionListener(e -> {
            int r = tbl.getSelectedRow();
            if (r < 0) {
                JOptionPane.showMessageDialog(this,
                    "Chọn một nhân viên để chỉnh sửa",
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String maNV = model.getValueAt(r,0).toString();
            SuaNhanVienDialog dlg = new SuaNhanVienDialog(
                SwingUtilities.getWindowAncestor(this), maNV
            );
            dlg.setVisible(true);
            if (dlg.isSaved()) reloadData();
        });

        btnXoa.addActionListener(e -> {
            int r = tbl.getSelectedRow();
            if (r < 0) {
                JOptionPane.showMessageDialog(this,
                    "Chọn một nhân viên để xóa",
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (JOptionPane.showConfirmDialog(this,
                "Xóa nhân viên này?") == JOptionPane.YES_OPTION) {
                String maNV = model.getValueAt(r,0).toString();
                bll.xoa(maNV);
                reloadData();
            }
        });

        return toolbar;
    }

    /** Tải lại data từ BLL, áp dụng filter và tìm kiếm */
    private void reloadData() {
        List<NhanVienDTO> all = bll.layTatCa();

        // 1) Lọc theo chức vụ
        String cv = (String)cbbFilter.getSelectedItem();
        if (!"Tất cả".equals(cv)) {
            all = all.stream()
                     .filter(n -> n.getChucVu().equalsIgnoreCase(cv))
                     .collect(Collectors.toList());
        }

        // 2) Tìm kiếm theo mã hoặc họ tên
        String kw = txtSearch.getText().trim().toLowerCase();
        if (!kw.isEmpty()) {
            all = all.stream()
                     .filter(n -> n.getMaNV().toLowerCase().contains(kw)
                               || n.getHoTen().toLowerCase().contains(kw))
                     .collect(Collectors.toList());
        }

        // Đổ lên bảng
        model.setRowCount(0);
        for (NhanVienDTO n : all) {
            model.addRow(new Object[]{
                n.getMaNV(),
                n.getHoTen(),
                n.getChucVu(),
                n.getSdt(),
                n.getDiaChi()
            });
        }
    }
}