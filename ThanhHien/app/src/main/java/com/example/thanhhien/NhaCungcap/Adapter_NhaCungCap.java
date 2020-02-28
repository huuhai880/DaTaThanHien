package com.example.thanhhien.NhaCungcap;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.thanhhien.R;
import java.util.ArrayList;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class Adapter_NhaCungCap extends RecyclerView.Adapter<Adapter_NhaCungCap.ViewHolder> {
        private Context mContext;
        ArrayList<Model_NhaCungCap> listNhaCungCap;

        public Adapter_NhaCungCap(Context mContext, ArrayList<Model_NhaCungCap> listNhaCungCap) {
            this.mContext = mContext;
            this.listNhaCungCap = listNhaCungCap;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater=LayoutInflater.from(mContext);
            View view= layoutInflater.inflate(R.layout.item_layoutlistnhacungcap,parent,false);
            ViewHolder viewHolder=new ViewHolder(view);
            return viewHolder;
        }
        @Override
        public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
            final Model_NhaCungCap model_nhaCungCap=listNhaCungCap.get(position);
            holder.txtNhaCungCap.setText(model_nhaCungCap.getTenNhaCungCap());

        }

        @Override
        public int getItemCount() {
            return listNhaCungCap.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder{
            private TextView txtNhaCungCap;
            private LinearLayout btnSuaNhaCungCap,btnXoaNhaCungCap,btnChiTiet;
            public ViewHolder(@NonNull View itemView) {

                super(itemView);
                txtNhaCungCap=itemView.findViewById(R.id.txtNhaCungCap);
                btnSuaNhaCungCap=itemView.findViewById(R.id.btnSuaNhaCungCap);
            }

        }

    }

