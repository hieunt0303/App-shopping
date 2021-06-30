package com.example.practiceandroid.cart;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.TouchDelegate;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import com.example.practiceandroid.BuildConfig;
import com.example.practiceandroid.R;
import com.example.practiceandroid.class_ThongTinSanPham;
import com.example.practiceandroid.function.cart_function.getBool_btnDeleteClicked;
import com.example.practiceandroid.function.cart_function.getTotalPrice;
import com.example.practiceandroid.home.adapter_Information_product;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class adapter_cart extends BaseAdapter {
    Context myContext;
    int myLayout;
    ArrayList<class_cart> myarr_cart;
    DatabaseReference mData= FirebaseDatabase.getInstance().getReference();

    // Thông tin cần hiển thị
    public class ViewHolder{
        TextView txt_name_cart_product;
        TextView txt_price_cart_product;
        TextView txt_number_product;
        ImageView img_cart_product;
        Button btn_minus;
        Button btn_add;
        CheckBox cb_checked;
        CheckBox cb_delete;
    }




    public adapter_cart(Context myContext, int myLayout, ArrayList<class_cart> myarr_cart) {
        this.myContext = myContext;
        this.myLayout = myLayout;
        this.myarr_cart = myarr_cart;
    }

    @Override
    public int getCount() {
        return myarr_cart.size();
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
        ViewHolder viewHolder;



        if(convertView== null){
            viewHolder= new ViewHolder();

            LayoutInflater inflater = LayoutInflater.from(myContext);
            convertView = inflater.inflate(myLayout, null);

            // Khởi tạo giá tri
            viewHolder.txt_name_cart_product= convertView.findViewById(R.id.productname_cart);
            viewHolder.txt_price_cart_product= convertView.findViewById(R.id.productprice_cart);
            viewHolder.txt_number_product= convertView.findViewById(R.id.txt_number_cart);
            viewHolder.img_cart_product= convertView.findViewById(R.id.productimage_cart);

            viewHolder.btn_minus= convertView.findViewById(R.id.btn_minus_cart);
            viewHolder.btn_add= convertView.findViewById(R.id.btn_add_cart);
            viewHolder.cb_checked= convertView.findViewById(R.id.box_checked_cart);



            // NẾU CLICK VÀO CHỮ XÓA THÌ TẤT CẢ CÁC ITEM SẼ HIỆN CÁI CHECKBOX DELETE
            if(getBool_btnDeleteClicked.get_btnDelete().equals("true")){
                viewHolder.cb_delete.setVisibility(View.VISIBLE);
            }
            else{
                viewHolder.cb_delete.setVisibility(View.INVISIBLE);
            }

            convertView.setTag(viewHolder);
        }
        else {
            viewHolder= (ViewHolder) convertView.getTag();
        }




       // Ánh xạ
        viewHolder.txt_name_cart_product.setText(myarr_cart.get(position).name_cart_product);
        viewHolder.txt_price_cart_product.setText(myarr_cart.get(position).price_cart_product);
        viewHolder.txt_number_product.setText(myarr_cart.get(position).number_product);


        // Set background cho checkbox
        viewHolder.cb_checked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String total_before = getTotalPrice.getTotal();
                int number_product =Integer.parseInt( viewHolder.txt_number_product.getText().toString());
                if(viewHolder.cb_checked.isChecked()){
                    // Vô hiệu quá 2 cái button -,+
                    viewHolder.btn_add.setClickable(false);
                    viewHolder.btn_minus.setClickable(false);
                    viewHolder.btn_add.setBackground(ContextCompat.getDrawable(myContext,R.drawable.add_disable));
                    viewHolder.btn_minus.setBackground(ContextCompat.getDrawable(myContext,R.drawable.minus_disable));

                    viewHolder.cb_checked.setBackground(ContextCompat.getDrawable(myContext,R.drawable.checkbox_check1));
                    int total=  Integer.parseInt(total_before)+number_product* Integer.parseInt(viewHolder.txt_price_cart_product.getText().toString());
                    String total_after =String.valueOf(total);
                    mData.child("Cart").child("price_total").setValue(total_after);
                }
                else{
                    // Bỏ vô hiệu hóa 2 cái nút
                    viewHolder.btn_add.setClickable(true);
                    viewHolder.btn_minus.setClickable(true);
                    viewHolder.btn_add.setBackground(ContextCompat.getDrawable(myContext,R.drawable.add1));
                    viewHolder.btn_minus.setBackground(ContextCompat.getDrawable(myContext,R.drawable.minus1));

                    viewHolder.cb_checked.setBackground(ContextCompat.getDrawable(myContext,R.drawable.circlebox));
                    String total_after =String.valueOf( Integer.parseInt(total_before)-number_product* Integer.parseInt(viewHolder.txt_price_cart_product.getText().toString()));
                    mData.child("Cart").child("price_total").setValue(total_after);
                }
            }
        });



        // Set click cho 2 button -, +
        viewHolder.btn_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(viewHolder.txt_number_product.getText().toString().equals("0"))
                    Toast.makeText(myContext,"You can not do it.",Toast.LENGTH_SHORT).show();
                else{
                    int number_next= Integer.parseInt( viewHolder.txt_number_product.getText().toString())-1;
                    viewHolder.txt_number_product.setText(String.valueOf(number_next));
                }
            }
        });

        viewHolder.btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int number_next= Integer.parseInt( viewHolder.txt_number_product.getText().toString())+1;
                viewHolder.txt_number_product.setText(String.valueOf(number_next));
            }
        });


        StorageReference mStorage;
        String name_product= myarr_cart.get(position).name_cart_product;
        mStorage = FirebaseStorage.getInstance().getReference().child(name_product+"/"+ToLowerCase_Trim(name_product)+1+".png");
        try {
            final File localfile= File.createTempFile(ToLowerCase_Trim(name_product)+1,"png");
            mStorage.getFile(localfile)
                    .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            //Toast.makeText(HomeFragment.this,"Đưa ảnh lên thành công !!!",Toast.LENGTH_SHORT).show();
                            Bitmap bitmap= BitmapFactory.decodeFile(localfile.getAbsolutePath());
                            viewHolder.img_cart_product.setImageBitmap(bitmap);
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return convertView;
    }
    public  static String ToLowerCase_Trim(String data){
        //làm thành chữ thường và bỏ dấu cách
        String [] arrdata= data.split(" ");
        String trim="";
        for(int i=0;i<arrdata.length;i++){
            trim = trim + arrdata[i].toLowerCase();
        }
        return  trim;
    }
}
