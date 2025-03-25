package GUI.Component;

import GUI.Main;
import GUI.Panel.QuatPanel;
import GUI.Panel.TrangChu;
import GUI.Panel.PhieuNhapPanel;
import GUI.Panel.NhaCungCapPanel;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Thanh menu bên trái hiển thị các chức năng:
 *  - Trang chủ
 *  - Quản lý quạt
 *  - ... (thêm nút khác nếu muốn)
 */
public class MenuTaskbar extends JPanel {
    private Main mainFrame;

    public MenuTaskbar(Main main) {
        this.mainFrame = main;
        initComponent();
    }

    private void initComponent() {
        this.setLayout(new BorderLayout());
        this.setBackground(Color.WHITE);

        // Phần hiển thị thông tin user ở trên (ví dụ cứng)
        JPanel userPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        userPanel.setBackground(Color.WHITE);
        JLabel lblUserName = new JLabel("Nhân viên: Nguyễn Văn A");
        lblUserName.setFont(lblUserName.getFont().deriveFont(Font.BOLD, 14f));
        userPanel.add(lblUserName);

        this.add(userPanel, BorderLayout.NORTH);

        // Phần các nút chức năng
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(0, 1, 0, 5));
        menuPanel.setBackground(Color.WHITE);

        // Nút Trang chủ
        JButton btnTrangChu = new JButton("Trang chủ");
        btnTrangChu.setHorizontalAlignment(SwingConstants.LEFT);
        btnTrangChu.addActionListener(e -> {
            mainFrame.setPanel(new TrangChu());
        });
        menuPanel.add(btnTrangChu);

        // Nút Quản lý quạt
        JButton btnQuanLyQuat = new JButton("Quản lý quạt");
        btnQuanLyQuat.setHorizontalAlignment(SwingConstants.LEFT);
        btnQuanLyQuat.addActionListener(e -> {
            mainFrame.setPanel(new QuatPanel());
        });
        menuPanel.add(btnQuanLyQuat);
        
        JButton btnPhieuNhap = new JButton("Phiếu nhập");
        btnPhieuNhap.setHorizontalAlignment(SwingConstants.LEFT);
        btnPhieuNhap.addActionListener(e -> {
            mainFrame.setPanel(new PhieuNhapPanel());
        });
        menuPanel.add(btnPhieuNhap);
                
        JButton btnNhaCungCap = new JButton("Nhà Cung Cấp");
        btnNhaCungCap.setHorizontalAlignment(SwingConstants.LEFT);
        btnNhaCungCap.addActionListener(e -> {
            mainFrame.setPanel(new NhaCungCapPanel());
        });
        menuPanel.add(btnNhaCungCap);

        // Thêm các nút khác (phiếu nhập, phiếu xuất, khách hàng, hóa đơn, v.v.) nếu muốn
        // ...

        this.add(menuPanel, BorderLayout.CENTER);
        
        
    }
}
