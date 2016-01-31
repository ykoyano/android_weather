package com.example.user.weather.logic;

import com.example.user.weather.model.WeatherModel;
import com.example.user.weather.request.WeatherApi;
import rx.Observable;

import javax.inject.Inject;

public class WeatherLogicImpl implements WeatherLogic {

    private WeatherApi weatherApi;

    @Inject
    public WeatherLogicImpl(WeatherApi weatherApi) {
        this.weatherApi = weatherApi;
    }

    @Override
    public Observable<WeatherModel> getWeather(String city) {
        return weatherApi.getWeather(city);
    }
}