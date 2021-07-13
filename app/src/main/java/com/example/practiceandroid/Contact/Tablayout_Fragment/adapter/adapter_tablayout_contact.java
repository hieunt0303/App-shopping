package com.example.practiceandroid.Contact.Tablayout_Fragment.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.practiceandroid.Contact.Tablayout_Fragment.FeedbackFragment;

public class adapter_tablayout_contact extends FragmentStatePagerAdapter {


    public adapter_tablayout_contact(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            default:
                return new FeedbackFragment();
        }
    }

    @Override
    public int getCount() {
        return 1;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = "Feedback";
        return title;
    }
}
