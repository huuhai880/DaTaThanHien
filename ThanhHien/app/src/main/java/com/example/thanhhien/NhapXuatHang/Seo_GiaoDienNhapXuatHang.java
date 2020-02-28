package com.example.thanhhien.NhapXuatHang;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;
import com.example.thanhhien.BanHang.Seo_BanHangLe.Adapter_ListSanPhamBanLe;
import com.example.thanhhien.BanHang.Seo_BanHangLe.Model_ListSanPhamBanLe;
import com.example.thanhhien.R;
import java.util.ArrayList;
public class Seo_GiaoDienNhapXuatHang extends AppCompatActivity {
    private Toolbar toolbar;
    private GridView gridViewListSanPham;
    private Adapter_ListSanPhamBanLe adapter_listSanPhamBanLe;
    private ArrayList<Model_ListSanPhamBanLe> mListSanPhamBanLe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.seo_giaodiennhapxuathang);
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
        adapter_listSanPhamBanLe=new Adapter_ListSanPhamBanLe(Seo_GiaoDienNhapXuatHang.this,R.layout.item_layoutsanphambanle,mListSanPhamBanLe);
        gridViewListSanPham.setAdapter(adapter_listSanPhamBanLe);
        gridViewListSanPham.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(Seo_GiaoDienNhapXuatHang.this,Seo_ListSanPhamNhapKho.class);
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
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                onBackPressed();
                return true;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
