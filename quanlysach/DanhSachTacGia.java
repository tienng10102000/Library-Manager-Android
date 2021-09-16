package com.example.quanlysach;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class DanhSachTacGia extends AppCompatActivity {
    DbConnect dbConnect;
    ArrayList<TacGia> arrayList;
    TacGiaAdapter adapter;
    ListView listView;
    boolean temp = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_tac_gia);

        listView = findViewById(R.id.danhsachTacGia);

        dbConnect = new DbConnect(this);

        arrayList = new ArrayList<>();
        adapter = new TacGiaAdapter(this, R.layout.item_tac_gia, arrayList);
        listView.setAdapter(adapter);

        getData();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(temp)
                    DialogCapNhatTacGia(arrayList.get(position).maTacGia,arrayList.get(position).maTacGia, arrayList.get(position).tenTacGia );
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                DialogXoaTacGia(arrayList.get(position).maTacGia,arrayList.get(position).maTacGia);
                return temp=true;
            }
        });

        findViewById(R.id.btnQuayVe).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void getData() {
        //hiển thị
        final Cursor data = dbConnect.GetData("SELECT * FROM TacGia");
        arrayList.clear();// xoá mảng trước khi add để cập nhật lại dữ liệu mới thôi

        while (data.moveToNext()){
            String tenTacGia = data.getString(1);
            String maTacGia = data.getString(0);

            arrayList.add(new TacGia(maTacGia, tenTacGia));
        }
        adapter.notifyDataSetChanged();
    }

    public void DialogCapNhatTacGia(final String maTacGiaCu, String maTacGia, String tenTacGia){
        final Dialog dialogTacGia = new Dialog(DanhSachTacGia.this);
        dialogTacGia.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogTacGia.setContentView(R.layout.activity_them_tac_gia);

        final EditText editMaTacGia = dialogTacGia.findViewById(R.id.editMaTacGia);
        final EditText editTenTacGia = dialogTacGia.findViewById(R.id.editTenTacGia);

        Button btnXoaTrang = dialogTacGia.findViewById(R.id.btnXoaTrang);
        Button btnLuuTacGia = dialogTacGia.findViewById(R.id.btnLuuTacGia);

        editMaTacGia.setText(maTacGia);
        editTenTacGia.setText(tenTacGia);
        btnLuuTacGia.setText("Cập nhật thông tin tác giả");
        btnXoaTrang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editMaTacGia.setText("");
                editTenTacGia.setText("");
            }
        });

        btnLuuTacGia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String maTacgia = editMaTacGia.getText().toString().trim();
                String tenTacgia = editTenTacGia.getText().toString().trim();
                dbConnect.QueryData("Update TacGia SET MaTacGia = '"+maTacgia
                                                +"', TenTacGia = '"+tenTacgia+"' WHERE MaTacGia = '"+maTacGiaCu+"'");
                dialogTacGia.dismiss();
                getData();
            }
        });
        dialogTacGia.show();
    }

    public void DialogXoaTacGia(final String maTacGiaCu, String tenTacGia){
        final AlertDialog.Builder builder = new AlertDialog.Builder(DanhSachTacGia.this);
        builder.setMessage("Bạn có đồng ý xoá tác giả "+ tenTacGia +"?");
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dbConnect.QueryData("DELETE FROM TacGia WHERE MaTacGia = '"+ maTacGiaCu +"'");
                getData();
            }
        });

        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.show();
    }
}
