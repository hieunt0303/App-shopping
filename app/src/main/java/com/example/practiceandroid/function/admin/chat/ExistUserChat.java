package com.example.practiceandroid.function.admin.chat;

import com.example.practiceandroid.function.FIREBASE;

public class ExistUserChat {
    public  static boolean get(String nameUser){
        try {
            FIREBASE.MDATA.child("Message").child(nameUser).getKey();
            return true;
        }
        catch (Exception e){
            return false;
        }

    }
}
