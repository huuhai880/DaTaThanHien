package com.example.thanhhien.NhapXuatHang;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.thanhhien.BanHang.Seo_BanHangLe.Seo_BanHangLe;
import com.example.thanhhien.NhaCungcap.Model_NhaCungCap;
import com.example.thanhhien.R;

import java.util.ArrayList;

public class Seo_ChonNhaCungCap extends AppCompatActivity {
    private Toolbar toolbar;
    private GridView gridViewListNhaCungCap;
    private  Adapter_NhaCungCap adapter_nhaCungCap;
    private ArrayList<Model_NhaCungCap> mListNhaCungCap;
    private Button btnNhapHangNhoLe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.seo_chonnguyenlieu);
        setTitle("Chọn nhà cung cấp");
        AnhXa();
        mListNhaCungCap=new ArrayList<>();
        mListNhaCungCap.add(new Model_NhaCungCap("01","Nhà cung cấp Seo Boss"));
        mListNhaCungCap.add(new Model_NhaCungCap("01","Nhà cung cấp nào đó"));
        mListNhaCungCap.add(new Model_NhaCungCap("01","Nhà cung cấp Seo 1"));
        mListNhaCungCap.add(new Model_NhaCungCap("01","Nhà cung cấp Seo 2"));
        mListNhaCungCap.add(new Model_NhaCungCap("01","Nhà cung cấp Seo 3"));
        mListNhaCungCap.add(new Model_NhaCungCap("01","Nhà cung cấp Seo 4"));
        mListNhaCungCap.add(new Model_NhaCungCap("01","Nhà cung cấp Seo 5"));
        mListNhaCungCap.add(new Model_NhaCungCap("01","Nhà cung cấp Seo 6"));
        mListNhaCungCap.add(new Model_NhaCungCap("01","Nhà cung cấp Seo 7"));
        mListNhaCungCap.add(new Model_NhaCungCap("01","Nhà cung cấp Seo 8"));
        adapter_nhaCungCap=new Adapter_NhaCungCap(Seo_ChonNhaCungCap.this,mListNhaCungCap);
        gridViewListNhaCungCap.setAdapter(adapter_nhaCungCap);

        gridViewListNhaCungCap.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(Seo_ChonNhaCungCap.this, Seo_GiaoDienNhapXuatHang.class);
                startActivity(intent);
            }
        });
         btnNhapHangNhoLe.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent=new Intent(Seo_ChonNhaCungCap.this,Seo_NhapHangNhoLe.class);
                 startActivity(intent);
             }
         });

    }
    private void AnhXa() {
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        gridViewListNhaCungCap=findViewById(R.id.gridViewListNhaCungCap);
        btnNhapHangNhoLe=findViewById(R.id.btnNhapHangNhoLe);
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
