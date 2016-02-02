package com.example.user.weather.logic;

import rx.Observable;

import java.util.List;

public interface GeoLogic {

    Observable<List<String>> getCities(String prefecture);
}
