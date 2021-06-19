package com.example.practiceandroid.home.Category;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.example.practiceandroid.R;


import org.w3c.dom.Text;

public class CategoryProduct extends AppCompatActivity {
    TextView txt ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_product);
        // MỞ RA 1 CÁI DIALOG ĐỂ CHỌN LÀ MUỐN SĂP XẾP THEO KIỂU NÀO
        txt= findViewById(R.id.arrangeProduct_category);
        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}