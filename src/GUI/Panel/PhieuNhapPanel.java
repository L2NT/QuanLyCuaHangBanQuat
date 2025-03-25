package GUI.Panel;

import GUI.Dialog.ThemPhieuNhapDialog;
import GUI.Dialog.ChiTietPhieuNhapDialog;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PhieuNhapPanel extends JPanel {
    private JTable table;
    private DefaultTableModel tableModel;

    public PhieuNhapPanel() {
        initComponent();
    }

    private void initComponent() {
        setLayout(new BorderLayout(10,10));
        setBackground(Color.WHITE);

        // Toolbar
        JPanel toolbar = new JPanel(new FlowLayout(FlowLayout.LEFT,10,5));
        JButton btnThem = new JButton("Thêm");
        JButton btnXoa = new JButton("Xóa");
        JButton btnChiTiet = new JButton("Chi tiết");
        JButton btnRefresh = new JButton("Làm mới");
        toolbar.add(btnThem);
        toolbar.add(btnXoa);
        toolbar.add(btnChiTiet);
        toolbar.add(btnRefresh);

        add(toolbar, BorderLayout.NORTH);

        // Bảng
        String[] columns = {"Mã phiếu nhập", "Mã NV", "Mã NCC", "Ngày nhập", "Tổng tiền"};
        tableModel = new DefaultTableModel(columns,0);
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Thêm dữ liệu mẫu
        addDummyData();

        // Sự kiện Thêm
        btnThem.addActionListener(e -> {
            ThemPhieuNhapDialog dlg = new ThemPhieuNhapDialog(SwingUtilities.getWindowAncestor(this));
            dlg.setVisible(true);
        });

        // Sự kiện Xóa
        btnXoa.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                tableModel.removeRow(row);
            }
        });

        // Chi tiết
        btnChiTiet.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row < 0) return;
            // Mở dialog chi tiết
            ChiTietPhieuNhapDialog dlg = new ChiTietPhieuNhapDialog(SwingUtilities.getWindowAncestor(this));
            dlg.setVisible(true);
        });

        // Làm mới
        btnRefresh.addActionListener(e -> {
            tableModel.setRowCount(0);
            addDummyData();
        });
    }

    private void addDummyData() {
        tableModel.addRow(new Object[]{"PN001", "NV01", "NCC01", "2025-03-25", "2.000.000"});
        tableModel.addRow(new Object[]{"PN002", "NV02", "NCC03", "2025-03-26", "1.500.000"});
    }
}
