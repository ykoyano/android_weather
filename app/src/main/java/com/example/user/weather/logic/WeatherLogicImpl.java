package com.example.user.weather.logic;

import com.example.user.weather.model.WeatherModel;
import com.example.user.weather.request.Request;
import com.example.user.weather.request.WeatherRequest;
import rx.Observable;

import javax.inject.Inject;

public class WeatherLogicImpl implements WeatherLogic{

    @Inject
    public WeatherLogicImpl() {
    }

    @Override
    public Observable<WeatherModel> getWeather(String city) {
        return new Request()
                .connection()
                .create(WeatherRequest.class)
                .getWeather(city);
    }
}
