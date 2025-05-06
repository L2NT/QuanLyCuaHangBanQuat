package GUI.Dialog;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ChiTietHoaDonDialog extends JDialog {
    private JTable table;
    private DefaultTableModel tableModel;
    private String maHD; // Mã hóa đơn được truyền vào

    public ChiTietHoaDonDialog(Window owner, String maHD) {
        super(owner, "Chi Tiết Hóa Đơn - " + maHD, ModalityType.APPLICATION_MODAL);
        this.maHD = maHD;
        initComponent();
    }

    private void initComponent() {
        setSize(600, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel lblTitle = new JLabel("CHI TIẾT HÓA ĐƠN " + maHD, SwingConstants.CENTER);
        lblTitle.setFont(lblTitle.getFont().deriveFont(Font.BOLD, 16f));
        add(lblTitle, BorderLayout.NORTH);

        // Bảng hiển thị: Mã quạt, Số lượng, Đơn giá, Thành tiền, Mã bảo hành
        String[] columns = {"Mã quạt", "Số lượng", "Đơn giá", "Thành tiền", "Mã bảo hành"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Panel nút
        JPanel pnlBottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnDong = new JButton("Đóng");
        pnlBottom.add(btnDong);
        add(pnlBottom, BorderLayout.SOUTH);

        // Thêm dữ liệu dummy
        addDummyData(maHD);

        btnDong.addActionListener(e -> dispose());
    }

    private void addDummyData(String maHD) {
        // Tùy theo maHD, ta có thể thêm dữ liệu khác nhau
        // Ở đây demo cứng
        if (maHD.equals("HD001")) {
            tableModel.addRow(new Object[]{"Q001", 2, 300000, 600000, "BH001"});
            tableModel.addRow(new Object[]{"Q003", 1, 900000, 900000, null});
        } else if (maHD.equals("HD002")) {
            tableModel.addRow(new Object[]{"Q002", 3, 200000, 600000, "BH005"});
        } else {
            tableModel.addRow(new Object[]{"Q004", 1, 500000, 500000, "BH007"});
        }
    }

    public boolean isDataUpdated() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
