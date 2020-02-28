package com.example.thanhhien.BanHang.Seo_SanPhamDaChon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.thanhhien.BanHang.Seo_BanHangLe.Adapter_ListSanPhamBan;
import com.example.thanhhien.ChuyenDoiTongTien;
import com.example.thanhhien.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import cn.pedant.SweetAlert.SweetAlertDialog;
public class Seo_BanHang extends AppCompatActivity {
    public static ArrayList<Model_ListSanPhamBan> sanPhamArrayList;
    private Toolbar toolbar;
    public static Button btnTongTienThanhToan,btnTrove;
    private ArrayList<Model_ListSanPhamBan> mListSanPhamBan;
    private Adapter_SanPhamDaChon adapter_listSanPhamBan;
    private RecyclerView recyclerViewListSanPham;
    long SoTienNo=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.seo_banhang);
        setTitle("Bán hàng");

        AnhXa();
        onClick();

        mListSanPhamBan=new ArrayList<>();
        mListSanPhamBan.add(new Model_ListSanPhamBan("dhdh","ksks","10","300000","jsjsj","djjdj","jdjdj","cây"));
        mListSanPhamBan.add(new Model_ListSanPhamBan("dhdh","ksks","10","300000","jsjsj","djjdj","jdjdj","cây"));
        adapter_listSanPhamBan=new Adapter_SanPhamDaChon(Seo_BanHang.this,mListSanPhamBan);
        LinearLayoutManager layoutManager = new LinearLayoutManager(Seo_BanHang.this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewListSanPham.setLayoutManager(layoutManager);
        recyclerViewListSanPham.setAdapter(adapter_listSanPhamBan);




    }

    private void AnhXa() {
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        btnTongTienThanhToan=findViewById(R.id.btnTongTienThanhToan);
        btnTrove=findViewById(R.id.btnTrove);
        recyclerViewListSanPham=findViewById(R.id.recyclerViewListSanPham);
    }
    private void onClick(){
        btnTongTienThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    eventClickBanHang();
            }
        });
        btnTrove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
    // Hàm gửi dữ liệu về Activity1

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
    private void eventClickBanHang() {
        View view = getLayoutInflater().inflate(R.layout.item_layuotthanhtoanhoadon, null);
        ImageView btnclosedialog=view.findViewById(R.id.btnclosedialog);
        Button btnNhapHang=view.findViewById(R.id.btnNhapHang);
        final TextView txtNo=view.findViewById(R.id.txtNo);
        final TextView editDaTra=view.findViewById(R.id.editDaTra);
        TextView txtTongTien=view.findViewById(R.id.txtTongTien);
        btnNhapHang.setText("Thanh toán");

        editDaTra.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(editDaTra.getText().toString().trim().length()<=0){
                    editDaTra.setText("0");
                }
                else {
                    SoTienNo=0;
                    String TongTienChuyenDoi= ChuyenDoiTongTien.priceWithoutDecimal(Double.parseDouble(SoTienNo+""));
                    txtNo.setText(TongTienChuyenDoi);
                    if(SoTienNo<=0){
                        txtNo.setText("0");
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        final Dialog mBottomSheetDialog = new Dialog(Seo_BanHang.this, R.style.MaterialDialogSheet);
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
        btnNhapHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateFormat dateFormatter = new SimpleDateFormat("yyyyMMddHHmmss");
                dateFormatter.setLenient(false);
                Date today = new Date();
                final String MaHoaDonBanLe = dateFormatter.format(today);
                DateFormat dateFormatter2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                dateFormatter2.setLenient(false);
                Date today2= new Date();
                final String NgayTao = dateFormatter2.format(today2);

                new SweetAlertDialog(Seo_BanHang.this, SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("Xác nhận")
                        .setContentText("Xác nhận thanh toán")
                        .setConfirmText("Xác nhận")
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                mBottomSheetDialog.dismiss();
                                SoTienNo=0;
                                if(editDaTra.getText().toString().equals("0")){
                                }
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



}
