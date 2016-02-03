package com.example.user.weather.logic;

import com.example.user.weather.model.Location;
import rx.Observable;

import java.util.List;

public interface LocationLogic {

    void save(Location location);

    void delete(Location location);

    List<Location> findAll();

    Observable<List<String>> getAreas();

    Observable<List<String>> getPrefectures(String area);

    Observable<List<Location>> getCities(String area, String prefecture);

    Observable<List<Location>> getAddressByCoordinate(double lon, double lat);

    Observable<List<Location>> getAddressByKeyword(String keyword);
}
