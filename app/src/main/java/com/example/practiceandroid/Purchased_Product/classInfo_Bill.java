package com.example.practiceandroid.Purchased_Product;

public class classInfo_Bill {
    String user_name;
    String total_price;
    String day;

    public classInfo_Bill(String user_name, String total_price, String day) {
        this.user_name = user_name;
        this.total_price = total_price;
        this.day = day;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getTotal_price() {
        return total_price;
    }

    public void setTotal_price(String total_price) {
        this.total_price = total_price;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
}
