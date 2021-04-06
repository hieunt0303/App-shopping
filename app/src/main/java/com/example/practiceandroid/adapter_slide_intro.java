package com.example.practiceandroid;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;

public class adapter_slide_intro  extends PagerAdapter {

    public adapter_slide_intro(Context context) {
        this.context = context;
    }

    Context context;

    @NonNull
    @Override
    // hàm này tương tự cái hàm chuyển đổi xml -> view của BaseAdapter
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater= LayoutInflater.from(context);
        View view= inflater.inflate(R.layout.layout_slide_intro,null);

        TextView txtheading= view.findViewById(R.id.textView_intro_heading);
        TextView txtintro= view.findViewById(R.id.textView_intro);
        ImageView imgIntro= view.findViewById(R.id.imageView_intro);

        ImageView circle1= view.findViewById(R.id.button_circle1);
        ImageView circle2= view.findViewById(R.id.button_circle2);
        ImageView circle3= view.findViewById(R.id.button_circle3);

        Button btn_startshopping= view.findViewById(R.id.button_startshopping);
        btn_startshopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(context,MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK| Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

        switch (position){
            case 0:{
                circle1.setImageResource(R.drawable.selected);
                circle2.setImageResource(R.drawable.unselected);
                circle3.setImageResource(R.drawable.unselected);
                btn_startshopping.setClickable(false);
                break;

            }
            case 1:{
                imgIntro.setImageResource(R.drawable.welcome2);
                txtheading.setText("Select Your Product");
                txtintro.setText("Choose your desire product that you want to buy");
                circle1.setImageResource(R.drawable.unselected);
                circle2.setImageResource(R.drawable.selected);
                circle3.setImageResource(R.drawable.unselected);
                btn_startshopping.setEnabled(false);
                break;
            }
            case 2:{
                imgIntro.setImageResource(R.drawable.welcome3);
                txtheading.setText("Confirm Your Purchase");
                txtintro.setText("Make the final purchase and get the quick delivery");
                circle1.setImageResource(R.drawable.unselected);
                circle2.setImageResource(R.drawable.unselected);
                circle3.setImageResource(R.drawable.selected);
                btn_startshopping.setEnabled(true);
                break;

            }
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
