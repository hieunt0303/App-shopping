package com.example.practiceandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

public class activity_slide_intro extends AppCompatActivity {

    ViewPager viewPager;
    adapter_slide_intro adapter_slide_intro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide_intro);

        viewPager=findViewById(R.id.viewpaper);
        adapter_slide_intro= new adapter_slide_intro(this);
        viewPager.setAdapter(adapter_slide_intro);
        if(isOpenAlread())
        {
            Intent intent= new Intent(activity_slide_intro.this,MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK| Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
        else{
            SharedPreferences.Editor editor= getSharedPreferences("slide",MODE_PRIVATE).edit();
            editor.putBoolean("slide",true);
            editor.commit();
        }
    }

    private boolean isOpenAlread() {
        SharedPreferences sharedPreferences=getSharedPreferences("slide",MODE_PRIVATE);
        boolean re= sharedPreferences.getBoolean("slide",false);
        return re;
    }
}