package com.example.WalletWizard;

public class AddCategory {
    private String name;
    private float amount;
    private String category;

    public AddCategory(String name, float amount, String category){
        this.name = name;
        this.amount = amount;
        this.category = category;
    }
    public String getName(){
        return this.name;
    }

    public float getAmount(){
        return this.amount;
    }

    public String getCategory(){
        return this.category;
    }









}
