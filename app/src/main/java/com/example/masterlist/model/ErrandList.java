package com.example.masterlist.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ErrandList {


    private String name;
    private final List<ErrandItem> items = new ArrayList<>();

    public ErrandList(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int size() {
        return items.size();
    }

    public ErrandItem getItem(int position) {
        return items.get(position);
    }

//    public ErrandItem getItem(ErrandItem item) {
//        for (ErrandItem errandItem :
//                items) {
//            if(item.equals(errandItem)) return errandItem;
//        }
//    }



    /**
     * Adds an item to the errand list.
     *
     * @param item Item to add.
     * @return True if succeeded, else false.
     */
    public boolean addItem(ErrandItem item) {
        if (!items.contains(item)) {
            items.add(item);
            return true;
        }
        return false;
    }

    /**
     * Removes an item from the errand list.
     *
     * @param item Item to remove
     * @return True if the removal succeeded, else false.
     */
    public boolean removeItem(ErrandItem item) {
        return items.remove(item);
    }

    public void editItemQuantity(ErrandItem item, int newQuantity) {
        if (items.contains(item)) {
            item.setQuantity(newQuantity);
        }
    }

    public boolean editItemName(ErrandItem item, String newName) {
        if (items.contains(item)) {
            item.setName(newName);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format(Locale.FRANCE, "%s : %d objets", name, size());
    }
}
