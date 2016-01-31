package com.example.user.weather.logic;

import com.example.user.weather.model.WeatherModel;
import com.example.user.weather.request.Request;
import com.example.user.weather.request.WeatherRequest;

import rx.Observable;

public final class WeatherLogicTest {

    private static final WeatherLogicTest INSTANCE = new WeatherLogicTest();

    public static WeatherLogicTest getInstance() {
        return INSTANCE;
    }

    private WeatherLogicTest() {
    }

    public Observable<WeatherModel> getWeather(String city) {
        return new Request()
                .connection()
                .create(WeatherRequest.class)
                .getWeather(city);
    }
}
