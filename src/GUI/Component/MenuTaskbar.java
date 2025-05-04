package GUI.Component;

import GUI.LoginFrame;
import GUI.ManagerMainFrame;
import GUI.EmployeeMainFrame;
import GUI.Panel.TrangChu;
import GUI.Panel.BanQuatPanel;
import GUI.Panel.QuatPanel;
import GUI.Panel.QuanLyThuocTinhSP;
import GUI.Panel.HoaDonPanel;
import GUI.Panel.KhuyenMaiPanel;
import GUI.Panel.KhachHangPanel;
import GUI.Panel.PhieuNhapPanel;
import GUI.Panel.PhieuXuatPanel;
import GUI.Panel.NhaCungCapPanel;
import GUI.Panel.NhanVienPanel;
import GUI.Panel.TaiKhoanPanel;
import GUI.Panel.PhanQuyenPanel;
import GUI.Panel.ThongKePanel;

import javax.swing.*;
import javax.swing.SwingConstants;
import java.awt.*;
import java.awt.event.ActionListener;

public class MenuTaskbar extends JPanel {
    private final JFrame parent;
    private final boolean isManager;

    /**
     * @param parent    cửa sổ chính để gọi setPanel(...)
     * @param isManager true = hiển thị đủ menu, false = chỉ home, bán quạt, nhập xuất phiếu
     */
    public MenuTaskbar(JFrame parent, boolean isManager) {
        this.parent    = parent;
        this.isManager = isManager;
        initComponent();
    }

    private void initComponent() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // Thông tin user
        JLabel lblUser = new JLabel(
            parent instanceof ManagerMainFrame
                ? "Nhân viên: Admin"
                : "Nhân viên: NV01"
        );
        lblUser.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(lblUser, BorderLayout.NORTH);

        // Panel chứa các nút
        JPanel menu = new JPanel(new GridLayout(0, 1, 0, 5));
        menu.setBackground(Color.WHITE);

        // Các chức năng chung cho cả Manager và Employee
        addButton(menu, "Trang chủ",    e -> swap(new TrangChu()));
        addButton(menu, "Sản phẩm ",     e -> swap(new BanQuatPanel()));
        addButton(menu, "Hóa đơn",             e -> swap(new HoaDonPanel()));
        addButton(menu, "Khách hàng",          e -> swap(new KhachHangPanel()));
        addButton(menu, "Phiếu nhập",   e -> swap(new PhieuNhapPanel()));
        addButton(menu, "Phiếu xuất",   e -> swap(new PhieuXuatPanel()));

        // Chỉ manager mới có các mục dưới đây
        if (isManager) {
            addButton(menu, "Quản lý quạt",        e -> swap(new QuatPanel()));
            addButton(menu, "Quản lý thuộc tính",  e -> swap(new QuanLyThuocTinhSP((ManagerMainFrame) parent)));
            addButton(menu, "Hóa đơn",             e -> swap(new HoaDonPanel()));
            addButton(menu, "Khuyến mãi",          e -> swap(new KhuyenMaiPanel()));
            addButton(menu, "Khách hàng",          e -> swap(new KhachHangPanel()));
            addButton(menu, "Nhà cung cấp",        e -> swap(new NhaCungCapPanel()));
            addButton(menu, "Nhân viên",           e -> swap(new NhanVienPanel()));
            addButton(menu, "Tài khoản",           e -> swap(new TaiKhoanPanel()));
            addButton(menu, "Phân quyền",          e -> swap(new PhanQuyenPanel()));
            addButton(menu, "Thống kê",            e -> swap(new ThongKePanel()));
        }

        // Nút đăng xuất luôn có
        JButton btnLogout = new JButton("Đăng xuất");
        btnLogout.setHorizontalAlignment(SwingConstants.LEFT);
        btnLogout.setForeground(Color.RED);
        btnLogout.addActionListener(e -> {
            parent.dispose();
            new LoginFrame().setVisible(true);
        });
        menu.add(btnLogout);

        add(menu, BorderLayout.CENTER);
    }

    /**
     * Tiện ích thêm nút vào panel
     */
    private void addButton(JPanel menu, String text, ActionListener action) {
        JButton btn = new JButton(text);
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.addActionListener(action);
        menu.add(btn);
    }

    /**
     * Chuyển panel chính của cửa sổ
     */
    private void swap(JPanel panel) {
        if (parent instanceof ManagerMainFrame) {
            ((ManagerMainFrame) parent).setPanel(panel);
        } else if (parent instanceof EmployeeMainFrame) {
            ((EmployeeMainFrame) parent).setPanel(panel);
        }
    }
}
