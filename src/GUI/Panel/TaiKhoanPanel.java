package GUI.Panel;

import BLL.TaiKhoanBLL;
import DTO.TaiKhoan;
import GUI.Dialog.ThemTaiKhoanDialog;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class TaiKhoanPanel extends JPanel {
    private final DefaultTableModel model;
    private final JTable tbl;

    public TaiKhoanPanel() {
        setLayout(new BorderLayout(10,10));
        setBorder(new EmptyBorder(10,10,10,10));

        // 1) Toolbar
        add(createButtonPanel(), BorderLayout.NORTH);

        // 2) Table
        model = new DefaultTableModel(
            new Object[]{"Mã TK","Mã NV","Tên TK","Mật khẩu","Vai trò"}, 0
        ) {
            @Override public boolean isCellEditable(int row, int col) { return false; }
        };
        tbl = new JTable(model);
        tbl.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        loadData();
        add(new JScrollPane(tbl), BorderLayout.CENTER);
    }

    private JPanel createButtonPanel() {
        JPanel toolbar = new JPanel(new BorderLayout());

        // (A) Nút bên trái
        JPanel left = new JPanel(new FlowLayout(FlowLayout.LEFT,10,5));
        JButton btnThem     = new JButton("THÊM");
        JButton btnChinhSua = new JButton("CHỈNH SỬA");
        JButton btnXoa      = new JButton("XÓA");
        JButton btnChiTiet  = new JButton("CHI TIẾT");
        left.add(btnThem);
        left.add(btnChinhSua);
        left.add(btnXoa);
        left.add(btnChiTiet);

        // THÊM
        btnThem.addActionListener(e -> {
            ThemTaiKhoanDialog dlg = new ThemTaiKhoanDialog(
                SwingUtilities.getWindowAncestor(this)
            );
            dlg.setVisible(true);
            loadData();
        });

        // CHỈNH SỬA (không cho TK000)
        btnChinhSua.addActionListener(e -> {
            int r = tbl.getSelectedRow();
            if (r == -1) return;
            String maTK = model.getValueAt(r, 0).toString();
            if ("TK000".equals(maTK)) {
                JOptionPane.showMessageDialog(
                    this,
                    "Tài khoản admin (TK000) không được phép chỉnh sửa!",
                    "Thông báo",
                    JOptionPane.WARNING_MESSAGE
                );
                return;
            }
            ThemTaiKhoanDialog dlg = new ThemTaiKhoanDialog(
                SwingUtilities.getWindowAncestor(this)
            );
            dlg.loadForEdit(maTK);
            dlg.setVisible(true);
            loadData();
        });

        // XÓA (không cho TK000)
        btnXoa.addActionListener(e -> {
            int r = tbl.getSelectedRow();
            if (r == -1) return;
            String maTK = model.getValueAt(r, 0).toString();
            if ("TK000".equals(maTK)) {
                JOptionPane.showMessageDialog(
                    this,
                    "Không thể xóa tài khoản admin!",
                    "Thông báo",
                    JOptionPane.WARNING_MESSAGE
                );
                return;
            }
            int c = JOptionPane.showConfirmDialog(
                this,
                "Xác nhận xóa tài khoản " + maTK + "?",
                "Xác nhận",
                JOptionPane.YES_NO_OPTION
            );
            if (c == JOptionPane.YES_OPTION) {
                new TaiKhoanBLL().xoa(maTK);
                loadData();
            }
        });

        // CHI TIẾT (mở dialog chỉnh sửa)
        btnChiTiet.addActionListener(e -> {
            int r = tbl.getSelectedRow();
            if (r == -1) return;
            String maTK = model.getValueAt(r, 0).toString();
            ThemTaiKhoanDialog dlg = new ThemTaiKhoanDialog(
                SwingUtilities.getWindowAncestor(this)
            );
            dlg.loadForEdit(maTK);
            dlg.setVisible(true);
            loadData();
        });

        // (B) Lọc + tìm kiếm + làm mới
        JPanel right = new JPanel(new FlowLayout(FlowLayout.RIGHT,10,5));
        JComboBox<String> cbbFilter = new JComboBox<>(
            new String[]{"Tất cả","Mã TK","Tên TK","Vai trò"}
        );
        JTextField txtSearch = new JTextField(15);
        txtSearch.setText("Nhập nội dung tìm kiếm...");
        JButton btnLamMoi = new JButton("LÀM MỚI");
        btnLamMoi.addActionListener(e -> loadData());

        right.add(cbbFilter);
        right.add(txtSearch);
        right.add(btnLamMoi);

        toolbar.add(left,  BorderLayout.WEST);
        toolbar.add(right, BorderLayout.EAST);
        return toolbar;
    }

    private void loadData() {
        model.setRowCount(0);
        List<TaiKhoan> list = new TaiKhoanBLL().layTatCa();
        for (TaiKhoan t : list) {
            model.addRow(new Object[]{
                t.getMaTaiKhoan(),
                t.getMaNhanVien(),
                t.getTenTaiKhoan(),
                t.getMatKhau(),
                t.getVaiTro()
            });
        }
    }
}
