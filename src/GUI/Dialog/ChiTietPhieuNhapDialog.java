package GUI.Dialog;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ChiTietPhieuNhapDialog extends JDialog {
    private JTable table;
    private DefaultTableModel tableModel;

    public ChiTietPhieuNhapDialog(Window owner) {
        super(owner, "Chi Tiết Phiếu Nhập", Dialog.ModalityType.APPLICATION_MODAL);
        initComponent();
    }

    private void initComponent() {
        setSize(600,400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Tiêu đề
        JLabel lblTitle = new JLabel("CHI TIẾT PHIẾU NHẬP", SwingConstants.CENTER);
        lblTitle.setFont(lblTitle.getFont().deriveFont(Font.BOLD,16f));
        add(lblTitle, BorderLayout.NORTH);

        // Bảng
        tableModel = new DefaultTableModel(new Object[]{"Mã quạt", "Số lượng", "Đơn giá"},0);
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Panel nút
        JPanel pnlButton = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnDong = new JButton("Đóng");
        pnlButton.add(btnDong);
        add(pnlButton, BorderLayout.SOUTH);

        // Dummy data
        addDummyData();

        btnDong.addActionListener(e -> dispose());
    }

    private void addDummyData() {
        tableModel.addRow(new Object[]{"Q001", 2, "200.000"});
        tableModel.addRow(new Object[]{"Q002", 1, "300.000"});
    }
}
