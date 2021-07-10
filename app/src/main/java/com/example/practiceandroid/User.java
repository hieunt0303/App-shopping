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

    public User(String address, String avatar, String email, String id, String name_user, String password, String phone) {
        this.address = address;
        this.avatar = avatar;
        this.email = email;
        this.id = id;
        this.name_user = name_user;
        this.password = password;
        this.phone = phone;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
