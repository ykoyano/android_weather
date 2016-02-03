package com.example.user.weather.Repository;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseWrapper extends SQLiteOpenHelper {

    public static final String GEO_ENTITIES = "GeoEntities";
    public static final String GEO_ID = "_id";
    public static final String GEO_CITY = "_city";
    public static final String GEO_TOWN = "_town";
    public static final String GEO_X = "_x";
    public static final String GEO_Y = "_y";

    private static final String DATABASE_NAME = "GeoEntitys.db";
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_CREATE = "create table " + GEO_ENTITIES
            + "(" + GEO_ID + " integer primary key autoincrement, " + GEO_CITY + ", " + GEO_TOWN + ", " + GEO_X + ", "
            + GEO_Y + " text not null);";

    public DataBaseWrapper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }
}