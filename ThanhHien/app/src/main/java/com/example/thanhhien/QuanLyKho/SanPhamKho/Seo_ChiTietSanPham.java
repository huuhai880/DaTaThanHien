package com.example.thanhhien.QuanLyKho.SanPhamKho;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
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
import com.example.thanhhien.QuanLyKho.ThemSuaXoaSanPham.Seo_ThemSuaXoaSanPham;
import com.example.thanhhien.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class Seo_ChiTietSanPham extends AppCompatActivity {
    private Toolbar toolbar;
    private TextView txtTenSanPham,txtSoLuongTon,txtSoLuongNhap,txtSoLuongXuat,txtDonViTinh2,txtDonViTinh1,txtDonViTinh3,txtNgay,txtNgayThangNam;
    private ListView listViewLichSuNhapXuat;
    private Adapter_LichSuNhapXuatChiTiet adapter_lichSuNhapXuatChiTiet;
    private ArrayList<Model_LichSuNhapXuatChiTiet> LichSuNhapXuatArraylist;
    private Intent intent;
    private LinearLayout btnChonNgay;
    private int mYear, mMonth, mDay,LuuNgayBatDau,LuuNgayKetThuc;
    private Button btnNgayHomNay,btnNgayHomQua,btnBayNgayQua,btnBaMuoiNgayQua,btnNgayKhac;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.seo_chitietsanpham);
        setTitle("Chi tiết sản phẩm");
        AnhXa();
        intent=getIntent();
        final Calendar calendar=Calendar.getInstance();
        final int ngay=calendar.get(Calendar.DATE);
        txtNgay.setText(ngay+"");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        dateFormatter.setLenient(false);
        Date today = new Date();
        final String NgayHomNay  = dateFormatter.format(today);
        txtNgayThangNam.setText(NgayHomNay);

        LichSuNhapXuatArraylist=new ArrayList<>();
        getSoLuongTonNhapXuatTheoIDChiTietSanPham(Api_custom.listSoLuongTonNhapXuatTheoIDChiTietSanPham,intent.getStringExtra("banIDChiTietSanPham"));
        getTatCaPhieuNhapNgayHomNay(Api_custom.listDanhSachPhieuNhapHomNay,NgayHomNay,NgayHomNay,intent.getStringExtra("banIDChiTietSanPham"));
        adapter_lichSuNhapXuatChiTiet=new Adapter_LichSuNhapXuatChiTiet(Seo_ChiTietSanPham.this,R.layout.item_listnhapxuatsanpham,LichSuNhapXuatArraylist);
        listViewLichSuNhapXuat.setAdapter(adapter_lichSuNhapXuatChiTiet);
        btnChonNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eventClickChonNgay();
            }
        });
    }
    private void AnhXa() {
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        listViewLichSuNhapXuat=findViewById(R.id.listViewLichSuNhapXuat);
        txtTenSanPham=findViewById(R.id.txtTenSanPham);
        txtSoLuongTon=findViewById(R.id.txtSoLuongTon);
        txtSoLuongNhap=findViewById(R.id.txtSoLuongNhap);
        txtSoLuongXuat=findViewById(R.id.txtSoLuongXuat);
        txtDonViTinh2=findViewById(R.id.txtDonViTinh2);
        txtDonViTinh1=findViewById(R.id.txtDonViTinh1);
        txtDonViTinh3=findViewById(R.id.txtDonViTinh3);
        txtNgay=findViewById(R.id.txtNgay);
        txtNgayThangNam=findViewById(R.id.txtNgayThangNam);
        btnChonNgay=findViewById(R.id.btnNgayKhac);
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
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                Intent intent1=new Intent(Seo_ChiTietSanPham.this, Seo_ThemSuaXoaSanPham.class);
                intent1.putExtra("Ma","2");
                intent1.putExtra("banIDChiTietSanPham",intent.getStringExtra("banIDChiTietSanPham"));
                startActivityForResult(intent1,111);
                return true;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==resultCode){
            if(resultCode==111){
                finish();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_themsanpham,menu);
        menu.getItem(0).setIcon(R.drawable.imgedit);
        return super.onCreateOptionsMenu(menu);
    }
    private void eventClickChonNgay() {
        // khai báo ánh xạ
        View view = getLayoutInflater().inflate(R.layout.item_layoutchonngay, null);
        ImageView btnclosedialog=view.findViewById(R.id.btnclosedialog);
        btnNgayHomNay=view.findViewById(R.id.btnNgayHomNay);
        btnNgayHomQua=view.findViewById(R.id.btnNgayHomQua);
        btnBayNgayQua=view.findViewById(R.id.btnBayNgayQua);
        btnBaMuoiNgayQua=view.findViewById(R.id.btnBaMuoiNgayQua);
        btnNgayKhac=view.findViewById(R.id.btnNgayKhac);
        // khai báo bottom sheet
        final Dialog mBottomSheetDialog = new Dialog(Seo_ChiTietSanPham.this, R.style.MaterialDialogSheet);
        mBottomSheetDialog.setContentView(view);
        mBottomSheetDialog.setCancelable(false);
        mBottomSheetDialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        mBottomSheetDialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        mBottomSheetDialog.getWindow().setGravity(Gravity.BOTTOM);
        mBottomSheetDialog.show();
        btnclosedialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBottomSheetDialog.dismiss();
            }
        });

        // các sự kiên click của bottom sheet
        // lấy ngày hôm nay
        btnNgayHomNay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LichSuNhapXuatArraylist.clear();
                DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
                dateFormatter.setLenient(false);
                Date today = new Date();
                final String NgayHomNay  = dateFormatter.format(today);
                txtNgayThangNam.setText(NgayHomNay);
                getTatCaPhieuNhapNgayHomNay(Api_custom.listDanhSachPhieuNhapHomNay,NgayHomNay,NgayHomNay,intent.getStringExtra("banIDChiTietSanPham"));
                adapter_lichSuNhapXuatChiTiet.notifyDataSetChanged();
                mBottomSheetDialog.dismiss();
            }
        });
        // lấy ngày hôm qua
        btnNgayHomQua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LichSuNhapXuatArraylist.clear();

                GregorianCalendar calendar = new GregorianCalendar();
                DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");

                calendar.add(calendar.DAY_OF_MONTH, -1);
                Date tomorrow = calendar.getTime();
                final String NgayHomQua  = fmt.format(tomorrow);
                getTatCaPhieuNhapNgayHomNay(Api_custom.listDanhSachPhieuNhapHomNay,NgayHomQua,NgayHomQua,intent.getStringExtra("banIDChiTietSanPham"));
                adapter_lichSuNhapXuatChiTiet.notifyDataSetChanged();

                mBottomSheetDialog.dismiss();
            }
        });
        // sự kiện lấy bảy ngày qua
        btnBayNgayQua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LichSuNhapXuatArraylist.clear();
                // lấy bảy ngày trước
                GregorianCalendar calendar = new GregorianCalendar();
                DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
                final String BayNgayTruoc ;
                calendar.add(calendar.DAY_OF_MONTH, -7);
                Date tomorrow = calendar.getTime();
                BayNgayTruoc = fmt.format(tomorrow);
                // lấy ngày hôm nay
                DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
                dateFormatter.setLenient(false);
                Date today = new Date();
                final String NgayHomNay = dateFormatter.format(today);
                // lấy dữ liệu
                getTatCaPhieuNhapNgayHomNay(Api_custom.listDanhSachPhieuNhapHomNay,BayNgayTruoc,NgayHomNay,intent.getStringExtra("banIDChiTietSanPham"));
                adapter_lichSuNhapXuatChiTiet.notifyDataSetChanged();
                mBottomSheetDialog.dismiss();




            }
        });
        btnBaMuoiNgayQua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LichSuNhapXuatArraylist.clear();
                // lấy nagỳ hôm nay
                DateFormat dateFormatter2 = new SimpleDateFormat("yyyy-MM-dd");
                dateFormatter2.setLenient(false);
                Date today2 = new Date();
                final String NgayHomNay = dateFormatter2.format(today2);
                // lấy ngày đầu của tháng
                DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-");
                dateFormatter.setLenient(false);
                Date today = new Date();
                final String DauThangNay = dateFormatter.format(today);

                getTatCaPhieuNhapNgayHomNay(Api_custom.listDanhSachPhieuNhapHomNay,DauThangNay+"01",NgayHomNay,intent.getStringExtra("banIDChiTietSanPham"));
                adapter_lichSuNhapXuatChiTiet.notifyDataSetChanged();
                mBottomSheetDialog.dismiss();





            }
        });

        btnNgayKhac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Seo_ChiTietSanPham.this, "Lấy danh sách nhập hàng những ngày khác", Toast.LENGTH_SHORT).show();
                LichSuNhapXuatArraylist.clear();
                LayoutInflater inflater2 = getLayoutInflater();
                View alertLayout2 = inflater2.inflate(R.layout.ad_item_layoutngaythongke, null);
                final EditText editNgayBatDau = (EditText) alertLayout2.findViewById(R.id.editNgayBatDau);
                final EditText editNgayKetThuc = (EditText) alertLayout2.findViewById(R.id.editNgayKetThuc);
                editNgayBatDau.setInputType(InputType.TYPE_NULL);
                editNgayBatDau.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Calendar calendar=Calendar.getInstance();
                        DatePickerDialog datePickerDialog=new DatePickerDialog(Seo_ChiTietSanPham.this, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int monthofYear, int dateOfMonth) {
                                editNgayBatDau.setText(year+"-"+(monthofYear+1)+"-"+dateOfMonth);
                                LuuNgayBatDau=year+(monthofYear+1)+dateOfMonth;
                            }
                        },calendar.get(Calendar.YEAR),
                                calendar.get(Calendar.MONTH),
                                calendar.get(Calendar.DAY_OF_MONTH)
                        );
                        datePickerDialog.show();
                    }
                });
                editNgayKetThuc.setInputType(InputType.TYPE_NULL);
                editNgayKetThuc.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final Calendar c = Calendar.getInstance();
                        mYear = c.get(Calendar.YEAR);
                        mMonth = c.get(Calendar.MONTH);
                        mDay = c.get(Calendar.DAY_OF_MONTH);
                        DatePickerDialog datePickerDialog = new DatePickerDialog(Seo_ChiTietSanPham.this,
                                new DatePickerDialog.OnDateSetListener() {

                                    @Override
                                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                        editNgayKetThuc.setText( year+ "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                                        LuuNgayKetThuc=year+(monthOfYear+1)+dayOfMonth;
                                    }
                                }, mYear, mMonth, mDay);
                        datePickerDialog.show();
                    }
                });

                new SweetAlertDialog(Seo_ChiTietSanPham.this, SweetAlertDialog.NORMAL_TYPE)
                        .setTitleText("Chọn Ngày")
                        .setCustomView(alertLayout2)
                        .setConfirmText("Ok")
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                if(LuuNgayBatDau>LuuNgayKetThuc){
                                    new SweetAlertDialog(Seo_ChiTietSanPham.this, SweetAlertDialog.ERROR_TYPE)
                                            .setTitleText("Lỗi...")
                                            .setContentText("Ngày kết thúc không nhỏ hơn ngày bắt đầu!!")
                                            .show();
                                }
                                getTatCaPhieuNhapNgayHomNay(Api_custom.listDanhSachPhieuNhapHomNay,editNgayBatDau.getText().toString(),editNgayKetThuc.getText().toString(),intent.getStringExtra("banIDChiTietSanPham"));
                                adapter_lichSuNhapXuatChiTiet.notifyDataSetChanged();
                                mBottomSheetDialog.dismiss();
                                sDialog.dismissWithAnimation();
                            }
                        })
                        .setCancelButton("Hủy", new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                sDialog.dismissWithAnimation();
                            }
                        })
                        .show();




            }
        });

    }

    public void getTatCaPhieuNhapNgayHomNay(String urlService,final String NgayBatDau,final String NgayKetThuc,final String iDChiTietSanPham) {
        urlService = urlService+NgayBatDau+"/"+NgayKetThuc+"/"+iDChiTietSanPham;
        Log.d("palmcur",urlService);
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
                                    LichSuNhapXuatArraylist.add(new Model_LichSuNhapXuatChiTiet(jsonObject.getString("Ten"),jsonObject.getString("DonViTinh"),jsonObject.getString("SoLuongNhap"),jsonObject.getString("NgayTao")));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                            adapter_lichSuNhapXuatChiTiet=new Adapter_LichSuNhapXuatChiTiet(Seo_ChiTietSanPham.this,R.layout.item_listnhapxuatsanpham,LichSuNhapXuatArraylist);
                            listViewLichSuNhapXuat.setAdapter(adapter_lichSuNhapXuatChiTiet);
                            adapter_lichSuNhapXuatChiTiet.notifyDataSetChanged();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Seo_ChiTietSanPham.this, "error getTatCaPhieuNhapNgayHomNay", Toast.LENGTH_SHORT).show();
                    }
                }
        );

// Add the request to the RequestQueue.
        requestQueue.add(jsonArrayRequest);
    }//end
    public void getSoLuongTonNhapXuatTheoIDChiTietSanPham(String urlService,final String iDChiTietSanPham) {
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
                            String soLuongNhap="0";
                            for (int i = 0; i < response.length(); i++) {
                                try {
                                    JSONObject jsonObject = response.getJSONObject(i);
                                    txtDonViTinh1.setText(jsonObject.getString("DonViTinh"));
                                    txtDonViTinh2.setText(jsonObject.getString("DonViTinh"));
                                    txtDonViTinh3.setText(jsonObject.getString("DonViTinh"));
                                    txtSoLuongTon.setText(jsonObject.getString("SoLuongTon"));
                                    txtTenSanPham.setText(jsonObject.getString("Ten"));

                                    soLuongNhap=(Integer.parseInt(soLuongNhap)+Integer.parseInt(jsonObject.getString("SoLuongNhap")))+"";
                                    txtSoLuongNhap.setText(soLuongNhap);
                                    txtSoLuongXuat.setText("Chưa làm");
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                            adapter_lichSuNhapXuatChiTiet=new Adapter_LichSuNhapXuatChiTiet(Seo_ChiTietSanPham.this,R.layout.item_listnhapxuatsanpham,LichSuNhapXuatArraylist);
                            listViewLichSuNhapXuat.setAdapter(adapter_lichSuNhapXuatChiTiet);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Seo_ChiTietSanPham.this, "error getSoLuongTonNhapXuatTheoIDChiTietSanPham", Toast.LENGTH_SHORT).show();
                    }
                }
        );

// Add the request to the RequestQueue.
        requestQueue.add(jsonArrayRequest);
    }//end
}
