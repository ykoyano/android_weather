package com.example.user.weather.Repository;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import com.example.user.weather.model.GeoEntity;

public class GeoOperations {

    private DataBaseWrapper dbHelper;
    private String[] GEO_ENTITY_TABLE_COLUMNS = { DataBaseWrapper.GEO_ID, DataBaseWrapper.GEO_CITY, DataBaseWrapper.GEO_TOWN, DataBaseWrapper.GEO_X, DataBaseWrapper.GEO_Y};
    private SQLiteDatabase database;

    public GeoOperations(Context context) {
        dbHelper = new DataBaseWrapper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public GeoEntity addGeoEntity(GeoEntity geoEntity) {

        ContentValues values = new ContentValues();

        values.put(DataBaseWrapper.GEO_CITY, geoEntity.getCity());
        values.put(DataBaseWrapper.GEO_TOWN, geoEntity.getTown());
        values.put(DataBaseWrapper.GEO_X, geoEntity.getX());
        values.put(DataBaseWrapper.GEO_Y, geoEntity.getY());

        long id = database.insert(DataBaseWrapper.GEO_ENTITIES, null, values);

        Cursor cursor = database.query(DataBaseWrapper.GEO_ENTITIES, GEO_ENTITY_TABLE_COLUMNS, DataBaseWrapper.GEO_ID + " = " + id, null, null, null, null);

        cursor.moveToFirst();

        GeoEntity newComment = parseGeoEntity(cursor);
        cursor.close();
        return newComment;
    }

    public void deleteGeoEntity(GeoEntity comment) {
        long id = comment.getId();
        database.delete(DataBaseWrapper.GEO_ENTITIES, DataBaseWrapper.GEO_ID + " = " + id, null);
    }

    public List<GeoEntity> getAllGeoEntities() {
        List<GeoEntity> GeoEntities = new ArrayList();

        Cursor cursor = database.query(DataBaseWrapper.GEO_ENTITIES, GEO_ENTITY_TABLE_COLUMNS, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            GeoEntity geoEntity = parseGeoEntity(cursor);
            GeoEntities.add(geoEntity);
            cursor.moveToNext();
        }

        cursor.close();
        return GeoEntities;
    }

    private GeoEntity parseGeoEntity(Cursor cursor) {
        GeoEntity geoEntity = new GeoEntity(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getDouble(3), cursor.getDouble(4));
        return geoEntity;
    }
}