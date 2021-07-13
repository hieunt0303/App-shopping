package com.example.practiceandroid.adminHome.AddProduct;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;


import com.example.practiceandroid.R;
import com.example.practiceandroid.adminHome.AddProduct.Adapter.AddProduct_Component;
import com.example.practiceandroid.adminHome.AddProduct.Adapter.CategoriesAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddProduct_Categories extends AppCompatActivity implements CategoriesAdapter.onCategoriesClickListener {
    @BindView(R.id.listCategories)
    RecyclerView rvCategories;
    CategoriesAdapter adapter;
    ArrayList<AddProduct_Component> list;

    @BindView(R.id.icon_Back_AddProduct_Categories)
    ImageButton imbBack;


    //Fire base hiển thị sản phẩm
    DatabaseReference mData;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product_categories);
        ButterKnife.bind(AddProduct_Categories.this);
        list = new ArrayList<>();
        setupRecyclerView();
        mData= FirebaseDatabase.getInstance().getReference();


        imbBack.setOnClickListener(v -> AddProduct_Categories.this.finish());

    }

    private void setupRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(AddProduct_Categories.this);
        rvCategories.setLayoutManager(linearLayoutManager);
        adapter = new CategoriesAdapter(getListCategories(), this);
        rvCategories.setAdapter(adapter);
    }


    private ArrayList<AddProduct_Component> getListCategories(){
        // Add firebase Data here
        list.add(new AddProduct_Component("Electronics"));
        list.add(new AddProduct_Component("Furniture"));
        list.add(new AddProduct_Component("Laptop"));
        list.add(new AddProduct_Component("Man"));
        list.add(new AddProduct_Component("Phones"));
        list.add(new AddProduct_Component("Shoes"));
        list.add(new AddProduct_Component("Watch"));
        list.add(new AddProduct_Component("Women"));
        return list;
    }

    @Override
    public void onItemListioner(int posittion) {
        String sCategory = list.get(posittion).getName();
        Intent intent = new Intent();
        intent.putExtra("Categories", sCategory);

        setResult(Activity.RESULT_OK, intent);

        AddProduct_Categories.this.finish();
    }
}