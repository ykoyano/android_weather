package com.example.user.weather.logic;

import com.example.user.weather.model.weather.InformationEntity;
import com.example.user.weather.request.WeatherApi;
import rx.Observable;

import javax.inject.Inject;

public class WeatherLogicImpl implements WeatherLogic {

    private static final String APP_ID = "9da45a992a7fa31176ad812fe56581f2";

    private WeatherApi weatherApi;

    @Inject
    public WeatherLogicImpl(WeatherApi weatherApi) {
        this.weatherApi = weatherApi;
    }

    @Override
    public Observable<InformationEntity> getWeather(double lat, double lon) {
        return weatherApi.getWeather(lat, lon, APP_ID);
    }
}