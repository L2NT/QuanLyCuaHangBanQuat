package GUI.Panel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class NhaCungCapPanel extends JPanel {
    private JTable table;
    private DefaultTableModel tableModel;

    public NhaCungCapPanel() {
        initComponent();
    }

    private void initComponent() {
        setLayout(new BorderLayout(10,10));
        setBackground(Color.WHITE);

        // Thanh công cụ
        JPanel toolbar = new JPanel(new FlowLayout(FlowLayout.LEFT,10,5));
        JButton btnThem = new JButton("Thêm");
        JButton btnXoa = new JButton("Xóa");
        JButton btnSua = new JButton("Sửa");
        toolbar.add(btnThem);
        toolbar.add(btnXoa);
        toolbar.add(btnSua);

        add(toolbar, BorderLayout.NORTH);

        // Bảng danh sách NCC
        String[] columns = {"Mã NCC", "Tên NCC", "SĐT", "Địa chỉ", "Email"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Thêm dữ liệu mẫu
        addDummyData();

        // Xử lý nút
        btnThem.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Thêm nhà cung cấp (demo)!");
        });
        btnXoa.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                tableModel.removeRow(row);
            }
        });
        btnSua.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Sửa nhà cung cấp (demo)!");
        });
    }

    private void addDummyData() {
        tableModel.addRow(new Object[]{"NCC001", "Cty Quạt ABC", "0909009009", "Hà Nội", "abc@quat.com"});
        tableModel.addRow(new Object[]{"NCC002", "Cty Quạt XYZ", "0912345678", "TP.HCM", "xyz@quat.com"});
    }
}
