package com.example.practiceandroid;

public class class_ThongTinSanPham {
    //nên đặt mấy cái thành phần là public để sau này trong adapter còn tái sử dụng lại
    public  String name;
    public  int price;
    public String currency;// đơn vị tính
    public int image_phone;
    public int image_avatar;
    public int image_share;
    public  int image_heart;
    public int rating_star;

    public class_ThongTinSanPham(String name, int price, String currency, int image_phone, int image_avatar, int image_share, int image_heart, int rating_star) {
        this.name = name;
        this.price = price;
        this.currency = currency;
        this.image_phone = image_phone;
        this.image_avatar = image_avatar;
        this.image_share = image_share;
        this.image_heart = image_heart;
        this.rating_star = rating_star;
    }
}
