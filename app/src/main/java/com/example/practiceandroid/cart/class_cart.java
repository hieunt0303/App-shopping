package com.example.practiceandroid.cart;

public class class_cart {
    // Thông số ban đầu
    int id;
    String name_cart_product;
    String category_cart_product;
    String id_cart_product;
    String price_cart_product;



    // các nút thêm bớt và mua
    String number_product;
    Boolean checked;// có chọn nó hay chưa

    public class_cart(int id, String name_cart_product, String category_cart_product, String id_cart_product, String price_cart_product, String number_product, Boolean checked) {
        this.id = id;
        this.name_cart_product = name_cart_product;
        this.category_cart_product = category_cart_product;
        this.id_cart_product = id_cart_product;
        this.price_cart_product = price_cart_product;
        this.number_product = number_product;
        this.checked = checked;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName_cart_product() {
        return name_cart_product;
    }

    public void setName_cart_product(String name_cart_product) {
        this.name_cart_product = name_cart_product;
    }

    public String getCategory_cart_product() {
        return category_cart_product;
    }

    public void setCategory_cart_product(String category_cart_product) {
        this.category_cart_product = category_cart_product;
    }

    public String getId_cart_product() {
        return id_cart_product;
    }

    public void setId_cart_product(String id_cart_product) {
        this.id_cart_product = id_cart_product;
    }

    public String getPrice_cart_product() {
        return price_cart_product;
    }

    public void setPrice_cart_product(String price_cart_product) {
        this.price_cart_product = price_cart_product;
    }

    public String getNumber_product() {
        return number_product;
    }

    public void setNumber_product(String number_product) {
        this.number_product = number_product;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }
}
