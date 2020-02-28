package com.example.thanhhien.QuanLyKho.ThemSuaXoaSanPham;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
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
import com.example.thanhhien.NhaCungcap.Model_NhaCungCap;
import com.example.thanhhien.NhapXuatHang.Adapter_NhaCungCap;
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

import cn.pedant.SweetAlert.SweetAlertDialog;

public class Seo_ThemSuaXoaSanPham extends AppCompatActivity {
    private Toolbar toolbar;
    private Button btnThemSanPham,btnXoaSanPham,btnSuaSanPham,btnaddCungCap;
    private LinearLayout llSL;
    private Spinner spnNhaCungCap;
    private EditText editTenSanPham, editDonVi, edit_Mau, editDoDay, editKichThuoc, editDoDai, editGiaNhap, editGiaBan, editSoLuong;
    private ArrayList<Model_NhaCungCap> listNhaCungCap;
    Adapter_NhaCungCap adapter;
    private String chonNhaCungCap = "hintSpiner";
    private String IDSanPhamA = "IDSanPham";
    private String IDThuocTinhA = "IDThuocTinh";
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.seo_themsuaxoasanpham);
        intent=getIntent();
        AnhXa();
        if(intent.getStringExtra("Ma").equals("1")){
            setTitle("Thêm sản phẩm");
            llSL.setVisibility(View.GONE);
            btnXoaSanPham.setVisibility(View.GONE);
            btnSuaSanPham.setVisibility(View.GONE);
            //getTatCaNhaCungCap(Api_custom.listTatCaNhaCungCap,"pppppp");

        }else if(intent.getStringExtra("Ma").equals("2")) {
            setTitle("Sửa sản phẩm");
            btnThemSanPham.setVisibility(View.GONE);
            //getTatCaNhaCungCap(Api_custom.listTatCaNhaCungCap,"Sua");
        }
        btnaddCungCap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = getLayoutInflater().inflate(R.layout.bottom_sheet, null);
                ImageView btnclosedialog=view.findViewById(R.id.btnclosedialog);
                final Button btnThemNhaCungCap=view.findViewById(R.id.btnThemNhaCungCap);
                final EditText edit_TenNhaCungCap=view.findViewById(R.id.edit_TenNhaCungCap);
                final EditText edit_SoDienThoai=view.findViewById(R.id.edit_SoDienThoai);
                final EditText edit_DiaChi=view.findViewById(R.id.edit_DiaChi);
                final EditText edit_Email=view.findViewById(R.id.edit_Email);
                final Dialog mBottomSheetDialog = new Dialog(Seo_ThemSuaXoaSanPham.this, R.style.MaterialDialogSheet);
                mBottomSheetDialog.setContentView(view);
                mBottomSheetDialog.setCancelable(false);
                mBottomSheetDialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                mBottomSheetDialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
                mBottomSheetDialog.getWindow().setGravity(Gravity.BOTTOM);
                mBottomSheetDialog.show();
                btnclosedialog.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mBottomSheetDialog.dismiss();
                    }
                });
                btnThemNhaCungCap.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String tenNhaCungCap=edit_TenNhaCungCap.getText().toString().trim();
                        String soDienThoai=edit_SoDienThoai.getText().toString().trim();
                        String diaChi=edit_DiaChi.getText().toString().trim();
                        String email=edit_Email.getText().toString().trim();
                        if(tenNhaCungCap.isEmpty()||soDienThoai.isEmpty()||diaChi.isEmpty()||email.isEmpty()){
                            new SweetAlertDialog(Seo_ThemSuaXoaSanPham.this, SweetAlertDialog.ERROR_TYPE)
                                    .setTitleText("Lỗi!")
                                    .setContentText("Bạn chưa nhập đầy đủ thông tin!")
                                    .show();
                            return;
                        }
                        getKiemTraNhaCungCapKhiThemMoi(Api_custom.listNCCKhiThemMoi,tenNhaCungCap,tenNhaCungCap,soDienThoai,diaChi,email,"1",mBottomSheetDialog);
                    }
                });
                btnThemNhaCungCap.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if(event.getAction() == MotionEvent.ACTION_DOWN){
                            btnThemNhaCungCap.setTextColor(Color.parseColor("#2196F3"));
                        }
                        if(event.getAction() == MotionEvent.ACTION_UP){
                            btnThemNhaCungCap.setTextColor(Color.WHITE);
                        }
                        return false;
                    }
                });
            }
        });
        btnThemSanPham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (chonNhaCungCap.equalsIgnoreCase("hintSpiner")) {
                    new SweetAlertDialog(Seo_ThemSuaXoaSanPham.this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Lỗi!")
                            .setContentText("Bạn chưa nhập đầy đủ thông tin!")
                            .show();
                    return;
                }
                String tenSP = editTenSanPham.getText().toString().trim();
                String donViTinh = editDonVi.getText().toString().trim();
                String mauSP = edit_Mau.getText().toString().trim();
                String doDay = editDoDay.getText().toString().trim();
                String kichThuoc = editKichThuoc.getText().toString().trim();
                String doDai = editDoDai.getText().toString().trim();
                String giaBan = editGiaBan.getText().toString().trim();
                String giaNhap = editGiaNhap.getText().toString().trim();
                String soLuong = editSoLuong.getText().toString().trim();
                if (tenSP.isEmpty() || donViTinh.isEmpty() || mauSP.isEmpty() || doDay.isEmpty() || kichThuoc.isEmpty() || doDai.isEmpty() || giaBan.isEmpty() || giaNhap.isEmpty() || soLuong.isEmpty()) {
                    new SweetAlertDialog(Seo_ThemSuaXoaSanPham.this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Lỗi!")
                            .setContentText("Bạn chưa nhập đầy đủ thông tin!")
                            .show();
                    return;
                }
                getAllThuotTinh(Api_custom.listThuotTinh);
                getAllSanPham(Api_custom.listSanPham);
                getKiemtraAllSanPhamThem(Api_custom.listKiemtraAllSanPhamThem,tenSP,donViTinh,mauSP,doDay,kichThuoc,doDai,giaNhap,giaBan,Seo_QuanLySanPhamKho.maKho,chonNhaCungCap);


            }
        });
        btnSuaSanPham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (chonNhaCungCap.equalsIgnoreCase("hintSpiner")) {
                    new SweetAlertDialog(Seo_ThemSuaXoaSanPham.this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Lỗi!")
                            .setContentText("Bạn chưa chọn nhà cung cấp!")
                            .show();
                    return;
                }
                String tenSP = editTenSanPham.getText().toString().trim();
                String donViTinh = editDonVi.getText().toString().trim();
                String mauSP = edit_Mau.getText().toString().trim();
                String doDay = editDoDay.getText().toString().trim();
                String kichThuoc = editKichThuoc.getText().toString().trim();
                String doDai = editDoDai.getText().toString().trim();
                String giaBan = editGiaBan.getText().toString().trim();
                String giaNhap = editGiaNhap.getText().toString().trim();
                String soLuong = editSoLuong.getText().toString().trim();
                if (tenSP.isEmpty() || donViTinh.isEmpty() || mauSP.isEmpty() || doDay.isEmpty() || kichThuoc.isEmpty() || doDai.isEmpty() || giaBan.isEmpty() || giaNhap.isEmpty() || soLuong.isEmpty()) {
                    new SweetAlertDialog(Seo_ThemSuaXoaSanPham.this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Lỗi!")
                            .setContentText("Bạn chưa nhập đầy đủ thông tin!")
                            .show();
                    return;
                }
                getKiemTraNhaCungCapChiTietSanPham_Sua(Api_custom.listSanPhamTheoIDChiTietSanPham,intent.getStringExtra("banIDChiTietSanPham"));
                //getKiemtraAllSanPhamSua(Api_custom.listKiemtraAllSanPhamThem,tenSP,donViTinh,mauSP,doDay,kichThuoc,doDai,giaNhap,giaBan,Seo_QuanLySanPhamKho.maKho,chonNhaCungCap);

            }
        });
        btnXoaSanPham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CapNhatSoLuongChiTietSanPham(Api_custom.CapNhatSoLuongSanPhamKhiNhap, intent.getStringExtra("banIDChiTietSanPham"),"-1");
            }
        });

        spnNhaCungCap.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                chonNhaCungCap = listNhaCungCap.get(position).getIdNhaCungCap();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    private void AnhXa() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        llSL = findViewById(R.id.llSL);
        btnaddCungCap = findViewById(R.id.btnaddCungCap);
        btnSuaSanPham = findViewById(R.id.btnSuaSanPham);
        btnThemSanPham = findViewById(R.id.btnThemSanPham);
        btnXoaSanPham = findViewById(R.id.btnXoaSanPham);
        spnNhaCungCap = findViewById(R.id.spnNhaCungCap);
        editSoLuong = findViewById(R.id.editSoLuong);
        editTenSanPham = findViewById(R.id.editTenSanPham);
        editDonVi = findViewById(R.id.editDonVi);
        edit_Mau = findViewById(R.id.edit_Mau);
        editDoDay = findViewById(R.id.editDoDay);
        editKichThuoc = findViewById(R.id.editKichThuoc);
        editDoDai = findViewById(R.id.editDoDai);
        editGiaNhap = findViewById(R.id.editGiaNhap);
        editGiaBan = findViewById(R.id.editGiaBan);

        listNhaCungCap = new ArrayList<>();
        listNhaCungCap.add(new Model_NhaCungCap("hintSpiner", "Chọn nhà cung cấp"));
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
    public void getAllThuotTinh(String urlService) {
        RequestQueue requestQueue;

        Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024); // 1MB cap

        final Network network = new BasicNetwork(new HurlStack());

        requestQueue = new RequestQueue(cache, network);

        requestQueue.start();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                urlService,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if (response != null && response.length() != 0) {
                            IDThuocTinhA=response.length()+"";

                        }else {IDThuocTinhA="0";}
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Seo_ThemSuaXoaSanPham.this, "error getAllThuotTinh", Toast.LENGTH_SHORT).show();
                    }
                }
        );

// Add the request to the RequestQueue.
        requestQueue.add(jsonArrayRequest);
    }//end
    public void getAllThuotTinh_Sua(String urlService) {
        RequestQueue requestQueue;

        Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024); // 1MB cap

        final Network network = new BasicNetwork(new HurlStack());

        requestQueue = new RequestQueue(cache, network);

        requestQueue.start();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                urlService,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if (response != null && response.length() != 0) {
                            CapNhatIDThuocTinhChiTietSanPham(Api_custom.CapNhatIDThuocTinhKhiSuaTrung,intent.getStringExtra("banIDChiTietSanPham"),response.length()+"");

                        }else {IDThuocTinhA="0";}
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Seo_ThemSuaXoaSanPham.this, "error getAllThuotTinh_Sua", Toast.LENGTH_SHORT).show();
                    }
                }
        );

// Add the request to the RequestQueue.
        requestQueue.add(jsonArrayRequest);
    }//end
    public void getAllSanPham_Sua(String urlService) {
        RequestQueue requestQueue;

        Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024); // 1MB cap

        final Network network = new BasicNetwork(new HurlStack());

        requestQueue = new RequestQueue(cache, network);

        requestQueue.start();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                urlService,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if (response != null && response.length() != 0) {
                            CapNhatIDSanPhamChiTietSanPham(Api_custom.CapNhatIDSanPhamKhiSuaTrung,intent.getStringExtra("banIDChiTietSanPham"),response.length()+"");
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Seo_ThemSuaXoaSanPham.this, "error getAllSanPham_Sua", Toast.LENGTH_SHORT).show();
                    }
                }
        );

// Add the request to the RequestQueue.
        requestQueue.add(jsonArrayRequest);
    }//end
//    public void getTatCaNhaCungCap(String urlService, final String Sua) {
//        urlService = urlService;
//        RequestQueue requestQueue;
//
//        Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024); // 1MB cap
//
//        final Network network = new BasicNetwork(new HurlStack());
//
//        requestQueue = new RequestQueue(cache, network);
//
//        requestQueue.start();
//
//        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
//                Request.Method.GET,
//                urlService,
//                null,
//                new Response.Listener<JSONArray>() {
//                    @Override
//                    public void onResponse(JSONArray response) {
//                        if (response != null && response.length() != 0) {
//                            for (int i = 0; i < response.length(); i++) {
//                                try {
//                                    JSONObject jsonObject = response.getJSONObject(i);
//                                    listNhaCungCap.add(new Model_NhaCungCap(jsonObject.getString("IDNhaCungCap"), jsonObject.getString("TenNhaCungCap")));
//                                } catch (JSONException e) {
//                                    e.printStackTrace();
//                                }
//                            }
//                            adapter = new Adapter_NhaCungCap(Seo_ThemSuaXoaSanPham.this, listNhaCungCap);
//                            spnNhaCungCap.setAdapter(adapter);
//                            if(Sua.equalsIgnoreCase("Sua")){
//                                getSanPhamTheoIDChiTietSanPham(Api_custom.listSanPhamTheoIDChiTietSanPham,intent.getStringExtra("banIDChiTietSanPham"),"âsasas");
//                            }
//                        }
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(Seo_ThemSuaXoaSanPham.this, "error getTatCaNhaCungCap", Toast.LENGTH_SHORT).show();
//                    }
//                }
//        );
//
//// Add the request to the RequestQueue.
//        requestQueue.add(jsonArrayRequest);
//    }//end
    public void getKiemtraAllSanPhamThem(String urlService,String Ten,String DonViTinh,String Mau,String DoDay,String KichThuoc,String DoDai,String GiaNhap,String GiaBan,String IDKho, String IDNhaCungCap) {
        try {
            Ten  = URLEncoder.encode(Ten, "UTF-8");
            DonViTinh  = URLEncoder.encode(DonViTinh, "UTF-8");
            Mau  = URLEncoder.encode(Mau, "UTF-8");
            DoDay  = URLEncoder.encode(DoDay, "UTF-8");
            KichThuoc  = URLEncoder.encode(KichThuoc, "UTF-8");
            DoDai  = URLEncoder.encode(DoDai, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        urlService = urlService+Ten.replace("+","%20")+"/"+DonViTinh.replace("+","%20")+"/"+Mau.replace("+","%20")+"/"+DoDay.replace("+","%20")+"/"+KichThuoc.replace("+","%20")+"/"+DoDai.replace("+","%20")+"/"+GiaNhap+"/"+GiaBan+"/"+IDKho+"/"+IDNhaCungCap;
        RequestQueue requestQueue;

        Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024); // 1MB cap

        final Network network = new BasicNetwork(new HurlStack());

        requestQueue = new RequestQueue(cache, network);

        requestQueue.start();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                urlService,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if (response != null && response.length() != 0) {
                            new SweetAlertDialog(Seo_ThemSuaXoaSanPham.this, SweetAlertDialog.ERROR_TYPE)
                                    .setTitleText("Lỗi!")
                                    .setContentText("Sản phẩm đã tồn tại!")
                                    .show();
                        }else {
                            String tenSP = editTenSanPham.getText().toString().trim();
                            String donViTinh = editDonVi.getText().toString().trim();
                            getKiemTraThemSanPham(Api_custom.listKiemTraThemSanPham,tenSP,donViTinh,"Tốt");
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Seo_ThemSuaXoaSanPham.this, "error getKiemtraAllSanPhamThem", Toast.LENGTH_SHORT).show();
                    }
                }
        );

// Add the request to the RequestQueue.
        requestQueue.add(jsonArrayRequest);
    }//end
    public void getKiemtraAllSanPhamSua(String urlService,String Ten,String DonViTinh,String Mau,String DoDay,String KichThuoc,String DoDai,String GiaNhap,String GiaBan,String IDKho, String IDNhaCungCap,final String soLuongBanDau) {
        try {
            Ten  = URLEncoder.encode(Ten, "UTF-8");
            DonViTinh  = URLEncoder.encode(DonViTinh, "UTF-8");
            Mau  = URLEncoder.encode(Mau, "UTF-8");
            DoDay  = URLEncoder.encode(DoDay, "UTF-8");
            KichThuoc  = URLEncoder.encode(KichThuoc, "UTF-8");
            DoDai  = URLEncoder.encode(DoDai, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        urlService = urlService+Ten.replace("+","%20")+"/"+DonViTinh.replace("+","%20")+"/"+Mau.replace("+","%20")+"/"+DoDay.replace("+","%20")+"/"+KichThuoc.replace("+","%20")+"/"+DoDai.replace("+","%20")+"/"+GiaNhap+"/"+GiaBan+"/"+IDKho+"/"+IDNhaCungCap;
        RequestQueue requestQueue;

        Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024); // 1MB cap

        final Network network = new BasicNetwork(new HurlStack());

        requestQueue = new RequestQueue(cache, network);

        requestQueue.start();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                urlService,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        String IDChiTietSanPham="";
                        String soLuongIDChiTiet="";
                        if (response != null && response.length() != 0) {
                            for (int i = 0; i < response.length(); i++) {
                                try {
                                    JSONObject jsonObject = response.getJSONObject(i);
                                    IDChiTietSanPham=jsonObject.getString("iDChiTietSanPham");
                                    soLuongIDChiTiet=jsonObject.getString("SoLuong");
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                            AlertDialog.Builder builder = new AlertDialog.Builder(Seo_ThemSuaXoaSanPham.this);
                            builder.setTitle("Sản phẩm trùng với 1 sản phẩm khác.\nBạn có muốn cập nhật lên sản phẩm đã có không?");
                            final String finalIDChiTietSanPham = IDChiTietSanPham;
                            final String finalSoLuongIDChiTiet = soLuongIDChiTiet;
                            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    CapNhatSoLuongChiTietSanPham(Api_custom.CapNhatSoLuongSanPhamKhiNhap, finalIDChiTietSanPham,""+(Integer.parseInt(finalSoLuongIDChiTiet)+Integer.parseInt(soLuongBanDau)));

                                }
                            });
                            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            });
                            AlertDialog dialog = builder.create();
                            dialog.show();
                        }else {
                            getKiemTraNhaCungCapChiTietSanPham_Sua(Api_custom.listChiTietSanPhamTheoIDChiTietSanPham,intent.getStringExtra("banIDChiTietSanPham"));
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Seo_ThemSuaXoaSanPham.this, "error getKiemtraAllSanPhamSua", Toast.LENGTH_SHORT).show();
                    }
                }
        );

// Add the request to the RequestQueue.
        requestQueue.add(jsonArrayRequest);
    }//end
    public void getSanPhamTheoIDChiTietSanPham(String urlService, String iDChiTietSanPham, final String Sua) {

        urlService = urlService+iDChiTietSanPham;

        RequestQueue requestQueue;

        Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024); // 1MB cap

        final Network network = new BasicNetwork(new HurlStack());

        requestQueue = new RequestQueue(cache, network);

        requestQueue.start();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                urlService,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if (response != null && response.length() != 0) {
                            for (int i = 0; i < response.length(); i++) {
                                try {
                                    JSONObject jsonObject = response.getJSONObject(i);
                                    if(Sua.equalsIgnoreCase("Sua")){
                                        String tenSP = editTenSanPham.getText().toString().trim();
                                        String donViTinh = editDonVi.getText().toString().trim();
                                        String mauSP = edit_Mau.getText().toString().trim();
                                        String doDay = editDoDay.getText().toString().trim();
                                        String kichThuoc = editKichThuoc.getText().toString().trim();
                                        String doDai = editDoDai.getText().toString().trim();
                                        String giaBan = editGiaBan.getText().toString().trim();
                                        String giaNhap = editGiaNhap.getText().toString().trim();
                                        String soLuong = editSoLuong.getText().toString().trim();

                                        String tenSP1 = jsonObject.getString("Ten");
                                        String donViTinh1 = jsonObject.getString("DonViTinh");
                                        String mauSP1 = jsonObject.getString("Mau");
                                        String doDay1 = jsonObject.getString("DoDay");
                                        String kichThuoc1 = jsonObject.getString("KichThuoc");
                                        String doDai1 = jsonObject.getString("DoDai");
                                        String giaBan1 = jsonObject.getString("GiaBan");
                                        String giaNhap1 = jsonObject.getString("GiaNhap");
                                        String soLuong1 = jsonObject.getString("SoLuong");
                                        String IDNhaCungCap = jsonObject.getString("IDNhaCungCap");

                                        if(tenSP.equalsIgnoreCase(tenSP1)&&donViTinh.equalsIgnoreCase(donViTinh1)&&mauSP.equalsIgnoreCase(mauSP1)&&doDay.equalsIgnoreCase(doDay1)&&kichThuoc.equalsIgnoreCase(kichThuoc1)&&doDai.equalsIgnoreCase(doDai1)&&giaBan.equalsIgnoreCase(giaBan1)&&giaNhap.equalsIgnoreCase(giaNhap1)&&soLuong.equalsIgnoreCase(soLuong1)&&chonNhaCungCap.equalsIgnoreCase(IDNhaCungCap)){
                                            Toast.makeText(Seo_ThemSuaXoaSanPham.this, "Không có gì thay đổi", Toast.LENGTH_SHORT).show();
                                            return;
                                        }

                                        getKiemtraAllSanPhamSua(Api_custom.listKiemtraAllSanPhamThem,tenSP,donViTinh,mauSP,doDay,kichThuoc,doDai,giaNhap,giaBan,Seo_QuanLySanPhamKho.maKho,chonNhaCungCap,soLuong1);

                                    }else {
                                        editTenSanPham.setText(jsonObject.getString("Ten"));
                                        editDonVi.setText(jsonObject.getString("DonViTinh"));
                                        edit_Mau.setText(jsonObject.getString("Mau"));
                                        editDoDay.setText(jsonObject.getString("DoDay"));
                                        editKichThuoc.setText(jsonObject.getString("KichThuoc"));
                                        editDoDai.setText(jsonObject.getString("DoDai"));
                                        editGiaNhap.setText(jsonObject.getString("GiaNhap"));
                                        editGiaBan.setText(jsonObject.getString("GiaBan"));
                                        editSoLuong.setText(jsonObject.getString("SoLuong"));
                                        for (int j=0;j<listNhaCungCap.size();j++){
                                            if(listNhaCungCap.get(j).getTenNhaCungCap().equalsIgnoreCase(jsonObject.getString("TenNhaCungCap"))){
                                                spnNhaCungCap.setSelection(j);
                                                chonNhaCungCap=listNhaCungCap.get(j).getIdNhaCungCap();
                                            }
                                        }
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }else {

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Seo_ThemSuaXoaSanPham.this, "error getSanPhamTheoIDChiTietSanPham", Toast.LENGTH_SHORT).show();
                    }
                }
        );

// Add the request to the RequestQueue.
        requestQueue.add(jsonArrayRequest);
    }//end
    public void getKiemTraChiTietSanPham(String urlService,String IDThuocTinh,String IDNhaCungCap,String GiaNhap,String GiaBan,String SoLuong,String IDSanPham,String IDKho) {
        urlService = urlService+IDThuocTinh+"/"+IDNhaCungCap+"/"+GiaNhap+"/"+GiaBan+"/"+IDSanPham+"/"+IDKho;
        RequestQueue requestQueue;

        Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024); // 1MB cap

        final Network network = new BasicNetwork(new HurlStack());

        requestQueue = new RequestQueue(cache, network);

        requestQueue.start();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                urlService,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if (response != null && response.length() != 0) {

                        }else {
                            String giaBan = editGiaBan.getText().toString().trim();
                            String giaNhap = editGiaNhap.getText().toString().trim();
                            String soLuong = editSoLuong.getText().toString().trim();

                            ThemChiTietSanPham(Api_custom.themchitietsanpham, IDThuocTinhA, chonNhaCungCap, giaNhap, giaBan, soLuong, IDSanPhamA, Seo_QuanLySanPhamKho.maKho);

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Seo_ThemSuaXoaSanPham.this, "error getKiemTraChiTietSanPham", Toast.LENGTH_SHORT).show();
                    }
                }
        );

// Add the request to the RequestQueue.
        requestQueue.add(jsonArrayRequest);
    }//end
    public void getKiemTraChiTietSanPham_Sua(String urlService,String iDChiTietSanPham) {
        urlService = urlService+iDChiTietSanPham;
        RequestQueue requestQueue;

        Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024); // 1MB cap

        final Network network = new BasicNetwork(new HurlStack());

        requestQueue = new RequestQueue(cache, network);

        requestQueue.start();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                urlService,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if (response != null && response.length() != 0) {
                            for (int i = 0; i < response.length(); i++) {
                                try {
                                    JSONObject jsonObject = response.getJSONObject(i);
                                    if(jsonObject.getString("GiaNhap").equalsIgnoreCase(editGiaNhap.getText().toString().trim())&&jsonObject.getString("GiaBan").equalsIgnoreCase(editGiaBan.getText().toString().trim())&&jsonObject.getString("SoLuong").equalsIgnoreCase(editSoLuong.getText().toString().trim())){
                                        new SweetAlertDialog(Seo_ThemSuaXoaSanPham.this, SweetAlertDialog.SUCCESS_TYPE)
                                                .setTitleText("Thành công!")
                                                .setContentText("Cập nhật thành công!")
                                                .show();
                                        finish();
                                    }else {
                                        String giaNhap=editGiaNhap.getText().toString().trim();
                                        String giaBan=editGiaBan.getText().toString().trim();
                                        String soLuong=editSoLuong.getText().toString().trim();
                                        CapNhatChiTietSanPham(Api_custom.CapNhatChiTietSanPham,intent.getStringExtra("banIDChiTietSanPham"),giaNhap,giaBan,soLuong);
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Seo_ThemSuaXoaSanPham.this, "error getKiemTraChiTietSanPham_Sua", Toast.LENGTH_SHORT).show();
                    }
                }
        );

// Add the request to the RequestQueue.
        requestQueue.add(jsonArrayRequest);
    }//end
    public void getKiemTraThemSanPham(String urlService,String Ten,String DonViTinh,String ChatLuong) {
        try {
            Ten  = URLEncoder.encode(Ten, "UTF-8");
            DonViTinh  = URLEncoder.encode(DonViTinh, "UTF-8");
            ChatLuong  = URLEncoder.encode(ChatLuong, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        urlService = urlService+Ten.replace("+","%20")+"/"+DonViTinh.replace("+","%20")+"/"+ChatLuong.replace("+","%20");
        RequestQueue requestQueue;

        Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024); // 1MB cap

        final Network network = new BasicNetwork(new HurlStack());

        requestQueue = new RequestQueue(cache, network);

        requestQueue.start();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                urlService,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        String tenSP = editTenSanPham.getText().toString().trim();
                        String donViTinh = editDonVi.getText().toString().trim();
                        String mauSP = edit_Mau.getText().toString().trim();
                        String doDay = editDoDay.getText().toString().trim();
                        String kichThuoc = editKichThuoc.getText().toString().trim();
                        String doDai = editDoDai.getText().toString().trim();
                        if (response != null && response.length() != 0) {
                            for (int i = 0; i < response.length(); i++) {
                                try {
                                    JSONObject jsonObject = response.getJSONObject(i);
                                    IDSanPhamA=jsonObject.getString("IDSanPham");
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }else {
                            ThemSanPham(Api_custom.themsanpham, tenSP, donViTinh, "Tốt");
                            IDSanPhamA=(Integer.parseInt(IDSanPhamA)+1)+"";
                        }
                        getKiemTraThuocTinhSanPham(Api_custom.listKiemTraThuocTinhSanPham,mauSP,doDay,kichThuoc,doDai,"Tốt");
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Seo_ThemSuaXoaSanPham.this, "error getKiemTraThemSanPham", Toast.LENGTH_SHORT).show();
                    }
                }
        );

// Add the request to the RequestQueue.
        requestQueue.add(jsonArrayRequest);
    }//end
    public void getKiemTraThemSanPham_Sua(String urlService,String Ten,String DonViTinh,String ChatLuong) {
        try {
            Ten  = URLEncoder.encode(Ten, "UTF-8");
            DonViTinh  = URLEncoder.encode(DonViTinh, "UTF-8");
            ChatLuong  = URLEncoder.encode(ChatLuong, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        urlService = urlService+Ten.replace("+","%20")+"/"+DonViTinh.replace("+","%20")+"/"+ChatLuong.replace("+","%20");
        Log.d("plaaaaaaaa",urlService);
        RequestQueue requestQueue;

        Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024); // 1MB cap

        final Network network = new BasicNetwork(new HurlStack());

        requestQueue = new RequestQueue(cache, network);

        requestQueue.start();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                urlService,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        String tenSP = editTenSanPham.getText().toString().trim();
                        String donViTinh = editDonVi.getText().toString().trim();
                        String mauSP = edit_Mau.getText().toString().trim();
                        String doDay = editDoDay.getText().toString().trim();
                        String kichThuoc = editKichThuoc.getText().toString().trim();
                        String doDai = editDoDai.getText().toString().trim();
                        if (response != null && response.length() != 0) {

                            for (int i = 0; i < response.length(); i++) {
                                try {
                                    JSONObject jsonObject = response.getJSONObject(i);
                                    CapNhatIDSanPhamChiTietSanPham(Api_custom.CapNhatIDSanPhamKhiSuaTrung,intent.getStringExtra("banIDChiTietSanPham"),jsonObject.getString("IDSanPham"));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }else {
                            ThemSanPham_Sua(Api_custom.themsanpham, tenSP, donViTinh, "Tốt");
                        }
                        getKiemTraThuocTinhSanPham_Sua(Api_custom.listKiemTraThuocTinhSanPham,mauSP,doDay,kichThuoc,doDai,"Tốt");
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Seo_ThemSuaXoaSanPham.this, "error getKiemTraThemSanPham_Sua", Toast.LENGTH_SHORT).show();
                    }
                }
        );

// Add the request to the RequestQueue.
        requestQueue.add(jsonArrayRequest);
    }//end
    public void getKiemTraNhaCungCapChiTietSanPham_Sua(String urlService,String iDChiTietSanPham) {
        urlService = urlService+iDChiTietSanPham;
        RequestQueue requestQueue;

        Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024); // 1MB cap

        final Network network = new BasicNetwork(new HurlStack());

        requestQueue = new RequestQueue(cache, network);

        requestQueue.start();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                urlService,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if (response != null && response.length() != 0) {
                            for (int i = 0; i < response.length(); i++) {
                                try {
                                    JSONObject jsonObject = response.getJSONObject(i);
                                    if(jsonObject.getString("IDNhaCungCap").equalsIgnoreCase(chonNhaCungCap)){
                                        String tenSPSua=editTenSanPham.getText().toString().trim();
                                        String donViTinh=editDonVi.getText().toString().trim();
                                        getKiemTraThemSanPham_Sua(Api_custom.listKiemTraThemSanPham,tenSPSua,donViTinh,"Tốt");

                                    }else {
                                        CapNhatIDNhaCungCapChiTietSanPham(Api_custom.CapNhatIDNhaCungCapChiTietSanPham,intent.getStringExtra("banIDChiTietSanPham"),chonNhaCungCap);
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Seo_ThemSuaXoaSanPham.this, "error getKiemTraNhaCungCapChiTietSanPham_Sua", Toast.LENGTH_SHORT).show();
                    }
                }
        );

// Add the request to the RequestQueue.
        requestQueue.add(jsonArrayRequest);
    }//end
    public void getKiemTraThuocTinhSanPham(String urlService,String Mau,String DoDay,String KichThuoc,String DoDai,String TinhChat) {
        try {
            Mau  = URLEncoder.encode(Mau, "UTF-8");
            DoDay  = URLEncoder.encode(DoDay, "UTF-8");
            KichThuoc  = URLEncoder.encode(KichThuoc, "UTF-8");
            DoDai  = URLEncoder.encode(DoDai, "UTF-8");
            TinhChat  = URLEncoder.encode(TinhChat, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        urlService = urlService+Mau.replace("+","%20")+"/"+DoDay.replace("+","%20")+"/"+KichThuoc.replace("+","%20")+"/"+DoDai.replace("+","%20")+"/"+TinhChat.replace("+","%20");
        RequestQueue requestQueue;

        Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024); // 1MB cap

        final Network network = new BasicNetwork(new HurlStack());

        requestQueue = new RequestQueue(cache, network);

        requestQueue.start();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                urlService,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        String tenSP = editTenSanPham.getText().toString().trim();
                        String donViTinh = editDonVi.getText().toString().trim();
                        String mauSP = edit_Mau.getText().toString().trim();
                        String doDay = editDoDay.getText().toString().trim();
                        String kichThuoc = editKichThuoc.getText().toString().trim();
                        String doDai = editDoDai.getText().toString().trim();
                        String giaBan = editGiaBan.getText().toString().trim();
                        String giaNhap = editGiaNhap.getText().toString().trim();
                        String soLuong = editSoLuong.getText().toString().trim();
                        if (response != null && response.length() != 0) {
                            for (int i = 0; i < response.length(); i++) {
                                try {
                                    JSONObject jsonObject = response.getJSONObject(i);
                                    IDThuocTinhA=jsonObject.getString("IDThuocTinh");
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }else {
                            ThemThuocTinh(Api_custom.themthuoctinhsanpham, mauSP, doDay, kichThuoc, doDai, "Tốt", IDSanPhamA);
                            IDThuocTinhA=(Integer.parseInt(IDThuocTinhA)+1)+"";

                        }
                        getKiemTraChiTietSanPham(Api_custom.listKiemTraChiTietSanPham1,IDThuocTinhA,chonNhaCungCap,giaNhap,giaBan,soLuong,IDSanPhamA,Seo_QuanLySanPhamKho.maKho);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Seo_ThemSuaXoaSanPham.this, "error getKiemTraThuocTinhSanPham", Toast.LENGTH_SHORT).show();
                    }
                }
        );

// Add the request to the RequestQueue.
        requestQueue.add(jsonArrayRequest);
    }//end
    public void getKiemTraThuocTinhSanPham_Sua(String urlService,String Mau,String DoDay,String KichThuoc,String DoDai,String TinhChat) {
        try {
            Mau  = URLEncoder.encode(Mau, "UTF-8");
            DoDay  = URLEncoder.encode(DoDay, "UTF-8");
            KichThuoc  = URLEncoder.encode(KichThuoc, "UTF-8");
            DoDai  = URLEncoder.encode(DoDai, "UTF-8");
            TinhChat  = URLEncoder.encode(TinhChat, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        urlService = urlService+Mau.replace("+","%20")+"/"+DoDay.replace("+","%20")+"/"+KichThuoc.replace("+","%20")+"/"+DoDai.replace("+","%20")+"/"+TinhChat.replace("+","%20");
        RequestQueue requestQueue;

        Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024); // 1MB cap

        final Network network = new BasicNetwork(new HurlStack());

        requestQueue = new RequestQueue(cache, network);

        requestQueue.start();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                urlService,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        String tenSP = editTenSanPham.getText().toString().trim();
                        String donViTinh = editDonVi.getText().toString().trim();
                        String mauSP = edit_Mau.getText().toString().trim();
                        String doDay = editDoDay.getText().toString().trim();
                        String kichThuoc = editKichThuoc.getText().toString().trim();
                        String doDai = editDoDai.getText().toString().trim();
                        String giaBan = editGiaBan.getText().toString().trim();
                        String giaNhap = editGiaNhap.getText().toString().trim();
                        String soLuong = editSoLuong.getText().toString().trim();
                        if (response != null && response.length() != 0) {
                            for (int i = 0; i < response.length(); i++) {
                                try {
                                    JSONObject jsonObject = response.getJSONObject(i);
                                    CapNhatIDThuocTinhChiTietSanPham(Api_custom.CapNhatIDThuocTinhKhiSuaTrung,intent.getStringExtra("banIDChiTietSanPham"),jsonObject.getString("IDThuocTinh"));
                                    IDThuocTinhA=jsonObject.getString("IDThuocTinh");
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }else {
                            ThemThuocTinh_Sua(Api_custom.themthuoctinhsanpham, mauSP, doDay, kichThuoc, doDai, "Tốt", IDSanPhamA);
                        }
                        getKiemTraChiTietSanPham_Sua(Api_custom.listChiTietSanPhamTheoIDChiTietSanPham,intent.getStringExtra("banIDChiTietSanPham"));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Seo_ThemSuaXoaSanPham.this, "error getKiemTraThuocTinhSanPham_Sua", Toast.LENGTH_SHORT).show();
                    }
                }
        );

// Add the request to the RequestQueue.
        requestQueue.add(jsonArrayRequest);
    }//end
    public void getAllSanPham(String urlService) {
        urlService = urlService;
        RequestQueue requestQueue;

        Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024); // 1MB cap

        final Network network = new BasicNetwork(new HurlStack());

        requestQueue = new RequestQueue(cache, network);

        requestQueue.start();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                urlService,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if (response != null && response.length() != 0) {
                            IDSanPhamA=response.length()+"";
                        }else {
                            IDSanPhamA="0";
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Seo_ThemSuaXoaSanPham.this, "error getAllSanPham", Toast.LENGTH_SHORT).show();
                    }
                }
        );

// Add the request to the RequestQueue.
        requestQueue.add(jsonArrayRequest);
    }//end

    private void ThemSanPham(String url, final String Ten, final String DonViTinh, final String ChatLuong) {
        RequestQueue requestQueue = Volley.newRequestQueue(Seo_ThemSuaXoaSanPham.this);
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Seo_ThemSuaXoaSanPham.this, "error ThemSanPham", Toast.LENGTH_SHORT).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Ten", Ten);
                params.put("DonViTinh", DonViTinh);
                params.put("ChatLuong", ChatLuong);
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }//end
    private void ThemSanPham_Sua(String url, final String Ten, final String DonViTinh, final String ChatLuong) {
        RequestQueue requestQueue = Volley.newRequestQueue(Seo_ThemSuaXoaSanPham.this);
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        getAllSanPham_Sua(Api_custom.listSanPham);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Seo_ThemSuaXoaSanPham.this, "error ThemSanPham_Sua", Toast.LENGTH_SHORT).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Ten", Ten);
                params.put("DonViTinh", DonViTinh);
                params.put("ChatLuong", ChatLuong);
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }//end
    private void CapNhatSoLuongChiTietSanPham(String url, final String iDChiTietSanPham, final String SoLuong) {
        RequestQueue requestQueue = Volley.newRequestQueue(Seo_ThemSuaXoaSanPham.this);
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        CapNhatSoLuongChiTietSanPham1(Api_custom.CapNhatSoLuongSanPhamKhiNhap, intent.getStringExtra("banIDChiTietSanPham"),"0");
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Seo_ThemSuaXoaSanPham.this, "error CapNhatSoLuongChiTietSanPham", Toast.LENGTH_SHORT).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("iDChiTietSanPham", iDChiTietSanPham);
                params.put("SoLuong", SoLuong);
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }//end
    private void CapNhatIDSanPhamChiTietSanPham(String url, final String iDChiTietSanPham, final String IDSanPham) {
        RequestQueue requestQueue = Volley.newRequestQueue(Seo_ThemSuaXoaSanPham.this);
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        String tenSP = editTenSanPham.getText().toString().trim();
                        String donViTinh = editDonVi.getText().toString().trim();
                        String mauSP = edit_Mau.getText().toString().trim();
                        String doDay = editDoDay.getText().toString().trim();
                        String kichThuoc = editKichThuoc.getText().toString().trim();
                        String doDai = editDoDai.getText().toString().trim();
                        getKiemTraThuocTinhSanPham_Sua(Api_custom.listKiemTraThuocTinhSanPham,mauSP,doDay,kichThuoc,doDai,"Tốt");
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Seo_ThemSuaXoaSanPham.this, "error CapNhatIDSanPhamChiTietSanPham\n"+error, Toast.LENGTH_SHORT).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("iDChiTietSanPham", iDChiTietSanPham);
                params.put("IDSanPham", IDSanPham);
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }//end
    private void CapNhatChiTietSanPham(String url, final String iDChiTietSanPham, final String GiaNhap, final String GiaBan, final String SoLuong) {
        RequestQueue requestQueue = Volley.newRequestQueue(Seo_ThemSuaXoaSanPham.this);
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        new SweetAlertDialog(Seo_ThemSuaXoaSanPham.this, SweetAlertDialog.SUCCESS_TYPE)
                                .setTitleText("Thành công!")
                                .setContentText("Cập nhật thành công!")
                                .show();
                        finish();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Seo_ThemSuaXoaSanPham.this, "error CapNhatChiTietSanPham", Toast.LENGTH_SHORT).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("iDChiTietSanPham", iDChiTietSanPham);
                params.put("GiaNhap", GiaNhap);
                params.put("GiaBan", GiaBan);
                params.put("SoLuong", SoLuong);
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }//end
    private void CapNhatIDNhaCungCapChiTietSanPham(String url, final String iDChiTietSanPham, final String IDNhaCungCap) {
        RequestQueue requestQueue = Volley.newRequestQueue(Seo_ThemSuaXoaSanPham.this);
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        String tenSPSua=editTenSanPham.getText().toString().trim();
                        String donViTinh=editDonVi.getText().toString().trim();
                        getKiemTraThemSanPham_Sua(Api_custom.listKiemTraThemSanPham,tenSPSua,donViTinh,"Tốt");

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Seo_ThemSuaXoaSanPham.this, "error CapNhatIDNhaCungCapChiTietSanPham", Toast.LENGTH_SHORT).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("iDChiTietSanPham", iDChiTietSanPham);
                params.put("IDNhaCungCap", IDNhaCungCap);
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }//end
    private void CapNhatIDThuocTinhChiTietSanPham(String url, final String iDChiTietSanPham, final String IDThuocTinh) {
        RequestQueue requestQueue = Volley.newRequestQueue(Seo_ThemSuaXoaSanPham.this);
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        getKiemTraChiTietSanPham_Sua(Api_custom.listChiTietSanPhamTheoIDChiTietSanPham,intent.getStringExtra("banIDChiTietSanPham"));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Seo_ThemSuaXoaSanPham.this, "error CapNhatIDThuocTinhChiTietSanPham", Toast.LENGTH_SHORT).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("iDChiTietSanPham", iDChiTietSanPham);
                params.put("IDThuocTinh", IDThuocTinh);
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }//end
    private void CapNhatSoLuongChiTietSanPham1(String url, final String iDChiTietSanPham, final String SoLuong) {
        RequestQueue requestQueue = Volley.newRequestQueue(Seo_ThemSuaXoaSanPham.this);
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        new SweetAlertDialog(Seo_ThemSuaXoaSanPham.this, SweetAlertDialog.SUCCESS_TYPE)
                                .setTitleText("Thành công!")
                                .setContentText("Cập nhật thành công!")
                                .show();
                        Intent intent = new Intent();
                        setResult(111,intent);
                        finish();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Seo_ThemSuaXoaSanPham.this, "error CapNhatSoLuongChiTietSanPham1", Toast.LENGTH_SHORT).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("iDChiTietSanPham", iDChiTietSanPham);
                params.put("SoLuong", SoLuong);
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }//end
    private void ThemThuocTinh(String urlThemThuocTinh, final String Mau, final String DoDay, final String KichThuoc, final String DoDai, final String TinhChat, final String IDSanPham) {
        RequestQueue requestQueue = Volley.newRequestQueue(Seo_ThemSuaXoaSanPham.this);
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                urlThemThuocTinh,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Seo_ThemSuaXoaSanPham.this, "error ThemThuocTinh", Toast.LENGTH_SHORT).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Mau", Mau);
                params.put("DoDay", DoDay);
                params.put("KichThuoc", KichThuoc);
                params.put("DoDai", DoDai);
                params.put("TinhChat", TinhChat);
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }//end
    private void ThemThuocTinh_Sua(String urlThemThuocTinh, final String Mau, final String DoDay, final String KichThuoc, final String DoDai, final String TinhChat, final String IDSanPham) {
        RequestQueue requestQueue = Volley.newRequestQueue(Seo_ThemSuaXoaSanPham.this);
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                urlThemThuocTinh,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        getAllThuotTinh_Sua(Api_custom.listThuotTinh);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Seo_ThemSuaXoaSanPham.this, "error ThemThuocTinh_Sua", Toast.LENGTH_SHORT).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Mau", Mau);
                params.put("DoDay", DoDay);
                params.put("KichThuoc", KichThuoc);
                params.put("DoDai", DoDai);
                params.put("TinhChat", TinhChat);
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }//end
    private void ThemChiTietSanPham(String urlThemChiTietSanPham, final String IDThuocTinh, final String IDNhaCungCap, final String GiaNhap, final String GiaBan, final String SoLuong, final String IDSanPham, final String IDKho) {
        RequestQueue requestQueue = Volley.newRequestQueue(Seo_ThemSuaXoaSanPham.this);
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                urlThemChiTietSanPham,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        new SweetAlertDialog(Seo_ThemSuaXoaSanPham.this, SweetAlertDialog.SUCCESS_TYPE)
                                .setTitleText("Thành công!")
                                .setContentText("Thêm thành công!")
                                .show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Seo_ThemSuaXoaSanPham.this, "error ThemChiTietSanPham" , Toast.LENGTH_SHORT).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("IDThuocTinh", IDThuocTinh);
                params.put("IDNhaCungCap", IDNhaCungCap);
                params.put("GiaNhap", GiaNhap);
                params.put("GiaBan", GiaBan);
                params.put("SoLuong", "0");
                params.put("IDSanPham", IDSanPham);
                params.put("IDKho", IDKho);
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }//end
    public void getKiemTraNhaCungCapKhiThemMoi(String urlService, String TenNhaCungCap, final String TenNhaCungCap1, final String SDT, final String DiaChi, final String Email, final String GhiChu, final Dialog mBottomSheetDialog) {
        try {
            TenNhaCungCap  = URLEncoder.encode(TenNhaCungCap, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        urlService = urlService+TenNhaCungCap.replace("+","%20");
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
                            new SweetAlertDialog(Seo_ThemSuaXoaSanPham.this, SweetAlertDialog.ERROR_TYPE)
                                    .setTitleText("Lỗi!")
                                    .setContentText("Nhà cung cấp đã tồn tại!")
                                    .show();
                        }else {
                            ThemNhaCungCap(Api_custom.themncc,TenNhaCungCap1,SDT,DiaChi,Email,GhiChu,mBottomSheetDialog);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Seo_ThemSuaXoaSanPham.this, "error getKiemTraKhoKhiThemMoi", Toast.LENGTH_SHORT).show();
                    }
                }
        );

// Add the request to the RequestQueue.
        requestQueue.add(jsonArrayRequest);
    }//end
    private void ThemNhaCungCap(String url, final String TenNhaCungCap, final String SDT, final String DiaChi, final String Email, final String GhiChu, final Dialog mBottomSheetDialog) {
        RequestQueue requestQueue = Volley.newRequestQueue(Seo_ThemSuaXoaSanPham.this);
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        listNhaCungCap.clear();
                        listNhaCungCap.add(new Model_NhaCungCap("hintSpiner", "Chọn nhà cung cấp"));
                        //getTatCaNhaCungCap(Api_custom.listTatCaNhaCungCap,"pppp");
                        chonNhaCungCap = "hintSpiner";
                        new SweetAlertDialog(Seo_ThemSuaXoaSanPham.this, SweetAlertDialog.SUCCESS_TYPE)
                                .setTitleText("Thành công")
                                .setContentText("Thêm nhà cung cấp thành công!")
                                .setConfirmText("Ok")
                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sDialog) {
                                        sDialog.dismissWithAnimation();
                                        mBottomSheetDialog.dismiss();

                                    }
                                }).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Seo_ThemSuaXoaSanPham.this, "error ThemNhaCungCap", Toast.LENGTH_SHORT).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("TenNhaCungCap", TenNhaCungCap);
                params.put("SDT", SDT);
                params.put("DiaChi", DiaChi);
                params.put("Email", Email);
                params.put("GhiChu", GhiChu);
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }//end
}
