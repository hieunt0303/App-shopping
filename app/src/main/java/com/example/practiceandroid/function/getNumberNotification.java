package com.example.practiceandroid.function;


import com.example.practiceandroid.Manhinh_Login;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class getNumberNotification {
    public  static DatabaseReference mData= FirebaseDatabase.getInstance().getReference();
    public static void setNumber(String number)
    {
        mData.child("text_Notification").child(Manhinh_Login.userlogin.getName_user()).child("number_Notification").setValue(number);
    }
}
