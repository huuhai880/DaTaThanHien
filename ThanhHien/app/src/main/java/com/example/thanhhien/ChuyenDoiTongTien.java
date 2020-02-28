package com.example.thanhhien;

import java.text.DecimalFormat;

public class ChuyenDoiTongTien {
    public static String priceWithoutDecimal (Double price) {
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        return formatter.format(price);
    }





}
