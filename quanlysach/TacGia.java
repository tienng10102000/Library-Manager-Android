package com.example.quanlysach;

public class TacGia {
    String maTacGia;
    String tenTacGia;

    public TacGia (){
    }

    public TacGia(String ma, String ten){
        this.maTacGia = ma;
        this.tenTacGia = ten;
    }

    public String getMaTacGia() {
        return maTacGia;
    }

    public String getTenTacGia() {
        return tenTacGia;
    }

    public void setMaTacGia(String maTacGia) {
        this.maTacGia = maTacGia;
    }

    public void setTenTacGia(String tenTacGia) {
        this.tenTacGia = tenTacGia;
    }
    @Override
    public String toString() {
        return tenTacGia;
    }
}
