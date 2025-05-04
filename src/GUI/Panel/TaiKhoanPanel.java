package GUI.Panel;

import BUS.TaiKhoanBUS;
import DTO.TaiKhoan;
import GUI.Dialog.ThemTaiKhoanDialog;
import GUI.Dialog.SuaTaiKhoanDialog;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class TaiKhoanPanel extends JPanel {
    private final TaiKhoanBUS bll = new TaiKhoanBUS();
    private final DefaultTableModel model;
    private final JTable tbl;
    private final TableRowSorter<DefaultTableModel> sorter;

    private JComboBox<String> cbbFilter;
    private JTextField txtSearch;
    private JButton btnThem, btnSua, btnXoa, btnLamMoi;

    public TaiKhoanPanel() {
        setLayout(new BorderLayout(10,10));
        setBorder(new EmptyBorder(10,10,10,10));

        // === Toolbar ===
        JPanel tb = new JPanel(new BorderLayout());
        JPanel left = new JPanel(new FlowLayout(FlowLayout.LEFT,10,5));
        btnThem   = new JButton("THÊM");
        btnSua    = new JButton("CHỈNH SỬA");
        btnXoa    = new JButton("XÓA");
        left.add(btnThem);
        left.add(btnSua);
        left.add(btnXoa);
        tb.add(left, BorderLayout.WEST);

        JPanel right = new JPanel(new FlowLayout(FlowLayout.RIGHT,10,5));
        cbbFilter = new JComboBox<>(new String[]{"Tất cả","QuanLy","NhanVien"});
        txtSearch = new JTextField(15);
        btnLamMoi = new JButton("LÀM MỚI");
        right.add(new JLabel("Lọc Vai trò:"));
        right.add(cbbFilter);
        right.add(new JLabel("Tìm kiếm:"));
        right.add(txtSearch);
        right.add(btnLamMoi);
        tb.add(right, BorderLayout.EAST);

        add(tb, BorderLayout.NORTH);

        // === Table ===
        model = new DefaultTableModel(
            new Object[]{"Mã TK","Mã NV","Tên TK","Mật khẩu","Vai trò"}, 0
        ) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        tbl = new JTable(model);
        tbl.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        sorter = new TableRowSorter<>(model);
        tbl.setRowSorter(sorter);
        add(new JScrollPane(tbl), BorderLayout.CENTER);

        // Load dữ liệu
        reloadData();

        // === Actions ===
        btnThem.addActionListener(e -> {
            ThemTaiKhoanDialog dlg = new ThemTaiKhoanDialog(
                SwingUtilities.getWindowAncestor(this)
            );
            dlg.setVisible(true);
            if (dlg.isSaved()) reloadData();
        });
        btnSua.addActionListener(e -> {
            int row = tbl.getSelectedRow();
            if (row < 0) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn tài khoản!",
                                              "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String maTK = model.getValueAt(
                tbl.convertRowIndexToModel(row), 0).toString();
            if ("TK000".equals(maTK)) {
                JOptionPane.showMessageDialog(this, "Không thể sửa Admin",
                                              "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            SuaTaiKhoanDialog dlg = new SuaTaiKhoanDialog(
                SwingUtilities.getWindowAncestor(this)
            );
            dlg.loadForEdit(maTK);
            dlg.setVisible(true);
            if (dlg.isSaved()) reloadData();
        });
        btnXoa.addActionListener(e -> {
            int row = tbl.getSelectedRow();
            if (row < 0) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn tài khoản!",
                                              "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String maTK = model.getValueAt(
                tbl.convertRowIndexToModel(row), 0).toString();
            if ("TK000".equals(maTK)) {
                JOptionPane.showMessageDialog(this, "Không thể xóa Admin",
                                              "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (JOptionPane.showConfirmDialog(this,
                  "Xóa tài khoản " + maTK + "?", "Xác nhận",
                  JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                bll.xoa(maTK);
                reloadData();
            }
        });
        btnLamMoi.addActionListener(e -> {
            txtSearch.setText("");
            cbbFilter.setSelectedIndex(0);
            sorter.setRowFilter(null);
        });

        // === Tìm kiếm + lọc ===
        txtSearch.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { applyFilter(); }
            public void removeUpdate(DocumentEvent e) { applyFilter(); }
            public void changedUpdate(DocumentEvent e) { applyFilter(); }
        });
        cbbFilter.addActionListener(e -> applyFilter());
    }

    private void applyFilter() {
        List<RowFilter<DefaultTableModel, Integer>> filters = new ArrayList<>();
        // filter theo vai trò nếu không phải 'Tất cả'
        String role = (String)cbbFilter.getSelectedItem();
        if (!"Tất cả".equals(role)) {
            filters.add(RowFilter.regexFilter(
                "^" + Pattern.quote(role) + "$$", 4));
        }
        // filter theo text
        String text = txtSearch.getText().trim();
        if (!text.isEmpty()) {
            try {
                filters.add(RowFilter.regexFilter("(?i)" + Pattern.quote(text)));
            } catch (PatternSyntaxException ex) {
                // bỏ qua nếu không hợp lệ
            }
        }
        sorter.setRowFilter(RowFilter.andFilter(filters));
    }

    private void reloadData() {
        model.setRowCount(0);
        for (TaiKhoan t : bll.layTatCa()) {
            model.addRow(new Object[]{
                t.getMaTaiKhoan(),
                t.getMaNhanVien(),
                t.getUsername(),
                t.getPassword(),
                t.getVaiTro()
            });
        }
    }
}
