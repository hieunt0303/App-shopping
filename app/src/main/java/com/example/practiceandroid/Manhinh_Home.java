package com.example.practiceandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Manhinh_Home extends AppCompatActivity {

    ImageButton btngh,btntb,btnchat,btnacc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manhinh__home);
        btngh =  (ImageButton) findViewById(R.id.button_Cart);
        btntb =  (ImageButton) findViewById(R.id.button_Nof);
        btnchat =  (ImageButton) findViewById(R.id.button_Mes);
        btnacc =  (ImageButton) findViewById(R.id.button_Per);
        btngh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mhbuy = new Intent(Manhinh_Home.this , Manhinh_Cart.class);
                startActivity(mhbuy);
            }
        });
        btntb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mhtb = new Intent(Manhinh_Home.this , Manhinh_Nof.class);
                startActivity(mhtb);
            }
        });
        btnchat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mhchat = new Intent(Manhinh_Home.this , Manhinh_Mes.class);
                startActivity(mhchat);
            }
        });
        btnacc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mhtt = new Intent(Manhinh_Home.this , Manhinh_Per.class);
                startActivity(mhtt);
            }
        });
    }
}