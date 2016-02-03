package com.example.user.weather.logic;

import com.example.user.weather.Repository.LocationRepository;
import com.example.user.weather.model.Location;
import com.example.user.weather.request.LocationApi;
import rx.Observable;

import javax.inject.Inject;
import java.util.List;

public class LocationLogicImpl implements LocationLogic {

    private static final String GET_AREAS = "getAreas";
    private static final String GET_PREFECTURES = "getPrefectures";
    private static final String GET_CITIES = "getCities";
    private static final String SUGGEST = "suggest";
    private static final String SEARCH_BY_LOCATION = "searchByGeoLocation";
    private static final String MATCHING_LIKE = "like";

    private LocationApi api;
    private LocationRepository repository;

    @Inject
    public LocationLogicImpl(LocationApi api, LocationRepository repository) {
        this.api = api;
        this.repository = repository;
    }

    @Override
    public void save(Location location) {
        this.repository.save(location);
    }

    @Override
    public void delete(Location location){
        this.repository.delete(location);
    }

    @Override
    public List<Location> findAll() {
        return this.repository.findAll();
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
    public Observable<List<Location>> getCities(String area, String prefecture) {
        return api.getCities(GET_CITIES, area, prefecture);
    }

    @Override
    public Observable<List<Location>> getAddressByCoordinate(double lon, double lat) {
        return api.getAddressByCoordinate(SEARCH_BY_LOCATION, lon, lat);
    }

    @Override
    public Observable<List<Location>> getAddressByKeyword(String keyword) {
        return api.getAddressByKeyword(SUGGEST, MATCHING_LIKE, keyword);
    }
}