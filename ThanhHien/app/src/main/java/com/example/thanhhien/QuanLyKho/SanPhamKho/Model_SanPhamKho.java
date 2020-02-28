package com.example.thanhhien.QuanLyKho.SanPhamKho;

public class Model_SanPhamKho {
    String MaSanPham,TenSanPham,GiaSanPham,SoLuongSanPham,NhaCungCap,DonViTinh,MaChiTietSanPham,SoLuongBanDau;

    public Model_SanPhamKho() {
    }

    public Model_SanPhamKho(String maSanPham, String tenSanPham, String giaSanPham, String soLuongSanPham, String nhaCungCap, String donViTinh) {
        MaSanPham = maSanPham;
        TenSanPham = tenSanPham;
        GiaSanPham = giaSanPham;
        SoLuongSanPham = soLuongSanPham;
        NhaCungCap = nhaCungCap;
        DonViTinh = donViTinh;
    }

    public Model_SanPhamKho(String maSanPham, String tenSanPham, String giaSanPham, String soLuongSanPham, String nhaCungCap, String donViTinh, String maChiTietSanPham) {
        MaSanPham = maSanPham;
        TenSanPham = tenSanPham;
        GiaSanPham = giaSanPham;
        SoLuongSanPham = soLuongSanPham;
        NhaCungCap = nhaCungCap;
        DonViTinh = donViTinh;
        MaChiTietSanPham = maChiTietSanPham;
    }

    public String getSoLuongBanDau() {
        return SoLuongBanDau;
    }

    public void setSoLuongBanDau(String soLuongBanDau) {
        SoLuongBanDau = soLuongBanDau;
    }

    public String getMaChiTietSanPham() {
        return MaChiTietSanPham;
    }

    public void setMaChiTietSanPham(String maChiTietSanPham) {
        MaChiTietSanPham = maChiTietSanPham;
    }

    public String getMaSanPham() {
        return MaSanPham;
    }

    public void setMaSanPham(String maSanPham) {
        MaSanPham = maSanPham;
    }

    public String getTenSanPham() {
        return TenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        TenSanPham = tenSanPham;
    }

    public String getGiaSanPham() {
        return GiaSanPham;
    }

    public void setGiaSanPham(String giaSanPham) {
        GiaSanPham = giaSanPham;
    }

    public String getSoLuongSanPham() {
        return SoLuongSanPham;
    }

    public void setSoLuongSanPham(String soLuongSanPham) {
        SoLuongSanPham = soLuongSanPham;
    }

    public String getNhaCungCap() {
        return NhaCungCap;
    }

    public void setNhaCungCap(String nhaCungCap) {
        NhaCungCap = nhaCungCap;
    }

    public String getDonViTinh() {
        return DonViTinh;
    }

    public void setDonViTinh(String donViTinh) {
        DonViTinh = donViTinh;
    }
}
