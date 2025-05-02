package GUI.Component;

import GUI.LoginFrame;
import GUI.Main;
import GUI.Panel.TrangChu;
import GUI.Panel.BanQuatPanel;
import GUI.Panel.QuatPanel;
import GUI.Panel.QuanLyThuocTinhSP;
import GUI.Panel.HoaDonPanel;
import GUI.Panel.KhuyenMaiPanel;
import GUI.Panel.KhachHangPanel;
import GUI.Panel.NhaCungCapPanel;
import GUI.Panel.PhieuNhapPanel;
import GUI.Panel.PhieuXuatPanel;
import GUI.Panel.NhanVienPanel;
import GUI.Panel.TaiKhoanPanel;
import GUI.Panel.PhanQuyenPanel;
import GUI.Panel.ThongKePanel;

import javax.swing.*;
import javax.swing.SwingConstants;
import java.awt.*;
import java.awt.event.ActionListener;

public class MenuTaskbar extends JPanel {
    private final Main mainFrame;

    public MenuTaskbar(Main main) {
        this.mainFrame = main;
        initComponent();
    }

    private void initComponent() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // User info panel
        JPanel userPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        userPanel.setBackground(Color.WHITE);
        JLabel lblUserName = new JLabel("Nhân viên: Lê Nguyễn Nhất Tâm");
        lblUserName.setFont(lblUserName.getFont().deriveFont(Font.BOLD, 14f));
        userPanel.add(lblUserName);
        add(userPanel, BorderLayout.NORTH);

        // Menu buttons
        JPanel menuPanel = new JPanel(new GridLayout(0, 1, 0, 5));
        menuPanel.setBackground(Color.WHITE);

        addMenuButton(menuPanel, "Trang chủ", e -> mainFrame.setPanel(new TrangChu()));
        addMenuButton(menuPanel, "Bán quạt", e -> mainFrame.setPanel(new BanQuatPanel()));
        addMenuButton(menuPanel, "Quản lý quạt", e -> mainFrame.setPanel(new QuatPanel()));
        addMenuButton(menuPanel, "Quản lý thuộc tính", e -> mainFrame.setPanel(new QuanLyThuocTinhSP(mainFrame)));
        addMenuButton(menuPanel, "Hóa đơn", e -> mainFrame.setPanel(new HoaDonPanel()));
        addMenuButton(menuPanel, "Khuyến mãi", e -> mainFrame.setPanel(new KhuyenMaiPanel()));
        addMenuButton(menuPanel, "Khách hàng", e -> mainFrame.setPanel(new KhachHangPanel()));
        addMenuButton(menuPanel, "Phiếu nhập", e -> mainFrame.setPanel(new PhieuNhapPanel()));
        addMenuButton(menuPanel, "Phiếu xuất", e -> mainFrame.setPanel(new PhieuXuatPanel()));
        addMenuButton(menuPanel, "Nhà cung cấp", e -> mainFrame.setPanel(new NhaCungCapPanel()));
        addMenuButton(menuPanel, "Nhân viên", e -> mainFrame.setPanel(new NhanVienPanel()));
        addMenuButton(menuPanel, "Tài khoản", e -> mainFrame.setPanel(new TaiKhoanPanel()));
        addMenuButton(menuPanel, "Phân quyền", e -> mainFrame.setPanel(new PhanQuyenPanel()));
        addMenuButton(menuPanel, "Thống kê", e -> mainFrame.setPanel(new ThongKePanel()));

        // Logout button
        JButton btnDangXuat = new JButton("Đăng xuất");
        btnDangXuat.setHorizontalAlignment(SwingConstants.LEFT);
        btnDangXuat.setForeground(Color.RED);
        btnDangXuat.addActionListener(e -> {
            int choice = JOptionPane.showConfirmDialog(
                this,
                "Bạn có chắc chắn muốn đăng xuất?",
                "Đăng xuất",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.INFORMATION_MESSAGE
            );
            if (choice == JOptionPane.OK_OPTION) {
                new LoginFrame().setVisible(true);
                mainFrame.dispose();
            }
        });
        menuPanel.add(btnDangXuat);

        add(menuPanel, BorderLayout.CENTER);
    }

    private void addMenuButton(JPanel parent, String text, ActionListener action) {
        JButton btn = new JButton(text);
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.addActionListener(action);
        parent.add(btn);
    }
}
