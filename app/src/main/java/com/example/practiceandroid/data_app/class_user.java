package com.example.practiceandroid.data_app;

import org.w3c.dom.Text;

public class class_user {
    String id;
    String name_user;
    String address;
    String avatar;
    String email;
    String password;

    public class_user(String id, String name_user, String address, String avatar, String email, String password) {
        this.id = id;
        this.name_user = name_user;
        this.address = address;
        this.avatar = avatar;
        this.email = email;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName_user() {
        return name_user;
    }

    public void setName_user(String name_user) {
        this.name_user = name_user;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
