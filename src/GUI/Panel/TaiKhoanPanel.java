package GUI.Panel;

import BLL.TaiKhoanBLL;
import DTO.TaiKhoan;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class TaiKhoanPanel extends JPanel {
    private final DefaultTableModel model;
    private final JTable tbl;
    private final JButton btnThem, btnChinhSua, btnXoa, btnLamMoi;
    private final JComboBox<String> cbbFilter;
    private final JTextField txtSearch;

    public TaiKhoanPanel() {
        setLayout(new BorderLayout(10,10));
        setBorder(new EmptyBorder(10,10,10,10));

        // Toolbar
        JPanel toolbar = new JPanel(new BorderLayout());
        JPanel left = new JPanel(new FlowLayout(FlowLayout.LEFT, 10,5));
        btnThem      = new JButton("THÊM");
        btnChinhSua  = new JButton("CHỈNH SỬA");
        btnXoa       = new JButton("XÓA");
        left.add(btnThem);
        left.add(btnChinhSua);
        left.add(btnXoa);
        JPanel right = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10,5));
        cbbFilter    = new JComboBox<>(new String[]{"Tất cả","Mã TK","Tên TK","Vai trò"});
        txtSearch    = new JTextField("Nhập nội dung tìm kiếm...", 15);
        btnLamMoi    = new JButton("LÀM MỚI");
        right.add(cbbFilter);
        right.add(txtSearch);
        right.add(btnLamMoi);
        toolbar.add(left, BorderLayout.WEST);
        toolbar.add(right, BorderLayout.EAST);
        add(toolbar, BorderLayout.NORTH);

        // Table
        model = new DefaultTableModel(
            new Object[]{"Mã TK","Mã NV","Tên TK","Mật khẩu","Vai trò"}, 
            0
        ) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        tbl = new JTable(model);
        tbl.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        add(new JScrollPane(tbl), BorderLayout.CENTER);

        // Lần đầu nạp dữ liệu
        loadDataFromBLL();
    }

    /** Nạp lại dữ liệu từ BLL/DAO */
    public void loadDataFromBLL() {
        model.setRowCount(0);
        List<TaiKhoan> ds = new TaiKhoanBLL().layTatCa();
        for (TaiKhoan tk : ds) {
            model.addRow(new Object[]{
                tk.getMaTaiKhoan(),
                tk.getMaNhanVien(),
                tk.getTenTaiKhoan(),
                tk.getMatKhau(),
                tk.getVaiTro()
            });
        }
    }

    /** Lấy mã TK đang chọn (hoặc null nếu chưa chọn) */
    public String getSelectedMaTK() {
        int r = tbl.getSelectedRow();
        return r == -1 ? null : model.getValueAt(r, 0).toString();
    }

    // Cho phép các lớp ngoài gắn listener
    public void addThemAction(ActionListener a)     { btnThem.addActionListener(a); }
    public void addChinhSuaAction(ActionListener a) { btnChinhSua.addActionListener(a); }
    public void addXoaAction(ActionListener a)      { btnXoa.addActionListener(a); }
    public void addLamMoiAction(ActionListener a)   { btnLamMoi.addActionListener(a); }
}
