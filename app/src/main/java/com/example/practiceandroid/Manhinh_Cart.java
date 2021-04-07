package com.example.practiceandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Manhinh_Cart extends AppCompatActivity {

    ImageButton btnhome,btntb,btnchat,btnacc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manhinh__cart);
        btnhome =  (ImageButton) findViewById(R.id.button_Home);
        btntb =  (ImageButton) findViewById(R.id.button_Nof);
        btnchat =  (ImageButton) findViewById(R.id.button_Mes);
        btnacc =  (ImageButton) findViewById(R.id.button_Per);
        btnhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mhbuy = new Intent(Manhinh_Cart.this , Manhinh_Home.class);
                startActivity(mhbuy);
            }
        });
        btntb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mhtb = new Intent(Manhinh_Cart.this , Manhinh_Nof.class);
                startActivity(mhtb);
            }
        });
        btnchat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mhchat = new Intent(Manhinh_Cart.this , Manhinh_Mes.class);
                startActivity(mhchat);
            }
        });
        btnacc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mhtt = new Intent(Manhinh_Cart.this , Manhinh_Per.class);
                startActivity(mhtt);
            }
        });
    }
}