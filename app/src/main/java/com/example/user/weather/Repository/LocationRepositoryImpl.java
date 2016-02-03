package com.example.user.weather.Repository;

import android.content.Context;
import com.example.user.weather.model.Location;

import javax.inject.Inject;
import java.util.List;

public class LocationRepositoryImpl implements LocationRepository {

    private Context context;

    @Inject
    public LocationRepositoryImpl(Context context) {
        this.context = context;
    }

    @Override
    public void save(Location location) {
        LocationOperations locationOperations = new LocationOperations(context);
        locationOperations.open();
        locationOperations.save(location);
        locationOperations.close();
    }

    @Override
    public void delete(Location location) {
        LocationOperations locationOperations = new LocationOperations(context);
        locationOperations.open();
        locationOperations.delete(location);
        locationOperations.close();
    }

    @Override
    public List<Location> findAll() {
        LocationOperations locationOperations = new LocationOperations(context);
        locationOperations.open();
        List<Location> entities = locationOperations.findAll();
        locationOperations.close();
        return entities;
    }

}