package com.example.thanhhien.LichSuNhapXuat;

public class Model_LichSuNhapXuat {
    String TenNhaCungCap,IDPhieuNhap,NgayTao,GhiChu,TongTien,No;




    public Model_LichSuNhapXuat(String tenNhaCungCap, String IDPhieuNhap, String ngayTao, String ghiChu, String tongTien, String no) {
        TenNhaCungCap = tenNhaCungCap;
        this.IDPhieuNhap = IDPhieuNhap;
        NgayTao = ngayTao;
        GhiChu = ghiChu;
        TongTien = tongTien;
        No = no;
    }

    public String getIDPhieuNhap() {
        return IDPhieuNhap;
    }

    public void setIDPhieuNhap(String IDPhieuNhap) {
        this.IDPhieuNhap = IDPhieuNhap;
    }

    public String getNgayTao() {
        return NgayTao;
    }

    public void setNgayTao(String ngayTao) {
        NgayTao = ngayTao;
    }

    public String getGhiChu() {
        return GhiChu;
    }

    public void setGhiChu(String ghiChu) {
        GhiChu = ghiChu;
    }

    public String getTongTien() {
        return TongTien;
    }

    public void setTongTien(String tongTien) {
        TongTien = tongTien;
    }

    public String getNo() {
        return No;
    }

    public void setNo(String no) {
        No = no;
    }

    public String getTenNhaCungCap() {
        return TenNhaCungCap;
    }

    public void setTenNhaCungCap(String tenNhaCungCap) {
        TenNhaCungCap = tenNhaCungCap;
    }
}
