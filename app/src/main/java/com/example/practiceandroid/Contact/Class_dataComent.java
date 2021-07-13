package com.example.practiceandroid.Contact;

public class Class_dataComent {
      String Categories;
      String Comment;
      String userName;
      String productID;
       String productName;
       String Star = "0";

    public Class_dataComent(String categories, String comment, String userName, String productID, String productName, String star) {
        Categories = categories;
        Comment = comment;
        this.userName = userName;
        this.productID = productID;
        this.productName = productName;
        Star = star;
    }

    public String getCategories() {
        return Categories;
    }

    public void setCategories(String categories) {
        Categories = categories;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String comment) {
        Comment = comment;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getStar() {
        return Star;
    }

    public void setStar(String star) {
        Star = star;
    }
}
