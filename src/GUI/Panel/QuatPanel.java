package GUI.Panel;

import Database.DBConnection;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class QuatPanel extends JPanel {
    private JTable table;
    private DefaultTableModel tableModel;

    public QuatPanel() {
        setLayout(new BorderLayout());

        tableModel = new DefaultTableModel(new Object[]{
            "Mã Quạt", "Tên Quạt", "Giá", "Mã NSX", "Ngày Sản Xuất", "Chất liệu", "Thương Hiệu", "Mã Loại"
        }, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Không cho phép chỉnh sửa ô
            }
        };

        table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Thiết lập độ rộng cột theo phần trăm
        table.addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(java.awt.event.ComponentEvent evt) {
                int w = table.getWidth();
                table.getColumnModel().getColumn(0).setPreferredWidth((int)(w * 0.08));
                table.getColumnModel().getColumn(1).setPreferredWidth((int)(w * 0.25));
                table.getColumnModel().getColumn(2).setPreferredWidth((int)(w * 0.10));
                table.getColumnModel().getColumn(3).setPreferredWidth((int)(w * 0.10));
                table.getColumnModel().getColumn(4).setPreferredWidth((int)(w * 0.12));
                table.getColumnModel().getColumn(5).setPreferredWidth((int)(w * 0.13));
                table.getColumnModel().getColumn(6).setPreferredWidth((int)(w * 0.12));
                table.getColumnModel().getColumn(7).setPreferredWidth((int)(w * 0.10));
            }
        });

        add(new JScrollPane(table), BorderLayout.CENTER);

        loadDataFromDatabase();
    }

    private void loadDataFromDatabase() {
        tableModel.setRowCount(0);

        String sql = "SELECT * FROM quat";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                tableModel.addRow(new Object[]{
                    rs.getString("MaQuat"),
                    rs.getString("TenQuat"),
                    rs.getInt("Gia"),
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
