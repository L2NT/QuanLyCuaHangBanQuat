package GUI.Component;

import GUI.Log_In;
import GUI.Main;
import GUI.Panel.QuatPanel;
import GUI.Panel.TrangChu;
import GUI.Panel.PhieuNhapPanel;
import GUI.Panel.NhaCungCapPanel;
import GUI.Panel.PhieuXuatPanel;
import GUI.Panel.KhuyenMaiPanel;
import GUI.Panel.HoaDonPanel;
import GUI.Panel.BanQuatPanel;
import GUI.Panel.QuanLyThuocTinhSP;

// ...

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
        JLabel lblUserName = new JLabel("Nhân viên: Lê Nguyễn Nhất Tâm");
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

        
        // NÚT BÁN HÀNG CỦA NHÂN VIÊN
        JButton btnBanQuat = new JButton("Bán quạt");
        btnBanQuat.addActionListener(e -> {
            mainFrame.setPanel(new BanQuatPanel());
        });
        menuPanel.add(btnBanQuat);

        
        // Nút Quản lý quạt
        JButton btnQuanLyQuat = new JButton("Quản lý quạt");
        btnQuanLyQuat.setHorizontalAlignment(SwingConstants.LEFT);
        btnQuanLyQuat.addActionListener(e -> {
            mainFrame.setPanel(new QuatPanel());
        });
        menuPanel.add(btnQuanLyQuat);
        
        JButton btnQuanLyThuocTinh = new JButton("Quản lý thuộc tính");
        btnQuanLyThuocTinh.setHorizontalAlignment(SwingConstants.LEFT);
        btnQuanLyThuocTinh.addActionListener(e -> {
            mainFrame.setPanel(new QuanLyThuocTinhSP(mainFrame));
        });
menuPanel.add(btnQuanLyThuocTinh);
        
        // ...
        JButton btnHoaDon = new JButton("Hóa đơn");
        btnHoaDon.setHorizontalAlignment(SwingConstants.LEFT);
        btnHoaDon.addActionListener(e -> {
            mainFrame.setPanel(new HoaDonPanel());
        });
        menuPanel.add(btnHoaDon);
        
        // Nút Quản lý phiếu nhập
        
        JButton btnPhieuNhap = new JButton("Phiếu nhập");
        btnPhieuNhap.setHorizontalAlignment(SwingConstants.LEFT);
        btnPhieuNhap.addActionListener(e -> {
            mainFrame.setPanel(new PhieuNhapPanel());
        });
        menuPanel.add(btnPhieuNhap);
                
        
        // Nút Quản lý phiếu Xuất
        JButton btnPhieuXuat = new JButton("Phiếu xuất");
        btnPhieuXuat.setHorizontalAlignment(SwingConstants.LEFT);
        btnPhieuXuat.addActionListener(e -> {
            mainFrame.setPanel(new PhieuXuatPanel());
        });
        menuPanel.add(btnPhieuXuat);
        
        // Nút quản lý nhà cung cấp
        JButton btnNhaCungCap = new JButton("Nhà Cung Cấp");
        btnNhaCungCap.setHorizontalAlignment(SwingConstants.LEFT);
        btnNhaCungCap.addActionListener(e -> {
            mainFrame.setPanel(new NhaCungCapPanel());
        });
        menuPanel.add(btnNhaCungCap);
        
        // Nút quản lý khuyến mãi
        JButton btnKhuyenMai = new JButton("Khuyến mãi");
        btnKhuyenMai.setHorizontalAlignment(SwingConstants.LEFT);
        btnKhuyenMai.addActionListener(e -> {
            mainFrame.setPanel(new KhuyenMaiPanel());
        });
        menuPanel.add(btnKhuyenMai);

        // 🔹 **Nút Đăng xuất**
        JButton btnDangXuat = new JButton("Đăng xuất");
        btnDangXuat.setHorizontalAlignment(SwingConstants.LEFT);
        btnDangXuat.setForeground(Color.RED); // Màu đỏ để nổi bật
        btnDangXuat.addActionListener(e -> {
            int input = JOptionPane.showConfirmDialog(null,
                    "Bạn có chắc chắn muốn đăng xuất?", "Đăng xuất",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
            if (input == JOptionPane.OK_OPTION) {
                Log_In login = new Log_In();
                mainFrame.dispose(); // Đóng cửa sổ hiện tại
                login.setVisible(true); // Mở lại màn hình đăng nhập
            }
        });
        menuPanel.add(btnDangXuat);
        // Thêm các nút khác 
        // ...

        this.add(menuPanel, BorderLayout.CENTER);
        
        
    }
}
