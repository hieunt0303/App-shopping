package com.example.practiceandroid.home;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.practiceandroid.Manhinh_Home;
import com.example.practiceandroid.R;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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

    ImageView star_down_1;
    ImageView star_down_2;
    ImageView star_down_3;
    ImageView star_down_4;
    ImageView star_down_5;

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
        txt_numratingbar_comment= findViewById(R.id.textView_numRatingBar_comment);

        // Gan gia tri
        txt_nameCategory_detail.setText(intent.getStringExtra("Category_product"));
        txt_nameProduct_detail.setText(intent.getStringExtra("Name_product"));
        txt_Price_detail.setText(intent.getStringExtra("Price_product_real"));
        txt_numberRatingbar_detail.setText(intent.getStringExtra("Sum_Ratingbar"));
        txt_numberBought_detail.setText(intent.getStringExtra("Sum_Bought"));
        txt_numratingbar_comment.setText(intent.getStringExtra("Sum_Ratingbar"));

        //Set hình cho ngôi sao
        setImgNgoi_sao();

        btn_addtocart= findViewById(R.id.button_addtocart);
        btn_buynow= findViewById(R.id.button_buynow);

        // Set clik cho button ADDTOCART , BUY NOW
        btn_buynow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // viết code cho việc nhấn button Buy Now ở đây
                Toast.makeText(Detail_Information_Product.this,"Click button Buy Now", Toast.LENGTH_SHORT).show();
            }
        });
        btn_addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Viết code cho việc nhấn button Add to cart ở đây
                Toast.makeText(Detail_Information_Product.this,"Click button Add to cart", Toast.LENGTH_SHORT).show();
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

        star_down_1 = findViewById(R.id.icon_ngoisao1_detail_comment);
        star_down_2 = findViewById(R.id.icon_ngoisao2_detail_comment);
        star_down_3 = findViewById(R.id.icon_ngoisao3_detail_comment);
        star_down_4 = findViewById(R.id.icon_ngoisao4_detail_comment);
        star_down_5 = findViewById(R.id.icon_ngoisao5_detail_comment);

        switch (intent.getStringExtra("Sum_Ratingbar")){
            case "0":
                star_up_1.setImageResource(R.drawable.icon_star_none);
                star_up_2.setImageResource(R.drawable.icon_star_none);
                star_up_3.setImageResource(R.drawable.icon_star_none);
                star_up_4.setImageResource(R.drawable.icon_star_none);
                star_up_5.setImageResource(R.drawable.icon_star_none);

                star_down_1.setImageResource(R.drawable.icon_star_none);
                star_down_2.setImageResource(R.drawable.icon_star_none);
                star_down_3.setImageResource(R.drawable.icon_star_none);
                star_down_4.setImageResource(R.drawable.icon_star_none);
                star_down_5.setImageResource(R.drawable.icon_star_none);
                break;
            case "0.5":
                star_up_1.setImageResource(R.drawable.icon_star_haft);
                star_up_2.setImageResource(R.drawable.icon_star_none);
                star_up_3.setImageResource(R.drawable.icon_star_none);
                star_up_4.setImageResource(R.drawable.icon_star_none);
                star_up_5.setImageResource(R.drawable.icon_star_none);

                star_down_1.setImageResource(R.drawable.icon_star_haft);
                star_down_2.setImageResource(R.drawable.icon_star_none);
                star_down_3.setImageResource(R.drawable.icon_star_none);
                star_down_4.setImageResource(R.drawable.icon_star_none);
                star_down_5.setImageResource(R.drawable.icon_star_none);
                break;
            case "1":
                star_up_1.setImageResource(R.drawable.icon_star);
                star_up_2.setImageResource(R.drawable.icon_star_none);
                star_up_3.setImageResource(R.drawable.icon_star_none);
                star_up_4.setImageResource(R.drawable.icon_star_none);
                star_up_5.setImageResource(R.drawable.icon_star_none);

                star_down_1.setImageResource(R.drawable.icon_star);
                star_down_2.setImageResource(R.drawable.icon_star_none);
                star_down_3.setImageResource(R.drawable.icon_star_none);
                star_down_4.setImageResource(R.drawable.icon_star_none);
                star_down_5.setImageResource(R.drawable.icon_star_none);
                break;
            case "1.5":
                star_up_1.setImageResource(R.drawable.icon_star);
                star_up_2.setImageResource(R.drawable.icon_star_haft);
                star_up_3.setImageResource(R.drawable.icon_star_none);
                star_up_4.setImageResource(R.drawable.icon_star_none);
                star_up_5.setImageResource(R.drawable.icon_star_none);

                star_down_1.setImageResource(R.drawable.icon_star);
                star_down_2.setImageResource(R.drawable.icon_star_haft);
                star_down_3.setImageResource(R.drawable.icon_star_none);
                star_down_4.setImageResource(R.drawable.icon_star_none);
                star_down_5.setImageResource(R.drawable.icon_star_none);
                break;
            case "2":
                star_up_1.setImageResource(R.drawable.icon_star);
                star_up_2.setImageResource(R.drawable.icon_star);
                star_up_3.setImageResource(R.drawable.icon_star_none);
                star_up_4.setImageResource(R.drawable.icon_star_none);
                star_up_5.setImageResource(R.drawable.icon_star_none);

                star_down_1.setImageResource(R.drawable.icon_star);
                star_down_2.setImageResource(R.drawable.icon_star);
                star_down_3.setImageResource(R.drawable.icon_star_none);
                star_down_4.setImageResource(R.drawable.icon_star_none);
                star_down_5.setImageResource(R.drawable.icon_star_none);
                break;
            case "2.5":
                star_up_1.setImageResource(R.drawable.icon_star);
                star_up_2.setImageResource(R.drawable.icon_star);
                star_up_3.setImageResource(R.drawable.icon_star_haft);
                star_up_4.setImageResource(R.drawable.icon_star_none);
                star_up_5.setImageResource(R.drawable.icon_star_none);

                star_down_1.setImageResource(R.drawable.icon_star);
                star_down_2.setImageResource(R.drawable.icon_star);
                star_down_3.setImageResource(R.drawable.icon_star_haft);
                star_down_4.setImageResource(R.drawable.icon_star_none);
                star_down_5.setImageResource(R.drawable.icon_star_none);
                break;
            case "3":
                star_up_1.setImageResource(R.drawable.icon_star);
                star_up_2.setImageResource(R.drawable.icon_star);
                star_up_3.setImageResource(R.drawable.icon_star);
                star_up_4.setImageResource(R.drawable.icon_star_none);
                star_up_5.setImageResource(R.drawable.icon_star_none);

                star_down_1.setImageResource(R.drawable.icon_star);
                star_down_2.setImageResource(R.drawable.icon_star);
                star_down_3.setImageResource(R.drawable.icon_star);
                star_down_4.setImageResource(R.drawable.icon_star_none);
                star_down_5.setImageResource(R.drawable.icon_star_none);
                break;
            case "3.5":
                star_up_1.setImageResource(R.drawable.icon_star);
                star_up_2.setImageResource(R.drawable.icon_star);
                star_up_3.setImageResource(R.drawable.icon_star);
                star_up_4.setImageResource(R.drawable.icon_star_haft);
                star_up_5.setImageResource(R.drawable.icon_star_none);

                star_down_1.setImageResource(R.drawable.icon_star);
                star_down_2.setImageResource(R.drawable.icon_star);
                star_down_3.setImageResource(R.drawable.icon_star);
                star_down_4.setImageResource(R.drawable.icon_star_haft);
                star_down_5.setImageResource(R.drawable.icon_star_none);
                break;
            case "4":
                star_up_1.setImageResource(R.drawable.icon_star);
                star_up_2.setImageResource(R.drawable.icon_star);
                star_up_3.setImageResource(R.drawable.icon_star);
                star_up_4.setImageResource(R.drawable.icon_star);
                star_up_5.setImageResource(R.drawable.icon_star_none);

                star_down_1.setImageResource(R.drawable.icon_star);
                star_down_2.setImageResource(R.drawable.icon_star);
                star_down_3.setImageResource(R.drawable.icon_star);
                star_down_4.setImageResource(R.drawable.icon_star);
                star_down_5.setImageResource(R.drawable.icon_star_none);
                break;
            case "4.5":
                star_up_1.setImageResource(R.drawable.icon_star);
                star_up_2.setImageResource(R.drawable.icon_star);
                star_up_3.setImageResource(R.drawable.icon_star);
                star_up_4.setImageResource(R.drawable.icon_star);
                star_up_5.setImageResource(R.drawable.icon_star_haft);

                star_down_1.setImageResource(R.drawable.icon_star);
                star_down_2.setImageResource(R.drawable.icon_star);
                star_down_3.setImageResource(R.drawable.icon_star);
                star_down_4.setImageResource(R.drawable.icon_star);
                star_down_5.setImageResource(R.drawable.icon_star_haft);
                break;
            case "5":
                star_up_1.setImageResource(R.drawable.icon_star);
                star_up_2.setImageResource(R.drawable.icon_star);
                star_up_3.setImageResource(R.drawable.icon_star);
                star_up_4.setImageResource(R.drawable.icon_star);
                star_up_5.setImageResource(R.drawable.icon_star);

                star_down_1.setImageResource(R.drawable.icon_star);
                star_down_2.setImageResource(R.drawable.icon_star);
                star_down_3.setImageResource(R.drawable.icon_star);
                star_down_4.setImageResource(R.drawable.icon_star);
                star_down_5.setImageResource(R.drawable.icon_star);
                break;

        }
    }

}