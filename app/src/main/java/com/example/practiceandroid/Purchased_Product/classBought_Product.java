package com.example.practiceandroid.Purchased_Product;

public class classBought_Product {
    String idUser;
    String idProduct;
    String categoryProduct;
    String nameProduct;
    String priceProduct;
    String numberBought;
    String userName;
    String userPhoneNumber;
    String userAddress;
    // Ngày và giờ cụ thể lúc đặt mua
    String dayBought;
    // Đã nhận được hàng chưa, dùng để tiện thao tác với giao diện giao hàng
    Boolean DeliveringFinish;

    public classBought_Product(String idUser, String idProduct, String categoryProduct, String nameProduct, String priceProduct, String numberBought, String userName, String userPhoneNumber, String userAddress, String dayBought, Boolean deliveringFinish) {
        this.idUser = idUser;
        this.idProduct = idProduct;
        this.categoryProduct = categoryProduct;
        this.nameProduct = nameProduct;
        this.priceProduct = priceProduct;
        this.numberBought = numberBought;
        this.userName = userName;
        this.userPhoneNumber = userPhoneNumber;
        this.userAddress = userAddress;
        this.dayBought = dayBought;
        DeliveringFinish = deliveringFinish;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(String idProduct) {
        this.idProduct = idProduct;
    }

    public String getCategoryProduct() {
        return categoryProduct;
    }

    public void setCategoryProduct(String categoryProduct) {
        this.categoryProduct = categoryProduct;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public void setUserPhoneNumber(String userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
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
