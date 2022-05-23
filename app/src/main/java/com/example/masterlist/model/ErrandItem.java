package com.example.masterlist.model;

public class ErrandItem {
//    private int id;
    private String name = "";
    private int quantity = 1;
    private boolean isQuantifiable = true;

    public ErrandItem(String name, int quantity, boolean isQuantifiable) {
        this.name = name;
        this.quantity = quantity;
        this.isQuantifiable = isQuantifiable;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return isQuantifiable ? quantity : 0 ;
    }

    public boolean isQuantifiable(){
        return isQuantifiable;
    }
}
