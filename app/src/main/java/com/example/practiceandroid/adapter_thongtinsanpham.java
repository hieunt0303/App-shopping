package com.example.practiceandroid;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import org.w3c.dom.Text;
import org.xmlpull.v1.XmlPullParser;

import java.util.ArrayList;


public class adapter_thongtinsanpham extends BaseAdapter {
    Context myContext;
    int myLayout;
    ArrayList<class_ThongTinSanPham> myarrThongtinsanpham;

    // giống như adapter bình thường cần 3 biến truyền vào thì khi custom cx phải có 3 biến
    adapter_thongtinsanpham(Context context, int layout, ArrayList<class_ThongTinSanPham> arrThongtinsanpham){
        myContext=context;
        myLayout=layout;
        myarrThongtinsanpham=arrThongtinsanpham;
    }

    @Override
    public int getCount() {
        // số lượng hiển thị các sản phẩm return
        return myarrThongtinsanpham.size();
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
        // trong hàm này chúng ta sẽ convert file xml -> view  để hiển thị trong list view
        LayoutInflater inflater = LayoutInflater.from(myContext);
        //LayoutInflater inflater = (LayoutInflater) myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // lấy context ( tức xml )
        convertView = inflater.inflate( myLayout,null);
        // từ bây h , convert view chính là view của mỗi item chứa layout trong cái layout_thongitnsanpham

        // ÁNh xạ và gán giá trị
        TextView txtName= convertView.findViewById(R.id.textViewName);
        txtName.setText(myarrThongtinsanpham.get(position).name);

        TextView txtPrice= convertView.findViewById(R.id.textViewPrice);
        txtPrice.setText(String.valueOf(myarrThongtinsanpham.get(position).price));

        TextView txtCurrency= convertView.findViewById(R.id.textViewCurrency);
        txtCurrency.setText(myarrThongtinsanpham.get(position).currency);

        ImageView imgPhone= convertView.findViewById(R.id.imageView);
        imgPhone.setImageResource(myarrThongtinsanpham.get(position).image_phone);

        RatingBar ratingBar=convertView.findViewById(R.id.ratingBar);
        ratingBar.setRating(myarrThongtinsanpham.get(position).rating_star);


        ImageButton imgAvatar= convertView.findViewById(R.id.image_avatar);
        imgAvatar.setImageResource(myarrThongtinsanpham.get(position).image_avatar);


        ImageButton imgHeart= convertView.findViewById(R.id.imagebutton_heart);
        imgHeart.setImageResource(myarrThongtinsanpham.get(position).image_heart);
        //ImageButton img_button_heart= findViewById(R.id.imagebutton_heart);
        imgHeart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgHeart.setImageResource(R.drawable.icon_heart_red);
            }
        });

        ImageButton imgShare= convertView.findViewById(R.id.imageButton_share);
        imgShare.setImageResource(myarrThongtinsanpham.get(position).image_share);

        return convertView;
    }
}
