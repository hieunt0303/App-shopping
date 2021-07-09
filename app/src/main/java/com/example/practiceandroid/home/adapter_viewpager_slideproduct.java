package com.example.practiceandroid.home;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.practiceandroid.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;

public class adapter_viewpager_slideproduct  extends PagerAdapter {
    Context context;
    String nameproduct="";
    String category_product;
    StorageReference mStorage;

    ImageView imgview_slideProduct;
    ImageView imgview_slidehere;
    ImageView imgview_slidehere1;
    ImageView imgview_slidehere2;

    public adapter_viewpager_slideproduct(Context context,String name_product) {
        this.context = context;
        this.nameproduct= name_product;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater= LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.layout_slideproduct,container,false);

         imgview_slideProduct= view.findViewById(R.id.imageView_slideProduct);
         imgview_slidehere= view.findViewById(R.id.imageView_slide_slideProduct);
         imgview_slidehere1= view.findViewById(R.id.imageView_slide_slideProduct1);
         imgview_slidehere2= view.findViewById(R.id.imageView_slide_slideProduct2);

        ///////////////////



        switch (position){
            case 0:
                setImg_slide_product("1");
                imgview_slidehere.setImageResource(R.drawable.slidehere);
                imgview_slidehere1.setImageResource(R.drawable.slidenextback);
                imgview_slidehere2.setImageResource(R.drawable.slidenextback);
                break;
            case 1:
                setImg_slide_product("2");
                imgview_slidehere.setImageResource(R.drawable.slidenextback);
                imgview_slidehere1.setImageResource(R.drawable.slidehere);
                imgview_slidehere2.setImageResource(R.drawable.slidenextback);
                break;
            case 2:
                setImg_slide_product("3");
                imgview_slidehere.setImageResource(R.drawable.slidenextback);
                imgview_slidehere1.setImageResource(R.drawable.slidenextback);
                imgview_slidehere2.setImageResource(R.drawable.slidehere);
                break;
        }

//        imgview_slideProduct.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(context,nameproduct,Toast.LENGTH_SHORT).show();
//            }
//        });

        container.addView(view);


        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
    public void setImg_slide_product(String number_img){
        mStorage = FirebaseStorage.getInstance().getReference().child(nameproduct+"/"+ToLowerCase_Trim(nameproduct,number_img)+".png");
        try {
            final File localfile= File.createTempFile(ToLowerCase_Trim(nameproduct,number_img),"png");
            mStorage.getFile(localfile)
                    .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            //Toast.makeText(HomeFragment.this,"Đưa ảnh lên thành công !!!",Toast.LENGTH_SHORT).show();
                            Bitmap bitmap= BitmapFactory.decodeFile(localfile.getAbsolutePath());
                            imgview_slideProduct.setImageBitmap(bitmap);

                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public  static String ToLowerCase_Trim(String data,String number_img){
        //làm thành chữ thường và bỏ dấu cách
        String [] arrdata= data.split(" ");
        String trim="";
        for(int i=0;i<arrdata.length;i++){
            trim = trim + arrdata[i].toLowerCase();
        }
        return  trim+number_img;
    }
}
