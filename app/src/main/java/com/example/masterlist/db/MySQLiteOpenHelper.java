package com.example.masterlist.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MySQLiteOpenHelper extends SQLiteOpenHelper {

    // propriété de création d'une table dans la base de données
    private String creationErrandList = "create table " + getErrandListTableName() +" ("
            + "id INTEGER PRIMARY KEY," +
            "name TEXT, " +
            "lastModification TEXT, " +
            "isMaster INTEGER);";

    private String creationErrandItem = "create table " + getErrandItemTableName() + " ("
            + "id INTEGER PRIMARY KEY," +
            "name TEXT," +
            "isQuantifiable INTEGER); ";

//    private String creationMasterListItems = "create table " + getErrandListItemTableName() + "(" +
//            "idErrandList INTEGER, " +
//            "idItem INTEGER, " +
//            "FOREIGN KEY(idErrandList) REFERENCES errandlist(id)," +
//            "FOREIGN KEY(idItem) REFERENCES erranditem(id)); ";

    private String creationErrandListItems = "create table " + getErrandListItemTableName() + "(" +
            "idErrandList INTEGER, " +
            "idItem INTEGER, " +
            "isChecked INTEGER, " +
            "FOREIGN KEY(idErrandList) REFERENCES errandlist(id)," +
            "FOREIGN KEY(idItem) REFERENCES erranditem(id)); ";



    public String getErrandListTableName(){ return "errandlist";}
    public String getErrandItemTableName(){ return "erranditem";}
    public String getErrandListItemTableName(){ return "masterlistitems";}


    /**
     * Construction de l'accès à une base de données locale
     *
     * @param context
     * @param name
     * @param version
     */
    public MySQLiteOpenHelper(Context context, String name, int version) {
        super(context, name, null, version);
    }

    /**
     * méthode redéfinie appelée automatiquement par le constructeur
     * uniquement si celui-ci repère que la base n'existe pas encore
     *
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(creationErrandList);
        db.execSQL(creationErrandItem);
        db.execSQL(creationErrandListItems);

    }

    /**
     * méthode redéfinie appelée automatiquement s'il y a changement de version de la base
     *
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub

    }

}