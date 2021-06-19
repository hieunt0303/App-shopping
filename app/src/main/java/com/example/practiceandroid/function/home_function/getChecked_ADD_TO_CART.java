package com.example.practiceandroid.function.home_function;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class getChecked_ADD_TO_CART {
    static  String boolAdd_To_Cart= "false" ;
    static  DatabaseReference mData= FirebaseDatabase.getInstance().getReference();

    public static String get( String Category, String IDproduct){

        mData.child("Products").child(Category).child(IDproduct).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if(snapshot.getKey().equals("add_to_cart"))
                    boolAdd_To_Cart= snapshot.getValue().toString();
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
        return boolAdd_To_Cart;
    }
    public static void set( String Category, String IDproduct,String bool){
        mData.child("Products").child(Category).child(IDproduct).child("add_to_cart").setValue(bool);
    }
}
