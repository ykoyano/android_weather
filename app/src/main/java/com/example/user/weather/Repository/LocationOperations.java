package com.example.user.weather.Repository;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import com.example.user.weather.model.Location;

public class LocationOperations {

    private DataBaseWrapper dbHelper;
    private String[] LOCATION_TABLE_COLUMNS =
            { DataBaseWrapper.LOCATION_ID, DataBaseWrapper.LOCATION_CITY, DataBaseWrapper.LOCATION_TOWN, DataBaseWrapper.LOCATION_LON, DataBaseWrapper.LOCATION_LAT};
    private SQLiteDatabase database;

    public LocationOperations(Context context) {
        dbHelper = new DataBaseWrapper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Location save(Location location) {

        ContentValues values = new ContentValues();

        values.put(DataBaseWrapper.LOCATION_CITY, location.getCity());
        values.put(DataBaseWrapper.LOCATION_TOWN, location.getTown());
        values.put(DataBaseWrapper.LOCATION_LON, location.getLon());
        values.put(DataBaseWrapper.LOCATION_LAT, location.getLat());

        long id = database.insert(DataBaseWrapper.LOCATION, null, values);

        Cursor cursor = database.query(DataBaseWrapper.LOCATION, LOCATION_TABLE_COLUMNS, DataBaseWrapper.LOCATION_ID + " = " + id, null, null, null, null);

        cursor.moveToFirst();

        Location newComment = parseGeoEntity(cursor);
        cursor.close();
        return newComment;
    }

    public void delete(Location location) {
        long id = location.getId();
        database.delete(DataBaseWrapper.LOCATION, DataBaseWrapper.LOCATION_ID + " = " + id, null);
    }

    public List<Location> findAll() {
        List<Location> locations = new ArrayList();

        Cursor cursor = database.query(DataBaseWrapper.LOCATION, LOCATION_TABLE_COLUMNS, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Location location = parseGeoEntity(cursor);
            locations.add(location);
            cursor.moveToNext();
        }

        cursor.close();
        return locations;
    }

    private Location parseGeoEntity(Cursor cursor) {
        return new Location(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getDouble(3), cursor.getDouble(4));
    }
}