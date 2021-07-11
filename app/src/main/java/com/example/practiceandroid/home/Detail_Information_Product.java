package com.example.practiceandroid.home;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.practiceandroid.Fragment.CartFragment;
import com.example.practiceandroid.Manhinh_Home;
import com.example.practiceandroid.Manhinh_Login;
import com.example.practiceandroid.R;
import com.example.practiceandroid.function.FIREBASE;
import com.example.practiceandroid.function.getCurrent_Day_Time;
import com.example.practiceandroid.function.getShowCartFragment;
import com.example.practiceandroid.function.home_function.getChecked_ADD_TO_CART;
import com.example.practiceandroid.function.pushNotification;
import com.example.practiceandroid.home.Comment.adapterComment;
import com.example.practiceandroid.home.Comment.classComment;
import com.example.practiceandroid.shopping.activity_shopping;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class Detail_Information_Product extends AppCompatActivity {
    private ViewPager mViewPager;

    TextView txt_nameProduct_detail;
    TextView txt_nameCategory_detail;
    TextView txt_Price_detail;
    TextView txt_numberBought_detail;
    TextView txt_numberRatingbar_detail;

    TextView txt_numratingbar_comment;
    Intent intent;


    // Khai báo ngôi sao trên và dưới comment
    ImageView star_up_1;
    ImageView star_up_2;
    ImageView star_up_3;
    ImageView star_up_4;
    ImageView star_up_5;

    // COMMENT
    List<classComment> arr_cmt;
    adapterComment adapter_cmt;
    ListView listView_cmt;

    Button btn_addtocart;
    Button btn_buynow;

    DatabaseReference mData= FirebaseDatabase.getInstance().getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail__information__product);
        intent= getIntent();

        // Anh xa
        txt_nameProduct_detail= findViewById(R.id.name_product_detail);
        txt_nameCategory_detail = findViewById(R.id.header_name_category_product);
        txt_numberBought_detail = findViewById(R.id.numberBought_product_detail);
        txt_numberRatingbar_detail= findViewById(R.id.numberRatingbar_product_detail);
        txt_Price_detail= findViewById(R.id.price_product_detail);


        // Gan gia tri
        txt_nameCategory_detail.setText(intent.getStringExtra("Category_product"));
        txt_nameProduct_detail.setText(intent.getStringExtra("Name_product"));
        txt_Price_detail.setText(intent.getStringExtra("Price_product_real"));
        txt_numberRatingbar_detail.setText(intent.getStringExtra("Sum_Ratingbar"));
        txt_numberBought_detail.setText(intent.getStringExtra("Sum_Bought"));

        //Set hình cho ngôi sao
        setImgNgoi_sao();

        // COMMENT
        arr_cmt= new ArrayList<>();
        adapter_cmt= new adapterComment(this, R.layout.layout_item_comment,arr_cmt);
        listView_cmt= findViewById(R.id.listview_comment);
        listView_cmt.setAdapter(adapter_cmt);
        setComment(arr_cmt);

        btn_addtocart= findViewById(R.id.button_addtocart);
        btn_buynow= findViewById(R.id.button_buynow);

        // check xem thử sản phẩm đã có trong CART chưa
        String checked = getChecked_ADD_TO_CART.get(
                txt_nameCategory_detail.getText().toString()
                ,intent.getStringExtra("Id_product")
        );
        // Set clik cho button ADDTOCART , BUY NOW
        btn_buynow.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                // XÉT XEM THỬ ĐÃ TỒN TẠI TRONG CART CHƯA
                // NẾU RỒI CHUYỂN QUA GIAO DIỆN CART
                // NẾU CHƯA THÌ ADD VÀ CHUYỂN QUA GIAO DIỆN CART
                if(checked.equals("false")){
                    getChecked_ADD_TO_CART.set(
                            txt_nameCategory_detail.getText().toString(),
                            intent.getStringExtra("Id_product"),
                            "true"
                    );
                    // CHUYEN QUA GIAO DIEN CART
                    getShowCartFragment.set("true");
                    startActivity(new Intent(Detail_Information_Product.this, Manhinh_Home.class));
                    
                }
                else{
                    getShowCartFragment.set("true");
                    startActivity(new Intent(Detail_Information_Product.this, Manhinh_Home.class));
                }
            }
        });
        btn_addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // XÉT XEM THỬ ĐÃ TỒN TẠI TRONG CART CHƯA
                // NẾU RỒI THÌ THÔNG BÁO ĐÃ CÓ RỒI VÀ K ADD ĐC
                // NẾU CHƯA THÌ ADD VÀO VÀ THÔNG BÁO ĐÃ ADD
                if(checked.equals("false")){
                    getChecked_ADD_TO_CART.set(
                            txt_nameCategory_detail.getText().toString(),
                            intent.getStringExtra("Id_product"),
                            "true"
                    );
                    Toast.makeText(Detail_Information_Product.this,"The product has been added to cart", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(Detail_Information_Product.this,"The product has been added to cart",Toast.LENGTH_SHORT).show();
                }
            }
        });


        //mData.child("Products").child(intent.getStringExtra("Category_product"))
        //txt_nameProduct_detail.setText();

        initView();
        ImageView button_back= findViewById(R.id.button_back);
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Detail_Information_Product.this, Manhinh_Home.class));
            }
        });
    }
    public void setComment(List<classComment>arr){
        FIREBASE.MDATA.child("Comment").child(intent.getStringExtra("Category_product")).child(intent.getStringExtra("Id_product"))
                .addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                        classComment cmt= snapshot.getValue(classComment.class);
                        arr.add(cmt);
                        adapter_cmt.notifyDataSetChanged();
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
    private void initView() {
        mViewPager= findViewById(R.id.viewpager_detail_product);
        mViewPager.setAdapter(new adapter_tablayout_detailProduct(getSupportFragmentManager(),
                intent.getStringExtra("detail"),
                intent.getStringExtra("description"))
        );

        // HIỂN THỊ 3 CÁI ẢNH
        TabLayout tabLayout= findViewById(R.id.tabLayout_detail_product);
        tabLayout.setupWithViewPager(mViewPager);

        // HIỂN THỊ DESCIPTION VÀ DETAIL
        ViewPager viewPagerslideProduct= findViewById(R.id.viewpager_slideProduct);

        adapter_viewpager_slideproduct  adapterSlideproduct= new adapter_viewpager_slideproduct(
                this,
                intent.getStringExtra("Name_product")
        );

        viewPagerslideProduct.setAdapter(adapterSlideproduct);

    }


    public void setImgNgoi_sao(){
        star_up_1= findViewById(R.id.icon_ngoisao1_detail);
        star_up_2= findViewById(R.id.icon_ngoisao2_detail);
        star_up_3= findViewById(R.id.icon_ngoisao3_detail);
        star_up_4= findViewById(R.id.icon_ngoisao4_detail);
        star_up_5= findViewById(R.id.icon_ngoisao5_detail);



        switch (intent.getStringExtra("Sum_Ratingbar")){
            case "0":
                star_up_1.setImageResource(R.drawable.icon_star_none);
                star_up_2.setImageResource(R.drawable.icon_star_none);
                star_up_3.setImageResource(R.drawable.icon_star_none);
                star_up_4.setImageResource(R.drawable.icon_star_none);
                star_up_5.setImageResource(R.drawable.icon_star_none);


                break;
            case "0.5":
                star_up_1.setImageResource(R.drawable.icon_star_haft);
                star_up_2.setImageResource(R.drawable.icon_star_none);
                star_up_3.setImageResource(R.drawable.icon_star_none);
                star_up_4.setImageResource(R.drawable.icon_star_none);
                star_up_5.setImageResource(R.drawable.icon_star_none);


                break;
            case "1":
                star_up_1.setImageResource(R.drawable.icon_star);
                star_up_2.setImageResource(R.drawable.icon_star_none);
                star_up_3.setImageResource(R.drawable.icon_star_none);
                star_up_4.setImageResource(R.drawable.icon_star_none);
                star_up_5.setImageResource(R.drawable.icon_star_none);


                break;
            case "1.5":
                star_up_1.setImageResource(R.drawable.icon_star);
                star_up_2.setImageResource(R.drawable.icon_star_haft);
                star_up_3.setImageResource(R.drawable.icon_star_none);
                star_up_4.setImageResource(R.drawable.icon_star_none);
                star_up_5.setImageResource(R.drawable.icon_star_none);


                break;
            case "2":
                star_up_1.setImageResource(R.drawable.icon_star);
                star_up_2.setImageResource(R.drawable.icon_star);
                star_up_3.setImageResource(R.drawable.icon_star_none);
                star_up_4.setImageResource(R.drawable.icon_star_none);
                star_up_5.setImageResource(R.drawable.icon_star_none);


                break;
            case "2.5":
                star_up_1.setImageResource(R.drawable.icon_star);
                star_up_2.setImageResource(R.drawable.icon_star);
                star_up_3.setImageResource(R.drawable.icon_star_haft);
                star_up_4.setImageResource(R.drawable.icon_star_none);
                star_up_5.setImageResource(R.drawable.icon_star_none);


                break;
            case "3":
                star_up_1.setImageResource(R.drawable.icon_star);
                star_up_2.setImageResource(R.drawable.icon_star);
                star_up_3.setImageResource(R.drawable.icon_star);
                star_up_4.setImageResource(R.drawable.icon_star_none);
                star_up_5.setImageResource(R.drawable.icon_star_none);


                break;
            case "3.5":
                star_up_1.setImageResource(R.drawable.icon_star);
                star_up_2.setImageResource(R.drawable.icon_star);
                star_up_3.setImageResource(R.drawable.icon_star);
                star_up_4.setImageResource(R.drawable.icon_star_haft);
                star_up_5.setImageResource(R.drawable.icon_star_none);


                break;
            case "4":
                star_up_1.setImageResource(R.drawable.icon_star);
                star_up_2.setImageResource(R.drawable.icon_star);
                star_up_3.setImageResource(R.drawable.icon_star);
                star_up_4.setImageResource(R.drawable.icon_star);
                star_up_5.setImageResource(R.drawable.icon_star_none);


                break;
            case "4.5":
                star_up_1.setImageResource(R.drawable.icon_star);
                star_up_2.setImageResource(R.drawable.icon_star);
                star_up_3.setImageResource(R.drawable.icon_star);
                star_up_4.setImageResource(R.drawable.icon_star);
                star_up_5.setImageResource(R.drawable.icon_star_haft);


                break;
            case "5":
                star_up_1.setImageResource(R.drawable.icon_star);
                star_up_2.setImageResource(R.drawable.icon_star);
                star_up_3.setImageResource(R.drawable.icon_star);
                star_up_4.setImageResource(R.drawable.icon_star);
                star_up_5.setImageResource(R.drawable.icon_star);


                break;

        }
    }

}