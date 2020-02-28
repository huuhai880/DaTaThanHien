package com.example.thanhhien;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.thanhhien.BanHang.Seo_BanHangLe.Seo_BanHangLe;
import com.example.thanhhien.HoaDon.Seo_QuanLyHoaDon;
import com.example.thanhhien.LichSuNhapXuat.Seo_LichSuNhapXuat;
import com.example.thanhhien.NhaCungcap.Seo_QuanLyNhaCungCap;
import com.example.thanhhien.NhapXuatHang.Seo_ChonNhaCungCap;
import com.example.thanhhien.QuanLyKho.GiaoDienQuanLyKho.Seo_GiaoDienKho;

import com.example.thanhhien.ThongKe.Seo_QuanLyThongKe;

import com.google.android.material.navigation.NavigationView;

import org.eazegraph.lib.charts.BarChart;
import org.eazegraph.lib.models.BarModel;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Seo_GiaoDienChinh extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private Toolbar toolbar;
    private LinearLayout btnBanHang,btnHoaDon,btnKhachHang,btnKho,btnThongKe,btnlichSuPhieuNhapXuat,btnNhaCungCap,btnThongKeNgay,btnNhapHang;
    private TextView txtTongTienTheoNgay;
    private int TongTien;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.seo_giaodienchinh);
        setTitle("Trang chủ");
        AnhXa();
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.imgmenudrawer);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                });
        onClick();
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        dateFormatter.setLenient(false);
        Date today = new Date();
        final String NgayHomNay = dateFormatter.format(today);


        // dữ liệu biểu đồ
        BarChart mBarChart = (BarChart) findViewById(R.id.barchart);
        mBarChart.addBar(new BarModel("T 2", 4000000,0xFF123456));
        mBarChart.addBar(new BarModel("T 3",5000000,0xFF343456));
        mBarChart.addBar(new BarModel("T 4", 2000000,0xFF563456));
        mBarChart.addBar(new BarModel("T 5", 4000000,0xFF873F56));
        mBarChart.addBar(new BarModel("T 6",2600000, 0xFF56B7F1));
        mBarChart.addBar(new BarModel("T 7",2000000,  0xFF343456));
        mBarChart.addBar(new BarModel("CN",6000000, 0xFF1FF4AC));
        mBarChart.startAnimation();
    }
    private void onClick() {
        btnBanHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TongTien=0;
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                Intent intent=new Intent(Seo_GiaoDienChinh.this, Seo_BanHangLe.class);
                startActivity(intent);
            }
        });
        btnHoaDon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TongTien=0;
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                Intent intent=new Intent(Seo_GiaoDienChinh.this, Seo_QuanLyHoaDon.class);
                startActivity(intent);
            }
        });
        btnNhaCungCap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TongTien=0;
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                Intent intent=new Intent(Seo_GiaoDienChinh.this, Seo_QuanLyNhaCungCap.class);
                startActivity(intent);
            }
        });
        btnKho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TongTien=0;
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                Intent intent=new Intent(Seo_GiaoDienChinh.this, Seo_GiaoDienKho.class);
                startActivity(intent);
            }
        });

        btnlichSuPhieuNhapXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TongTien=0;
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                Intent intent=new Intent(Seo_GiaoDienChinh.this, Seo_LichSuNhapXuat.class);
                startActivity(intent);
            }
        });
        btnThongKe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TongTien=0;
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                Intent intent=new Intent(Seo_GiaoDienChinh.this, Seo_QuanLyThongKe.class);
                startActivity(intent);
            }
        });
        btnThongKeNgay.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.ECLAIR)
            @Override
            public void onClick(View v) {
                TongTien=0;
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                Intent intent=new Intent(Seo_GiaoDienChinh.this, Seo_QuanLyThongKe.class);
                startActivity(intent);
            }
        });

        btnNhapHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Seo_GiaoDienChinh.this, Seo_ChonNhaCungCap.class);
                startActivity(intent);
            }
        });

    }

    private void AnhXa() {
        mDrawerLayout=findViewById(R.id.drawer_layout);
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        txtTongTienTheoNgay=findViewById(R.id.txtTongTienTheoNgay);
        btnBanHang=findViewById(R.id.btnBanHang);
        btnHoaDon=findViewById(R.id.btnHoaDon);
        btnKhachHang=findViewById(R.id.btnKhachHang);
        btnKho=findViewById(R.id.btnKho);
        btnThongKe=findViewById(R.id.btnKho);
        btnlichSuPhieuNhapXuat=findViewById(R.id.btnlichSuPhieuNhapXuat);
        btnNhaCungCap=findViewById(R.id.btnNhaCungCap);
        btnThongKe=findViewById(R.id.btnThongKe);
        btnThongKeNgay=findViewById(R.id.btnThongKeNgay);
        btnNhapHang=findViewById(R.id.btnNhapHang);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;

            case R.id.nav_thongbao:
                Toast.makeText(this, "Trang thông báo", Toast.LENGTH_SHORT).show();
                break;
                default:
                    break;

        }
    return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_thongbao,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    protected void onRestart() {
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        dateFormatter.setLenient(false);
        Date today = new Date();
        final String NgayHomNay = dateFormatter.format(today);
        super.onRestart();
    }
}
