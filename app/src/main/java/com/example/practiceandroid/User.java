package com.example.practiceandroid;

import android.widget.ImageView;

public class User {
    public String id;
    public String userName;
    public String password;
    public String email;
    public String address;
    public String phoneNumber;
    public ImageView avatar;



    public User(String id, String userName, String email, String password) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.address = "";
        this.phoneNumber = "";
        this.avatar = null;


    }
}
