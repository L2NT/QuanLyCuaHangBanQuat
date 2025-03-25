package GUI.Panel;

import GUI.Dialog.ChiTietQuatDialog;
import GUI.Dialog.ThemQuatDialog;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class QuatPanel extends JPanel {
    private JTable table;
    private DefaultTableModel tableModel;

    public QuatPanel() {
        initComponent();
    }

    private void initComponent() {
        this.setLayout(new BorderLayout(10, 10));
        this.setBackground(Color.WHITE);

        // Thanh công cụ (toolbar) ở trên
        JPanel toolbar = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        JButton btnThem = new JButton("Thêm");
        JButton btnXoa = new JButton("Xóa");
        JButton btnChiTiet = new JButton("Chi tiết");
        JButton btnExcel = new JButton("Xuất Excel");
        JButton btnRefresh = new JButton("Làm mới");

        toolbar.add(btnThem);
        toolbar.add(btnXoa);
        toolbar.add(btnChiTiet);
        toolbar.add(btnExcel);
        toolbar.add(btnRefresh);

        this.add(toolbar, BorderLayout.NORTH);

        // Bảng hiển thị danh sách quạt
        String[] columns = {"Mã quạt", "Tên quạt", "Loại quạt", "Thương hiệu", "Xuất xứ", "Số lượng"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);

        // Thêm dữ liệu mẫu
        addDummyData();

        JScrollPane scroll = new JScrollPane(table);
        this.add(scroll, BorderLayout.CENTER);

        // Sự kiện nút Thêm
        btnThem.addActionListener(e -> {
            ThemQuatDialog dlg = new ThemQuatDialog(SwingUtilities.getWindowAncestor(this));
            dlg.setVisible(true);
        });

        // Sự kiện nút Chi tiết
        btnChiTiet.addActionListener(e -> {
            ChiTietQuatDialog dlg = new ChiTietQuatDialog(SwingUtilities.getWindowAncestor(this));
            dlg.setVisible(true);
        });

        // Sự kiện nút Xóa (xóa row trên bảng, không DB)
        btnXoa.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                tableModel.removeRow(row);
            }
        });

        // Làm mới (xóa rồi thêm lại dummy)
        btnRefresh.addActionListener(e -> {
            tableModel.setRowCount(0);
            addDummyData();
        });

        // Xuất Excel (chỉ demo)
        btnExcel.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Chức năng xuất Excel (demo)!");
        });
    }

    private void addDummyData() {
        tableModel.addRow(new Object[]{"Q001", "Quạt treo tường X", "Quạt treo tường", "Panasonic", "Nhật Bản", 10});
        tableModel.addRow(new Object[]{"Q002", "Quạt đứng Y", "Quạt đứng", "Midea", "Trung Quốc", 5});
        tableModel.addRow(new Object[]{"Q003", "Quạt bàn Z", "Quạt bàn", "Asia", "Việt Nam", 8});
    }
}
