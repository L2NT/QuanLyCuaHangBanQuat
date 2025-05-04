package dto;

public class Quat {
    private String maQuat;
    private String tenQuat;
    private int gia;
    private String maNSX;
    private String ngaySanXuat;
    private String chatLieu;
    private String thuongHieu;
    private String maLoaiSP;

    public Quat() { }

    public Quat(String maQuat, String tenQuat, int gia, String maNSX,
                String ngaySanXuat, String chatLieu, String thuongHieu, String maLoaiSP) {
        this.maQuat = maQuat;
        this.tenQuat = tenQuat;
        this.gia = gia;
        this.maNSX = maNSX;
        this.ngaySanXuat = ngaySanXuat;
        this.chatLieu = chatLieu;
        this.thuongHieu = thuongHieu;
        this.maLoaiSP = maLoaiSP;
    }

    public String getMaQuat() {
        return maQuat;
    }

    public void setMaQuat(String maQuat) {
        this.maQuat = maQuat;
    }

    public String getTenQuat() {
        return tenQuat;
    }

    public void setTenQuat(String tenQuat) {
        this.tenQuat = tenQuat;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public String getMaNSX() {
        return maNSX;
    }

    public void setMaNSX(String maNSX) {
        this.maNSX = maNSX;
    }

    public String getNgaySanXuat() {
        return ngaySanXuat;
    }

    public void setNgaySanXuat(String ngaySanXuat) {
        this.ngaySanXuat = ngaySanXuat;
    }

    public String getChatLieu() {
        return chatLieu;
    }

    public void setChatLieu(String chatLieu) {
        this.chatLieu = chatLieu;
    }

    public String getThuongHieu() {
        return thuongHieu;
    }

    public void setThuongHieu(String thuongHieu) {
        this.thuongHieu = thuongHieu;
    }

    public String getMaLoaiSP() {
        return maLoaiSP;
    }

    public void setMaLoaiSP(String maLoaiSP) {
        this.maLoaiSP = maLoaiSP;
    }
}
