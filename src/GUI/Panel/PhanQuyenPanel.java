package GUI.Panel;

import GUI.Dialog.ChiTietPhanQuyenDialog;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;


public class PhanQuyenPanel extends JPanel {
    private JButton btnThemTK, btnXoaTK, btnPhanQuyen, btnExcel, btnLamMoi;
    private JTable table;
    private DefaultTableModel tableModel;
    private List<TaiKhoan> listTK; // Danh sách tài khoản gốc (dummy)

    public PhanQuyenPanel() {
        initComponent();
        initData();
        loadDataToTable(listTK);
    }

    private void initComponent() {
        setLayout(new BorderLayout(10, 10));
        setBackground(Color.WHITE);

        // ========== Thanh công cụ (toolbar) ==========
        JPanel toolbar = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        btnThemTK = new JButton("THÊM TÀI KHOẢN");
        btnXoaTK = new JButton("XÓA TÀI KHOẢN");
        btnPhanQuyen = new JButton("THỰC HIỆN PHÂN QUYỀN");
        btnExcel = new JButton("XUẤT EXCEL");
        btnLamMoi = new JButton("LÀM MỚI");

        toolbar.add(btnThemTK);
        toolbar.add(btnXoaTK);
        toolbar.add(btnPhanQuyen);
        toolbar.add(btnExcel);
        toolbar.add(btnLamMoi);

        add(toolbar, BorderLayout.NORTH);

        // ========== Bảng hiển thị Mã tài khoản + Tên tài khoản ==========
        // Cột: Mã tài khoản, Tên tài khoản
        String[] columns = {"Mã tài khoản", "Tên tài khoản"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);

        JScrollPane scroll = new JScrollPane(table);
        add(scroll, BorderLayout.CENTER);

        // ========== Sự kiện nút ==========
        // Thêm tài khoản
        btnThemTK.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Thêm tài khoản (demo)!");
            // Hoặc mở dialog ThemTaiKhoanDialog
        });

        // Xóa tài khoản
        btnXoaTK.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                tableModel.removeRow(row);
            }
        });

        // Thực hiện phân quyền
        btnPhanQuyen.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row < 0) {
                JOptionPane.showMessageDialog(this, "Chưa chọn tài khoản để phân quyền!");
                return;
            }
            // Lấy mã tài khoản, tên tài khoản
            String maTK = (String) tableModel.getValueAt(row, 0);
            String tenTK = (String) tableModel.getValueAt(row, 1);

            // Mở dialog chi tiết phân quyền
            ChiTietPhanQuyenDialog dlg = new ChiTietPhanQuyenDialog(
                    SwingUtilities.getWindowAncestor(this),
                    maTK, tenTK
            );
            dlg.setVisible(true);
            // Ở đây, nếu cần, có thể lưu cài đặt checkbox...
        });

        // Xuất Excel
        btnExcel.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Xuất Excel (demo)!");
        });

        // Làm mới
        btnLamMoi.addActionListener(e -> {
            loadDataToTable(listTK);
        });
    }

    /**
     * Tạo dữ liệu mẫu (dummy)
     */
    private void initData() {
        listTK = new ArrayList<>();
        // (maTK, tenTK)
        listTK.add(new TaiKhoan("TK001", "Admin"));
        listTK.add(new TaiKhoan("TK002", "Nhân viên bán hàng"));
        listTK.add(new TaiKhoan("TK003", "Quản lý kho"));
        listTK.add(new TaiKhoan("TK004", "Thành viên đăng ký"));
    }

    private void loadDataToTable(List<TaiKhoan> data) {
        tableModel.setRowCount(0);
        for (TaiKhoan tk : data) {
            tableModel.addRow(new Object[]{tk.getMaTK(), tk.getTenTK()});
        }
    }

    // ========== Lớp TaiKhoan (dummy) ==========
    class TaiKhoan {
        private String maTK;
        private String tenTK;

        public TaiKhoan(String ma, String ten) {
            this.maTK = ma;
            this.tenTK = ten;
        }

        public String getMaTK() { return maTK; }
        public String getTenTK() { return tenTK; }
    }
}
