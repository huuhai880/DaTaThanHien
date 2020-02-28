package com.example.thanhhien.NhapXuatHang;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thanhhien.ChuyenDoiTongTien;
import com.example.thanhhien.MainActivity;
import com.example.thanhhien.R;

import java.util.ArrayList;

public class Adapter_ChonSanPhamTheoNhaCungCap extends RecyclerView.Adapter<Adapter_ChonSanPhamTheoNhaCungCap.ViewHolder>{
    ArrayList<Model_ChonNguyenLieu> listSanPham;
    Context context;

    public Adapter_ChonSanPhamTheoNhaCungCap(ArrayList<Model_ChonNguyenLieu> listSanPham, Context context) {
        this.listSanPham = listSanPham;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View itemVew=layoutInflater.inflate(R.layout.item_chonsanphamtheo_ncc,parent,false);
        return new ViewHolder(itemVew);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.txtTenSanPham.setText(listSanPham.get(position).getTenSanPham());
        holder.txtGiaSanPham.setText(ChuyenDoiTongTien.priceWithoutDecimal(Double.parseDouble(listSanPham.get(position).getGiaNhap()))+" VNĐ");
        holder.editSoLuong.setText(MainActivity.listSanPhamDaChon.get(position).getSoLuongMua());

        holder.ivXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.listSanPhamDaChon.remove(position);
                notifyDataSetChanged();
            }
        });
        holder.editSoLuong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowDialog(position,holder.editSoLuong);
            }
        });
        holder.ibTang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.editSoLuong.setText(""+(Integer.parseInt(holder.editSoLuong.getText().toString().trim())+1));
                MainActivity.listSanPhamDaChon.set(position,new Model_ChonNguyenLieu(MainActivity.listSanPhamDaChon.get(position).getIDThuocTinh(), MainActivity.listSanPhamDaChon.get(position).getiDChiTietSanPham(), MainActivity.listSanPhamDaChon.get(position).getIDNhaCungCap(), MainActivity.listSanPhamDaChon.get(position).getGiaNhap(), MainActivity.listSanPhamDaChon.get(position).getGiaBan(), MainActivity.listSanPhamDaChon.get(position).getSoLuong(), MainActivity.listSanPhamDaChon.get(position).getIDSanPham(), MainActivity.listSanPhamDaChon.get(position).getIDKho(),""+(Integer.parseInt(MainActivity.listSanPhamDaChon.get(position).getSoLuongMua())+1), MainActivity.listSanPhamDaChon.get(position).getTenSanPham()));
            }
        });
        holder.ibGiam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.editSoLuong.getText().toString().trim().length()<=0){

                }else {
                    holder.editSoLuong.setText(""+(Integer.parseInt(holder.editSoLuong.getText().toString().trim())-1));
                    MainActivity.listSanPhamDaChon.set(position,new Model_ChonNguyenLieu(MainActivity.listSanPhamDaChon.get(position).getIDThuocTinh(), MainActivity.listSanPhamDaChon.get(position).getiDChiTietSanPham(), MainActivity.listSanPhamDaChon.get(position).getIDNhaCungCap(), MainActivity.listSanPhamDaChon.get(position).getGiaNhap(), MainActivity.listSanPhamDaChon.get(position).getGiaBan(), MainActivity.listSanPhamDaChon.get(position).getSoLuong(), MainActivity.listSanPhamDaChon.get(position).getIDSanPham(), MainActivity.listSanPhamDaChon.get(position).getIDKho(),""+(Integer.parseInt(MainActivity.listSanPhamDaChon.get(position).getSoLuongMua())-1), MainActivity.listSanPhamDaChon.get(position).getTenSanPham()));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listSanPham.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView ivXoa;
        TextView txtTenSanPham,txtGiaSanPham;
        ImageButton ibGiam,ibTang;
        EditText editSoLuong;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivXoa=itemView.findViewById(R.id.ivXoa);
            txtTenSanPham=itemView.findViewById(R.id.txtTenSanPham);
            txtGiaSanPham=itemView.findViewById(R.id.txtGiaSanPham);
            ibGiam=itemView.findViewById(R.id.ibGiam);
            ibTang=itemView.findViewById(R.id.ibTang);
            editSoLuong=itemView.findViewById(R.id.editSoLuong);

        }

    }
    private   void ShowDialog(final int position, final EditText text){
        final androidx.appcompat.app.AlertDialog alertDialog=new androidx.appcompat.app.AlertDialog.Builder(context).create();
        View viewDL = LayoutInflater.from(context).inflate(R.layout.dialog_themsoluong, null);

        final EditText etSoLuong;
        Button btnHuy,btnXacNhan;
        etSoLuong=viewDL.findViewById(R.id.etSoLuong);
        btnHuy=viewDL.findViewById(R.id.btnHuy);
        btnXacNhan=viewDL.findViewById(R.id.btnXacNhan);
        alertDialog.setView(viewDL);
        alertDialog.show();

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etSoLuong.getText().toString().isEmpty()){
                    Toast.makeText(context, "Bạn chưa nhập số lượng", Toast.LENGTH_SHORT).show();
                }
                else if((Integer.parseInt(etSoLuong.getText().toString().trim()))<=0){
                    Toast.makeText(context, "Số lượng không hợp lệ", Toast.LENGTH_SHORT).show();
                }
                else {
                    MainActivity.listSanPhamDaChon.set(position,new Model_ChonNguyenLieu(MainActivity.listSanPhamDaChon.get(position).getIDThuocTinh(), MainActivity.listSanPhamDaChon.get(position).getiDChiTietSanPham(), MainActivity.listSanPhamDaChon.get(position).getIDNhaCungCap(), MainActivity.listSanPhamDaChon.get(position).getGiaNhap(), MainActivity.listSanPhamDaChon.get(position).getGiaBan(), MainActivity.listSanPhamDaChon.get(position).getSoLuong(), MainActivity.listSanPhamDaChon.get(position).getIDSanPham(), MainActivity.listSanPhamDaChon.get(position).getIDKho(),etSoLuong.getText().toString().trim(), MainActivity.listSanPhamDaChon.get(position).getTenSanPham()));
                    text.setText(etSoLuong.getText().toString().trim());
                    alertDialog.dismiss();
                }


            }
        });
    }
}


