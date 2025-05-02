package gui.panel;

import bll.TaiKhoanBLL;
import dto.TaiKhoan;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class TaiKhoanPanel extends JPanel {
    private final TaiKhoanBLL bll = new TaiKhoanBLL();
    private final DefaultTableModel model = new DefaultTableModel(new String[]{"ID","Username","Password"}, 0);
    private final JTable table = new JTable(model);
    private final JTextField txtUser = new JTextField(10);
    private final JTextField txtPass = new JTextField(10);

    public TaiKhoanPanel() {
        setLayout(new BorderLayout(10,10));

        // Form thêm / xóa
        JPanel form = new JPanel(new FlowLayout(FlowLayout.LEFT));
        form.add(new JLabel("Username:")); form.add(txtUser);
        form.add(new JLabel("Password:")); form.add(txtPass);

        JButton btnAdd = new JButton("Thêm");
        btnAdd.addActionListener(e -> {
            if (bll.them(txtUser.getText(), txtPass.getText())) {
                loadData();
            } else {
                JOptionPane.showMessageDialog(this, "Lỗi thêm tài khoản");
            }
        });

        JButton btnDelete = new JButton("Xóa");
        btnDelete.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0 && bll.xoa((int)model.getValueAt(row,0))) {
                loadData();
            }
        });

        form.add(btnAdd);
        form.add(btnDelete);
        add(form, BorderLayout.NORTH);

        // Bảng dữ liệu
        loadData();
        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    private void loadData() {
        model.setRowCount(0);
        List<TaiKhoan> list = bll.layTatCa();
        for (TaiKhoan tk : list) {
            model.addRow(new Object[]{ tk.getId(), tk.getUsername(), tk.getPassword() });
        }
    }
}
