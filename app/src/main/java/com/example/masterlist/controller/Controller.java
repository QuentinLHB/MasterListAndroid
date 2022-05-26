package com.example.masterlist.controller;

import com.example.masterlist.model.ErrandList;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    private static Controller instance = null;
    List<ErrandList> masterLists = new ArrayList<>();

    /**
     * Private constructor
     */
    private Controller() {
        super();
    }

    /**
     * Returns the unique instance of the controller.
     * @return instance
     */
    public static Controller getInstance() {
        if (Controller.instance == null) {
            Controller.instance = new Controller();
            return Controller.instance;
        }
        return Controller.instance;
    }

    public List<ErrandList> getMasterLists(){
        //todo
        if(masterLists.isEmpty()){
            masterLists.add(new ErrandList("Super U", LocalDate.now()));
            masterLists.add(new ErrandList("BBG", LocalDate.now()));
        }
        else return masterLists;


        return masterLists;
    }

    public boolean editMasterListName(ErrandList masterList, String newName){
        if(newName.isEmpty()) return false;
        masterList.setName(newName);
        return true;
    }

    public boolean removeMasterList(ErrandList masterList){
        if(masterLists.remove(masterList)){
            return true;
        }
        return false;
    }
}
