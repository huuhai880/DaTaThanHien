package com.example.thanhhien.NhaCungcap;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.example.thanhhien.R;

public class Seo_ThemSuaXoaNhaCungCap extends AppCompatActivity {
    private Toolbar toolbar;
    private Button btnThemNhaCungCap,btnSuaNhaCungCap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.seo_themsuaxoanhacungcap);

        AnhXa();
        Intent intent=getIntent();
        if(intent.getStringExtra("Ma").equals("0")){
            btnSuaNhaCungCap.setVisibility(View.GONE);
            setTitle("Thêm nhà cung cấp");
        }else if(intent.getStringExtra("Ma").equals("1")){
            btnThemNhaCungCap.setVisibility(View.GONE);
            setTitle("Sửa nhà cung cấp");
        }

    }

    private void AnhXa() {
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        btnThemNhaCungCap=findViewById(R.id.btnThemNhaCungCap);
        btnSuaNhaCungCap=findViewById(R.id.btnSuaNhaCungCap);
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
