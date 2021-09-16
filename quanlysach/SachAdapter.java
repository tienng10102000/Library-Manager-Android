package com.example.quanlysach;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class SachAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<Sach> sachList;

    public SachAdapter(Context context, int layout, List<Sach> sachList) {
        this.context = context;
        this.layout = layout;
        this.sachList = sachList;
    }

    @Override
    public int getCount() {
        return sachList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    class ViewHolder{
        TextView itemMaSach, itemTenSach, itemNgayXB;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null){
            holder = new SachAdapter.ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);
            holder.itemMaSach = convertView.findViewById(R.id.itemMaSach);
            holder.itemTenSach = convertView.findViewById(R.id.itemTenSach);
            holder.itemNgayXB = convertView.findViewById(R.id.itemNgayXB);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        Sach sach = sachList.get(position);
        holder.itemMaSach.setText(sach.getMaSach());
        holder.itemTenSach.setText(sach.getTenSach());
        holder.itemNgayXB.setText(sach.getNgayXB());

        return convertView;
    }
}
