package com.example.practiceandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //Ánh xạ các thuộc tính
    GridView lvSanpham;
    ArrayList<class_ThongTinSanPham> arrThongtinsanpham;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvSanpham= findViewById(R.id.listViewSanpham);
        arrThongtinsanpham= new ArrayList<class_ThongTinSanPham>();
        arrThongtinsanpham.add(new class_ThongTinSanPham("iPhone",26000000,"vnd",R.drawable.phone12,R.drawable.avatar,R.drawable.icon_share,R.drawable.icon_heart,R.id.ratingBar));
        arrThongtinsanpham.add(new class_ThongTinSanPham("iPhone",10800000,"vnd",R.drawable.iphone_x_256gb_4,R.drawable.avatar,R.drawable.icon_share,R.drawable.icon_heart,R.id.ratingBar));
        arrThongtinsanpham.add(new class_ThongTinSanPham("Macboo",49500000,"vnd",R.drawable.macpro,R.drawable.avatar,R.drawable.icon_share,R.drawable.icon_heart,R.id.ratingBar));
        arrThongtinsanpham.add(new class_ThongTinSanPham("MXWV22020",55500000,"vnd",R.drawable.imac,R.drawable.avatar,R.drawable.icon_share,R.drawable.icon_heart,R.id.ratingBar));
        arrThongtinsanpham.add(new class_ThongTinSanPham("iPad ",12688000,"vnd",R.drawable.ipad,R.drawable.avatar,R.drawable.icon_share,R.drawable.icon_heart,R.id.ratingBar));
        arrThongtinsanpham.add(new class_ThongTinSanPham("Apple ",3190000,"vnd",R.drawable.airpod,R.drawable.avatar,R.drawable.icon_share,R.drawable.icon_heart,R.id.ratingBar));


        adapter_thongtinsanpham adapter= new adapter_thongtinsanpham(MainActivity.this,
                R.layout.layout_thongtintungsanpham,
                arrThongtinsanpham);

        lvSanpham.setAdapter(adapter);

    }
}