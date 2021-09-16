package com.example.quanlysach;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class TacGiaAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<TacGia> tacGiaList;

    public TacGiaAdapter(Context context, int layout, List<TacGia> tacGiaList) {
        this.context = context;
        this.layout = layout;
        this.tacGiaList = tacGiaList;
    }


    @Override
    public int getCount() {
        return tacGiaList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolder{
        TextView txtMaTacGia, txtTenTacGia;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if(convertView == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);
            holder.txtMaTacGia = convertView.findViewById(R.id.txtMaTacGia);
            holder.txtTenTacGia = convertView.findViewById(R.id.txtTenTacGia);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        TacGia tacGia = tacGiaList.get(position);
        holder.txtMaTacGia.setText(tacGia.getMaTacGia());
        holder.txtTenTacGia.setText(tacGia.getTenTacGia());

        return convertView;
    }
}
