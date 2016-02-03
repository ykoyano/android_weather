package com.example.user.weather.Repository;

import com.example.user.weather.model.GeoEntity;

import java.util.List;

public interface GeoRepository {

    void save(GeoEntity geoEntity);

    void delete(GeoEntity geoEntity);

    List<GeoEntity> findAll();
}
