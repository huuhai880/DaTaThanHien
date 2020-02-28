package com.example.thanhhien.QuanLyKho.GiaoDienQuanLyKho;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.thanhhien.R;

import java.util.ArrayList;

class Adapter_Kho extends BaseAdapter {
    private Context mContext;
    private int mLayout;
    private ArrayList<Model_kho> khoArrayList;

    public Adapter_Kho(Context mContext, int mLayout, ArrayList<Model_kho> khoArrayList) {
        this.mContext = mContext;
        this.mLayout = mLayout;
        this.khoArrayList = khoArrayList;
    }

    @Override
    public int getCount() {
        return khoArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    public class ViewHolder {
    private TextView txtTenKho;

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView==null){
            viewHolder=new ViewHolder();
            LayoutInflater inflater= (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(mLayout,null);
            viewHolder.txtTenKho=convertView.findViewById(R.id.txtTenKho);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }

        Model_kho model_kho=khoArrayList.get(position);
        viewHolder.txtTenKho.setText(model_kho.getTenKho());
        return convertView;
    }
}
