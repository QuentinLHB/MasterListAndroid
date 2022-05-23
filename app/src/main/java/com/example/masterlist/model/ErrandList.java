package com.example.masterlist.model;

import java.util.ArrayList;
import java.util.List;

public class ErrandList {
    private String name = "";
    private final List<ErrandItem> items = new ArrayList<>();

    public ErrandList(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public int size(){
        return items.size();
    }

    public ErrandItem getItem(){
        return null; //todo
    }
    public void addItem(ErrandItem item){
        // todo
    }

    public void removeItem(ErrandItem item){
        // todo remove if exists
    }

    public void editItemQuantity(ErrandItem item, int newQuantity){
        // todo : if the errand is in the errand list, change its quantity
    }

    public void editItemName(ErrandItem item, String newName){
        // todo : if the errand is in the errand list, change its name
    }






}
