package com.example.thanhhien.LichSuNhapXuat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thanhhien.ChuyenDoiTongTien;
import com.example.thanhhien.R;
import com.robertlevonyan.views.expandable.Expandable;

import java.util.ArrayList;

public class Adapter_LichSuNhapXuat extends RecyclerView.Adapter<Adapter_LichSuNhapXuat.ViewHolder> {
        private Context mContext;
        ArrayList<Model_LichSuNhapXuat> listLichSuNhapXuat;

        public Adapter_LichSuNhapXuat(Context mContext, ArrayList<Model_LichSuNhapXuat> listLichSuNhapXuat) {
            this.mContext = mContext;
            this.listLichSuNhapXuat = listLichSuNhapXuat;
        }

        @NonNull
        @Override
        public Adapter_LichSuNhapXuat.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater=LayoutInflater.from(mContext);
            View view= layoutInflater.inflate(R.layout.item_layoutlistnhapxuat,parent,false);
            Adapter_LichSuNhapXuat.ViewHolder viewHolder=new Adapter_LichSuNhapXuat.ViewHolder(view);
            return viewHolder;
        }
        @Override
        public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
            final Model_LichSuNhapXuat model_lichSuNhapXuat=listLichSuNhapXuat.get(position);
            holder.txtMaPhieuNhap.setText(model_lichSuNhapXuat.getIDPhieuNhap());
            holder.txtTenNhaCungCap.setText(model_lichSuNhapXuat.getTenNhaCungCap());
           holder.txtNhaCungCap.setText(model_lichSuNhapXuat.getTenNhaCungCap());
           holder.txtIDPhieuNhap.setText(model_lichSuNhapXuat.getIDPhieuNhap());
//           String NgayTao=model_lichSuNhapXuat.getNgayTao().substring(0,19).replace("T"," ");
           holder.txtNgayTao.setText(model_lichSuNhapXuat.getNgayTao());
           holder.txtNgayTaoPhieu.setText(model_lichSuNhapXuat.getNgayTao());
           holder.txtGhiChu.setText(model_lichSuNhapXuat.getGhiChu());

            String TongTienChuyenDoi= ChuyenDoiTongTien.priceWithoutDecimal(Double.parseDouble(model_lichSuNhapXuat.getTongTien()));
            holder.txtTongTien.setText(TongTienChuyenDoi+" VNĐ");
            String TongTienChuyenDoi2= ChuyenDoiTongTien.priceWithoutDecimal(Double.parseDouble(model_lichSuNhapXuat.getNo()));
           holder.txtNo.setText(TongTienChuyenDoi2+ " VNĐ");


        }

        @Override
        public int getItemCount() {
            return listLichSuNhapXuat.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder{
            private TextView txtMaPhieuNhap,txtNhaCungCap,txtIDPhieuNhap,txtTenNhaCungCap,txtNgayTao,txtNgayTaoPhieu,txtGhiChu,txtTongTien,txtNo;
            public ViewHolder(@NonNull View itemView) {

                super(itemView);
                txtMaPhieuNhap=itemView.findViewById(R.id.txtMaPhieuNhap);
                txtNhaCungCap=itemView.findViewById(R.id.txtNhaCungCap);
                txtIDPhieuNhap= itemView.findViewById(R.id.txtIDPhieuNhap);
                txtTenNhaCungCap= itemView.findViewById(R.id.txtTenNhaCungCap);
                txtNgayTao= itemView.findViewById(R.id.txtNgayTao);
                txtNgayTaoPhieu=itemView.findViewById(R.id.txtNgayTaoPhieu);
                txtGhiChu= itemView.findViewById(R.id.txtGhiChu);
                txtTongTien= itemView.findViewById(R.id.txtTongTien);
                txtTenNhaCungCap=itemView.findViewById(R.id.txtTenNhaCungCap);
                txtNo= itemView.findViewById(R.id.txtNo);

            }

        }

}
