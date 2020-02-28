package com.example.thanhhien.NhaCungcap;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.thanhhien.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Seo_QuanLyNhaCungCap extends AppCompatActivity {
    private Toolbar toolbar;
    private RecyclerView recyclerViewListNCC;
    private Adapter_NhaCungCap adapter_nhaCungCap;
    private ArrayList<Model_NhaCungCap> listNhaCungCap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.seo_quanlynhacungcap);
        setTitle("Nhà cung cấp");
        AnhXa();
        listNhaCungCap=new ArrayList<>();
        listNhaCungCap.add(new Model_NhaCungCap("hjshsh","hshaha","jsấhạia","jágđáy","ádsudh"));
        listNhaCungCap.add(new Model_NhaCungCap("hjshsh","hshaha","jsấhạia","jágđáy","ádsudh"));
        adapter_nhaCungCap=new Adapter_NhaCungCap(Seo_QuanLyNhaCungCap.this,listNhaCungCap);
        LinearLayoutManager layoutManager = new LinearLayoutManager(Seo_QuanLyNhaCungCap.this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewListNCC.setLayoutManager(layoutManager);
        recyclerViewListNCC.setAdapter(adapter_nhaCungCap);
    }

    private void AnhXa() {
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        recyclerViewListNCC=findViewById(R.id.recyclerViewListNCC);


    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.nav_themsanpham:
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                Intent intent=new Intent(Seo_QuanLyNhaCungCap.this,Seo_ThemSuaXoaNhaCungCap.class);
                intent.putExtra("Ma","0");
                startActivity(intent);
                return true;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_themsanpham,menu);
        return super.onCreateOptionsMenu(menu);
    }



}
