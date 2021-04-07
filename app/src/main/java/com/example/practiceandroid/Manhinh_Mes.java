package com.example.practiceandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Manhinh_Mes extends AppCompatActivity {

    ImageButton btngh,btntb,btnhome,btnacc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manhinh__mes);
        btngh =  (ImageButton) findViewById(R.id.button_Cart);
        btntb =  (ImageButton) findViewById(R.id.button_Nof);
        btnhome =  (ImageButton) findViewById(R.id.button_Home);
        btnacc =  (ImageButton) findViewById(R.id.button_Per);
        btngh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mhbuy = new Intent(Manhinh_Mes.this , Manhinh_Cart.class);
                startActivity(mhbuy);
            }
        });
        btntb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mhtb = new Intent(Manhinh_Mes.this , Manhinh_Nof.class);
                startActivity(mhtb);
            }
        });
        btnhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mhchat = new Intent(Manhinh_Mes.this , Manhinh_Home.class);
                startActivity(mhchat);
            }
        });
        btnacc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mhtt = new Intent(Manhinh_Mes.this , Manhinh_Per.class);
                startActivity(mhtt);
            }
        });
    }
}