package com.example.practiceandroid.home;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.example.practiceandroid.AdminContent.AdminHome.Activity_Information_Product_admin;
import com.example.practiceandroid.Fragment.HomeFragment;
import com.example.practiceandroid.Manhinh_Home;
import com.example.practiceandroid.Manhinh_Login;
import com.example.practiceandroid.R;
import com.example.practiceandroid.function.FIREBASE;
import com.example.practiceandroid.function.ToLowerCase_Trim;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class adapter_Information_product extends BaseAdapter {
    Context context;
    int layout;
    List<class_Information_Product> InformationProduct;


    public adapter_Information_product(Context context, int layout, List<class_Information_Product> informationProduct) {
        this.context = context;
        this.layout = layout;
        InformationProduct = informationProduct;

    }

    @Override
    public int getCount() {
        return InformationProduct.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public class ViewHolder{
        ImageView imgHeart;
        ImageView imgProduct;
        TextView txtName;
        TextView txtPrice;
        TextView txtSumRatingbar;
        TextView txtBought;
        ImageView imgStar1;
        ImageView imgStar2;
        ImageView imgStar3;
        ImageView imgStar4;
        ImageView imgStar5;
        ImageView border_product;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null) {
            viewHolder= new ViewHolder();

            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(layout, null);

            // Anh xa
            viewHolder.imgHeart         = convertView.findViewById(R.id.img_heart);
            viewHolder.imgProduct       = convertView.findViewById(R.id.img_product);
            viewHolder.txtName          = convertView.findViewById(R.id.text_Name_Product);
            viewHolder.txtPrice         = convertView.findViewById(R.id.text_Price_Product);
            viewHolder.txtSumRatingbar  = convertView.findViewById(R.id.text_Ratingbar);
            viewHolder.txtBought        = convertView.findViewById(R.id.text_Bought);
            viewHolder.imgStar1         = convertView.findViewById(R.id.img_star1);
            viewHolder.imgStar2         = convertView.findViewById(R.id.img_star2);
            viewHolder.imgStar3         = convertView.findViewById(R.id.img_star3);
            viewHolder.imgStar4         = convertView.findViewById(R.id.img_star4);
            viewHolder.imgStar5         = convertView.findViewById(R.id.img_star5);
            viewHolder.border_product   = convertView.findViewById(R.id.border_product);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        //Lấy giá trị của các ngôi sao

        // Gan gia tri

        // nếu yêu thích thì trái tim đỏ còn k thì trái tim đen
        if(InformationProduct.get(position).favourite){
            viewHolder.imgHeart.setImageResource(R.drawable.icon_heart_red);
        }
        else
            viewHolder.imgHeart.setImageResource(R.drawable.icon_traitimden);

        // Lấy từ fireBase Storage
        StorageReference mStorage;
        String name_product= InformationProduct.get(position).name_product;
        mStorage = FirebaseStorage.getInstance().getReference().child(name_product+"/"+ ToLowerCase_Trim.set(name_product)+1+".png");
        try {
            final File localfile= File.createTempFile(ToLowerCase_Trim.set(name_product)+1,"png");
            mStorage.getFile(localfile)
                    .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            //Toast.makeText(HomeFragment.this,"Đưa ảnh lên thành công !!!",Toast.LENGTH_SHORT).show();
                            Bitmap bitmap= BitmapFactory.decodeFile(localfile.getAbsolutePath());
                            viewHolder.imgProduct.setImageBitmap(bitmap);

                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }

       // viewHolder.imgProduct.setImageResource(InformationProduct.get(position).img_header_product);
        //viewHolder.imgProduct.setImageResource(R.drawable.avatar_app);
        // Lấy từ firebase database
        viewHolder.txtName.setText(InformationProduct.get(position).name_product);
        viewHolder.txtPrice.setText(InformationProduct.get(position).price_product_real);
        viewHolder.txtSumRatingbar.setText(InformationProduct.get(position).Sum_Ratingbar);
        viewHolder.txtBought.setText(String.valueOf(InformationProduct.get(position).Sum_Bought));
        viewHolder.border_product.setImageResource(InformationProduct.get(position).Img_border);

        switch (viewHolder.txtSumRatingbar.getText().toString()){
            case "0":
                viewHolder.imgStar1.setImageResource(R.drawable.icon_star_none);
                viewHolder.imgStar2.setImageResource(R.drawable.icon_star_none);
                viewHolder.imgStar3.setImageResource(R.drawable.icon_star_none);
                viewHolder.imgStar4.setImageResource(R.drawable.icon_star_none);
                viewHolder.imgStar5.setImageResource(R.drawable.icon_star_none);
                break;
            case "1":
                viewHolder.imgStar1.setImageResource(R.drawable.icon_star);
                viewHolder.imgStar2.setImageResource(R.drawable.icon_star_none);
                viewHolder.imgStar3.setImageResource(R.drawable.icon_star_none);
                viewHolder.imgStar4.setImageResource(R.drawable.icon_star_none);
                viewHolder.imgStar5.setImageResource(R.drawable.icon_star_none);
                break;
            case "1.5":
                viewHolder.imgStar1.setImageResource(R.drawable.icon_star);
                viewHolder.imgStar2.setImageResource(R.drawable.icon_star_haft);
                viewHolder.imgStar3.setImageResource(R.drawable.icon_star_none);
                viewHolder.imgStar4.setImageResource(R.drawable.icon_star_none);
                viewHolder.imgStar5.setImageResource(R.drawable.icon_star_none);
                break;
            case "2":
                viewHolder.imgStar1.setImageResource(R.drawable.icon_star);
                viewHolder.imgStar2.setImageResource(R.drawable.icon_star);
                viewHolder.imgStar3.setImageResource(R.drawable.icon_star_none);
                viewHolder.imgStar4.setImageResource(R.drawable.icon_star_none);
                viewHolder.imgStar5.setImageResource(R.drawable.icon_star_none);
                break;
            case "2.5":
                viewHolder.imgStar1.setImageResource(R.drawable.icon_star);
                viewHolder.imgStar2.setImageResource(R.drawable.icon_star);
                viewHolder.imgStar3.setImageResource(R.drawable.icon_star_haft);
                viewHolder.imgStar4.setImageResource(R.drawable.icon_star_none);
                viewHolder.imgStar5.setImageResource(R.drawable.icon_star_none);
                break;
            case "3":
                viewHolder.imgStar1.setImageResource(R.drawable.icon_star);
                viewHolder.imgStar2.setImageResource(R.drawable.icon_star);
                viewHolder.imgStar3.setImageResource(R.drawable.icon_star);
                viewHolder.imgStar4.setImageResource(R.drawable.icon_star_none);
                viewHolder.imgStar5.setImageResource(R.drawable.icon_star_none);
                break;
            case "3.5":
                viewHolder.imgStar1.setImageResource(R.drawable.icon_star);
                viewHolder.imgStar2.setImageResource(R.drawable.icon_star);
                viewHolder.imgStar3.setImageResource(R.drawable.icon_star);
                viewHolder.imgStar4.setImageResource(R.drawable.icon_star_haft);
                viewHolder.imgStar5.setImageResource(R.drawable.icon_star_none);
                break;
            case "4":
                viewHolder.imgStar1.setImageResource(R.drawable.icon_star);
                viewHolder.imgStar2.setImageResource(R.drawable.icon_star);
                viewHolder.imgStar3.setImageResource(R.drawable.icon_star);
                viewHolder.imgStar4.setImageResource(R.drawable.icon_star);
                viewHolder.imgStar5.setImageResource(R.drawable.icon_star_none);
                break;
            case "4.5":
                viewHolder.imgStar1.setImageResource(R.drawable.icon_star);
                viewHolder.imgStar2.setImageResource(R.drawable.icon_star);
                viewHolder.imgStar3.setImageResource(R.drawable.icon_star);
                viewHolder.imgStar4.setImageResource(R.drawable.icon_star);
                viewHolder.imgStar5.setImageResource(R.drawable.icon_star_haft);
                break;
            case "5":
                viewHolder.imgStar1.setImageResource(R.drawable.icon_star);
                viewHolder.imgStar2.setImageResource(R.drawable.icon_star);
                viewHolder.imgStar3.setImageResource(R.drawable.icon_star);
                viewHolder.imgStar4.setImageResource(R.drawable.icon_star);
                viewHolder.imgStar5.setImageResource(R.drawable.icon_star);
                break;
            default:
                //Toast.makeText(context,InformationProduct.get(position).Sum_Ratingbar,Toast.LENGTH_SHORT).show();
        }

        viewHolder.imgHeart.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                // nếu đã yêu thích thì ....
                if(viewHolder.imgHeart.getDrawable().getConstantState().equals(viewHolder.imgHeart.getContext().getDrawable(R.drawable.icon_heart_red).getConstantState())){
                    viewHolder.imgHeart.setImageResource(R.drawable.icon_traitimden);
                    InformationProduct.get(position).favourite= false;
                    Update_Heart(InformationProduct.get(position).categories,InformationProduct.get(position).name_product,false);
                }
                else {
                    viewHolder.imgHeart.setImageResource(R.drawable.icon_heart_red);
                    InformationProduct.get(position).favourite= true;
                    Update_Heart(InformationProduct.get(position).categories,InformationProduct.get(position).name_product,true);
                }
            }
        });


        // SET SỰ KIỆN CLICK VÀO SẢN PHẨM ĐỂ HIỂN THỊ CHI TIẾT SẢN PHẨM
        // CÓ THỂ CLICK VÀO HÌNH HOẶC TÊN
        viewHolder.imgProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                if(Manhinh_Login.userlogin.getName_user().equals("admin")){
                    intent = new Intent(context, Activity_Information_Product_admin.class );
                }
                else{
                    intent = new Intent(context,Detail_Information_Product.class );

                }
                intent.putExtra("Name_product",InformationProduct.get(position).name_product);
                intent.putExtra("Category_product",InformationProduct.get(position).categories);
                intent.putExtra("Id_product",InformationProduct.get(position).id_product);
                intent.putExtra("Price_product_real",InformationProduct.get(position).price_product_real);
                intent.putExtra("Sum_Ratingbar",InformationProduct.get(position).Sum_Ratingbar);
                intent.putExtra("Sum_Bought",InformationProduct.get(position).Sum_Bought);
                intent.putExtra("description",InformationProduct.get(position).description);
                intent.putExtra("detail",InformationProduct.get(position).detail);
                intent.putExtra("discount",InformationProduct.get(position).discount);
                intent.putExtra("inStock",InformationProduct.get(position).in_stock);


                Toast.makeText(context,InformationProduct.get(position).name_product,Toast.LENGTH_SHORT).show();
                context.startActivity(intent);
            }
        });
        viewHolder.txtName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                if(Manhinh_Login.userlogin.getName_user().equals("admin")){
                    intent = new Intent(context, Activity_Information_Product_admin.class );
                }
                else{
                    intent = new Intent(context,Detail_Information_Product.class );

                }
                intent.putExtra("Name_product",InformationProduct.get(position).name_product);
                intent.putExtra("Category_product",InformationProduct.get(position).categories);
                intent.putExtra("Id_product",InformationProduct.get(position).id_product);
                intent.putExtra("Price_product_real",InformationProduct.get(position).price_product_real);
                intent.putExtra("Sum_Ratingbar",InformationProduct.get(position).Sum_Ratingbar);
                intent.putExtra("Sum_Bought",InformationProduct.get(position).Sum_Bought);
                intent.putExtra("description",InformationProduct.get(position).description);
                intent.putExtra("detail",InformationProduct.get(position).detail);
                intent.putExtra("discount",InformationProduct.get(position).discount);

                Toast.makeText(context,InformationProduct.get(position).name_product,Toast.LENGTH_SHORT).show();
                context.startActivity(intent);
            }
        });

        return convertView;
    }


    public  void Update_Heart(String categories,String name,Boolean logic){

        FIREBASE.MDATA.child("Products").child(categories).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if(snapshot.child("name_product").getValue().equals(name)){
                    String [] arr_decode= snapshot.child("name_product").getRef().toString().split("/");

                    Toast.makeText(context,arr_decode[5],Toast.LENGTH_SHORT).show();
                    FIREBASE.MDATA.child("Products").child(categories).child(arr_decode[5]).child("favourite").setValue(logic);

                    //mData.child("Products").child("name_product").updateChildren("favourite",false);
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
