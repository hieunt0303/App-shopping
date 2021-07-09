package com.example.practiceandroid.home;

public class class_Information_Product {

    public String id_product;
    public String categories;
    public String name_product;
    public String price_product_real;
    public String discount;
    public String price_product_fake;
    public String Sum_Ratingbar;
    public int in_stock;
    public int Sum_Bought;
    public Boolean favourite;
    public Boolean add_to_cart;
    public String description;
    public String detail;
    public int color1;
    public  int color2;
    public int Img_border;

    public String getPrice_product_real() {
        return price_product_real;
    }

    public void setPrice_product_real(String price_product_real) {
        this.price_product_real = price_product_real;
    }

    public class_Information_Product(String id_product, String categories, String name_product,
                                     String price_product_real, String discount, String price_product_fake,
                                     String sum_Ratingbar,
                                     int in_stock, int sum_Bought,
                                     Boolean favourite, Boolean add_to_cart,
                                     String description, String detail,
                                     int color1, int color2,
                                     int img_border) {
        this.id_product = id_product;
        this.categories = categories;
        this.name_product = name_product;
        this.price_product_real = price_product_real;
        this.discount = discount;
        this.price_product_fake = price_product_fake;
        Sum_Ratingbar = sum_Ratingbar;
        this.in_stock = in_stock;
        Sum_Bought = sum_Bought;
        this.favourite = favourite;
        this.add_to_cart = add_to_cart;
        this.description = description;
        this.detail = detail;
        this.color1 = color1;
        this.color2 = color2;
        Img_border = img_border;
    }
}
