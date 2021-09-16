package com.example.quanlysach;

public class Sach {
    String maSach;
    String tenSach;
    String ngayXB;
    String maTacGia;

    public Sach(){}

    public Sach(String maSach, String tenSach, String ngayXB, String maTacGia ){
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.ngayXB = ngayXB;
        this.maTacGia = maTacGia;
    }

    public Sach(String maSach, String tenSach, String ngayXB ){
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.ngayXB = ngayXB;
    }

    public String getMaTacGia() {
        return maTacGia;
    }

    public String getMaSach() {
        return maSach;
    }

    public String getNgayXB() {
        return ngayXB;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setMaTacGia(String maTacGia) {
        this.maTacGia = maTacGia;
    }

    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }

    public void setNgayXB(String nhaXB) {
        this.ngayXB = ngayXB;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }
}
