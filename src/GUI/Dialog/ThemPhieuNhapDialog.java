package GUI.Dialog;

import javax.swing.*;
import java.awt.*;

public class ThemPhieuNhapDialog extends JDialog {
    private JTextField txtMaPN, txtMaNV, txtMaNCC, txtNgayNhap, txtTongTien;
    private JButton btnLuu, btnHuy;

    public ThemPhieuNhapDialog(Window owner) {
        super(owner, "Thêm Phiếu Nhập", Dialog.ModalityType.APPLICATION_MODAL);
        initComponent();
    }

    private void initComponent() {
        setSize(400,300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10,10));

        JPanel pnlForm = new JPanel(new GridLayout(5,2,5,5));
        pnlForm.add(new JLabel("Mã phiếu nhập:"));
        txtMaPN = new JTextField();
        pnlForm.add(txtMaPN);

        pnlForm.add(new JLabel("Mã NV:"));
        txtMaNV = new JTextField();
        pnlForm.add(txtMaNV);

        pnlForm.add(new JLabel("Mã NCC:"));
        txtMaNCC = new JTextField();
        pnlForm.add(txtMaNCC);

        pnlForm.add(new JLabel("Ngày nhập:"));
        txtNgayNhap = new JTextField();
        pnlForm.add(txtNgayNhap);

        pnlForm.add(new JLabel("Tổng tiền:"));
        txtTongTien = new JTextField();
        pnlForm.add(txtTongTien);

        add(pnlForm, BorderLayout.CENTER);

        JPanel pnlButton = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnLuu = new JButton("Lưu");
        btnHuy = new JButton("Hủy");
        pnlButton.add(btnLuu);
        pnlButton.add(btnHuy);
        add(pnlButton, BorderLayout.SOUTH);

        btnLuu.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Đã lưu phiếu nhập (demo).");
            dispose();
        });
        btnHuy.addActionListener(e -> dispose());
    }
}
