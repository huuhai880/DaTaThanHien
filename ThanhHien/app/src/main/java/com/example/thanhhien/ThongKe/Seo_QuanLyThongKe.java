package com.example.thanhhien.ThongKe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.thanhhien.Api_custom;
import com.example.thanhhien.ChuyenDoiTongTien;
import com.example.thanhhien.R;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class Seo_QuanLyThongKe extends AppCompatActivity {
    private Toolbar toolbar;
    private TextView txtDoanhThuHomNay,txtNgayHomNay,txtDoanhThuBanLe,txtDoanhThuBanSi,
            txtDonBanSi,txtDonBanLe,btnBaoCaoLaiLo,btnChonNgayKhac,txtTongTienDaChi,
            txtDoanhThuThangNay,txtThuNhapThang,txtTongDon,txtTenDoanhThu;
    private int SoLuongDon,TongChiTrongThang;
    private Button btnNgayHomNay,btnNgayHomQua,btnBayNgayQua,btnBaMuoiNgayQua,btnNgayKhac;
    private int mYear, mMonth, mDay,LuuNgayBatDau,LuuNgayKetThuc;
    private long ThuNhapThang,TongTien;
    private LinearLayout btnChiTietDonBanLe,btnChiTietDonBanSi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.seo_quanlythongke);
        setTitle("Thống kê");
        AnhXa();
        // lấy tổng tiêng bán lẻ theo ngày
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        dateFormatter.setLenient(false);
        Date today = new Date();
        final String NgayHomNay = dateFormatter.format(today);
        //getTongTienBanLeTheoNgay(Api_custom.laytongtienbanletheongay,NgayHomNay,NgayHomNay);
        txtNgayHomNay.setText(NgayHomNay);
        // lấy tổng chi trong tháng này
        DateFormat dateFormatter2 = new SimpleDateFormat("yyyy-MM");
        dateFormatter2.setLenient(false);
        Date today2 = new Date();
        final String ThangNay = dateFormatter2.format(today2);
        //getTongTienBanLeTheoThang(Api_custom.laytongtienbanletheongay,ThangNay+"-01",NgayHomNay);

        // biểu đồ sản phẩm bán chạy
        PieChart mPieChart = (PieChart) findViewById(R.id.piechart);

        mPieChart.addPieSlice(new PieModel("Sắt V", 15, Color.parseColor("#FE6DA8")));
        mPieChart.addPieSlice(new PieModel("Sắt U20", 25, Color.parseColor("#56B7F1")));
        mPieChart.addPieSlice(new PieModel("Tôn lạnh xanh", 35, Color.parseColor("#CDA67F")));
        mPieChart.addPieSlice(new PieModel("Sắt V20", 9, Color.parseColor("#FED70E")));

        mPieChart.startAnimation();

    }

    private void AnhXa() {
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        txtDoanhThuHomNay=findViewById(R.id.txtDoanhThuHomNay);
        txtNgayHomNay=findViewById(R.id.txtNgayHomNay);
        txtDoanhThuBanLe=findViewById(R.id.txtDoanhThuBanLe);
        txtDoanhThuBanSi=findViewById(R.id.txtDoanhThuBanSi);
        txtDonBanSi=findViewById(R.id.txtDonBanSi);
        txtDonBanLe=findViewById(R.id.txtDonBanLe);
        txtTongTienDaChi=findViewById(R.id.txtTongTienDaChi);
        txtTenDoanhThu=findViewById(R.id.txtTenDoanhThu);
        btnBaoCaoLaiLo=findViewById(R.id.btnBaoCaoLaiLo);
        txtThuNhapThang=findViewById(R.id.txtThuNhapThang);
        txtDoanhThuThangNay=findViewById(R.id.txtDoanhThuThangNay);
        txtTongDon=findViewById(R.id.txtTongDon);
        btnBaoCaoLaiLo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                Intent intent=new Intent(Seo_QuanLyThongKe.this,Seo_ChiTietBaoCaoLaiLo.class);
                startActivity(intent);
            }
        });
        btnChonNgayKhac=findViewById(R.id.btnChonNgayKhac);
        btnChonNgayKhac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eventClickChonNgay();
            }
        });
        btnChiTietDonBanLe=findViewById(R.id.btnChiTietDonBanLe);
        btnChiTietDonBanSi=findViewById(R.id.btnChiTietDonBanSi);
        // sự kiện click xem chi tiết
        btnChiTietDonBanLe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                Intent intent=new Intent(Seo_QuanLyThongKe.this,Seo_ThongKeChiTietDonLeSi.class);
                intent.putExtra("Ma","0");
                startActivity(intent);
            }
        });
        btnChiTietDonBanSi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                Intent intent=new Intent(Seo_QuanLyThongKe.this,Seo_ThongKeChiTietDonLeSi.class);
                intent.putExtra("Ma","1");
                startActivity(intent);
            }
        });





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
        final Dialog mBottomSheetDialog = new Dialog(Seo_QuanLyThongKe.this, R.style.MaterialDialogSheet);
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
                TongTien=0;
                SoLuongDon=0;
                btnChonNgayKhac.setText("Hôm nay");
                DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
                dateFormatter.setLenient(false);
                Date today = new Date();
                final String NgayHomNay = dateFormatter.format(today);
                getTongTienBanLeTheoNgay(Api_custom.laytongtienbanletheongay,NgayHomNay,NgayHomNay);
                txtNgayHomNay.setText(NgayHomNay);
                mBottomSheetDialog.dismiss();
            }
        });
        // lấy ngày hôm qua
        btnNgayHomQua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TongTien=0;
                SoLuongDon=0;
                btnChonNgayKhac.setText("Hôm qua");
                txtTenDoanhThu.setText("Doanh thu ngày hôm qua");
                // lấy ngày hôm qua
                GregorianCalendar calendar = new GregorianCalendar();
                DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
                final String NgayHomQua ;
                calendar.add(calendar.DAY_OF_MONTH, -1);
                Date tomorrow = calendar.getTime();
                NgayHomQua = fmt.format(tomorrow);
                // lấy dữ liệu
                getTongTienBanLeTheoNgay(Api_custom.laytongtienbanletheongay,NgayHomQua,NgayHomQua);
                txtNgayHomNay.setText(NgayHomQua);
                mBottomSheetDialog.dismiss();

            }
        });
        // sự kiện lấy bảy ngày qua
        btnBayNgayQua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnChonNgayKhac.setText("Bảy ngay qua");
                TongTien=0;
                SoLuongDon=0;
                txtTenDoanhThu.setText("Doanh thu bảy ngày qua");
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
                getTongTienBanLeTheoNgay(Api_custom.laytongtienbanletheongay,BayNgayTruoc,NgayHomNay);
                txtNgayHomNay.setText("Từ: "+BayNgayTruoc+" ==> Đến: "+NgayHomNay);
                mBottomSheetDialog.dismiss();
            }
        });
        btnBaMuoiNgayQua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnChonNgayKhac.setText("Tháng này");
                TongTien=0;
                SoLuongDon=0;
                txtTenDoanhThu.setText("Doanh thu tháng này");
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
                // lấy dữ liệu
                getTongTienBanLeTheoNgay(Api_custom.laytongtienbanletheongay,DauThangNay+"01",NgayHomNay);
                txtNgayHomNay.setText("Từ: "+DauThangNay+"01"+" ==> Đến: "+NgayHomNay);
                mBottomSheetDialog.dismiss();
            }
        });

        btnNgayKhac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnChonNgayKhac.setText("Ngày khác");
                TongTien=0;
                SoLuongDon=0;
                txtTenDoanhThu.setText("Doanh thu");
                LayoutInflater inflater2 = getLayoutInflater();
                View alertLayout2 = inflater2.inflate(R.layout.ad_item_layoutngaythongke, null);
                final EditText editNgayBatDau = (EditText) alertLayout2.findViewById(R.id.editNgayBatDau);
                final EditText editNgayKetThuc = (EditText) alertLayout2.findViewById(R.id.editNgayKetThuc);
                editNgayBatDau.setInputType(InputType.TYPE_NULL);
                editNgayBatDau.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Calendar calendar=Calendar.getInstance();
                        DatePickerDialog datePickerDialog=new DatePickerDialog(Seo_QuanLyThongKe.this, new DatePickerDialog.OnDateSetListener() {
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
                        DatePickerDialog datePickerDialog = new DatePickerDialog(Seo_QuanLyThongKe.this,
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

                new SweetAlertDialog(Seo_QuanLyThongKe.this, SweetAlertDialog.NORMAL_TYPE)
                        .setTitleText("Chọn Ngày")
                        .setCustomView(alertLayout2)
                        .setConfirmText("Ok")
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                if(LuuNgayBatDau>LuuNgayKetThuc){
                                    new SweetAlertDialog(Seo_QuanLyThongKe.this, SweetAlertDialog.ERROR_TYPE)
                                            .setTitleText("Lỗi...")
                                            .setContentText("Ngày kết thúc không nhỏ hơn ngày bắt đầu!!")
                                            .show();
                                }
                                getTongTienBanLeTheoNgay(Api_custom.laytongtienbanletheongay,editNgayBatDau.getText().toString(),editNgayKetThuc.getText().toString());
                                txtNgayHomNay.setText("Từ: "+editNgayBatDau.getText()+" ==> Đến:"+editNgayKetThuc.getText());
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
    public void getTongTienBanLeTheoNgay(String urlService, final String NgayBatDau, final String NgayKetThuc){
        urlService=urlService+NgayBatDau+"/"+NgayKetThuc;
        RequestQueue requestQueue= Volley.newRequestQueue(Seo_QuanLyThongKe.this);
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
                                            if(jsonObject.getString("TongTien").equals("null")){
                                                TongTien=TongTien+0;
                                                txtDoanhThuBanLe.setText("0 VNĐ");
                                                txtDonBanLe.setText("0 Đơn");
                                            }else {
                                                String TongTienChuyenDoi= ChuyenDoiTongTien.priceWithoutDecimal(Double.parseDouble(jsonObject.getString("TongTien")));
                                                TongTien=TongTien+Long.parseLong(jsonObject.getString("TongTien"));
                                                txtDoanhThuBanLe.setText(TongTienChuyenDoi+" VNĐ");
                                                txtDonBanLe.setText(jsonObject.getString("SoLuongDonBanLe")+" Đơn");
                                                SoLuongDon=SoLuongDon+Integer.parseInt(jsonObject.getString("SoLuongDonBanLe"));
                                            }
                                            getTongTienBanSiTheoNgay(Api_custom.laytongtienbansitheongay,NgayBatDau,NgayKetThuc);


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
                        Toast.makeText(Seo_QuanLyThongKe.this, "Lỗi lấy thu nhập ngày", Toast.LENGTH_SHORT).show();
                    }
                }
        );

        // Add the request to the RequestQueue.
        requestQueue.add(jsonArrayRequest);
    }//end

    // lấy tổng tiền bán le theo tháng
    public void getTongTienBanLeTheoThang(String urlService, final String NgayBatDau, final String NgayKetThuc){
        urlService=urlService+NgayBatDau+"/"+NgayKetThuc;
        RequestQueue requestQueue= Volley.newRequestQueue(Seo_QuanLyThongKe.this);
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
                                    if(jsonObject.getString("TongTien").equals("null")){
                                        ThuNhapThang=ThuNhapThang+0;

                                    }else {
                                        ThuNhapThang=ThuNhapThang+Long.parseLong(jsonObject.getString("TongTien"));
                                    }
                                    getTongTienBanSiTheoThang(Api_custom.laytongtienbansitheongay,NgayBatDau,NgayKetThuc);
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
                        Toast.makeText(Seo_QuanLyThongKe.this, "Lỗi lấy thu nhập ngày", Toast.LENGTH_SHORT).show();
                    }
                }
        );

        // Add the request to the RequestQueue.
        requestQueue.add(jsonArrayRequest);
    }//end

    public void getTongTienBanSiTheoNgay(String urlService, String NgayBatDau, String NgayKetThuc){
        urlService=urlService+NgayBatDau+"/"+NgayKetThuc;
        RequestQueue requestQueue= Volley.newRequestQueue(Seo_QuanLyThongKe.this);
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
                                            if(jsonObject.getString("TonTienBanSi").equals("null")){
                                                TongTien=TongTien+0;
                                                txtDoanhThuBanSi.setText("0 VNĐ");
                                                String TongTienChuyenDoi2= ChuyenDoiTongTien.priceWithoutDecimal(Double.parseDouble(TongTien+""));
                                                txtDoanhThuHomNay.setText(TongTienChuyenDoi2+" VNĐ");
                                                txtDonBanSi.setText("0 Đơn");
                                                txtTongDon.setText(SoLuongDon+" Đơn");

                                            }else {
                                                String TongTienChuyenDoi= ChuyenDoiTongTien.priceWithoutDecimal(Double.parseDouble(jsonObject.getString("TonTienBanSi")));
                                                txtDoanhThuBanSi.setText(TongTienChuyenDoi+" VNĐ");
                                                SoLuongDon=SoLuongDon+Integer.parseInt(jsonObject.getString("SoLuongDonBanSi"));
                                                TongTien=TongTien+Long.parseLong(jsonObject.getString("TonTienBanSi"));
                                                String TongTienChuyenDoi2= ChuyenDoiTongTien.priceWithoutDecimal(Double.parseDouble(TongTien+""));
                                                txtDoanhThuHomNay.setText(TongTienChuyenDoi2+" VNĐ");
                                                txtTongDon.setText(SoLuongDon+" Đơn");
                                                txtDonBanSi.setText(jsonObject.getString("SoLuongDonBanSi")+" Đơn");



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
                        Toast.makeText(Seo_QuanLyThongKe.this, "Lỗi lấy thu nhập ngày", Toast.LENGTH_SHORT).show();
                    }
                }
        );

        // Add the request to the RequestQueue.
        requestQueue.add(jsonArrayRequest);
    }//end
    // lsy tong tien ban si theo thang
    public void getTongTienBanSiTheoThang(String urlService, String NgayBatDau, String NgayKetThuc){
        urlService=urlService+NgayBatDau+"/"+NgayKetThuc;
        RequestQueue requestQueue= Volley.newRequestQueue(Seo_QuanLyThongKe.this);
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
                                    if(jsonObject.getString("TonTienBanSi").equals("null")){
                                        ThuNhapThang=ThuNhapThang+0;
                                        txtDoanhThuBanSi.setText("0 VNĐ");
                                        String TongTienChuyenDoi2= ChuyenDoiTongTien.priceWithoutDecimal(Double.parseDouble(ThuNhapThang+""));
                                        txtDoanhThuThangNay.setText(TongTienChuyenDoi2);
                                    }else {
                                        ThuNhapThang=ThuNhapThang+Long.parseLong(jsonObject.getString("TonTienBanSi"));
                                        String TongTienChuyenDoi2= ChuyenDoiTongTien.priceWithoutDecimal(Double.parseDouble(ThuNhapThang+""));
                                        txtDoanhThuThangNay.setText(TongTienChuyenDoi2+" VNĐ");

                                    }
                                    DateFormat dateFormatter2 = new SimpleDateFormat("yyyy-MM");
                                    dateFormatter2.setLenient(false);
                                    Date today2 = new Date();
                                    final String ThangNay = dateFormatter2.format(today2);
                                    getTongTienChiTheoThang(Api_custom.laytongtiendachitheothang,ThangNay,ThangNay);
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
                        Toast.makeText(Seo_QuanLyThongKe.this, "Lỗi lấy thu nhập ngày", Toast.LENGTH_SHORT).show();
                    }
                }
        );

        // Add the request to the RequestQueue.
        requestQueue.add(jsonArrayRequest);
    }//end



    public void getTongTienChiTheoThang(String urlService, final String NgayBatDau, final String NgayKetThuc){
        urlService=urlService+NgayBatDau+"/"+NgayKetThuc;
        RequestQueue requestQueue= Volley.newRequestQueue(Seo_QuanLyThongKe.this);
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
                                    if(jsonObject.getString("TongChiTrongThang").equals("null")){
                                        txtTongTienDaChi.setText("0 VNĐ");
                                    }else {
                                        TongChiTrongThang=Integer.parseInt(jsonObject.getString("TongChiTrongThang"));
                                        String TongTienChuyenDoi= ChuyenDoiTongTien.priceWithoutDecimal(Double.parseDouble(jsonObject.getString("TongChiTrongThang")));
                                        txtTongTienDaChi.setText(TongTienChuyenDoi+" VNĐ");

                                    }
                                    String TongTienChuyenDoi2= ChuyenDoiTongTien.priceWithoutDecimal(Double.parseDouble((ThuNhapThang-TongChiTrongThang)+""));
                                    if(ThuNhapThang-TongChiTrongThang<=0){
                                        txtThuNhapThang.setText("0 VNĐ");
                                    }else {
                                        txtThuNhapThang.setText(TongTienChuyenDoi2+" VNĐ");
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
                        Toast.makeText(Seo_QuanLyThongKe.this, "Lỗi lấy tổng chi trong tháng", Toast.LENGTH_SHORT).show();
                    }
                }
        );

        // Add the request to the RequestQueue.
        requestQueue.add(jsonArrayRequest);
    }//end


















}
