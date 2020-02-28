package com.example.thanhhien.HoaDon;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.thanhhien.Api_custom;
import com.example.thanhhien.ChuyenDoiTongTien;
import com.example.thanhhien.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cn.pedant.SweetAlert.SweetAlertDialog;


public class Adapter_HoaDon extends RecyclerView.Adapter<Adapter_HoaDon.ViewHolder> {
    private Context mContext;
    ArrayList<Model_HoaDon> listHoaDon;
    public Adapter_HoaDon(Context mContext, ArrayList<Model_HoaDon> listHoaDon) {
        this.mContext = mContext;
        this.listHoaDon = listHoaDon;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(mContext);
        View view= layoutInflater.inflate(R.layout.item_layoutchitiethoadon,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final Model_HoaDon model_hoaDon=listHoaDon.get(position);
        holder.txtMaHoaDon.setText(model_hoaDon.MaHoaDon);
        holder.txtTenKhachHang.setText(model_hoaDon.TenKhachHang+"");
        holder.txtNgayTao.setText(model_hoaDon.NgayTao);
        String TongTienChuyenDoi= ChuyenDoiTongTien.priceWithoutDecimal(Double.parseDouble(model_hoaDon.TongTien+""));
        holder.txtTongTien.setText(TongTienChuyenDoi+" VNĐ");

        holder.btnChiTietDonBanLe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        holder.setIsRecyclable(false);

    }

    @Override
    public int getItemCount() {
        return listHoaDon.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txtTenKhachHang,txtNgayTao,txtTongTien,txtMaHoaDon;
        private LinearLayout btnChiTietDonBanLe;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTenKhachHang=itemView.findViewById(R.id.txtTenKhachHang);
            txtNgayTao=itemView.findViewById(R.id.txtNgayTao);
            txtTongTien=itemView.findViewById(R.id.txtTongTien);
            btnChiTietDonBanLe=itemView.findViewById(R.id.btnChiTietDonBanLe);
            txtMaHoaDon=itemView.findViewById(R.id.txtMaHoaDon);


        }

    }


}
