package com.example.user.weather.logic;

import com.example.user.weather.model.WeatherModel;
import rx.Observable;

public interface WeatherLogic {

    Observable<WeatherModel> getWeather(String city);
}
