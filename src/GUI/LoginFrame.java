package GUI;

import Database.DBConnection;
import GUI.Component.InputForm;
import GUI.Dialog.QuenMatKhau;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginFrame extends JFrame implements KeyListener {

    private JPanel pnlMain, pnlLogIn;
    private JLabel lblImage, lblTitle, lblLoginBtn, lblForgot;
    private InputForm txtUsername, txtPassword;
    private final Color hoverColor = new Color(96, 125, 139);

    public LoginFrame() {
        initLookAndFeel();
        initComponent();
        // mặc định để test, có thể xóa
        txtUsername.setText("tranthingoc");
        txtPassword.setPass("ngoc2024");
    }

    private void initLookAndFeel() {
        FlatRobotoFont.install();
        FlatLaf.setPreferredFontFamily(FlatRobotoFont.FAMILY);
        FlatLaf.setPreferredLightFontFamily(FlatRobotoFont.FAMILY_LIGHT);
        FlatLaf.setPreferredSemiboldFontFamily(FlatRobotoFont.FAMILY_SEMIBOLD);
        FlatIntelliJLaf.registerCustomDefaultsSource("style");
        FlatIntelliJLaf.setup();
        UIManager.put("PasswordField.showRevealButton", true);
    }

    private void initComponent() {
        setTitle("Đăng nhập");
        setSize(1000, 500);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        imgIntro();

        // Panel chứa form
        pnlMain = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 10));
        pnlMain.setBackground(Color.WHITE);
        pnlMain.setBorder(new EmptyBorder(20, 0, 0, 0));
        pnlMain.setPreferredSize(new Dimension(500, 500));

        lblTitle = new JLabel("ĐĂNG NHẬP VÀO HỆ THỐNG");
        lblTitle.setFont(new Font(FlatRobotoFont.FAMILY_SEMIBOLD, Font.BOLD, 20));
        pnlMain.add(lblTitle);

        // Form đăng nhập
        JPanel formPanel = new JPanel(new GridLayout(2, 1, 0, 0));
        formPanel.setBackground(Color.white);
        formPanel.setPreferredSize(new Dimension(400, 200));

        txtUsername = new InputForm("Tên đăng nhập");
        txtPassword = new InputForm("Mật khẩu", "password");
        formPanel.add(txtUsername);
        formPanel.add(txtPassword);

        txtUsername.getTxtForm().addKeyListener(this);
        txtPassword.getTxtPass().addKeyListener(this);

        pnlMain.add(formPanel);

        // Nút Đăng nhập
        lblLoginBtn = new JLabel("ĐĂNG NHẬP", SwingConstants.CENTER);
        lblLoginBtn.setFont(new Font(FlatRobotoFont.FAMILY, Font.BOLD, 16));
        lblLoginBtn.setForeground(Color.WHITE);

        pnlLogIn = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 12));
        pnlLogIn.putClientProperty(FlatClientProperties.STYLE, "arc: 99");
        pnlLogIn.setBackground(Color.BLACK);
        pnlLogIn.setPreferredSize(new Dimension(380, 45));
        pnlLogIn.add(lblLoginBtn);
        pnlLogIn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                pnlLogIn.setBackground(hoverColor);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                pnlLogIn.setBackground(Color.BLACK);
            }
            @Override
            public void mousePressed(MouseEvent e) {
                checkLogin();
            }
        });
        pnlMain.add(pnlLogIn);

        // Link Quên mật khẩu
        lblForgot = new JLabel("Quên mật khẩu", SwingConstants.RIGHT);
        lblForgot.setFont(new Font(FlatRobotoFont.FAMILY, Font.ITALIC, 18));
        lblForgot.setPreferredSize(new Dimension(380, 30));
        lblForgot.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new QuenMatKhau(LoginFrame.this, true).setVisible(true);
            }
        });
        pnlMain.add(lblForgot);

        add(pnlMain, BorderLayout.EAST);
    }

    private void imgIntro() {
        JPanel imgPanel = new JPanel();
        imgPanel.setBackground(Color.WHITE);
        imgPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        imgPanel.setPreferredSize(new Dimension(500, 500));

        lblImage = new JLabel();
        ImageIcon icon = new ImageIcon(getClass().getResource("/img/login-image2.png"));
        Image img = icon.getImage().getScaledInstance(400, 400, Image.SCALE_SMOOTH);
        lblImage.setIcon(new ImageIcon(img));
        imgPanel.add(lblImage);

        add(imgPanel, BorderLayout.WEST);
    }

    private void checkLogin() {
    String user = txtUsername.getText().trim();
    String pass = txtPassword.getPass().trim();

    if (user.isEmpty() || pass.isEmpty()) {
        JOptionPane.showMessageDialog(this,
            "Vui lòng nhập đầy đủ thông tin","Cảnh báo",JOptionPane.WARNING_MESSAGE);
        return;
    }

    String sql = "SELECT * FROM taikhoan WHERE TenTaiKhoan=? AND MatKhau=?";
    try (Connection conn = DBConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setString(1, user);
        ps.setString(2, pass);
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                String role = rs.getString("VaiTro");   // đọc vai trò từ CSDL
                if ("Admin".equalsIgnoreCase(role)) {
                    new ManagerMainFrame().setVisible(true);
                } else {
                    new EmployeeMainFrame().setVisible(true);
                }
                dispose();
            } else {
                JOptionPane.showMessageDialog(this,
                    "Đăng nhập thất bại","Lỗi",JOptionPane.ERROR_MESSAGE);
            }
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this,
            "Lỗi kết nối CSDL","Lỗi",JOptionPane.ERROR_MESSAGE);
    }
}



    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            checkLogin();
        }
    }
    @Override public void keyTyped(KeyEvent e) { }
    @Override public void keyReleased(KeyEvent e) { }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginFrame().setVisible(true));
    }
}
