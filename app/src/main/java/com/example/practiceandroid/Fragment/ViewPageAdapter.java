package com.example.practiceandroid.Fragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class ViewPageAdapter extends FragmentStatePagerAdapter {

    public ViewPageAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new com.example.practiceandroid.Fragment.HomeFragment();
            case 1:
                return new com.example.practiceandroid.Fragment.CartFragment();
            case 2:
                return new com.example.practiceandroid.Fragment.SearchFragment();
            case 3:
                return new SpeechFragment();
            case 4:
                return new ContactFragment();
            default:
                return new com.example.practiceandroid.Fragment.HomeFragment();
        }
    }

    @Override
    public int getCount() {
        return 5;
    }
}
