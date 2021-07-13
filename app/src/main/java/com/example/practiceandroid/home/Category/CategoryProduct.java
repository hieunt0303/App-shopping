package com.example.practiceandroid.home.Category;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.practiceandroid.Manhinh_Home;
import com.example.practiceandroid.Manhinh_Login;
import com.example.practiceandroid.R;
import com.example.practiceandroid.admin_Home;
import com.example.practiceandroid.function.FIREBASE;
import com.example.practiceandroid.function.getNumberCategories;
import com.example.practiceandroid.home.Detail_Information_Product;
import com.example.practiceandroid.home.adapter_Information_product;
import com.example.practiceandroid.home.class_Information_Product;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CategoryProduct extends AppCompatActivity {
    TextView txt ;
    AlertDialog dialog;
    AlertDialog.Builder builder;
    String[] items={"Price Low to High","Price High to Low","Popular Items"};
    GridView gridView;
    TextView txtnameCategory;
    TextView txtnumberCategory;
    ImageView btnback;
    // hiển thị sản phẩm
    List<class_Information_Product> productArrayList;
    adapter_Information_product adapter_information_product_adminhome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_product);

        // NHẬN DATA ĐỂ BIẾT LÀ ĐANG Ở LOẠI NÀO TỪ GIAO DIỆN TRƯỚC
        Intent intent= getIntent();
        String nameCategory= intent.getStringExtra("nameCategory");
        String numberCategory= intent.getStringExtra("numberCategory");
        // ANH XA
        txt= findViewById(R.id.arrangeProduct_category);
        txtnameCategory= findViewById(R.id.nameCategory);
        txtnumberCategory = findViewById(R.id.numberCategoryProduct);
        btnback = findViewById(R.id.btn_back2);
        gridView= findViewById(R.id.gridviewCategoryProduct);
        productArrayList= new ArrayList<>();
        adapter_information_product_adminhome= new adapter_Information_product(CategoryProduct.this,R.layout.layout_sanpham,productArrayList);
        gridView.setAdapter(adapter_information_product_adminhome);

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Manhinh_Login.userlogin.getName_user().equals("admin"))
                    startActivity(new Intent(CategoryProduct.this, admin_Home.class));
                else
                    startActivity(new Intent(CategoryProduct.this, Manhinh_Home.class));
            }
        });
        txtnameCategory.setText(nameCategory);
        // MỞ RA 1 CÁI DIALOG ĐỂ CHỌN LÀ MUỐN SĂP XẾP THEO KIỂU NÀO
        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });
        builder= new AlertDialog.Builder((CategoryProduct.this));
        builder.setTitle("select choose");
        builder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                txt.setText(items[which]);
                // FUNCTION TO ARRANGE PRODUCT
                ArrangeProduct(items[which],nameCategory);
            }
        });
        dialog= builder.create();

        // HIEN THI SAN PHAM
        showData(nameCategory);
    }

    public void ArrangeProduct(String select,String nameCategory){
        switch (select){
            case "Price Low to High":{
                Collections.sort(productArrayList, new Comparator<class_Information_Product>() {
                    @Override
                    public int compare(class_Information_Product o1, class_Information_Product o2) {
                        return o1.getPrice_product_real().compareTo(o2.getPrice_product_real());
                    }
                });
                adapter_information_product_adminhome.notifyDataSetChanged();
                break;
            }
            case "Price High to Low":{
                Collections.sort(productArrayList, new Comparator<class_Information_Product>() {
                    @Override
                    public int compare(class_Information_Product o1, class_Information_Product o2) {
                        return o2.getPrice_product_real().compareTo(o1.getPrice_product_real());
                    }
                });
                adapter_information_product_adminhome.notifyDataSetChanged();
                break;
            }
            case "Popular Items":{
                productArrayList.clear();
                showData(nameCategory);
                break;
            }
        }
    }

    public void showData(String nameCategory){
        FIREBASE.MDATA.child("Products").child(nameCategory).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                productArrayList.add(new class_Information_Product(
                        snapshot.child("id_product").getValue().toString(),
                        snapshot.child("categories").getValue().toString(),
                        snapshot.child("name_product").getValue().toString(),
                        snapshot.child("price_product_real").getValue().toString(),
                        snapshot.child("discount").getValue().toString(),
                        snapshot.child("price_product_fake").getValue().toString(),
                        snapshot.child("Sum_Ratingbar").getValue().toString(),
                        Integer.valueOf(snapshot.child("in_stock").getValue().toString()),
                        Integer.valueOf(snapshot.child("Sum_Bought").getValue().toString()),
                        Boolean.valueOf(snapshot.child("favourite").getValue().toString()),
                        Boolean.valueOf(snapshot.child("add_to_cart").getValue().toString()),
                        snapshot.child("description").getValue().toString(),
                        snapshot.child("detail").getValue().toString(),
                        Integer.valueOf(snapshot.child("color1").getValue().toString()),
                        Integer.valueOf(snapshot.child("color2").getValue().toString()),
                        R.drawable.border_product
                ));
                adapter_information_product_adminhome.notifyDataSetChanged();
                //Toast.makeText(container.getContext(),"hieu",Toast.LENGTH_SHORT).show();
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