package com.example.thanhhien.QuanLyKho.SanPhamKho;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.thanhhien.Api_custom;
import com.example.thanhhien.MainActivity;
import com.example.thanhhien.NhapXuatHang.Seo_GiaoDienNhapXuatHang;
import com.example.thanhhien.QuanLyKho.ThemSuaXoaSanPham.Seo_ThemSuaXoaSanPham;
import com.example.thanhhien.R;
import com.github.clans.fab.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Seo_QuanLySanPhamKho extends AppCompatActivity {
    private Toolbar toolbar;
    private FloatingActionButton btnNhapHang,btnXuatHang;
    private EditText edit_TimKiem;
    private RecyclerView recyclerViewListSanPham;
    private ArrayList<Model_SanPhamKho> sanPhamArrayList;
    private Adapter_SanPhamKho adapter_sanPhamKho;
    public static String maKho="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.seo_quanlykho);
        final Intent intent=getIntent();
        maKho=intent.getStringExtra("MaKho");
        setTitle(intent.getStringExtra("TenKho"));
        AnhXa();
        onClick();
        sanPhamArrayList=new ArrayList<>();
        sanPhamArrayList.add(new Model_SanPhamKho("sfsdf","","kskks","jsjs","kdksk","jdjs"));
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewListSanPham.setLayoutManager(layoutManager);
        adapter_sanPhamKho=new Adapter_SanPhamKho(Seo_QuanLySanPhamKho.this,sanPhamArrayList);
        adapter_sanPhamKho.setOnItemClickedListener(new Adapter_SanPhamKho.OnItemClickedListener() {
            @Override
            public void onItemClick(String MaSanPham) {
                Toast.makeText(Seo_QuanLySanPhamKho.this, "Chuyển đến thông tin sản phẩm", Toast.LENGTH_SHORT).show();
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                Intent intent1=new Intent(Seo_QuanLySanPhamKho.this,Seo_ChiTietSanPham.class);
                startActivity(intent1);

            }
        });


    }
    private void onClick() {
        btnNhapHang.setOnClickListener(new View.OnClickListener() {// sự kiện onclick nhập hàng
            @Override
            public void onClick(View v) {
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                Intent intent=new Intent(Seo_QuanLySanPhamKho.this, Seo_GiaoDienNhapXuatHang.class);
                intent.putExtra("Id","0");
                startActivity(intent);
                MainActivity.listSanPhamDaChon.clear();
            }
        });

        btnXuatHang.setOnClickListener(new View.OnClickListener() {// sự kiện onclik xuất hàng
            @Override
            public void onClick(View v) {
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                Intent intent=new Intent(Seo_QuanLySanPhamKho.this, Seo_GiaoDienNhapXuatHang.class);
                intent.putExtra("Id","1");
                startActivity(intent);
                MainActivity.listSanPhamDaChon.clear();
            }
        });
        edit_TimKiem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter_sanPhamKho.getFilter().filter((edit_TimKiem.getText().toString().trim()));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void AnhXa() {
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        btnNhapHang=findViewById(R.id.btnNhapHang);
        btnXuatHang=findViewById(R.id.btnXuatHang);
        edit_TimKiem=findViewById(R.id.edit_TimKiem);
        recyclerViewListSanPham=findViewById(R.id.recyclerViewListSanPham);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                onBackPressed();
                return true;
            case R.id.nav_themsanpham:
                Intent intent=new Intent(Seo_QuanLySanPhamKho.this, Seo_ThemSuaXoaSanPham.class);
                intent.putExtra("Ma","1");
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                return true;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        sanPhamArrayList.clear();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_themsanpham,menu);
        return super.onCreateOptionsMenu(menu);
    }

}
