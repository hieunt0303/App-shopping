package com.example.practiceandroid.function;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class getShowCartFragment {
    public  static DatabaseReference mData= FirebaseDatabase.getInstance().getReference();
    public static String SHOW= "false";
    public static String get(){
        mData.child("show_CartFragment").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if(snapshot.getKey().toString().equals("bool"))
                    SHOW=snapshot.getValue().toString();
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
        return SHOW;
    }
    public static void set(String bool){
        mData.child("show_CartFragment").child("bool").setValue(bool);
    }
}
