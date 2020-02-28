package com.example.thanhhien.BanHang.Seo_SanPhamDaChon;

public class Model_ListSanPhamBan {
    String MaSanPham,TenSanPham,SoLuong,GiaSanPham,ThuocTinh,NhaCungCap,MaChiTietSanPham,DonViTinh;

    public Model_ListSanPhamBan() {
    }

    public Model_ListSanPhamBan(String maSanPham, String tenSanPham, String soLuong, String giaSanPham, String thuocTinh, String nhaCungCap, String maChiTietSanPham, String donViTinh) {
        MaSanPham = maSanPham;
        TenSanPham = tenSanPham;
        SoLuong = soLuong;
        GiaSanPham = giaSanPham;
        ThuocTinh = thuocTinh;
        NhaCungCap = nhaCungCap;
        MaChiTietSanPham = maChiTietSanPham;
        DonViTinh = donViTinh;
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

    public String getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(String soLuong) {
        SoLuong = soLuong;
    }

    public String getGiaSanPham() {
        return GiaSanPham;
    }

    public void setGiaSanPham(String giaSanPham) {
        GiaSanPham = giaSanPham;
    }


    public String getThuocTinh() {
        return ThuocTinh;
    }

    public void setThuocTinh(String thuocTinh) {
        ThuocTinh = thuocTinh;
    }

    public String getNhaCungCap() {
        return NhaCungCap;
    }

    public void setNhaCungCap(String nhaCungCap) {
        NhaCungCap = nhaCungCap;
    }

    public String getMaChiTietSanPham() {
        return MaChiTietSanPham;
    }

    public void setMaChiTietSanPham(String maChiTietSanPham) {
        MaChiTietSanPham = maChiTietSanPham;
    }

    public String getDonViTinh() {
        return DonViTinh;
    }

    public void setDonViTinh(String donViTinh) {
        DonViTinh = donViTinh;
    }
}
