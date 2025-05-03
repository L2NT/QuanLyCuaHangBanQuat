// ... import như trước
import GUI.AdminMainFrame;
import GUI.EmployeeMainFrame;
// ...

private void checkLogin() {
    String user = txtUsername.getText().trim();
    String pass = txtPassword.getPass().trim();
    if (user.isEmpty() || pass.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin",
                                      "Cảnh báo", JOptionPane.WARNING_MESSAGE);
        return;
    }

    String sql = "SELECT * FROM `tai khoan` WHERE TenTaiKhoan=? AND MatKhau=?";
    try (Connection conn = DBConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setString(1, user);
        ps.setString(2, pass);
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                String role = rs.getString("VaiTro");
                String maNV  = rs.getString("MaNhanVien"); // có thể null nếu admin

                if ("Admin".equalsIgnoreCase(role)) {
                    new AdminMainFrame().setVisible(true);
                } else {
                    new EmployeeMainFrame(maNV).setVisible(true);
                }
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Đăng nhập thất bại",
                                              "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Lỗi kết nối CSDL",
                                      "Lỗi", JOptionPane.ERROR_MESSAGE);
    }
}
