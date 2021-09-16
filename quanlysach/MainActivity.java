package com.example.quanlysach;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btnThemTacGia, btnDanhSachTacGia, btnQuanLySach;
    DbConnect dbConnect;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        laydoituong();

        //tạo database
        dbConnect = new DbConnect(this);
        //tạo bảng
        dbConnect.QueryData("CREATE TABLE IF NOT EXISTS TacGia(MaTacGia TEXT PRIMARY KEY, TenTacGia VARCHAR(200))");
        dbConnect.QueryData("CREATE TABLE IF NOT EXISTS Sach(MaSach TEXT PRIMARY KEY, TenSach VARCHAR(200), NgayXB VARCHAR(20), MaTacGia TEXT, CONSTRAINT FK_Sach_TacGia FOREIGN KEY (MaTacGia) REFERENCES TacGia(MaTacGia))");


        btnThemTacGia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogThemTacGia();
            }
        });

        btnDanhSachTacGia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, DanhSachTacGia.class) ;
                startActivity(i);
            }
        });

        btnQuanLySach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, QuanLySach.class) ;
                startActivity(i);
            }
        });
    }

    public void laydoituong(){
        btnThemTacGia = findViewById(R.id.btnThemTacGia);
        btnDanhSachTacGia = findViewById(R.id.btnDanhSachTacGia);
        btnQuanLySach = findViewById(R.id.btnQuanLySach);
    }

    public void DialogThemTacGia(){
        final Dialog dialogThemTacGia = new Dialog(MainActivity.this);
        dialogThemTacGia.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogThemTacGia.setContentView(R.layout.activity_them_tac_gia);

        final EditText editMaTacGia = dialogThemTacGia.findViewById(R.id.editMaTacGia);
        final EditText editTenTacGia = dialogThemTacGia.findViewById(R.id.editTenTacGia);

        Button btnXoaTrang = dialogThemTacGia.findViewById(R.id.btnXoaTrang);
        Button btnLuuTacGia = dialogThemTacGia.findViewById(R.id.btnLuuTacGia);

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
                dbConnect.QueryData("INSERT INTO TacGia VALUES ( '"+maTacgia+"' , '"+tenTacgia+"')");
                dialogThemTacGia.dismiss();
            }
        });
        dialogThemTacGia.show();
    }
}
