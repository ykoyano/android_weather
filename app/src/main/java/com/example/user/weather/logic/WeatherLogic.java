package com.example.user.weather.logic;

import com.example.user.weather.model.WeatherModel;
import com.example.user.weather.request.Request;
import com.example.user.weather.request.WeatherRequest;

import rx.Observable;

public final class WeatherLogic {

    private static final WeatherLogic INSTANCE = new WeatherLogic();

    public static WeatherLogic getInstance() {
        return INSTANCE;
    }

    private WeatherLogic() {
    }

    public Observable<WeatherModel> getWeather(String city) {
        return new Request()
                .connection()
                .create(WeatherRequest.class)
                .getWeather(city);
    }
}
