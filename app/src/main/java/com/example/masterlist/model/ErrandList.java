package com.example.masterlist.model;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ErrandList {

    public int getId() {
        return id;
    }

    public boolean isMaster() {
        return isMaster;
    }

    private int id;
    private String name;
    private LocalDateTime lastModification;
    private boolean isMaster = false;
    private final List<ErrandItem> items = new ArrayList<>();


    public ErrandList(String name, LocalDateTime lastModification) {
        this.name = name;
        this.lastModification = lastModification;

    }

    public ErrandList(int id, String name, LocalDateTime lastModification, boolean isMaster) {
        this(name, lastModification);
        this.id = id;
        this.isMaster = isMaster;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        updateModificationDate();
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
            updateModificationDate();
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
        if (items.remove(item)){
            updateModificationDate();
            return true;
        }
        return false;
    }

    public boolean editItemQuantity(ErrandItem item, int newQuantity) {
        if (items.contains(item)) {
            item.setQuantity(newQuantity);
            updateModificationDate();
            return true;
        }
        return false;
    }

    public boolean editItemName(ErrandItem item, String newName) {
        if (items.contains(item)) {
            item.setName(newName);
            updateModificationDate();
            return true;
        }
        return false;
    }

    private void updateModificationDate() {
        lastModification = LocalDateTime.now();
    }


    @Override
    public String toString() {
        return String.format(Locale.FRANCE, "%s : %d objets", "modifié le " +  lastModification.format(DateTimeFormatter.ofPattern("dd/MM/yyyy 'à' HH:mm")), size());
    }


}
