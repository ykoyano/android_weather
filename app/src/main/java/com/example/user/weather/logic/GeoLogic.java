package com.example.user.weather.logic;

import com.example.user.weather.model.GeoEntity;
import rx.Observable;

import java.util.List;

public interface GeoLogic {

    Observable<List<String>> getAreas(String method);

    Observable<List<String>> getPrefectures(String method, String area);

    Observable<List<GeoEntity>> getCities(String prefecture);

    Observable<List<GeoEntity >> getAddressByCoordinate(double lon, double lat);

    Observable<List<GeoEntity>> getAddressByKeyword(String matching, String keyword);
}
