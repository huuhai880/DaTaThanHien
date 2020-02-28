package com.example.thanhhien.QuanLyKho.GiaoDienQuanLyKho;

class Model_kho {
    String MaKho,TenKho,TinhTrang;


    public Model_kho(String maKho, String tenKho) {
        MaKho = maKho;
        TenKho = tenKho;
    }

    public String getMaKho() {
        return MaKho;
    }

    public void setMaKho(String maKho) {
        MaKho = maKho;
    }

    public String getTenKho() {
        return TenKho;
    }

    public void setTenKho(String tenKho) {
        TenKho = tenKho;
    }

    public String getTinhTrang() {
        return TinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        TinhTrang = tinhTrang;
    }
}
