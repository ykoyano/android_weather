package com.example.user.weather.logic;

import com.example.user.weather.model.GeoEntity;
import com.example.user.weather.request.GeoApi;
import rx.Observable;

import javax.inject.Inject;
import java.util.List;

public class GeoLogicImpl implements GeoLogic {

    private static final String GET_AREAS = "getAreas";
    private static final String GET_PREFECTURES = "getPrefectures";
    private static final String GET_CITIES = "getCities";
    private static final String SUGGEST = "suggest";
    private static final String SEARCH_BY_LOCATION = "searchByGeoLocation";

    private GeoApi api;

    @Inject
    public GeoLogicImpl(GeoApi api) {
        this.api = api;
    }

    @Override
    public Observable<List<String>> getAreas() {
        return api.getAreas(GET_AREAS);
    }

    @Override
    public Observable<List<String>> getPrefectures(String area) {
        return api.getPrefectures(GET_PREFECTURES, area);
    }

    @Override
    public Observable<List<GeoEntity>> getCities(String area, String prefecture) {
        return api.getCities(GET_CITIES, area, prefecture);
    }

    @Override
    public Observable<List<GeoEntity>> getAddressByCoordinate(double lon, double lat) {
        return api.getAddressByCoordinate(SEARCH_BY_LOCATION, lon, lat);
    }

    @Override
    public Observable<List<GeoEntity>> getAddressByKeyword(String matching, String keyword) {
        return api.getAddressByKeyword(SUGGEST, matching, keyword);
    }
}