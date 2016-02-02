package com.example.user.weather.logic;

import com.example.user.weather.request.GeoApi;
import rx.Observable;

import javax.inject.Inject;
import java.util.List;

public class GeoLogicImpl implements GeoLogic {

    private static final String GET_CITIES = "getCities";
    private static final String SUGGEST = "suggest";
    private static final String SEARCH_BY_LOCATION = "searchByGeoLocation";

    private GeoApi api;

    @Inject
    public GeoLogicImpl(GeoApi api) {
        this.api = api;
    }

    @Override
    public Observable<List<String>> getCities(String prefecture) {
        return api.getCities(GET_CITIES, prefecture);
    }
}