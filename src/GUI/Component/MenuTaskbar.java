package GUI.Component;

import GUI.LoginFrame;
import GUI.ManagerMainFrame;
import GUI.EmployeeMainFrame;
import GUI.Panel.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MenuTaskbar extends JPanel {

    private final JFrame parent;
    private final boolean isManager;
    private final String maNhanVien;

    public MenuTaskbar(JFrame parent, boolean isManager, String maNhanVien) {
        this.parent = parent;
        this.isManager = isManager;
        this.maNhanVien = maNhanVien;
        initComponent();
    }
    
    // Constructor original para mantener compatibilidad
    public MenuTaskbar(JFrame parent, boolean isManager) {
        this(parent, isManager, null);
    }

    private void initComponent() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        JPanel menu = new JPanel(new GridLayout(0, 1, 0, 5));
        menu.setBackground(Color.WHITE);

        // Các chức năng chung
        addButton(menu, "Trang chủ", e -> swap(new TrangChuPanel()));
        
     
        
        addButton(menu, "Bán quạt", e -> {
            String maNV = null;

            if (parent instanceof EmployeeMainFrame) {
                maNV = ((EmployeeMainFrame) parent).getMaNhanVien();
            } else if (parent instanceof ManagerMainFrame) {
                maNV = ((ManagerMainFrame) parent).getMaNhanVien();
            }

            if (maNV != null) {
                swap(new BanQuatPanel(maNV));
            } else {
                JOptionPane.showMessageDialog(this, "Không xác định được mã nhân viên.");
            }
        });

        addButton(menu, "Quản lý quạt", e -> swap(new QuatPanel()));
        addButton(menu, "Hóa đơn", e -> swap(new HoaDonPanel()));
        addButton(menu, "Phiếu nhập", e -> swap(new PhieuNhapPanel()));
        addButton(menu, "Phiếu xuất", e -> swap(new PhieuXuatPanel()));
        addButton(menu, "Loại sản phẩm", e -> swap(new LoaiSanPhamPanel()));

        // Chức năng riêng cho quản lý
        if (isManager) {
            addButton(menu, "Khuyến mãi", e -> swap(new KhuyenMaiPanel()));
            addButton(menu, "Khách hàng", e -> swap(new KhachHangPanel()));
            addButton(menu, "Nhà cung cấp", e -> swap(new NhaCungCapPanel()));
            addButton(menu, "Nhân viên", e -> swap(new NhanVienPanel()));
            addButton(menu, "Tài khoản", e -> swap(new TaiKhoanPanel(false))); //mặc định mở từ manager
            addButton(menu, "Thống kê", e -> swap(new ThongKePanel()));
        }
        
        // Thêm chức năng xem thông tin cá nhân
        if (maNhanVien != null) {
            addButton(menu, "Thông tin cá nhân", e -> swap(new ThongTinCaNhanPanel(maNhanVien)));
        }

        // Nút đăng xuất
        JButton btnLogout = new JButton("Đăng xuất");
        btnLogout.setHorizontalAlignment(SwingConstants.LEFT);
        btnLogout.setForeground(Color.RED);
        btnLogout.addActionListener(e -> {
            parent.dispose();
            new LoginFrame().setVisible(true);
        });
        menu.add(btnLogout);

        add(menu, BorderLayout.CENTER);

        for (Component comp : menu.getComponents()) {
            if (comp instanceof JButton) {
                comp.setFont(new Font("Arial", Font.PLAIN, 14));
            }
        }
    }

    // Phiên bản không có icon
    private void addButton(JPanel menu, String text, ActionListener action) {
        JButton btn = new JButton(text);
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.addActionListener(action);
        menu.add(btn);
    }

    private void swap(JPanel panel) {
        if (parent instanceof ManagerMainFrame) {
            ((ManagerMainFrame) parent).setPanel(panel);
        } else if (parent instanceof EmployeeMainFrame) {
            ((EmployeeMainFrame) parent).setPanel(panel);
        }
    }
}