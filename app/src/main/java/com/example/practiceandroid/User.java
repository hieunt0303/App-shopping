package com.example.practiceandroid;

public class User {
    public String address;
    public String avatar;
    public String email;
    public String id;
    public String name_user;
    public String password;
    public String phone;

    public User()
    {

    }
    public User(String add, String ava,  String email, String id, String userName, String password, String phone) {
        this.id = id;
        this.name_user = userName;
        this.email = email;
        this.password = password;
        this.address = add;
        this.avatar = ava;
        this.phone = phone;
    }
    public void setAddress(String add)
    {
        this.address = add;
    }
    public  void setPhone(String phone)
    {
        this.phone = phone;
    }
    public String getName_user() {
        return name_user;
    }
}
