package com.example.thanhhien.HoaDon;

public class Model_HoaDon {
    String MaHoaDon,TenKhachHang,NgayTao,TongTien,DaTra,No,NguoiTao,ThietBiTao;


    public Model_HoaDon(String maHoaDon, String tenKhachHang, String ngayTao, String tongTien, String daTra, String no, String nguoiTao, String thietBiTao) {
        MaHoaDon = maHoaDon;
        TenKhachHang = tenKhachHang;
        NgayTao = ngayTao;
        TongTien = tongTien;
        DaTra = daTra;
        No = no;
        NguoiTao = nguoiTao;
        ThietBiTao = thietBiTao;
    }

    public String getMaHoaDon() {
        return MaHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        MaHoaDon = maHoaDon;
    }

    public String getTenKhachHang() {
        return TenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        TenKhachHang = tenKhachHang;
    }

    public String getNgayTao() {
        return NgayTao;
    }

    public void setNgayTao(String ngayTao) {
        NgayTao = ngayTao;
    }

    public String getTongTien() {
        return TongTien;
    }

    public void setTongTien(String tongTien) {
        TongTien = tongTien;
    }

    public String getDaTra() {
        return DaTra;
    }

    public void setDaTra(String daTra) {
        DaTra = daTra;
    }

    public String getNo() {
        return No;
    }

    public void setNo(String no) {
        No = no;
    }

    public String getNguoiTao() {
        return NguoiTao;
    }

    public void setNguoiTao(String nguoiTao) {
        NguoiTao = nguoiTao;
    }

    public String getThietBiTao() {
        return ThietBiTao;
    }

    public void setThietBiTao(String thietBiTao) {
        ThietBiTao = thietBiTao;
    }
}
