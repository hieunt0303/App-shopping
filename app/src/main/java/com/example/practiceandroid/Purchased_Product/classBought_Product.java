package com.example.practiceandroid.Purchased_Product;

public class classBought_Product {
    String idUser;
    String nameProduct;
    String priceProduct;
    String numberBought;
    // Ngày và giờ cụ thể lúc đặt mua
    String dayBought;
    // Đã nhận được hàng chưa, dùng để tiện thao tác với giao diện giao hàng
    Boolean DeliveringFinish;

    public classBought_Product(String idUser, String nameProduct, String priceProduct, String numberBought, String dayBought, Boolean deliveringFinish) {
        this.idUser = idUser;
        this.nameProduct = nameProduct;
        this.priceProduct = priceProduct;
        this.numberBought = numberBought;
        this.dayBought = dayBought;
        DeliveringFinish = deliveringFinish;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public String getPriceProduct() {
        return priceProduct;
    }

    public void setPriceProduct(String priceProduct) {
        this.priceProduct = priceProduct;
    }

    public String getNumberBought() {
        return numberBought;
    }

    public void setNumberBought(String numberBought) {
        this.numberBought = numberBought;
    }

    public String getDayBought() {
        return dayBought;
    }

    public void setDayBought(String dayBought) {
        this.dayBought = dayBought;
    }

    public Boolean getDeliveringFinish() {
        return DeliveringFinish;
    }

    public void setDeliveringFinish(Boolean deliveringFinish) {
        DeliveringFinish = deliveringFinish;
    }
}
