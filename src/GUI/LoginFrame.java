private void doLogin(ActionEvent e) {
    String user = txtUser.getText().trim();
    String pass = new String(txtPass.getPassword()).trim();
    String sql = "SELECT * FROM taikhoan WHERE username=? AND password=?";
    try (Connection c = DBConnection.getConnection();
         PreparedStatement ps = c.prepareStatement(sql)) {
        ps.setString(1, user);
        ps.setString(2, pass);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            // Đăng nhập thành công -> mở cửa sổ chính
            new Main().setVisible(true);
            dispose();  // đóng LoginFrame
        } else {
            JOptionPane.showMessageDialog(this, "Đăng nhập thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    } catch (Exception ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Lỗi kết nối", "Lỗi", JOptionPane.ERROR_MESSAGE);
    }
}
