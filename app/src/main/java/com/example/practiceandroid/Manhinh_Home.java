package com.example.practiceandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import com.example.practiceandroid.Fragment.ViewPageAdapter;
import com.example.practiceandroid.function.getShowCartFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

public class Manhinh_Home extends AppCompatActivity {
    private ViewPager viewPager;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manhinh__home);

        viewPager = findViewById((R.id.View_paper));
        bottomNavigationView = findViewById(R.id.Bottom_navigation);
        ViewPageAdapter adapter = new ViewPageAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter((adapter));


        // SET CỨNG KHI CLICK BUTTON BUY NOW THÌ NÓ SẼ HIỂN HTỊ CÁI CART LUN
        if(getShowCartFragment.get().equals("true")) {
            Toast.makeText(Manhinh_Home.this,"getShowCartFragment",Toast.LENGTH_SHORT).show();
            bottomNavigationView.getMenu().findItem(R.id.menu_cart).setChecked(true);
            viewPager.setCurrentItem(1);
            getShowCartFragment.set("false");
        }

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        bottomNavigationView.getMenu().findItem(R.id.menu_home).setChecked(true);
                        break;
                    case 1:
                        bottomNavigationView.getMenu().findItem(R.id.menu_cart).setChecked(true);
                        break;
                    case 2:
                        bottomNavigationView.getMenu().findItem(R.id.menu_search).setChecked(true);
                        break;
                    case 3:
                        bottomNavigationView.getMenu().findItem(R.id.menu_speech).setChecked(true);
                        break;
                    case 4:
                        bottomNavigationView.getMenu().findItem(R.id.menu_contact).setChecked(true);
                        break;

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menu_home:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.menu_cart:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.menu_search:
                        viewPager.setCurrentItem(2);
                        break;
                    case R.id.menu_speech:
                        viewPager.setCurrentItem(3);
                        break;
                    case R.id.menu_contact:
                        viewPager.setCurrentItem(4);
                        break;
                }
                return true;
            }
        });


    }
}