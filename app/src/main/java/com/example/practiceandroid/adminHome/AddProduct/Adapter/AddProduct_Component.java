package com.example.practiceandroid.adminHome.AddProduct.Adapter;

public class AddProduct_Component {
    String name;
    boolean checked = false;
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public AddProduct_Component(String name) {
        this.name = name;
    }

    public boolean isChecked(){return checked;}
    public void setChecked (boolean status){
        this.checked = status;
    }
}
