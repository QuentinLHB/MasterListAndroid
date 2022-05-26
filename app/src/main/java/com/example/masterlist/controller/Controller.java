package com.example.masterlist.controller;

import com.example.masterlist.model.ErrandList;

import java.util.ArrayList;
import java.util.List;

public class Controller {

    private static Controller instance = null;

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
        List<ErrandList> errandLists = new ArrayList<>();
        errandLists.add(new ErrandList("Super U"));
        return errandLists;
    }
}
