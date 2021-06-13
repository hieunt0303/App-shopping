package com.example.practiceandroid.Contact.Tablayout_Fragment.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.practiceandroid.Contact.Tablayout_Fragment.DeliveringFragment;
import com.example.practiceandroid.Contact.Tablayout_Fragment.FeedbackFragment;
import com.example.practiceandroid.Contact.Tablayout_Fragment.WaitingFragment;

public class adapter_tablayout_contact extends FragmentStatePagerAdapter {


    public adapter_tablayout_contact(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new WaitingFragment();
            case 1:
                return new DeliveringFragment();
            case 2:
                return new FeedbackFragment();
            default:
                return new WaitingFragment();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position){
            case 0:
                title = "Waiting";
                break;
            case 1:
                title = "Delivering";
                break;
            case 2:
                title = "Feedback";
                break;
        }
        return title;
    }
}
