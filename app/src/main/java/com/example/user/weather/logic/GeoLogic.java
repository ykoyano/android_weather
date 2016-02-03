package com.example.user.weather.logic;

import com.example.user.weather.model.GeoEntity;
import rx.Observable;

import java.util.List;

public interface GeoLogic {

    void save(GeoEntity geoEntity);

    void delete(GeoEntity geoEntity);

    List<GeoEntity> findAll();

    Observable<List<String>> getAreas();

    Observable<List<String>> getPrefectures(String area);

    Observable<List<GeoEntity>> getCities(String area, String prefecture);

    Observable<List<GeoEntity>> getAddressByCoordinate(double lon, double lat);

    Observable<List<GeoEntity>> getAddressByKeyword(String keyword);
}
