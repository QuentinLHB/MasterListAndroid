package com.example.masterlist.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.masterlist.Tools;
import com.example.masterlist.model.ErrandList;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LocalDB {

    private final MySQLiteOpenHelper dbAccess;
    private SQLiteDatabase db;


    public LocalDB(Context context) {
        dbAccess = new MySQLiteOpenHelper(context, "dbMasterList", 1);
    }

    private boolean genericDelete(String tableName, int id){
        return genericDelete(tableName, "id", id);
    }

    private boolean genericDelete(String tableName, String idKey, int idValue){
        db = dbAccess.getWritableDatabase();
        int rowsAffected = db.delete(tableName, idKey + "=?", new String[]{String.valueOf(idValue)});
        db.close();
        return rowsAffected > 0;
    }

    private boolean genericUpdate(String tableName, ContentValues contentValues, int id){
        boolean success = false;
        db = dbAccess.getWritableDatabase();
        if (contentValues.size() > 0) {
            int rowsAffected = db.update(tableName, contentValues, "id=?", new String[]{String.valueOf(id)});
            success = rowsAffected > 0;
        }
        db.close();
        return success;
    }

    //TODO generic Insert

    public List<ErrandList> selectAllLists(boolean isMaster) {

        List<ErrandList> errandLists = new ArrayList<>();
        db = dbAccess.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT id, name, lastModification, isMaster FROM " + dbAccess.getErrandListTableName() + " WHERE isMaster=?", new String[]{String.valueOf(Tools.convertBooleanToInt(isMaster))});
        cursor.moveToLast();
        if (!cursor.isAfterLast()) {
            ErrandList errandList = new ErrandList(
                    cursor.getInt(0),
                    cursor.getString(1),
                    Tools.convertStringDateToLocalDate(cursor.getString(2)),
                    Tools.convertIntToBoolean(cursor.getInt(3))
            );
            errandLists.add(errandList);
        }
        cursor.close();
        return errandLists;

    }

    public void insertIntoErrandList(String name, String lastModification, boolean isMaster) {
        db = dbAccess.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("lastModification", lastModification);
        values.put("isMaster", Tools.convertBooleanToInt(isMaster));
        db.insert(dbAccess.getErrandListTableName(), null, values);
        db.close();
    }

    public boolean updateErrandList(int id, String name, String lastModification) {
        ContentValues contentValues = new ContentValues();
        if (name != null) contentValues.put("name", name);
        if (lastModification != null) contentValues.put("lastModification", lastModification);
        return genericUpdate(dbAccess.getErrandListTableName(), contentValues, id);
    }

    public boolean deleteErrandList(int id){
        genericDelete(dbAccess.getErrandListItemTableName(), "idErrandList", id);
        return genericDelete(dbAccess.getErrandListTableName(), id);
    }

    public void insertIntoErrandItem(String name, boolean isQuantifiable) {
        db = dbAccess.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("isQuantifiable", Tools.convertBooleanToInt(isQuantifiable));
        db.insert(dbAccess.getErrandItemTableName(), null, values);
        db.close();
    }

    public boolean updateErrandItem(int id, String name, Boolean isQuantifiable) {
        boolean success = false;
        db = dbAccess.getWritableDatabase();
        ContentValues values = new ContentValues();
        if (name != null) values.put("name", name);
        if (isQuantifiable != null) values.put("lastModification", isQuantifiable ? 1 : 0);
        if (values.size() > 0) {
            int rowsAffected = db.update(dbAccess.getErrandItemTableName(), values, "id=?", new String[]{String.valueOf(id)});
            success = rowsAffected > 0;
        }
        db.close();
        return success;
    }

    //TODO delete ErrandItem (delete depencies from association table first)


    public void insertIntoListItem(int idErrandList, int idItem) {
        db = dbAccess.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("idErrandList", idErrandList);
        values.put("idItem", idItem);
        db.insert(dbAccess.getErrandListItemTableName(), null, values);
        db.close();
    }

    public boolean updateListItem(int idErrandList, int idItem, Boolean isChecked) {
        boolean success = false;
        db = dbAccess.getWritableDatabase();
        ContentValues values = new ContentValues();
        if (isChecked != null) values.put("isChecked", isChecked);
        if (values.size() > 0) {
            int rowsAffected = db.update(dbAccess.getErrandItemTableName(), values, "idErrandList=? AND idItem=?", new String[]{String.valueOf(idErrandList), String.valueOf(idItem)});
            success = rowsAffected > 0;
        }
        db.close();
        return success;
    }


    //TODO delete masterlist item


    //TODO insert, update (ischecked), delete errandlist item


}
