package com.example.masterlist.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LocalDB {

    private final MySQLiteOpenHelper dbAccess;
    private SQLiteDatabase db;


    public LocalDB(Context context) {
        dbAccess = new MySQLiteOpenHelper(context, "dbMasterList", 1);
    }

    public void insertIntoErrandListTable(String name, LocalDate lastModification, boolean isMaster) {
        db = dbAccess.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("lastModification", lastModification.toString());
        values.put("isMaster", isMaster ? 1 : 0);
        db.insert(dbAccess.getErrandItemTableName(), null, values);
        db.close();
    }

    public void updateErrandListTable(int id, String name, LocalDate lastModification) {
        db = dbAccess.getWritableDatabase();
        ContentValues values = new ContentValues();
        if (name != null) values.put("name", name);
        if (lastModification != null) values.put("lastModification", lastModification.toString());
        if (values.size() > 0) {
            db.update(dbAccess.getErrandItemTableName(), values, "id=?", new String[]{String.valueOf(id)});
        }
        db.close();
    }

    //TODO delete ErrandList (delete depencies from association table first)

    public void insertIntoErrandItemTable(String name, boolean isQuantifiable) {
        db = dbAccess.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("isQuantifiable", isQuantifiable ? 1 : 0);
        db.insert(dbAccess.getErrandItemTableName(), null, values);
        db.close();
    }

    public void updateErrandItemTable(int id, String name, Boolean isQuantifiable) {
        db = dbAccess.getWritableDatabase();
        ContentValues values = new ContentValues();
        if (name != null) values.put("name", name);
        if (isQuantifiable != null) values.put("lastModification", isQuantifiable ? 1 : 0);
        if (values.size() > 0) {
            db.update(dbAccess.getErrandItemTableName(), values, "id=?", new String[]{String.valueOf(id)});
        }
        db.close();
    }

    //TODO delete ErrandItem (delete depencies from association table first)


    public void insertIntoListItemTable(int idErrandList, int idItem) {
        db = dbAccess.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("idErrandList", idErrandList);
        values.put("idItem", idItem);
        db.insert(dbAccess.getErrandListItemTableName(), null, values);
        db.close();
    }

    public void updateListItemTable(int idErrandList, int idItem, Boolean isChecked) {
        db = dbAccess.getWritableDatabase();
        ContentValues values = new ContentValues();
        if (isChecked != null) values.put("isChecked", isChecked);
        if (values.size() > 0) {
            db.update(dbAccess.getErrandItemTableName(), values, "idErrandList=? AND idItem=?", new String[]{String.valueOf(idErrandList), String.valueOf(idItem)});
        }
        db.close();
    }


    //TODO delete masterlist item

    //TODO insert, update (ischecked), delete errandlist item


}
