package com.example.practiceandroid.adminHome;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.practiceandroid.Fragment.ContactFragment;
import com.example.practiceandroid.Fragment.SpeechFragment;

public class ViewPageAdapterAdmin extends FragmentStatePagerAdapter {
    public ViewPageAdapterAdmin(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new com.example.practiceandroid.adminHome.Fragment_adminHome();
            case 1:
                return new com.example.practiceandroid.adminHome.Fragment_adminChat();
            case 2:
                return new com.example.practiceandroid.adminHome.Fragment_adminAdd();
            case 3:
                return new com.example.practiceandroid.adminHome.Fragment_adminStatic();
            default:
                return new com.example.practiceandroid.adminHome.Fragment_adminHome();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }
}
