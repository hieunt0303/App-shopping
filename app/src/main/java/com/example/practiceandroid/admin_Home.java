package com.example.practiceandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.viewpager.widget.ViewPager;

import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.practiceandroid.Fragment.ViewPageAdapter;
import com.example.practiceandroid.R;
import com.example.practiceandroid.adminHome.ViewPageAdapterAdmin;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class admin_Home extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__home);
        bottomNavigationView = findViewById(R.id.bottom_nav);
        viewPager = findViewById((R.id.View_paperadmin));
        ViewPageAdapterAdmin adapter = new ViewPageAdapterAdmin(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter((adapter));

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        bottomNavigationView.getMenu().findItem(R.id.adminHome).setChecked(true);
                        break;
                    case 1:
                        bottomNavigationView.getMenu().findItem(R.id.adminChat).setChecked(true);
                        break;
                    case 2:
                        bottomNavigationView.getMenu().findItem(R.id.adminAdd).setChecked(true);
                        break;
                    case 3:
                        bottomNavigationView.getMenu().findItem(R.id.adminStatic).setChecked(true);
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
                    case R.id.adminHome:
                        viewPager.setCurrentItem(0);

                        break;
                    case R.id.adminChat:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.adminAdd:
                        viewPager.setCurrentItem(2);
                        break;
                    case R.id.adminStatic:
                        viewPager.setCurrentItem(3);
                        break;

                }
                return true;
            }
        });



    }


}