package DTO;

public class TaiKhoan {
    private final String maTaiKhoan;
    private final String maNhanVien;
    private final String username;
    private final String password;
    private final String vaiTro;

    public TaiKhoan(String maTaiKhoan, String maNhanVien, String username, String password, String vaiTro) {
        this.maTaiKhoan  = maTaiKhoan;
        this.maNhanVien  = maNhanVien;
        this.username    = username;
        this.password    = password;
        this.vaiTro      = vaiTro;
    }

    public String getMaTaiKhoan() { return maTaiKhoan; }
    public String getMaNhanVien() { return maNhanVien; }
    public String getUsername()   { return username; }
    public String getPassword()   { return password; }
    public String getVaiTro()     { return vaiTro; }
}
