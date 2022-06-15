package com.example.masterlist.controller;

import android.content.Context;

import com.example.masterlist.Tools;
import com.example.masterlist.db.LocalDB;
import com.example.masterlist.model.ErrandList;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    private static Controller instance = null;
    List<ErrandList> masterLists = new ArrayList<>();
    LocalDB localDB;

    /**
     * Private constructor
     */
    private Controller(Context context) {
        super();
        localDB = new LocalDB(context);
    }

    /**
     * Returns the unique instance of the controller.
     *
     * @return instance
     */
    public static Controller getInstance(Context context) {
        if (Controller.instance == null) {
            Controller.instance = new Controller(context);
            return Controller.instance;
        }
        return Controller.instance;
    }

    public List<ErrandList> getMasterLists() {
        //todo
        System.out.println("local date sans format : " + LocalDateTime.now());
        System.out.println("local date avec format : " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")));
//        localDB.insertIntoErrandListTable("coop", LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")), true);


        if (masterLists.isEmpty()) {
//            masterLists.add(new ErrandList("Super U", LocalDate.now()));
//            masterLists.add(new ErrandList("BBG", LocalDate.now()));
            localDB.insertIntoErrandList("coop", Tools.getCurrentDateTimeFormattedString(), true);
            masterLists.addAll(localDB.selectAllLists(true));
        } else return masterLists;
        return masterLists;
    }

    public boolean editMasterListName(ErrandList masterList, String newName) {
        if (newName.isEmpty()) return false;
        if(localDB.updateErrandList(masterList.getId(), newName, Tools.getCurrentDateTimeFormattedString())){
            masterList.setName(newName);
            return true;
        }
        return false;
    }

    public boolean removeMasterList(ErrandList masterList) {
        if(localDB.deleteErrandList(masterList.getId())){
            return masterLists.remove(masterList);
        }
        return false;


    }
}
