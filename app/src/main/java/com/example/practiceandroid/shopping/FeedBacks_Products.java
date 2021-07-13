package com.example.practiceandroid.shopping;

import com.google.firebase.firestore.DocumentSnapshot;

public class FeedBacks_Products{
    String productID;
    String productName;
    String productCategory;
    String productPrice;
    String userName;
    String productComment;

    public FeedBacks_Products(){
        //Do nothing
    }

    public FeedBacks_Products(String productID, String productName, String productCategory, String productPrice, String userName) {
        this.productID = productID;
        this.productName = productName;
        this.productCategory = productCategory;
        this.productPrice = productPrice;
        this.userName = userName;
    }

    public String getProductComment() {
        return productComment;
    }

    public void setProductComment(String productComment) {
        this.productComment = productComment;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
