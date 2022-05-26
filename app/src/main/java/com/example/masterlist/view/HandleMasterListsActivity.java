package com.example.masterlist.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.masterlist.R;
import com.example.masterlist.controller.Controller;
import com.example.masterlist.model.ErrandList;

import java.util.List;

public class HandleMasterListsActivity extends AppCompatActivity {
    Controller controller;
    List<ErrandList> masterLists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handle_master_lists);
        init();
    }

    private void init(){
        this.controller = Controller.getInstance();
        masterLists = controller.getMasterLists();
        ListView lstMasterLists = findViewById(R.id.lstMasterLists);
        lstMasterLists.setAdapter(new MasterListsLayoutAdapter(masterLists, HandleMasterListsActivity.this));
    }
}