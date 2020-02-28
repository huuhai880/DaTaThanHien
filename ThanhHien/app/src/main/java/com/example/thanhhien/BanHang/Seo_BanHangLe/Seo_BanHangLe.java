package com.example.thanhhien.BanHang.Seo_BanHangLe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.thanhhien.BanHang.Seo_BanHangLe.ListSanPhamBanLe.Seo_ListSanPhamBanLe;
import com.example.thanhhien.ChuyenDoiTongTien;
import com.example.thanhhien.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class Seo_BanHangLe extends AppCompatActivity {
    private Toolbar toolbar;
    private GridView gridViewListSanPham;
    private Adapter_ListSanPhamBanLe adapter_listSanPhamBanLe;
    private ArrayList<Model_ListSanPhamBanLe> mListSanPhamBanLe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.seo_banhangle);
        AnhXa();
        mListSanPhamBanLe=new ArrayList<>();
        mListSanPhamBanLe.add(new Model_ListSanPhamBanLe("01","Sắt U"));
        mListSanPhamBanLe.add(new Model_ListSanPhamBanLe("01","Sắt V"));
        mListSanPhamBanLe.add(new Model_ListSanPhamBanLe("01","Sắt Ống"));
        mListSanPhamBanLe.add(new Model_ListSanPhamBanLe("01","Sắt Hộp"));
        mListSanPhamBanLe.add(new Model_ListSanPhamBanLe("01","Ống Kẽm"));
        mListSanPhamBanLe.add(new Model_ListSanPhamBanLe("01","Sắt Vuông"));
        mListSanPhamBanLe.add(new Model_ListSanPhamBanLe("01","Sắt I"));
        mListSanPhamBanLe.add(new Model_ListSanPhamBanLe("01","Tôn hoa sen"));
        adapter_listSanPhamBanLe=new Adapter_ListSanPhamBanLe(Seo_BanHangLe.this,R.layout.item_layoutsanphambanle,mListSanPhamBanLe);
        gridViewListSanPham.setAdapter(adapter_listSanPhamBanLe);
        gridViewListSanPham.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Model_ListSanPhamBanLe model_listSanPhamBanLe=mListSanPhamBanLe.get(position);
                        Intent intent=new Intent(Seo_BanHangLe.this, Seo_ListSanPhamBanLe.class);
                        intent.putExtra("MaSanPham",model_listSanPhamBanLe.getIDSanPham());
                        intent.putExtra("TenSanPham",model_listSanPhamBanLe.getTenSanPham());
                        startActivity(intent);

            }
        });
    }
    private void AnhXa() {
        toolbar=(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        gridViewListSanPham=(GridView) findViewById(R.id.gridViewListSanPham);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
            onBackPressed();
                return true;

            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
