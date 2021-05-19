package com.example.practiceandroid;

public class User {
    public String address;
    public String avatar;
    public String email;
    public String id;
    public String name_user;
    public String password;

    public User()
    {

    }
    public User(String add, String ava,  String email, String id, String userName, String password) {
        this.id = id;
        this.name_user = userName;
        this.email = email;
        this.password = password;
    }
}
