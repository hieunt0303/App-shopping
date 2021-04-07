package com.example.practiceandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Manhinh_Per extends AppCompatActivity {

    ImageButton btngh,btntb,btnchat,btnhome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manhinh__per);
        btngh =  (ImageButton) findViewById(R.id.button_Cart);
        btntb =  (ImageButton) findViewById(R.id.button_Nof);
        btnchat =  (ImageButton) findViewById(R.id.button_Mes);
        btnhome =  (ImageButton) findViewById(R.id.button_Home);
        btngh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mhbuy = new Intent(Manhinh_Per.this , Manhinh_Cart.class);
                startActivity(mhbuy);
            }
        });
        btntb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mhtb = new Intent(Manhinh_Per.this , Manhinh_Nof.class);
                startActivity(mhtb);
            }
        });
        btnchat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mhchat = new Intent(Manhinh_Per.this , Manhinh_Mes.class);
                startActivity(mhchat);
            }
        });
        btnhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mhtt = new Intent(Manhinh_Per.this , Manhinh_Home.class);
                startActivity(mhtt);
            }
        });
    }
}