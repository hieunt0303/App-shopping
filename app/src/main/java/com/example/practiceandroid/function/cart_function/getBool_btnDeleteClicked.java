package com.example.practiceandroid.function.cart_function;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class getBool_btnDeleteClicked {
    static String check="false";
    static DatabaseReference mData= FirebaseDatabase.getInstance().getReference();

    public void setBtnDelete_clicked(Boolean bool){
        mData.child("Cart").child("btnDelete_clicked").setValue(bool);
    }

    public  static  String get_btnDelete(){
        mData.child("Cart").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if(snapshot.getKey().equals("btnDelete_clicked")){
                    check=snapshot.getValue().toString();
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if(snapshot.getKey().equals("btnDelete_clicked")){
                    check=snapshot.getValue().toString();
                }
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
       return check;
    }
}
