package DTO;

public class TaiKhoan {
    private String maTaiKhoan;
    private String tenTaiKhoan;
    private String matKhau;
    private String vaiTro;
    private String maNhanVien; // có thể null nếu là Admin

    public TaiKhoan(String maTaiKhoan, String tenTaiKhoan, String matKhau, String vaiTro, String maNhanVien) {
        this.maTaiKhoan = maTaiKhoan;
        this.tenTaiKhoan = tenTaiKhoan;
        this.matKhau = matKhau;
        this.vaiTro = vaiTro;
        this.maNhanVien = maNhanVien;
    }

    public String getMaTaiKhoan() { return maTaiKhoan; }
    public String getTenTaiKhoan() { return tenTaiKhoan; }
    public String getMatKhau()      { return matKhau; }
    public String getVaiTro()       { return vaiTro; }
    public String getMaNhanVien()   { return maNhanVien; }
}
