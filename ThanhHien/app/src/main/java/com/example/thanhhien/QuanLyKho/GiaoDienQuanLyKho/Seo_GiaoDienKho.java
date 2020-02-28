package com.example.thanhhien.QuanLyKho.GiaoDienQuanLyKho;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.thanhhien.Api_custom;
import com.example.thanhhien.QuanLyKho.SanPhamKho.Seo_QuanLySanPhamKho;
import com.example.thanhhien.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Seo_GiaoDienKho extends AppCompatActivity {
    private Toolbar toolbar;
    private GridView view_ListKho;
    private ArrayList<Model_kho> khoArrayList;
    private Adapter_Kho adapter_kho;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.seo_giaodienkho);
        setTitle("Quản lý kho");
        AnhXa();

        khoArrayList=new ArrayList<>();

        khoArrayList.add(new Model_kho("jsjs","sjsj"));
        view_ListKho.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Model_kho model_kho=khoArrayList.get(position);
                Intent intent=new Intent(Seo_GiaoDienKho.this, Seo_QuanLySanPhamKho.class);
                intent.putExtra("MaKho",model_kho.getMaKho());
                intent.putExtra("TenKho",model_kho.getTenKho());
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });


        getKho(Api_custom.listKho);
    }

    private void AnhXa() {
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        view_ListKho=findViewById(R.id.view_ListKho);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        return super.onCreateOptionsMenu(menu);
    }


    public void getKiemTraKhoKhiThemMoi(String urlService, String TenKho, final String TenKho1) {
        try {
            TenKho  = URLEncoder.encode(TenKho, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        urlService = urlService+TenKho.replace("+","%20");
        final RequestQueue requestQueue;

        Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024); // 1MB cap

        final Network network = new BasicNetwork(new HurlStack());

        requestQueue = new RequestQueue(cache, network);

        requestQueue.start();

        final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                urlService,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if (response != null && response.length() != 0) {
                            Toast.makeText(Seo_GiaoDienKho.this, "Tên kho đã tồn tại", Toast.LENGTH_SHORT).show();
                        }else {
                            ThemKho(Api_custom.themKho,TenKho1);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Seo_GiaoDienKho.this, "error getKiemTraKhoKhiThemMoi", Toast.LENGTH_SHORT).show();
                    }
                }
        );

// Add the request to the RequestQueue.
        requestQueue.add(jsonArrayRequest);
    }//end
    private void ThemKho(String url, final String TenKho) {
        RequestQueue requestQueue = Volley.newRequestQueue(Seo_GiaoDienKho.this);
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(Seo_GiaoDienKho.this, "Thêm kho thành công", Toast.LENGTH_SHORT).show();
                        khoArrayList.clear();
                        getKho(Api_custom.listKho);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Seo_GiaoDienKho.this, "error ThemKho", Toast.LENGTH_SHORT).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("TenKho", TenKho);
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }//end
    public void getKho(String urlService){
        RequestQueue requestQueue;

        Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024); // 1MB cap

        final Network network = new BasicNetwork(new HurlStack());

        requestQueue = new RequestQueue(cache, network);

        requestQueue.start();

        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(
                Request.Method.GET,
                urlService,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {


                        if(response!=null&&response.length()!=0){
                            for (int i=0;i<response.length();i++){
                                try {
                                    JSONObject jsonObject=response.getJSONObject(i);
                                    khoArrayList.add(new Model_kho(jsonObject.getString("IDKho"),jsonObject.getString("TenKho")));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                            adapter_kho=new Adapter_Kho(Seo_GiaoDienKho.this,R.layout.item_layoutgiaodienkho,khoArrayList);
                            view_ListKho.setAdapter(adapter_kho);
                        }else {

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Seo_GiaoDienKho.this, "error", Toast.LENGTH_SHORT).show();
                    }
                }
        );

// Add the request to the RequestQueue.
        requestQueue.add(jsonArrayRequest);
    }//end

}
