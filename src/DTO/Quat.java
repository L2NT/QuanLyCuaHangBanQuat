
package DTO;

import java.util.Date;

public class Quat {
    private String maQuat;
    private String tenQuat;
    private int gia;
    private String maNSX;

    private Date ngaySanXuat;
    private String chatLieu;
    private String thuongHieu;
    private String maLoaiSP;


    public Quat() {
    }

    public Quat(String maQuat, String tenQuat, int gia, String maNSX, Date ngaySanXuat,
                String chatLieu, String thuongHieu, String maLoaiSP) {
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


    public Date getNgaySanXuat() {
        return ngaySanXuat;
    }

    public void setNgaySanXuat(Date ngaySanXuat) {
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

    @Override
    public String toString() {
        return "Quat{" +
                "maQuat='" + maQuat + '\'' +
                ", tenQuat='" + tenQuat + '\'' +
                ", gia=" + gia +
                ", maNSX='" + maNSX + '\'' +
                ", ngaySanXuat=" + ngaySanXuat +
                ", chatLieu='" + chatLieu + '\'' +
                ", thuongHieu='" + thuongHieu + '\'' +
                ", maLoaiSP='" + maLoaiSP + '\'' +
                '}';
    }
}
