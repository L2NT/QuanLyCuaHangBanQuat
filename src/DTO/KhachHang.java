package DTO;

public class KhachHang {
    private String maKhachHang;
    private String hoTenKH;
    private int sdtKH;
    private String diaChiKH;
    private Float tongTienDaMua;

    // Constructor mặc định
    public KhachHang() {
    }

    // Constructor đầy đủ
    public KhachHang(String maKhachHang, String hoTenKH, int sdtKH, String diaChiKH, Float tongTienDaMua) {
        this.maKhachHang = maKhachHang;
        this.hoTenKH = hoTenKH;
        this.sdtKH = sdtKH;
        this.diaChiKH = diaChiKH;
        this.tongTienDaMua = tongTienDaMua;
    }

    // Getter và Setter
    public String getMaKhachHang() {
        return maKhachHang;
    }

    public void setMaKhachHang(String maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    public String getHoTenKH() {
        return hoTenKH;
    }

    public void setHoTenKH(String hoTenKH) {
        this.hoTenKH = hoTenKH;
    }

    public int getSdtKH() {
        return sdtKH;
    }

    public void setSdtKH(int sdtKH) {
        this.sdtKH = sdtKH;
    }

    public String getDiaChiKH() {
        return diaChiKH;
    }

    public void setDiaChiKH(String diaChiKH) {
        this.diaChiKH = diaChiKH;
    }

    public Float getTongTienDaMua() {
        return tongTienDaMua;
    }

    public void setTongTienDaMua(Float tongTienDaMua) {
        this.tongTienDaMua = tongTienDaMua;
    }

    // toString() để in thông tin đối tượng
    @Override
    public String toString() {
        return "KhachHang{" +
                "maKhachHang='" + maKhachHang + '\'' +
                ", hoTenKH='" + hoTenKH + '\'' +
                ", sdtKH=" + sdtKH +
                ", diaChiKH='" + diaChiKH + '\'' +
                ", tongTienDaMua=" + tongTienDaMua +
                '}';
    }
}
