package GUI.Dialog;

import BUS.TaiKhoanBUS;
import DTO.NhanVienDTO;
import BUS.NhanVienBUS;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

public class ThemTaiKhoanDialog extends JDialog {
    private final JComboBox<String> cbbNV;
    private final JTextField txtUser, txtPass;
    private final JComboBox<String> cbbQuyenHang;
    private boolean saved = false;
    private final NhanVienBUS nvBUS = new NhanVienBUS();

    public ThemTaiKhoanDialog(Window owner) {
        super(owner,"Thêm tài khoản",ModalityType.APPLICATION_MODAL);
        JPanel p = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx=0; gbc.gridy=0; p.add(new JLabel("Nhân viên:"),gbc);
        gbc.gridx=1;
        cbbNV = new JComboBox<>();
        new TaiKhoanBUS().layDanhSachNhanVienChuaCoTaiKhoan().forEach(cbbNV::addItem);
        p.add(cbbNV,gbc);

        gbc.gridy=1; gbc.gridx=0; p.add(new JLabel("Tên đăng nhập:"),gbc);
        gbc.gridx=1; txtUser=new JTextField(15); p.add(txtUser,gbc);

        gbc.gridy=2; gbc.gridx=0; p.add(new JLabel("Mật khẩu:"),gbc);
        gbc.gridx=1; txtPass=new JTextField(15); p.add(txtPass,gbc);

        gbc.gridy=3; gbc.gridx=0; p.add(new JLabel("Quyền hạng:"),gbc);
        gbc.gridx=1; cbbQuyenHang=new JComboBox<>(new String[]{"QuanLy","NhanVien"});
    
        p.add(cbbQuyenHang,gbc);

        gbc.gridy=4; gbc.gridx=0; gbc.gridwidth=2; gbc.anchor=GridBagConstraints.CENTER;
        JPanel btnP = new JPanel();
        JButton btnL=new JButton("Lưu"), btnH=new JButton("Hủy");
        btnP.add(btnL); btnP.add(btnH);
        p.add(btnP,gbc);

        getContentPane().add(p);
        pack(); setLocationRelativeTo(owner);

        // Sự kiện khi chọn Nhân viên
        cbbNV.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    // Cập nhật giá trị mặc định cho quyền hạng dựa trên chức vụ
                    setDefaultQuyenHang();
                }
            }
        });

        btnH.addActionListener(e->dispose());
        btnL.addActionListener(e->{
            try {
                new TaiKhoanBUS().them(
                    txtUser.getText().trim(),
                    txtPass.getText().trim(),
                    (String)cbbQuyenHang.getSelectedItem(), // Sử dụng quyền hạng đã chọn
                    (String)cbbNV.getSelectedItem()
                );
                saved=true;
                dispose();
            } catch(Exception ex){
                JOptionPane.showMessageDialog(this,ex.getMessage(),"Lỗi",JOptionPane.ERROR_MESSAGE);
            }
        });
        
        // Cập nhật quyền hạng mặc định khi hiển thị dialogg
        if (cbbNV.getItemCount() > 0) {
            setDefaultQuyenHang();
        }
    }
    
    // Phương thức cập nhật quyền hạng mặc định dựa trên chức vụ của nhân viên (trong db là ChucVu)
    private void setDefaultQuyenHang() {
        String maNV = (String)cbbNV.getSelectedItem();
        if (maNV != null && !maNV.isEmpty()) {
            // Truy vấn thông tin nhân viên từ BUS
            List<NhanVienDTO> listNV = nvBUS.layTatCa();
            for (NhanVienDTO nv : listNV) {
                if (nv.getMaNV().equals(maNV)) {
                    // Kiểm tra chức vụ và chọn đúng quyền hạng tương ứng
                    if ("Quản lý".equals(nv.getChucVu())) {
                        cbbQuyenHang.setSelectedItem("QuanLy");
                    } else {
                        cbbQuyenHang.setSelectedItem("NhanVien");
                    }
                    break;
                }
            }
        }
    }

    public boolean isSaved(){ return saved; }
}