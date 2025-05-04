package GUI.Panel;

import BLL.TaiKhoanBLL;
import DTO.TaiKhoan;
import GUI.Dialog.ThemTaiKhoanDialog;
import GUI.Dialog.ChinhSuaTaiKhoanDialog;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class TaiKhoanPanel extends JPanel {
    private final DefaultTableModel model;
    private final JTable tbl;
    private final TaiKhoanBLL bll = new TaiKhoanBLL();

    public TaiKhoanPanel() {
        setLayout(new BorderLayout(10,10));
        setBorder(new EmptyBorder(10,10,10,10));

        // toolbar
        JPanel tb = new JPanel(new BorderLayout());
        JPanel left = new JPanel(new FlowLayout(FlowLayout.LEFT,10,5));
        JButton btnThem    = new JButton("THÊM");
        JButton btnSua     = new JButton("CHỈNH SỬA");
        JButton btnXoa     = new JButton("XÓA");
        JButton btnLamMoi  = new JButton("LÀM MỚI");
        left.add(btnThem); left.add(btnSua); left.add(btnXoa);
        tb.add(left, BorderLayout.WEST);

        JPanel right = new JPanel(new FlowLayout(FlowLayout.RIGHT,10,5));
        right.add(btnLamMoi);
        tb.add(right, BorderLayout.EAST);
        add(tb, BorderLayout.NORTH);

        // table
        model = new DefaultTableModel(new Object[]{"Mã TK","Mã NV","Tên TK","Mật khẩu","Vai trò"},0) {
            @Override public boolean isCellEditable(int r,int c){return false;}
        };
        tbl = new JTable(model);
        tbl.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        add(new JScrollPane(tbl), BorderLayout.CENTER);

        // load
        reload();

        // actions
        btnThem.addActionListener(e -> {
            ThemTaiKhoanDialog dlg = new ThemTaiKhoanDialog(SwingUtilities.getWindowAncestor(this));
            dlg.setVisible(true);
            if (dlg.isSaved()) reload();
        });
        btnSua.addActionListener(e -> {
            int r = tbl.getSelectedRow();
            if (r<0) { JOptionPane.showMessageDialog(this,"Chọn dòng!","Lỗi",JOptionPane.ERROR_MESSAGE); return; }
            String maTK = model.getValueAt(r,0).toString();
            if ("TK000".equals(maTK)) {
                JOptionPane.showMessageDialog(this,"Không được sửa Admin","Lỗi",JOptionPane.ERROR_MESSAGE);
                return;
            }
            ChinhSuaTaiKhoanDialog dlg = new ChinhSuaTaiKhoanDialog(SwingUtilities.getWindowAncestor(this));
            dlg.loadForEdit(maTK);
            dlg.setVisible(true);
            if (dlg.isSaved()) reload();
        });
        btnXoa.addActionListener(e -> {
            int r = tbl.getSelectedRow();
            if (r<0) { JOptionPane.showMessageDialog(this,"Chọn dòng!","Lỗi",JOptionPane.ERROR_MESSAGE); return; }
            String maTK = model.getValueAt(r,0).toString();
            if ("TK000".equals(maTK)) {
                JOptionPane.showMessageDialog(this,"Không được xóa Admin","Lỗi",JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (JOptionPane.showConfirmDialog(this,"Xóa "+maTK+"?","Xác nhận",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
                try{ bll.xoa(maTK); reload(); }
                catch(Exception ex){ JOptionPane.showMessageDialog(this,ex.getMessage(),"Lỗi",JOptionPane.ERROR_MESSAGE); }
            }
        });
        btnLamMoi.addActionListener(e -> reload());
    }

    private void reload() {
        model.setRowCount(0);
        List<TaiKhoan> ds = bll.layTatCa();
        for (TaiKhoan t : ds) {
            model.addRow(new Object[]{
                t.getMaTaiKhoan(),
                t.getMaNhanVien(),
                t.getUsername(),
                t.getPassword(),
                t.getVaiTro()
            });
        }
    }
}
