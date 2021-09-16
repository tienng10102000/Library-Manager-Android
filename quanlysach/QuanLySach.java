package com.example.quanlysach;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class QuanLySach extends AppCompatActivity {
    DbConnect dbConnect;
    List<TacGia> tacGiaList;
    List<Sach> sachList;
    ArrayAdapter<TacGia> tacGiaArrayAdapter;
    SachAdapter sachAdapter;
    Spinner spinner;
    DatePickerDialog datePickerDialog;
    EditText txtNgayXB, txtMaSach, txtTenSach;
    Button btnThemSach,btnChonNgay;
    String maSach, tenSach ,ngayXB, maTacGia;
    ListView listSach;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_sach);
        laydoituong();

        dbConnect = new DbConnect(this);

        //Lấy dữ liệu vào spinner
        tacGiaList = new ArrayList<>();
        tacGiaArrayAdapter = new ArrayAdapter<TacGia>(this,
                android.R.layout.simple_spinner_item, tacGiaList);
        tacGiaArrayAdapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(tacGiaArrayAdapter);
        layDSTacGia();
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maTacGia = tacGiaList.get(position).getMaTacGia();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        //Lấy dữ liệu vào listview
        sachList = new ArrayList<>();
        sachAdapter = new SachAdapter(this, R.layout.item_sach, sachList);
        listSach.setAdapter(sachAdapter);
        layDSSach();

        txtNgayXB = findViewById(R.id.txtNgayXB);
        txtNgayXB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    ChonNgay();
            }
        });
        btnChonNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChonNgay();
            }
        });
        //Thêm sách
        btnThemSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                maSach = txtMaSach.getText().toString();
                tenSach = txtTenSach.getText().toString();
                ngayXB = txtNgayXB.getText().toString();
                if(maSach != "" && tenSach !="" && ngayXB !=""){
                    dbConnect.QueryData("INSERT INTO Sach VALUES ( '"+maSach+"' , '"+tenSach+"', '"+ngayXB+"','"+maTacGia+"')");
                    XoaTrang();
                    layDSSach();
                }
                else Toast.makeText(QuanLySach.this, "Không thể thực hiện thêm sách", Toast.LENGTH_SHORT).show();

            }
        });


    }

    void laydoituong(){
        txtNgayXB = findViewById(R.id.txtNgayXB);
        txtMaSach = findViewById(R.id.txtMaSach);
        txtTenSach = findViewById(R.id.txtTenSach);
        btnChonNgay = findViewById(R.id.btnChonNgay);
        btnThemSach = findViewById(R.id.btnThemSach);
        listSach = findViewById(R.id.lsSach);
        spinner = findViewById(R.id.duLieuTacGia);
    }

    void XoaTrang(){
        txtNgayXB.setText("");
        txtMaSach.setText("");
        txtTenSach.setText("");
    }

    void ChonNgay(){
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR); // current year
        int mMonth = c.get(Calendar.MONTH); // current month
        int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
        // date picker dialog
        datePickerDialog = new DatePickerDialog(QuanLySach.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        // set day of month , month and year value in the edit text
                        txtNgayXB.setText(dayOfMonth + "/"
                                + (monthOfYear + 1) + "/" + year);
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    private void layDSTacGia() {
        //hiển thị
        final Cursor data = dbConnect.GetData("SELECT * FROM TacGia");
        tacGiaList.clear();// xoá mảng trước khi add để cập nhật lại dữ liệu mới thôi

        while (data.moveToNext()){
            String tenTacGia = data.getString(1);
            String maTacGia = data.getString(0);
            tacGiaList.add(new TacGia(maTacGia, tenTacGia));
        }
        tacGiaArrayAdapter.notifyDataSetChanged();
    }

    private void layDSSach() {
        //hiển thị
        final Cursor data = dbConnect.GetData("SELECT * FROM Sach");
        sachList.clear();// xoá mảng trước khi add để cập nhật lại dữ liệu mới thôi

        while (data.moveToNext()){
            String maSach = data.getString(0);
            String tenSach = data.getString(1);
            String ngayXB = data.getString(2);
            sachList.add(new Sach(maSach, tenSach,ngayXB));
        }
        sachAdapter.notifyDataSetChanged();
    }

}
