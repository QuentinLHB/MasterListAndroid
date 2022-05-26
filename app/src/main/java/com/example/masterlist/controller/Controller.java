package com.example.masterlist.controller;

import com.example.masterlist.model.ErrandList;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    private static Controller instance = null;
    List<ErrandList> errandLists = new ArrayList<>();

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
        if(errandLists.isEmpty()){
            errandLists.add(new ErrandList("Super U", LocalDate.now()));
            errandLists.add(new ErrandList("BBG", LocalDate.now()));
        }
        else return  errandLists;


        return errandLists;
    }

    public void editMasterListName(ErrandList masterList, String newName){
        masterList.setName(newName);
    }
}
