package GUI.Panel;

import Database.DBConnection;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.sql.*;

public class QuatPanel extends JPanel {
    private JTable table;
    private DefaultTableModel tableModel;

    public QuatPanel() {
        setLayout(new BorderLayout());

        tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Không cho phép chỉnh sửa ô
            }
        };

        tableModel.setColumnIdentifiers(new Object[]{
            "Mã Quạt", "Tên Quạt", "Giá", "Mã NSX", "Ngày Sản Xuất", "Chất liệu", "Thương Hiệu", "Mã Loại"
        });

        table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Chọn 1 dòng
        table.setRowSelectionAllowed(true);
        table.setColumnSelectionAllowed(false);

        // Thiết lập độ rộng cột theo phần trăm
        table.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                int tableWidth = table.getWidth();
                table.getColumnModel().getColumn(0).setPreferredWidth((int)(tableWidth * 0.08));  // MaQuat
                table.getColumnModel().getColumn(1).setPreferredWidth((int)(tableWidth * 0.25)); // TenQuat
                table.getColumnModel().getColumn(2).setPreferredWidth((int)(tableWidth * 0.10)); // Gia
                table.getColumnModel().getColumn(3).setPreferredWidth((int)(tableWidth * 0.10)); // MaNSX
                table.getColumnModel().getColumn(4).setPreferredWidth((int)(tableWidth * 0.12)); // NgaySanXuat
                table.getColumnModel().getColumn(5).setPreferredWidth((int)(tableWidth * 0.13)); // ChatLieu
                table.getColumnModel().getColumn(6).setPreferredWidth((int)(tableWidth * 0.12)); // ThuongHieu
                table.getColumnModel().getColumn(7).setPreferredWidth((int)(tableWidth * 0.10)); // MaLoaiSP
            }
        });

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        loadDataFromDatabase();
    }

    private void loadDataFromDatabase() {
        tableModel.setRowCount(0); // Xoá dữ liệu cũ

        try (Connection conn = MySQLConnect.getConnection()) {
            String query = "SELECT * FROM quat";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                String maQuat = rs.getString("MaQuat");
                String tenQuat = rs.getString("TenQuat");
                String gia = rs.getString("Gia");
                String maNSX = rs.getString("MaNSX");
                String ngaySX = rs.getString("NgaySanXuat");
                String chatLieu = rs.getString("ChatLieu");
                String thuongHieu = rs.getString("ThuongHieu");
                String maLoaiSP = rs.getString("MaLoaiSP");

                tableModel.addRow(new Object[]{
                    maQuat, tenQuat, gia, maNSX, ngaySX, chatLieu, thuongHieu, maLoaiSP
                });
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi tải dữ liệu từ CSDL!\n" + e.getMessage());
            e.printStackTrace();
        }
    }

}
