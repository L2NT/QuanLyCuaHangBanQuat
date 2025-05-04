package GUI.Panel;
import GUI.Dialog.ThemQuatDialog;
import Database.DBConnection;
import bll.QuatBLL;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import dto.Quat;
import GUI.Dialog.SuaQuatDialog;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

public class QuatPanel extends JPanel {
    private JTable table;
    private DefaultTableModel tableModel;

    public QuatPanel() {
        setBackground(Color.LIGHT_GRAY);
        setLayout(new BorderLayout());  

        add(createButtonPanel(), BorderLayout.NORTH);
        add(createTablePanel(), BorderLayout.CENTER); 

        loadDataFromDatabase();
    }

    private JPanel createButtonPanel() {
       JPanel toolbar = new JPanel(new BorderLayout());

        JPanel leftToolPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        JButton btnThem = new JButton("THÊM");
        JButton btnXoa = new JButton("XÓA");
        JButton btnSua = new JButton("SỬA");
        JButton btnExcel = new JButton("XUẤT EXCEL");
        leftToolPanel.add(btnThem);
        leftToolPanel.add(btnXoa);
        leftToolPanel.add(btnSua);
        leftToolPanel.add(btnExcel);

        JPanel rightToolPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 5));
        JComboBox<String> cbbFilter = new JComboBox<>(new String[]{"Tất cả", "Mã Quạt", "Tên Quạt", "Thương Hiệu"});
        JLabel lblSearch = new JLabel("Tìm kiếm:");
        JTextField txtSearch = new JTextField(15);
        JButton btnLamMoi = new JButton("LÀM MỚI");

        rightToolPanel.add(cbbFilter);
        rightToolPanel.add(lblSearch);
        rightToolPanel.add(txtSearch);
        rightToolPanel.add(btnLamMoi);

        toolbar.add(leftToolPanel, BorderLayout.WEST);
        toolbar.add(rightToolPanel, BorderLayout.EAST);

        btnThem.addActionListener(e -> {
            ThemQuatDialog dialog = new ThemQuatDialog((Window) SwingUtilities.getWindowAncestor(this)); // Khởi tạo dialog
            dialog.setVisible(true);
            
            if (dialog.isAdded()) {
                loadDataFromDatabase(); 
            }
        });
        
        
        btnXoa.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();

            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn một quạt để xóa.", "Thông báo", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa quạt này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (confirm != JOptionPane.YES_OPTION) {
                return;
            }
            String maQuat = table.getValueAt(selectedRow, 0).toString();
            QuatBLL bll = new QuatBLL();
            if (bll.xoa(maQuat)) {
                JOptionPane.showMessageDialog(this, "Xóa quạt thành công.", "Thành công", JOptionPane.INFORMATION_MESSAGE);
                loadDataFromDatabase();
            } else {
                JOptionPane.showMessageDialog(this, "Xóa quạt thất bại.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnSua.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn một quạt để sửa.", "Thông báo", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String maQuat = tableModel.getValueAt(selectedRow, 0).toString();
            String tenQuat = tableModel.getValueAt(selectedRow, 1).toString();
            int gia = Integer.parseInt(tableModel.getValueAt(selectedRow, 2).toString());
            int soLuongTon= Integer.parseInt(tableModel.getValueAt(selectedRow, 3).toString());
            String maNSX = tableModel.getValueAt(selectedRow, 4).toString();
            String ngaySanXuat = tableModel.getValueAt(selectedRow, 5).toString();
            String chatLieu = tableModel.getValueAt(selectedRow, 6).toString();
            String thuongHieu = tableModel.getValueAt(selectedRow, 7).toString();
            String maLoaiSP = tableModel.getValueAt(selectedRow, 8).toString();

            Quat quat = new Quat(maQuat, tenQuat, gia,soLuongTon, maNSX, ngaySanXuat, chatLieu, thuongHieu, maLoaiSP);
            SuaQuatDialog dialog = new SuaQuatDialog((Window) SwingUtilities.getWindowAncestor(this), quat);
            dialog.setVisible(true);

            // Chỉ load lại nếu có thay đổi
            if (dialog.isUpdated()) {
                loadDataFromDatabase();
            }
        });
        
        txtSearch.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                search();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                search();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                search();
            }

            private void search() {
                String keyword = txtSearch.getText().trim();
                String filter = cbbFilter.getSelectedItem().toString();
                QuatBLL quatBLL = new QuatBLL();

                List<Quat> results = new ArrayList<>();
                    if (keyword.isEmpty()) {
                   
                        results = quatBLL.layTatCa(); 
               } else {
                   switch (filter) {
                       case "Mã Quạt":
                           Quat q = quatBLL.timTheoMaQuat(keyword);
                           if (q != null) results.add(q);
                           break;
                       case "Tên Quạt":
                           results = quatBLL.timTheoTenQuat(keyword);
                           break;
                       case "Thương Hiệu":
                           results = quatBLL.timTheoThuongHieu(keyword);
                           break;
                      case "Tất cả":
                        Set<Quat> resultSet = new LinkedHashSet<>(); // Giữ thứ tự và tự loại trùng

                        Quat q1 = quatBLL.timTheoMaQuat(keyword);
                        if (q1 != null) resultSet.add(q1);

                        List<Quat> q2 = quatBLL.timTheoTenQuat(keyword);
                        List<Quat> q3 = quatBLL.timTheoThuongHieu(keyword);

                        resultSet.addAll(q2);
                        resultSet.addAll(q3);

                        results = new ArrayList<>(resultSet); // Chuyển về lại List nếu cần
                        break;

                   }
               }
                loadDataToTable(results);
            }
        });
        
        btnLamMoi.addActionListener(e -> {
            txtSearch.setText("");
            cbbFilter.setSelectedIndex(0); // Reset về "Tất cả"
            loadDataFromDatabase(); // Hàm load toàn bộ dữ liệu lên bảng
        });




        return toolbar;
    }
    

    private JScrollPane createTablePanel() {
     tableModel = new DefaultTableModel(new Object[] {
         "Mã Quạt", "Tên Quạt", "Giá","Số Lương Tồn", "Mã NSX", "Ngày Sản Xuất", "Chất liệu", "Thương Hiệu", "Mã Loại"
     }, 0) {
         @Override
         public boolean isCellEditable(int row, int column) {
             return false;
         }
     };

     table = new JTable(tableModel);
     setTableCellAlignment();
     table.setFont(new Font("Arial", Font.PLAIN, 14)); 
     table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
     table.setRowHeight(30);

     JTableHeader header = table.getTableHeader();
     header.setFont(new Font("Arial", Font.BOLD, 14)); 
     header.setPreferredSize(new Dimension(header.getPreferredSize().width, 50));

     table.addComponentListener(new java.awt.event.ComponentAdapter() {
         @Override
         public void componentResized(java.awt.event.ComponentEvent evt) {
             adjustTableColumnWidth();
         }
     });

     return new JScrollPane(table);
 }


    private void setTableCellAlignment() {
        DefaultTableCellRenderer cangiua = new DefaultTableCellRenderer();
        cangiua.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            if (i != 1) { 
                table.getColumnModel().getColumn(i).setCellRenderer(cangiua);
            }
        }
    }

    private void adjustTableColumnWidth() {
        int w = table.getWidth();
        table.getColumnModel().getColumn(0).setPreferredWidth((int)(w * 0.06));
        table.getColumnModel().getColumn(1).setPreferredWidth((int)(w * 0.25));
        table.getColumnModel().getColumn(2).setPreferredWidth((int)(w * 0.06));

        table.getColumnModel().getColumn(3).setPreferredWidth((int)(w * 0.10));
        table.getColumnModel().getColumn(4).setPreferredWidth((int)(w * 0.10));
        table.getColumnModel().getColumn(5).setPreferredWidth((int)(w * 0.10));
        table.getColumnModel().getColumn(6).setPreferredWidth((int)(w * 0.13));
        table.getColumnModel().getColumn(7).setPreferredWidth((int)(w * 0.12));
        table.getColumnModel().getColumn(8).setPreferredWidth((int)(w * 0.10));
    }

    private void loadDataToTable(List<Quat> list) {
        tableModel.setRowCount(0);  // Xóa dữ liệu cũ

        for (Quat q : list) {
            tableModel.addRow(new Object[]{
                q.getMaQuat(),
                q.getTenQuat(),
                q.getGia(),
                q.getSoLuongTon(),
                q.getMaNSX(),
                q.getNgaySanXuat(),
                q.getChatLieu(),
                q.getThuongHieu(),
                q.getMaLoaiSP()
            });
        }
    }

    private void loadDataFromDatabase() {
        tableModel.setRowCount(0); 

        String sql = "SELECT * FROM quat";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                tableModel.addRow(new Object[] {
                    rs.getString("MaQuat"),
                    rs.getString("TenQuat"),
                    rs.getInt("Gia"),
                    rs.getInt("soLuongTon"),
                    rs.getString("MaNSX"),
                    rs.getDate("NgaySanXuat"),
                    rs.getString("ChatLieu"),
                    rs.getString("ThuongHieu"),
                    rs.getString("MaLoaiSP")
                });
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(
                this,
                "Lỗi khi tải dữ liệu từ CSDL!\n" + e.getMessage(),
                "Lỗi", JOptionPane.ERROR_MESSAGE
            );
            e.printStackTrace();
        }
    }
}
