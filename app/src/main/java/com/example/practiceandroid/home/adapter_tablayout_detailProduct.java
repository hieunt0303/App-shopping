package com.example.practiceandroid.home;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.practiceandroid.home.Fragment.Fragment_description;
import com.example.practiceandroid.home.Fragment.Fragment_detail;

public class adapter_tablayout_detailProduct extends FragmentStatePagerAdapter {
    private String listtab[]= {"Description","Detail"};
    private Fragment_description fragment_description;
    private Fragment_detail fragment_detail;


    public adapter_tablayout_detailProduct(@NonNull FragmentManager fm,String detail, String decription) {
        super(fm);
        fragment_description= new Fragment_description(decription);
        fragment_detail= new Fragment_detail(detail);

    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if(position ==0)
        {
            return fragment_description;
        }
        else if( position== 1){
            return fragment_detail;
        }
        return null;
    }

    @Override
    public int getCount() {
        return listtab.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return  listtab[position];
    }
}
