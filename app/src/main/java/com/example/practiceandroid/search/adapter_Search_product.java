package com.example.practiceandroid.search;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.practiceandroid.R;
import com.example.practiceandroid.home.Detail_Information_Product;
import com.example.practiceandroid.home.class_Information_Product;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class adapter_Search_product extends BaseAdapter {
    public Context context;
    int layout;
    List<class_Information_Product> class_search_products;
    StorageReference mStorage;

    public adapter_Search_product(Context context, int layout, List<class_Information_Product> class_search_products) {
        this.context = context;
        this.layout = layout;
        this.class_search_products = class_search_products;
    }

    @Override
    public int getCount() {
        return class_search_products.size();
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
        convertView= inflater.inflate(layout,null);

        // Anh xa

        ImageView img_product_search = convertView.findViewById(R.id.imageView_Product_search);
        TextView text_price_search= convertView.findViewById(R.id.textView_Price_search);
        TextView text_name_search = convertView.findViewById(R.id.textView_Name_search);
        TextView text_categories= convertView.findViewById(R.id.textView_Color_search);

        // gan gai tri

        text_name_search.setText(class_search_products.get(position).name_product);
        text_price_search.setText(class_search_products.get(position).price_product_real);
        text_categories.setText(class_search_products.get(position).categories);

        String name_product_search = text_name_search.getText().toString();
        mStorage = FirebaseStorage.getInstance().getReference().child(name_product_search+"/"+ToLowerCase_Trim(name_product_search)+1+".png");
        try {
            final File localfile= File.createTempFile(ToLowerCase_Trim(name_product_search)+1,"png");
            mStorage.getFile(localfile)
                    .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            //Toast.makeText(HomeFragment.this,"Đưa ảnh lên thành công !!!",Toast.LENGTH_SHORT).show();
                            Bitmap bitmap= BitmapFactory.decodeFile(localfile.getAbsolutePath());
                            img_product_search.setImageBitmap(bitmap);

                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }

        img_product_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,Detail_Information_Product.class );
                intent.putExtra("Name_product",class_search_products.get(position).name_product);
                intent.putExtra("Category_product",class_search_products.get(position).categories);
                intent.putExtra("Id_product",class_search_products.get(position).id_product);
                intent.putExtra("Price_product_real",class_search_products.get(position).price_product_real);
                intent.putExtra("Sum_Ratingbar",class_search_products.get(position).Sum_Ratingbar);
                intent.putExtra("Sum_Bought",class_search_products.get(position).Sum_Bought);
                intent.putExtra("description",class_search_products.get(position).description);
                intent.putExtra("detail",class_search_products.get(position).detail);

                Toast.makeText(context,class_search_products.get(position).name_product,Toast.LENGTH_SHORT).show();
                context.startActivity(intent);

                //Toast.makeText(context,"click!",Toast.LENGTH_SHORT).show();

            }
        });
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
