package com.example.masterlist.model;

public class ErrandItem {


    private int id;
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

    public void setName(String name) {
        if(!name.equals("")) this.name = name;
    }

    public int getQuantity() {
        return isQuantifiable ? quantity : 0 ;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isQuantifiable(){
        return isQuantifiable;
    }

    public void isQuantifiable(boolean isQuantifiable){
        if(this.isQuantifiable && !isQuantifiable){
            quantity = 0;
        }
        else if (!this.isQuantifiable && isQuantifiable){
            quantity = 1;
        }
        this.isQuantifiable = isQuantifiable;
    }
}
