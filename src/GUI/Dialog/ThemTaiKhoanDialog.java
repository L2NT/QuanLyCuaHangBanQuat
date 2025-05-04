package GUI.Dialog;

import BLL.TaiKhoanBLL;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ThemTaiKhoanDialog extends JDialog {
    private final JComboBox<String> cbbNV;
    private final JTextField txtUser, txtPass;
    private final JComboBox<String> cbbRole;
    private boolean saved = false;

    public ThemTaiKhoanDialog(Window owner) {
        super(owner,"Thêm tài khoản",ModalityType.APPLICATION_MODAL);
        JPanel p = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx=0; gbc.gridy=0; p.add(new JLabel("Nhân viên:"),gbc);
        gbc.gridx=1;
        cbbNV = new JComboBox<>();
        new TaiKhoanBLL().layDanhSachNhanVienChuaCoTaiKhoan().forEach(cbbNV::addItem);
        p.add(cbbNV,gbc);

        gbc.gridy=1; gbc.gridx=0; p.add(new JLabel("Tên đăng nhập:"),gbc);
        gbc.gridx=1; txtUser=new JTextField(15); p.add(txtUser,gbc);

        gbc.gridy=2; gbc.gridx=0; p.add(new JLabel("Mật khẩu:"),gbc);
        gbc.gridx=1; txtPass=new JTextField(15); p.add(txtPass,gbc);

        gbc.gridy=3; gbc.gridx=0; p.add(new JLabel("Vai trò:"),gbc);
        gbc.gridx=1; cbbRole=new JComboBox<>(new String[]{"QuanLy","NhanVien"});
        p.add(cbbRole,gbc);

        gbc.gridy=4; gbc.gridx=0; gbc.gridwidth=2; gbc.anchor=GridBagConstraints.CENTER;
        JPanel btnP = new JPanel();
        JButton btnL=new JButton("Lưu"), btnH=new JButton("Hủy");
        btnP.add(btnL); btnP.add(btnH);
        p.add(btnP,gbc);

        getContentPane().add(p);
        pack(); setLocationRelativeTo(owner);

        btnH.addActionListener(e->dispose());
        btnL.addActionListener(e->{
            try {
                new TaiKhoanBLL().them(
                    txtUser.getText().trim(),
                    txtPass.getText().trim(),
                    (String)cbbRole.getSelectedItem(),
                    (String)cbbNV.getSelectedItem()
                );
                saved=true;
                dispose();
            } catch(Exception ex){
                JOptionPane.showMessageDialog(this,ex.getMessage(),"Lỗi",JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    public boolean isSaved(){ return saved; }
}
