package DTO;

public class TaiKhoan {
    private String maTK;
    private String tenTK;
    private String matKhau;
    private String vaiTro;
    private String maNhanVien;

    public TaiKhoan(String maTK, String tenTK, String matKhau, String vaiTro, String maNhanVien) {
        this.maTK         = maTK;
        this.tenTK        = tenTK;
        this.matKhau      = matKhau;
        this.vaiTro       = vaiTro;
        this.maNhanVien   = maNhanVien;
    }

    // getters / setters
    public String getMaTK()            { return maTK; }
    public void   setMaTK(String v)    { maTK = v; }
    public String getTenTK()           { return tenTK; }
    public void   setTenTK(String v)   { tenTK = v; }
    public String getMatKhau()         { return matKhau; }
    public void   setMatKhau(String v) { matKhau = v; }
    public String getVaiTro()          { return vaiTro; }
    public void   setVaiTro(String v)  { vaiTro = v; }
    public String getMaNhanVien()      { return maNhanVien; }
    public void   setMaNhanVien(String v) { maNhanVien = v; }
}
