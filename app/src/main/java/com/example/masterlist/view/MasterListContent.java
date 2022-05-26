package com.example.masterlist.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.masterlist.R;

/**
 * View containing the content of a master list (its items).
 */
public class MasterListContent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master_list_content);
    }
}