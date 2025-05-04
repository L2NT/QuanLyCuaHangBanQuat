package GUI.Panel;

import bll.NhaCungCapBLL;
import dto.NhaCungCap;
import GUI.Dialog.ThemNhaCungCapDialog;
import GUI.Dialog.SuaNhaCungCapDialog;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import java.awt.*;
import java.util.List;

public class NhaCungCapPanel extends JPanel {
    private JTable table;
    private DefaultTableModel tableModel;

    public NhaCungCapPanel() {
        setBackground(Color.LIGHT_GRAY);
        setLayout(new BorderLayout());

        add(createButtonPanel(), BorderLayout.NORTH);
        add(createTablePanel(), BorderLayout.CENTER);

        loadData();
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton btnThem = new JButton("THÊM");
        JButton btnSua = new JButton("SỬA");
        JButton btnXoa = new JButton("XÓA");
        leftPanel.add(btnThem);
        leftPanel.add(btnSua);
        leftPanel.add(btnXoa);

        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JTextField txtSearch = new JTextField(15);
        JButton btnTim = new JButton("TÌM KIẾM");
        JButton btnLamMoi = new JButton("LÀM MỚI");
        rightPanel.add(new JLabel("Tìm theo mã:"));
        rightPanel.add(txtSearch);
        rightPanel.add(btnTim);
        rightPanel.add(btnLamMoi);

        panel.add(leftPanel, BorderLayout.WEST);
        panel.add(rightPanel, BorderLayout.EAST);

        btnThem.addActionListener(e -> {
            ThemNhaCungCapDialog dialog = new ThemNhaCungCapDialog((Window) SwingUtilities.getWindowAncestor(this));
            dialog.setVisible(true);
            if (dialog.isAdded()) loadData();
        });

        btnSua.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn nhà cung cấp để sửa.");
                return;
            }

            String ma = table.getValueAt(row, 0).toString();
            String ten = table.getValueAt(row, 1).toString();
            String diachi = table.getValueAt(row, 2).toString();
            String sdt = table.getValueAt(row, 3).toString();

            NhaCungCap ncc = new NhaCungCap(ma, ten, diachi, sdt);
            SuaNhaCungCapDialog dialog = new SuaNhaCungCapDialog((Window) SwingUtilities.getWindowAncestor(this), ncc);
            dialog.setVisible(true);
            if (dialog.isUpdated()) loadData();
        });

        btnXoa.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn nhà cung cấp để xóa.");
                return;
            }
            String ma = table.getValueAt(row, 0).toString();
            int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa?", "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                NhaCungCapBLL bll = new NhaCungCapBLL();
                if (bll.xoa(ma)) {
                    JOptionPane.showMessageDialog(this, "Xóa thành công!");
                    loadData();
                } else {
                    JOptionPane.showMessageDialog(this, "Xóa thất bại!");
                }
            }
        });

        btnTim.addActionListener(e -> {
            String keyword = txtSearch.getText().trim();
            if (keyword.isEmpty()) return;
            NhaCungCapBLL bll = new NhaCungCapBLL();
            NhaCungCap result = bll.timTheoMa(keyword);
            if (result != null) {
                tableModel.setRowCount(0);
                tableModel.addRow(new Object[]{
                    result.getMaNCC(),
                    result.getTenNCC(),
                    result.getDiaChiNCC(),
                    result.getSdtNCC()
                });
            } else {
                JOptionPane.showMessageDialog(this, "Không tìm thấy!");
            }
        });

        btnLamMoi.addActionListener(e -> {
            txtSearch.setText("");
            loadData();
        });

        return panel;
    }

    private JScrollPane createTablePanel() {
        tableModel = new DefaultTableModel(new Object[]{"Mã NCC", "Tên NCC", "Địa Chỉ", "SĐT"}, 0) {
            public boolean isCellEditable(int r, int c) {
                return false;
            }
        };

        table = new JTable(tableModel);
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.setRowHeight(28);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 14));
        header.setPreferredSize(new Dimension(header.getWidth(), 40));

        DefaultTableCellRenderer center = new DefaultTableCellRenderer();
        center.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(center);
        }

        return new JScrollPane(table);
    }

    private void loadData() {
        tableModel.setRowCount(0);
        NhaCungCapBLL bll = new NhaCungCapBLL();
        List<NhaCungCap> ds = bll.layTatCa();
        for (NhaCungCap ncc : ds) {
            tableModel.addRow(new Object[]{
                ncc.getMaNCC(),
                ncc.getTenNCC(),
                ncc.getDiaChiNCC(),
                ncc.getSdtNCC()
            });
        }
    }
}
