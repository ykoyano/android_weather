package com.example.user.weather.Repository;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseWrapper extends SQLiteOpenHelper {

    public static final String LOCATION = "locations";
    public static final String LOCATION_ID = "_id";
    public static final String LOCATION_CITY = "_city";
    public static final String LOCATION_TOWN = "_town";
    public static final String LOCATION_LON = "_lon";
    public static final String LOCATION_LAT = "_lat";

    private static final String DATABASE_NAME = "Locations.db";
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_CREATE = "create table " + LOCATION
            + "(" + LOCATION_ID + " integer primary key autoincrement, " + LOCATION_CITY + ", " + LOCATION_TOWN + ", " + LOCATION_LON + ", "
            + LOCATION_LAT + " text not null);";

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