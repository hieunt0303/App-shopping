package com.example.practiceandroid.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.practiceandroid.R;

public class adapter_slide_header_home  extends PagerAdapter {
    Context context;

    public adapter_slide_header_home(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater= LayoutInflater.from(context);
        View view= inflater.inflate(R.layout.layout_slide_header_home,container,false);

        // Anh xa
        ImageView img_slide_header1= view.findViewById(R.id.icon_slide_header_home1);
        ImageView img_slide_header2= view.findViewById(R.id.icon_slide_header_home2);
        ImageView img_slide_header3= view.findViewById(R.id.icon_slide_header_home3);
        ImageView img_slideimage_header = view.findViewById(R.id.imageView_slide_header_home);

        switch (position){
            case 0:
                img_slideimage_header.setImageResource(R.drawable.slide_header_2);
                img_slide_header1.setImageResource(R.drawable.slidehere);
                img_slide_header2.setImageResource(R.drawable.iconslide_header_trang);
                img_slide_header3.setImageResource(R.drawable.iconslide_header_trang);
                break;
            case 1:
                img_slideimage_header.setImageResource(R.drawable.slide_header_1);
                img_slide_header1.setImageResource(R.drawable.iconslide_header_trang);
                img_slide_header2.setImageResource(R.drawable.slidehere);
                img_slide_header3.setImageResource(R.drawable.iconslide_header_trang);
                break;
            case 2:
                img_slideimage_header.setImageResource(R.drawable.slide_header_3);
                img_slide_header1.setImageResource(R.drawable.iconslide_header_trang);
                img_slide_header2.setImageResource(R.drawable.iconslide_header_trang);
                img_slide_header3.setImageResource(R.drawable.slidehere);
                break;



        }

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }
}
