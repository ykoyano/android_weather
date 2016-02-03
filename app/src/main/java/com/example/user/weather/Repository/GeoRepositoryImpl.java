package com.example.user.weather.Repository;

import android.content.Context;
import com.example.user.weather.model.GeoEntity;

import javax.inject.Inject;
import java.util.List;

public class GeoRepositoryImpl implements GeoRepository {

    private Context context;

    @Inject
    public GeoRepositoryImpl(Context context) {
        this.context = context;
    }

    @Override
    public void save(GeoEntity geoEntity) {
        GeoOperations geoOperations = new GeoOperations(context);
        geoOperations.open();
        geoOperations.addGeoEntity(geoEntity);
        geoOperations.close();
    }

    @Override
    public void delete(GeoEntity geoEntity) {
        GeoOperations geoOperations = new GeoOperations(context);
        geoOperations.open();
        geoOperations.deleteGeoEntity(geoEntity);
        geoOperations.close();
    }

    @Override
    public List<GeoEntity> findAll() {
        GeoOperations geoOperations = new GeoOperations(context);
        geoOperations.open();
        List<GeoEntity> entities = geoOperations.getAllGeoEntities();
        geoOperations.close();
        return entities;
    }

}