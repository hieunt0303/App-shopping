package com.example.practiceandroid.home.Comment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.practiceandroid.R;
import com.example.practiceandroid.home.class_Information_Product;

import java.util.List;

public class adapterComment extends BaseAdapter {
    Context context;
    int layout;
    List<classComment> arr_comment;

    ImageView img_Avatar;
    TextView txt_nameUser;
    TextView txt_content;

    ImageView imgStar1;
    ImageView imgStar2;
    ImageView imgStar3;
    ImageView imgStar4;
    ImageView imgStar5;

    public adapterComment(Context context, int layout, List<classComment> arr_comment) {
        this.context = context;
        this.layout = layout;
        this.arr_comment = arr_comment;
    }

    @Override
    public int getCount() {
        return arr_comment.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(layout, null);
        img_Avatar = convertView.findViewById(R.id.imageView_Avatar_cmt);
        txt_nameUser = convertView.findViewById(R.id.TextView_nameUser_cmt);
        txt_content = convertView.findViewById(R.id.Textview_cmt);

        imgStar1 = convertView.findViewById(R.id.imageview_star1_cmt);
        imgStar2 = convertView.findViewById(R.id.imageview_star2_cmt);
        imgStar3 = convertView.findViewById(R.id.imageview_star3_cmt);
        imgStar4 = convertView.findViewById(R.id.imageview_star4_cmt);
        imgStar5 = convertView.findViewById(R.id.imageview_star5_cmt);

        // gan gia tri
        // Lấy nguồn từ setting
        //img_Avatar.setImageResource();
        txt_nameUser.setText(arr_comment.get(position).getId_User());
        txt_content.setText(arr_comment.get(position).getCmt());
        setStar(arr_comment.get(position).sum_Star);

        return convertView;
    }

    public void setStar(String sumRatingbar){
        switch (sumRatingbar){
            case "0":
                imgStar1.setImageResource(R.drawable.icon_star_none);
                imgStar2.setImageResource(R.drawable.icon_star_none);
                imgStar3.setImageResource(R.drawable.icon_star_none);
                imgStar4.setImageResource(R.drawable.icon_star_none);
                imgStar5.setImageResource(R.drawable.icon_star_none);
                break;
            case "1":
                imgStar1.setImageResource(R.drawable.icon_star);
                imgStar2.setImageResource(R.drawable.icon_star_none);
                imgStar3.setImageResource(R.drawable.icon_star_none);
                imgStar4.setImageResource(R.drawable.icon_star_none);
                imgStar5.setImageResource(R.drawable.icon_star_none);
                break;
            case "1.5":
                imgStar1.setImageResource(R.drawable.icon_star);
                imgStar2.setImageResource(R.drawable.icon_star_haft);
                imgStar3.setImageResource(R.drawable.icon_star_none);
                imgStar4.setImageResource(R.drawable.icon_star_none);
                imgStar5.setImageResource(R.drawable.icon_star_none);
                break;
            case "2":
                imgStar1.setImageResource(R.drawable.icon_star);
                imgStar2.setImageResource(R.drawable.icon_star);
                imgStar3.setImageResource(R.drawable.icon_star_none);
                imgStar4.setImageResource(R.drawable.icon_star_none);
                imgStar5.setImageResource(R.drawable.icon_star_none);
                break;
            case "2.5":
                imgStar1.setImageResource(R.drawable.icon_star);
                imgStar2.setImageResource(R.drawable.icon_star);
                imgStar3.setImageResource(R.drawable.icon_star_haft);
                imgStar4.setImageResource(R.drawable.icon_star_none);
                imgStar5.setImageResource(R.drawable.icon_star_none);
                break;
            case "3":
                imgStar1.setImageResource(R.drawable.icon_star);
                imgStar2.setImageResource(R.drawable.icon_star);
                imgStar3.setImageResource(R.drawable.icon_star);
                imgStar4.setImageResource(R.drawable.icon_star_none);
                imgStar5.setImageResource(R.drawable.icon_star_none);
                break;
            case "3.5":
                imgStar1.setImageResource(R.drawable.icon_star);
                imgStar2.setImageResource(R.drawable.icon_star);
                imgStar3.setImageResource(R.drawable.icon_star);
                imgStar4.setImageResource(R.drawable.icon_star_haft);
                imgStar5.setImageResource(R.drawable.icon_star_none);
                break;
            case "4":
                imgStar1.setImageResource(R.drawable.icon_star);
                imgStar2.setImageResource(R.drawable.icon_star);
                imgStar3.setImageResource(R.drawable.icon_star);
                imgStar4.setImageResource(R.drawable.icon_star);
                imgStar5.setImageResource(R.drawable.icon_star_none);
                break;
            case "4.5":
                imgStar1.setImageResource(R.drawable.icon_star);
                imgStar2.setImageResource(R.drawable.icon_star);
                imgStar3.setImageResource(R.drawable.icon_star);
                imgStar4.setImageResource(R.drawable.icon_star);
                imgStar5.setImageResource(R.drawable.icon_star_haft);
                break;
            case "5":
                imgStar1.setImageResource(R.drawable.icon_star);
                imgStar2.setImageResource(R.drawable.icon_star);
                imgStar3.setImageResource(R.drawable.icon_star);
                imgStar4.setImageResource(R.drawable.icon_star);
                imgStar5.setImageResource(R.drawable.icon_star);
                break;
        }
    }
}
