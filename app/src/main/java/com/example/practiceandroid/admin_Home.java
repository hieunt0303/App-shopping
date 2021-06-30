package com.example.practiceandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.example.practiceandroid.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class admin_Home extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__home);
        bottomNavigationView = findViewById(R.id.bottom_nav);
        SetupBottomNav();

    }
    private void SetupBottomNav() {
        NavController navController = Navigation.findNavController(this, R.id.fragment_container);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);
    }

}