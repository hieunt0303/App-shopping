package com.example.practiceandroid.function.cart_function;

import com.example.practiceandroid.function.getCategory_from_NameProduct;
import com.example.practiceandroid.function.getIDproduct_from_NameProduct;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class setADDTOCART_product {
    public static void set(String nameProduct, String Category, String IDproduct, boolean setChecked){
        DatabaseReference mData= FirebaseDatabase.getInstance().getReference();
        mData.child("Products").child(Category).child(IDproduct).child("add_to_cart").setValue(setChecked);
    }
}
