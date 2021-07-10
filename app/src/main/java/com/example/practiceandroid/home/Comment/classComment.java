package com.example.practiceandroid.home.Comment;

import com.example.practiceandroid.Manhinh_Login;

public class classComment {
    public String id_product;
    public String categories;
    public String name_product;
    public String id_User;
    public String sum_Star;
    public String cmt;
    public classComment(){

    }
    public classComment(String id_product, String categories, String name_product, String id_User, String sum_Star, String cmt) {
        this.id_product = id_product;
        this.categories = categories;
        this.name_product = name_product;
        this.id_User = id_User;
        this.sum_Star = sum_Star;
        this.cmt = cmt;
    }

    public String getId_product() {
        return id_product;
    }

    public void setId_product(String id_product) {
        this.id_product = id_product;
    }

    public String getCategories() {
        return categories;
    }

    public String getSum_Star() {
        return sum_Star;
    }

    public void setSum_Star(String sum_Star) {
        this.sum_Star = sum_Star;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public String getName_product() {
        return name_product;
    }

    public void setName_product(String name_product) {
        this.name_product = name_product;
    }

    public String getId_User() {
        return id_User;
    }

    public void setId_User(String id_User) {
        this.id_User = id_User;
    }



    public String getCmt() {
        return cmt;
    }

    public void setCmt(String cmt) {
        this.cmt = cmt;
    }
}
