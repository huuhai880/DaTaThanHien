package com.example.thanhhien.NhapXuatHang;


import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thanhhien.BanHang.Seo_SanPhamDaChon.Model_ListSanPhamBan;
import com.example.thanhhien.BanHang.Seo_BanHangLe.ListSanPhamBanLe.Adapter_QuyCachVaTinhChat;
import com.example.thanhhien.ChuyenDoiTongTien;
import com.example.thanhhien.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class Adapter_ListSanPhamNhap extends RecyclerView.Adapter<Adapter_ListSanPhamNhap.ViewHolder> {
        private Context mContext;
        ArrayList<Model_ListSanPhamBan> sanphamArrayList;
        private Adapter_QuyCachVaTinhChat adapter_quyCachVaTinhChat;
        long SoTienNo=0;
        public Adapter_ListSanPhamNhap(Context mContext, ArrayList<Model_ListSanPhamBan> sanphamArrayList) {
            this.mContext = mContext;
            this.sanphamArrayList = sanphamArrayList;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater=LayoutInflater.from(mContext);
            View view= layoutInflater.inflate(R.layout.item_layoutlistsanphamban,parent,false);
            ViewHolder viewHolder=new ViewHolder(view);
            return viewHolder;
        }

        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

            final Model_ListSanPhamBan model_kho=sanphamArrayList.get(position);
            // sét giá trị truyền vào
            holder.txtTenSanPham.setText(model_kho.getTenSanPham());
            String TongTienChuyenDoi= ChuyenDoiTongTien.priceWithoutDecimal(Double.parseDouble(model_kho.getGiaSanPham()+""));
            holder.txtGiaSanPham.setText(TongTienChuyenDoi+" VNĐ");
            holder.txtNhaCungCap.setText(model_kho.getNhaCungCap());
            holder.txtThuocTinh.setText(model_kho.getThuocTinh());
            holder.txtSoLuongSanPham.setText(model_kho.getSoLuong());
            holder.txtDonViTinh.setText(model_kho.getDonViTinh());
            final int SoLuong=Integer.parseInt(model_kho.getSoLuong());
            if(SoLuong>10){
                holder.txtTinhTrangSanPham.setText("Còn hàng");
                holder.txtTinhTrangSanPham.setTextColor(Color.GREEN);
            }else  if(SoLuong<10){
                if(SoLuong <1){
                    holder.txtTinhTrangSanPham.setText("Hết hàng");
                    holder.txtTinhTrangSanPham.setTextColor(Color.parseColor("#F44336"));
                    holder.onClickItem.setBackgroundColor(Color.parseColor("#C1C5C5C5"));
                }else {
                    holder.txtTinhTrangSanPham.setText("Gần hết hàng");
                    holder.txtTinhTrangSanPham.setTextColor(Color.parseColor("#FFC107"));
                }
            }
            // sự kiện onclick vào item recycal view
            holder.onClickItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "Đã đến với seo nè", Toast.LENGTH_SHORT).show();
                   eventClickBanHang();

                }
            });


            holder.setIsRecyclable(false);
        }

        @Override
        public int getItemCount() {
            return sanphamArrayList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder{
            private TextView txtTenSanPham,txtThuocTinh,txtGiaSanPham,txtNhaCungCap,txtSoLuongSanPham,txtDonViTinh,txtTinhTrangSanPham;
            private LinearLayout onClickItem;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                txtTenSanPham=itemView.findViewById(R.id.txtTenSanPham);
                txtGiaSanPham=itemView.findViewById(R.id.txtGiaSanPham);
                txtNhaCungCap=itemView.findViewById(R.id.txtNhaCungCap);
                txtThuocTinh=itemView.findViewById(R.id.txtThuocTinh);
                txtSoLuongSanPham=itemView.findViewById(R.id.txtSoLuongSanPham);
                txtDonViTinh=itemView.findViewById(R.id.txtDonViTinh);
                txtTinhTrangSanPham=itemView.findViewById(R.id.txtTinhTrangSanPham);
                onClickItem=itemView.findViewById(R.id.onClickItem);

            }


        }
        public interface OnItemClickedListener {
            void onItemClick(String MaSanPham, String TenSanPham, String GiaSanPham, String ThuocTinh);
        }

        private OnItemClickedListener onItemClickedListener;

        public void setOnItemClickedListener(OnItemClickedListener onItemClickedListener) {
            this.onItemClickedListener = onItemClickedListener;
        }
    // chọn sản phẩm thanh toán
    private void eventClickBanHang() {
        final LayoutInflater inflater=(LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_layoutnhapsanpham, null);
        ImageView btnclosedialog=view.findViewById(R.id.btnclosedialog);
        Button btnTiepTuc=view.findViewById(R.id.btnTiepTuc);
        Button btnThanhToan=view.findViewById(R.id.btnThanhToan);
        final Dialog mBottomSheetDialog = new Dialog(mContext, R.style.MaterialDialogSheet);
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
        btnTiepTuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBottomSheetDialog.dismiss();

            }
        });

     btnThanhToan.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             Intent intent=new Intent(mContext,Seo_ThanhToanNhapHang.class);
             mContext.startActivity(intent);
         }
     });

    }
    }
