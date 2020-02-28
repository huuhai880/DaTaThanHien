package com.example.thanhhien.BanHang.Seo_BanHangLe.ListSanPhamBanLe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thanhhien.BanHang.Seo_SanPhamDaChon.Seo_BanHang;
import com.example.thanhhien.BanHang.Seo_BanHangLe.Adapter_ListSanPhamBan;
import com.example.thanhhien.BanHang.Seo_SanPhamDaChon.Model_ListSanPhamBan;
import com.example.thanhhien.BanHang.Seo_BanHangLe.Adapter_ListSanPhamBanLe;
import com.example.thanhhien.BanHang.Seo_BanHangLe.Model_ListSanPhamBanLe;
import com.example.thanhhien.ChuyenDoiTongTien;
import com.example.thanhhien.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class Seo_ListSanPhamBanLe extends AppCompatActivity {
    private Toolbar toolbar;
    private GridView gridViewListQuyCach;
    private GridView gridViewListTinhChat;
    public static TextView txtTenQuyCach;
    private Button btnGioHang,btnTrove,btnThanhToan;
    private RecyclerView recyclerViewListSanPham;
    private Adapter_QuyCachVaTinhChat adapter_quyCachVaTinhChat;
    private Adapter_ListSanPhamBanLe adapter_listSanPhamBanLe;
    private ArrayList<Model_ListSanPhamBanLe> mListSanPhamBanLe;
    private Adapter_ListSanPhamBan adapter_listSanPhamBan;
    private ArrayList<Model_QuyCachVaTinhChat>mListQuyCach;
    private ArrayList<Model_ListSanPhamBan> mListSanPhamBan;
    private  int SoTienNo=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.seo_listsanphambanle);
        final Intent intent=getIntent();
        setTitle(intent.getStringExtra("TenSanPham"));
        AnhXa();
        mListQuyCach=new ArrayList<>();
        mListQuyCach.add(new Model_QuyCachVaTinhChat("55","U 40*2.5"));
        mListQuyCach.add(new Model_QuyCachVaTinhChat("55","U 30*2.5"));
        mListQuyCach.add(new Model_QuyCachVaTinhChat("55","U 60*2.5"));
        mListQuyCach.add(new Model_QuyCachVaTinhChat("55","U 50*2.5"));
        mListQuyCach.add(new Model_QuyCachVaTinhChat("55","U 20*2.5"));
        mListQuyCach.add(new Model_QuyCachVaTinhChat("55","U 10*2.5"));
        mListQuyCach.add(new Model_QuyCachVaTinhChat("55","U 55*2.5"));
        mListQuyCach.add(new Model_QuyCachVaTinhChat("CODESEONE","Khác "));
        adapter_quyCachVaTinhChat=new Adapter_QuyCachVaTinhChat(Seo_ListSanPhamBanLe.this,R.layout.item_layoutquycachvatinhchat,mListQuyCach);
        gridViewListQuyCach.setAdapter(adapter_quyCachVaTinhChat);

        //
        gridViewListQuyCach.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(Seo_ListSanPhamBanLe.this, ""+position, Toast.LENGTH_SHORT).show();
                Model_QuyCachVaTinhChat model_quyCachVaTinhChat=mListQuyCach.get(position);
                txtTenQuyCach.setText(model_quyCachVaTinhChat.getTenQuyCachVaTT());
                if(model_quyCachVaTinhChat.getMaQuyCachVaTT().equals("CODESEONE")){

                    eventClickBanHang();
                }

            }
        });
        //list sản phẩm
        mListSanPhamBanLe=new ArrayList<>();
        mListSanPhamBanLe.add(new Model_ListSanPhamBanLe("01","Sắt U"));
        mListSanPhamBanLe.add(new Model_ListSanPhamBanLe("01","Sắt V"));
        mListSanPhamBanLe.add(new Model_ListSanPhamBanLe("01","Sắt Ống"));
        mListSanPhamBanLe.add(new Model_ListSanPhamBanLe("01","Sắt Hộp"));
        mListSanPhamBanLe.add(new Model_ListSanPhamBanLe("01","Ống Kẽm"));
        mListSanPhamBanLe.add(new Model_ListSanPhamBanLe("01","Sắt Vuông"));
        mListSanPhamBanLe.add(new Model_ListSanPhamBanLe("01","Sắt I"));
        mListSanPhamBanLe.add(new Model_ListSanPhamBanLe("01","Tôn hoa sen"));
        adapter_listSanPhamBanLe=new Adapter_ListSanPhamBanLe(Seo_ListSanPhamBanLe.this,R.layout.item_layoutsanphambanle2,mListSanPhamBanLe);
        gridViewListTinhChat.setAdapter(adapter_listSanPhamBanLe);

        // list sản phẩm
        mListSanPhamBan=new ArrayList<>();
        mListSanPhamBan.add(new Model_ListSanPhamBan("dhdh","ksks","10","300000","jsjsj","djjdj","jdjdj","cây"));
        mListSanPhamBan.add(new Model_ListSanPhamBan("dhdh","ksks","10","300000","jsjsj","djjdj","jdjdj","cây"));
        adapter_listSanPhamBan=new Adapter_ListSanPhamBan(Seo_ListSanPhamBanLe.this,mListSanPhamBan);
        LinearLayoutManager layoutManager = new LinearLayoutManager(Seo_ListSanPhamBanLe.this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewListSanPham.setLayoutManager(layoutManager);
        recyclerViewListSanPham.setAdapter(adapter_listSanPhamBan);
        btnGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Seo_ListSanPhamBanLe.this, "jjjjjjjjjjj", Toast.LENGTH_SHORT).show();
                Intent intent1=new Intent(Seo_ListSanPhamBanLe.this, Seo_BanHang.class);
                startActivity(intent1);
            }
        });
        btnTrove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btnThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eventClickThanhToan();
            }
        });
    }
    private void AnhXa() {
        toolbar=(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        gridViewListQuyCach=findViewById(R.id.gridViewListQuyCach);
        gridViewListTinhChat=findViewById(R.id.gridViewListTinhChat);
        recyclerViewListSanPham=findViewById(R.id.recyclerViewListSanPham);
        txtTenQuyCach=findViewById(R.id.txtTenQuyCach);
        btnGioHang=findViewById(R.id.btnGioHang);
        btnTrove=findViewById(R.id.btnTrove);
        btnThanhToan=findViewById(R.id.btnThanhToan);
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

    private void eventClickBanHang() {

        View view = getLayoutInflater().inflate(R.layout.item_bottomdialogquycachkhac, null);
        ImageView btnclosedialog=view.findViewById(R.id.btnclosedialog);
        GridView gridViewListQuyCach=view.findViewById(R.id.gridViewListQuyCach);
        final Dialog mBottomSheetDialog = new Dialog(Seo_ListSanPhamBanLe.this, R.style.MaterialDialogSheet);
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
        final ArrayList<Model_QuyCachVaTinhChat> mListQuyCach2=new ArrayList<>();
        mListQuyCach2.add(new Model_QuyCachVaTinhChat("55","U 20*2.5"));
        mListQuyCach2.add(new Model_QuyCachVaTinhChat("55","U 50*2.5"));
        mListQuyCach2.add(new Model_QuyCachVaTinhChat("55","U 60*2.5"));
        mListQuyCach2.add(new Model_QuyCachVaTinhChat("55","U 30*2.5"));
        mListQuyCach2.add(new Model_QuyCachVaTinhChat("55","U 10*2.5"));
        mListQuyCach2.add(new Model_QuyCachVaTinhChat("55","U 55*2.5"));
        mListQuyCach2.add(new Model_QuyCachVaTinhChat("55","U 60*2.5"));
        mListQuyCach2.add(new Model_QuyCachVaTinhChat("55","U 20*2.5"));
        mListQuyCach2.add(new Model_QuyCachVaTinhChat("55","U 50*2.5"));
        mListQuyCach2.add(new Model_QuyCachVaTinhChat("55","U 60*2.5"));
        mListQuyCach2.add(new Model_QuyCachVaTinhChat("55","U 30*2.5"));
        mListQuyCach2.add(new Model_QuyCachVaTinhChat("55","U 10*2.5"));
        mListQuyCach2.add(new Model_QuyCachVaTinhChat("55","U 55*2.5"));
        mListQuyCach2.add(new Model_QuyCachVaTinhChat("55","U 60*2.5"));
        mListQuyCach2.add(new Model_QuyCachVaTinhChat("55","U 20*2.5"));
        mListQuyCach2.add(new Model_QuyCachVaTinhChat("55","U 50*2.5"));
        adapter_quyCachVaTinhChat=new Adapter_QuyCachVaTinhChat(Seo_ListSanPhamBanLe.this,R.layout.item_layoutlistsanphambanle,mListQuyCach2);
        gridViewListQuyCach.setAdapter(adapter_quyCachVaTinhChat);
        gridViewListQuyCach.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Model_QuyCachVaTinhChat model_quyCachVaTinhChat=mListQuyCach2.get(position);
                txtTenQuyCach.setText(model_quyCachVaTinhChat.getTenQuyCachVaTT());
                mBottomSheetDialog.dismiss();
            }
        });

    }

    private void eventClickThanhToan() {
        final LayoutInflater inflater=(LayoutInflater) Seo_ListSanPhamBanLe.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_layuotthanhtoanhoadon, null);
        ImageView btnclosedialog=view.findViewById(R.id.btnclosedialog);
        Button btnNhapHang=view.findViewById(R.id.btnNhapHang);
        Button btnNhapHangVaIn=view.findViewById(R.id.btnNhapHangVaIn);
        final TextView txtNo=view.findViewById(R.id.txtNo);
        final TextView editDaTra=view.findViewById(R.id.editDaTra);
        TextView txtTongTien=view.findViewById(R.id.txtTongTien);
        btnNhapHang.setText("Thanh toán");
        btnNhapHangVaIn.setText("Thanh toán và In");
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
        final Dialog mBottomSheetDialog = new Dialog(Seo_ListSanPhamBanLe.this, R.style.MaterialDialogSheet);
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

                new SweetAlertDialog(Seo_ListSanPhamBanLe.this, SweetAlertDialog.WARNING_TYPE)
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
